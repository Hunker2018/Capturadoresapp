package com.perufarma.capturadoresapp.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("resultado")
    @Expose
    private String resultado;

    public String get$id() { return $id; }
    public void set$id(String $id) { this.$id = $id; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

}
