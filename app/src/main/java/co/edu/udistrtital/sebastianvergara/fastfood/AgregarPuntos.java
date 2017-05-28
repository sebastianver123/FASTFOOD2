package co.edu.udistrtital.sebastianvergara.fastfood;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarPuntos extends AppCompatActivity {

    EditText nombreLugar;
    EditText latLugar;
    EditText lngLugar;
    EditText idLugar;
    Button btnGuardar;
    Button verificarExistencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_puntos);

        nombreLugar = (EditText) findViewById(R.id.nombreLugar);
        latLugar = (EditText) findViewById(R.id.latLugar);
        lngLugar = (EditText) findViewById(R.id.lngLugar);
        idLugar = (EditText) findViewById(R.id.idLugarComida);
        btnGuardar = (Button) findViewById(R.id.btnGuardarAdd);
        verificarExistencia = (Button) findViewById(R.id.btnVerificarExistencia);


        final DatosLugaresComida miVerificacion = new DatosLugaresComida(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    SQLiteDatabase db = miVerificacion.getWritableDatabase();
                    ContentValues datos = new ContentValues();
                    datos.put(DatosLugaresComida.DatosTabla.COLUMNA_ID,idLugar.getText().toString());
                    datos.put(DatosLugaresComida.DatosTabla.COLUMNA_LUGAR,nombreLugar.getText().toString());
                    datos.put(DatosLugaresComida.DatosTabla.COLUMNA_LAT,latLugar.getText().toString());
                    datos.put(DatosLugaresComida.DatosTabla.COLUMNA_LNG,lngLugar.getText().toString());

                    Long idGuardado = db.insert(DatosLugaresComida.DatosTabla.NOMBRE_TABLA, null, datos);

                    Toast.makeText(getApplicationContext(), "Lugar Guardado "+idGuardado, Toast.LENGTH_LONG).show();

            }
        });

        verificarExistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    SQLiteDatabase db = miVerificacion.getReadableDatabase();


                    String [] projection = new String[]{DatosLugaresComida.DatosTabla.COLUMNA_LUGAR,DatosLugaresComida.DatosTabla.COLUMNA_LAT, DatosLugaresComida.DatosTabla.COLUMNA_LNG};
                    String [] argselect = new String[]{idLugar.getText().toString()};
                    Cursor c = db.query(DatosLugaresComida.DatosTabla.NOMBRE_TABLA , projection, DatosLugaresComida.DatosTabla.COLUMNA_ID+"=?",argselect,null,null,null);

                    c.moveToFirst();
                    nombreLugar.setText(c.getString(0));
                    latLugar.setText(c.getString(1));
                    lngLugar.setText(c.getString(2));

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No existe aún", Toast.LENGTH_LONG).show();
                }

            }
        });




    }
}
