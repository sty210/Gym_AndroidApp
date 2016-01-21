package activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baemin.sun.Adapters.GymRowDataAdapter;
import com.baemin.sun.Adapters.GymsRowData;
import com.baemin.sun.gym_android.R;
import com.baemin.sun.Adapters.RowData;
import com.baemin.sun.Adapters.RowDataAdapter;

import java.util.ArrayList;
import java.util.List;

import network.Exercises;
import network.GymRequest;
import network.Gyms;
import network.TyEx;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchGymActivity extends AppCompatActivity {

    private ArrayList<GymsRowData> mDatas = new ArrayList<GymsRowData>();
    private ListView mListview;
    private List<Gyms> mGymsList;
    private TextView mSearchGymBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gym);

        mSearchGymBtn = (TextView)findViewById(R.id.searchgymbtn);
        //mSearchGymBtn.setOnClickListener(listner);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.0.158:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GymRequest service = retrofit.create(GymRequest.class);
        Call<List<Gyms>> gyms = service.listGyms();

        gyms.enqueue(new Callback<List<Gyms>>() {
            @Override
            public void onResponse(Response<List<Gyms>> response, Retrofit retrofit) {
                mGymsList = response.body();

                for (Gyms gym : mGymsList) {
                    /*Log.i("gym_id", gym.getId() + "");
                    Log.i("gym_nm", gym.getGym_nm());
                    Log.i("gym_img", gym.getGym_img());
                    Log.i("gym_adr", gym.getGym_adr());
                    Log.i("gym_tel", gym.getGym_tel());
                    Log.i("gym_tel", gym.getRgn_cd()+"");
                    */

                    mDatas.add(new GymsRowData(gym.getId(), gym.getGym_nm(), gym.getGym_img(), gym.getGym_adr(), gym.getGym_tel(), gym.getRgn_cd()));

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        mListview= (ListView)findViewById(R.id.lv_search_gym);

        GymRowDataAdapter gymAdapter= new GymRowDataAdapter(getApplicationContext(), getLayoutInflater() , mDatas);

        mListview.setAdapter(gymAdapter);
        mListview.setOnItemClickListener(listener);
    }

    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {
        //첫번째 파라미터 : 클릭된 아이템을 보여주고 있는 AdapterView 객체(여기서는 ListView객체)
        //두번째 파라미터 : 클릭된 아이템 뷰
        //세번째 파라미터 : 클릭된 아이템의 위치(ListView이 첫번째 아이템(가장위쪽)부터 차례대로 0,1,2,3.....)
        //네번재 파리미터 : 클릭된 아이템의 아이디(특별한 설정이 없다면 세번째 파라이터인 position과 같은 값)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(),SearchGymDetailActivity.class);
            intent.putExtra("gymId",mDatas.get(position).getId());
            intent.putExtra("gymName",mDatas.get(position).getGymName());
            intent.putExtra("gymAddress",mDatas.get(position).getGymAddress());
            intent.putExtra("gymImgUrl",mDatas.get(position).getImgUrl());
            intent.putExtra("gymTel",mDatas.get(position).getGymTel());
            startActivity(intent);
        }
    };

}
