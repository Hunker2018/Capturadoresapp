package com.perufarma.capturadoresapp.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLogin {

    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("pwd")
    @Expose
    private String password;
    @SerializedName("empresa")
    @Expose
    private String empresa;

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
}
