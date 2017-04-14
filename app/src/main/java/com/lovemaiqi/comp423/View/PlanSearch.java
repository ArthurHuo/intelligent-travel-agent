package com.lovemaiqi.comp423.View;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.lovemaiqi.comp423.R;

import java.util.ArrayList;

public class PlanSearch extends ActionBarActivity {
    private ArrayList<String> selectcities;
    private Button btn_next;
    private Button btn_reco_1,btn_reco_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_search);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init(){
        selectcities = new ArrayList<>();
//        btn_next = (Button)findViewById(R.id.plan_search_makeplan);
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(PlanSearch.this,PlanSetting.class);
//                startActivity(i);
//            }
//        });
        btn_reco_1 = (Button)findViewById(R.id.search_btn_rec_1);
        btn_reco_1.setText("Wellington");
        btn_reco_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectcities.add(btn_reco_1.getText().toString());
                String city = btn_reco_1.getText().toString();
                Intent i = new Intent(PlanSearch.this,PlanSetting.class);

                i.putExtra("city",city);
//                i.putExtra("resutlId",1);
                startActivity(i);
            }
        });

        btn_reco_2 = (Button)findViewById(R.id.search_btn_rec_2);
        btn_reco_2.setText("Beijing");
        btn_reco_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectcities.add(btn_reco_2.getText().toString());
                String city = btn_reco_2.getText().toString();
                Intent i = new Intent(PlanSearch.this,PlanSetting.class);

                i.putExtra("city",city);
//                i.putExtra("resutlId",1);
                startActivity(i);
            }
        });
    }



}
