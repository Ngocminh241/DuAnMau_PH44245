package com.example.duanmau_ph44245.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph44245.R;
import com.example.duanmau_ph44245.dao.PhieuMuonDAO;
import com.example.duanmau_ph44245.dao.ThanhVienDAO;
import com.example.duanmau_ph44245.dao.ThuThuDAO;
import com.example.duanmau_ph44245.fragment.ThanhVienFragment;
import com.example.duanmau_ph44245.fragment.ThuThuFragment;
import com.example.duanmau_ph44245.model.PhieuMuon;
import com.example.duanmau_ph44245.model.ThanhVien;
import com.example.duanmau_ph44245.model.ThuThu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuViewHolder> {
    ArrayList<ThuThu> arrThuThu = new ArrayList<>();
    ThuThuDAO thuThuDAO;
    Context context;

    LayoutInflater inflater;
    View viewDeleteThuThu, viewUpdateThuThu;
    ThuThuFragment thuThuFragment;
    View viewAlert;


    public ThuThuAdapter(Context context, ThuThuFragment thuThuFragment, ArrayList<ThuThu> arrThuThu) {
        this.context = context;
        this.arrThuThu = arrThuThu;
        this.thuThuFragment = thuThuFragment;
    }

    @NonNull
    @Override
    public ThuThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.custom_thu_thu, parent, false);
        ThuThuViewHolder thuThuViewHolder = new ThuThuViewHolder(viewItem);
        viewAlert = parent;
        return thuThuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        thuThuFragment = new ThuThuFragment();
        thuThuDAO = new ThuThuDAO(context);
        ThuThu thuThu = arrThuThu.get(position);
        holder.tt_id_tt.setText(thuThu.maTT + " ");
        holder.tt_name_tt.setText(thuThu.hoTen);


        inflater = LayoutInflater.from(context);
        viewDeleteThuThu = inflater.inflate(R.layout.dialog_delete_tt, null);
//        viewUpdateThanhVien = inflater.inflate(R.layout.dialog_update_tv, null);

        holder.img_delete_tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewDeleteThuThu.getParent() != null) {
                    ((ViewGroup) viewDeleteThuThu.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(viewDeleteThuThu);

                Button btn_delete_tt, btn_cancel_delete_tt;
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                btn_delete_tt = viewDeleteThuThu.findViewById(R.id.btn_dialog_delete_tt);
                btn_cancel_delete_tt = viewDeleteThuThu.findViewById(R.id.btn_dialog_cancel_delete_tt);
                btn_cancel_delete_tt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                btn_delete_tt.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        thuThuDAO.deleteThuThu(thuThu.maTT + "");
                        arrThuThu.remove(thuThu);
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                        Snackbar.make(viewAlert, "Xoá thành viên thành công!", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(R.color.primary_color)
                                .show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrThuThu.size();
    }
}
