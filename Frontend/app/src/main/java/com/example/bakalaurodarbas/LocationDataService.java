package com.example.bakalaurodarbas;

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

public class LocationDataService {
    public static final String QUERY_FOR_LOCATION = "http://78.60.209.53:8080/api/v1/location/";
    public static final String QUERY_FOR_LOCATION_UPDATE = "http://78.60.209.53:8080/api/v1/location/edit";

    Context context;
    ArrayList<String> Location_address_array = new ArrayList<String>();
    JSONArray LocationJSONList = new JSONArray();
    JSONObject Location_Object = new JSONObject();
    public LocationDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(ArrayList<String> Location_address_array);
        void onResponse(JSONArray LocationJSONList);
        void onResponse(JSONObject Location_object);
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
                    Log.i("SOMETYING", Location_Object.toString());
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

    public void reserveLocation(JSONObject location, String reserver_status){

        String url =QUERY_FOR_LOCATION_UPDATE;
        try {
            location.put("reservation_status", reserver_status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PUT, url, location,
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
