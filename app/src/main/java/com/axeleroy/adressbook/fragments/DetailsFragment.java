package com.axeleroy.adressbook.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.axeleroy.adressbook.models.Entry;
import com.axeleroy.adressbook.R;

public class DetailsFragment extends Fragment {
    TextView lastNameTv;
    TextView surnameTv;
    TextView numberTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        lastNameTv = (TextView) rootView.findViewById(R.id.textViewLastName);
        surnameTv= (TextView) rootView.findViewById(R.id.textViewSurname);
        numberTv = (TextView) rootView.findViewById(R.id.textViewNumber);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            populate((Entry)bundle.getParcelable("entry"));
        }

        return rootView;
    }

    public void populate(Entry entry) {
        if (entry != null) {
            lastNameTv.setText(entry.getLastname());
            surnameTv.setText(entry.getSurname());
            numberTv.setText(entry.getNumber());
        }
    }
}
