package com.example.root.multilayoutlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by root on 13/3/16.
 */
public class RecAdapter extends RecyclerView.Adapter {
    private static final int TYPE_BATMAN = 1;
    private static final int TYPE_SUPERMAN = 2;
    private ArrayList<String> myItems = new ArrayList<>();
    Boolean[] booleans = new Boolean[30];
    public RecAdapter(ArrayList<String> myItems) {
        this.myItems=myItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Arrays.fill(booleans,false);
        switch (viewType) {
            case TYPE_BATMAN:
                return new BatviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card1_layout, parent, false));
            case TYPE_SUPERMAN:
                return new SuperViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card2_layout,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {

            case TYPE_BATMAN:
                BatviewHolder batviewHolder = (BatviewHolder) holder;
                batviewHolder.img.setImageResource(R.drawable.batman);
                batviewHolder.txt.setText("Batman");
                break;

            case TYPE_SUPERMAN:
                final String My = myItems.get(position);
                final SuperViewHolder superViewHolder = (SuperViewHolder) holder;
                superViewHolder.superimg.setImageResource(R.drawable.superman);
                superViewHolder.supertxt.setText("Superman");
                superViewHolder.checkBox.setOnCheckedChangeListener(null);
                superViewHolder.checkBox.setChecked(booleans[position]);
                superViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked)
                        {
                            booleans[position]=true;
                        }
                        else
                        {
                            booleans[position]=false;
                        }
                    }
                });
             //   superViewHolder.checkBox.setChecked(My.)

                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position%2 == 0 ? TYPE_BATMAN : TYPE_SUPERMAN;
    }

    @Override
    public int getItemCount() {
        return 30;
    }
    public class BatviewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;
        public BatviewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.batimg);
            txt = (TextView) itemView.findViewById(R.id.batman);
        }
    }
    public class SuperViewHolder extends RecyclerView.ViewHolder{
        ImageView superimg;
        TextView supertxt;
        CheckBox checkBox;

        public SuperViewHolder(View itemView) {
            super(itemView);
            superimg = (ImageView) itemView.findViewById(R.id.supermanimg);
            supertxt = (TextView) itemView.findViewById(R.id.supertxt);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
