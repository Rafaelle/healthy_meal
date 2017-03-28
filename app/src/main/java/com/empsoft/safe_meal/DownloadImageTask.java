package com.empsoft.safe_meal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;


public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;
    ImageView imageBtn;
    Bitmap image;

    public DownloadImageTask(ImageView imageView){
        this.imageView = imageView;
        image = null;
    }

    public DownloadImageTask(){
        imageView = null;
        image = null;
    }

    public DownloadImageTask(ImageButton imageBtn){
        this.imageBtn = imageBtn;
        image = null;
    }
    /*
        doInBackground(Params... params)
            Override this method to perform a computation on a background thread.
     */
    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];

        try{
            InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
            image = BitmapFactory.decodeStream(is);
        }catch(Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        return image;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result){
        if(imageView != null)imageView.setImageBitmap(result);
        else imageBtn.setImageBitmap(result);
    }

    public Bitmap getImage() {
        return image;
    }
}

