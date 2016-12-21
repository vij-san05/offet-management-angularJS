package com.example.ancyshaj.servicekart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;


public class ChangePassword extends Activity implements View.OnClickListener{
    EditText t3,t4;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
       /* if (android.os.Build.VERSION.SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/
        t3= (EditText) findViewById(R.id.editText3);
        t4= (EditText) findViewById(R.id.editText4);
        b= (Button) findViewById(R.id.button);

        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(t3.getText().toString().matches("") || t4.getText().toString().matches("") )
        {
            Toast.makeText(getApplicationContext(), "please enter valid data", Toast.LENGTH_LONG).show();
        }
        else if(!t3.getText().toString().matches(t4.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Password not matching", Toast.LENGTH_LONG).show();
        }
        else
        {
          try
          {// Login start
                String keyvalue = SaveData.getDefaults("mobilekey", this);
                returndata= performaction(t3.getText().toString(),keyvalue,"editpasswordmobile");
                System.out.println("**********:"+returndata);
                if(returndata.trim().matches(""))
                {

                   Toast.makeText(getApplicationContext(),"email id not existed", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Password Successfully changed",Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    WebserviceJava client;
    String returndata;
    User u;
    String returnemail;

    public String performaction(String password, String mobilekey,String action) throws Exception {

        try {

            System.out.println("start###");


            u = new User();
            u.setMobilekey(mobilekey);
            u.setPassword(password);


/*//2 .convert the object to json string*/
            Gson gson = new Gson();

            String jsonRepresentation = gson.toJson(u);
            System.out.println("input "+jsonRepresentation); // this is in json format input

            client = new WebserviceJava(action);
            client.setJsonmsg(jsonRepresentation);

// 4. execute and send data to server
            client.Execute(WebserviceJava.RequestMethod.POST);

            // 5. get response
            String response = client.getResponse();
            // System.out.println(response);  // now this is in json format output


            //6 . convert  the string result back to Object and display the result in UI
            User obj = gson.fromJson(response, User.class);
            // if(obj.getEmail()=="null" || obj.getEmail()==null )
            //System.out.println("$$$$$44 null mail");
            // this is an object

            if(obj.getEmail()!=null && action.matches("loginmobile"))
            {
                /// save
                SaveData.setDefaults("email",obj.getEmail(),this);
                SaveData.setDefaults("mobilekey",obj.getMobilekey(),this);

            }


            returnemail = obj.getEmail();
            System.out.println("***"+returnemail);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return returnemail;

    }



}
