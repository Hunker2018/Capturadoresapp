package com.perufarma.capturadoresapp.ui.traspaso;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;
import com.perufarma.capturadoresapp.retrofit.RetrofitInstance;

public class TraspasoHastaFragment  extends Fragment {


    private Context context;
    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;

    private EditText txtTHAlmacen, txtTHZona, txtTHSituacion;
    private Button btn_tHIngresar, btn_tHSalir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traspaso_hasta, container, false);
        context = getContext();

        InitialViews(view);
        retrofitInit();

        return view;
    }

    private void InitialViews(View view){

        txtTHAlmacen = view.findViewById(R.id.txtTHAlmacen);
        txtTHZona = view.findViewById(R.id.txtTHZona);
        txtTHSituacion = view.findViewById(R.id.txtTHSituacion);

        btn_tHIngresar = view.findViewById(R.id.btn_tHIngresar);
        btn_tHSalir = view.findViewById(R.id.btn_tHSalir);

        btn_tHIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String almacen = txtTHAlmacen.getText().toString();
                String zona = txtTHZona.getText().toString();
                String situacion = txtTHSituacion.getText().toString();

                if(almacen.trim().equals("")){
                    Toast.makeText(context, "Ingrese el almacén", Toast.LENGTH_SHORT).show();
                }else if (zona.trim().equals("")){
                    Toast.makeText(context, "Ingrese la zona", Toast.LENGTH_SHORT).show();
                }else if(situacion.trim().equals("")){
                    Toast.makeText(context, "Ingrese a situación", Toast.LENGTH_SHORT).show();
                }else{
                    Bundle args = new Bundle();
                    args.putString("almacenth", almacen);
                    args.putString("zonath", zona);
                    args.putString("situacionth", situacion);

                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    TraspasoArticuoCBFragment traspasoArticuoCBFragment = new TraspasoArticuoCBFragment();
                    traspasoArticuoCBFragment.setArguments(args);
                    trans.replace(R.id.traspasoFragment, traspasoArticuoCBFragment);
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }
            }
        });

    }

    private void retrofitInit() {
        retrofitInstance = RetrofitInstance.getInstance();
        interfaceAPI = retrofitInstance.getInterfaceAPI();
    }

}
