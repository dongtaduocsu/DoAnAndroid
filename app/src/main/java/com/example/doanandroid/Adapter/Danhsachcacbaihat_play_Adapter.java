package com.example.doanandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Activity.PlayNhacActivity;
import com.example.doanandroid.Model.Baihat;
import com.example.doanandroid.Model.Playlist;
import com.example.doanandroid.R;
import com.example.doanandroid.Service.APIservice;
import com.example.doanandroid.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Danhsachcacbaihat_play_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Baihat> mangbaihat;
    PlayNhacActivity play;
    int index_;

    public Danhsachcacbaihat_play_Adapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }


    @Override
    public int getCount() {
        return mangbaihat.size();
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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.dong_danh_sach_bai_hat_play,null);
         TextView txttenbaihat=view.findViewById(R.id.textviewtenbaihat1);
         TextView txttencasi=view.findViewById(R.id.textviewtencasi1);
         TextView txtvitri=view.findViewById(R.id.textviewdanhsachindex1);

         txttenbaihat.setText(mangbaihat.get(i).getTenbaihat());
         txttencasi.setText(mangbaihat.get(i).getCasi());
         txtvitri.setText(mangbaihat.get(i).getIdbaihat()+1+"");



        return view;

    }
}
