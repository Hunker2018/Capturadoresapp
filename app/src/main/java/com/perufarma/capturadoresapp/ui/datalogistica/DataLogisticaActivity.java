package com.perufarma.capturadoresapp.ui.datalogistica;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;
import com.perufarma.capturadoresapp.retrofit.RetrofitInstance;
import com.perufarma.capturadoresapp.retrofit.request.RequestDataLogistica;
import com.perufarma.capturadoresapp.retrofit.request.RequestLogin;
import com.perufarma.capturadoresapp.retrofit.response.ResponseDataLogistica;
import com.perufarma.capturadoresapp.retrofit.response.ResponseLogin;

import androidx.navigation.NavController;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLogisticaActivity extends AppCompatActivity {

    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;

    private Context context;

    private EditText txtCodigo;
    private TextView lblCodigo, lblDescripcion, lblEAN13, lblDUN14, lblUnidadesCajas, lblBultosCaja, lblCamasPallet, lblAltoCaja, lblAnchoCaja, lblProfunCaja;
    private Button btn_dlSalir;

    public DataLogisticaActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalogistica_consulta);
        context = this;
        InitialViews();
        retrofitInit();
    }

    private void retrofitInit() {
        retrofitInstance = RetrofitInstance.getInstance();
        interfaceAPI = retrofitInstance.getInterfaceAPI();
    }

    private void InitialViews()
    {
        txtCodigo = findViewById(R.id.txtCodigo);
        lblCodigo = findViewById(R.id.lblCodigo);
        lblDescripcion = findViewById(R.id.lblDescripcion);
        lblEAN13 = findViewById(R.id.lblEAN13);
        lblDUN14 = findViewById(R.id.lblDUN14);
        lblUnidadesCajas = findViewById(R.id.lblUnidadesCajas);
        lblBultosCaja = findViewById(R.id.lblBultosCaja);
        lblCamasPallet = findViewById(R.id.lblCamasPallet);
        lblAnchoCaja = findViewById(R.id.lblAnchoCaja);
        lblAltoCaja = findViewById(R.id.lblAltoCaja);
        lblProfunCaja = findViewById(R.id.lblProfunCaja);
        btn_dlSalir = findViewById(R.id.btn_dlSalir);

        //NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
        //NavigationUI.setupWithNavController(navigationView,navController);

        btn_dlSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerDatosLogisticos("101", txtCodigo.getText().toString());
            }
        });
    }

    private void obtenerDatosLogisticos(String pempresa, String pcodigo)
    {
       RequestDataLogistica requestDataLogistica = new RequestDataLogistica();
       requestDataLogistica.setEmpresa(pempresa);
       requestDataLogistica.setArticulo(pcodigo);

        Call<ResponseDataLogistica> call = interfaceAPI.getResponseDataLogistica(requestDataLogistica);

        Toast.makeText(context, "Primer paso", Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<ResponseDataLogistica>() {
            @Override
            public void onResponse(Call<ResponseDataLogistica> call, Response<ResponseDataLogistica> response) {
                if(response.isSuccessful())
                {
                    assert response.body() != null;
                    String Mensaje = response.body().getMensaje();

                    if (Mensaje.equals("Ok"))
                    {
                        //Mostrar data logística
                        lblCodigo.setText(response.body().getData().getCodarticulo());
                        lblDescripcion.setText(response.body().getData().getDescripcion());
                        lblEAN13.setText(response.body().getData().getEan13());
                        lblDUN14.setText(response.body().getData().getDun14());
                        lblUnidadesCajas.setText(response.body().getData().getUnidades_caja());
                        lblBultosCaja.setText(response.body().getData().getBultos_cama());
                        lblCamasPallet.setText(response.body().getData().getCamas_pallet());
                        lblAltoCaja.setText(response.body().getData().getAlto_caja());
                        lblAnchoCaja.setText(response.body().getData().getAncho_caja());
                        lblProfunCaja.setText(response.body().getData().getProfun_caja());
                    }else
                    {
                        Toast.makeText(context, Mensaje, Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseDataLogistica> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
