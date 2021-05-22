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

public class LocationService {
    public static final String QUERY_FOR_LOCATION = "http://78.60.209.53:8080/api/v1/location/";

    Context context;
    ArrayList<String> Location_address_array = new ArrayList<String>();
    JSONObject Location_Object = new JSONObject();
    JSONArray LocationJSONList = new JSONArray();
    public LocationService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(ArrayList<String> Location_address_array);
        void onResponse(JSONObject Location_object);
        void onResponse(JSONArray LocationJSONList);
    }

    public void getJSONArryOfLocations(VolleyResponseListener volleyResponseListener, String status){
        String url =QUERY_FOR_LOCATION;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                            if(jArray.getJSONObject(i).getString("reservation_status").equals(status))
                                LocationJSONList.put(jArray.getJSONObject(i));
                        }
                    }
                    volleyResponseListener.onResponse(LocationJSONList);
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

    public void getAllLocationAddress(VolleyResponseListener volleyResponseListener, String status){
        String url =QUERY_FOR_LOCATION;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                            if(jArray.getJSONObject(i).getString("reservation_status").equals(status))
                                Location_address_array.add(jArray.getJSONObject(i).getString("location_address"));
                        }
                    }
                    volleyResponseListener.onResponse(Location_address_array);
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
    public void getOneLocationInfo(VolleyResponseListener volleyResponseListener, String location_address){
        String url =QUERY_FOR_LOCATION;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            if(jsonObject.getString("location_address").equals(location_address)) {
                                Location_Object = jsonObject; //this is the index of the JSONObject you want
                            }
                        }
                    }
                    volleyResponseListener.onResponse(Location_Object);
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

    public void reserveLocation(JSONObject location, String reservation_status){
        String url =QUERY_FOR_LOCATION;
        try {
            location.put("reservation_status", Boolean.parseBoolean(reservation_status));
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
