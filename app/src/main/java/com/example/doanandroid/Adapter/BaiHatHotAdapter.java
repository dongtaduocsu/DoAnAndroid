package com.example.doanandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Activity.PlayNhacActivity;
import com.example.doanandroid.Model.Baihat;
import com.example.doanandroid.R;
import com.example.doanandroid.Service.APIservice;
import com.example.doanandroid.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                context.startActivity(intent);
            }
        });
        imgluotthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgluotthich.setImageResource(R.drawable.iconloved);
                Dataservice dataservice = APIservice.getservice();
                Call<String> callback = dataservice.Updateluotthich("1", baihatArrayList.get(getPosition()).getIdbaihat());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua = response.body();
                        if (ketqua.equals("Success")) {
                            Toast.makeText(context, "Đã thích.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                imgluotthich.setEnabled(false);
            }
        });
    }
}
}
