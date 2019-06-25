package com.example.icloset;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> mData;


    public RecyclerViewAdapter(Context mContext, List<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item,viewGroup,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.item_thumbnail.setImageResource(mData.get(i).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView item_thumbnail;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_thumbnail = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}
