package com.axeleroy.adressbook.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.axeleroy.adressbook.fragments.DetailsFragment;
import com.axeleroy.adressbook.models.Entry;
import com.axeleroy.adressbook.fragments.EntryListFragment;
import com.axeleroy.adressbook.R;

public class MainActivity extends AppCompatActivity implements EntryListFragment.OnEntrySelectedListener {
    private final static String LOG_TAG = MainActivity.class.getCanonicalName();

    private DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), InsertActivity.class);
                startActivity(intent);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container_main, new EntryListFragment());
        // L'appareil est à l'horizontal
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            detailsFragment = new DetailsFragment();
            ft.add(R.id.container_details, detailsFragment);
        }
        ft.commit();
    }


    @Override
    public void onEntrySelected(Entry entry) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            detailsFragment.populate(entry);
        } else { // L'appareil à la verticale, on lance une nouvelle activité
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("entry", entry);
            startActivity(intent);
        }
    }
}
