package com.example.ancyshaj.servicekart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * Created by Ancy Sha J on 4/17/2015.
 */
public class ProductDetails extends Activity {
    int position1;
    String imageicon;

   TextView name,detail,price,offer,rewards,points,describtion;
    ImageView images;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Intent i=getIntent();
        images=(ImageView)findViewById(R.id.img_product);
        name=(TextView)findViewById(R.id.pname);
        detail=(TextView)findViewById(R.id.pdetails);
        price=(TextView)findViewById(R.id.pprice);
        offer =(TextView)findViewById(R.id.ppoffer);
        rewards =(TextView)findViewById(R.id.ppreward);
        points = (TextView)findViewById(R.id.pppoints);
       // describtion = (TextView)findViewById(R.id.ppdescription);

       // offer=(TextView)findViewById(R.id.textView4);
        if(i!=null) {

          PublicData product=(PublicData)i.getExtras().get("position");
            name.setText(product.getProductname());
            detail.setText(product.getProductdetails());
            price.setText("$"+product.getProductprice());
            offer.setText(product.getProductoffer());
           rewards.setText(product.getRewarddetails());
            points.setText(product.getPointsyoucanearnperproduct());
           // describtion.setText(product.productdetails);
            //offer.setText(productoffer+""+productofferdetails);
            AsynTask task = new AsynTask();
            AsyncTask<String, Void, Bitmap> bb =  task.execute(new String[]{product.getProductimage()});
            try {
                Bitmap image1 = bb.get();

                images.setImageBitmap(image1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }

         //images.setImageBitmap(imageicon));




    }
}

