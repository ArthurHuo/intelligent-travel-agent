package com.lovemaiqi.comp423.Tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lovemaiqi.comp423.Db.Db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Maiqi on 2015/4/19.
 */
public class MakePlan {
    private String city;
    private int natural;
    private int cultural;
    private int pace;
    private int budget;
    private Db db;
    private SQLiteDatabase dbRead,dbWrite;
    private Context context;
    private Date startDate,endDate;
    private int numDays;
    private ArrayList<HashMap<String,Object>> list1,list2,list3,resultlist,firstList;
    ArrayList<Integer> exist;



    public MakePlan(Context context,String city,Date startDate,Date endDate, int natural,int cultural,int pace,int budget){
        this.context = context;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.natural = natural;
        this.cultural = cultural;
        this.pace = pace;
        this.budget = budget;
    }
    public int makePlan(){
        db = new Db(context);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        numDays = (int)(endDate.getTime() - startDate.getTime())/(1000*60*60*24);

        Cursor c = dbRead.query(Db.TABLE_NAME_PLACES,null, Db.COLUMN_NAME_PLACES_CITY+"=?", new String[]{city}, null, null, null);

        firstList = new ArrayList<HashMap<String,Object>>();
        list1 = new ArrayList<HashMap<String,Object>>();
        list2 = new ArrayList<HashMap<String,Object>>();
        list3 = new ArrayList<HashMap<String,Object>>();
        resultlist = new ArrayList<HashMap<String,Object>>();

        while(c.moveToNext()){
            int _id = c.getInt(c.getColumnIndex(Db.COLUMN_NAME_ID));
            String attribute = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_ATTRIBUTE));
            String price = c.getString(c.getColumnIndex(Db.COLUMN_NAME_PLACES_PRICE));
            Double avgTime = c.getDouble(c.getColumnIndex(Db.COLUMN_NAME_PLACES_AVGTIME));
            Double latitude = c.getDouble(c.getColumnIndex(Db.COLUMN_NAME_PLACES_LATITUDE));
            Double longitude = c.getDouble(c.getColumnIndex(Db.COLUMN_NAME_PLACES_LONGITUDE));


            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put(Db.COLUMN_NAME_ID,_id);
            map.put(Db.COLUMN_NAME_PLACES_ATTRIBUTE,attribute);
            map.put(Db.COLUMN_NAME_PLACES_PRICE,price);
            map.put(Db.COLUMN_NAME_PLACES_AVGTIME,avgTime);
            map.put(Db.COLUMN_NAME_PLACES_LATITUDE,latitude);
            map.put(Db.COLUMN_NAME_PLACES_LONGITUDE,longitude);

            firstList.add(map);


        }
//        String result = "";
//        for(int i = 0; i < firstList.size(); i++){
//            result = result + firstList.get(i).get(Db.COLUMN_NAME_ID).toString();
//            if((i+1)<firstList.size()){
//                result = result + ",";
//            }
//        }








        return buildSecondList(firstList);
    }

    public int buildSecondList(ArrayList<HashMap<String,Object>> firstList){
        ArrayList<HashMap<String,Object>> tempList1 = new ArrayList<HashMap<String,Object>>();

        if(budget == 0){
            for(int i = 0; i < firstList.size();i++){
                if(firstList.get(i).get(Db.COLUMN_NAME_PLACES_PRICE).equals("FREE")){
                    tempList1.add(firstList.get(i));
                }
            }
        }else{
            tempList1 = firstList;
        }



        int[] vector1 = new int[]{natural,cultural};
        int[] vectorNatural = new int[]{100,0};
        int[] vectorCultural = new int[]{0,100};
        String att = "";
        String[] atts;
//        System.out.println(tempList1.size());
        for(int i = 0; i < tempList1.size();i++){
            att = (String)tempList1.get(i).get(Db.COLUMN_NAME_PLACES_ATTRIBUTE);
            atts = att.split(",");
            int[] vector2 = new int[2];
            vector2[0] = Integer.valueOf(atts[0]);
            vector2[1] = Integer.valueOf(atts[1]);

            double similar = checkSimilar(vector1,vector2);
            if(similar > 0.8){
                list1.add(tempList1.get(i));
            }

            similar = checkSimilar(vector2,vectorNatural);
            if(similar >= 0.8){
                list2.add(tempList1.get(i));
            }else {
                similar = checkSimilar(vector2,vectorCultural);
                if(similar >= 0.8){
                    list3.add(tempList1.get(i));
                }
            }
        }
    return createNewPlan();

    }
    public int createNewPlan(){
        exist = new ArrayList<Integer>();
        for(int i= 0;i < numDays ;i++){
            createNewPlanByDay();
            if((i+1)<numDays){
                int _id = 0;
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put(Db.COLUMN_NAME_ID,_id);
                map.put(Db.COLUMN_NAME_PLACES_PRICE,"FREE");
                resultlist.add(map);
            }

        }

        return savePlan();
    }


    public void createNewPlanByDay(){

        ArrayList<HashMap<String,Object>> tempList = new ArrayList<HashMap<String,Object>>();
        Random random = new Random();
        int ran;
        boolean flag = false;

            if(cultural == 0){
                for(int i = 0; i < list1.size(); i++){
                    tempList.add(list1.get(i));
                }
                for(int i = 0; i < list2.size(); i++){
                    tempList.add(list2.get(i));
                }
                if(pace == 0){
                    ran = random.nextInt(tempList.size());
                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    if( temptime>= 3){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        for(int i = 0; i < tempList.size(); i++){
                            if((double)tempList.get(i).get(Db.COLUMN_NAME_PLACES_AVGTIME) < 3){
                                if(exist.contains((int)tempList.get(ran).get(Db.COLUMN_NAME_ID))){
                                    continue;
                                }
                                tt.add(tempList.get(i));
                            }
                        }
                        if(tt.size() == 0){
//                            return savePlan();
                            return;
                        }
                        ran = random.nextInt(tt.size() + 1);
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                        resultlist.add(tt.get(ran));
//                        return savePlan();
                        return;
                    }
                }else if(pace == 50){
                    System.out.println(tempList.size());
                    ran = random.nextInt(tempList.size());
                    System.out.println(ran);
                    double totaltime = 0;
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    totaltime += temptime;
                    if( temptime>= 6){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        boolean tempflag = true;
                        int iflag= 0;
                        while(tempflag && iflag < 1000){
                            ran = random.nextInt(tempList.size());
                            if (exist.contains((int)tempList.get(ran).get(Db.COLUMN_NAME_ID))){
                                iflag += 1;
                                continue;
                            }
                            temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                            if(totaltime + temptime < 6){
                                if(totaltime + temptime + 2 < 6){
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    totaltime += temptime;
                                    continue;
                                }
                            }else if(totaltime + temptime >= 6){
                                if(totaltime + temptime < 8){
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    tempflag =false;
                                    break;
                                }
                            }
                            iflag += 1;
                        }
//                        for(int i = 0; i < tempList.size(); i++){
//                            temptime = (double)tempList.get(i).get(Db.COLUMN_NAME_PLACES_AVGTIME);
//                            if(temptime < 6){
//                                if(i == ran){
//                                    continue;
//                                }
//                                tt.add(tempList.get(i));
//                            }
//                        }
//                        if(tt.size() == 0){
//                            return;
//                        }
//                        ran = random.nextInt(tt.size() + 1);
//                        resultlist.add(tt.get(ran));
//                        return savePlan();
                    }
                }else if(pace == 100){
                    ran = random.nextInt(tempList.size());
                    double totaltime = 0;
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    totaltime += temptime;
                    if( temptime>= 6){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        boolean tempflag = true;
                        int iflag = 0;
                        while (tempflag && iflag < 1000) {
                            ran = random.nextInt(tempList.size());
                            if (exist.contains((int) tempList.get(ran).get(Db.COLUMN_NAME_ID))) {
                                iflag += 1;
                                continue;
                            }
                            temptime = (double) tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                            if (totaltime + temptime < 8) {
                                if (totaltime + temptime + 2 < 8) {
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    totaltime += temptime;
                                    continue;
                                }
                            } else if (totaltime + temptime >= 8) {
                                if (totaltime + temptime < 10) {
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    tempflag = false;
                                    break;
                                }
                            }
                            iflag += 1;
                        }
                    }
                }



            }else if(natural == 0){
                for(int i = 0; i < list1.size(); i++){
                    tempList.add(list1.get(i));
                }
                for(int i = 0; i < list3.size(); i++){
                    tempList.add(list3.get(i));
                }
                if(pace == 0){
                    ran = random.nextInt(tempList.size());
                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    if( temptime>= 3){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        for(int i = 0; i < tempList.size(); i++){
                            if((double)tempList.get(i).get(Db.COLUMN_NAME_PLACES_AVGTIME) < 3){
                                if(exist.contains((int)tempList.get(ran).get(Db.COLUMN_NAME_ID))){
                                    continue;
                                }
                                tt.add(tempList.get(i));
                            }
                        }
                        if(tt.size() == 0){
//                            return savePlan();
                            return;
                        }
                        ran = random.nextInt(tt.size() + 1);
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                        resultlist.add(tt.get(ran));
//                        return savePlan();
                        return;
                    }
                }else if(pace == 50){
                    System.out.println(tempList.size());
                    ran = random.nextInt(tempList.size());
                    System.out.println(ran);
                    double totaltime = 0;
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    totaltime += temptime;
                    if( temptime>= 6){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        boolean tempflag = true;
                        int iflag= 0;
                        while(tempflag && iflag < 1000){
                            ran = random.nextInt(tempList.size());
                            if (exist.contains((int)tempList.get(ran).get(Db.COLUMN_NAME_ID))){
                                iflag += 1;
                                continue;
                            }
                            temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                            if(totaltime + temptime < 6){
                                if(totaltime + temptime + 2 < 6){
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    totaltime += temptime;
                                    continue;
                                }
                            }else if(totaltime + temptime >= 6){
                                if(totaltime + temptime < 8){
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    tempflag =false;
                                    break;
                                }
                            }
                            iflag += 1;
                        }
//                        for(int i = 0; i < tempList.size(); i++){
//                            temptime = (double)tempList.get(i).get(Db.COLUMN_NAME_PLACES_AVGTIME);
//                            if(temptime < 6){
//                                if(i == ran){
//                                    continue;
//                                }
//                                tt.add(tempList.get(i));
//                            }
//                        }
//                        if(tt.size() == 0){
//                            return;
//                        }
//                        ran = random.nextInt(tt.size() + 1);
//                        resultlist.add(tt.get(ran));
//                        return savePlan();
                    }
                }else if(pace == 100){
                    ran = random.nextInt(tempList.size());
                    double totaltime = 0;
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    totaltime += temptime;
                    if( temptime>= 6){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        boolean tempflag = true;
                        int iflag = 0;
                        while (tempflag && iflag < 1000) {
                            ran = random.nextInt(tempList.size());
                            if (exist.contains((int) tempList.get(ran).get(Db.COLUMN_NAME_ID))) {
                                iflag += 1;
                                continue;
                            }
                            temptime = (double) tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                            if (totaltime + temptime < 8) {
                                if (totaltime + temptime + 2 < 8) {
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    totaltime += temptime;
                                    continue;
                                }
                            } else if (totaltime + temptime >= 8) {
                                if (totaltime + temptime < 10) {
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    tempflag = false;
                                    break;
                                }
                            }
                            iflag += 1;
                        }
                    }
                }


            }else {
////                if(natural/cultural > 2)
//                if(list1.size() != 0){
//                    ran = random.nextInt(list1.size()+1);
//                    tempList.add(list1.get(ran));
//                }else if(list2.size() != 0){
//                    ran = random.nextInt(list2.size()+1);
//                    tempList.add(list2.get(ran));
////                    ran = random.nextInt(list3.size()+1);
////                    resultlist.add(list3.get(ran));
//                }
//                if(tempList.size() == 0){
//                    //fill
//                }
//                double time = (double)tempList.get(0).get(Db.COLUMN_NAME_PLACES_AVGTIME);
//                if(time >= 5){
//                    if (pace == 0){
//                        resultlist.add(tempList.get(0));
////                        return savePlan();
//                        return;
//                    }else if(pace == 50){
//                        resultlist.add(tempList.get(0));
//                    }
//                }
//
//                if(list1.size() == 0){
//                    ran = random.nextInt(list3.size()+1);
//                    tempList.add(list3.get(ran));
//                }
                for(int i = 0; i < list1.size(); i++){
                    tempList.add(list1.get(i));
                }
                for(int i = 0; i < list2.size(); i++){
                    tempList.add(list2.get(i));
                }
                for(int i = 0; i < list3.size(); i++){
                    tempList.add(list3.get(i));
                }
                if(pace == 0){
                    ran = random.nextInt(tempList.size());
                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    if( temptime>= 3){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        for(int i = 0; i < tempList.size(); i++){
                            if((double)tempList.get(i).get(Db.COLUMN_NAME_PLACES_AVGTIME) < 3){
                                if(exist.contains((int)tempList.get(ran).get(Db.COLUMN_NAME_ID))){
                                    continue;
                                }
                                tt.add(tempList.get(i));
                            }
                        }
                        if(tt.size() == 0){
//                            return savePlan();
                            return;
                        }
                        ran = random.nextInt(tt.size() + 1);
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                        resultlist.add(tt.get(ran));
//                        return savePlan();
                        return;
                    }
                }else if(pace == 50){
                    System.out.println(tempList.size());
                    ran = random.nextInt(tempList.size());
                    System.out.println(ran);
                    double totaltime = 0;
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    totaltime += temptime;
                    if( temptime>= 6){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        boolean tempflag = true;
                        int iflag= 0;
                        while(tempflag && iflag < 1000){
                            ran = random.nextInt(tempList.size());
                            if (exist.contains((int)tempList.get(ran).get(Db.COLUMN_NAME_ID))){
                                iflag += 1;
                                continue;
                            }
                            temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                            if(totaltime + temptime < 6){
                                if(totaltime + temptime + 2 < 6){
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    totaltime += temptime;
                                    continue;
                                }
                            }else if(totaltime + temptime >= 6){
                                if(totaltime + temptime < 8){
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    tempflag =false;
                                    break;
                                }
                            }
                            iflag += 1;
                        }
//                        for(int i = 0; i < tempList.size(); i++){
//                            temptime = (double)tempList.get(i).get(Db.COLUMN_NAME_PLACES_AVGTIME);
//                            if(temptime < 6){
//                                if(i == ran){
//                                    continue;
//                                }
//                                tt.add(tempList.get(i));
//                            }
//                        }
//                        if(tt.size() == 0){
//                            return;
//                        }
//                        ran = random.nextInt(tt.size() + 1);
//                        resultlist.add(tt.get(ran));
//                        return savePlan();
                    }
                }else if(pace == 100){
                    ran = random.nextInt(tempList.size());
                    double totaltime = 0;
                    double temptime = (double)tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                    totaltime += temptime;
                    if( temptime>= 6){
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        return savePlan();
                        return;
                    }else {
                        resultlist.add(tempList.get(ran));
                        exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
//                        ArrayList<HashMap<String,Object>> tt = new ArrayList<HashMap<String,Object>>();
                        boolean tempflag = true;
                        int iflag = 0;
                        while (tempflag && iflag < 1000) {
                            ran = random.nextInt(tempList.size());
                            if (exist.contains((int) tempList.get(ran).get(Db.COLUMN_NAME_ID))) {
                                iflag += 1;
                                continue;
                            }
                            temptime = (double) tempList.get(ran).get(Db.COLUMN_NAME_PLACES_AVGTIME);
                            if (totaltime + temptime < 8) {
                                if (totaltime + temptime + 2 < 8) {
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    totaltime += temptime;
                                    continue;
                                }
                            } else if (totaltime + temptime >= 8) {
                                if (totaltime + temptime < 10) {
                                    resultlist.add(tempList.get(ran));
                                    exist.add((int)tempList.get(ran).get(Db.COLUMN_NAME_ID));
                                    tempflag = false;
                                    break;
                                }
                            }
                            iflag += 1;
                        }
                    }
                }

            }


            return;
//        int aa = savePlan();
//        return aa;
    }
    public  int savePlan(){
        String result = "";
        for(int i = 0; i < resultlist.size(); i++){
            result = result + resultlist.get(i).get(Db.COLUMN_NAME_ID).toString();
            if((i+1)<resultlist.size()){
                result = result + ",";
            }
        }
        double cost = 0.0;
        for(int j = 0 ; j < resultlist.size(); j++){
            String ss = (String)resultlist.get(j).get(Db.COLUMN_NAME_PLACES_PRICE);
            if(ss != null || ss != ""){
                if(!ss.equals("FREE")){
                    ss.replace("$","");
                    cost += Double.valueOf(ss);
                }
            }


        }


        ContentValues cv = new ContentValues();

        cv.put(Db.COLUMN_NAME_PLANS_NAME, "Trip to"+ city);
        cv.put(Db.COLUMN_NAME_PLANS_CITY,city);
        cv.put(Db.COLUMN_NAME_PLANS_PLAN,result);
        if(cost == 0.0){
            cv.put(Db.COLUMN_NAME_PLANS_COST,"Free");
        }else {
            cv.put(Db.COLUMN_NAME_PLANS_COST,"$"+cost);
        }

        dbWrite.insert(Db.TABLE_NAME_PLANS, null, cv);
        Cursor cc = dbRead.rawQuery("SELECT last_insert_rowid()", null);
        cc.moveToFirst();
        int id = cc.getInt(0);
        return id;
    }
    public double checkSimilar(int[] vector1,int[] vector2){
        double vector1Modulo = 0.00;
        double vector2Modulo = 0.00;
        double vectorProduct = 0.00;

        for(int i = 0; i < vector1.length; i++){
            vector1Modulo += vector1[i]*vector1[i];
            vector2Modulo += vector2[i]*vector2[i];

            vectorProduct += vector1[i]*vector2[i];
        }

        vector1Modulo = Math.sqrt(vector1Modulo);
        vector2Modulo = Math.sqrt(vector2Modulo);

        return (vectorProduct/(vector1Modulo*vector2Modulo));
    }
}
