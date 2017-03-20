package com.empsoft.safe_meal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    Bitmap image;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
        image = null;
    }

    public DownloadImageTask(){
        bmImage = null;
        image = null;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        Log.d("Globals", "doInBackground");
        return image;
    }

    protected void onPostExecute(Bitmap result) {
        if(bmImage!= null){
            bmImage.setImageBitmap(result);
        }
    }

    public Bitmap getImage() {
        return image;
    }
}
