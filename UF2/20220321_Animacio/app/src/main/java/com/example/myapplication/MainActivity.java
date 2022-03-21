package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener {

    ImageView imvSubZero;
    RelativeLayout rtlRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //-----------------------------------------
        imvSubZero = this.findViewById(R.id.imvSubZero);
        AnimationDrawable animacio = (AnimationDrawable)imvSubZero.getDrawable();
        animacio.start();
        //-----------------------------------------.
        rtlRoot = this.findViewById(R.id.rtlRoot);
        rtlRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        // desactivemn el layout
        rtlRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);

        //-------------------------------------------
        // Animacions amb ObjectAnimator

        int ampladaPantalla =  rtlRoot.getWidth();
        int ampladaSubzero = imvSubZero.getWidth();
        float posicioSubzero = imvSubZero.getX();
        float desplacament = ampladaPantalla-posicioSubzero-ampladaSubzero;

        ObjectAnimator a1 = ObjectAnimator.ofFloat(imvSubZero,"translationX",desplacament);//desplacament);
        a1.setDuration(2000);
        ObjectAnimator a2 = ObjectAnimator.ofFloat(imvSubZero, "rotationY", 180);
        a2.setDuration(1);
        ObjectAnimator a3 = ObjectAnimator.ofFloat(imvSubZero,"translationX",0);//desplacament);
        a3.setDuration(2000);


        ObjectAnimator a4 = ObjectAnimator.ofFloat(imvSubZero,"alpha",0, 1);//desplacament);
        a4.setDuration(4000);
        a4.setInterpolator(new LinearInterpolator());
        AnimatorSet as1 = new AnimatorSet();
        as1.playSequentially(a1,a2,a3);
        AnimatorSet as2 = new AnimatorSet();
        as2.playTogether(as1, a4);

        as2.start();
        as2.addListener(new VagoAnimator() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imvSubZero.setImageResource(R.drawable.animacio_idle);
                AnimationDrawable animacio = (AnimationDrawable)imvSubZero.getDrawable();
                animacio.start();
            }
        });

    }
}