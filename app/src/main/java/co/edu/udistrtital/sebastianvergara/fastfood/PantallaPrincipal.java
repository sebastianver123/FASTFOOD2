package co.edu.udistrtital.sebastianvergara.fastfood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PantallaPrincipal extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap miMapa;
    EditText edBuscar;
    Button btnLimpiar;
    Button btnBuscar;
    Button btnAgregarPunto;
    TextView tvLat;
    TextView tvLng;
    String nombreDelLugar;
    String sLat;
    String sLng;
    double lat;
    double lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapView);
        mapFragment.getMapAsync(this);

        edBuscar = (EditText) findViewById(R.id.edBuscar);
        btnLimpiar = (Button) findViewById(R.id.btnClear);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnAgregarPunto = (Button) findViewById(R.id.btnAgregarPunto);
        tvLat = (TextView) findViewById(R.id.tvLat);
        tvLng = (TextView) findViewById(R.id.tvLng);

        final DatosLugaresComida miBusqueda = new DatosLugaresComida(getApplicationContext());

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    SQLiteDatabase db = miBusqueda.getReadableDatabase();


                    String[] projection = new String[]{DatosLugaresComida.DatosTabla.COLUMNA_LUGAR, DatosLugaresComida.DatosTabla.COLUMNA_LAT, DatosLugaresComida.DatosTabla.COLUMNA_LNG};
                    String[] argselect = new String[]{edBuscar.getText().toString()};
                    Cursor c = db.query(DatosLugaresComida.DatosTabla.NOMBRE_TABLA, projection, DatosLugaresComida.DatosTabla.COLUMNA_ID+ "=?", argselect, null, null, null);

                    c.moveToFirst();
                    tvLat.setText(c.getString(1));
                    tvLng.setText(c.getString(2));
                    nombreDelLugar = c.getString(0);
                    sLat = c.getString(1);
                    sLng = c.getString(2);
                    lat = Double.parseDouble(sLat) ;
                    lng = Double.parseDouble(sLng);


                    miMapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                    LatLng posLugar = new LatLng(lat,lng);
                    Marker marker = miMapa.addMarker(new MarkerOptions().position(posLugar).title(nombreDelLugar).draggable(true));
                    miMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posLugar,18));

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No se encuentra el lugar buscado", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    miMapa.clear();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Intente de nuevo", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAgregarPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PantallaPrincipal.this, AgregarPuntos.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        miMapa = googleMap;
        miMapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }


}
