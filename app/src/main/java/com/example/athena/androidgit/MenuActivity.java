package com.example.athena.androidgit;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuActivity extends AppCompatActivity {
    ImageView imageDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imageDownload = (ImageView) findViewById(R.id.imageDownload);
    }

    public void carregarImagem(View v)
    {
        DownloadImageAsync downloadImageAsync= new DownloadImageAsync();
        URL url = null;
        try {
            url = new URL("http://www.themarysue.com/wp-content/uploads/2016/02/south-park-season-29-premiere-date-750x400.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        downloadImageAsync.execute(url);
    }

    private class DownloadImageAsync extends AsyncTask<URL, Integer,Bitmap>
    {
        ProgressDialog progress;

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(MenuActivity.this,
                    "Baixando imagem",
                    "Por favor espere!");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            if(bitmap != null)
            {
                imageDownload.setImageBitmap(bitmap);
            }
            progress.dismiss();
        }

        @Override
        protected Bitmap doInBackground(URL... params)
        {
            Bitmap myBitmap = null;
            try{
                myBitmap = Util.loadImage(params[0]);
            }catch (IOException e)
            {
                Toast.makeText(MenuActivity.this,
                        "Imagem nao carregada.",
                        Toast.LENGTH_SHORT).show();
            }

            return myBitmap;
        }


    }
}
