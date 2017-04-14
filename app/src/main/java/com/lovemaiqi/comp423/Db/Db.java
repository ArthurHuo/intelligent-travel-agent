package com.lovemaiqi.comp423.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lovemaiqi.comp423.IntelligentTravelAgent;
import com.lovemaiqi.comp423.R;

import java.util.Random;

/**
 * Created by Maiqi on 2015/4/16.
 */
public class Db extends SQLiteOpenHelper {

    public Db(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_PLANS+" ("+
                COLUMN_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_NAME_PLANS_NAME+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLANS_CITY+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLANS_PLAN+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLANS_COST+" TEXT NOT NULL DEFAULT \"\""+
                ")");
        db.execSQL("CREATE TABLE "+TABLE_NAME_PLACES+" ("+
                COLUMN_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_NAME_PLACES_NAME+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_CITY+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_ATTRIBUTE+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_IMAGE+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_TYPE+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_RATING+" REAL NOT NULL DEFAULT 0,"+
                COLUMN_NAME_PLACES_PRICE+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_ADDRESS+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_AVGTIME+" REAL NOT NULL DEFAULT 0,"+
                COLUMN_NAME_PLACES_WORKHOUR+" TEXT NOT NULL DEFAULT \"\","+
                COLUMN_NAME_PLACES_LONGITUDE+" REAL NOT NULL DEFAULT 0,"+
                COLUMN_NAME_PLACES_LATITUDE+" REAL NOT NULL DEFAULT 0,"+
                COLUMN_NAME_PLACES_DESC+" TEXT NOT NULL DEFAULT \"\""+
                ")");
        createTestData(db);
//        TestData td = new TestData();
//        td.createTestDate(this);
    }
    public void createTestData(SQLiteDatabase dbWrite){
//        SQLiteDatabase dbWrite = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(Db.COLUMN_NAME_PLACES_NAME, "Zealandia");
        cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
        cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
        cv.put(Db.COLUMN_NAME_PLACES_IMAGE, R.drawable.img_tepapa2);
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
            cv.put(Db.COLUMN_NAME_PLACES_NAME, "Test Natural "+i);
            cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
            cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
            cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
            cv.put(Db.COLUMN_NAME_PLACES_RATING,random.nextInt(6));
            cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
            cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"49 Lookout Rd Hataitai Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,random.nextInt(6)+1);
            cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"");
            cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.296121);
            cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.794387);
            cv.put(Db.COLUMN_NAME_PLACES_DESC,"Test Natural "+ i);

            dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);
        }
        for(int j = 0; j < 50; j++){
            cv = new ContentValues();
            cv.put(Db.COLUMN_NAME_PLACES_NAME, "Test Cultural "+j);
            cv.put(Db.COLUMN_NAME_PLACES_CITY,"Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"0,100");
            cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
            cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
            cv.put(Db.COLUMN_NAME_PLACES_RATING,random.nextInt(6));
            cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
            cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"49 Lookout Rd Hataitai Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,random.nextInt(6)+1);
            cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"");
            cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.296121);
            cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.794387);
            cv.put(Db.COLUMN_NAME_PLACES_DESC,"Test Cultural "+ j);

            dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);
        }

        for(int i = 0; i < 50; i++){
            cv = new ContentValues();
            cv.put(Db.COLUMN_NAME_PLACES_NAME, "Test Natural "+i);
            cv.put(Db.COLUMN_NAME_PLACES_CITY,"Beijing");
            cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"100,0");
            cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
            cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
            cv.put(Db.COLUMN_NAME_PLACES_RATING,random.nextInt(6));
            cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
            cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"49 Lookout Rd Hataitai Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,random.nextInt(6)+1);
            cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"");
            cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.296121);
            cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.794387);
            cv.put(Db.COLUMN_NAME_PLACES_DESC,"Test Natural "+ i);

            dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);
        }
        for(int j = 0; j < 50; j++){
            cv = new ContentValues();
            cv.put(Db.COLUMN_NAME_PLACES_NAME, "Test Cultural "+j);
            cv.put(Db.COLUMN_NAME_PLACES_CITY,"Beijing");
            cv.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,"0,100");
            cv.put(Db.COLUMN_NAME_PLACES_IMAGE,R.drawable.img_tepapa2);
            cv.put(Db.COLUMN_NAME_PLACES_TYPE, "Sightseeing");
            cv.put(Db.COLUMN_NAME_PLACES_RATING,random.nextInt(6));
            cv.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
            cv.put(Db.COLUMN_NAME_PLACES_ADDRESS,"49 Lookout Rd Hataitai Wellington");
            cv.put(Db.COLUMN_NAME_PLACES_AVGTIME,random.nextInt(6)+1);
            cv.put(Db.COLUMN_NAME_PLACES_WORKHOUR,"");
            cv.put(Db.COLUMN_NAME_PLACES_LATITUDE,-41.296121);
            cv.put(Db.COLUMN_NAME_PLACES_LONGITUDE,174.794387);
            cv.put(Db.COLUMN_NAME_PLACES_DESC,"Test Cultural "+ j);

            dbWrite.insert(Db.TABLE_NAME_PLACES, null, cv);
        }


//        dbWrite.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static final String TABLE_NAME_PLANS = "plans";
    public static final String TABLE_NAME_PLACES = "places";

    public static final String COLUMN_NAME_ID = "_id";
    public static final String COLUMN_NAME_PLANS_NAME = "name";
    public static final String COLUMN_NAME_PLANS_CITY = "city";
    public static final String COLUMN_NAME_PLANS_PLAN = "plan";
    public static final String COLUMN_NAME_PLANS_COST = "cost";

    public static final String COLUMN_NAME_PLACES_NAME = "name";
    public static final String COLUMN_NAME_PLACES_CITY = "city";
    public static final String COLUMN_NAME_PLACES_ATTRIBUTE = "attribute";
    public static final String COLUMN_NAME_PLACES_IMAGE = "image";
    public static final String COLUMN_NAME_PLACES_TYPE = "type";
    public static final String COLUMN_NAME_PLACES_RATING = "rating";
    public static final String COLUMN_NAME_PLACES_PRICE = "price";
    public static final String COLUMN_NAME_PLACES_ADDRESS = "address";
    public static final String COLUMN_NAME_PLACES_AVGTIME = "avgtime";
    public static final String COLUMN_NAME_PLACES_WORKHOUR = "workhour";
    public static final String COLUMN_NAME_PLACES_LONGITUDE = "longitude";
    public static final String COLUMN_NAME_PLACES_LATITUDE = "latitude";
    public static final String COLUMN_NAME_PLACES_DESC = "desc";


}
