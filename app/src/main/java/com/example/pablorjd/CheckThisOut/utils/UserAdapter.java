package com.example.pablorjd.CheckThisOut.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class UserAdapter extends BaseAdapter{

    private Context context;
    private List<UserSession> list;
    private int layout;

    public UserAdapter(Context context, List<UserSession> list, int layout){

        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<UserSession> getList() {
        return list;
    }

    public void setList(List<UserSession> list) {
        this.list = list;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
