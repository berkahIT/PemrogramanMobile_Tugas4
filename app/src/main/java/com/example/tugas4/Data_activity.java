package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Data_activity extends AppCompatActivity {

    TextView txtDeveloper,txtVersion, txtSourceModel, txtWebsite, txtDeskripsi;
//    ProgressBar loadingIndicator;
    ImageView imgLogo;

    JSONObject dataTerbaru;
    String namaOperasiSistem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        inisialisasiView();
        ambilDataOperasiSistem();
    }

    private void inisialisasiView() {

        txtDeveloper = findViewById(R.id.Developer);
        txtVersion = findViewById(R.id.latest_version);
        txtSourceModel = findViewById(R.id.source_model);
        txtWebsite = findViewById(R.id.website);
        txtDeskripsi = findViewById(R.id.deskripsi);
        imgLogo =  (ImageView)findViewById(R.id.logo_url);
    }

        private void ambilDataOperasiSistem() {
//        loadingIndicator.setVisibility(View.VISIBLE);
        String baseURL = "https://ewinsutriandi.github.io/mockapi/operating_system.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, baseURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("MAIN",response.toString());
                        dataTerbaru = response;
                        Intent intent = getIntent();
                        namaOperasiSistem = intent.getStringExtra(MainActivity.os_key);
                        tampilkanOperasiSistem(namaOperasiSistem);
//                        loadingIndicator.setVisibility(View.INVISIBLE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        loadingIndicator.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Gagal mengambil data",Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void tampilkanOperasiSistem(String namaOperasiSistem) {

        this.namaOperasiSistem = namaOperasiSistem;
        txtDeveloper.setText(namaOperasiSistem);
        try { // try catch untuk antisipasi error saat parsing JSON
            // siapkan kemudian tampilkan nilai tukar
            JSONObject data = dataTerbaru.getJSONObject(namaOperasiSistem);
            txtDeveloper.setText(data.getString("developer"));
            txtVersion.setText(data.getString("latest_version"));
            txtSourceModel.setText(data.getString("source_model"));
            txtWebsite.setText(data.getString("website"));
            txtDeskripsi.setText(data.getString("description"));
            Glide.with(Data_activity.this)
                    // LOAD URL DARI INTERNET
                    .load(data.getString("logo_url"))
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .placeholder(R.drawable.ic_launcher_background)
                    //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                    .error(R.drawable.ic_launcher_background)
                    .into(imgLogo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}