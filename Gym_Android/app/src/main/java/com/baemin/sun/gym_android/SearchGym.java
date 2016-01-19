package com.baemin.sun.gym_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchGym extends AppCompatActivity {

    ArrayList<RowData> datas = new ArrayList<RowData>();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gym);

        datas.add(new RowData(1, "태준헬스장", R.drawable.gym1));
        datas.add(new RowData(2, "용대헬스장", R.drawable.gym2));
        datas.add( new RowData(3, "태양헬스장", R.drawable.gym3));
        datas.add( new RowData(4, "여은헬스장", R.drawable.gym4));
        datas.add( new RowData(5, "은여헬스장", R.drawable.gym5));
        datas.add( new RowData(6, "양태헬스장", R.drawable.gym6));
        datas.add( new RowData(7, "대용헬스장", R.drawable.gym7));
        datas.add( new RowData(8, "준태헬스장", R.drawable.gym8));
        datas.add( new RowData(9, "썬스장", R.drawable.gym9));

        listview= (ListView)findViewById(R.id.lv_search_gym);

        RowDataAdapter adapter= new RowDataAdapter( getLayoutInflater() , datas);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(listener);
    }
    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {
        //첫번째 파라미터 : 클릭된 아이템을 보여주고 있는 AdapterView 객체(여기서는 ListView객체)
        //두번째 파라미터 : 클릭된 아이템 뷰
        //세번째 파라미터 : 클릭된 아이템의 위치(ListView이 첫번째 아이템(가장위쪽)부터 차례대로 0,1,2,3.....)
        //네번재 파리미터 : 클릭된 아이템의 아이디(특별한 설정이 없다면 세번째 파라이터인 position과 같은 값)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(),SearchGymDetail.class);
            intent.putExtra("id",position);
            startActivity(intent);
        }
    };

}
