package com.irpan.budiana.volumebalok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  static final String STATE_HASIL = "state_hasil";

    private EditText edtPanjang;
    private EditText edtLebar;
    private EditText edtTinggi;
    private Button btnHitung;
    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPanjang = findViewById(R.id.edt_panjang);
        edtLebar = findViewById(R.id.edt_lebar);
        edtTinggi = findViewById(R.id.edt_tinggi);
        btnHitung = findViewById(R.id.btn_hitung);
        tvHasil = findViewById(R.id.tv_hasil);

        btnHitung.setOnClickListener(this);

        if (savedInstanceState != null) {
            String hasil = savedInstanceState.getString(STATE_HASIL);
            tvHasil.setText(hasil);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_HASIL, tvHasil.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hitung) {
            String inputPanjang = edtPanjang.getText().toString().trim();
            String inputLebar = edtLebar.getText().toString().trim();
            String inputTinggi = edtTinggi.getText().toString().trim();

            boolean isKolomKosong = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputPanjang)) {
                isKolomKosong = true;
                edtPanjang.setError("Kolom ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputLebar)) {
                isKolomKosong = true;
                edtLebar.setError("Kolom ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputTinggi)) {
                isKolomKosong = true;
                edtTinggi.setError("Kolom ini tidak boleh kosong");
            }

            Double panjang = toDouble(inputPanjang);
            Double lebar = toDouble(inputLebar);
            Double tinggi = toDouble(inputTinggi);

            if (panjang == null) {
                isInvalidDouble = true;
                edtPanjang.setError("Kolom ini harus berupa nomor yang valid");
            }
            if (lebar == null) {
                isInvalidDouble = true;
                edtLebar.setError("Kolom ini harus berupa nomor yang valid");
            }
            if (tinggi == null) {
                isInvalidDouble = true;
                edtTinggi.setError("Kolom ini harus berupa nomor yang valid");
            }

            if (!isKolomKosong && !isInvalidDouble) {
                double volume = panjang * lebar * tinggi;
                tvHasil.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}