package com.ehgmrr.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ehgmrr.R;
import com.ehgmrr.pojo.User;
import com.ehgmrr.ui.googleMap.MapsActivity;

import java.util.List;


public class ViewPatentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<User> data;

    public ViewPatentAdapter(Context context, List<User> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_user, parent, false);

        MyHolder holder = new MyHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final User g = data.get(position);

        MyHolder myHolder = (MyHolder) holder;

        myHolder.username.setText(g.getUsername());
        myHolder.phone.setText(g.getPhone());
      //  myHolder.phone_fa.setText(g.getPhone());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView username, phone, phone_fa;

        public MyHolder(View itemViewRes) {
            super(itemViewRes);

            username = itemViewRes.findViewById(R.id.username);
            phone = itemViewRes.findViewById(R.id.phone);
            phone_fa = itemViewRes.findViewById(R.id.phone_fa);

        }
    }
}