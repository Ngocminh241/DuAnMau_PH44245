package com.example.duanmau_ph44245.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph44245.R;

public class ThuThuViewHolder extends RecyclerView.ViewHolder {
    TextView tt_name_tt, tt_id_tt;
    ImageView img_delete_tt;
    View item_tt;
    public ThuThuViewHolder(@NonNull View itemView) {
        super(itemView);
        tt_id_tt = itemView.findViewById(R.id.tt_id_tt_frag_tt);
        img_delete_tt = itemView.findViewById(R.id.img_delete_thuthu);
        tt_name_tt = itemView.findViewById(R.id.tt_name_tt_frag_tt);
        item_tt = itemView.findViewById(R.id.item_tt_frag_tt);
    }
}
