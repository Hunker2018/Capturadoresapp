package com.perufarma.capturadoresapp.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseobtenerArticulo {
    @SerializedName("$id")
    @Expose
    private String $id;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    @SerializedName("datosarticulo")
    @Expose
    private String datosarticulo;

    public String get$id() {
        return $id;
    }
    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getDatosarticulo() { return datosarticulo; }
    public void setDatosarticulo(String datosarticulo) { this.datosarticulo = datosarticulo; }


}
