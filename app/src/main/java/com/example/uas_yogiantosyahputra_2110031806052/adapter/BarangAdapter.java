package com.example.uas_yogiantosyahputra_2110031806052.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_yogiantosyahputra_2110031806052.MainActivity;
import com.example.uas_yogiantosyahputra_2110031806052.R;
import com.example.uas_yogiantosyahputra_2110031806052.api.ApiConnection;
import com.example.uas_yogiantosyahputra_2110031806052.api.ApiInterface;
import com.example.uas_yogiantosyahputra_2110031806052.model.Barang;
import com.example.uas_yogiantosyahputra_2110031806052.util.Cons;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.myViewHolder> {
    ApiInterface apiInterface;
    String id;
    EditText etNama, etAlamat, etNoPenjual, etKodeBarang, etJumlahPenjualan, etHargaSatuan, etDiskon, etTotalHarga;
    MaterialButton btnUpdate, btnCancel;

    ArrayList<Barang> barangArrayList;
    OnBarangListener onBarangListener;

    public BarangAdapter(ArrayList<Barang> barangArrayList, OnBarangListener onBarangListener) {
        this.barangArrayList = barangArrayList;
        this.onBarangListener = onBarangListener;
    }

    @NonNull
    @Override
    public BarangAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new BarangAdapter.myViewHolder(view, onBarangListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapter.myViewHolder holder, int position) {
        holder.cId.setText(String.valueOf(barangArrayList.get(position).getId()));
        holder.cNama.setText(barangArrayList.get(position).getNama());
        holder.cAlamat.setText(barangArrayList.get(position).getAlamat());
        holder.cNoPenjual.setText(barangArrayList.get(position).getNo_penjual());
        holder.cKodeBarang.setText(barangArrayList.get(position).getKode_barang());
        holder.cJumlahPenjualan.setText(String.valueOf(barangArrayList.get(position).getJumlah_penjualan()));
        holder.cHargaSatuan.setText(String.format("Rp. %s", barangArrayList.get(position).getHarga_satuan()));
        holder.cDiskon.setText(String.format("%s%%", barangArrayList.get(position).getDiskon()));
        holder.cTotalHarga.setText(String.format("Rp. %s", barangArrayList.get(position).getTotal_harga()));

        holder.cBtnMore.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), holder.cBtnMore);
            popupMenu.getMenuInflater().inflate(R.menu.more_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.menu_update) {
                    final Dialog dialog = new Dialog(v.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_update);
                    dialog.getWindow().setLayout(Cons.widthScreen, Cons.heightScreen);

                    // Declaration
                    id = String.valueOf(barangArrayList.get(position).getId());
                    etNama = dialog.findViewById(R.id.et_nama_barang);
                    etAlamat = dialog.findViewById(R.id.et_alamat);
                    etNoPenjual = dialog.findViewById(R.id.et_no_telp);
                    etKodeBarang = dialog.findViewById(R.id.et_kode_barang);
                    etJumlahPenjualan = dialog.findViewById(R.id.et_jumlah_penjualan);
                    etHargaSatuan = dialog.findViewById(R.id.et_harga_satuan);
                    etDiskon = dialog.findViewById(R.id.et_diskon);
                    etTotalHarga = dialog.findViewById(R.id.et_total_harga);
                    btnUpdate = dialog.findViewById(R.id.btn_update);
                    btnCancel = dialog.findViewById(R.id.btn_cancel);

                    // Set Text
                    etNama.setText(barangArrayList.get(position).getNama());
                    etAlamat.setText(barangArrayList.get(position).getAlamat());
                    etNoPenjual.setText(barangArrayList.get(position).getNo_penjual());
                    etKodeBarang.setText(barangArrayList.get(position).getKode_barang());
                    etJumlahPenjualan.setText(String.valueOf(barangArrayList.get(position).getJumlah_penjualan()));
                    etHargaSatuan.setText(String.valueOf(barangArrayList.get(position).getHarga_satuan()));
                    etDiskon.setText(String.valueOf(barangArrayList.get(position).getDiskon()));
                    etTotalHarga.setText(String.valueOf(barangArrayList.get(position).getTotal_harga()));

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

                    btnUpdate.setOnClickListener(unused -> {
                        updateBarang(
                                v.getContext(),
                                id,
                                etNama.getText().toString(),
                                etAlamat.getText().toString(),
                                etNoPenjual.getText().toString(),
                                etKodeBarang.getText().toString(),
                                etJumlahPenjualan.getText().toString(),
                                etHargaSatuan.getText().toString(),
                                etDiskon.getText().toString(),
                                etTotalHarga.getText().toString()
                        );
                    });

                    btnCancel.setOnClickListener(unused -> {
                        dialog.dismiss();
                    });

                    dialog.show();
                } else if (menuItem.getItemId() == R.id.menu_delete) {
                    id = String.valueOf(barangArrayList.get(position).getId());

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage(R.string.dialog_delete)
                            .setPositiveButton(R.string.yes, (dialogInterface, i) -> deleteBarang(
                                    v.getContext(),
                                    id
                            ))
                            .setNegativeButton(R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());
                    builder.show();
                } else {
                    Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

                return true;
            });

            popupMenu.show();
        });

    }

    private void deleteBarang(Context context, String id) {
        apiInterface = ApiConnection.Connection().create(ApiInterface.class);
        Call<Barang> deleteBarang = apiInterface.deleteBarang(id);

        deleteBarang.enqueue(new Callback<Barang>() {
            @Override
            public void onResponse(Call<Barang> call, Response<Barang> response) {
                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Barang> call, Throwable t) {
                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }

    private void updateBarang(Context context,String id, String nama, String alamat, String noTelp, String kodeBarang, String jumlahPenjualan, String hargaSatuan, String diskon, String totalHarga) {
        apiInterface = ApiConnection.Connection().create(ApiInterface.class);
        Call<Barang> simpanBarang = apiInterface.editBarang(
                id,
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
                Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Barang> call, Throwable t) {
                Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return barangArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cId, cNama, cAlamat, cNoPenjual, cKodeBarang, cJumlahPenjualan, cHargaSatuan, cDiskon, cTotalHarga;
        MaterialButton cBtnMore;
        CardView cItemBarang;

        OnBarangListener onBarangListener;

        public myViewHolder(@NonNull View itemView, OnBarangListener onBarangListener) {
            super(itemView);
            cId = itemView.findViewById(R.id.tv_id);
            cNama = itemView.findViewById(R.id.tv_nama_barang);
            cAlamat = itemView.findViewById(R.id.tv_alamat);
            cNoPenjual = itemView.findViewById(R.id.tv_no_telp);
            cKodeBarang = itemView.findViewById(R.id.tv_kode_barang);
            cJumlahPenjualan = itemView.findViewById(R.id.tv_jumlah_barang);
            cHargaSatuan = itemView.findViewById(R.id.tv_harga_satuan);
            cDiskon = itemView.findViewById(R.id.tv_diskon);
            cTotalHarga = itemView.findViewById(R.id.tv_total_harga);
            cBtnMore = itemView.findViewById(R.id.btn_more);
            cItemBarang = itemView.findViewById(R.id.cv_item_barang);

            this.onBarangListener = onBarangListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBarangListener.onBarangListener(getAdapterPosition());
        }
    }

    public interface OnBarangListener {
        void onBarangListener(int position);
    }
}
