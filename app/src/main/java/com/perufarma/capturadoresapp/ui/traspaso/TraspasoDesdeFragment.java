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
import com.perufarma.capturadoresapp.ui.consultaproducto.ConsultaArticuloUbiFragment;

public class TraspasoDesdeFragment extends Fragment {

    private Context context;
    private RetrofitInstance retrofitInstance;
    private InterfaceAPI interfaceAPI;

    private EditText txtTAlmacen, txttZona, txtTSituacion;
    private Button btn_tIngresar, btn_tSalir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traspado_desde, container, false);
        context = getContext();

        InitialViews(view);
        retrofitInit();

        return view;
    }

    private void InitialViews(View view)
    {
        txtTAlmacen = view.findViewById(R.id.txtTAlmacen);
        txttZona = view.findViewById(R.id.txttZona);
        txtTSituacion = view.findViewById(R.id.txtTSituacion);

        btn_tIngresar = view.findViewById(R.id.btn_tIngresar);
        btn_tSalir = view.findViewById(R.id.btn_tSalir);

        btn_tIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String almacen = txtTAlmacen.getText().toString();
                String zona = txttZona.getText().toString();
                String situacion = txtTSituacion.getText().toString();

                if(almacen.trim().equals("")){
                    Toast.makeText(context, "Ingrese el almacén", Toast.LENGTH_SHORT).show();
                }else if (zona.trim().equals("")){
                    Toast.makeText(context, "Ingrese la zona", Toast.LENGTH_SHORT).show();
                }else if(situacion.trim().equals("")){
                    Toast.makeText(context, "Ingrese a situación", Toast.LENGTH_SHORT).show();
                }else{
                    Bundle args = new Bundle();
                    args.putString("almacent", almacen);
                    args.putString("zonat", zona);
                    args.putString("situaciont", situacion);

                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    TraspasoHastaFragment traspasoHastaFragment = new TraspasoHastaFragment();
                    traspasoHastaFragment.setArguments(args);
                    trans.replace(R.id.traspasoFragment, traspasoHastaFragment);
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
