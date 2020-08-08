package ugm.fznzz.nightowlproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import ugm.fznzz.nightowlproject.dataclass.desiredRoom;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.Delayed;
import java.util.stream.IntStream;

public class Waiting extends AppCompatActivity{

    TextView status;
    FirebaseDatabase database;
    DatabaseReference destination;
    String[] kodeRuangan, kodeOnline;
    String TAG = "MyActivity";
    String kode;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        //Passing kode ruangan
        TextView tv = findViewById(R.id.tv_testingKode);
        kode= getIntent().getExtras().getString("kode");
        tv.setText(kode);

        //Declare shit
        status = findViewById(R.id.txt_isi);
        database = FirebaseDatabase.getInstance();
        destination = database.getReference("desired_room");
        kodeRuangan = getResources().getStringArray(R.array.kodeRuangan);
        kodeOnline = getResources().getStringArray(R.array.kodeOnline);
    }

    public void upDestination(String dest)
    {
        destination.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                desiredRoom room = new desiredRoom(dest);
                destination.setValue(room);
                Log.w(TAG, "data changed to:"+dest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Log.d(TAG, "upDestination listener cancelled");
            }
        });
    }

    public void repeater(String starting)
    {
        String newCode="asd";
        destination.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(starting==newCode)
        {
            //do this
        }
        else
        {
            //do that
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
        upDestination(a);
    }

    public void backClick(View v)
    {
        finish();
    }
}
