package com.perufarma.capturadoresapp.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestValidaRepartoA20 {

    @SerializedName("empresa")
    @Expose
    private String empresa;

    @SerializedName("reparto")
    @Expose
    private String reparto;

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getReparto() { return reparto; }
    public void setReparto(String reparto) { this.reparto = reparto; }
}
