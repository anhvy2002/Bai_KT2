package com.test.bai_kt2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.bai_kt2.adapter.CaAdapter;
import com.test.bai_kt2.model.Ca;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvCayXanh;
    private ArrayList<Ca> arrayList;
    private CaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCayXanh = findViewById(R.id.lvCayXanh);
        arrayList = new ArrayList<>();
        GetData();

        adapter = new CaAdapter(this,R.layout.custom_listview_item, arrayList);
        lvCayXanh.setAdapter(adapter);
    }
    private void GetData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("DbCa");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();

                for (DataSnapshot data : snapshot.getChildren()){
                    Ca ca = data.getValue(Ca.class);
                    ca.setId(data.getKey());
                    adapter.add(ca);
                    Log.d("MYTAG","onDataChange: "+ca);
                }
                Toast.makeText(getApplicationContext(),"Load thành công",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Load thất bại",Toast.LENGTH_LONG).show();
                Log.d("MYTAG","onCancelled: "+error.toString());
            }
        });
    }
}