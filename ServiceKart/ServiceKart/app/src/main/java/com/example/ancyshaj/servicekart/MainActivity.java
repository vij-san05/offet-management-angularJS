package com.example.ancyshaj.servicekart;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    EditText email, user, pass,cpass;
    Button reg;

    // check email
    // registration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        email = (EditText) findViewById(R.id.editText);
        user = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText3);
        cpass=(EditText)findViewById(R.id.editText4);
        reg=(Button)findViewById(R.id.button);
        reg.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String mail = email.getText().toString();
        String usern = user.getText().toString();
        String passw = pass.getText().toString();
        String cpass1= cpass.getText().toString();
        if (mail.equals("")  || usern.equals("") ||  passw.equals("") || cpass1.equals("") ) {
            Toast.makeText(getApplicationContext(), "please enter valid data", Toast.LENGTH_LONG).show();
        } else {
            try {

                // user
                System.out.println("first action");
                returndata =  performaction(mail, usern, passw,cpass1,"registrationcheckmobile");
                System.out.println("return data"+ returndata);

                if(returndata.trim().matches("null"))
                {
                    System.out.println("second action");
                   /// registration start
                    returndata= performaction(mail, usern, passw,cpass1,"registrationmobile");
                    if(passw.equals(cpass1)) {
                        if (returndata.trim().matches("null")) {
                            System.out.println("third action");
                            Toast.makeText(getApplicationContext(), "Registration not successfull", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(this, Login.class));
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Password not matches", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Registration not successfull",Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }





    WebserviceJava client;
    String returndata;
    User u;
    String returnemail;

    public String performaction(String email, String name, String pass, String epass, String action) throws Exception {

        try {

            System.out.println("start###");


            u = new User();
            u.setEmail(email);
            u.setPassword(pass);
            u.setConfirmpassword(epass);
            u.setUsername(name);
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
            if(obj.getEmail().trim().matches("null") || obj.getEmail()==null )
            System.out.println("$$$$$44 null mail");
             // this is an object

                if(obj.getEmail()!=null && action.matches("registrationmobile"))
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