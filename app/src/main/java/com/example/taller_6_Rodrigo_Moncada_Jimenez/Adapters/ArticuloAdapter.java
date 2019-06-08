package com.example.taller_6_Rodrigo_Moncada_Jimenez.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.taller_6_Rodrigo_Moncada_Jimenez.Modelos.Articulo;
import com.example.taller_6_Rodrigo_Moncada_Jimenez.R;

import java.util.List;

public class ArticuloAdapter extends ArrayAdapter<Articulo> {
    int mLayautId;
    public ArticuloAdapter(Context context, int layoutid, List<Articulo> items) {
        super(context, layoutid, items);
        mLayautId = layoutid;
    }

    @Override
    public View getView (int position, View view, ViewGroup parent)
    {
        Articulo articulo = getItem(position);
        String name = articulo.getNombre();
        String cantidad = articulo.getCantidad();


        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mLayautId,parent,false);
        }

        TextView nameView = (TextView) view.findViewById(R.id.txtName);
        TextView cantidadView = (TextView) view.findViewById(R.id.txtCantidad);


        nameView.setText(name);
        cantidadView.setText(cantidad);

        return view;

    }
}
