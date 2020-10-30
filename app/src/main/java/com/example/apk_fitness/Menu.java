package com.example.apk_fitness;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;
public class Menu extends AppCompatActivity {
    private ViewFlipper vf;
    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        vf = (ViewFlipper)findViewById(R.id.slider);
        for(int i = 0; i < images.length; i++)
        {
            flip_image(images[i]);
        }
    }
    // función que me permitirá configurar el slider
    public void flip_image(int i)
    {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);

        vf.addView(view);           // añadimos al viewflipper el contenido del slider
        vf.setFlipInterval(2800);   // duración intervalo de imagenes
        vf.setAutoStart(true);     // inicia de forma automatica.

        // Sentido al slider
        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    // Intent Explícito.
    public  void Insumos (View view){
        Intent i = new Intent(this, Insumos.class);
        startActivity(i);
    }
    public void Info(View v)
    {
        Intent i = new Intent(this, Info.class);
        startActivity(i);
    }

    public void Maps(View v)
    {
        Intent i = new Intent(this, GoogleMaps.class);
        startActivity(i);
    }

}