package com.example.a2fa_10_dhjetor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ValidateOtpActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validate_activity);

        EditText otp = findViewById(R.id.otpText);
        Button validate = findViewById(R.id.validateButton);
        String getOriginalOtp = getIntent().getStringExtra("otp");
        Button resend = findViewById(R.id.resendButton);

        validate.setOnClickListener(v->{
        if (otp.getText().toString()
                .equals(getOriginalOtp)){
            Toast.makeText(this, "Correct OTP", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SuccessActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
        }
        });

        resend.setOnClickListener(v->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

    }


}
