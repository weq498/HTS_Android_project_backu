package com.example.weq498.hts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 2015/8/28.
 */
public class login_menu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            statuslist_fragment statuslist_fragment = new statuslist_fragment();
            transaction.replace(R.id.Show_staticlist, statuslist_fragment);
            transaction.commit();

    }
}
