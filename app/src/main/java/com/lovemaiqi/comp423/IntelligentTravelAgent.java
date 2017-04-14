package com.lovemaiqi.comp423;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lovemaiqi.comp423.Db.Db;
import com.lovemaiqi.comp423.View.PlanList;
import com.lovemaiqi.comp423.View.PlanMap;
import com.lovemaiqi.comp423.View.PlanSearch;
import com.lovemaiqi.comp423.View.PlanSetting;

import java.util.Random;


public class IntelligentTravelAgent extends ActionBarActivity {
    private Button btn_makeplans;
    private Button btn_showplans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent_travel_agent);
        init();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intelligent_travel_agent, menu);
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
        ImageView main_city = (ImageView)findViewById(R.id.main_city);
        ImageView btn_search = (ImageView)findViewById(R.id.btn_search);

        ImageView main_reco1 = (ImageView)findViewById(R.id.main_recommendation_1);
        ImageView main_reco2 = (ImageView)findViewById(R.id.main_recommendation_2);
        ImageView main_reco3 = (ImageView)findViewById(R.id.main_recommendation_3);
        ImageView main_reco4 = (ImageView)findViewById(R.id.main_recommendation_4);

        main_city.setImageResource(R.drawable.img_wellington_top);
//        btn_search.setImageResource(R.drawable.btn_search);

        main_reco1.setImageResource(R.drawable.img_tepapa_reco);
        main_reco2.setImageResource(R.drawable.img_zealandia_reco);
        main_reco3.setImageResource(R.drawable.img_botanicalgarden_reco);
        main_reco4.setImageResource(R.drawable.img_wellingtonzoo_reco);

        btn_makeplans = (Button)findViewById(R.id.main_makeplans);
        btn_makeplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntelligentTravelAgent.this, PlanSearch.class);
//                Intent i = new Intent(IntelligentTravelAgent.this,PlanMap.class);
                startActivity(i);
            }
        });

        btn_showplans = (Button)findViewById(R.id.main_existingplans);
        btn_showplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntelligentTravelAgent.this, PlanList.class);
                startActivity(i);
            }
        });

//        createDemo();
//        createPlans();

    }
    public void createDemo(){
        Db db = new Db(this);
        SQLiteDatabase dbWrite = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Zealandia");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
        cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
        cv.put(Db.COLUMN_NAME_PLACES_RATING,4.4);
        cv.put(Db.COLUMN_NAME_PLACES_PRICE,"$17.50");
        cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"53 Waiapu Road Karori Wellington 6012");
        cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,6);
        cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"9am-5pm");
        cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.290060);
        cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.753553);
        cv.put(Db.COLUMN_NAME_PLACES_DESC,"Just 10 minutes from central Wellington, Zealandia is a must-see eco-attraction and groundbreaking restoration project: a nature lover's paradise and a sanctuary by the city!");

        dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Wellington Zoo");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"50,50");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
        cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Zoo");
        cv.put(Db.COLUMN_NAME_PLACES_RATING,4.3);
        cv.put(Db.COLUMN_NAME_PLACES_PRICE,"$21");
        cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"200 Daniell St,Newtown Wellington 6021");
        cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,5);
        cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"9.30am-5pm");
        cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.319527);
        cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.785888);
        cv.put(Db.COLUMN_NAME_PLACES_DESC,"WELCOME TO WELLINGTON ZOO – THE BEST LITTLE ZOO IN THE WORLD. EXPLORE THE ZOO ONLINE OR VISIT NEW ZEALAND’S FIRST ZOO TO GET UP CLOSE AND PERSONAL TO NATIVE TREASURES AND ENDANGERED EXOTIC ANIMALS.");

        dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Mount Victoria");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
        cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
        cv.put(Db.COLUMN_NAME_PLACES_RATING,4.5);
        cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
        cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"49 Lookout Rd Hataitai Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,3);
        cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"");
        cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.296121);
        cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.794387);
        cv.put(Db.COLUMN_NAME_PLACES_DESC,"Rising 196m above the city, the Mount Victoria Lookout is a Wellington must-do. Head to the lookout and be rewarded with stunning panoramic views of Wellington city and harbour, and beyond." +
                "Located right next to the central business district, you can drive all the way up, or take a walkway through the bush-covered Town Belt." +
                "From the top, enjoy the views of Tinakori Hill, the Hutt Valley and Eastern harbour bays, Matiu/Somes Island and the Miramar Peninsula. Beyond are Baring and Pencarrow Heads and further to the right, Wellington’s Southern suburbs and Mt Mathews, Wellington’s highest point to the East." +
                "Take a picnic with you, settle in on the hillside and relax while watching ferries and cruise ships sail into the harbour and planes fly in and out of the airport. This is also one of the best spots in the city for taking in a sunrise or sunset.");

        dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Te Papa");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"0,100");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
        cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Museum");
        cv.put(Db.COLUMN_NAME_PLACES_RATING,4.6);
        cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
        cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"55 Cable Street Wellington 6011");
        cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,3);
        cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"10am-6pm");
        cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.290450);
        cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.782092);
        cv.put(Db.COLUMN_NAME_PLACES_DESC,"The Museum of New Zealand Te Papa Tongarewa is the national museum and art gallery of New Zealand, located in Wellington. It is branded and commonly known as Te Papa and Our Place; \"Te Papa Tongarewa\" is broadly translatable as \"the place of treasures of this land\"." +
                "The museum's principles incorporate the concepts of unified collections; the narratives of culture and place; the idea of forum; the bicultural partnership between Tangata Whenua and Tangata Tiriti; and an emphasis on diversity and multidisciplinary collaboration." +
                "In January 2013 Te Papa management announced the museum would be split into two parts – one operating much as it has in the past, and the other focusing on the future.");
        dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Museum of Wellington City & Sea");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"0,100");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
        cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Museum");
        cv.put(Db.COLUMN_NAME_PLACES_RATING,4.3);
        cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
        cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"Jervois Quay Wellington 6011");
        cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,1);
        cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"10am-5pm");
        cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.285185);
        cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.778176);
        cv.put(Db.COLUMN_NAME_PLACES_DESC,"Museum of Wellington City & Sea." +
                "This is Wellington’s museum, celebrating Wellington’s social, cultural and maritime history." +
                "Housed in the historic Bond Store, the original architecture complements the exhibitions of precious objects and stunning technology.");
        dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Wellington Botanic Garden");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"50,50");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
        cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Park");
        cv.put(Db.COLUMN_NAME_PLACES_RATING,4.7);
        cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
        cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"101 Glenmore Wellington 6012");
        cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,3);
        cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"9am-5pm");
        cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.282356);
        cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.767537);
        cv.put(Db.COLUMN_NAME_PLACES_DESC,"Visit Wellington Botanic Garden and enjoy its 25ha of unique landscape, protected native forest, conifers, specialised plant collections, colourful floral displays, and views over Wellington city.");
        dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Otari-Wilton's Bush");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
        cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Park");
        cv.put(Db.COLUMN_NAME_PLACES_RATING,4.4);
        cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
        cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"160 Wilton Road Wilton Wellington 6012");
        cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,4);
        cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"9am-4pm");
        cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.265218);
        cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.752001);
        cv.put(Db.COLUMN_NAME_PLACES_DESC,"Otari Native Botanic Garden and Wilton's Bush Reserve is the only public botanic garden in New Zealand dedicated solely to native plants. "+
                "The Garden is a unique plant sanctuary and forest reserve and includes 100ha of native forest and 5ha of plant collections. Some of Wellington's oldest trees are here, including an 800-year-old rimu." +
                "It is classified as a Garden of National Significance by the Royal New Zealand Institute of Horticulture.");
        dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);

        Random random = new Random();
        for(int i = 0; i < 50; i++){
            cv = new ContentValues();
            cv.put(Db.COLUMN_NAME_PLACES_NAME, "Test Natural"+i);
            cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
            cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
            cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
            cv.put(Db.COLUMN_NAME_PLACES_RATING,4.5);
            cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
            cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"49 Lookout Rd Hataitai Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,random.nextInt(7));
            cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"");
            cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.296121);
            cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.794387);
            cv.put(Db.COLUMN_NAME_PLACES_DESC,"Test Natural 001");

            dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);
        }
        for(int j = 0; j < 50; j++){
            cv = new ContentValues();
            cv.put(Db.COLUMN_NAME_PLACES_NAME, "Test Natural"+j);
            cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
            cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
            cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
            cv.put(Db.COLUMN_NAME_PLACES_RATING,4.5);
            cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
            cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"49 Lookout Rd Hataitai Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,random.nextInt(7));
            cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"");
            cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.296121);
            cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.794387);
            cv.put(Db.COLUMN_NAME_PLACES_DESC,"Test Natural 001");

            dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);
        }


        dbWrite.close();
    }
    public void createPlans(){
        Db db = new Db(this);
        SQLiteDatabase dbWrite = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(Db.COLUMN_NAME_PLANS_NAME, "Trip to Wellington");
        cv.put(Db.COLUMN_NAME_PLANS_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLANS_PLAN,"1,3,2");
        cv.put(Db.COLUMN_NAME_PLANS_COST,"$255");
        dbWrite.insert(Db.TABLE_NAME_PLANS, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLANS_NAME, "Trip to Wellington 2");
        cv.put(Db.COLUMN_NAME_PLANS_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLANS_PLAN,"3,2,1");
        cv.put(Db.COLUMN_NAME_PLANS_COST,"$600");
        dbWrite.insert(Db.TABLE_NAME_PLANS, null, cv);

        cv = new ContentValues();
        cv.put(Db.COLUMN_NAME_PLANS_NAME, "Trip to Wellington 3");
        cv.put(Db.COLUMN_NAME_PLANS_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLANS_PLAN,"2,3,1");
        cv.put(Db.COLUMN_NAME_PLANS_COST,"$0");
        dbWrite.insert(Db.TABLE_NAME_PLANS, null, cv);

        dbWrite.close();
    }
}
