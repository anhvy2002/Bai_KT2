package com.test.bai_kt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.bai_kt2.model.Ca;

public class ThongTin extends AppCompatActivity {
    private TextView txtTenKH, txTenTGoi, txtDacTinh, txtMauSac;
    private Button btnThoat;
    private Ca ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);

        addControl();
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addControl() {
        txtTenKH = findViewById(R.id.txt_tenkh);
        txTenTGoi = findViewById(R.id.txt_tentgoi);
        txtDacTinh = findViewById(R.id.txt_dactinh);
        txtMauSac = findViewById(R.id.txt_mausac);

        btnThoat = findViewById(R.id.bt_thoat);

        Intent intent = getIntent();
        ca = (Ca) intent.getSerializableExtra("CA");
        if (ca !=null){
            txtTenKH.setText(ca.getTenKH());
            txTenTGoi.setText(ca.getTenTGoi());
            txtDacTinh.setText(ca.getDacTinh());
            txtMauSac.setText(ca.getMauSac());
        }else {
            Toast.makeText(this,"Lỗi load dữ liệu",Toast.LENGTH_LONG).show();
        }
    }
}