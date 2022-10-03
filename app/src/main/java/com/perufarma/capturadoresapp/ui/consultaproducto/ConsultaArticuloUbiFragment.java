package com.perufarma.capturadoresapp.ui.consultaproducto;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;
import com.perufarma.capturadoresapp.retrofit.RetrofitInstance;
import com.perufarma.capturadoresapp.retrofit.request.RequestobtenerArticuloxUbicacion;
import com.perufarma.capturadoresapp.retrofit.response.ResponseobtenerArticuloxUbicacion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultaArticuloUbiFragment extends Fragment {

    public static ConsultaArticuloUbiFragment newInstance() { return new ConsultaArticuloUbiFragment(); }

    private Context context;
    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;

    private TextView lblCACodigo, lblCAUbicacion, lblCADescripcion, lblCACantidad, lblCALote, lblCAFechaVencimiento;
    private EditText txtCACB;
    private Button btn_caubiConsultar;


    private List<ResponseobtenerArticuloxUbicacion.Data> listaUbicaciones = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ca_consultaubi, container, false);
        context = getContext();

        InitialViews(view);
        retrofitInit();
        //mostrarInformacion();
        return view;
    }

    private void InitialViews(View view)
    {
        lblCACodigo = view.findViewById(R.id.lblCACodigo);
        lblCAUbicacion = view.findViewById(R.id.lblCAUbicacion);
        lblCADescripcion = view.findViewById(R.id.lblCADescripcion);
        lblCACantidad = view.findViewById(R.id.lblCACantidad);
        lblCALote = view.findViewById(R.id.lblCALote);
        lblCAFechaVencimiento = view.findViewById(R.id.lblCAFechaVencimiento);
        txtCACB = view.findViewById(R.id.txtCACB);
        btn_caubiConsultar = view.findViewById(R.id.btn_caubiConsultar);

        btn_caubiConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mostrarInformacion();
            }
        });
    }

    private void retrofitInit() {
        retrofitInstance = RetrofitInstance.getInstance();
        interfaceAPI = retrofitInstance.getInterfaceAPI();
    }

    private void mostrarInformacion()
    {
        Bundle args = getArguments();

        String almacenca = args.getString("almacenca");
        String zonaca = args.getString("zonaca");
        String situacionca = args.getString("situacionca");

        RequestobtenerArticuloxUbicacion requestobtenerArticuloxUbicacion = new RequestobtenerArticuloxUbicacion();
        requestobtenerArticuloxUbicacion.setEmpresa("101");
        requestobtenerArticuloxUbicacion.setAlmacen(args.getString(almacenca));
        requestobtenerArticuloxUbicacion.setZona(args.getString(zonaca));
        requestobtenerArticuloxUbicacion.setSituacion(args.getString(situacionca));
        requestobtenerArticuloxUbicacion.setAlmacen(txtCACB.getText().toString());

        Call<ResponseobtenerArticuloxUbicacion> call = interfaceAPI.getResponseobtenerArticuloxUbicacion(requestobtenerArticuloxUbicacion);
        call.enqueue(new Callback<ResponseobtenerArticuloxUbicacion>() {
            @Override
            public void onResponse(Call<ResponseobtenerArticuloxUbicacion> call, Response<ResponseobtenerArticuloxUbicacion> response) {
                if(response.isSuccessful())
                {
                    assert response.body() != null;
                    String v_mensaje = response.body().getMensaje();

                    if (v_mensaje.equals("Ok"))
                    {
                        listaUbicaciones = response.body().getData();

                        if (listaUbicaciones.size() > 1)
                        {
                            String mensaje = "La ubicación tiene más de un artículo o lote: ";

                            for (int i = 0; i < listaUbicaciones.size(); i++) {
                                mensaje = mensaje  + " - " + listaUbicaciones.get(i).getArticulo() + " " + listaUbicaciones.get(i).getLote() +  " " + listaUbicaciones.get(i).getCant();
                            }
                            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        }else
                        {
                            if (listaUbicaciones.size() > 0)
                            {
                                lblCAUbicacion.setText(listaUbicaciones.get(0).getUbicac());
                                lblCACodigo.setText(listaUbicaciones.get(0).getArticulo());
                                lblCADescripcion.setText(listaUbicaciones.get(0).getArticulo());
                                lblCACantidad.setText(listaUbicaciones.get(0).getCant());
                                lblCALote.setText(listaUbicaciones.get(0).getLote());
                                lblCAFechaVencimiento.setText(listaUbicaciones.get(0).getFvenc());
                            }else
                            {
                                Toast.makeText(context, "La ubicación no tiene artículo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseobtenerArticuloxUbicacion> call, Throwable t) {

            }
        });
    }
}
