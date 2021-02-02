package com.example.mytodolist;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private List<MyItem> notesList = new ArrayList<MyItem>();

    public CustomArrayAdapter(@NonNull Context context, List<MyItem> list) {
        super(context, 0 , list);
        mContext = context;
        notesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_delete,parent,false);

        MyItem currentItem = notesList.get(position);


        TextView title = (TextView) listItem.findViewById(R.id.del_item);
        title.setText(currentItem.getTitle());

        TextView release = (TextView) listItem.findViewById(R.id.del_item_desc);
        release.setText(currentItem.getDescription());

        return listItem;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
