package com.example.ktlan2_sqlite_tablayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ktlan2_sqlite_tablayout.dal.SQLiteHelper;
import com.example.ktlan2_sqlite_tablayout.model.CongViec;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    public Spinner spiner;
    private EditText edttencv, edtnoidung, edtngayhoanthanh;
    private RadioButton rbcanhan, rbcongtac;
    private Button btnUpdate, btnCancel;
    private Boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        anhXa();
        btnUpdate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        edtngayhoanthanh.setOnClickListener(this);
        rbcanhan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check = true;
                }
            }
        });
        rbcongtac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    check = false;
                }
            }
        });
    }

    private void anhXa() {
        spiner = findViewById(R.id.spiner);
        edttencv = findViewById(R.id.edttencv);
        edtnoidung = findViewById(R.id.edtnoidung);
        edtngayhoanthanh = findViewById(R.id.edtngayhoanthanh);
        rbcanhan = findViewById(R.id.rbcanhan);
        rbcongtac = findViewById(R.id.rbcongtac);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        spiner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spiner,
                        getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View v) {
        if(v == edtngayhoanthanh){
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, (view, year, month, dayOfMonth) -> {
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
        if (v== btnCancel) {
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
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(cv);
                finish();
            }
        }
    }
}