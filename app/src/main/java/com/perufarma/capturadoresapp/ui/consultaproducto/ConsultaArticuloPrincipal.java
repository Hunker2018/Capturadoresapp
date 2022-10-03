package com.perufarma.capturadoresapp.ui.consultaproducto;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.perufarma.capturadoresapp.R;

public class ConsultaArticuloPrincipal extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultaarticulo_principal);
        context = this;

    }
}
