package com.example.duanmau_ph44245.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_ph44245.R;
import com.example.duanmau_ph44245.adapter.ThongKeAdapter;
import com.example.duanmau_ph44245.dao.ThongKeDAO;
import com.example.duanmau_ph44245.model.Top;

import java.util.ArrayList;

public class Fragment_Top10 extends Fragment {
    LayoutInflater inflater;
    Context mContext;

    ArrayList<Top> arrTop = new ArrayList<>();
    RecyclerView rcyTop;
    ThongKeDAO thongKeDAO;
    ThongKeAdapter thongKeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
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

        rcyTop= view.findViewById(R.id.rcv_top);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcyTop.setLayoutManager(layoutManager);

        thongKeDAO = new ThongKeDAO(mContext);
        arrTop = (ArrayList<Top>) thongKeDAO.getTop();
        thongKeAdapter = new ThongKeAdapter(mContext, arrTop);
        rcyTop.setAdapter(thongKeAdapter);

    }
}
