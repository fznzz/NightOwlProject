package ugm.fznzz.nightowlproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ugm.fznzz.nightowlproject.guide.guide_adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Guide extends AppCompatActivity implements guide_adapter.OnNoteListener {

    String namaRuangan[],kodeRuangan[];
    String kode;
    RecyclerView rv;
    guide_adapter adp;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        namaRuangan = getResources().getStringArray(R.array.namaRuangan); //start of recyclerview code
        kodeRuangan = getResources().getStringArray(R.array.kodeRuangan);
        rv = findViewById(R.id.rv_guide);
        guide_adapter adapter = new guide_adapter(this,namaRuangan,kodeRuangan,this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this)); //end of recyclerview code
    }

    @Override
    public void onNoteClick(int position) {
        kode = kodeRuangan[position];
    }

    public void backClick(View v)
    {
        finish();
    }

    public void nextClick(View v)
    {
        if(kode==null)
        {
            snackbar = Snackbar.make(findViewById(R.id.coordinator),"Pilih tujuan terlebih dahulu!",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else {
            Intent startIntent = new Intent(getApplicationContext(), Waiting.class);
            startIntent.putExtra("kode",kode);
            startActivity(startIntent);
        }
    }
}
