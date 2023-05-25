package com.rippleInv.sidemung.Adapters;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rippleInv.sidemung.Model.ApiClient;
import com.rippleInv.sidemung.Model.Pengaduan;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.views.Pengaduan.PengaduanActivity;
import com.rippleInv.sidemung.views.main.MainActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusBelumDiProsesAdapter extends RecyclerView.Adapter<StatusBelumDiProsesAdapter.StatusBelumDiProsesAdapterViewHolder>{
    List<Pengaduan> listPengaduan;
    Pengaduan pengaduan;
    LinearLayout linearLayout;
    View view;

    public StatusBelumDiProsesAdapter(List<Pengaduan> listPengaduan) {
        this.listPengaduan = listPengaduan;
    }

    @NonNull
    @Override
    public StatusBelumDiProsesAdapter.StatusBelumDiProsesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pengaduan_belum_prosess_card_item,parent,false);
        return new StatusBelumDiProsesAdapter.StatusBelumDiProsesAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StatusBelumDiProsesAdapter.StatusBelumDiProsesAdapterViewHolder holder, int position) {
        Pengaduan pengaduan = listPengaduan.get(position);
        holder.judulPengaduan.setText(pengaduan.getJudul());
        holder.status.setText(pengaduan.getDescription());
        Picasso.get().load("http://192.168.1.6:8000/data_file/"+pengaduan.getImage()).into(holder.gambar);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> deletePengaduan = ApiClient.getUserService(view.getContext()).deletePengaduan(pengaduan.getId());
                deletePengaduan.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(response.raw());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
//        return listPengaduan.size();
        return listPengaduan != null ? listPengaduan.size() : 0;
    }

    public class StatusBelumDiProsesAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView judulPengaduan,status;
        public LinearLayout linearLayout;
        public ImageView gambar;
        public StatusBelumDiProsesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            judulPengaduan = itemView.findViewById(R.id.judulPengaduan);
            status = itemView.findViewById(R.id.statusBelumDiProses);
            linearLayout = itemView.findViewById(R.id.deleteBelumDiProses);
            gambar = itemView.findViewById(R.id.gambarBelumDiProses);

        }
    }

}
