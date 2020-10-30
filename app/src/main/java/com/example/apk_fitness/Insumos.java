package com.example.apk_fitness;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apk_fitness.clases.AdminSQLiteOpenHelper;

public class Insumos extends AppCompatActivity {
    private EditText edcodigo, ednombre, edprecio, edstock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);
        edcodigo = (EditText)findViewById(R.id.et_codigo);
        ednombre = (EditText)findViewById(R.id.et_nombre);
        edprecio = (EditText)findViewById(R.id.et_precio);
        edstock = (EditText)findViewById(R.id.et_stock);
    }
    public void AddInsumos(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = edcodigo.getText().toString();
        if(!codigo.isEmpty()){
            ContentValues cont = new ContentValues();
            cont.put("codigo",edcodigo.getText().toString());
            cont.put("nombre",ednombre.getText().toString());
            cont.put("precio",edprecio.getText().toString());
            cont.put("stock",edstock.getText().toString());
            db.insert("insumos",null,cont);
            db.close();
            Toast.makeText(this, "Has guardado un valor", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debe ingresar un codigo", Toast.LENGTH_SHORT).show();
        }
    }
    public void ShowInsumos(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = edcodigo.getText().toString();
        if(!codigo.isEmpty()){
            Cursor fila = db.rawQuery("SELECT nombre, precio, stock FROM insumos WHERE codigo = "+codigo, null);
            if(fila.moveToFirst()){
                ednombre.setText(fila.getString(0));
                edprecio.setText(fila.getString(1));
                edstock.setText(fila.getString(2));
            }
            else{
                Toast.makeText(this, "No hay campos en la entidad insumos", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No hay insumo al codigo asociado", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public void DeleteInsumos(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = edcodigo.getText().toString();
        db.delete("insumos","codigo="+codigo,null);
        db.close();
        Toast.makeText(this, "Has eliminado el insumo", Toast.LENGTH_SHORT).show();
        edcodigo.setText("");
        ednombre.setText("");
        edprecio.setText("");
        edstock.setText("");
    }
    public void UpdateInsumos(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"fichero",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = edcodigo.getText().toString();
        ContentValues cont = new ContentValues();
        cont.put("codigo",edcodigo.getText().toString());
        cont.put("nombre",ednombre.getText().toString());
        cont.put("precio",edprecio.getText().toString());
        cont.put("stock",edstock.getText().toString());
        if(!codigo.isEmpty()){
            db.update("insumos",cont,"codigo="+codigo,null);
            db.close();
            Toast.makeText(this, "Has actualizado un campo", Toast.LENGTH_SHORT).show();
        }
    }
}