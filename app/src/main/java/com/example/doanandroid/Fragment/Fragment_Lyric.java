package com.example.doanandroid.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.doanandroid.R;

public class Fragment_Lyric extends Fragment {

    View view;
    TextView txttenbaihat, txtcasi, txtlyric;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lyric, container, false);
        txttenbaihat = view.findViewById(R.id.textviewlyrictenbaihat);
        txtcasi = view.findViewById(R.id.textviewlyriccasi);
        txtlyric = view.findViewById(R.id.textviewlyric);

        return view;
    }

    public void ThemLyric(String tenbaihat, String casi, String lyric) {
        txttenbaihat.setText(tenbaihat);
        txtcasi.setText(casi);
        txtlyric.setText(lyric);
    }

    public void load() {
        try {
            wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
