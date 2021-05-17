package com.example.bakalaurodarbas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReserveParkingSpotActivity extends Activity {

    TextView Phone_number_textview, location_address_textview, price_per_hour_textview;
    JSONObject location = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_parking_spot);
        Intent intent = getIntent();
        String str = intent.getStringExtra("value");
        Phone_number_textview = findViewById(R.id.Phone_number_textview);
        location_address_textview = findViewById(R.id.location_address_textview);
        price_per_hour_textview = findViewById(R.id.price_per_hour_textview);
        Log.i("got it", str);
        LocationDataService locationDataService = new LocationDataService(ReserveParkingSpotActivity.this);
        locationDataService.getOneLocationInfo(new LocationDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                location_address_textview.setText(message);
            }

            @Override
            public void onResponse(ArrayList<String> Location_address_array) {
                location_address_textview.setText(Location_address_array.toString());
            }

            @Override
            public void onResponse(JSONArray LocationJSONList) {

            }

            @Override
            public void onResponse(JSONObject Location_object) {
                try{
                    location_address_textview.setText("Automobilių stovėjimo aikštelė adresas: \n" + Location_object.getString("location_address"));
                    Phone_number_textview.setText("Savininko telefono numeris:  \n" + Location_object.getString("contact_info"));
                    price_per_hour_textview.setText("Valandinis stovėjimo mokestis: \n" + Location_object.getString("price_per_hour") + "€");
                    location = Location_object;
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, str);
        Button Order_btn = (Button)findViewById(R.id.Order_btn);
        Order_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LocationDataService locationDataService = new LocationDataService(ReserveParkingSpotActivity.this);
                locationDataService.reserveLocation(location, "true");
                Toast toast = null;
                try {
                    toast = Toast.makeText(ReserveParkingSpotActivity.this, "Sėkmingai rezervuota: "+location.getString("location_address"), Toast.LENGTH_LONG);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                toast.show();
                try {
                    String value;
                    value = location.getString("location_address");
                    Log.e("EXSDEAEW", value);
                    Intent intent = new Intent(ReserveParkingSpotActivity.this, MainActivity.class);
                    intent.putExtra("current_reservation", value);
                    startActivity(intent);
                    finish();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn3);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReserveParkingSpotActivity.this,MainActivity.class));
            }
        });
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn3);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReserveParkingSpotActivity.this,MapsActivityCurrentPlace.class));
            }
        });
        ImageButton btn_search = (ImageButton)findViewById(R.id.search3);
        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings3);
    }
}
