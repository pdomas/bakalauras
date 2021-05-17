package com.example.bakalaurodarbas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarNumberActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_number);
        EditText car_number = (EditText)findViewById(R.id.car_number_exittext);
        Button save_car_number = (Button)findViewById(R.id.save_car_number_btn);
        save_car_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = null;
                if(!car_number.getText().toString().equals("")){
                    toast = Toast.makeText(CarNumberActivity.this, "Sėkmingai išsaugotas mašinos numeris: "+car_number.getText().toString(), Toast.LENGTH_LONG);
                }
                else{
                    toast = Toast.makeText(CarNumberActivity.this, "Nepavyko išsaugoti mašinos numerio", Toast.LENGTH_LONG);
                }
                toast.show();
                Intent intent= new Intent(CarNumberActivity.this,MainActivity.class);
                startActivity(new Intent(intent));
                finish();
            }
        });
    }
}
