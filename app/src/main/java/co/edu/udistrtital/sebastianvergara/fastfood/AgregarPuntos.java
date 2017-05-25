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
        idLugar = (EditText) findViewById(R.id.idLugar);
        btnGuardar = (Button) findViewById(R.id.btnGuardarAdd);
        verificarExistencia = (Button) findViewById(R.id.btnVerificarExistencia);


        final DatosLugaresComida miVerificacion = new DatosLugaresComida(getApplicationContext());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    SQLiteDatabase datos = miVerificacion.getReadableDatabase();
                    ContentValues dLugares = new ContentValues();
                    dLugares.put(DatosLugaresComida.DatosTabla.COLUMNA_ID,idLugar.getText().toString());
                    dLugares.put(DatosLugaresComida.DatosTabla.COLUMNA_LUGAR,nombreLugar.getText().toString());
                    dLugares.put(DatosLugaresComida.DatosTabla.COLUMNA_LAT,latLugar.getText().toString());
                    dLugares.put(DatosLugaresComida.DatosTabla.COLUMNA_LNG,lngLugar.getText().toString());

                    Long datoLugar = datos.insert(DatosLugaresComida.DatosTabla.NOMBRE_TABLA, DatosLugaresComida.DatosTabla.COLUMNA_ID, dLugares);
                    Toast.makeText(getApplicationContext(), "Dato Guardado Exitosamente", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error Al Guardar El Dato", Toast.LENGTH_LONG).show();

                }

            }
        });

        verificarExistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    SQLiteDatabase db = miVerificacion.getReadableDatabase();

                    String [] argselect = {nombreLugar.getText().toString()};
                    String [] projection = {DatosLugaresComida.DatosTabla.COLUMNA_LUGAR,DatosLugaresComida.DatosTabla.COLUMNA_LAT, DatosLugaresComida.DatosTabla.COLUMNA_LNG};
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
