package com.baemin.sun.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baemin.sun.gym_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by elite on 16. 1. 19..
 */
public class RowDataAdapter extends BaseAdapter {

    private ArrayList<RowData> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;

    public RowDataAdapter(Context context, LayoutInflater inflater, ArrayList<RowData> datas) {
        this.mDatas= datas;
        this.mInflater= inflater;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDatas.size(); //datas의 개수를 리턴
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mDatas.get(position);//datas의 특정 인덱스 위치 객체 리턴.
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewholder;

        if( convertView==null){
            convertView = mInflater.inflate(R.layout.list_row, parent, false);
            mViewholder = new ViewHolder();

            mViewholder.mTextView = (TextView)convertView.findViewById(R.id.list_text);
            mViewholder.mImageView= (ImageView)convertView.findViewById(R.id.list_img);

            convertView.setTag(mViewholder);
        }
        else{
            mViewholder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(mContext).load(mDatas.get(position).getImgUrl()).into(mViewholder.mImageView);
        mViewholder.mTextView.setText(mDatas.get(position).getName());

        return convertView;
    }

    public class ViewHolder{
        int mId;
        TextView mTextView;
        ImageView mImageView;
    }

}
