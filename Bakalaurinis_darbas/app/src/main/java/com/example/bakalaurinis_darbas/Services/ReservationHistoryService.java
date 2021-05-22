package com.example.bakalaurinis_darbas.Services;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReservationHistoryService  {
    public static final String QUERY_FOR_RESERVATION_HISTORY = "http://78.60.209.53:8080/api/v1/reservation_history/";

        Context context;
        ArrayList<String> Reservation_history_array = new ArrayList<String>();
        public ReservationHistoryService(Context context) {
            this.context = context;
        }

public interface VolleyResponseListener{
    void onError(String message);
    void onResponse(ArrayList<String> Location_address_array);
}
    public void getAllReservations(ReservationHistoryService.VolleyResponseListener volleyResponseListener, UUID user_id){
        String url =QUERY_FOR_RESERVATION_HISTORY;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                            if(jArray.getJSONObject(i).getString("user_id").equals(user_id.toString()))
                                Reservation_history_array.add(jArray.getJSONObject(i).getString("location_address") +
                                        "\nData: " + jArray.getJSONObject(i).getString("date") +
                                        "\nMokėta suma: " + jArray.getJSONObject(i).getString("total_price") +
                                        "\nSavininko telefono numeris: " + jArray.getJSONObject(i).getString("contact_info"));
                        }
                    }
                    volleyResponseListener.onResponse(Reservation_history_array);
                } catch (JSONException e) {
                    e.printStackTrace();
                    volleyResponseListener.onError("Something wrong");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Error: Response", "" + error.getMessage());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void Add_reservation_history(JSONObject reservation){
        String url =QUERY_FOR_RESERVATION_HISTORY;
        try {
            reservation.put("reservation_id", JSONObject.NULL);
            reservation.put("hours_reserved", JSONObject.NULL);
            reservation.put("price_per_hour", JSONObject.NULL);
            reservation.put("reservation_history_id", UUID.randomUUID());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            reservation.put("date", formatter.format(date));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, reservation,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("It WORKED",response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "Error: Response", error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        MySingleton.getInstance(context).addToRequestQueue(jsonObjReq);
    }
}