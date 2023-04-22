package com.example.ktlan2_sqlite_tablayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ktlan2_sqlite_tablayout.dal.SQLiteHelper;
import com.example.ktlan2_sqlite_tablayout.model.CongViec;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    public Spinner spiner;
    private EditText edttencv, edtnoidung, edtngayhoanthanh;
    private RadioButton rbcanhan, rbcongtac;
    private Button btnUpdate, btnBack, btnRemove;
    private Boolean check = true;
    private CongViec congViec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        anhXa();
        btnUpdate.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        edtngayhoanthanh.setOnClickListener(this);

        Intent intent = getIntent();
        congViec = (CongViec) intent.getSerializableExtra("congViec");
        edttencv.setText(congViec.getTenCV());
        edtnoidung.setText(congViec.getNoiDungCV());
        edtngayhoanthanh.setText(congViec.getNgayHoanThanh());
        String tinhTrang = congViec.getTinhTrang();
        if (tinhTrang.equals("Cá nhân")){
            rbcanhan.setChecked(true);
        }
        else {
            rbcongtac.setChecked(true);
        }
        int p = 0;
        for(int i = 0; i < spiner.getCount(); i++){
            if(spiner.getItemAtPosition(i).toString().equalsIgnoreCase(congViec.getTinhTrang())){
                p = i;
                break;
            }
        }
        spiner.setSelection(p);
    }

    private void anhXa() {
        spiner = findViewById(R.id.spiner);
        edttencv = findViewById(R.id.edttencv);
        edtnoidung = findViewById(R.id.edtnoidung);
        edtngayhoanthanh = findViewById(R.id.edtngayhoanthanh);
        rbcanhan = findViewById(R.id.rbcanhan);
        rbcongtac = findViewById(R.id.rbcongtac);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnBack);
        btnRemove = findViewById(R.id.btnRemove);
        spiner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spiner,
                getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View v) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(v == edtngayhoanthanh){
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, (view, year, month, dayOfMonth) -> {
                String date = "";
                if(month > 8){
                    date = dayOfMonth + "/" + (month + 1) + "/" + y;
                }
                else{
                    date = dayOfMonth + "/0" + (month + 1) + "/" + y;
                }
                edtngayhoanthanh.setText(date);
            }, y, m , d);
            dialog.show();
        }
        if (v== btnBack) {
            finish();
        }
        if (v == btnUpdate){
            String tenCV = edttencv.getText().toString();
            String noiDung = edtnoidung.getText().toString();
            String ngayHoanThanh = edtngayhoanthanh.getText().toString();
            String congtac = "Cá nhân";
            if (check == false){
                congtac = "Cộng tác";
            }
            String tinhTrang = spiner.getSelectedItem().toString();
            if(!tenCV.isEmpty() && !noiDung.isEmpty()){
                CongViec cv = new CongViec(tenCV, noiDung, ngayHoanThanh, tinhTrang, congtac);
                db.addItem(cv);
                finish();
            }
        }
        if (v == btnRemove){
            int id = congViec.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa " + congViec.getTenCV() +  " không?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Có", (dialog, which) -> {
                db.deleteItem(id);
                finish();
            });
            builder.setNegativeButton("Không", (dialog, which) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}