//package com.rippleInv.sidemung.Fragment_recap;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.rippleInv.sidemung.R;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link SelesaiFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class SelesaiFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public SelesaiFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SelesaiFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SelesaiFragment newInstance(String param1, String param2) {
//        SelesaiFragment fragment = new SelesaiFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_selesai, container, false);
//    }
//}

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

public class SelesaiFragment extends Fragment {
    View view;
    private  RecyclerView recyclerView;
    List<Pengaduan> pengaduan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_selesai, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSelesai);
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
        Call<List<Pengaduan>> listPengaduan = ApiClient.getUserService(getContext()).daftarPengaduan("3");
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