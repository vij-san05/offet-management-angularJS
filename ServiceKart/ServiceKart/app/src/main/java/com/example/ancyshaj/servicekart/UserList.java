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

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class UserList extends Activity {
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
        String action = getdata.getStringExtra("action");
        String mobky = getdata.getStringExtra("MobileKey");
        try {
            listvalue1 =   performaction(mobky, action);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PopulateList(listvalue1);



    }

    private void PopulateList(List<FinalPoints>  lista) {

           if(lista==null)
           {
               Toast.makeText(getApplicationContext(), "no data avaliable", Toast.LENGTH_LONG).show();
           }
        else {
               ArrayAdapter<FinalPoints> adapter = new ListAdapterUser(this, lista);
               ListView list = (ListView) findViewById(R.id.listView);
               list.setAdapter(adapter);
           }
    }



    WebserviceJava client;
    String data = "";
    User u;
    List<FinalPoints> listvalue1;

    public List<FinalPoints> performaction( String mobkey, String action) throws Exception {

        try {

            System.out.println("start###" + mobkey);

            u = new User();
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
            Type type = new TypeToken<List<FinalPoints>>() {
            }.getType();
            listvalue1 = gson.fromJson(response, type);


            for (FinalPoints p : listvalue1) {
                System.out.println(p.getOutletname());
                System.out.println(p.getTotalpoints());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ListView list = (ListView) findViewById(R.id.listView);
        //  list.setAdapter(new ArrayAdapter<PublicData>(this,android.R.layout.simple_list_item_1,listvalue1));
         return listvalue1;
    }



    private class ListAdapterUser extends ArrayAdapter<FinalPoints> {
        private Context context;

        // java.util.List<PublicData> listvalue1 = new ArrayList<PublicData>();

        public ListAdapterUser(Context context, List<FinalPoints> listvalue1) {
            super(context, R.layout.listviewuserdetails, listvalue1);
            this.context = context;


        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            if (item == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                item = inflater.inflate(R.layout.listviewuserdetails, parent, false);
            }
            FinalPoints data = listvalue1.get(position);









            //image.setImageResource(data.getProductimage());
            TextView txtname = (TextView) item.findViewById(R.id.outletname);
            txtname.setText(data.getOutletname());
            TextView txtdetails = (TextView) item.findViewById(R.id.outletcity);
            txtdetails.setText(data.getLocation());
            TextView txtprice = (TextView) item.findViewById(R.id.userpoints);
            txtprice.setText(data.getTotalpoints());

            return item;
        }
    }
}
