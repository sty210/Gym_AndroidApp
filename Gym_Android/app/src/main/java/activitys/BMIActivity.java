package activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.baemin.sun.gym_android.R;
import com.baemin.sun.Adapters.RowData;
import com.baemin.sun.Adapters.RowDataAdapter;

import java.util.ArrayList;
import java.util.List;

import network.Exercises;
import network.TyEx;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BMIActivity extends AppCompatActivity {

    private  ArrayList<RowData> mDatas;
    private  ListView mListView;
    private String mParamType;
    private int mParamId;
    private TextView mOkBtn;
    private EditText mEtWeight;
    private EditText mEtHeight;
    private Double mWeight;
    private Double mHeight;
    private Double mBMI;
    private String mIp = "http://192.168.0.4";
    private String mPort = "3000";
    private RowDataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        mEtWeight = (EditText)findViewById(R.id.et_weight);
        mEtHeight = (EditText)findViewById(R.id.et_height);
        mOkBtn = (TextView)findViewById(R.id.tv_bmi);
        mOkBtn.setOnClickListener(btnClickListner);


        mListView= (ListView)findViewById(R.id.lv_bmi);
        mDatas = new ArrayList<RowData>();
        mAdapter = new RowDataAdapter(getBaseContext(), getLayoutInflater() , mDatas);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(itemClickListener);
    }


    private View.OnClickListener btnClickListner = new View.OnClickListener() {
        public void onClick(View v) {
            mWeight = Double.parseDouble(mEtWeight.getText().toString());
            mHeight = Double.parseDouble(mEtHeight.getText().toString());
            mHeight /= 100;
            //BMI지수 계산
            mBMI = mWeight / (mHeight * mHeight);

            //대한 비만학회 기준 23이상이면 과체중, 많이 심하면 비만
            //mParamId=1은 근력운동 mParamId=2는 유산소 운동 목록을 가져옴
            if(mBMI < 23){
                mParamId = 1;
            }else{
                mParamId = 2;
            }

            mParamType = "exty";

            startLogic();

            //그냥은 안되고 OS의 Handler를 이용하여 딜레이 후 notify해야지만 listview가 정상적으로 refresh되는 현상 발견
            //아마도 사진 업로드에 시간이 너무 많이 걸려서 그런 것으로.. 예상.
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }
            }, 1000);

            //키보드 내려주기 센스!
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


        }
    };

    public void startLogic(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mIp + ":" + mPort)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TyEx service = retrofit.create(TyEx.class);

        Call<List<List<Exercises>>> exercises = service.listExercises(mParamType, mParamId);

        //data를 비워준다. 연속으로 몇번 더 검사 해볼 수도 있으니.. 겹치는거 방지.
        mDatas.clear();

        exercises.enqueue(new Callback<List<List<Exercises>>>() {
            @Override
            public void onResponse(Response<List<List<Exercises>>> response, Retrofit retrofit) {
                List<List<Exercises>> exercisesList = response.body();

                for (List<Exercises> ex : exercisesList) {
                    mDatas.add(new RowData(ex.get(0).id, ex.get(1).cd_nm, ex.get(0).ex_mth_img, ex.get(0).ex_mth_ep));
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
            });

        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            //첫번째 파라미터 : 클릭된 아이템을 보여주고 있는 AdapterView 객체(여기서는 ListView객체)
            //두번째 파라미터 : 클릭된 아이템 뷰
            //세번째 파라미터 : 클릭된 아이템의 위치(ListView이 첫번째 아이템(가장위쪽)부터 차례대로 0,1,2,3.....)
            //네번재 파리미터 : 클릭된 아이템의 아이디(특별한 설정이 없다면 세번째 파라이터인 position과 같은 값)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ExerciseExplanationActivity.class);
                intent.putExtra("exId", mDatas.get(position).getId());
                intent.putExtra("exMth", mDatas.get(position).getExmth());
                intent.putExtra("exNm", mDatas.get(position).getName());
                intent.putExtra("imgUrl",mDatas.get(position).getImgUrl());
            startActivity(intent);
        }
    };

}
