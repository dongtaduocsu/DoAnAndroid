package com.example.doanandroid.Service;

import com.example.doanandroid.Model.Playlist;
import com.example.doanandroid.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {

    @GET ("songbanner.php")
    Call<List<Quangcao>> GetDataBanner ();

    @GET ("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();
}
