package com.perufarma.capturadoresapp.ui.traspaso;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.perufarma.capturadoresapp.R;

public class TraspasoPrincipal extends AppCompatActivity {


    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traspaso_principal);
        context = this;

    }
}
