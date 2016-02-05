package com.axeleroy.adressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EntryAdapter extends ArrayAdapter<Entry> {
    public EntryAdapter(Context context, List<Entry> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entry entry = getItem(position);

        if (convertView == null);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_entry,parent, false);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.textViewName);

        nameTextView.setText(entry.getSurname() + " " + entry.getLastname());

        return convertView;
    }
}
