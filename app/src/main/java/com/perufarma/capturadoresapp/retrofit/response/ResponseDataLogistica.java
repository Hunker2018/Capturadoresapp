package com.perufarma.capturadoresapp.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDataLogistica {

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("data")
    @Expose
    private Data data = null;

    public String get$id() {
        return $id;
    }
    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data
    {
        @SerializedName("$id")
        @Expose
        private String $id;

        @SerializedName("empresa")
        @Expose
        private String empresa;

        @SerializedName("articulo")
        @Expose
        private String articulo;

        @SerializedName("unidades_caja")
        @Expose
        private String unidades_caja;

        @SerializedName("alto_caja")
        @Expose
        private String alto_caja;

        @SerializedName("ancho_caja")
        @Expose
        private String ancho_caja;

        @SerializedName("profun_caja")
        @Expose
        private String profun_caja;

        @SerializedName("bultos_cama")
        @Expose
        private String bultos_cama;

        @SerializedName("camas_pallet")
        @Expose
        private String camas_pallet;

        @SerializedName("codarticulo")
        @Expose
        private String codarticulo;

        @SerializedName("ean13")
        @Expose
        private String ean13;

        @SerializedName("dun14")
        @Expose
        private String dun14;

        @SerializedName("descripcion")
        @Expose
        private String descripcion;

        public String get$id() { return $id; }
        public void set$id(String $id) { this.$id = $id; }

        public String getEmpresa() { return empresa; }
        public void setEmpresa(String empresa) { this.empresa = empresa; }

        public String getArticulo() { return articulo; }
        public void setArticulo(String articulo) { this.articulo = articulo; }

        public String getUnidades_caja() { return unidades_caja; }
        public void setUnidades_caja(String unidades_caja) { this.unidades_caja = unidades_caja; }

        public String getAlto_caja() { return alto_caja; }
        public void setAlto_caja(String alto_caja) { this.alto_caja = alto_caja; }

        public String getAncho_caja() { return ancho_caja; }
        public void setAncho_caja(String ancho_caja) { this.ancho_caja = ancho_caja; }

        public String getProfun_caja() { return profun_caja; }
        public void setProfun_caja(String profun_caja) { this.profun_caja = profun_caja; }

        public String getBultos_cama() { return bultos_cama; }
        public void setBultos_cama(String bultos_cama) { this.bultos_cama = bultos_cama; }

        public String getCamas_pallet() { return camas_pallet; }
        public void setCamas_pallet(String camas_pallet) { this.camas_pallet = camas_pallet; }

        public String getCodarticulo() { return codarticulo; }
        public void setCodarticulo(String codarticulo) { this.codarticulo = codarticulo; }

        public String getEan13(){ return ean13; }
        public void setEan13(String ean13) { this.ean13 = ean13; }

        public String getDun14() { return dun14; }
        public void setDun14(String dun14) { this.dun14 = dun14; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    }
}
