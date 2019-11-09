package com.example.doanandroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

@SerializedName("Idalbum")
@Expose
private String idalbum;
@SerializedName("Tenalbum")
@Expose
private String tenalbum;
@SerializedName("Tencasialbum")
@Expose
private String tencasialbum;
@SerializedName("Hinhanhalbum")
@Expose
private String hinhanhalbum;

public String getIdalbum() {
return idalbum;
}

public void setIdalbum(String idalbum) {
this.idalbum = idalbum;
}

public String getTenalbum() {
return tenalbum;
}

public void setTenalbum(String tenalbum) {
this.tenalbum = tenalbum;
}

public String getTencasialbum() {
return tencasialbum;
}

public void setTencasialbum(String tencasialbum) {
this.tencasialbum = tencasialbum;
}

public String getHinhanhalbum() {
return hinhanhalbum;
}

public void setHinhanhalbum(String hinhanhalbum) {
this.hinhanhalbum = hinhanhalbum;
}

}