package com.axeleroy.adressbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class EntryListFragment extends Fragment {
    private final static String LOG_TAG = EntryListFragment.class.getCanonicalName();

    ArrayAdapter<Entry> arrayAdapter;
    ListView entriesListView;
    OnEntrySelectedListener callback;

    public interface OnEntrySelectedListener {
        public void onEntrySelected(Entry entry);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnEntrySelectedListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_entry_list, container, false);
        entriesListView = (ListView) rootView.findViewById(R.id.listViewEntries);

        return rootView;
    }

    private void populateView() {
        final List<Entry> entries = FileManager.getEntries(getContext());

        arrayAdapter = new EntryAdapter(getContext(), entries);
        entriesListView.setAdapter(arrayAdapter);
        entriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry entry = (Entry) parent.getItemAtPosition(position);
                callback.onEntrySelected(entry);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        populateView();
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    arrayAdapter.getFilter().filter(newText);
                    return true;
                }
            });
        }
    }
}
