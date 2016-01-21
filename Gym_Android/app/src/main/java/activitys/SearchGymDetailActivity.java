package activitys;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baemin.sun.gym_android.R;
import com.baemin.sun.Adapters.ViewPagerAdapter;

public class SearchGymDetailActivity extends AppCompatActivity {

    int id;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gym_detail);

        TextView tv = (TextView)this.findViewById(R.id.tv_search_gym_detail);
        tv.setOnClickListener(listner);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("id") + 1;

        pager = (ViewPager)findViewById(R.id.vp_search_gym_detail);

        //ViewPager에 설정할 Adapter 객체 생성
        //ListView에서 사용하는 Adapter와 같은 역할.
        //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
        //PagerAdapter를 상속받은 CustomAdapter 객체 생성
        //CustomAdapter에게 LayoutInflater 객체 전달
        ViewPagerAdapter adapter= new ViewPagerAdapter(getLayoutInflater());

        //ViewPager에 Adapter 설정
        pager.setAdapter(adapter);

    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener listner = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),ReviewsActivity.class);
            startActivity(intent);

        }
    };


}