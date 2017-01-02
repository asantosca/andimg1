package image.asantos.imagemanipulation2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
    Button b1, b2, b3;
    ImageView im;

    private Bitmap bmp;
    private Bitmap operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        im = (ImageView) findViewById(R.id.imageView);

        BitmapDrawable abmp = (BitmapDrawable) im.getDrawable();
        bmp = abmp.getBitmap();
    }


    /*

    123456789012
    123456789012

    112233445566
    112233445566

    111222333444
    111222333444
    111222333444

     */

    private int calculateAvg(int i, int j, int pixelSize) {
        int p = bmp.getPixel(i, j);
        int a = Color.alpha(p);
        int r = Color.red(p);
        int g = Color.green(p);
        int b = Color.blue(p);

        return Color.argb(a, r, g, b);
/*
        Color c = Color.;
        // calculates the average color
        int avgr = 0;
        int avgg = 0;
        int avgb = 0;
        int avga = 0;
        for (int pi = 0; pi < pixelSize; pi++) {
            for (int pj = 0; pj < pixelSize; pj++) {

                int p = bmp.getPixel(i + pi, j + pj);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int a = Color.alpha(p);

                avgr = (avgr + r );
                avgg = (avgg + g );
                avgb = (avgb + b );
                avga = (avga + a );
            }
            avgr = avgr / pixelSize;
            avgg = avgg / pixelSize;
            avgb = avgb / pixelSize;
            avga = avga / pixelSize;
        }
        avgr = avgr / pixelSize;
        avgg = avgg / pixelSize;
        avgb = avgb / pixelSize;
        avga = avga / pixelSize;
        return c;
        */
    }

    public void pixelate(View view) {
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        int pixelSize = 30;
        for (int i = 0; i < bmp.getWidth(); i = i + pixelSize) {
            for (int j = 0; j < bmp.getHeight(); j = j + pixelSize) {

                int avg = calculateAvg(i, j, pixelSize);

                // apply the average color
                for (int pi = 0; pi < pixelSize; pi++) {
                    for (int pj = 0; pj < pixelSize; pj++) {
                        if (i + pi < bmp.getWidth() &&  j + pj < bmp.getHeight())
                            operation.setPixel(i + pi, j + pj, avg);
                    }
                }
            }
        }
        im.setImageBitmap(operation);
    }

    public void original(View view) {
        im.setImageBitmap(bmp);
    }

    public void pixelate2(View view) {
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());
        double red = 0.33;
        double green = 0.59;
        double blue = 0.11;

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);

                if (i == j) {
                    r = 0;
                    g = g + 150;
                    b = 0;
                }
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        im.setImageBitmap(operation);
    }

    public void gray(View view) {
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());
        double red = 0.33;
        double green = 0.59;
        double blue = 0.11;

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);

                r = (int) red * r;
                g = (int) green * g;
                b = (int) blue * b;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        im.setImageBitmap(operation);
    }

    public void bright(View view){
        operation= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = 100  +  r;
                g = 100  + g;
                b = 100  + b;
                alpha = 100 + alpha;
                operation.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        im.setImageBitmap(operation);
    }

    public void dark(View view){
        operation= Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(),bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  r - 50;
                g =  g - 50;
                b =  b - 50;
                alpha = alpha -50;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        im.setImageBitmap(operation);
    }

    public void gama(View view) {
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(),bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  r + 150;
                g =  0;
                b =  0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        im.setImageBitmap(operation);
    }

    public void green(View view){
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  0;
                g =  g+150;
                b =  0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        im.setImageBitmap(operation);
    }

    public void blue(View view){
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r =  0;
                g =  0;
                b =  b+150;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        im.setImageBitmap(operation);
    }
}