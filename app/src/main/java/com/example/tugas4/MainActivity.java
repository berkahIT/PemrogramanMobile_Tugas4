package com.example.tugas4;

import android.content.Intent;
import android.os.Bundle;
import android.system.Os;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static final String os_key= "os";
    ImageView btnMicrosoft, btnMacos, btnUbuntu, btnCentos,btnCromeos, btnOracle, btnFreebsd;

    View lyCurrency;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
    }

    private void inisialisasiView() {

        btnMicrosoft = findViewById(R.id.microsoft);
        btnMicrosoft.setOnClickListener(view -> tampilkanOperasiSistem("Microsoft Windows"));

        btnMacos = findViewById(R.id.macos);
        btnMacos.setOnClickListener(view -> tampilkanOperasiSistem("MacOS"));

        btnUbuntu = findViewById(R.id.linux);
        btnUbuntu.setOnClickListener(view -> tampilkanOperasiSistem("Ubuntu Linux"));

        btnCentos = findViewById(R.id.centos);
        btnCentos.setOnClickListener(view -> tampilkanOperasiSistem("CentOS"));

        btnCromeos = findViewById(R.id.cromeos);
        btnCromeos.setOnClickListener(view -> tampilkanOperasiSistem("ChromeOS"));

        btnOracle = findViewById(R.id.solaris);
        btnOracle.setOnClickListener(view -> tampilkanOperasiSistem("Oracle Solaris"));

        btnFreebsd = findViewById(R.id.freebsd);
        btnFreebsd.setOnClickListener(view -> tampilkanOperasiSistem("FreeBSD"));
    }

    private void tampilkanOperasiSistem(String namaOperasiSistem) {

        Log.d("MAIN","Buka activity ");
        Intent intent = new Intent(this, Data_activity.class);
        intent.putExtra(os_key, namaOperasiSistem);
        startActivity(intent);
    }
}