package com.example.bakalaurinis_darbas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakalaurinis_darbas.Services.LocationService;
import com.example.bakalaurinis_darbas.Services.ReservationService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class ReservationWindowActivity extends AppCompatActivity {

    UUID user_id = UUID.fromString("6acadb30-a0c6-4606-959c-5f9810d80da2");
    JSONObject location = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_window);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SpinnerResources, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView Phone_number_textview = (TextView)findViewById(R.id.Phone_number_textview);
        TextView location_address_textview = (TextView)findViewById(R.id.location_address_textview);
        TextView price_per_hour_textview = (TextView)findViewById(R.id.price_per_hour_textview);

        Intent intent = getIntent();
        String str = intent.getStringExtra("value");
        LocationService locationService = new LocationService(ReservationWindowActivity.this);
        locationService.getOneLocationInfo(new LocationService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                location_address_textview.setText(message);
            }

            @Override
            public void onResponse(ArrayList<String> Location_address_array) {
                location_address_textview.setText(Location_address_array.toString());
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

            @Override
            public void onResponse(JSONArray LocationJSONList) {

            }
        }, str);

        ReservationService reservationService = new ReservationService(ReservationWindowActivity.this);
        Button Order_btn = (Button)findViewById(R.id.Order_btn);
        Order_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String spinner_text = spinner.getSelectedItem().toString();
                if(!spinner_text.equals("Kiek laiko")){
                    locationService.reserveLocation(location, "true");
                    reservationService.Reservation(location, user_id, Integer.parseInt(spinner_text.replaceAll("\\D+","")));
                    try {
                        Toast toast = Toast.makeText(ReservationWindowActivity.this, "Sėkmingai rezervuota: "+location.getString("location_address"), Toast.LENGTH_LONG);
                        toast.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(ReservationWindowActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast toast = Toast.makeText(ReservationWindowActivity.this, "Nepasirinktas laikas", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn3);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationWindowActivity.this,MainActivity.class));
            }
        });
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn3);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationWindowActivity.this,MapsActivityCurrentPlace.class));
            }
        });
        ImageButton btn_search = (ImageButton)findViewById(R.id.search3);
        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings3);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationWindowActivity.this,SettingsActivity.class));
            }
        });

    }
}