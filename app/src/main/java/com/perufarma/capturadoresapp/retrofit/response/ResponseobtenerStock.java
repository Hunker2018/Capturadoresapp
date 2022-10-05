package com.perufarma.capturadoresapp.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseobtenerStock {
    @SerializedName("$id")
    @Expose
    private String $id;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    @SerializedName("stock")
    @Expose
    private String stock;

    public String get$id() {
        return $id;
    }
    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }
}
