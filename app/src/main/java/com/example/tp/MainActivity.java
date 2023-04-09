package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp.controladores.UsuarioBD;
import com.example.tp.modelos.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    Button btnLogin;

    private EditText username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        context= getApplicationContext();
        btnLogin=findViewById(R.id.login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                UsuarioBD usuarioBD=new UsuarioBD(context,"UsuarioBD.db",null,1);
                Usuario usuario= usuarioBD.elemento(username.getText().toString());
                Toast.makeText(context,usuario.getNombre(),Toast.LENGTH_LONG).show();
                break;

        }
    }
}