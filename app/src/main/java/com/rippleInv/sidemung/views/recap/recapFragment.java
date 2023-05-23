package com.rippleInv.sidemung.views.recap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rippleInv.sidemung.Fragment_recap.BelumDiProsesFragment;
import com.rippleInv.sidemung.Fragment_recap.SelesaiFragment;
import com.rippleInv.sidemung.Fragment_recap.SudahDiProsesFragment;
import com.rippleInv.sidemung.R;


public class recapFragment extends Fragment {
    View view;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recap, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.view_page);

        tabLayout.addTab(tabLayout.newTab().setText("Belum di proses"));
        tabLayout.addTab(tabLayout.newTab().setText("Sedang di proses"));
        tabLayout.addTab(tabLayout.newTab().setText("Selesai"));

        Toolbar toolbar = view.findViewById(R.id.action_barRekap);
        toolbar.setTitle("Pengaduan Anda");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        BelumDiProsesFragment belumDiProsesFragment = new BelumDiProsesFragment();
                        return belumDiProsesFragment;
                    case 1:
                        SudahDiProsesFragment sudahDiProsesFragment = new SudahDiProsesFragment();
                        return sudahDiProsesFragment;
                    case 2:
                        SelesaiFragment selesaiFragment = new SelesaiFragment();
                        return selesaiFragment;
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}