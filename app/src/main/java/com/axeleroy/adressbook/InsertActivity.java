package com.axeleroy.adressbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    Entry entry;

    EditText etLastName;
    EditText etSurname;
    EditText etPhoneNumber;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setEntry();
        outState.putParcelable("ENTRY", entry);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_contact);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        etLastName = (EditText) findViewById(R.id.input_lastname);
        etSurname = (EditText) findViewById(R.id.input_surname);
        etPhoneNumber = (EditText) findViewById(R.id.input_number);

        if (savedInstanceState != null) {
            entry = savedInstanceState.getParcelable("ENTRY");

            etLastName.setText(entry.getLastname());
            etSurname.setText(entry.getSurname());
            etPhoneNumber.setText(entry.getNumber());
        } else {
            entry = new Entry();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            setEntry();
            FileManager.writeEntry(entry, this);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setEntry() {
        entry.setLastname(etLastName.getText().toString());
        entry.setSurname(etSurname.getText().toString());
        entry.setNumber(etPhoneNumber.getText().toString());
    }
}
