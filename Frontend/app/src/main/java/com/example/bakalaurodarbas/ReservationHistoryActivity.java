package com.example.bakalaurodarbas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ReservationHistoryActivity extends Activity {
    ListView history_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservartion_history);

        history_listview = findViewById(R.id.history_listview);
        LocationDataService locationDataService = new LocationDataService(ReservationHistoryActivity.this);
        locationDataService.getAllLocationAddress(new LocationDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(ReservationHistoryActivity.this,android.R.layout.simple_list_item_1, Collections.singletonList("Something wrong"));
                history_listview.setAdapter(arrayAdapter);
            }
            @Override
            public void onResponse(ArrayList<String> Location_address_array) {
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(ReservationHistoryActivity.this,android.R.layout.simple_list_item_1, Location_address_array);
                history_listview.setAdapter(arrayAdapter);
            }

            @Override
            public void onResponse(JSONArray LocationJSONList) {

            }

            @Override
            public void onResponse(JSONObject Location_object) {

            }
        }, "true");

        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn5);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationHistoryActivity.this,MainActivity.class));
                finish();
            }
        });
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn5);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationHistoryActivity.this,MapsActivityCurrentPlace.class));
                finish();
            }
        });
        ImageButton btn_search = (ImageButton)findViewById(R.id.search5);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationHistoryActivity.this,SearchActivity.class));
                finish();
            }
        });

        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings5);
    }
}
