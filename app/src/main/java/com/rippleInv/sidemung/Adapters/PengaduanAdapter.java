package com.rippleInv.sidemung.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rippleInv.sidemung.Model.Pengaduan;
import com.rippleInv.sidemung.R;

import java.util.ArrayList;
import java.util.List;

public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.PengaduanViewHolder> {

    List<Pengaduan> listPengaduan;
    Pengaduan pengaduan;

    public PengaduanAdapter(List<Pengaduan> listPengaduan) {
        this.listPengaduan = listPengaduan;
    }

    @NonNull
    @Override
    public PengaduanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pengaduan_card_item,parent,false);
        return new PengaduanViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PengaduanViewHolder holder, int position) {
        Pengaduan pengaduan = listPengaduan.get(position);
        holder.judulPengaduan.setText(pengaduan.getJudul());
        holder.status.setText(pengaduan.getDescription());
    }

    @Override
    public int getItemCount() {
//        return listPengaduan.size();
        return listPengaduan != null ? listPengaduan.size() : 0;
    }

    public class PengaduanViewHolder extends RecyclerView.ViewHolder {

        public TextView judulPengaduan,status;
        public PengaduanViewHolder(@NonNull View itemView) {
            super(itemView);
            judulPengaduan = itemView.findViewById(R.id.judulPengaduan);
            status = itemView.findViewById(R.id.statusBelumDiProses);
        }
    }
}
