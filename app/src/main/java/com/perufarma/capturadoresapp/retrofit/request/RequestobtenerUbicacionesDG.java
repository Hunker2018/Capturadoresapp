package com.perufarma.capturadoresapp.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestobtenerUbicacionesDG {

    @SerializedName("empresa")
    @Expose
    private String empresa;

    @SerializedName("ubicacion")
    @Expose
    private String ubicacion;

    @SerializedName("almacen")
    @Expose
    private String almacen;

    @SerializedName("zona")
    @Expose
    private String zona;

    @SerializedName("situacion")
    @Expose
    private String situacion;

    @SerializedName("articulo")
    @Expose
    private String articulo;

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getAlmacen() { return almacen; }
    public void setAlmacen(String almacen) { this.almacen = almacen; }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public String getSituacion() { return situacion; }
    public void setSituacion(String situacion) { this.situacion = situacion; }

    public String getArticulo() { return articulo; }
    public void setArticulo(String articulo) { this.articulo = articulo; }
}
