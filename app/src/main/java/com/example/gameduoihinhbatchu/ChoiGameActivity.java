package com.example.gameduoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gameduoihinhbatchu.Object.CauDo;
import com.example.gameduoihinhbatchu.model.ChoiGameModels;
import com.example.gameduoihinhbatchu.adapter.DapAn;

import java.util.ArrayList;
import java.util.Random;

public class ChoiGameActivity extends AppCompatActivity {
    ChoiGameModels models;
    CauDo cauDo;
    private String dapan="soctrang";

    ArrayList<String> arrCauTraLoi;
    GridView gdvCauTraLoi;
    int index=0;
    ArrayList<String> arrDapAn;
    GridView gdvDapAn;
    ImageView imgAnhCauDo;
    TextView txvTienNguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_game);
        init();
        AnhXa();
        setOnClick();
        hienCauDo();
    }

    private void AnhXa(){
        gdvCauTraLoi = findViewById(R.id.gdvCauTraLoi);
        gdvDapAn = findViewById(R.id.gdvDapAn);
        imgAnhCauDo = findViewById(R.id.imgAnhCauDo);
        txvTienNguoiDung = findViewById(R.id.txvTienNguoiDung);
           }
    private void init(){
        models = new ChoiGameModels(this);
        arrCauTraLoi = new ArrayList<>();
        arrDapAn = new ArrayList<>();

    }
    private void hienCauDo(){
        cauDo =models.layCauDo();
        dapan =cauDo.dapAn;
        Data();
        hienthiCauTraLoi();
        hienthiDapAn();
        Glide.with(this)
                .load(cauDo.anh)
                .into(imgAnhCauDo);
        models.layThongTin();
        txvTienNguoiDung.setText(models.nguoiDung.tien+"$");
    }
    private void hienthiCauTraLoi(){
        gdvCauTraLoi.setNumColumns(arrCauTraLoi.size());
        gdvCauTraLoi.setAdapter(new DapAn(this,0,arrCauTraLoi));
    }
    private void setOnClick(){
        gdvDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if(s.length()!=0 && index<arrCauTraLoi.size()){
                    for (int i=0;i<arrCauTraLoi.size();i++){
                        if(arrCauTraLoi.get(i).length()==0){
                            index = i;
                            break;
                        }
                    }
                    arrDapAn.set(position,"");
                    arrCauTraLoi.set(index,s);
                    index++;
                    hienthiCauTraLoi();
                    hienthiDapAn();
                    checkWin();
                }
            }
        });
        gdvCauTraLoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if(s.length()!=0 ){
                    index = position;
                    arrCauTraLoi.set(position,"");
                    for(int i=0;i<arrDapAn.size();i++) {
                       if(arrDapAn.get(i).length()==0){
                          arrDapAn.set(i,s);
                          break;
                        }
                    }
                    hienthiCauTraLoi();
                    hienthiDapAn();
                }
            }
        });
    }

    private void hienthiDapAn(){
        gdvDapAn.setNumColumns(arrDapAn.size()/2);
        gdvDapAn.setAdapter(new DapAn(this,0,arrDapAn));
    }
    private void Data(){
        index=0;
        arrCauTraLoi.clear();
        arrDapAn.clear();
        Random r = new Random();

        for (int i=0;i<dapan.length();i++){
           arrCauTraLoi.add("");
           String s = "" + (char)(r.nextInt(26)+65);
           arrDapAn.add(s);

            String s1 = "" + (char)(r.nextInt(26)+65);
            arrDapAn.add(s1);
        }
        for (int i=0;i<dapan.length();i++){
            String s = "" + dapan.charAt(i);
            arrDapAn.set(i,s.toUpperCase());
        }
        for(int i=0;i<arrDapAn.size();i++){
            String s = arrDapAn.get(i);
            int vt = r.nextInt(arrDapAn.size());
            arrDapAn.set(i,arrDapAn.get(vt));
            arrDapAn.set(vt,s);
        }
    }

    private void checkWin(){
        String s="";
        for(String sl:arrCauTraLoi){
            s=s+sl;
        }
        s=s.toUpperCase();
        if(s.equals(dapan.toUpperCase())){
            Toast.makeText(this,"Ban Da Chien Thang",Toast.LENGTH_SHORT).show();
            hienCauDo();
        }
    }
}