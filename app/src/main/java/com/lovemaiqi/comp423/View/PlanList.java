package com.lovemaiqi.comp423.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lovemaiqi.comp423.Db.Db;
import com.lovemaiqi.comp423.R;

public class PlanList extends ActionBarActivity {
    private SimpleCursorAdapter adapter;
    private Db db;
    private SQLiteDatabase dbRead,dbWrite;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_list, menu);
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

        lv = (ListView)findViewById(R.id.plan_list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = adapter.getCursor();
                c.moveToPosition(position);
                int selectId = c.getInt(c.getColumnIndex(Db.COLUMN_NAME_ID));
                Intent i = new Intent(PlanList.this,PlanResult.class);
                i.putExtra("planId",selectId);
                startActivity(i);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(PlanList.this).setTitle("Caution").setMessage("Do you want to delete this plan?").setNegativeButton("No", null).setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Cursor c = adapter.getCursor();
                        c.moveToPosition(position);
                        int selectId = c.getInt(c.getColumnIndex(Db.COLUMN_NAME_ID));

//                        dbWrite = db.getWritableDatabase();
                        dbWrite.delete(Db.TABLE_NAME_PLANS,Db.COLUMN_NAME_ID+"=?",new String[]{selectId+""});
//                        dbWrite.close();
                        refreshListView();
                    }
                }).show();

                return true;
            }
        });

        adapter = new SimpleCursorAdapter(this,R.layout.result_view_plan,null,new String[]{Db.COLUMN_NAME_PLANS_NAME,Db.COLUMN_NAME_PLANS_CITY,Db.COLUMN_NAME_PLANS_COST},
                new int[]{R.id.list_plan_name,R.id.list_plan_city,R.id.list_plan_cost});
        lv.setAdapter(adapter);

        refreshListView();

//        if(dbWrite.isOpen()){
//            dbWrite.close();
//        }
//        if(dbRead.isOpen()){
//            dbRead.close();
//        }
    }
    public void refreshListView(){

        Cursor c = dbRead.query(Db.TABLE_NAME_PLANS,null, null, null, null, null, null);


        adapter.changeCursor(c);
    }
}
