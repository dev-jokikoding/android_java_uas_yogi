package com.example.uas_yogiantosyahputra_2110031806052;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas_yogiantosyahputra_2110031806052.api.ApiConnection;
import com.example.uas_yogiantosyahputra_2110031806052.api.ApiInterface;
import com.example.uas_yogiantosyahputra_2110031806052.model.Barang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryActivity extends AppCompatActivity {

    EditText etNama, etAlamat, etNoTelp, etKodeBarang, etJumlahPenjualan, etHargaSatuan, etDiskon, etTotalHarga;
    Button btnCancel, btnEntry;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        etNama = findViewById(R.id.et_nama_barang);
        etAlamat = findViewById(R.id.et_alamat);
        etNoTelp = findViewById(R.id.et_no_telp);
        etKodeBarang = findViewById(R.id.et_kode_barang);
        etJumlahPenjualan = findViewById(R.id.et_jumlah_penjualan);
        etHargaSatuan = findViewById(R.id.et_harga_satuan);
        etDiskon = findViewById(R.id.et_diskon);
        etTotalHarga = findViewById(R.id.et_total_harga);

        btnCancel = findViewById(R.id.btn_cancel);
        btnEntry = findViewById(R.id.btn_entry);

        btnCancel.setOnClickListener(v -> super.onBackPressed());

        btnEntry.setOnClickListener(v -> {
            addBarang(
                    etNama.getText().toString(),
                    etAlamat.getText().toString(),
                    etNoTelp.getText().toString(),
                    etKodeBarang.getText().toString(),
                    etJumlahPenjualan.getText().toString(),
                    etHargaSatuan.getText().toString(),
                    etDiskon.getText().toString(),
                    etTotalHarga.getText().toString()
            );
        });

        etDiskon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    if (!etJumlahPenjualan.getText().toString().isEmpty() || !etHargaSatuan.getText().toString().isEmpty()) {
                        int jumlahPenjualan = Integer.parseInt(etJumlahPenjualan.getText().toString());
                        int hargaSatuan = Integer.parseInt(etHargaSatuan.getText().toString());
                        int totalPenjualan = jumlahPenjualan * hargaSatuan;
                        double diskon = Double.parseDouble(etDiskon.getText().toString())/100;
                        int totalDiskon = (int) Math.round((jumlahPenjualan * hargaSatuan) * diskon);
                        int hargaAkhir = totalPenjualan - totalDiskon;
                        etTotalHarga.setText(String.valueOf(hargaAkhir));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // TODO
            }
        });
    }

    private void addBarang(String nama, String alamat, String noTelp, String kodeBarang, String jumlahPenjualan, String hargaSatuan, String diskon, String totalHarga) {
        apiInterface = ApiConnection.Connection().create(ApiInterface.class);
        Call<Barang> simpanBarang = apiInterface.simpanBarang(
                nama,
                alamat,
                noTelp,
                kodeBarang,
                jumlahPenjualan,
                hargaSatuan,
                diskon,
                totalHarga
        );

        simpanBarang.enqueue(new Callback<Barang>() {
            @Override
            public void onResponse(Call<Barang> call, Response<Barang> response) {
                Toast.makeText(EntryActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Barang> call, Throwable t) {
                // TODO
            }
        });

        Intent intent = new Intent(EntryActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}