package com.example.loophole;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class Welcome extends AppCompatActivity {

    Button register;
    Button login;
    TextView title;
    LottieAnimationView loophole_animation;
    LottieAnimationView plane_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        register = findViewById(R.id.id_welcome_signup);
        login = findViewById(R.id.id_welcome_login);
        title = findViewById(R.id.id_welcome_title);
        loophole_animation = (findViewById(R.id.id_loopholeanimation));
        plane_animation = (findViewById(R.id.id_planeanimation));
        loophole_animation.setAnimation(R.raw.loophole_animation);
        loophole_animation.setRepeatMode(LottieDrawable.RESTART);
        plane_animation.setAnimation(R.raw.plane);
        plane_animation.setRepeatCount(50);
        plane_animation.setScale((float)(0.8));
        plane_animation.setY(-200);
        loophole_animation.playAnimation();
        plane_animation.playAnimation();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, Register.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, Login.class));
            }
        });
    }
}
