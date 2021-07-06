package com.example.alisehomeproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.alisehomeproject.Pojo.Authentication;
import com.example.alisehomeproject.Pojo.Otpsubmit;
import com.example.alisehomeproject.ProfilePojo.Userdata;
import com.example.alisehomeproject.R;
import com.example.alisehomeproject.utils.ApiService;
import com.example.alisehomeproject.utils.GlobalMethod;
import com.example.alisehomeproject.utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageActivity extends AppCompatActivity {
    static final String BASE_URL = "https://testa2.aisle.co/V1/users/";
    static Retrofit retrofit = null;
    final static String Cookie = "cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        BottomNavigationView navView = findViewById(R.id.bottomNav_view);
        if (GlobalMethod.checkNetworkState(HomePageActivity.this))
        {
            connect();
        }
        else
        {
            GlobalMethod.showSettingsAlert(HomePageActivity.this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!GlobalMethod.checkNetworkState(HomePageActivity.this))
        {
            GlobalMethod.showSettingsAlert(HomePageActivity.this);
        }
    }

    private void connect() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        SessionManager sessionManager= new SessionManager(HomePageActivity.this);
        ApiService apiService = retrofit.create(ApiService.class);
        Call<JsonObject> call = apiService.getSubmitOtpStatus(Cookie,sessionManager.getSessionID() );
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                GlobalMethod.DialogEnd();
                Log.d("response", String.valueOf(response));
                Log.d("responsebnody", String.valueOf(response.body()));
                Gson gson = new Gson();
                Userdata userdata= new Userdata();
                Type type = new TypeToken<Userdata>() {
                }.getType();

                userdata = gson.fromJson(response.body(), type);
                Log.d("value", String.valueOf(userdata.getInvites().getProfiles()));
                Log.d("info",userdata.getInvites().getProfiles().get(0).getGeneralInformation().getDateOfBirth());


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("response", String.valueOf(t));
                GlobalMethod.DialogEnd();
                GlobalMethod.getAlertErrorCode(HomePageActivity.this,"SOMTHING WENT TO WRONG","");
            }


        });
    }
}