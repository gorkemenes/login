package com.example.girisekrani;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Kayit extends AppCompatActivity {

    TextView textView;
    EditText kayitAdSoyad, kayitSifre, kayitTekrar;
    Button kayitGeri,kayitKayit;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        kayitAdSoyad = findViewById(R.id.KayıtAdSoyad);
        kayitSifre = findViewById(R.id.KayıtSifre);
        kayitTekrar = findViewById(R.id.kayitTekrar);
        kayitKayit = findViewById(R.id.KayıtKayıt);
        kayitGeri = findViewById(R.id.KayıtGeri);
        DB = new DBHelper(this);

        kayitKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = kayitAdSoyad.getText().toString();
                String pass = kayitSifre.getText().toString();
                String repass = kayitSifre.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)){
                    Toast.makeText(Kayit.this, "Tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert == true){
                                Toast.makeText(Kayit.this, "KAYIT BAŞARILI", Toast.LENGTH_SHORT).show();
                                kayitAdSoyad.setText("");
                                kayitSifre.setText("");
                                kayitTekrar.setText("");
                            }else{
                                Toast.makeText(Kayit.this, "KAYIT BAŞARISIZ", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Kayit.this, "KULLANICI MEVCUT", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Kayit.this, "ŞİFRELER EŞLEŞMİYOR.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        kayitGeri.setOnClickListener(v -> {
            Intent i = new Intent(Kayit.this, Login.class);
            startActivity(i);
        });

    }
}