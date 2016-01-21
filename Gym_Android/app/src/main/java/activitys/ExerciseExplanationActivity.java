package activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.baemin.sun.gym_android.R;
import com.squareup.picasso.Picasso;

public class ExerciseExplanationActivity extends AppCompatActivity {

    private int mId;
    private String mExMth;
    private String mImgUrl;
    private String mName;
    private ImageView mImage;
    private TextView mExMthod;
    private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_explanation);

        Intent intent = getIntent();
        mId = intent.getExtras().getInt("id");
        mExMth = intent.getExtras().getString("exMth");
        mName = intent.getExtras().getString("exNm");
        mImgUrl = intent.getExtras().getString("imgUrl");

        mTitle = (TextView)this.findViewById(R.id.tv_exep_nm);
        mImage =(ImageView)this.findViewById(R.id.iv_exep);
        mExMthod = (TextView)this.findViewById(R.id.tv_exep_mth);

        mTitle.setText(mName);
        Picasso.with(getApplicationContext()).load(mImgUrl).into(mImage);
        mExMthod.setText("운동법 : " + mExMth);
    }
}
