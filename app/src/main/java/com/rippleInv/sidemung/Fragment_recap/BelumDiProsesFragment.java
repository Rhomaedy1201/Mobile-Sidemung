package com.rippleInv.sidemung.Fragment_recap;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rippleInv.sidemung.Adapters.PengaduanAdapter;
import com.rippleInv.sidemung.Adapters.StatusBelumDiProsesAdapter;
import com.rippleInv.sidemung.Model.ApiClient;
import com.rippleInv.sidemung.Model.Pengaduan;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.views.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import com.rippleInv.sidemung.adapter.AdapterBelumDiProses;
//import com.rippleInv.sidemung.model_rekap.DataRekapBelumDiProses;

public class BelumDiProsesFragment extends Fragment {
    View view;
    private  RecyclerView recyclerView;
    List<Pengaduan> pengaduan;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_belum_di_proses, container, false);

        recyclerView = view.findViewById(R.id.ricyclerViewBelumProses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = view.findViewById(R.id.progressBar_belumDiProses);
        progressBar.setVisibility(View.VISIBLE);


        getData();

        return view;
    }

    private void getData(){
        Call<List<Pengaduan>> listPengaduan = ApiClient.getUserService(getContext()).daftarPengaduan("1");
        listPengaduan.enqueue(new Callback<List<Pengaduan>>() {
            @Override
            public void onResponse(Call<List<Pengaduan>> call, Response<List<Pengaduan>> response) {
                System.out.println("belum di proses"+response.body());
                if (response.isSuccessful() && response.body() !=null){
                    System.out.println("blbla");
                    System.out.println(response.body());
                    pengaduan = response.body();
                    StatusBelumDiProsesAdapter statusBelumDiProsesAdapter = new StatusBelumDiProsesAdapter(response.body());
                    recyclerView.setAdapter(statusBelumDiProsesAdapter);
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);
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