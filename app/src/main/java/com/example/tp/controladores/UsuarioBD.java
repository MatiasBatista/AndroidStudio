package com.example.tp.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.database.sqlite.SQLiteDatabaseKt;

import com.example.tp.modelos.Usuario;

public class UsuarioBD extends SQLiteOpenHelper implements IusuarioBD{

    Context contexto;

    public UsuarioBD(@Nullable Context contexto, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version ){

        super(contexto,name,factory,version);
        this.contexto=contexto;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql= "CREATE TABLE usuarios("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, "+
                    "password TEXT, "+
                    "nombre TEXT, " +
                    "apellido TEXT)" ;

        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL("INSERT INTO usuarios (username,password,nombre,apellido) VALUES ('juani',4812274,'juan','etcheverry')");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public Usuario elemento(String username) {
        SQLiteDatabase database= getReadableDatabase();
        Cursor cursor= database.rawQuery("SELECT * FROM usuarios WHERE username= '" +username+"'",null);
        try{
        if (cursor.moveToNext()){
            return extraerUsuario(cursor);
        }
        else
            return null;
        }
        catch (Exception e ){
            Log.d("TAG","Error  elemento(id) LibroBD" + e.getMessage());
            throw e;
        }
        finally {
            if(cursor != null){
                cursor.close();
            }
        }
    }

    private Usuario extraerUsuario(Cursor cursor) {
        Usuario usuario= new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setUsername(cursor.getString(1));
        usuario.setPassword(cursor.getString(2));
        usuario.setNombre(cursor.getString(3));
        usuario.setApellido(cursor.getString(4));
        return usuario;
    }

    @Override
    public void crear(Usuario usuario) {
        SQLiteDatabase database= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("username",usuario.getUsername());
        values.put("password",usuario.getPassword());
        values.put("nombre",usuario.getNombre());
        values.put("apellido",usuario.getApellido());
        database.insert("usuarios",null,values);

    }

    @Override
    public boolean validacion(String username, String password) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor= database.rawQuery("SELECT * FROM usuarios WHERE username= '" +username+"' and password='"+password+"'",null);
        try{
            if (cursor.moveToNext()){
                return true;
            }
            else return false;
        }
        catch (Exception e ){
            Log.d("TAG","Error  validacion(username,password) UsuarioBD" + e.getMessage());
            throw e;
        }
        finally {
            if(cursor != null){
                cursor.close();
            }
        }
    }
}
