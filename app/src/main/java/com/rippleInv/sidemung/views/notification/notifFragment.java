package com.rippleInv.sidemung.views.notification;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rippleInv.sidemung.R;


public class notifFragment extends Fragment {

    View view;
    Button btnDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notif, container, false);

        btnDialog = view.findViewById(R.id.tampilDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void showDialog(){
        ConstraintLayout constraintLayout = view.findViewById(R.id.successConstrantLayout);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.success_dialog, constraintLayout);
        Button successDone = view1.findViewById(R.id.successDone);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view1);
        final AlertDialog alertDialog = builder.create();

        successDone.findViewById(R.id.successDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}