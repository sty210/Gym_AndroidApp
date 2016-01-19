package com.baemin.sun.gym_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseExplanation extends AppCompatActivity {

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_explanation);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("id") + 1;

        ImageView image =(ImageView)this.findViewById(R.id.iv_exep);
        TextView tv = (TextView)this.findViewById(R.id.tv_exep);
        tv.setText("운동법 : 그냥 열심히 하면 다 됩니다. ^^");

        if(id==1){image.setImageResource(R.drawable.ex1);}
        else if(id==2){image.setImageResource(R.drawable.ex2);}
        else if(id==3){image.setImageResource(R.drawable.ex3);}
        else if(id==4){image.setImageResource(R.drawable.ex4);}
        else if(id==5){image.setImageResource(R.drawable.ex5);}
        else if(id==6){image.setImageResource(R.drawable.ex6);}
        else if(id==7){image.setImageResource(R.drawable.ex7);}
        else if(id==8){image.setImageResource(R.drawable.ex8);}
        else if(id==9){image.setImageResource(R.drawable.ex9);}
    }
}
