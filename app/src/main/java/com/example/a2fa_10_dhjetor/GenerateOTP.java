package com.example.a2fa_10_dhjetor;

import java.util.Random;

public class GenerateOTP {

    public static String generateOTP(){
        Random random = new Random();

        String otp ="";
        for (int i=0;i<6;i++){
            otp+=random.nextInt(6);
        }

        return otp;


    }

}
