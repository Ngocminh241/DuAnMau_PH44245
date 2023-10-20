package com.example.duanmau_ph44245.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_ph44245.database.DBHelper;
import com.example.duanmau_ph44245.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;
    Context context;

    public static  final  String TABLE_NAME_SACH = DBHelper.TABLE_NAME_SACH;
    public SachDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public long insertSach (Sach sach) {
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.tenSach);
        values.put("namXuatBan", sach.namXB);
        values.put("giaThue", sach.giaThue);
        values.put("maLoai", sach.maLoai);

        return sqLiteDatabase.insert(TABLE_NAME_SACH, null, values);
    }

    public int updateSach (Sach sach) {
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.tenSach);
        values.put("namXuatBan", sach.namXB);
        values.put("giaThue", sach.giaThue);
        values.put("maLoai", sach.maLoai);

        return sqLiteDatabase.update(TABLE_NAME_SACH, values, "maSach=?", new String[]{String.valueOf(sach.maSach)});
    }

    public int deleteSach (String id) {
        return sqLiteDatabase.delete(TABLE_NAME_SACH, "maSach=?", new String[]{id});
    }

    public List<Sach> getAllSach() {
        String sql = "SELECT * FROM " + TABLE_NAME_SACH;
        return getData(sql);
    }

    public Sach getIdSach(String id) {
        String sql = "SELECT * FROM " + TABLE_NAME_SACH + " WHERE maSach=?";
        List<Sach> listSach = getData(sql, id);
        return listSach.get(0);
    }

    int row;
    public int getMaS(String tenS) {
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT maSach FROM tb_Sach WHERE tb_Sach.tenSach = ?", new String[] {tenS});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    row = cursor.getInt(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return row;
    }
    String tenSach;
    public String getTenS(int maSach) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT tenSach FROM tb_Sach WHERE tb_Sach.maSach = ?", new String[] {String.valueOf(maSach)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tenSach = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return tenSach;
    }
    int tienThue;
    public int getTienThue(String tenS) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT giaThue FROM tb_Sach WHERE tb_Sach.tenSach = ?", new String[] {tenS});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tienThue = cursor.getInt(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return tienThue;
    }
    int rowML;
    public boolean checkMaLoai(int maLoai) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT maLoai FROM tb_Sach WHERE tb_Sach.maLoai = ?", new String[] {String.valueOf(maLoai)});
            rowML = cursor.getCount();
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return (rowML <= 0) ? true : false;
    }

    private List<Sach> getData(String sql, String...selectionArgs) {
        List<Sach> listSach = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Sach sach = new Sach();
            sach.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            sach.tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
            sach.namXB = Integer.parseInt(cursor.getString(cursor.getColumnIndex("namXuatBan")));
            sach.giaThue = Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue")));
            sach.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai")));
            listSach.add(sach);
        }
        return listSach;
    }


}
