package com.example.bakalaurinis_darbas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakalaurinis_darbas.Services.LocationService;
import com.example.bakalaurinis_darbas.Services.ReservationHistoryService;
import com.example.bakalaurinis_darbas.Services.ReservationService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class ActiveReservationActivity extends AppCompatActivity {


    UUID user_id = UUID.fromString("6acadb30-a0c6-4606-959c-5f9810d80da2");
    JSONObject reservation = new JSONObject();
    String reservation_id = new String();
    String reservation_address = new String();
    String total_price_string = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_reservation);
        TextView location_address_textview = (TextView) (findViewById(R.id.address_textview));
        TextView Phone_number_textview = (TextView) (findViewById(R.id.phone_number_textview));
        TextView price_per_hour_textview = (TextView) (findViewById(R.id.price_textview));
        TextView hours_reserved = (TextView) (findViewById(R.id.hours_reserved));
        TextView total_price = (TextView) (findViewById(R.id.total_price));
        location_address_textview.setText("Jūs neturite rezervacijos");
        Button finish_btn = (Button) findViewById(R.id.Finish_reservation_btn);

        ReservationHistoryService reservationHistoryService = new ReservationHistoryService(ActiveReservationActivity.this);
        LocationService locationService = new LocationService(ActiveReservationActivity.this);
        ReservationService reservationService = new ReservationService(ActiveReservationActivity.this);
        reservationService.getJSONObejctReservation(new ReservationService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                location_address_textview.setText(message);
            }

            @Override
            public void onResponse(JSONObject reservation_object) {
                try {
                    location_address_textview.setText(String.format("Stovėjimo aikštelė adresas: \n%s", reservation_object.getString("location_address")));
                    Phone_number_textview.setText(String.format("Savininko telefono numeris:  \n%s", reservation_object.getString("contact_info")));
                    price_per_hour_textview.setText(String.format("Valandinis stovėjimo mokestis: \n%s€", reservation_object.getString("price_per_hour")));
                    hours_reserved.setText(String.format("Rezervuotas laikas: %sv", reservation_object.getString("hours_reserved")));
                    total_price.setText(String.format("Iš viso mokėti: %s€", reservation_object.getString("total_price")));

                    reservation = reservation_object;
                    reservation_id = reservation_object.getString("reservation_id");
                    reservation_address = reservation.getString("location_address");
                    total_price_string = reservation.getString("total_price");
                    Log.e("reservation_id",reservation_id);
                    Log.e("reservation_address",reservation_address);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!reservation_id.equals("")){
                            locationService.getOneLocationInfo(new LocationService.VolleyResponseListener() {
                                @Override
                                public void onError(String message) {
                                    Toast toast = Toast.makeText(ActiveReservationActivity.this, "Nepavyko atlaisvinti parkavimo vietos", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                @Override
                                public void onResponse(ArrayList<String> Location_address_array) {}

                                @Override
                                public void onResponse(JSONObject Location_object) {
                                    reservationHistoryService.Add_reservation_history(reservation);
                                    locationService.reserveLocation(Location_object, "false");
                                    reservationService.reservationDelete(new ReservationService.VolleyResponseListener() {
                                        @Override
                                        public void onError(String message) {
                                            Toast toast = Toast.makeText(ActiveReservationActivity.this, "Nepavyko nutraukti rezervacijos", Toast.LENGTH_LONG);
                                            toast.show();
                                        }

                                        @Override
                                        public void onResponse(JSONObject reservation_object) {}
                                    }, reservation_id);
                                }

                                @Override
                                public void onResponse(JSONArray LocationJSONList) {

                                }
                            }, reservation_address);
                            Toast toast = Toast.makeText(ActiveReservationActivity.this, "Sėkmingai baigta rezervacija, jum reikia sumokėti:" + total_price_string + "€", Toast.LENGTH_LONG);
                            toast.show();
                            location_address_textview.setText("Jūs neturite rezervacijos");
                            Phone_number_textview.setText("");
                            price_per_hour_textview.setText("");
                            hours_reserved.setText("");
                            total_price.setText("");
                            Intent browserIntent = new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.paysera.lt/v2/en-LT/index"));
                            startActivity(browserIntent);
                        }else {
                            Toast toast = Toast.makeText(ActiveReservationActivity.this, "Jūs neturite aktyvios rezervacijos", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    });
                }
            }, user_id);


        ImageButton btn_menu = (ImageButton)findViewById(R.id.menu_btn4);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ActiveReservationActivity.this,MainActivity.class));
                finish();
            }
        });
        ImageButton btn_map = (ImageButton)findViewById(R.id.map_btn4);
        btn_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ActiveReservationActivity.this,MapsActivityCurrentPlace.class));
                finish();
            }
        });
        ImageButton btn_search = (ImageButton)findViewById(R.id.search4);
        btn_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ActiveReservationActivity.this,SearchActivity.class));
                finish();
            }
        });
        ImageButton btn_settings = (ImageButton)findViewById(R.id.settings4);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ActiveReservationActivity.this,SettingsActivity.class));
                finish();
            }
        });
    }
}