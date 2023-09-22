package com.example.asm_mob2041_ph41626.Model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asm_mob2041_ph41626.Database.DbHelper;
import com.example.asm_mob2041_ph41626.Fragment.DoanhThuFragment;
import com.example.asm_mob2041_ph41626.Fragment.LoaiSachFragment;
import com.example.asm_mob2041_ph41626.Fragment.PhieuMuonFragment;
import com.example.asm_mob2041_ph41626.Fragment.SachFragment;
import com.example.asm_mob2041_ph41626.Fragment.ThanhVienFragment;
import com.example.asm_mob2041_ph41626.Fragment.Top10Fragment;
import com.example.asm_mob2041_ph41626.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final int FRAGMENT_PHIEUMUON = 0;
    private static final int FRAGMENT_LOAISACH = 1;
    private static final int FRAGMENT_SACH = 2;
    private static final int FRAGMENT_THANHVIEN = 3;
    private static final int FRAGMENT_TOP10 = 4;
    private static final int FRAGMENT_DOANHTHU = 5;

    private int mCurrentFragment = FRAGMENT_PHIEUMUON;
    Toolbar toolbar;
    DrawerLayout drawerLayout_home;
    NavigationView navigationView_home;
    String[] title = {"Quản lý Phiếu mượn",
            "Quản lý Loại sách",
            "Quản lý Sách",
            "Quản lý Thành viên",
            "Top 10 Sách mượn nhiều nhất",
            "Doanh thu"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findID();

        navigationView_home();
        navigationView_home.setItemTextColor(ColorStateList.valueOf(getColor(R.color.black)));
        navigationView_home.setItemIconTintList(ColorStateList.valueOf(getColor(R.color.black)));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title[0]);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,drawerLayout_home,toolbar,R.string.open,R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
//        drawerToggle.syncState();
        drawerLayout_home.addDrawerListener(drawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_menu));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_home,new PhieuMuonFragment());
        fragmentTransaction.commit();
        drawerLayout_home.close();
    }
    private void navigationView_home() {
        navigationView_home.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.manageLoanSlips) {
                    toolbar.setTitle(title[0]);
                    if (mCurrentFragment != FRAGMENT_PHIEUMUON) {
                        replaceFragment(new PhieuMuonFragment());
                        mCurrentFragment = FRAGMENT_PHIEUMUON;
                    }
                } else if(item.getItemId() == R.id.manageBookCategories) {
                    toolbar.setTitle(title[1]);
                    if (mCurrentFragment != FRAGMENT_LOAISACH) {
                        replaceFragment(new LoaiSachFragment());
                        mCurrentFragment = FRAGMENT_LOAISACH;
                    }
                } else if (item.getItemId() == R.id.bookManagement) {
                    toolbar.setTitle(title[2]);
                    if (mCurrentFragment != FRAGMENT_SACH) {
                        replaceFragment(new SachFragment());
                        mCurrentFragment = FRAGMENT_SACH;
                    }
                } else if (item.getItemId() == R.id.manageMember) {
                    toolbar.setTitle(title[3]);
                    if (mCurrentFragment != FRAGMENT_THANHVIEN) {
                        replaceFragment(new ThanhVienFragment());
                        mCurrentFragment = FRAGMENT_THANHVIEN;
                    }
                } else if (item.getItemId() == R.id.top10) {
                    toolbar.setTitle(title[4]);
                    if (mCurrentFragment != FRAGMENT_TOP10) {
                        replaceFragment(new Top10Fragment());
                        mCurrentFragment = FRAGMENT_TOP10;
                    }
                } else if (item.getItemId() == R.id.revenue) {
                    toolbar.setTitle(title[5]);
                    if (mCurrentFragment != FRAGMENT_DOANHTHU) {
                        replaceFragment(new DoanhThuFragment());
                        mCurrentFragment = FRAGMENT_DOANHTHU;
                    }
                }else {

                }
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_home,fragment);
        fragmentTransaction.commit();
        drawerLayout_home.close();
    }
    private void findID() {
        toolbar = findViewById(R.id.toolbar_home);

        drawerLayout_home = findViewById(R.id.drawerLayout_home);

        navigationView_home = findViewById(R.id.navigationView_home);
    }
}