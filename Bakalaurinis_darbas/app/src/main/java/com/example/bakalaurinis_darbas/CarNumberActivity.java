package com.example.bakalaurinis_darbas;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakalaurinis_darbas.Services.CarNumberService;

import org.json.JSONException;
import org.json.JSONObject;

public class CarNumberActivity extends AppCompatActivity {

    UUID user_id = UUID.fromString("6acadb30-a0c6-4606-959c-5f9810d80da2");
    JSONObject car_number_object = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_number);
        EditText car_number = (EditText)findViewById(R.id.car_number_exittext);
        TextView current_car_number = (TextView)findViewById(R.id.current_car_number);
        Button save_car_number = (Button)findViewById(R.id.save_car_number_btn);

        CarNumberService carNumberService = new CarNumberService(CarNumberActivity.this);
        carNumberService.getJSONObejctCarNumber(new CarNumberService.VolleyResponseListener() {
            @Override
            public void onError(String message) {}
            @Override
            public void onResponse(JSONObject Location_object) {
                try {
                    current_car_number.setText("Jūsų mašinos numers: " + Location_object.getString("car_number"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, user_id);

        save_car_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = null;
                String car_number_text = car_number.getText().toString();
                if(!car_number_text.equals("") && car_number_text.length() == 6){
                        carNumberService.getJSONObejctCarNumber(new CarNumberService.VolleyResponseListener() {
                            @Override
                            public void onError(String message) {
                                try{
                                    car_number_object.put("car_number_id", UUID.randomUUID());
                                    car_number_object.put("car_number", car_number_text);
                                    car_number_object.put("user_id", user_id);
                                    carNumberService.changeCarNumber(car_number_object);
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onResponse(JSONObject Car_number_object) {
                                try {
                                    Car_number_object.put("car_number", car_number_text);
                                    carNumberService.changeCarNumber(Car_number_object);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, user_id);
                    toast = Toast.makeText(CarNumberActivity.this, "Sėkmingai išsaugotas mašinos numeris: "+car_number.getText().toString(), Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent= new Intent(CarNumberActivity.this,MainActivity.class);
                    startActivity(new Intent(intent));
                    finish();
                }
                else{
                    toast = Toast.makeText(CarNumberActivity.this, "Nepavyko išsaugoti mašinos numerio", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn1);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CarNumberActivity.this,MainActivity.class));
                finish();
            }
        });
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn1);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CarNumberActivity.this,MapsActivityCurrentPlace.class));
                finish();
            }
        });
        ImageButton btn_search = (ImageButton)findViewById(R.id.search1);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CarNumberActivity.this,SearchActivity.class));
                finish();
            }
        });
        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings1);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CarNumberActivity.this,SearchActivity.class));
                finish();
            }
        });
    }
}