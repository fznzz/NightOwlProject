package ugm.fznzz.nightowlproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ugm.fznzz.nightowlproject.dataclass.desiredRoom;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Objects;

public class Waiting extends AppCompatActivity{

    TextView status, ip, loc;
    FirebaseDatabase database;
    DatabaseReference destination;
    String[] kodeRuangan, kodeOnline;
    String TAG = "MyActivity";
    String kode;
    desiredRoom a, b;
/*    AnimationDrawable anim;*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        //Passing kode ruangan
//        TextView tv = findViewById(R.id.tv_testingKode);
        kode= Objects.requireNonNull(getIntent().getExtras()).getString("kode");
//        tv.setText(kode);

/*        //animation
        ImageView img = findViewById(R.id.imageView);
        anim = (AnimationDrawable) img.getDrawable();*/

        //Declare shit
//        status = findViewById(R.id.txt_isi);
//        ip = findViewById(R.id.txt_ip);
//        loc = findViewById(R.id.txt_loc);
        database = FirebaseDatabase.getInstance();
        destination = database.getReference("desired_room");
        kodeRuangan = getResources().getStringArray(R.array.kodeRuangan);
        kodeOnline = getResources().getStringArray(R.array.kodeOnline);

//        status.setText(kode);

        String a = selector(kode);
        upDestination(a,"escorting");
    }
    public void upDestination(String dest, String rState)
    {
        desiredRoom room = new desiredRoom(dest, rState);
        destination.setValue(room);
        destination.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                a = new desiredRoom("lobby","homing");
                b = snapshot.getValue(desiredRoom.class);
                assert b != null;
//                loc.setText(b.getRoom());
//                ip.setText(a.getRoom());
                resetApp(a,b);
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public void upDestination2(String dest, String rState)
    {
        desiredRoom room = new desiredRoom(dest,rState);
        destination.setValue(room);
    }

    public void resetApp(desiredRoom a, desiredRoom b)
    {
        Log.w(TAG,"exec fkin shit, here's the bonus :"+a.getRoom()+a.getState()+" vs "+b.getRoom()+b.getState());
        if(a.getRoom().equals(b.getRoom())&&a.getState().equals(b.getState()))
        {
            Log.w(TAG,"successfully entered IF");
            Intent i = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    public static <String> int indexOf(String[] arr, String val)
    {
        return Arrays.asList(arr).indexOf(val);
    }

    public String selector(String code)
    {
        int i = indexOf(kodeRuangan,code);
        return kodeOnline[i];
    }

    public void copyClick(View v)
    {
        status.setText(kode);
    }

    public void sendClick(View v)
    {
        String a = selector(status.getText().toString());
        upDestination(a,"escorting");
    }

    public void backClick(View v)
    {
        finish();
    }

    public void homeClick(View v)
    {
        upDestination2("lobby","homing");
    }

/*    public void start(View v)
    {
        //asd
        //asd
        anim.start();
    }

    public void stop(View v)
    {
        //asd
        //asdasdasd
        anim.stop();
    }*/
}
