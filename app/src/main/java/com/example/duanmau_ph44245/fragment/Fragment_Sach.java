package com.example.duanmau_ph44245.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph44245.R;
import com.example.duanmau_ph44245.adapter.LoaiSachSpinnerAdapter;
import com.example.duanmau_ph44245.adapter.SachAdapter;
import com.example.duanmau_ph44245.dao.LoaiSachDAO;
import com.example.duanmau_ph44245.dao.SachDAO;
import com.example.duanmau_ph44245.model.LoaiSach;
import com.example.duanmau_ph44245.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Fragment_Sach extends Fragment {
    LayoutInflater inflater;
    View viewDialogAddSach;
    FloatingActionButton floatingActionButton;
    Context mContext;

    ArrayList<Sach> arrSach = new ArrayList<>();
    ArrayList<LoaiSach> arrLoaiSach = new ArrayList<>();
    RecyclerView rcySach;
    ArrayList<Sach> searchList;
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    SachAdapter sachAdapter;
    Spinner spinner_loai_sach;
    LoaiSachSpinnerAdapter loaiSachAdapter;
    Spinner spnSapXep;

    SearchView searchView;
    int maLoaiSach;
    public Fragment_Sach(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        inflater = getLayoutInflater();
        floatingActionButton = view.findViewById(R.id.float_btn_add_sach);
        spnSapXep = view.findViewById(R.id.spnSapXep);
        viewDialogAddSach = inflater.inflate(R.layout.dialog_add_sach, null);
        searchView = view.findViewById(R.id.searchView);

        rcySach= view.findViewById(R.id.rcv_sach);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcySach.setLayoutManager(layoutManager);

        loaiSachDAO = new LoaiSachDAO(mContext);
        sachDAO = new SachDAO(mContext);
        arrSach = (ArrayList<Sach>) sachDAO.getAllSach();
        sachAdapter = new SachAdapter(mContext, arrSach);
        rcySach.setAdapter(sachAdapter);
        //
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewDialogAddSach.getParent() != null) {
                    ((ViewGroup)viewDialogAddSach.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(viewDialogAddSach);
                EditText ed_name_sach, ed_nam_xb, ed_gia_thue;
                Button btn_add_sach, btn_cancel_add_sach;
                TextInputLayout input_name_sach, input_nam_xb_sach, input_price_sach;
                input_name_sach = viewDialogAddSach.findViewById(R.id.input_name_sach);
                input_nam_xb_sach = viewDialogAddSach.findViewById(R.id.input_nam_xb_sach);
                input_price_sach = viewDialogAddSach.findViewById(R.id.input_price_sach);
                spinner_loai_sach = viewDialogAddSach.findViewById(R.id.spn_sach);
                ed_name_sach = viewDialogAddSach.findViewById(R.id.ed_name_sach);
                ed_nam_xb = viewDialogAddSach.findViewById(R.id.ed_nam_xb_sach);
                ed_gia_thue = viewDialogAddSach.findViewById(R.id.ed_gia_thue_sach);
                btn_add_sach = viewDialogAddSach.findViewById(R.id.btn_dialog_add_sach);
                btn_cancel_add_sach = viewDialogAddSach.findViewById(R.id.btn_dialog_cancle_add_sach);

                ed_gia_thue.setText("");
                ed_nam_xb.setText("");
                ed_name_sach.setText("");
                input_name_sach.setError("");
                input_price_sach.setError("");
                input_nam_xb_sach.setError("");

                arrLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAllLoaiSach();
                int check = 1;
                if(arrLoaiSach.size() == 0) {
                    check = -1;
                    Snackbar snackbar = Snackbar
                            .make(view, "Chưa có loại sách!", Snackbar.LENGTH_LONG)
                            .setAction("Đóng", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.show();
                }

                if (check> 0) {
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    arrLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAllLoaiSach();
                    loaiSachAdapter = new LoaiSachSpinnerAdapter(mContext, arrLoaiSach);
                    spinner_loai_sach.setAdapter(loaiSachAdapter);
                    spinner_loai_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            maLoaiSach = arrLoaiSach.get(i).maloai;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    btn_cancel_add_sach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    btn_add_sach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int check = 1;
                            if (ed_name_sach.getText().toString().isEmpty()) {
                                input_name_sach.setError("Tên sách đang trống!");
                                check = -1;
                            }else if(ed_name_sach.getText().length()<3) {
                                input_name_sach.setError("Tên sách không được nhỏ hơn 5 kí tự!");
                                check = -1;
                            }else if(ed_name_sach.getText().length()> 16) {
                                input_name_sach.setError("Tên sách không được dài hơn 20 kí tự");
                                check = -1;
                            }else {
                                input_name_sach.setError("");
                            }
                            if (ed_nam_xb.getText().toString().isEmpty()) {
                                input_nam_xb_sach.setError("Năm xuất bản sách đang trống!");
                                check = -1;
                            }else if(ed_nam_xb.getText().length()<4 ||ed_nam_xb.getText().length()> 4) {
                                input_nam_xb_sach.setError("Năm xuất bản sách không hợp lệ!");
                                check = -1;
                            }else {
                                input_nam_xb_sach.setError("");
                            }
                            if (ed_gia_thue.getText().toString().isEmpty()) {
                                input_price_sach.setError("Giá sách đang trống!");
                                check = -1;
                            }else if(ed_gia_thue.getText().length()<4 ||ed_gia_thue.getText().length()> 16) {
                                input_price_sach.setError("Giá sách không hợp lệ!");
                                check = -1;
                            }else {
                                input_price_sach.setError("");
                            }
                            if(check > 0) {
                                Sach sach = new Sach();
                                sach.tenSach = ed_name_sach.getText().toString();
                                sach.namXB = Integer.parseInt(ed_nam_xb.getText().toString());
                                sach.giaThue = Integer.parseInt(ed_gia_thue.getText().toString());
                                sach.maLoai = maLoaiSach;
                                sachDAO.insertSach(sach);
                                arrSach = new ArrayList<>();
                                arrSach = (ArrayList<Sach>) sachDAO.getAllSach();
                                sachAdapter = new SachAdapter(mContext, arrSach);
                                rcySach.setAdapter(sachAdapter);
                                alertDialog.dismiss();
                                Snackbar.make(view, "Thêm sách thành công!", Snackbar.LENGTH_LONG)
                                        .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.primary_color))
                                        .show();
                                ed_nam_xb.setText("");
                                ed_gia_thue.setText("");
                                ed_name_sach.setText("");
                                input_name_sach.setError("");
                                input_nam_xb_sach.setError("");
                                input_price_sach.setError("");
                            }
                        }
                    });
                }
            }
        });
        //
        ArrayList<String> sapXepArr = new ArrayList<>();
        sapXepArr.add("-- Mặc định --");
        sapXepArr.add("Giá tăng dần");
        sapXepArr.add("Giá giảm dần");
        sapXepArr.add("Năm xuất bản");
        ArrayAdapter<String> adapterSX = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sapXepArr);
        spnSapXep.setAdapter(adapterSX);
        spnSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    arrSach = (ArrayList<Sach>) sachDAO.getAllSach();
                    sachAdapter = new SachAdapter(getContext(), arrSach);
                    rcySach.setAdapter(sachAdapter);
                } else if (position == 1) {
                    sapXepTang();
                } else if (position == 2) {
                    sapXepGiam();
                } else if (position == 3) {
                    sapXepNam();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
        //
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();
                if (query.length() > 0) {
                    for(int i = 0; i < arrSach.size(); i++) {
                        if(arrSach.get(i).tenSach.toUpperCase().contains(query.toUpperCase())) {
                            Sach s = new Sach();
                            s.setMaSach(arrSach.get(i).getMaSach());
                            s.setTenSach(arrSach.get(i).getTenSach());
                            s.setGiaThue(arrSach.get(i).getGiaThue());
                            s.setTenLoai(arrSach.get(i).getTenLoai());
                            searchList.add(s);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcySach.setLayoutManager(linearLayoutManager);
                    sachAdapter = new SachAdapter(getContext(), searchList);
                    rcySach.setAdapter(sachAdapter);
                    sachAdapter.notifyDataSetChanged();
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcySach.setLayoutManager(linearLayoutManager);
                    sachAdapter = new SachAdapter(getContext(), arrSach);
                    rcySach.setAdapter(sachAdapter);
                    sachAdapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();
                if (newText.length() > 0) {
                    for(int i = 0; i < arrSach.size(); i++) {
                        if(arrSach.get(i).getTenSach().toUpperCase().contains(newText.toUpperCase())) {
                            Sach s = new Sach();
                            s.setMaSach(arrSach.get(i).getMaSach());
                            s.setTenSach(arrSach.get(i).getTenSach());
                            s.setGiaThue(arrSach.get(i).getGiaThue());
                            s.setTenLoai(arrSach.get(i).getTenLoai());
                            searchList.add(s);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcySach.setLayoutManager(linearLayoutManager);
                    sachAdapter = new SachAdapter(getContext(), searchList);
                    rcySach.setAdapter(sachAdapter);
                    sachAdapter.notifyDataSetChanged();
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcySach.setLayoutManager(linearLayoutManager);
                    sachAdapter = new SachAdapter(getContext(), arrSach);
                    rcySach.setAdapter(sachAdapter);
                    sachAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
    }
    public void sapXepTang() {
        Collections.sort(arrSach, new Comparator<Sach>() {
            @Override
            public int compare(Sach o1, Sach o2) {
                if (o1.getGiaThue() > o2.getGiaThue()) {
                    return 1;
                } else {
                    if (o1.getGiaThue() == o2.getGiaThue()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        sachAdapter = new SachAdapter(getContext(), arrSach);
        rcySach.setAdapter(sachAdapter);
    }
    public void sapXepGiam() {
        Collections.sort(arrSach, new Comparator<Sach>() {
            @Override
            public int compare(Sach o1, Sach o2) {
                if (o1.getGiaThue() > o2.getGiaThue()) {
                    return -1;
                } else {
                    if (o1.getGiaThue() == o2.getGiaThue()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        });
        sachAdapter = new SachAdapter(getContext(), arrSach);
        rcySach.setAdapter(sachAdapter);
    }
    public void sapXepNam() {
        Collections.sort(arrSach, new Comparator<Sach>() {
            @Override
            public int compare(Sach o1, Sach o2) {
                if (o1.getNamXB() > o2.getNamXB()) {
                    return 1;
                } else {
                    if (o1.getNamXB() == o2.getNamXB()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        sachAdapter = new SachAdapter(getContext(), arrSach);
        rcySach.setAdapter(sachAdapter);
    }


}


