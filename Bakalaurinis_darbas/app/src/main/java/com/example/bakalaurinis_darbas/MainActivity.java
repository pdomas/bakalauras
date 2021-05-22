package com.example.bakalaurinis_darbas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.bakalaurinis_darbas.Services.ReservationHistoryService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button active_reservation = (Button)findViewById(R.id.active_reservation);

        active_reservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent new_intent = new Intent(MainActivity.this, ActiveReservationActivity.class);
                startActivity(new_intent);
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

        Button btn_reservation_history= (Button)findViewById(R.id.reservation_history_btn);
        btn_reservation_history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ReservationHistoryActivity.class);
                startActivity(new Intent(intent));
                finish();
            }
        });

        Button btn_payment_method= (Button)findViewById(R.id.payment_method);
        btn_payment_method.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.paysera.lt/v2/en-LT/index"));
                startActivity(browserIntent);
            }
        });

        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn);
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapsActivityCurrentPlace.class));
                finish();
            }
        });

        ImageButton btn_search = (ImageButton)findViewById(R.id.search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
                finish();
            }
        });
        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
                finish();
            }
        });
    }
}