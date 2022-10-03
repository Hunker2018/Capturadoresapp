package com.perufarma.capturadoresapp.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseobtenerArticuloxUbicacion {

    @SerializedName("$id")
    @Expose
    private String $id;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    @SerializedName("data")
    @Expose
    public List<Data> data;

    public String get$id() { return $id; }
    public void set$id(String $id) { this.$id = $id; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public List<Data> getData() { return data; }
    public void setData(List<Data> data) { this.data = data; }


    public class Data
    {
        @SerializedName("$id")
        @Expose
        private String $id;

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

        @SerializedName("lote")
        @Expose
        private String lote;

        @SerializedName("fvenc")
        @Expose
        private String fvenc;

        @SerializedName("ubicac")
        @Expose
        private String ubicac;

        @SerializedName("cant")
        @Expose
        private String cant;

        public String get$id() { return $id; }
        public void set$id(String $id) { this.$id = $id; }

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

        public String getLote() { return lote; }
        public void setLote(String lote) { this.lote = lote; }

        public String getFvenc() { return fvenc; }
        public void setFvenc(String fvenc) { this.fvenc = fvenc; }

        public String getUbicac() { return ubicac; }
        public void setUbicac(String ubicac) { this.ubicac = ubicac; }

        public String getCant() { return cant; }
        public void setCant(String cant) { this.cant = cant; }



    }

}
