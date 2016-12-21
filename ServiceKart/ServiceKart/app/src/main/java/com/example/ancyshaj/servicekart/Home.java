package com.example.ancyshaj.servicekart;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class Home extends ActionBarActivity implements AdapterView.OnClickListener, AdapterView.OnItemSelectedListener {
    // EditText t ;
    ImageButton image1, image2, image3, image4;
    Spinner sp;
    TextView tv;
    Geocoder geocoder;
    String bestProvider;
    List<Address> user = null;
    double lat;
    double lng;
    private static String latimob = "8.495689";
    private static String longimob = "76.931839";
    Login log1;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setTheme(android.R.style.Theme_Holo);


        android.support.v7.app.ActionBar bar = getSupportActionBar();
       // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f")));
        bar.setIcon(R.mipmap.ic_android);
        bar.setDisplayShowTitleEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setTitle("Retail Offers");

        //bar.setDisplayHomeAsUpEnabled(true);
      /*  t= (EditText)findViewById(R.id.editText4);
       String s= SaveData.getDefaults("mobilekey",this);
        t.setText(s);*/
        if (android.os.Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        image1 = (ImageButton) findViewById(R.id.imageButton1);
        image2 = (ImageButton) findViewById(R.id.imageButton2);



        image1.setOnClickListener(this);
        image2.setOnClickListener(this);


        // create class object
        gps = new GPSTracker(Home.this);
        // check if GPS enabled
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            latimob=String.valueOf(latitude);
            longimob=String.valueOf(longitude);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }







    }
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {

            case R.id.imageButton1:

             String keyvalue = SaveData.getDefaults("mobilekey", this);
                  System.out.println("---------" + keyvalue);

                try {
                    performaction(latimob, longimob, keyvalue, "getlocationservicemobile");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.imageButton2:

                String keyvalue1 = SaveData.getDefaults("mobilekey", this);
                System.out.println("---------" + keyvalue1);

                try {
                    performactionList(keyvalue1, "getuserscores");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


              /*  Intent i= new Intent(Home.this, Listproduct.class);

                startActivity(i);*/
        }

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Text = parent.getSelectedItem().toString();
        if (Text == "----Select----") {

        } else {
            if (Text == "THIRUVANANTHAPURAM") {

               /* SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

                 String key = preferences.getString(" mobilekey","");*/
                String keyvalue = SaveData.getDefaults("mobilekey", this);
                System.out.println("---------" + keyvalue);

                try {
                    performaction(latimob, longimob, keyvalue, "getlocationservicemobile");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(Home.this, ChangePassword.class);
                startActivity(i);

                return super.onOptionsItemSelected(item);

        }


        return true;
    }


    WebserviceJava client;
    String webdata;
    User u;


    public void performaction(String lati, String longi, String mobkey, String action) throws Exception {

        try {

            System.out.println("start###");

            Intent intent2 = new Intent(Home.this, Listproduct.class);
            intent2.putExtra("Latitude",lati);
            intent2.putExtra("Longitude",longi);
            intent2.putExtra("MobileKey", mobkey);

            startActivity(intent2);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void performactionList(String mobkey, String action) throws Exception {

        try {

            System.out.println("start###");

            Intent intent2 = new Intent(Home.this, UserList.class);
            intent2.putExtra("action",action);
            intent2.putExtra("MobileKey", mobkey);

            startActivity(intent2);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}




