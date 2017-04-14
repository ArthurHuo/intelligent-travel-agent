package com.lovemaiqi.comp423.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.lovemaiqi.comp423.Db.Db;
import com.lovemaiqi.comp423.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PlanResult extends ActionBarActivity {
    private String city;
    private int result_id;
    private SimpleCursorAdapter adapter;
    private SimpleAdapter sAdapter;
    private Db db;
    private SQLiteDatabase dbRead,dbWrite;
    private ListView lv;
    private ArrayList<HashMap<String,Object>> listData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_result);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_result, menu);
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

    public void init(){
        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        Intent i = getIntent();
//        city = i.getStringExtra("city");

        result_id = i.getIntExtra("planId",0);
//        System.out.println(result_id);
        //test
//        result_id = 1;
        fillList();
        fillAdapter();
//        setItemListener();

//        createDemo();
        if(dbRead.isOpen()){
            dbRead.close();
        }
    }
    public void setItemListener(){
        lv = (ListView)findViewById(R.id.resultlist);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Cursor c = adapter.getCursor();
//                c.moveToPosition(position);
//                int selectId = c.getInt(c.getColumnIndex(Db.COLUMN_NAME_ID));
//                Intent intent =new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=-41.290450,174.782092&daddr=-41.290060,174.753553"));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                        & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                intent.setClassName("com.google.android.apps.maps",
//                        "com.google.android.maps.MapsActivity");
//                startActivity(intent);
                Intent i = new Intent(PlanResult.this, PlanMap.class);
                startActivity(i);
            }
        });
    }

    public void createDemo(){
        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        lv = (ListView)findViewById(R.id.resultlist);

        adapter = new SimpleCursorAdapter(this,R.layout.result_view_place,null,new String[]{Db.COLUMN_NAME_PLACES_NAME,Db.COLUMN_NAME_PLACES_TYPE, Db.COLUMN_NAME_PLACES_PRICE,Db.COLUMN_NAME_PLACES_DESC,Db.COLUMN_NAME_PLACES_IMAGE},
                new int[]{R.id.result_place_name,R.id.result_place_tag_1,R.id.result_place_price,R.id.result_place_desc,R.id.result_place_pic});
        lv.setAdapter(adapter);

        refreshListView();

    }
    public void refreshListView(){
        Cursor c = dbRead.query(Db.TABLE_NAME_PLACES,null, null, null, null, null, null);
        adapter.changeCursor(c);
    }

    public void fillList(){
        int tempday = 1;
        listData = new ArrayList<HashMap<String,Object>>();

        Cursor c = dbRead.query(Db.TABLE_NAME_PLANS,null, Db.COLUMN_NAME_ID+"=?", new String[]{result_id+""}, null, null, null);
        c.moveToFirst();
        if(c.moveToFirst()){
            String tvName = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLANS_NAME));
            String tvCity = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLANS_CITY));
            String tvCost = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLANS_COST));
            TextView tv = (TextView)findViewById(R.id.result_plan_name);
            tv.setText(tvName);
            tv = (TextView)findViewById(R.id.result_plan_city);
            tv.setText(tvCity);
            tv = (TextView)findViewById(R.id.result_plan_cost);
            tv.setText(tvCost);
            String strResult = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLANS_PLAN));
//            System.out.println(strResult);
//            String name = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_NAME));
            String[] arrResult = strResult.split(",");
            for(int i = 0; i < arrResult.length; i++){
                int id;
                if(arrResult[i] != ""){
                    id = Integer.valueOf(arrResult[i]);
                }else {
                    continue;
                }

//                System.out.println(id);

                if(id == 0){
                    tempday = tempday + 1;

                }
                c = dbRead.query(Db.TABLE_NAME_PLACES,null, Db.COLUMN_NAME_ID+"=?", new String[]{id+""}, null, null, null);
                c.moveToFirst();
                if(c.moveToFirst()){
                    String day = "Day"+" "+ tempday;
                    String name = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_NAME));
                    String type = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_TYPE));
                    String price = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_PRICE));
                    String desc = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_DESC));
                    String image = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_IMAGE));
                    double avgtime = c.getDouble(c.getColumnIndex(Db.COLUMN_NAME_PLACES_AVGTIME));
                    double rating = c.getDouble(c.getColumnIndex(Db.COLUMN_NAME_PLACES_RATING));
                    String att = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_ATTRIBUTE));

//                    System.out.println(name);
                    HashMap<String, Object> map = new HashMap<String, Object>();

                    String[] atts = att.split(",");
//                    for(int k = 0; k< atts.length; k++){
//                        if(atts[]){
//
//                        }
//                    }
                    if(Integer.valueOf(atts[0])>=Integer.valueOf(atts[1])){
                        map.put("TypeImg",R.drawable.icon_nature);
                    }else{
                        map.put("TypeImg",R.drawable.icon_museum);
                    }

                    map.put("Day",day);
                    map.put(Db.COLUMN_NAME_PLACES_NAME,name);
                    map.put(Db.COLUMN_NAME_PLACES_TYPE,type);
                    map.put(Db.COLUMN_NAME_PLACES_PRICE,price);
                    map.put(Db.COLUMN_NAME_PLACES_DESC,desc);
                    map.put(Db.COLUMN_NAME_PLACES_IMAGE,image);
                    map.put(Db.COLUMN_NAME_PLACES_AVGTIME,avgtime);
                    map.put(Db.COLUMN_NAME_PLACES_RATING,rating);

                    listData.add(map);
                }

            }
        }

//        return listData;
    }

    public void fillAdapter(){
        lv = (ListView)findViewById(R.id.resultlist);
        sAdapter = new SimpleAdapter(this,listData,R.layout.result_view_place,new String[]{Db.COLUMN_NAME_PLACES_NAME,Db.COLUMN_NAME_PLACES_TYPE, Db.COLUMN_NAME_PLACES_PRICE,Db.COLUMN_NAME_PLACES_DESC,Db.COLUMN_NAME_PLACES_IMAGE,Db.COLUMN_NAME_PLACES_AVGTIME,"Day",Db.COLUMN_NAME_PLACES_RATING,"TypeImg"},
                new int[]{R.id.result_place_name,R.id.result_place_tag_1,R.id.result_place_price,R.id.result_place_desc,R.id.result_place_pic,R.id.result_avgtime,R.id.result_day,R.id.result_rating,R.id.result_place_type});
        sAdapter.setViewBinder(new TypeBinder());
        lv.setAdapter(sAdapter);
    }
    class TypeBinder implements SimpleAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if(view.getId() == R.id.result_rating){
                double dd = (double) data;
                String ss = String.valueOf(dd);
                float ratingValue = Float.valueOf(ss);
                RatingBar ratingBar = (RatingBar) view;
                ratingBar.setRating(ratingValue);
                return true;
            }
            return false;
        }
    }
}
