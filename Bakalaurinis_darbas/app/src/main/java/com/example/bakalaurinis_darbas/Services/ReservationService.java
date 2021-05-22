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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReservationService {
    public static final String QUERY_FOR_RESERVATION = "http://78.60.209.53:8080/api/v1/reservation/";
    String delete_request;
    Context context;

    JSONObject reservation_jsonObject = new JSONObject();
    public ReservationService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(JSONObject reservation_object);
    }

    public void reservationDelete(ReservationService.VolleyResponseListener volleyResponseListener, String reservation_id){
        String url =QUERY_FOR_RESERVATION;
//        try {
//            String delete_request = reservation.getString("reservation_id");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url+reservation_id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    volleyResponseListener.onResponse(reservation_jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    volleyResponseListener.onError("Nepavyko nutraukti rezervacijos");
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

    public void getJSONObejctReservation(ReservationService.VolleyResponseListener volleyResponseListener, UUID user_id){
        String url =QUERY_FOR_RESERVATION;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            if(jsonObject.getString("user_id").equals(user_id.toString())) {
                                reservation_jsonObject = jsonObject;
                                break;
                            }
                        }
                    }
                    volleyResponseListener.onResponse(reservation_jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    volleyResponseListener.onError("Jūs neturite aktyvios rezervacijos");
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

    public void Reservation(JSONObject location, UUID user_id, int time){
        String url =QUERY_FOR_RESERVATION;
        try {
            location.put("location_id", JSONObject.NULL);
            location.put("latitude", JSONObject.NULL);
            location.put("longitude", JSONObject.NULL);
            location.put("reservation_status", JSONObject.NULL);

            location.put("reservation_id", UUID.randomUUID());
            location.put("user_id", user_id);
            location.put("hours_reserved", time);

            location.put("total_price",Double.parseDouble(location.getString("price_per_hour"))*time);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, location,
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
