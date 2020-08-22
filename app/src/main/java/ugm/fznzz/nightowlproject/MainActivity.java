package ugm.fznzz.nightowlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Boolean newact=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(30000, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                if(newact==false)
                {
                    Intent i = new Intent(getApplicationContext(),IdleActivity.class);
                    startActivity(i);
                }
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
    }

    public void faqClick(View view){
        newact=true;
        Intent startIntent = new Intent(getApplicationContext(), FAQ.class);
        startActivity(startIntent);
    }
    public void guideClick(View view){
        newact=true;
        Intent startIntent = new Intent(getApplicationContext(), Guide.class);
        startActivity(startIntent);
    }
}
