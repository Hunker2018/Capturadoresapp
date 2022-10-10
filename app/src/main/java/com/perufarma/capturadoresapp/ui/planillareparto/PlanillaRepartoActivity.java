package com.perufarma.capturadoresapp.ui.planillareparto;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;
import com.perufarma.capturadoresapp.retrofit.RetrofitInstance;
import com.perufarma.capturadoresapp.retrofit.request.RequestConfirmaRepartoA20;
import com.perufarma.capturadoresapp.retrofit.request.RequestValidaRepartoA20;
import com.perufarma.capturadoresapp.retrofit.response.ResponseConfirmaRepartoA20;
import com.perufarma.capturadoresapp.retrofit.response.ResponseValidaRepartoA20;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanillaRepartoActivity  extends AppCompatActivity {

    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;

    private Context context;

    private EditText txtPRReparto, txtPRTransportista, txtPRFechaReparto, txtPRPlaca, txtPRNumDocumentos;
    private Button btn_prConfirmar, btn_prSalir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planillareparto);
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
        txtPRReparto = findViewById(R.id.txtPRReparto);
        txtPRTransportista = findViewById(R.id.txtPRTransportista);
        txtPRFechaReparto = findViewById(R.id.txtPRFechaReparto);
        txtPRPlaca = findViewById(R.id.txtPRPlaca);
        txtPRNumDocumentos = findViewById(R.id.txtPRNumDocumentos);

        btn_prConfirmar = findViewById(R.id.btn_prConfirmar);
        btn_prSalir = findViewById(R.id.btn_prSalir);


        btn_prSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarReparto(txtPRReparto.getText().toString());
            }
        });

        btn_prConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void validarReparto(String reparto)
    {
        RequestValidaRepartoA20 requestValidaRepartoA20 = new RequestValidaRepartoA20();
        requestValidaRepartoA20.setEmpresa("101");
        requestValidaRepartoA20.setReparto(reparto);

        Call<ResponseValidaRepartoA20> call = interfaceAPI.getResponseValidaRepartoA20(requestValidaRepartoA20);
        call.enqueue(new Callback<ResponseValidaRepartoA20>() {
            @Override
            public void onResponse(Call<ResponseValidaRepartoA20> call, Response<ResponseValidaRepartoA20> response) {
                if(response.isSuccessful())
                {

                    if(response.body().getMensaje().equals("Ok"))
                    {
                        String respuesta = response.body().getRpta();
                        String[] cadena = respuesta.split("\\|");

                        txtPRTransportista.setText(cadena[0]);
                        txtPRPlaca.setText(cadena[1]);
                        txtPRFechaReparto.setText(cadena[2]);
                        txtPRNumDocumentos.setText(cadena[3]);

                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseValidaRepartoA20> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void confirmarReparto(String reparto, String transportista, String fecha)
    {
        RequestConfirmaRepartoA20 requestConfirmaRepartoA20 = new RequestConfirmaRepartoA20();
        requestConfirmaRepartoA20.setReparto(reparto);
        requestConfirmaRepartoA20.setEmpresa("101");

        if (transportista.equals("") && fecha.equals(""))
        {
            Toast.makeText(context, "Debes validar el reparto", Toast.LENGTH_SHORT).show();
        }

        Call<ResponseConfirmaRepartoA20> call = interfaceAPI.getResponseConfirmaRepartoA20(requestConfirmaRepartoA20);
        call.enqueue(new Callback<ResponseConfirmaRepartoA20>() {
            @Override
            public void onResponse(Call<ResponseConfirmaRepartoA20> call, Response<ResponseConfirmaRepartoA20> response) {
                if(response.isSuccessful())
                {
                    String mensaje = response.body().getMensaje();

                    if(mensaje.equals("Ok"))
                    {

                        String rpta = response.body().getRpta();

                        if (rpta.equals(""))
                        {
                            txtPRReparto.setText("");
                            txtPRTransportista.setText("");
                            txtPRFechaReparto.setText("");
                            txtPRPlaca.setText("");
                            txtPRNumDocumentos.setText("");
                        }else
                        {
                            Toast.makeText(context, rpta, Toast.LENGTH_SHORT).show();
                        }

                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseConfirmaRepartoA20> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
