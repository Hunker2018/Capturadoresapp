package com.perufarma.capturadoresapp.ui.consultaproducto;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;
import com.perufarma.capturadoresapp.retrofit.RetrofitInstance;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerArticulo;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerArticulo2;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerFechaCaducidad;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerStock;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerStockAlmacen;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerUbicacionAlmacen;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerUbicacionesDG;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticulo;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticulo2;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerFechaCaducidad;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerStock;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerStockAlmacen;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerUbicacionAlmacen;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerUbicacionesDG;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultaArticuloInfoFragment extends Fragment {

    public static ConsultaArticuloUbiFragment newInstance() { return new ConsultaArticuloUbiFragment(); }

    private Context context;
    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;

    private TextView lblCAInfoCodigo, lblCAInfoUbicacion, lblCAInfoDescripcion, lblCAInfofechacad,
                        lblCAInfoUltimaFechaCaducidad, lblCAInfoStockAlmacen1, lblCAInfoStockReserva1, lblCAInfoStockTotalALmacen1,
                                lblCAInfoStock1, lblCAInfoStockRes1, lblCAInfoStockTotal1;

    private EditText txtCAInfoCB;
    private RadioGroup gruporadioca;
    private RadioButton radio_cb, radio_articulo;
    private Button btn_caInfoSalir;
    TableLayout stk;
    List<ResponseobtenerUbicacionesDG.Data> lista = new ArrayList<>();


    private static final String UBICACION_ALMACEN = "02";
    private static final String UBICACION_ZONA = "001";
    private static final String STOCK_ALMACEN = "01";

    String articulo = "";
    Boolean flagproducto = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_infoproducto, container, false);
        context = getContext();

        InitialViews(view);

        retrofitInit();
        limpiar();

        return view;
    }

    public void cargarUbicaciones(String articulo) {

        stk.removeAllViews();

        TableRow tbrow0 = new TableRow(context);
        TextView tv0 = new TextView(context);
        tv0.setText("        Lote       ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(context);
        tv1.setText("    Fec. Venc.    ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(context);
        tv2.setText("    Ubicación    ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(context);
        tv3.setText(" Cantidad ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);

        Bundle args = getArguments();

        String almacenca = args.getString("almacenca");
        String zonaca = args.getString("zonaca");
        String situacionca = args.getString("situacionca");

        RequestobtenerUbicacionesDG requestobtenerUbicacionesDG = new RequestobtenerUbicacionesDG();
        requestobtenerUbicacionesDG.setEmpresa("101");
        requestobtenerUbicacionesDG.setAlmacen(almacenca);
        requestobtenerUbicacionesDG.setZona(zonaca);
        requestobtenerUbicacionesDG.setSituacion(situacionca);
        requestobtenerUbicacionesDG.setArticulo(articulo);

        Call<ResponseobtenerUbicacionesDG> call = interfaceAPI.getResponseobtenerUbicacionesDG(requestobtenerUbicacionesDG);
        call.enqueue(new Callback<ResponseobtenerUbicacionesDG>() {
            @Override
            public void onResponse(Call<ResponseobtenerUbicacionesDG> call, Response<ResponseobtenerUbicacionesDG> response) {
                if(response.isSuccessful())
                {
                    lista = response.body().getData();
                    for (int i = 0; i < lista.size(); i++) {
                        TableRow tbrow = new TableRow(context);
                        TextView t1v = new TextView(context);
                        t1v.setText("   " + lista.get(i).getLote() + "  ");
                        t1v.setTextColor(Color.BLACK);
                        t1v.setGravity(Gravity.CENTER);
                        tbrow.addView(t1v);
                        TextView t2v = new TextView(context);
                        t2v.setText("   " + lista.get(i).getFvenc() + "   ");
                        t2v.setTextColor(Color.BLACK);
                        t2v.setGravity(Gravity.CENTER);
                        tbrow.addView(t2v);
                        TextView t3v = new TextView(context);
                        t3v.setText("   " + lista.get(i).getUbicac() + "   ");
                        t3v.setTextColor(Color.BLACK);
                        t3v.setGravity(Gravity.CENTER);
                        tbrow.addView(t3v);
                        TextView t4v = new TextView(context);
                        t4v.setText(lista.get(i).getCant());
                        t4v.setTextColor(Color.BLACK);
                        t4v.setGravity(Gravity.CENTER);
                        tbrow.addView(t4v);
                        stk.addView(tbrow);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseobtenerUbicacionesDG> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void obtenerStock(String empresa, String articulo, String almacen, String zona, String situacion, String disponible)
    {
        RequestobtenerStock requestobtenerStock = new RequestobtenerStock();
        requestobtenerStock.setEmpresa(empresa);
        requestobtenerStock.setAlmacen(almacen);
        requestobtenerStock.setZona(zona);
        requestobtenerStock.setArticulo(articulo);
        requestobtenerStock.setSituacion(situacion);
        requestobtenerStock.setDisponible(disponible);

        Call<ResponseobtenerStock> call = interfaceAPI.getResponseobtenerStock(requestobtenerStock);
        call.enqueue(new Callback<ResponseobtenerStock>() {
            @Override
            public void onResponse(Call<ResponseobtenerStock> call, Response<ResponseobtenerStock> response) {

                if(response.isSuccessful())
                {
                    lblCAInfoStock1.setText(response.body().getStock());
                }
            }

            @Override
            public void onFailure(Call<ResponseobtenerStock> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerUbicacionAlmacen(String empresa, String articulo)
    {
        RequestobtenerUbicacionAlmacen requestobtenerUbicacionAlmacen = new RequestobtenerUbicacionAlmacen();
        requestobtenerUbicacionAlmacen.setEmpresa(empresa);
        requestobtenerUbicacionAlmacen.setAlmacen(UBICACION_ALMACEN);
        requestobtenerUbicacionAlmacen.setZona(UBICACION_ZONA);
        requestobtenerUbicacionAlmacen.setArticulo(articulo);


        Call<ResponseobtenerUbicacionAlmacen> call = interfaceAPI.getResponseobtenerUbicacionAlmacen(requestobtenerUbicacionAlmacen);
        call.enqueue(new Callback<ResponseobtenerUbicacionAlmacen>() {
            @Override
            public void onResponse(Call<ResponseobtenerUbicacionAlmacen> call, Response<ResponseobtenerUbicacionAlmacen> response) {

                if(response.isSuccessful())
                {
                    String ubicacion = response.body().getUbicacion();
                    lblCAInfoUbicacion.setText("UBIC. " + UBICACION_ALMACEN + ":" + ubicacion);
                }
            }

            @Override
            public void onFailure(Call<ResponseobtenerUbicacionAlmacen> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerStockAlmacne(String empresa, String articulo)
    {
        RequestobtenerStockAlmacen requestobtenerStockAlmacen = new RequestobtenerStockAlmacen();
        requestobtenerStockAlmacen.setEmpresa(empresa);
        requestobtenerStockAlmacen.setAlmacen(STOCK_ALMACEN);
        requestobtenerStockAlmacen.setArticulo(articulo);


        Call<ResponseobtenerStockAlmacen> call = interfaceAPI.getResponseobtenerStockAlmacen(requestobtenerStockAlmacen);
        call.enqueue(new Callback<ResponseobtenerStockAlmacen>() {
            @Override
            public void onResponse(Call<ResponseobtenerStockAlmacen> call, Response<ResponseobtenerStockAlmacen> response) {

                if(response.isSuccessful())
                {
                    lblCAInfoStockTotalALmacen1.setText("Stk. Total Alm. " + STOCK_ALMACEN);
                    lblCAInfoStockTotal1.setText(response.body().getStock());
                }
            }

            @Override
            public void onFailure(Call<ResponseobtenerStockAlmacen> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerFechaCaducidad(String empresa, String articulo)
    {
        RequestobtenerFechaCaducidad requestobtenerFechaCaducidad = new RequestobtenerFechaCaducidad();
        requestobtenerFechaCaducidad.setEmpresa(empresa);
        requestobtenerFechaCaducidad.setArticulo(articulo);


        Call<ResponseobtenerFechaCaducidad> call = interfaceAPI.getResponseobtenerFechaCaducidad(requestobtenerFechaCaducidad);
        call.enqueue(new Callback<ResponseobtenerFechaCaducidad>() {
            @Override
            public void onResponse(Call<ResponseobtenerFechaCaducidad> call, Response<ResponseobtenerFechaCaducidad> response) {

                if(response.isSuccessful())
                {
                    String fecha = response.body().getFecha();
                    lblCAInfofechacad.setText("Ult.Fecha.Caducidad");

                    if (fecha.equals("01/01/0001"))
                    {
                        lblCAInfoUltimaFechaCaducidad.setText("No se registró");
                    }else
                    {
                        lblCAInfoUltimaFechaCaducidad.setText(fecha);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseobtenerFechaCaducidad> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InitialViews(View view)
    {
        lblCAInfoCodigo = view.findViewById(R.id.lblCAInfoCodigo);
        lblCAInfoUbicacion = view.findViewById(R.id.lblCAInfoUbicacion);
        lblCAInfoDescripcion = view.findViewById(R.id.lblCAInfoDescripcion);
        lblCAInfofechacad = view.findViewById(R.id.lblCAInfofechacad);
        lblCAInfoUltimaFechaCaducidad = view.findViewById(R.id.lblCAInfoUltimaFechaCaducidad);
        lblCAInfoStockAlmacen1 = view.findViewById(R.id.lblCAInfoStockAlmacen1);
        lblCAInfoStockTotalALmacen1 = view.findViewById(R.id.lblCAInfoStockTotalALmacen1);
        lblCAInfoStock1 = view.findViewById(R.id.lblCAInfoStock1);
        lblCAInfoStockTotal1 = view.findViewById(R.id.lblCAInfoStockTotal1);

        txtCAInfoCB = view.findViewById(R.id.txtCAInfoCB);
        gruporadioca = (RadioGroup) view.findViewById(R.id.gruporadioca);
        radio_cb = view.findViewById(R.id.radio_cb);
        radio_articulo = view.findViewById(R.id.radio_articulo);

        btn_caInfoSalir = view.findViewById(R.id.btn_caInfoSalir);
        stk = view.findViewById(R.id.lbxUbicacionesxArticulo);

        gruporadioca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                boolean isChecked = radio_cb.isChecked();
                if (isChecked)
                {
                    flagproducto = true;
                }else
                {
                    flagproducto = false;
                }
            }
        });



        btn_caInfoSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flagproducto)
                {
                    String tArticulo = txtCAInfoCB.getText().toString();
                    RequestobtenerArticulo2 requestobtenerArticulo = new RequestobtenerArticulo2();

                    requestobtenerArticulo.setEmpresa("101");
                    requestobtenerArticulo.setArticulo(tArticulo);



                    Call<ResponseobtenerArticulo2> call = interfaceAPI.getResponseobtenerArticulo2(requestobtenerArticulo);
                    call.enqueue(new Callback<ResponseobtenerArticulo2>() {
                        @Override
                        public void onResponse(Call<ResponseobtenerArticulo2> call, Response<ResponseobtenerArticulo2> response) {
                            if(response.isSuccessful())
                            {
                                articulo = response.body().getData();

                                if (articulo != "")
                                {
                                    if (articulo == "N")
                                    {
                                        Toast.makeText(context, "Error de conexión: inténtelo nuevamente.", Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        String[] cadena = articulo.split(",");
                                        String producto = cadena[0];

                                        Bundle args = getArguments();

                                        String almacenca = args.getString("almacenca");
                                        String zonaca = args.getString("zonaca");
                                        String situacionca = args.getString("situacionca");


                                        cargarUbicaciones(producto);
                                        lblCAInfoCodigo.setText(producto);
                                        lblCAInfoDescripcion.setText(cadena[1]);
                                        obtenerStock("101", producto, almacenca, zonaca, situacionca, "S");
                                        lblCAInfoStockAlmacen1.setText("Stk. Alm. " + almacenca);

                                        obtenerUbicacionAlmacen("101", producto);
                                        obtenerStockAlmacne("101", producto);
                                        obtenerFechaCaducidad("101", producto);
                                    }
                                }else
                                {
                                    Toast.makeText(context, "El producto no está registrado", Toast.LENGTH_SHORT).show();
                                    limpiar();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseobtenerArticulo2> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else
                {
                    RequestobtenerArticulo requestobtenerArticulo = new RequestobtenerArticulo();
                    String tArticulo = txtCAInfoCB.getText().toString();
                    requestobtenerArticulo.setEmpresa("101");
                    requestobtenerArticulo.setArticulo(tArticulo);

                    Call<ResponseobtenerArticulo> call = interfaceAPI.getResponseobtenerArticulo(requestobtenerArticulo);
                    call.enqueue(new Callback<ResponseobtenerArticulo>() {
                        @Override
                        public void onResponse(Call<ResponseobtenerArticulo> call, Response<ResponseobtenerArticulo> response) {
                            if(response.isSuccessful())
                            {
                                articulo = response.body().getData();

                                if (articulo != "")
                                {
                                    if (articulo == "N")
                                    {
                                        Toast.makeText(context, "Error de conexión: inténtelo nuevamente.", Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        String[] cadena = articulo.split(",");
                                        String producto = cadena[0];

                                        Bundle args = getArguments();

                                        String almacenca = args.getString("almacenca");
                                        String zonaca = args.getString("zonaca");
                                        String situacionca = args.getString("situacionca");

                                        cargarUbicaciones(producto);

                                        lblCAInfoCodigo.setText(producto);
                                        lblCAInfoDescripcion.setText(cadena[1]);
                                        obtenerStock("101", producto, almacenca, zonaca, situacionca, "S");
                                        lblCAInfoStockAlmacen1.setText("Stk. Alm. " + almacenca);

                                        obtenerUbicacionAlmacen("101", producto);
                                        obtenerStockAlmacne("101", producto);
                                        obtenerFechaCaducidad("101", producto);
                                    }
                                }else{
                                    Toast.makeText(context, "El producto no está registrado", Toast.LENGTH_SHORT).show();
                                    limpiar();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseobtenerArticulo> call, Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

        txtCAInfoCB.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.KEYCODE_ENTER)
                {
                    //aca


                    return true;
                }
                return false;
            }
        });

    }

    private void retrofitInit() {
        retrofitInstance = RetrofitInstance.getInstance();
        interfaceAPI = retrofitInstance.getInterfaceAPI();
    }

    private void limpiar()
    {
        lblCAInfoCodigo.setText("");
        lblCAInfoDescripcion.setText("");
        lblCAInfoStock1.setText("");
        lblCAInfoStockAlmacen1.setText("");
        lblCAInfoStockTotalALmacen1.setText("");
        lblCAInfoStockTotal1.setText("");
        lblCAInfoUbicacion.setText("");
        lblCAInfofechacad.setText("");
        lblCAInfoUltimaFechaCaducidad.setText("");
    }

}
