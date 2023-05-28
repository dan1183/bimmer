package com.example.bimmer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BMWSecondAdapter extends ArrayAdapter<Item> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<Item> items;
    public BMWSecondAdapter(BMWAutoAdapter.OnStateClickListener onClickListener, Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(this.layout, parent, false);
        TextView textView = view.findViewById(R.id.textView);
        Item item = items.get(position);
        textView.setText(item.getName());
        return view;
    }
}
