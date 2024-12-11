package com.example.a2fa_10_dhjetor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.mail.MessagingException;

public class LoginActivity extends AppCompatActivity {
    private EmailSender emailSender = new EmailSender();
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        EditText email = findViewById(R.id.loginEmail);
        EditText password =findViewById(R.id.loginPassword);
        Button login = findViewById(R.id.loginButton);
        Button signup = findViewById(R.id.loginToSignUpButton);

        DB db = new DB(this);
        login.setOnClickListener(v->{

            String emailToString = email.getText().toString();
            String passwordToString =password.getText().toString();

            if (db.loginUser(emailToString,passwordToString)){
                String otp = GenerateOTP.generateOTP();
                handleSendingEmailBackground(emailToString,otp);
                Intent intent = new Intent(this, ValidateOtpActivity.class);
                intent.putExtra("otp",otp);
                intent.putExtra("email",emailToString);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Wrong Credientials", Toast.LENGTH_SHORT).show();
            }


        });

        signup.setOnClickListener(v->{
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });



    }

    public void handleSendingEmailBackground(String destEmail,String otp){
        new Thread(()->{
            try {
                emailSender.sendEmail(destEmail,otp);
                runOnUiThread(()->{
                    Toast.makeText(this, "OTP send", Toast.LENGTH_SHORT).show();
                });
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

}
