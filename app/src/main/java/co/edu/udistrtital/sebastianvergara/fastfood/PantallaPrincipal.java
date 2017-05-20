package co.edu.udistrtital.sebastianvergara.fastfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        SupportMapFragment mapFragment = (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.MapView);
        mapFragment.getMapAsync(this);

        edBuscar = (EditText)findViewById(R.id.edBuscar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        miMapa = googleMap;
        miMapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        LatLng hamburguesa = new LatLng(4.628694, -74.065420);
        Marker marker = miMapa.addMarker(new MarkerOptions().position(hamburguesa).title("El Corral"));
        miMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(hamburguesa,19));

    }


}
