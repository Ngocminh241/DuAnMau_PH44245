package com.example.duanmau_ph44245.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph44245.R;
import com.example.duanmau_ph44245.adapter.ThanhVienAdapter;
import com.example.duanmau_ph44245.adapter.ThuThuAdapter;
import com.example.duanmau_ph44245.dao.ThanhVienDAO;
import com.example.duanmau_ph44245.dao.ThuThuDAO;
import com.example.duanmau_ph44245.model.ThanhVien;
import com.example.duanmau_ph44245.model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class ThuThuFragment extends Fragment {
    ThuThuDAO thuThuDAO;
    Button  btn_add_user;
    EditText ed_username, ed_name, ed_password, ed_repassword;
    TextInputLayout input_username, input_name, input_pass, input_repass;
    TextView show_err;
    LayoutInflater inflater;
    View viewDialogAddThuThu;
    FloatingActionButton floatingActionButton;
    Context mContext;

    ArrayList<ThuThu> arrThuThu = new ArrayList<>();
    RecyclerView rcyThuThu;
    ThuThuAdapter thuThuAdapter;
    ThuThuFragment thuThuFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_thu, container, false);
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
        floatingActionButton = view.findViewById(R.id.float_btn_add_thu_thu);
        viewDialogAddThuThu = inflater.inflate(R.layout.fragment_add_user, null);

        rcyThuThu= view.findViewById(R.id.rcv_thu_thu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcyThuThu.setLayoutManager(layoutManager);

        thuThuDAO = new ThuThuDAO(mContext);
        arrThuThu= (ArrayList<ThuThu>) thuThuDAO.getAllThuThu();
        thuThuAdapter = new ThuThuAdapter(mContext,this,  arrThuThu);
        rcyThuThu.setAdapter(thuThuAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewDialogAddThuThu.getParent() != null) {
                    ((ViewGroup)viewDialogAddThuThu.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(viewDialogAddThuThu);
                show_err = viewDialogAddThuThu.findViewById(R.id.check_trung);
                input_username = viewDialogAddThuThu.findViewById(R.id.input_username_add_user);
                input_name = viewDialogAddThuThu.findViewById(R.id.input_name_add_user);
                input_pass = viewDialogAddThuThu.findViewById(R.id.input_pass_add_user);
                input_repass = viewDialogAddThuThu.findViewById(R.id.input_repass_add_user);
                ed_username = viewDialogAddThuThu.findViewById(R.id.ed_username_add_user);
                ed_name = viewDialogAddThuThu.findViewById(R.id.ed_name_add_user);
                ed_password = viewDialogAddThuThu.findViewById(R.id.ed_password_add_user);
                ed_repassword = viewDialogAddThuThu.findViewById(R.id.ed_repassword_add_user);
                btn_add_user = viewDialogAddThuThu.findViewById(R.id.btn_add_user);



                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btn_add_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ThuThu thuThu = new ThuThu();
                        thuThu.maTT = ed_username.getText().toString();
                        thuThu.hoTen = ed_name.getText().toString();
                        thuThu.matKhau = ed_password.getText().toString();
                        if(validate() > 0) {
                            if(thuThuDAO.insertThuThu(thuThu)> 0) {
                                arrThuThu = new ArrayList<>();
                                arrThuThu = (ArrayList<ThuThu>) thuThuDAO.getAllThuThu();
                                thuThuAdapter = new ThuThuAdapter(mContext,thuThuFragment,  arrThuThu);
                                rcyThuThu.setAdapter(thuThuAdapter);
                                Snackbar.make(view, "Thêm thủ thư thành công!", Snackbar.LENGTH_LONG)
                                        .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.primary_color))
                                        .show();
                                alertDialog.dismiss();
                                ed_username.setText("");
                                ed_name.setText("");
                                ed_password.setText("");
                                ed_repassword.setText("");


                            } else {
                                Snackbar.make(view, "Thêm thủ thư thất bại!", Snackbar.LENGTH_LONG)
                                        .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.primary_color))
                                        .show();
                            }
                        }
                    }
                });

            }
            public int validate() {
                int check = 1;
                if(ed_username.getText().length() == 0) {
                    input_username.setError("Tên đăng nhập trống!");
                    check = -1;
                }else if(ed_username.getText().length()<3) {
                    input_username.setError("Tên đăng nhập phải lớn hơn 3 kí tự!");
                    check = -1;
                }else if(ed_username.getText().length()> 16) {
                    input_username.setError("Tên đăng nhập không được lớn hơn 16 kí tự!");
                    check = -1;
                }else {
                    input_username.setError("");
                }

                if(ed_name.getText().toString().length() == 0) {
                    input_name.setError("Họ tên đang trống!");
                    check = -1;
                }else if(ed_name.getText().length()<3) {
                    input_name.setError("Họ tên phải lớn hơn 3 kí tự!");
                    check = -1;
                }else if(ed_name.getText().length()> 16) {
                    input_name.setError("Họ tên không được lớn hơn 16 kí tự!");
                    check = -1;
                }else {
                    input_name.setError("");
                }
                if(ed_password.getText().length() == 0) {
                    input_pass.setError("Mật khẩu đang trống!");
                    check = -1;
                }else if(ed_password.getText().length()<3) {
                    input_pass.setError("Mật khẩu phải lớn hơn 3 kí tự!");
                    check = -1;
                }else if(ed_password.getText().length()> 16) {
                    input_pass.setError("Mật khẩu không được lớn hơn 16 kí tự!");
                    check = -1;
                }else {
                    input_pass.setError("");
                }
                if(ed_repassword.getText().length() == 0) {
                    input_repass.setError("Mật khẩu nhập lại đang trống!");
                    check = -1;
                }
                else {
                    input_repass.setError("");
                    String pass = ed_password.getText().toString();
                    String repass = ed_repassword.getText().toString();
                    if (!pass.equals(repass)) {
                        input_repass.setError("Mật khẩu nhập lại không trùng khớp!");
                        check = -1;
                    } else {
                        input_repass.setError("");
                    }
                }
                return  check;
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
