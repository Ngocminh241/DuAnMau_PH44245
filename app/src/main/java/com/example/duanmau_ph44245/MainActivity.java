package com.example.duanmau_ph44245;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.duanmau_ph44245.dao.ThuThuDAO;
import com.example.duanmau_ph44245.fragment.Fragment_ChangePass;
import com.example.duanmau_ph44245.fragment.Fragment_DoanhThu;
import com.example.duanmau_ph44245.fragment.Fragment_LoaiSach;
import com.example.duanmau_ph44245.fragment.Fragment_PhieuMuon;
import com.example.duanmau_ph44245.fragment.Fragment_Sach;
import com.example.duanmau_ph44245.fragment.Fragment_ThanhVien;
import com.example.duanmau_ph44245.fragment.Fragment_AddUser;
import com.example.duanmau_ph44245.fragment.Fragment_Top10;
import com.example.duanmau_ph44245.model.ThuThu;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    Fragment_AddUser fragmentAddUser;
    Fragment_ChangePass fragmentChangePass;
    Fragment_DoanhThu fragmentDoanhThu;
    Fragment_LoaiSach fragmentLoaiSach;
    Fragment_PhieuMuon fragmentPhieuMuon;
    Fragment_Sach fragmentSach;
    Fragment_ThanhVien fragmentThanhVien;
    Fragment_Top10 fragmentTop10;

    View dialogLogOut;
    LayoutInflater layoutInflater;

    View mHeaderView;
    TextView tvUsername;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        //anh xa dialog logout
        layoutInflater = getLayoutInflater();
        dialogLogOut = layoutInflater.inflate(R.layout.dialog_log_out, null);

        //drawer app
        drawerLayout = findViewById(R.id.drawer_app);
        navigationView = findViewById(R.id.menu_drawer);

        //custom tool bar
        toolbar = findViewById(R.id.toolbar_app);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.icon_menu_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //hien thi username len header
        mHeaderView = navigationView.getHeaderView(0);
        tvUsername = mHeaderView.findViewById(R.id.tv_username_header);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getIdTT(user);
        String username = thuThu.hoTen;
        tvUsername.setText(username);

        if (!user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.nav_them_nguoi_dung).setVisible(false);
        }

        fragmentAddUser = new Fragment_AddUser();
        fragmentChangePass = new Fragment_ChangePass();
        fragmentDoanhThu = new Fragment_DoanhThu();
        fragmentLoaiSach = new Fragment_LoaiSach();
        fragmentPhieuMuon = new Fragment_PhieuMuon();
        fragmentSach = new Fragment_Sach();
        fragmentThanhVien = new Fragment_ThanhVien();
        fragmentTop10 = new Fragment_Top10();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentPhieuMuon).commit();
        //an menu
//        navigationView.getMenu().findItem(R.id.nav_them_nguoi_dung).setVisible(false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_phieu_muon) {
                    setTitle("Quản lý phiếu mượn");
                    Fragment_PhieuMuon fragmentPhieuMuon = new Fragment_PhieuMuon();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragmentPhieuMuon).commit();
                } else if (itemId == R.id.nav_loai_sach) {
                    setTitle("Quản lý loại sách");
                    Fragment_LoaiSach fragmentLoaiSach = new Fragment_LoaiSach();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragmentLoaiSach).commit();
                } else if (itemId == R.id.nav_sach) {
                    setTitle("Quản lý sách");
                    Fragment_Sach fragmentSach = new Fragment_Sach();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragmentSach).commit();
                } else if (itemId == R.id.nav_thanh_vien) {
                    setTitle("Quản lý thành viên");
                    Fragment_ThanhVien fragmentThanhVien = new Fragment_ThanhVien();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragmentThanhVien).commit();
                } else if (itemId == R.id.nav_top) {
                    setTitle("10 sách mượn nhiều nhất");
                    Fragment_Top10 fragmentTop10 = new Fragment_Top10();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragmentTop10).commit();
                } else if (itemId == R.id.nav_doanh_thu) {
                    setTitle("Doanh thu");
                    Fragment_DoanhThu fragmentDoanhThu = new Fragment_DoanhThu();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragmentDoanhThu).commit();
                } else if (itemId == R.id.nav_them_nguoi_dung) {
                    setTitle("Thêm người dùng");
                    Fragment_AddUser fragmentAddUser = new Fragment_AddUser();
                    fragmentManager.beginTransaction()
                           .replace(R.id.fragment_container, fragmentAddUser).commit();
                } else if (itemId == R.id.nav_doi_mat_khau) {
                    setTitle("Đổi mật khẩu");
                    Fragment_ChangePass fragmentChangePass = new Fragment_ChangePass();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragmentChangePass).commit();
                } else if (itemId == R.id.nav_dang_xuat) {
                    if (dialogLogOut.getParent() != null) {
                        ((ViewGroup) dialogLogOut.getParent()).removeAllViews();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(dialogLogOut);

                    Button btn_logout, btn_cancel_logout;
                    btn_logout = dialogLogOut.findViewById(R.id.btn_dialog_log_out);
                    btn_cancel_logout = dialogLogOut.findViewById(R.id.btn_dialog_cancel_log_out);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    btn_logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            MainActivity.this.finish();
                        }
                    });
                    btn_cancel_logout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                }

                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
        }
        return super.onOptionsItemSelected(item);
    }
}
