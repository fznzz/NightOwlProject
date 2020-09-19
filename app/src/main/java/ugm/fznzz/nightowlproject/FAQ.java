package ugm.fznzz.nightowlproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ugm.fznzz.nightowlproject.faq.faq_adpJawaban;
import ugm.fznzz.nightowlproject.faq.faq_jawaban;
import ugm.fznzz.nightowlproject.faq.faq_pertanyaan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class FAQ extends AppCompatActivity {

    String[] ar_per;
    String[] ar_jaw;
    RecyclerView recyclerView;
    TextView asd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);

//        asd = findViewById(R.id.textViewjawabann);

        recyclerView = findViewById(R.id.rv_faq);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ar_per = getResources().getStringArray(R.array.ar_pertanyaan);
        ar_jaw = getResources().getStringArray(R.array.ar_jawaban);

        callFAQ();
    }

    public void callFAQ()
    {
        //Menghitung jumlah pertanyaan
        int length = ar_per.length;
        //Declare tempat membuat pertanyaan dan jawabannya untuk dimasukkan ke [1]
        faq_pertanyaan[] pertanyaan = new faq_pertanyaan[length];
        //Mengubah jawaban dari (string array) ke (data class array)
        faq_jawaban[] convertJawaban = new faq_jawaban[length];
        //Menaruh jawaban (data class array) yang telah diconvert ke (data class individual)
        ArrayList<faq_jawaban> jawaban = new ArrayList<>();
        for(int i = 0; i < length; i++)
        {
            convertJawaban[i] = new faq_jawaban(ar_jaw[i]);
        }
        for(int i = 0; i < length; i++)
        {
            jawaban= new ArrayList<>();
            jawaban.add(convertJawaban[i]);
            pertanyaan[i] = new faq_pertanyaan(ar_per[i], jawaban);
        }
        //[1] Declare tempat menaruh semua pertanyaan untuk dimasukkan ke adapter
        ArrayList<faq_pertanyaan> listPertanyaan = new ArrayList<>(Arrays.asList(pertanyaan).subList(0, length));
        faq_adpJawaban adapter = new faq_adpJawaban(listPertanyaan);
        recyclerView.setAdapter(adapter);
    }

    public void backClick(View v)
    {
        Intent i = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
