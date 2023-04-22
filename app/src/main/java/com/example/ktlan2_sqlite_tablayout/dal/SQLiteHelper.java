package com.example.ktlan2_sqlite_tablayout.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.ktlan2_sqlite_tablayout.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Congviec.db";
    private static int DATABASE_VERSION = 1;


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE congviecs(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenCV TEXt, noiDungCV TEXT, ngayHoanThanh TEXT, tinhTrang TEXT, congTac TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<CongViec> getAll() {
        List<CongViec> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = sqLiteDatabase.query("items",
                null, null, null,
                null, null, order);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String tenCV = rs.getString(1);
            String noiDungCV = rs.getString(2);
            String ngayHoanThanh = rs.getString(3);
            String tinhTrang = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,tenCV,noiDungCV,ngayHoanThanh,tinhTrang,congTac));
        }
        return list;
    }


    public long addItem(CongViec i){
        ContentValues values = new ContentValues();
        values.put("tenCV", i.getTenCV());
        values.put("noiDungCV", i.getNoiDungCV());
        values.put("ngayHoanThanh", i.getNgayHoanThanh());
        values.put("tinhTrang", i.getTinhTrang());
        values.put("congTac", i.getCongTac());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("congviecs",null, values);
    }

    public List<CongViec> getByDate(String date) {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "date like ?";
        String[] whereArgs = {date};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String tenCV = rs.getString(1);
            String noiDungCV = rs.getString(2);
            String ngayHoanThanh = rs.getString(3);
            String tinhTrang = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,tenCV,noiDungCV,ngayHoanThanh,tinhTrang,congTac));
        }
        return list;
    }

    public int updateItem(CongViec i) {
        ContentValues values = new ContentValues();
        values.put("tenCV", i.getTenCV());
        values.put("noiDungCV", i.getNoiDungCV());
        values.put("ngayHoanThanh", i.getNgayHoanThanh());
        values.put("tinhTrang", i.getTinhTrang());
        values.put("congTac", i.getCongTac());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("congviecs",
                values, whereClause, whereArgs);
    }

    public int deleteItem(int id){
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("congviecs", whereClause, whereArgs);
    }

    public List<CongViec> searchByTitle(String key) {
        List<CongViec> list= new ArrayList<>();
        String whereClause = "title like ?";
        String[] whereArgs = {"%"+key+"%"};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {

            int id= rs.getInt(0);
            String tenCV = rs.getString(1);
            String noiDungCV = rs.getString(2);
            String ngayHoanThanh = rs.getString(3);
            String tinhTrang = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,tenCV,noiDungCV,ngayHoanThanh,tinhTrang,congTac));
        }
        return list;
    }


    public List<CongViec> searchByCategory(String category) {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "category like ?";
        String[] whereArgs = {category};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String tenCV = rs.getString(1);
            String noiDungCV = rs.getString(2);
            String ngayHoanThanh = rs.getString(3);
            String tinhTrang = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,tenCV,noiDungCV,ngayHoanThanh,tinhTrang,congTac));
        }
        return list;
    }

    public List<CongViec> getByDateFromTo(String from,String to) {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "date BETWEEN ? AND ?";
        String[] whereArgs = { from.trim(),to.trim()};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String tenCV = rs.getString(1);
            String noiDungCV = rs.getString(2);
            String ngayHoanThanh = rs.getString(3);
            String tinhTrang = rs.getString(4);
            String congTac = rs.getString(5);
            list.add(new CongViec(id,tenCV,noiDungCV,ngayHoanThanh,tinhTrang,congTac));
        }
        return list;
    }

}
