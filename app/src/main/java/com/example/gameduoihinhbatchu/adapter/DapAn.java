package com.example.gameduoihinhbatchu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gameduoihinhbatchu.R;

import java.util.ArrayList;
import java.util.List;

public class DapAn extends ArrayAdapter<String> {
    private Context myContext;
    private ArrayList<String> arr;

    public DapAn(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_cau_tra_loi,null);
        }
        TextView textCauTraLoi = convertView.findViewById(R.id.textCauTraLoi);
        textCauTraLoi.setText(this.arr.get(position));
        return convertView;
    }
}
