package com.example.doanandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Activity.DanhsachbaihatActivity;
import com.example.doanandroid.Activity.PlayNhacActivity;
import com.example.doanandroid.Adapter.DanhsachbaihatAdapter;
import com.example.doanandroid.Adapter.Danhsachcacbaihat_play_Adapter;
import com.example.doanandroid.Adapter.PlaynhacAdapter;
import com.example.doanandroid.R;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment implements FragmentCallBack{
    View view;
    ListView recyclerViewplaynhac;
    PlaynhacAdapter playnhacAdapter;
    Danhsachcacbaihat_play_Adapter danhsachcacbaihat_play_adapter;
    PlayNhacActivity context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat, container, false);
        recyclerViewplaynhac = view.findViewById(R.id.recyclerviewPlaybaihat);
        if (PlayNhacActivity.mangbaihat.size() > 0 ) {
            playnhacAdapter = new PlaynhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            context=(PlayNhacActivity)getActivity();
            danhsachcacbaihat_play_adapter = new Danhsachcacbaihat_play_Adapter(getActivity(), PlayNhacActivity.mangbaihat);

            recyclerViewplaynhac.setAdapter(danhsachcacbaihat_play_adapter);
            recyclerViewplaynhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                context.onMsgFromFragAToMain(PlayNhacActivity.mangbaihat.get(i));
                }
            });


        }
        return view;
    }

    @Override
    public void onMsgFromMainToFragA() {

    }
}
