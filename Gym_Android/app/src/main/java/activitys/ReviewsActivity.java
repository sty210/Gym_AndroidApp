package activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baemin.sun.gym_android.R;

public class ReviewsActivity extends AppCompatActivity {

    private int mGymId;
    private String mGymName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        Intent intent = getIntent();
        mGymId = intent.getExtras().getInt("gymId");
        mGymName = intent.getExtras().getString("gymName");


    }
}
