package com.rippleInv.sidemung.views.profile;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rippleInv.sidemung.Model.ApiClient;
import com.rippleInv.sidemung.Model.MyPreferences;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.views.auth.Login;
import com.rippleInv.sidemung.views.auth.Register;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    View view;
    TextView toEdit_profile,email,nik,name;
    ImageView logout;
    LinearLayout btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyPreferences preferences = new MyPreferences(getContext());
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        toEdit_profile = view.findViewById(R.id.toEditProfile);
        logout = view.findViewById(R.id.logout);
        email = view.findViewById(R.id.email);
        nik = view.findViewById(R.id.nik);
        name = view.findViewById(R.id.name);
        btn_logout = view.findViewById(R.id.ll_logout);

        email.setText(preferences.getString("email",""));
        nik.setText(preferences.getString("nik",""));
        name.setText(preferences.getString("name",""));

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        toEdit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditprofileActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void showDialog(){
        ConstraintLayout constraintLayout = view.findViewById(R.id.dialogConfirmLogout);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.confirm_logout_dialog, constraintLayout);
        Button btn_batalKeluar = view1.findViewById(R.id.batal_keluar);
        Button btn_logout = view1.findViewById(R.id.keluar_logout);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view1);
        final AlertDialog alertDialog = builder.create();

        Call<ResponseBody> logout = ApiClient.getUserService(getActivity()).logout();

        btn_logout.findViewById(R.id.keluar_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            System.out.println(response.raw());
                            alertDialog.dismiss();
                            Toast.makeText(getActivity(), "Logout Berhasil", Toast.LENGTH_LONG).show();
                            MyPreferences preferences = new MyPreferences(getActivity());
                            preferences.clear();
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });
        btn_batalKeluar.findViewById(R.id.batal_keluar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

}