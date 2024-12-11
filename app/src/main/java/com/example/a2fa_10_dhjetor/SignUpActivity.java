package com.example.a2fa_10_dhjetor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);


        EditText name = findViewById(R.id.signUpName);

        EditText email = findViewById(R.id.signUpEmail);
        EditText password = findViewById(R.id.signUpPassword);

        Button signUp = findViewById(R.id.signUpButton);
        Button logIn = findViewById(R.id.signUpToLoginButton);

        DB db = new DB(this);
        signUp.setOnClickListener(v->{

            if (db.insertUser(name.getText().toString(),email.getText().toString(),password.getText().toString())){
                Toast.makeText(this, "Signed UP", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();

            }
        });

        logIn.setOnClickListener(v->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });



    }


}
