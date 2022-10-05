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
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerUbicacionesDG;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticulo;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticulo2;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerUbicacionesDG;

import java.util.ArrayList;
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


    String articulo = "";
    Boolean flagproducto = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_infoproducto, container, false);
        context = getContext();

        InitialViews(view);

        retrofitInit();

        return view;
    }

    public void cargarUbicaciones(String articulo) {

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

    }

    private void InitialViews(View view)
    {
        lblCAInfoCodigo = view.findViewById(R.id.lblCAInfoCodigo);
        lblCAInfoUbicacion = view.findViewById(R.id.lblCAInfoUbicacion);
        lblCAInfoDescripcion = view.findViewById(R.id.lblCAInfoDescripcion);
        lblCAInfofechacad = view.findViewById(R.id.lblCAInfofechacad);
        lblCAInfoUltimaFechaCaducidad = view.findViewById(R.id.lblCAInfoUltimaFechaCaducidad);
        lblCAInfoStockAlmacen1 = view.findViewById(R.id.lblCAInfoStockAlmacen1);
        lblCAInfoStockReserva1 = view.findViewById(R.id.lblCAInfoStockReserva1);
        lblCAInfoStockTotalALmacen1 = view.findViewById(R.id.lblCAInfoStockTotalALmacen1);
        lblCAInfoStock1 = view.findViewById(R.id.lblCAInfoStock1);
        lblCAInfoStockRes1 = view.findViewById(R.id.lblCAInfoStockRes1);
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

                                        cargarUbicaciones(producto);

                                    }
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

                                        cargarUbicaciones(producto);

                                        lblCAInfoStock1.setText("");

                                    }
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


    private void obtenerArticulo(String empresa, String codarticulo)
    {


    }


    private void obtenerArticulo2(String empresa, String codarticulo)
    {


    }

}
