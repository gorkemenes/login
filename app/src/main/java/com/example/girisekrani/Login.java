package com.example.girisekrani;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText adSoyad,tekrarsifre,sifre;
    TextView adText;

    ImageView imageperson,imagemail,imagesifre;
    Button girisyap,kayitol;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        adSoyad = findViewById(R.id.AdSoyad);
        tekrarsifre = findViewById(R.id.tekrarsifre);
        sifre = findViewById(R.id.Sifre);
        adText = findViewById(R.id.AdText);
        imageperson = findViewById(R.id.imageperson);
        imagemail = findViewById(R.id.imagemail);
        imagesifre = findViewById(R.id.imagesifre);
        girisyap = findViewById(R.id.girisyap);
        kayitol = findViewById(R.id.kayıtol);
        DB = new DBHelper(this);

        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kayit = new Intent(Login.this, Kayit.class);
                startActivity(kayit);
            }
        });
        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = adSoyad.getText().toString();
                String pass = sifre.getText().toString();
                String repass = tekrarsifre.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(Login.this, "Bütün Alanlar Doldurulmalıdır.", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                if(checkuserpass==true){
                    Toast.makeText(Login.this, "GİRİŞ BAŞARILI", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this,MainActivity3.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Login.this, "GİRİŞ BAŞARISIZ!!", Toast.LENGTH_SHORT).show();
                }

                }
            }

        });


    }
}