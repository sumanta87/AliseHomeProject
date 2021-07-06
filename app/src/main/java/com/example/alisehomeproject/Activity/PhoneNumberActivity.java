package com.example.alisehomeproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alisehomeproject.Pojo.OtpRequest;
import com.example.alisehomeproject.Pojo.Parser;
import com.example.alisehomeproject.utils.GlobalMethod;
import com.example.alisehomeproject.R;
import com.example.alisehomeproject.utils.ApiService;
import com.example.alisehomeproject.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneNumberActivity extends AppCompatActivity {
    static final String BASE_URL = "https://testa2.aisle.co/V1/users/";
    static Retrofit retrofit = null;
    final static String Cookie = "cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720";
   EditText editTextPhone;
   Button bu_submit;
   String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        initializeViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
       if (!GlobalMethod.checkNetworkState(PhoneNumberActivity.this))
       {
        GlobalMethod.showSettingsAlert(PhoneNumberActivity.this);
       }

    }

    private void initializeViews() {
        editTextPhone=findViewById(R.id.editTextPhone);
        bu_submit=findViewById(R.id.bu_submit);
        bu_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalMethod.checkNetworkState(PhoneNumberActivity.this))
                {
                    if (editTextPhone.getText().toString().trim().equals(""))
                    {
                        editTextPhone.setError("ENTER PHONE NUMBER");


                    }
                    else
                    {
                        editTextPhone.clearFocus();

                        if (editTextPhone.getText().toString().trim().length()!=10)
                        {
                            editTextPhone.setError("ENTER VALID PHONE NUMBER");
                        }
                        else {
                            editTextPhone.clearFocus();
                            connect();
                            GlobalMethod.DialogShow_Spin(PhoneNumberActivity.this,"Loading...");

                        }
                    }
                }
                else
                {
                    GlobalMethod.showSettingsAlert(PhoneNumberActivity.this);
                }


            }
        });


    }

    private void connect() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        OtpRequest otpRequest= new OtpRequest();
        otpRequest.setNumber("+91"+editTextPhone.getText().toString());
        number="+91"+editTextPhone.getText().toString();
        String sendNumber=new Gson().toJson(otpRequest);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<JsonObject> call = apiService.getOtpStatus(Cookie, otpRequest);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                GlobalMethod.DialogEnd();
                Log.d("response", String.valueOf(response));
                Log.d("responsebnody", String.valueOf(response.body()));
                Gson gson = new Gson();
                Type type = new TypeToken<Parser>() {
                }.getType();
                Parser parser= new Parser();
                parser = gson.fromJson(response.body(), type);
                if (parser.isStatus())
                {
                    SessionManager.getInstance(getApplicationContext());
                   Intent nextpage= new Intent(PhoneNumberActivity.this,OtpverifactionActivity.class);
                   nextpage.putExtra("phonenumber",number);
                    startActivity(nextpage);
                }
                else
                {
                 GlobalMethod.getAlertErrorCode(PhoneNumberActivity.this,"SOMTHING WENT TO WRONG","");
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("response", String.valueOf(t));
                GlobalMethod.DialogEnd();
                GlobalMethod.getAlertErrorCode(PhoneNumberActivity.this,"SOMTHING WENT TO WRONG","");
            }


        });
    }


}