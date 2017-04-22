package com.halfdotfull.sabswipesub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;

import static java.sql.Types.NULL;

public class topicswipe extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<swipeDetails> mSwipeDetails;
    Adapter mAdapter;
    DatabaseReference mDatabase;
    Query mdat;
    SharedPreferences msharedpreferences;
    int size;
    HashMap<String,Integer > meMap=new HashMap<String,Integer>();
    swipeDetails data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msharedpreferences = getSharedPreferences("sab", Context.MODE_PRIVATE);//To display MESSAGE
        setContentView(R.layout.activity_topicswipe);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycle);
        mSwipeDetails=new ArrayList<>();
        mAdapter=new Adapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mdat=mDatabase.child("profadded");
        mdat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   data=postSnapshot.getValue(swipeDetails.class);
                    Toast.makeText(topicswipe.this, "" + data.getProfname(), Toast.LENGTH_SHORT).show();
                    mSwipeDetails.add(data);                }
            mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("swd", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });



        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(mAdapter, true, true,
                true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }
    public class VH extends RecyclerView.ViewHolder implements RVHViewHolder{
        TextView swipeName,swipeProf;

        public VH(View itemView) {
            super(itemView);
            swipeName= (TextView) itemView.findViewById(R.id.swipeTopic);
            swipeProf= (TextView) itemView.findViewById(R.id.swipeProf);
        }

        @Override
        public void onItemSelected(int actionstate) {

        }

        @Override
        public void onItemClear() {

        }
    }
    public class Adapter extends RecyclerView.Adapter<VH> implements RVHAdapter{

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=getLayoutInflater();
            View v=inflater.inflate(R.layout.view_wipe,parent,false);

            return new VH(v);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            swipeDetails s=mSwipeDetails.get(position);
            holder.swipeProf.setText(s.getProfname());
            holder.swipeName.setText(s.getTopicName());
        }

        @Override
        public int getItemCount() {
            size=mSwipeDetails.size();
            return mSwipeDetails.size();
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            swap(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onItemDismiss(int position, int direction) {
            if(direction==32)
            {

                if(meMap.get(mSwipeDetails.get(position).getTopicName())==null)
                {
                    meMap.put(mSwipeDetails.get(position).getTopicName(),1);
                }
                else
                {
                    meMap.put(mSwipeDetails.get(position).getTopicName(),meMap.get(mSwipeDetails.get(position).getTopicName())+1);
                }

            }



            size--;
            if(size==0)
            {    for (Map.Entry<String, Integer> entry : meMap.entrySet())
            {
                sub hey=new sub();
                hey.setSubject(entry.getKey());
                hey.setCount(entry.getValue());
                msharedpreferences=getSharedPreferences("sab", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=msharedpreferences.edit();
                if(msharedpreferences.getInt(hey.getSubject(),0)==NULL)
                    editor.putInt(hey.getSubject(),hey.getCount());
                else
                    editor.putInt(hey.getSubject(),msharedpreferences.getInt(hey.getSubject(),0)+hey.getCount());
                editor.apply();
                mDatabase.child("stdentadeed").child(hey.getSubject()).setValue(msharedpreferences.getInt(hey.getSubject(),0));


            }
            }
            remove(position);
        }
        private void remove(int position) {
            mSwipeDetails.remove(position);

            notifyItemRemoved(position);
        }

        private void swap(int firstPosition, int secondPosition) {
            Collections.swap(mSwipeDetails, firstPosition, secondPosition);
            notifyItemMoved(firstPosition, secondPosition);
        }
    }

}
