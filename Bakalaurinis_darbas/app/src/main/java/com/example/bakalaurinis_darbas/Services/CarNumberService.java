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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CarNumberService {
    public static final String QUERY_FOR_CARNUMBER = "http://78.60.209.53:8080/api/v1/car_number/";

    Context context;
    JSONObject Car_Number_jsonObject = new JSONObject();
    public CarNumberService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(JSONObject Car_number_object);
    }
    public void getJSONObejctCarNumber(VolleyResponseListener volleyResponseListener, UUID user_id){
        String url =QUERY_FOR_CARNUMBER;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jArray = response;
                    if (jArray != null) {
                        for (int i=0;i<jArray.length();i++){
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            if(jsonObject.getString("user_id").equals(user_id.toString())) {
                                Car_Number_jsonObject = jsonObject;
                                break;
                            }
                        }
                    }
                    volleyResponseListener.onResponse(Car_Number_jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    volleyResponseListener.onError("No car number found");
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

    public void changeCarNumber(JSONObject car_number_object){
        String url =QUERY_FOR_CARNUMBER;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, car_number_object,
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
