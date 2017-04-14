package com.lovemaiqi.comp423.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lovemaiqi.comp423.R;
import com.lovemaiqi.comp423.Tools.MakePlan;

import java.util.Date;

public class PlanSetting extends ActionBarActivity {
    private Button btn_back;
    private Button btn_start_date;
    private Button btn_end_date;
    private Button btn_makeplan;
    private Button btn_use_remmendation;
    private SeekBar seekBarNatural;
    private SeekBar seekBarCultural;
    private SeekBar seekBarPace;
    private SeekBar seekBarBudget;
    private TextView textPace,textBudget;
    private Date startDate,endDate;
    public boolean useRecomendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_setting);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_setting, menu);
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
        btn_start_date = (Button)findViewById(R.id.btn_startdate);
        btn_end_date = (Button)findViewById(R.id.btn_enddate);
        btn_makeplan = (Button) findViewById(R.id.plan_setting_makeplan);
        btn_back = (Button)findViewById(R.id.setting_btn_back);
        btn_use_remmendation = (Button)findViewById(R.id.btn_use_recommendation);
        useRecomendation = false;

        final Date currentTime = new Date(System.currentTimeMillis());
        startDate = currentTime;
        endDate = currentTime;
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PlanSetting.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        btn_start_date.setText(String.format("%d/%d/%d",dayOfMonth,monthOfYear+1,year));
                        startDate = new Date(year,monthOfYear,dayOfMonth);
                    }
                },2015,3,20).show();
            }
        });

        btn_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PlanSetting.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        btn_end_date.setText(String.format("%d/%d/%d",dayOfMonth,monthOfYear+1,year));
                        endDate = new Date(year,monthOfYear,dayOfMonth);
                    }
                },2015,3,20).show();
            }
        });

        btn_makeplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numDays = (int)(endDate.getTime() - startDate.getTime())/(1000*60*60*24);
                if(0 < numDays && numDays < 4){
                    Intent ii = getIntent();
                    String city = ii.getStringExtra("city");
                    MakePlan makePlan = new MakePlan(PlanSetting.this,city,startDate,endDate,seekBarNatural.getProgress(),seekBarCultural.getProgress(),seekBarPace.getProgress(),seekBarBudget.getProgress());
                    int id = makePlan.makePlan();
                    Intent i = new Intent(PlanSetting.this,PlanResult.class);
                    i.putExtra("planId",id);
                    startActivity(i);
                }else if(numDays >=4){
                    Toast.makeText(PlanSetting.this,"Not Support More Than 4 days yet!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PlanSetting.this,"Please choose correct start and end date!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_use_remmendation.setText("NO");
        btn_use_remmendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_use_remmendation.getText().equals("NO")){
                    btn_use_remmendation.setText("YES");
                }else if(btn_use_remmendation.getText().equals("YES")){
                    btn_use_remmendation.setText("NO");
                }
            }
        });

        seekBarNatural = (SeekBar)findViewById(R.id.seekbar_natural);
        seekBarCultural = (SeekBar)findViewById(R.id.seekbar_cultural);
        seekBarPace = (SeekBar)findViewById(R.id.seekbar_pace);
        seekBarPace.setProgress(50);
        seekBarBudget = (SeekBar)findViewById(R.id.seekbar_budget);
        seekBarBudget.setProgress(66);
        textPace = (TextView)findViewById(R.id.text_pace);
        textBudget = (TextView)findViewById(R.id.text_budget);

        seekBarPace.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBarPace.getProgress() <= 33){
                    seekBarPace.setProgress(0);
                    textPace.setText("Low");
                }else if(seekBarPace.getProgress() <=66){
                    seekBarPace.setProgress(50);
                    textPace.setText("Medium");
                }else {
                    seekBarPace.setProgress(100);
                    textPace.setText("High");
                }
            }
        });

        seekBarBudget.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBarBudget.getProgress() <= 25){
                    seekBarBudget.setProgress(0);
                    textBudget.setText("Free");
                }else if(seekBarBudget.getProgress() <= 50){
                    seekBarBudget.setProgress(33);
                    textBudget.setText("Low");
                }else if(seekBarBudget.getProgress() <= 75){
                    seekBarBudget.setProgress(66);
                    textBudget.setText("Medium");
                }else{
                    seekBarPace.setProgress(100);
                    textPace.setText("High");
                }
            }
        });

    }
}
