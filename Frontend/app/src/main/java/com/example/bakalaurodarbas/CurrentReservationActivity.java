package com.example.bakalaurodarbas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CurrentReservationActivity extends Activity {
    private JSONObject location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reservation);
        TextView location_address_textview = (TextView)(findViewById(R.id.address_textview));
        TextView Phone_number_textview = (TextView)(findViewById(R.id.phone_number_textview));
        TextView price_per_hour_textview = (TextView)(findViewById(R.id.price_textview));
        location_address_textview.setText("Jūs neturite rezervacijos");
        try{
            Intent intent = getIntent();
            String current_reservation = intent.getStringExtra("reservation");
            LocationDataService locationDataService = new LocationDataService(CurrentReservationActivity.this);
            locationDataService.getOneLocationInfo(new LocationDataService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    location_address_textview.setText(message);
                }

                @Override
                public void onResponse(ArrayList<String> Location_address_array) {
                }

                @Override
                public void onResponse(JSONArray LocationJSONList) {

                }

                @Override
                public void onResponse(JSONObject Location_object) {
                    try{
                        if(Location_object.getString("reservation_status").equals("true")){
                            location_address_textview.setText("Automobilių stovėjimo aikštelė adresas: \n" + Location_object.getString("location_address"));
                            Phone_number_textview.setText("Savininko telefono numeris:  \n" + Location_object.getString("contact_info"));
                            price_per_hour_textview.setText("Valandinis stovėjimo mokestis: \n" + Location_object.getString("price_per_hour") + "€");
                            location = Location_object;
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, current_reservation);
        }catch (Exception e){

            }

        Button finish_btn = (Button)findViewById(R.id.Finish_reservation_btn);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = null;
                try{
                    LocationDataService locationDataService = new LocationDataService(CurrentReservationActivity.this);
                    locationDataService.reserveLocation(location, "false");
                    try {
                        toast = Toast.makeText(CurrentReservationActivity.this, "Sėkmingai užbaigta rezervuota: "+location.getString("location_address"), Toast.LENGTH_LONG);
                        toast.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                }
                }
                catch (Exception e){
                    try {
                        toast = Toast.makeText(CurrentReservationActivity.this, "Neturite aktyvios rezervacijos", Toast.LENGTH_LONG);
                        toast.show();
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
                Intent intent = new Intent(CurrentReservationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn5);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CurrentReservationActivity.this,MainActivity.class));
                finish();
            }
        });
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn5);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CurrentReservationActivity.this,MapsActivityCurrentPlace.class));
                finish();
            }
        });
        ImageButton btn_search = (ImageButton)findViewById(R.id.search5);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CurrentReservationActivity.this,SearchActivity.class));
                finish();
            }
        });

        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings5);
    }
}

