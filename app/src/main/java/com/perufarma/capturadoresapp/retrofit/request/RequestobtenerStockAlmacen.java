package com.perufarma.capturadoresapp.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestobtenerStockAlmacen {

    @SerializedName("empresa")
    @Expose
    private String empresa;

    @SerializedName("articulo")
    @Expose
    private String articulo;

    @SerializedName("almacen")
    @Expose
    private String almacen;


    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getArticulo() { return articulo; }
    public void setArticulo(String articulo) { this.articulo = articulo; }

    public String getAlmacen() { return almacen; }
    public void setAlmacen(String almacen) { this.almacen = almacen; }


}
