package com.perufarma.capturadoresapp.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseConfirmaRepartoA20 {

    @SerializedName("$id")
    @Expose
    private String $id;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    @SerializedName("rpta")
    @Expose
    private String rpta;

    public String get$id() {
        return $id;
    }
    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getRpta() { return rpta; }
    public void setRpta(String rpta) { this.rpta = rpta; }

}
