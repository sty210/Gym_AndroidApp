package com.baemin.sun.gym_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchExerciseByPart extends AppCompatActivity {

    ArrayList<RowData> datas = new ArrayList<RowData>();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise_by_part);

        datas.add(new RowData(1, "벤치프레스", R.drawable.ex1));
        datas.add(new RowData(2, "덤벨킥백", R.drawable.ex2));
        datas.add( new RowData(3, "런지", R.drawable.ex3));
        datas.add( new RowData(4, "스쿼트", R.drawable.ex4));
        datas.add( new RowData(5, "니업", R.drawable.ex5));
        datas.add( new RowData(6, "데드리프트", R.drawable.ex6));
        datas.add( new RowData(7, "로프크런치", R.drawable.ex7));
        datas.add( new RowData(8, "풀업", R.drawable.ex8));
        datas.add( new RowData(9, "풀다운", R.drawable.ex9));

        listview= (ListView)findViewById(R.id.lv_search_exercise_by_part);

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
            Intent intent = new Intent(getApplicationContext(),ExerciseExplanation.class);
            intent.putExtra("id",position);
            startActivity(intent);
        }
    };
}
