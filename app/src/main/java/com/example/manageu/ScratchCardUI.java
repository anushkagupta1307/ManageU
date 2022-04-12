package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScratchCardUI extends AppCompatActivity {

    public static Button backbtn;
    public static ImageView scratchimage;
    public static TextView scratchtextsss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card_ui);

        backbtn= findViewById(R.id.backtoproductivity);
        scratchimage=findViewById(R.id.scratchcardimage);
        scratchtextsss=findViewById(R.id.scratchcardtext);

        if(ProgressFragment.roleassigned==1)
        {
            if(ProgressFragment.studyprodpassed>=60f)
            {
                scratchimage.setImageResource(R.drawable.reward);
                scratchtextsss.setText("You Get a Cashback:) of 1000 Rupees for the hard work.");
            }
            else{

                scratchimage.setImageResource(R.drawable.tip);
                scratchtextsss.setText("Not every race is Sprint!! PLease be steady in this marathon");
            }
        }
        else if(ProgressFragment.roleassigned==2)
        {


            if(ProgressFragment.workprodpassed>=60f || ProgressFragment.meetingprodpassed>=60f )
            {
                scratchimage.setImageResource(R.drawable.reward);
                scratchtextsss.setText("Award yourself with a great outing for all the Harwork :)");
            }
            else{
                scratchimage.setImageResource(R.drawable.tip);
                scratchtextsss.setText("Mid-Week Blues are tiring, but Keep Going!!");

            }

        }
        else if(ProgressFragment.roleassigned==3)
        {
            if(ProgressFragment.cookingprodpassed>=60f || ProgressFragment.cleaningprodpassed>=60f )
            {
                scratchimage.setImageResource(R.drawable.reward);
                scratchtextsss.setText("Award yourself with a great meal for all the Harwork :)");
            }
            else{
                scratchimage.setImageResource(R.drawable.tip);
                scratchtextsss.setText("Handling toughest job on earth is not easy, but Keep Going!!");

            }
        }





        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ScratchCardUI.this, TaskActivity.class);
//                ScratchCardUI.this.startActivity(intent);
                ScratchCardUI.this.onBackPressed();
            }
        });

    }
}