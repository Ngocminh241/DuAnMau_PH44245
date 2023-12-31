package com.example.duanmau_ph44245.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau_ph44245.R;
import com.example.duanmau_ph44245.dao.ThongKeDAO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fragment_DoanhThu extends Fragment {
    Button btn_doanh_thu;
    EditText ed_doanh_thu_tu_ngay, ed_doanh_thu_den_ngay;
    TextView tv_doanh_thu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;
    int check = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        //
        ed_doanh_thu_tu_ngay = view.findViewById(R.id.ed_doanh_thu_tu_ngay);
        ed_doanh_thu_den_ngay = view.findViewById(R.id.ed_doanh_thu_den_ngay);
        tv_doanh_thu = view.findViewById(R.id.tv_doanh_thu);
        btn_doanh_thu = view.findViewById(R.id.btn_doanh_thu);
        //
        bt_DT();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_doanh_thu_tu_ngay.setText(sdf.format(c.getTime()));
            }
        };
        DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_doanh_thu_den_ngay.setText(sdf.format(c.getTime()));
            }
        };
        ed_doanh_thu_tu_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        ed_doanh_thu_den_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
    }
    public void bt_DT(){
        btn_doanh_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = ed_doanh_thu_tu_ngay.getText().toString();
                String denNgay = ed_doanh_thu_den_ngay.getText().toString();
                if (tuNgay.isEmpty() || denNgay.isEmpty()) {
                    Toast.makeText(getActivity(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                    check++;
                } else {
                    String[] temptuNgay = tuNgay.split("-");
                    String[] tempdenNgay = denNgay.split("-");

                    String newTuNgay = "";
                    String newDenNgay = "";

                    int intTuNgay = Integer.parseInt(newTuNgay.concat(temptuNgay[0]).concat(temptuNgay[1]).concat(temptuNgay[2]));
                    int intDenNgay = Integer.parseInt(newDenNgay.concat(tempdenNgay[0]).concat(tempdenNgay[1]).concat(tempdenNgay[2]));
                    if (intTuNgay > intDenNgay) {
                        Toast.makeText(getActivity(), "Lỗi, từ ngày phải bé hơn đến ngày", Toast.LENGTH_SHORT).show();
                        check++;
                    }
                }
                if (check == 0) {
                    ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
                    tv_doanh_thu.setTextColor(Color.BLACK);
                    tv_doanh_thu.setText(thongKeDAO.getDoanhThu(tuNgay, denNgay) + " vnđ");
                }
            }
        });
    }
}
