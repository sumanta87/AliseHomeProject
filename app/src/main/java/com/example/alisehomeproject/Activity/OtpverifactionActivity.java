package com.example.alisehomeproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.alisehomeproject.Pojo.Authentication;
import com.example.alisehomeproject.Pojo.Otpsubmit;
import com.example.alisehomeproject.R;
import com.example.alisehomeproject.Viewsmode.HighBoldEditText;
import com.example.alisehomeproject.Viewsmode.HighSemiBoldTextView;
import com.example.alisehomeproject.utils.ApiService;
import com.example.alisehomeproject.utils.GlobalMethod;
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

public class OtpverifactionActivity extends AppCompatActivity {

    HighSemiBoldTextView tv_number,tv_time;
    HighBoldEditText et_otp;
    ImageView iv_editnumber;
    Button bu_submit;
    static final String BASE_URL = "https://testa2.aisle.co/V1/users/";
    static Retrofit retrofit = null;
    final static String Cookie = "cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720";
    String number;
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverifaction);
        initializeViews();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            tv_number.setText(b.getString("phonenumber"));
            number=b.getString("phonenumber");
        }
        setTimer();
    }
    public void initializeViews()
    {
        tv_number=findViewById(R.id.tv_number);
        tv_time=findViewById(R.id.tv_time);
        et_otp=findViewById(R.id.et_otp);
        iv_editnumber=findViewById(R.id.iv_editnumber);
        bu_submit=findViewById(R.id.bu_submit);

        bu_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalMethod.checkNetworkState(OtpverifactionActivity.this))
                {
                    if (et_otp.getText().toString().trim().equals(""))
                    {
                        et_otp.setError("ENTER OTP");


                    }
                    else
                    {
                        et_otp.clearFocus();

                        if (et_otp.getText().toString().trim().length()!=4)
                        {
                            et_otp.setError("ENTER VALID 4 DIGIT OTP");
                        }
                        else {
                            et_otp.clearFocus();
                            connect();
                            GlobalMethod.DialogShow_Spin(OtpverifactionActivity.this,"Loading...");

                        }
                    }
                }
                else
                {
                    GlobalMethod.showSettingsAlert(OtpverifactionActivity.this);
                }
            }
        });

        iv_editnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        Otpsubmit otpsubmit=new Otpsubmit();
        otpsubmit.setNumber(number);
        otpsubmit.setOtp(et_otp.getText().toString().trim());

        ApiService apiService = retrofit.create(ApiService.class);
        Call<JsonObject> call = apiService.getSubmitOtpStatus(Cookie, otpsubmit);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                GlobalMethod.DialogEnd();
                et_otp.setText("");
                Log.d("response", String.valueOf(response));
                Log.d("responsebnody", String.valueOf(response.body()));
                Gson gson = new Gson();
                Authentication authentication= new Authentication();
                Type type = new TypeToken<Authentication>() {
                }.getType();

                authentication = gson.fromJson(response.body(), type);
                if (authentication.getToken()!=null)
                {
                    SessionManager sessionManager= new SessionManager(OtpverifactionActivity.this);
                    sessionManager.setSessionID(authentication.getToken());
                    sessionManager.commit();
                    Intent nextpage= new Intent(OtpverifactionActivity.this,HomePageActivity.class);
                    overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
                    startActivity(nextpage);
                    finish();
                }
                else
                {
                    GlobalMethod.getAlertErrorCode(OtpverifactionActivity.this,"SOMTHING WENT TO WRONG","");
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("response", String.valueOf(t));
                GlobalMethod.DialogEnd();
                GlobalMethod.getAlertErrorCode(OtpverifactionActivity.this,"SOMTHING WENT TO WRONG","");
            }


        });
    }

    private void setTimer() {
        if (cdt != null) {
            cdt.cancel();
        }
        cdt = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tv_time.setText( "00."+ millisUntilFinished / 1000);
            }

            public void onFinish() {

            }
        }.start();
    }
}