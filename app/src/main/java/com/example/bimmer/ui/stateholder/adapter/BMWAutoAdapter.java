package com.example.bimmer.ui.stateholder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bimmer.Item;
import com.example.bimmer.R;

import java.util.List;

public class BMWAutoAdapter extends RecyclerView.Adapter< BMWAutoAdapter.ViewHolder>{
    public interface OnStateClickListener{
        void onStateClick(Item item, int position);
    }
    private final OnStateClickListener onClickListener;

    private final LayoutInflater inflater;
    private final List<Item> items;
    public BMWAutoAdapter(OnStateClickListener onClickListener, Context context, List<Item> items1) {
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(context);
        this.items = items1;
    }
    @NonNull
    @Override
    public BMWAutoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bmw_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BMWAutoAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bmwModel.setText(item.getName());
        holder.bmwImage.setImageResource(item.getImageResource());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                onClickListener.onStateClick(item, position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView bmwModel;
        final ImageView bmwImage;
        ViewHolder(View view){
            super(view);
            bmwModel = view.findViewById(R.id.bmwModel);
            bmwImage = view.findViewById(R.id.bmwImage);
        }
    }
}