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

/**
 * Created by Ancy Sha J on 4/10/2015.
 */
public class Login extends Activity implements View.OnClickListener{
    EditText lemail,lpass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (android.os.Build.VERSION.SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        lemail=(EditText)findViewById(R.id.editText);
        lpass=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.button);

        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String maillog=lemail.getText().toString();
        String passlog=lpass.getText().toString();


        if (maillog.equals("")  || passlog.equals("") ) {
            Toast.makeText(getApplicationContext(), "please enter valid data", Toast.LENGTH_LONG).show();
        }
        else {
            try {




                    /// Login start
                    returndata= performaction(maillog, passlog,"loginmobile");
                System.out.println("**********:"+returndata);
                    if(returndata.trim().matches("null"))
                    {
                        Toast.makeText(getApplicationContext(),"email id not existed",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        startActivity(new Intent(this,Home.class));
                    }




              /*  if(returndata.trim().matches("null"))
                {

                    Toast.makeText(getApplicationContext(),"email id not existed",Toast.LENGTH_LONG).show();


                }
                else
                {
                    if()
                    SaveData.setDefaults("email",obj.getEmail(),this);
                    SaveData.setDefaults("mobilekey",obj.getMobilekey(),this);
                }*/
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    WebserviceJava client;
    String returndata;
    User u;
    String returnemail;

    public String performaction(String email, String pass,String action) throws Exception {

        try {

            System.out.println("start###");


            u = new User();
            u.setEmail(email);
            u.setPassword(pass);

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

        } catch (Exception e) {
            e.printStackTrace();
        }


        return returnemail;

    }


}
