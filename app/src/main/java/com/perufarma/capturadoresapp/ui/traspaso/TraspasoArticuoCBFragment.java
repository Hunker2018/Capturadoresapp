package com.perufarma.capturadoresapp.ui.traspaso;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;
import com.perufarma.capturadoresapp.retrofit.RetrofitInstance;

public class TraspasoArticuoCBFragment extends Fragment {

    private Context context;
    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;


    private EditText txtTCBArticulo;
    private Button btn_tCBIngresar, btn_tCBSalir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traspaso_hasta, container, false);
        context = getContext();

        InitialViews(view);
        retrofitInit();

        return view;
    }

    private void InitialViews(View view)
    {
        txtTCBArticulo = view.findViewById(R.id.txtTCBArticulo);
        btn_tCBIngresar = view.findViewById(R.id.btn_tCBIngresar);
        btn_tCBSalir = view.findViewById(R.id.btn_tCBSalir);

        btn_tCBIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void retrofitInit() {
        retrofitInstance = RetrofitInstance.getInstance();
        interfaceAPI = retrofitInstance.getInterfaceAPI();
    }
}
