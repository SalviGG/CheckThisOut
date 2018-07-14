package com.example.pablorjd.CheckThisOut.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pablorjd.CheckThisOut.R;
import com.example.pablorjd.CheckThisOut.utils.UserSession;

public class SettingsFragment extends Fragment {

    private EditText etPrefUsername;
    private EditText etPrefPassword;
    private EditText etPrefEmail;
    private TextView tvPrefUsername;
    private TextView tvPrefEmail;
    private Button btnCambios;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_settings, container, false);

        etPrefUsername = (EditText) rootView.findViewById(R.id.etPrefUsername);
        etPrefPassword = (EditText) rootView.findViewById(R.id.etPrefPassword);
        etPrefEmail = (EditText) rootView.findViewById(R.id.etPrefEmail);
        tvPrefUsername = (TextView) rootView.findViewById(R.id.tvPrefUsername);
        tvPrefEmail = (TextView) rootView.findViewById(R.id.tvPrefEmail);
        btnCambios = (Button) rootView.findViewById(R.id.btnCambios);

        UserSession userSession = new UserSession(rootView.getContext());
        tvPrefUsername.setText(userSession.getUserName());
        tvPrefEmail.setText(userSession.getEmail());

        btnCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return rootView;

    }
}
