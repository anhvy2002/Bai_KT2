package com.test.bai_kt2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.bai_kt2.R;
import com.test.bai_kt2.ThemCa;
import com.test.bai_kt2.ThongTin;
import com.test.bai_kt2.model.Ca;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class CaAdapter extends ArrayAdapter<Ca> {
    @NonNull
    private Activity activity;
    private int resource;
    @NonNull
    private List<Ca> objects;

    public CaAdapter(@NonNull Activity activity, int resource, @NonNull List<Ca> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);

        TextView txtTenKH = view.findViewById(R.id.txtTenKH);
        TextView txtTenTGoi = view.findViewById(R.id.txtTenTGoi);

        Ca ca = this.objects.get(position);
        txtTenKH.setText(ca.getTenKH());
        txtTenTGoi.setText(ca.getTenTGoi());

        ImageView btnMenu= view.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(activity,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.item_chitiet){
                            Intent intent= new Intent(activity, ThongTin.class);
                            intent.putExtra("CA",ca);
                            activity.startActivity(intent);
                        }
                        if(menuItem.getItemId() == R.id.item_them){
                            Intent intent = new Intent(activity, ThemCa.class);
                            activity.startActivity(intent);
                        }
                        if(menuItem.getItemId() == R.id.item_xoa){
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("DbCa");
                            myRef.child(ca.getId()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Toast.makeText(activity,"Xóa thành công",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        return false;
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                try{
                    Field field = popupMenu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper = field.get(popupMenu);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[]{boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper, new Object[]{true});
                }catch (Exception e){
                    Log.d("MYTAG","onClick: "+e.toString());
                }
                popupMenu.show();
            }
        });
        return view;
    }
}
