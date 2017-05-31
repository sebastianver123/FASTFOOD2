package co.edu.udistrtital.sebastianvergara.fastfood;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnsingin;
    Button btnsingup;
    EditText edCorreo;
    EditText edContraseña;
    EditText edCodigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsingin = (Button)findViewById(R.id.btnin);
        btnsingup = (Button)findViewById(R.id.btnup);
        edCorreo = (EditText)findViewById(R.id.correo);
        edContraseña = (EditText)findViewById(R.id.pass);
        edCodigo = (EditText)findViewById(R.id.codigo);


        final BaseDeDatos ayudaDatos = new BaseDeDatos(getApplicationContext());

        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = ayudaDatos.getReadableDatabase();
                ContentValues misDatos = new ContentValues();
                misDatos.put(BaseDeDatos.DatosDeTabla.COLUMNA_CODIGO,edCodigo.getText().toString());
                misDatos.put(BaseDeDatos.DatosDeTabla.COLUMNA_CORREO,edCorreo.getText().toString());
                misDatos.put(BaseDeDatos.DatosDeTabla.COLUMNA_CONTRASEÑA,edContraseña.getText().toString());

                Long DatoGuardado = db.insert(BaseDeDatos.DatosDeTabla.NOMBRE_TABLA, BaseDeDatos.DatosDeTabla.COLUMNA_CORREO, misDatos);
                Toast.makeText(getApplicationContext(), "Ha creado su cuenta. "+DatoGuardado, Toast.LENGTH_LONG).show();

            }
        });

         btnsingin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 try {

                     SQLiteDatabase db = ayudaDatos.getReadableDatabase();


                     String [] projection = new String[]{BaseDeDatos.DatosDeTabla.COLUMNA_CORREO, BaseDeDatos.DatosDeTabla.COLUMNA_CONTRASEÑA};
                     String [] argselect = new String[]{edCodigo.getText().toString()};
                     Cursor c = db.query(BaseDeDatos.DatosDeTabla.NOMBRE_TABLA, projection, BaseDeDatos.DatosDeTabla.COLUMNA_CODIGO+"=?",argselect,null,null,null);

                     c.moveToFirst();
                     edCorreo.setText(c.getString(0));
                     String correo = c.getString(0);
                     String contraseña = c.getString(1);

                     if(correo.equals(edCorreo.getText().toString())){
                         if(contraseña.equals(edContraseña.getText().toString())){
                             Intent intent = new Intent(MainActivity.this, PantallaPrincipal.class);
                             startActivity(intent);
                         }else{
                             Toast.makeText(getApplicationContext(), "Ingrese Contraseña", Toast.LENGTH_LONG).show();
                         }
                     }else {
                         Toast.makeText(getApplicationContext(), "Cuenta o Contraseña incorrecta", Toast.LENGTH_LONG).show();
                     }

                 }catch (Exception e){
                     Toast.makeText(getApplicationContext(), "Cree una cuenta", Toast.LENGTH_LONG).show();
                 }


             }
         });


    }

}