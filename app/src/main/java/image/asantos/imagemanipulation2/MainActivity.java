package image.asantos.imagemanipulation2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageView;

/*


   BLACK       = 0xFF000000;
   WHITE       = 0xFFFFFFFF;

   DKGRAY      = 0xFF444444;
   GRAY        = 0xFF888888;
   LTGRAY      = 0xFFCCCCCC;

   RED         = 0xFFFF0000;
   GREEN       = 0xFF00FF00;
   BLUE        = 0xFF0000FF;

   YELLOW      = 0xFFFFFF00;
   CYAN        = 0xFF00FFFF;
   MAGENTA     = 0xFFFF00FF;

   TRANSPARENT = 0;

Bright = RGB + 100
Dark   = RGB - 50

Red    = Red   + 150
Green  = Green + 150
Blue   = Blue  + 150


 */
public class MainActivity extends AppCompatActivity {

    ImageView im;

    private Bitmap bmp;
    private Bitmap operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im = (ImageView) findViewById(R.id.imageView);

        BitmapDrawable abmp = (BitmapDrawable) im.getDrawable();
        bmp = abmp.getBitmap();
    }

    private int calculateAvgGreyColor(int i, int j, int pixelSize) {
        int p = bmp.getPixel(i, j);
        int a = Color.alpha(p);


        int r = (Color.red(p) + Color.green(p) + Color.blue(p)) / 3;
        int g = r;
        int b = r;

//        int r = Color.red(p);
//        int g = Color.green(p);
//        int b = Color.blue(p);

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
        operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        int pixelSize = 30;
        for (int i = 0; i < bmp.getWidth(); i = i + pixelSize) {
            for (int j = 0; j < bmp.getHeight(); j = j + pixelSize) {

                int avg = calculateAvgGreyColor(i, j, pixelSize);

                // apply the average color
                for (int pi = 0; pi < pixelSize; pi++) {
                    for (int pj = 0; pj < pixelSize; pj++) {
                        if (i + pi < bmp.getWidth() &&  j + pj < bmp.getHeight()) {
                            operation.setPixel(i + pi, j + pj, avg);
                        }
                    }
                }
            }
        }
        im.setImageBitmap(operation);
    }

    public void original(View view) {
        im.setImageBitmap(bmp);
    }

    public void gray(View view) {
        operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                int p = bmp.getPixel(i, j);

                int r = (Color.red(p) + Color.green(p) + Color.blue(p)) / 3;
                int g = r;
                int b = r;

                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));

            }
        }
        im.setImageBitmap(operation);
    }

}