package com.test.bai_kt2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.bai_kt2.model.Ca;

public class ThemCa extends AppCompatActivity {
    private EditText edtTenKH, edtTenTGoi, edtDacTinh, edtMauSac;
    private Button btnThem, btnThoat, btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ca);

        addControl();
        addEvent();
    }

    private void addEvent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTenKH.setText("");
                edtTenTGoi.setText("");
                edtDacTinh.setText("");
                edtMauSac.setText("");
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenkh = edtTenKH.getText().toString();
                String tentg = edtTenTGoi.getText().toString();
                String dactinh = edtDacTinh.getText().toString();
                String mausac = edtMauSac.getText().toString();

                Ca ca = new Ca(tenkh,tentg,dactinh,mausac);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DbCa");

                String id = myRef.push().getKey();
                myRef.child(id).setValue(ca).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Thêm thất bại "+e.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void addControl() {
        edtTenKH = findViewById(R.id.edt_tenkh);
        edtTenTGoi = findViewById(R.id.edt_tentgoi);
        edtDacTinh = findViewById(R.id.edt_dactinh);
        edtMauSac = findViewById(R.id.edt_mausac);

        btnThem = findViewById(R.id.bt_them);
        btnThoat = findViewById(R.id.bt_thoat);
        btnHuy = findViewById(R.id.bt_huy);
    }
}