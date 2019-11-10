package com.example.doanandroid.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.Adapter.BaiHatHotAdapter;
import com.example.doanandroid.Model.Baihat;
import com.example.doanandroid.R;
import com.example.doanandroid.Service.APIservice;
import com.example.doanandroid.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewbaihathot;
    BaiHatHotAdapter baiHatHotAdapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.bai_hat_thich_nhat_fragment,container,false);
        recyclerViewbaihathot=view.findViewById(R.id.recycleviewbaihathot);

        GetData();
        return view;

    }

    private void GetData() {
        Dataservice dataservice= APIservice.getservice();
        Call<List<Baihat>> Callback=dataservice.GetBaiHatHot();
        Callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baiHatHotAdapter=new BaiHatHotAdapter(getActivity(),baihatArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewbaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewbaihathot.setAdapter(baiHatHotAdapter);

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
