package com.example.weq498.hts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class signon extends Fragment {
    private EditText userID;
    private EditText name;
    private EditText passwd;
    private EditText email;
    private EditText confirm_passwd;
    private Button confirm;
    private Button clear;
    private Button return_menu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sign_on, container, false);
        userID = (EditText) view.findViewById(R.id.userID);
        name = (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.email);
        passwd = (EditText) view.findViewById(R.id.passwd);
        confirm_passwd = (EditText) view.findViewById(R.id.confirm_passwd);
        confirm = (Button) view.findViewById(R.id.confirm);
        return_menu = (Button) view.findViewById(R.id.return_menu);
        return_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!passwd.getText().toString().equals(confirm_passwd.getText().toString())) {

                }
                //Database
            }
        });
        clear = (Button) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID.setText("");
                name.setText("");
                passwd.setText("");
                email.setText("");
                confirm_passwd.setText("");
            }
        });
        return view;
    }
}
