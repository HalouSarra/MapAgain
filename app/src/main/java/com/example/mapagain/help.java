package com.example.mapagain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class help extends AppCompatActivity {

    private ImageSwitcher imageSwitcher1,imageSwitcher2,imageSwitcher3,imageSwitcher4;

    private int[] images1 = {
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
    };

    private int[] images2 = {
            R.drawable.p9,
            R.drawable.p10,
            R.drawable.p11,
            R.drawable.p12,
    };

    private int[] images3 = {
            R.drawable.p22,
            R.drawable.p23,
            R.drawable.p24,
            R.drawable.p25,
            R.drawable.p26,

    };

    private int[] images4 = {
            R.drawable.p18,
            R.drawable.p19,
            R.drawable.p20,
            R.drawable.p21
    };

    private int currentIndex1 = 0;
    private int currentIndex2 = 0;
    private int currentIndex3 = 0;
    private int currentIndex4 = 0;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);



        imageSwitcher1 = findViewById(R.id.imageSwitcher1);
        imageSwitcher2 = findViewById(R.id.imageSwitcher2);
        imageSwitcher3 = findViewById(R.id.imageSwitcher3);
        imageSwitcher4 = findViewById(R.id.imageSwitcher4);







        imageSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ImageSwitcher.LayoutParams.MATCH_PARENT,
                        ImageSwitcher.LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        Animation slideInAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher1.setInAnimation(slideInAnimation);
        imageSwitcher1.setOutAnimation(slideOutAnimation);

        imageSwitcher1.setImageResource(images1[currentIndex1]);

        imageSwitcher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex1++;
                if (currentIndex1 == images1.length) {
                    currentIndex1 = 0;
                }
                imageSwitcher1.setImageResource(images1[currentIndex1]);
            }
        });


        imageSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ImageSwitcher.LayoutParams.MATCH_PARENT,
                        ImageSwitcher.LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        Animation slideInAnimation2 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation2 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher2.setInAnimation(slideInAnimation2);
        imageSwitcher2.setOutAnimation(slideOutAnimation2);

        imageSwitcher2.setImageResource(images2[currentIndex2]);

        imageSwitcher2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex2++;
                if (currentIndex2 == images2.length) {
                    currentIndex2 = 0;
                }
                imageSwitcher2.setImageResource(images2[currentIndex2]);
            }
        });


        imageSwitcher3.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ImageSwitcher.LayoutParams.MATCH_PARENT,
                        ImageSwitcher.LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        Animation slideInAnimation3 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation3 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher3.setInAnimation(slideInAnimation3);
        imageSwitcher3.setOutAnimation(slideOutAnimation3);

        imageSwitcher3.setImageResource(images3[currentIndex3]);

        imageSwitcher3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex3++;
                if (currentIndex3 == images3.length) {
                    currentIndex3 = 0;
                }
                imageSwitcher3.setImageResource(images3[currentIndex3]);
            }
        });


        imageSwitcher4.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ImageSwitcher.LayoutParams.MATCH_PARENT,
                        ImageSwitcher.LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        Animation slideInAnimation4 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation4 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher4.setInAnimation(slideInAnimation4);
        imageSwitcher4.setOutAnimation(slideOutAnimation4);

        imageSwitcher4.setImageResource(images4[currentIndex4]);

        imageSwitcher4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex4++;
                if (currentIndex4 == images4.length) {
                    currentIndex4 = 0;
                }
                imageSwitcher4.setImageResource(images4[currentIndex4]);
            }
        });

    }
}