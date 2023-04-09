package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp.controladores.UsuarioBD;
import com.example.tp.modelos.Usuario;

import java.util.Arrays;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;

    private EditText nombre,apellido,username,password,otraPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        init();
    }

    public void init(){
        context= getApplicationContext();
        nombre=findViewById(R.id.nombreRegister1);
        apellido=findViewById(R.id.apellidoRegister1);
        username=findViewById(R.id.usernameRegister1);
        password=findViewById(R.id.passwordRegister1);
        otraPassword=findViewById(R.id.otraPasswordRegister1);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register1:
                UsuarioBD usuarioBD=new UsuarioBD(context,"UsuarioBD.db",null,1);

                String nombreText=nombre.getText().toString();
                String apellidoText=apellido.getText().toString();
                String userText=username.getText().toString();
                String passwordText=password.getText().toString();
                String otraPasswordText=otraPassword.getText().toString();
                System.out.println(passwordText);
                System.out.println(otraPasswordText);
                //if(passwordText==otraPasswordText) {
                    Usuario usuario = new Usuario(userText, passwordText, nombreText, apellidoText);
                    usuarioBD.crear(usuario);

                    Toast.makeText(context,"Usuario creado con exito",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(context,MainActivity.class);
                    startActivity(i);
                //}else Toast.makeText(context,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();

                break;
            case R.id.volverLogin1:
                Intent j = new Intent(context,MainActivity.class);
                startActivity(j);
                break;
        }
    }
}