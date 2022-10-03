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
import androidx.fragment.app.FragmentTransaction;

import com.perufarma.capturadoresapp.R;
import com.perufarma.capturadoresapp.retrofit.InterfaceAPI;

public class ConsultaArticuloFragment extends Fragment {

    private InterfaceAPI interfaceAPI;
    private Context context;
    private EditText txtCAAlmacen, txtCAZona, txtCASituacion;
    private Button btn_caUbicaciones, btn_caArticulos, btn_caSalir;

    public ConsultaArticuloFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ca_consulta, container, false);
        context = getContext();
        InitialViews(view);

        return view;
    }

    private void InitialViews(View view)
    {
        txtCAAlmacen = view.findViewById(R.id.txtCAAlmacen);
        txtCAZona = view.findViewById(R.id.txtCAZona);
        txtCASituacion = view.findViewById(R.id.txtCASituacion);

        btn_caUbicaciones = view.findViewById(R.id.btn_caUbicaciones);
        btn_caArticulos = view.findViewById(R.id.btn_caArticulos);
        btn_caSalir = view.findViewById(R.id.btn_caSalir);

        btn_caUbicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String vAlmacen =txtCAAlmacen.getText().toString();
               String vZona = txtCAZona.getText().toString();
               String vSituacion = txtCASituacion.getText().toString();

               if(vAlmacen.equals("")){
                   Toast.makeText(context, "Ingrese el almacén", Toast.LENGTH_SHORT).show();
               }else if(vZona.equals("")){
                   Toast.makeText(context, "Ingrese la zona", Toast.LENGTH_SHORT).show();
               }else if (vSituacion.equals("")){
                   Toast.makeText(context, "Ingrese la situación", Toast.LENGTH_SHORT).show();
               }else{

                   Bundle args = new Bundle();
                   args.putString("almacenca", vAlmacen);
                   args.putString("zonaca", vZona);
                   args.putString("situacionca", vSituacion);

                   FragmentTransaction trans = getFragmentManager().beginTransaction();
                   ConsultaArticuloUbiFragment consultaArticuloUbiFragment = new ConsultaArticuloUbiFragment();
                   consultaArticuloUbiFragment.setArguments(args);
                   trans.replace(R.id.fragmentCA, consultaArticuloUbiFragment);
                   trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                   trans.addToBackStack(null);
                   trans.commit();
               }
            }
        });
    }
}
