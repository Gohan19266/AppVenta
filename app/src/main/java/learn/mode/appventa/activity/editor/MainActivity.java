package learn.mode.appventa.activity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import learn.mode.appventa.LoginActivity;
import learn.mode.appventa.R;
import learn.mode.appventa.activity.editor.PrincipalActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    private Dialog dialog;
    private View view;
    private static int Tiempo= 4000;
    private static final int DURACION = 1000;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiempo(Tiempo);
        music();
        animat();

    }

    public void animat(){
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f,1.0f);
        fadeIn.setDuration(DURACION);
        fadeIn.setStartOffset(DURACION);
        fadeIn.setFillAfter(true);
        imgView = findViewById(R.id.IdLogo);
        final AlphaAnimation fadeOut = new AlphaAnimation(1.0f,0.0f);
        fadeOut.setDuration(DURACION);
        fadeOut.setStartOffset(DURACION);
        fadeOut.setFillAfter(true);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgView.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        imgView.startAnimation(fadeIn);
    }
    public void music(){
      /*  mp = MediaPlayer.create(this, R.raw.logosound);
        mp.start();*/
    }

    public void tiempo(int tiempito){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                changeActivity(view);
            }
        },tiempito);
    }
    public void changeActivity(View view){

        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



}
