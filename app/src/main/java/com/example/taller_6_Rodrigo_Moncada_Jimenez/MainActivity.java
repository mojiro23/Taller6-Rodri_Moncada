package com.example.taller_6_Rodrigo_Moncada_Jimenez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.taller_6_Rodrigo_Moncada_Jimenez.Fragments.FrmListaCompras;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new FrmListaCompras()).commit();
    }
}
