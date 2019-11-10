package com.example.doanandroid.Service;

import com.example.doanandroid.Model.Album;
import com.example.doanandroid.Model.Baihat;
import com.example.doanandroid.Model.Playlist;
import com.example.doanandroid.Model.Quangcao;
import com.example.doanandroid.Model.Theloaivachude;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET ("songbanner.php")
    Call<List<Quangcao>> GetDataBanner ();

    @GET ("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET ("chudevatheloaitrongngay.php")
    Call<Theloaivachude> GetCategoryMusic();

    @GET ("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET ("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);


}
