package com.example.doanandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Activity.DanhsachbaihatActivity;
import com.example.doanandroid.Model.Album;
import com.example.doanandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtatcaalbumAdapter extends RecyclerView.Adapter<DanhsachtatcaalbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> mangalbum;

    public DanhsachtatcaalbumAdapter(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_tat_ca_album, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangalbum.get(position);
        Picasso.get().load(album.getHinhanhalbum()).into(holder.imgtatcaalbum);
        holder.txttenalbum.setText(album.getTenalbum());
        holder.txttencasialbum.setText(album.getTencasialbum());
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgtatcaalbum;
        TextView txttenalbum;
        TextView txttencasialbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtatcaalbum = itemView.findViewById(R.id.imageviewtatcaalbum);
            txttenalbum = itemView.findViewById(R.id.textviewtentatcaalbum);
            txttencasialbum = itemView.findViewById(R.id.textviewtencasitatcaalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idalbum", mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
