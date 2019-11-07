package com.example.doanandroid.Service;

public class APIservice {

    private static String  base_url= "https://nghenhacnao.000webhostapp.com/server/";
    public static Dataservice getservice(){
        return APIretrofitclient.getClient(base_url).create(Dataservice.class);
    }

}
