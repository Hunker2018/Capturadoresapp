package com.perufarma.capturadoresapp.retrofit;

import com.perufarma.capturadoresapp.retrofit.request.RequestConfirmaRepartoA20;
import com.perufarma.capturadoresapp.retrofit.request.RequestDataLogistica;
import com.perufarma.capturadoresapp.retrofit.request.RequestLogin;
import com.perufarma.capturadoresapp.retrofit.request.RequestValidaRepartoA20;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerArticulo;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerArticulo2;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerArticuloxUbicacion;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerFechaCaducidad;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerStock;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerStockAlmacen;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerUbicacionAlmacen;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerUbicacionesDG;
import com.perufarma.capturadoresapp.retrofit.response.ResponseConfirmaRepartoA20;
import com.perufarma.capturadoresapp.retrofit.response.ResponseDataLogistica;
import com.perufarma.capturadoresapp.retrofit.response.ResponseLogin;
import com.perufarma.capturadoresapp.retrofit.response.ResponseValidaRepartoA20;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticulo;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticulo2;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticuloxUbicacion;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerFechaCaducidad;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerStock;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerStockAlmacen;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerUbicacionAlmacen;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerUbicacionesDG;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface InterfaceAPI {

    @POST("login")
    Call<ResponseLogin> getLogin(@Body RequestLogin requestLogin);

    @POST("ConsultaDataLogistica")
    Call<ResponseDataLogistica> getResponseDataLogistica(@Body RequestDataLogistica requestDataLogistica);

    @POST("obtenerArticuloxUbicacion")
    Call<ResponseobtenerArticuloxUbicacion> getResponseobtenerArticuloxUbicacion(@Body RequestobtenerArticuloxUbicacion requestobtenerArticuloxUbicacion);

    @POST("obtenerArticulo")
    Call<ResponseobtenerArticulo> getResponseobtenerArticulo(@Body RequestobtenerArticulo requestobtenerArticulo);

    @POST("obtenerArticulo2")
    Call<ResponseobtenerArticulo2> getResponseobtenerArticulo2(@Body RequestobtenerArticulo2 requestobtenerArticulo);

    @POST("obtenerUbicacionesDG")
    Call<ResponseobtenerUbicacionesDG> getResponseobtenerUbicacionesDG(@Body RequestobtenerUbicacionesDG requestobtenerUbicacionesDG);

    @POST("obtenerStock")
    Call<ResponseobtenerStock> getResponseobtenerStock(@Body RequestobtenerStock requestobtenerStock);

    @POST("obtenerUbicacionAlmacen")
    Call<ResponseobtenerUbicacionAlmacen> getResponseobtenerUbicacionAlmacen(@Body RequestobtenerUbicacionAlmacen requestobtenerUbicacionAlmacen);

    @POST("obtenerStockAlmacen")
    Call<ResponseobtenerStockAlmacen> getResponseobtenerStockAlmacen(@Body RequestobtenerStockAlmacen requestobtenerStockAlmacen);

    @POST("obtenerFechaCaducidad")
    Call<ResponseobtenerFechaCaducidad> getResponseobtenerFechaCaducidad(@Body RequestobtenerFechaCaducidad requestobtenerFechaCaducidad);

    @POST("ValidaRepartoA20")
    Call<ResponseValidaRepartoA20> getResponseValidaRepartoA20(@Body RequestValidaRepartoA20 requestValidaRepartoA20);

    @POST("ConfirmaRepartoA20")
    Call<ResponseConfirmaRepartoA20> getResponseConfirmaRepartoA20(@Body RequestConfirmaRepartoA20 requestConfirmaRepartoA20);
}
