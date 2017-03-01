package com.example.athena.androidgit;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Util
{
    public static Bitmap loadImage(URL url) throws IOException {
        InputStream inputStream;
        Bitmap myBitmap;

        inputStream = url.openStream();
        myBitmap = BitmapFactory.decodeStream(inputStream);

        inputStream.close();
        return myBitmap;
    }

}
