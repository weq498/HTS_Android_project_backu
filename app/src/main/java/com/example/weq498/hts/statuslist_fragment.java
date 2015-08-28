package com.example.weq498.hts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by User on 2015/8/29.
 */
public class statuslist_fragment extends Fragment {
    private ListView showlist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.statuslist_fragment, container, false);
        showlist = (ListView) v.findViewById(R.id.showlist);
        String[] array = {"使用者狀態","更改密碼"};
        //showlist.setAdapter(new MyAdapter());
        //white something
        return v;
    }
}
