package com.rippleInv.sidemung.Fragment_recap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rippleInv.sidemung.Adapters.PengaduanAdapter;
import com.rippleInv.sidemung.Model.ApiClient;
import com.rippleInv.sidemung.Model.Pengaduan;
import com.rippleInv.sidemung.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import com.rippleInv.sidemung.adapter.AdapterBelumDiProses;
//import com.rippleInv.sidemung.model_rekap.DataRekapBelumDiProses;

public class SudahDiProsesFragment extends Fragment {
    View view;
    private  RecyclerView recyclerView;
    List<Pengaduan> pengaduan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sudah_di_proses, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewProses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        System.out.println("blbla");
        getData();
//        System.out.println(pengaduan);
//        printData();


//        DataRekapBelumDiProses[] dataRekapBelumDiProses = new DataRekapBelumDiProses[]{
//                new DataRekapBelumDiProses("Rumah Kebakaran", "belum di proses", "12-Maret-2023"),
//                new DataRekapBelumDiProses("Kerbau Masuk Parit", "belum di proses", "15-Maret-2023"),
//                new DataRekapBelumDiProses("Kuda Terlepas", "belum di proses", "25-Maret-2023"),
//                new DataRekapBelumDiProses("Ular dalam Rumah", "belum di proses", "30-Maret-2023"),
//        };
//
//        AdapterBelumDiProses myMovieAdapter = new AdapterBelumDiProses(dataRekapBelumDiProses,this);
//        recyclerView.setAdapter(AdapterBelumDiProses);

        return view;
    }

    private  void getData(){
        Call<List<Pengaduan>> listPengaduan = ApiClient.getUserService(getContext()).daftarPengaduan("2");
        listPengaduan.enqueue(new Callback<List<Pengaduan>>() {
            @Override
            public void onResponse(Call<List<Pengaduan>> call, Response<List<Pengaduan>> response) {
                if (response.isSuccessful() && response.body() !=null){
                    System.out.println("blbla");
                    System.out.println(response.body());
                    pengaduan = response.body();
                    PengaduanAdapter pengaduanAdapter = new PengaduanAdapter(response.body());
                    recyclerView.setAdapter(pengaduanAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pengaduan>> call, Throwable t) {

            }
        });
    }
    private void printData(){
        for (int i = pengaduan.size(); i <= pengaduan.size(); i++){
            System.out.println(pengaduan.get(i));
        }
    }


}