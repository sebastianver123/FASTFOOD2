package co.edu.udistrtital.sebastianvergara.fastfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnsingin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsingin = (Button)findViewById(R.id.btnin);

         btnsingin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                 startActivity(intent);

             }
         });
    }





}
