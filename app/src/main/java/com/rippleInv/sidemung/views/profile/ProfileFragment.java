package com.rippleInv.sidemung.views.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    TextView logout;

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

        email.setText(preferences.getString("email",""));
        nik.setText(preferences.getString("nik",""));
        name.setText(preferences.getString("name",""));
        toEdit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditprofileActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> logout = ApiClient.getUserService(getActivity()).logout();
                logout.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            MyPreferences preferences = new MyPreferences(getActivity());
                            preferences.clear();
                            System.out.println(response.raw());
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
        return view;
    }

}