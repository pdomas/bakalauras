package com.example.bakalaurinis_darbas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bakalaurinis_darbas.Services.LocationService;
import com.example.bakalaurinis_darbas.Services.ReservationHistoryService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class ReservationHistoryActivity extends AppCompatActivity {

    UUID user_id = UUID.fromString("6acadb30-a0c6-4606-959c-5f9810d80da2");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_history);
        EditText et_dataInput = (EditText) findViewById(R.id.et_dataInput);
        ListView lv_locationList = (ListView) findViewById(R.id.lv_locationList);
        ReservationHistoryService reservationHistoryService = new ReservationHistoryService(ReservationHistoryActivity.this);
        reservationHistoryService.getAllReservations(new ReservationHistoryService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(ReservationHistoryActivity.this, android.R.layout.simple_list_item_1, Collections.singletonList("Something wrong"));
                lv_locationList.setAdapter(arrayAdapter);
            }

            @Override
            public void onResponse(ArrayList<String> Location_address_array) {
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(ReservationHistoryActivity.this, android.R.layout.simple_list_item_1, Location_address_array);
                lv_locationList.setAdapter(arrayAdapter);
                et_dataInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
            }
        }, user_id);

        ImageButton btn_menu = (ImageButton) findViewById(R.id.menu_btn2);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationHistoryActivity.this, MainActivity.class));
                finish();
            }
        });
        ImageButton btn_map = (ImageButton) findViewById(R.id.map_btn2);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationHistoryActivity.this, MapsActivityCurrentPlace.class));
                finish();
            }
        });
        ImageButton btn_search = (ImageButton) findViewById(R.id.search2);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationHistoryActivity.this, SearchActivity.class));
                finish();
            }
        });
        ImageButton btn_settings = (ImageButton) findViewById(R.id.settings2);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ReservationHistoryActivity.this, SettingsActivity.class));
                finish();
            }
        });
    }
}