package com.perufarma.capturadoresapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.ui.consultaproducto.ConsultaArticuloPrincipal;
import com.perufarma.capturadoresapp.ui.datalogistica.DataLogisticaActivity;

import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;
import com.perufarma.capturadoresapp.retrofit.RetrofitInstance;
import com.perufarma.capturadoresapp.retrofit.response.ResponseLogin;
import com.perufarma.capturadoresapp.retrofit.request.RequestLogin;
import com.perufarma.capturadoresapp.ui.planillareparto.PlanillaRepartoActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity  extends AppCompatActivity {


    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;

    private Button btn_login;
    private Context context;
    private EditText txtUsuario, txtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        InitialViews();
        retrofitInit();

    }

    private void retrofitInit() {
        retrofitInstance = RetrofitInstance.getInstance();
        interfaceAPI = retrofitInstance.getInterfaceAPI();
    }


    private void InitialViews()
    {
        txtUsuario = this.findViewById(R.id.editTextUsuario);
        txtPwd = this.findViewById(R.id.editTextPassword);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = txtUsuario.getText().toString();
                String password = txtPwd.getText().toString();

                if(usuario.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Ingresar Usuario", Toast.LENGTH_LONG).show();
                }else if (password.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Ingresar la contraseña", Toast.LENGTH_LONG).show();
                }else{
                    Login("101", txtUsuario.getText().toString(), txtPwd.getText().toString());
                }

            }
        });
    }

    private void Login(String empresa, String usuario, String pwd)
    {
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setEmpresa(empresa);
        requestLogin.setUsuario(usuario);

        Call<ResponseLogin> call = interfaceAPI.getLogin(requestLogin);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful())
                {
                    assert response.body() != null;
                    String v_resultado = response.body().getResultado();

                    if(!v_resultado.equals(""))
                    {
                        //validar
                        String[] respuesta;
                        respuesta = v_resultado.split(";");
                        //Toast.makeText(context, respuesta[1], Toast.LENGTH_LONG).show();
                        if(pwd.equals(respuesta[1]))
                        {
                            goPrincipalScreen();
                        }else
                        {
                            Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                        }


                    }else
                    {
                        Toast.makeText(context, v_resultado, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goPrincipalScreen() {
        Intent intent = new Intent(MainActivity.this, PlanillaRepartoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
