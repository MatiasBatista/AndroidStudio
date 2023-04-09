package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    Context context;

    TextView bienvenida;

    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recibirDatos();

        init();

        bienvenida.setText("Bienvenido "+nombre);
    }

    public void init(){
        context= getApplicationContext();
        bienvenida=findViewById(R.id.textoBienvenida);
    }

    public void recibirDatos(){
        Bundle extras= getIntent().getExtras();
        this.nombre = extras.getString("nombre");
    }
}