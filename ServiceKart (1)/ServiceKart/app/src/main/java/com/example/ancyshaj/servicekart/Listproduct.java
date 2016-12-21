package com.example.ancyshaj.servicekart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ancy Sha J on 4/16/2015.
 */
public class Listproduct extends Activity {
    // java.util.List<PublicData> pub = new ArrayList<PublicData>();
Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listproduct);
        if (android.os.Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Intent getdata = getIntent();
        String lat = getdata.getStringExtra("Latitude");
        String lon = getdata.getStringExtra("Longitude");
        String mobky = getdata.getStringExtra("MobileKey");
        try {
         listvalue1 =   performaction(lat, lon, mobky, "getlocationservicemobile");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(listvalue1!=null || !listvalue1.isEmpty())
        {       PopulateList();


         ListClick();
    }}

   private void ListClick() {
       ListView list = (ListView)findViewById(R.id.listView);
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Toast.makeText(getApplicationContext(), "you selected   " + position, Toast.LENGTH_LONG).show();

               Intent i = new Intent(Listproduct.this,ProductDetails.class);

               //ArrayAdapter<PublicData> adapter = new ListAdapter();

               PublicData data =listvalue1.get(position);
               i.putExtra("position",data);



               startActivity(i);
           }
       });
     }
    private void PopulateList() {


        ArrayAdapter<PublicData> adapter = new ListAdapter(this, listvalue1);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }

    WebserviceJava client;
    String data = "";
    User u;
    List<PublicData> listvalue1;

    public  List<PublicData> performaction(String lati, String longi, String mobkey, String action) throws Exception {

        try {

            System.out.println("start###" + mobkey);

            u = new User();
            u.setLatimobile(lati);
            u.setLongimobile(longi);
            u.setMobilekey(mobkey);

/*//2 .convert the object to json string*/
            Gson gson = new Gson();

            String jsonRepresentation = gson.toJson(u);
            System.out.println("input " + jsonRepresentation); // this is in json format input

            client = new WebserviceJava(action);
            client.setJsonmsg(jsonRepresentation);


// 4. execute and send data to server
            client.Execute(WebserviceJava.RequestMethod.POST);

            // 5. get response
            String response = client.getResponse();
            // System.out.println(response);  // now this is in json format output
            // User obj = gson.fromJson(response, User.class);

            System.out.print(">>>>>>>>>>>>" + response);
            Type type = new TypeToken<List<PublicData>>() {
            }.getType();
            listvalue1 = gson.fromJson(response, type);


           /* for (PublicData p : listvalue1) {
                System.out.println(p.getProductname());
                System.out.println(p.getProductdetails());
                System.out.println(p.getProductimage());
                System.out.println(p.getProductprice());
                System.out.println(p.getProductoffer());
                System.out.println(p.getProductofferdetails());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listvalue1;
        // ListView list = (ListView) findViewById(R.id.listView);
        //  list.setAdapter(new ArrayAdapter<PublicData>(this,android.R.layout.simple_list_item_1,listvalue1));

    }


   private class ListAdapter extends ArrayAdapter<PublicData> {
        private Context context;

       // java.util.List<PublicData> listvalue1 = new ArrayList<PublicData>();

        public ListAdapter(Context context, List<PublicData> listvalue1) {
            super(context, R.layout.listviewdetails, listvalue1);
            this.context = context;


        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            if (item == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                item = inflater.inflate(R.layout.listviewdetails, parent, false);
            }
            PublicData data = listvalue1.get(position);









            //image.setImageResource(data.getProductimage());
            TextView txtname = (TextView) item.findViewById(R.id.proname);
            txtname.setText(data.getProductname());
            TextView txtdetails = (TextView) item.findViewById(R.id.prodetails);
            txtdetails.setText(data.getProductdetails());
            TextView txtprice = (TextView) item.findViewById(R.id.proprice);
            txtprice.setText(data.getProductprice() + "$");
            TextView txtoffer = (TextView) item.findViewById(R.id.prooffer);
            txtoffer.setText(data.getProductoffer());
            TextView txtoffrdetails = (TextView) item.findViewById(R.id.proofferdetails);
            txtoffrdetails.setText(data.getProductofferdetails());
            AsynTask task = new AsynTask();

            AsyncTask<String, Void, Bitmap> bb =  task.execute(new String[]{data.getProductimage()});
            try {
                Bitmap image1 = bb.get();
                ImageView image = (ImageView) item.findViewById(R.id.img_pro);
                image.setImageBitmap(image1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return item;
        }
    }

}





