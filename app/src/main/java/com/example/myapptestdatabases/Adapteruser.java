package com.example.myapptestdatabases;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapteruser extends ArrayAdapter<User> {
    public Adapteruser(Context context, List<User> objects) {
        super(context, 0,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User u=getItem(position);
        ((TextView)convertView.findViewById(R.id.lblnom)).setText(u.getNom());
        ((TextView)convertView.findViewById(R.id.lblville)).setText(u.getVille());

        return convertView;
    }
}
