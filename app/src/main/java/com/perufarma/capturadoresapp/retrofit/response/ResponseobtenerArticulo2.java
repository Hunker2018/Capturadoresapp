package com.perufarma.capturadoresapp.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseobtenerArticulo2 {
    @SerializedName("$id")
    @Expose
    private String $id;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    @SerializedName("data")
    @Expose
    private String data;

    public String get$id() {
        return $id;
    }
    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
