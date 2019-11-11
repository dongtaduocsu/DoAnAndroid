package com.example.doanandroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.doanandroid.Activity.DanhsachbaihatActivity;
import com.example.doanandroid.Activity.DanhsachtatcachudeActivity;
import com.example.doanandroid.Activity.DanhsachtheloaitheochudeActivity;
import com.example.doanandroid.Model.ChuDe;
import com.example.doanandroid.Model.TheLoai;
import com.example.doanandroid.Model.Theloaivachude;
import com.example.doanandroid.R;
import com.example.doanandroid.Service.APIservice;
import com.example.doanandroid.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_chude_theloai extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chude_theloai_fragment, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIservice.getservice();
        Call<Theloaivachude> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<Theloaivachude>() {
            @Override
            public void onResponse(Call<Theloaivachude> call, Response<Theloaivachude> response) {
                Theloaivachude theloaivachude = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theloaivachude.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theloaivachude.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
                layout.setMargins(10,20,10,30);
                for (int i = 0; i < (chuDeArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                        Picasso.get().load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude", chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for (int j = 0; j < (chuDeArrayList.size()); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        Picasso.get().load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<Theloaivachude> call, Throwable t) {

            }
        });
    }
}
