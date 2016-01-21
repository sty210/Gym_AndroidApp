package activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class SearchExerciseByPartActivity extends AppCompatActivity {

    private ArrayList<RowData> mDatas = new ArrayList<RowData>();
    private ListView mListview;
    private List<List<Exercises>> mExercisesList;
    private String mParamType;
    private int mParamId;
    private TextView mOkBtn;
    private EditText mEtPart;
    private String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise_by_part);
        mEtPart = (EditText)findViewById(R.id.et_part);
        mOkBtn = (TextView)findViewById(R.id.tv_part);
        mOkBtn.setOnClickListener(listner);
    }

    private View.OnClickListener listner = new View.OnClickListener() {
        public void onClick(View v) {
            input = mEtPart.getText().toString();

            if(input.equals("가슴")){
                mParamId = 1;
            }else if(input.equals("팔")){
                mParamId = 2;
            }else if(input.equals("다리")){
                mParamId = 3;
            }else if(input.equals("복근")){
                mParamId = 4;
            }else if(input.equals("등")){
                mParamId = 5;
            }else if(input.equals("전신")){
                mParamId = 6;
            }

            mParamType = "expt";

            //data를 비워준다. 연속으로 몇번 더 입력 해볼 수도 있으니.. 겹치는거 방지.
            mDatas = new ArrayList<RowData>();
            startLogic();
        }
    };

    void startLogic(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.0.158:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TyEx service = retrofit.create(TyEx.class);

        Call<List<List<Exercises>>> exercises = service.listExercises(mParamType, mParamId);

        exercises.enqueue(new Callback<List<List<Exercises>>>() {
            @Override
            public void onResponse(Response<List<List<Exercises>>> response, Retrofit retrofit) {
                mExercisesList = response.body();

                for (List<Exercises> ex : mExercisesList) {
                    Log.i("ex_id", ex.get(0).id + "");
                    Log.i("ex_mth_img", ex.get(0).ex_mth_img);
                    Log.i("ex_mth_ep", ex.get(0).ex_mth_ep);
                    Log.i("cd_nm", ex.get(1).cd_nm);

                    mDatas.add(new RowData(ex.get(0).id, ex.get(1).cd_nm, ex.get(0).ex_mth_img, ex.get(0).ex_mth_ep));

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });




        mListview= (ListView)findViewById(R.id.lv_search_exercise_by_part);

        RowDataAdapter adapter= new RowDataAdapter(getApplicationContext(), getLayoutInflater() , mDatas);

        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(listener);
    }
    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {
        //첫번째 파라미터 : 클릭된 아이템을 보여주고 있는 AdapterView 객체(여기서는 ListView객체)
        //두번째 파라미터 : 클릭된 아이템 뷰
        //세번째 파라미터 : 클릭된 아이템의 위치(ListView이 첫번째 아이템(가장위쪽)부터 차례대로 0,1,2,3.....)
        //네번재 파리미터 : 클릭된 아이템의 아이디(특별한 설정이 없다면 세번째 파라이터인 position과 같은 값)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(),ExerciseExplanationActivity.class);
            intent.putExtra("exId",mDatas.get(position).getId());
            intent.putExtra("exMth",mDatas.get(position).getExmth());
            intent.putExtra("exNm",mDatas.get(position).getName());
            intent.putExtra("imgUrl",mDatas.get(position).getImgUrl());
            startActivity(intent);
        }
    };
}
