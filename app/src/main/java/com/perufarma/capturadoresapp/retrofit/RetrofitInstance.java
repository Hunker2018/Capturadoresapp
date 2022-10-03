package com.perufarma.capturadoresapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.perufarma.capturadoresapp.common.Parametros.API_CAPTURADORES_URL;

public class RetrofitInstance {

    private static RetrofitInstance instance = null;
    private InterfaceAPI interfaceAPI;
    private static Retrofit retrofit;

    public RetrofitInstance()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_CAPTURADORES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        interfaceAPI = retrofit.create(InterfaceAPI.class);
    }

    public static RetrofitInstance getInstance()
    {
        if(instance==null)
        {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public InterfaceAPI getInterfaceAPI() { return interfaceAPI; }

}
