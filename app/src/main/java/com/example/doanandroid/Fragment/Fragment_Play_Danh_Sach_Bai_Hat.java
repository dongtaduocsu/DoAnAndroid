package com.example.doanandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Activity.PlayNhacActivity;
import com.example.doanandroid.Adapter.PlaynhacAdapter;
import com.example.doanandroid.R;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewplaynhac;
    PlaynhacAdapter playnhacAdapter;
    public static PlayNhacActivity context2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat, container, false);
        recyclerViewplaynhac = view.findViewById(R.id.recyclerviewPlaybaihat);
        context2 = (PlayNhacActivity) getActivity();
        if (PlayNhacActivity.mangbaihat.size() > 0 ) {
            playnhacAdapter = new PlaynhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playnhacAdapter);

        }
        return view;
    }

}
