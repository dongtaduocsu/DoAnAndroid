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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Danhsachcacbaihat_play_Adapter extends RecyclerView.Adapter<Danhsachcacbaihat_play_Adapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;
    int index_;

    public Danhsachcacbaihat_play_Adapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat_play, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Baihat baihat = mangbaihat.get(position);
        holder.txttencasi.setText(baihat.getCasi());
        holder.txttenbaihat.setText(baihat.getTenbaihat());
        holder.txtindex.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txttenbaihat, txttencasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttencasi = itemView.findViewById(R.id.textviewtencasi);
            txtindex = itemView.findViewById((R.id.textviewdanhsachindex));
            txttenbaihat = itemView.findViewById(R.id.textviewtenbaihat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc1",mangbaihat.get(getPosition()) );

                    context.startActivity(intent);
                }
            });
        }

    }
}
