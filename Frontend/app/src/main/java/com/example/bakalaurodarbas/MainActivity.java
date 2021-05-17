package com.example.bakalaurodarbas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private String reservation= "Jūs neturite rezervacijos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Button active_reservation = (Button)findViewById(R.id.active_reservation);

        active_reservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent intent = getIntent();
                    reservation = intent.getStringExtra("current_reservation");
                    Intent new_intent = new Intent(MainActivity.this, CurrentReservationActivity.class);
                    new_intent.putExtra("reservation", reservation);
                    startActivity(new_intent);
                    finish();
                }catch (Exception e){
                    Intent new_intent = new Intent(MainActivity.this, CurrentReservationActivity.class);
                    startActivity(new_intent);
                }
            }
        });
        Button reservation_history_btn = (Button)findViewById(R.id.reservation_history_btn);
        reservation_history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_intent = new Intent(MainActivity.this, ReservationHistoryActivity.class);
                startActivity(new_intent);
                finish();
            }
        });
        ImageButton btn_menu = (ImageButton)findViewById(R.id.map_btn5);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MapsActivityCurrentPlace.class);
                startActivity(new Intent(intent));
                finish();
            }
        });
        ImageButton btn_search= (ImageButton)findViewById(R.id.search5);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,SearchActivity.class);
                startActivity(new Intent(intent));
                finish();
            }
        });

        Button btn_auto_nr= (Button)findViewById(R.id.auto_nr);
        btn_auto_nr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,CarNumberActivity.class);
                startActivity(new Intent(intent));
                finish();
            }
        });
        Button payment_method = (Button)findViewById(R.id.payment_method);
        payment_method.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.paysera.lt/v2/en-LT/index"));
                startActivity(browserIntent);
            }
        });

    }

}