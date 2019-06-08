package com.example.taller_6_Rodrigo_Moncada_Jimenez.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taller_6_Rodrigo_Moncada_Jimenez.Adapters.ArticuloAdapter;
import com.example.taller_6_Rodrigo_Moncada_Jimenez.Modelos.Articulo;
import com.example.taller_6_Rodrigo_Moncada_Jimenez.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FrmListaCompras extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Articulo> mArticulo = new ArrayList<Articulo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frm_lista_compras, container, false);
        setupUI(view);
        muestraArticulos(view);
        return view;
    }

    private void muestraArticulos(View view)
    {
        final ArticuloAdapter adapter = new ArticuloAdapter(getContext(), R.layout.list_item_element,mArticulo);
        final ListView listView = (ListView)  view.findViewById(R.id.lstView);
        adapter.clear();

        db.collection("articulos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                String nombre = "Articulo: " + document.get("nombre").toString();
                                String cantidad = "Cantidad: " + document.get("cantidad").toString();
                                Articulo artic = new Articulo(nombre,cantidad);
                                adapter.add(artic);
                            }

                            listView.setAdapter(adapter);

                        } else {
                            Toast.makeText(getContext(),"Error al obtener los datos",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void setupUI (final View view)
    {
        final EditText wEditNombre = (EditText)  view.findViewById(R.id.edit_nombre);
        final EditText wEditCAntidad = (EditText)  view.findViewById(R.id.edit_cantidad);

        Button btnNext = (Button) view.findViewById(R.id.button_tomardatos);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui va el codigo
                Articulo artic = new Articulo();

                final String wNombre = wEditNombre.getText().toString();
                String wCantidad = wEditCAntidad.getText().toString();

                artic.setNombre(wNombre);
                artic.setCantidad(wCantidad);

                // Add a new document with a generated ID
                db.collection("articulos")
                        .add(artic)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getContext(),"Se agregaron los datos",Toast.LENGTH_SHORT).show();
                                wEditNombre.setText("");
                                wEditCAntidad.setText("");
                                wEditNombre.clearFocus();
                                muestraArticulos(view);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"No se agregaron los datos",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }


}
