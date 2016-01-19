package com.baemin.sun.gym_android;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by elite on 16. 1. 19..
 */
public class RowDataAdapter extends BaseAdapter {

    ArrayList<RowData> datas;
    LayoutInflater inflater;

    public RowDataAdapter(LayoutInflater inflater, ArrayList<RowData> datas) {
        this.datas= datas;
        this.inflater= inflater;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size(); //datas의 개수를 리턴
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datas.get(position);//datas의 특정 인덱스 위치 객체 리턴.
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView==null){
            convertView= inflater.inflate(R.layout.list_row, null);
        }

        TextView text_name= (TextView)convertView.findViewById(R.id.list_text);
        ImageView img_flag= (ImageView)convertView.findViewById(R.id.list_img);

        text_name.setText( datas.get(position).getName() );
        img_flag.setImageResource(datas.get(position).getImgId());

        return convertView;
    }

}
