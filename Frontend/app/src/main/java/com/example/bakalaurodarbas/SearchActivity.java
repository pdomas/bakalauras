package com.example.bakalaurodarbas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class SearchActivity extends Activity {

    EditText et_dataInput;
    ListView lv_locationList;
    TextView textView;
    ArrayList<String> Location_address_array = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_locationList = findViewById(R.id.lv_locationList);
        LocationDataService locationDataService = new LocationDataService(SearchActivity.this);
        locationDataService.getAllLocationAddress(new LocationDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1, Collections.singletonList("Something wrong"));
                lv_locationList.setAdapter(arrayAdapter);
            }
            @Override
            public void onResponse(ArrayList<String> Location_address_array) {
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1, Location_address_array);
                lv_locationList.setAdapter(arrayAdapter);
                et_dataInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

            }

            @Override
            public void onResponse(JSONArray LocationJSONList) {

            }

            @Override
            public void onResponse(JSONObject Location_object) {

            }
        }, "false");

        lv_locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object obj = lv_locationList.getAdapter().getItem(position);
                String value= obj.toString();
                Intent intent= new Intent(SearchActivity.this,ReserveParkingSpotActivity.class);
                intent.putExtra("value", value);
                startActivity(intent);
                finish();
            }
        });

        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn2);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,MainActivity.class));
                finish();
            }
        });
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn2);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,MapsActivityCurrentPlace.class));
                finish();
            }
        });
        ImageButton btn_search = (ImageButton)findViewById(R.id.search2);
        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings2);
    }
}
