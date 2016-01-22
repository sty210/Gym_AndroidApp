package activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.baemin.sun.Adapters.GymRowDataAdapter;
import com.baemin.sun.Adapters.GymsRowData;
import com.baemin.sun.gym_android.R;

import java.util.ArrayList;
import java.util.List;

import network.GymRequest;
import network.Gyms;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchGymActivity extends AppCompatActivity {

    private ArrayList<GymsRowData> mDatas;
    private ListView mListview;
    private TextView mSearchGymBtn;
    private String mInput;
    private EditText mEtSearchGym;
    private String mIp = "http://192.168.0.4";
    private String mPort = "3000";
    private GymRowDataAdapter mGymAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gym);

        mEtSearchGym = (EditText) findViewById(R.id.et_searchgym);
        mSearchGymBtn = (TextView)findViewById(R.id.searchgymbtn);
        mSearchGymBtn.setOnClickListener(clickListner);

        mDatas = new ArrayList<GymsRowData>();
        mInput = mEtSearchGym.getText().toString();

        mListview= (ListView)findViewById(R.id.lv_search_gym);
        mGymAdapter = new GymRowDataAdapter(getApplicationContext(), getLayoutInflater() , mDatas);
        mListview.setAdapter(mGymAdapter);
        mListview.setOnItemClickListener(listener);

        //가장 처음에는 전체 리스트를 한번에 보여준다
        updateGymList();
    }

    private View.OnClickListener clickListner = new View.OnClickListener() {
        public void onClick(View v) {

            updateGymList();

            //그냥은 안되고 OS의 Handler를 이용하여 딜레이 후 notify해야지만 listview가 정상적으로 refresh되는 현상 발견
            //아마도 사진 업로드에 시간이 너무 많이 걸려서 그런 것으로.. 예상.
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mGymAdapter.notifyDataSetChanged();
                }
            }, 1000);

            //키보드 내려주기 센스!
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    };

    public void updateGymList(){

        if(mEtSearchGym.getText().toString().isEmpty()){
            mInput = "empty";
        }else{
            mInput = mEtSearchGym.getText().toString();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mIp + ":" + mPort)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GymRequest service = retrofit.create(GymRequest.class);
        Call<List<Gyms>> gyms = service.listGyms();

        //data를 비워준다. 연속으로 몇번 더 검사 해볼 수도 있으니.. 겹치는거 방지.
        mDatas.clear();

        gyms.enqueue(new Callback<List<Gyms>>() {
            @Override
            public void onResponse(Response<List<Gyms>> response, Retrofit retrofit) {
                List<Gyms> gymsList = response.body();
                for (Gyms gym : gymsList) {
                    if (mInput.equals("empty")) {
                        mDatas.add(new GymsRowData(gym.getId(), gym.getGym_nm(), gym.getGym_img(), gym.getGym_adr(), gym.getGym_tel(), gym.getRgn_cd(), gym.getGym_ep()));
                    } else {
                        if (mInput.equals(gym.getGym_nm())) {
                            mDatas.add(new GymsRowData(gym.getId(), gym.getGym_nm(), gym.getGym_img(), gym.getGym_adr(), gym.getGym_tel(), gym.getRgn_cd(), gym.getGym_ep()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

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
            intent.putExtra("gymExplanation",mDatas.get(position).getGymExplanation());

            startActivity(intent);
        }
    };

}
