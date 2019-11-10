package com.example.doanandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Model.Baihat;
import com.example.doanandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder>{
    Context context;


    public BaiHatHotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    ArrayList<Baihat>baihatArrayList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);

        return new ViewHolder(  view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
Baihat baihat=baihatArrayList.get(position);
holder.txtcasi.setText(baihat.getCasi());
holder.txtten.setText(baihat.getTenbaihat());
        Picasso.get().load(baihat.getHinhbaihat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
    TextView txtten,txtcasi;
    ImageView imghinh,imgluotthich;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txtten=itemView.findViewById(R.id.textviewbaihathot);
        txtcasi=itemView.findViewById(R.id.textviewcasibaihathot);
        imghinh=itemView.findViewById(R.id.imageviewbaihathot);
        imgluotthich=itemView.findViewById(R.id.imageviewluotthich);
    }
}
}
