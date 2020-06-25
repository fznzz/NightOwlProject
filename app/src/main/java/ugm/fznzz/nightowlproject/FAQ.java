package ugm.fznzz.nightowlproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ugm.fznzz.nightowlproject.faq.faq_adpJawaban;
import ugm.fznzz.nightowlproject.faq.faq_jawaban;
import ugm.fznzz.nightowlproject.faq.faq_pertanyaan;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class FAQ extends AppCompatActivity {

    String ar_per[],ar_jaw[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);

        ImageView btBack = findViewById(R.id.bt_back_faq);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rv_faq);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<faq_pertanyaan> pertanyaans = new ArrayList<>();

        ArrayList<faq_jawaban> faq1 = new ArrayList<>();
        faq1.add(new faq_jawaban("Jurusan Teknik Elektro dan Teknologi Informasi (JTETI) Fakultas Teknik Universitas Gadjah Mada \n" +
                "berdiri sejak tahun 1963, dengan nama Bagian Teknik Mesin dan Listrik. Sebutan “Jurusan” (menggantikan “Bagian”) mulai digunakan pada tahun 1980 mengikuti PP No. 5 tahun 1980, sedangkan nama “Teknik Elektro” (menggantikan nama “Listrik”), mulai digunakan tahun 1983 untuk menyesuaikan dengan SK Menteri Pendidikan dan Kebudayaan RI No. 0174/0/1983 tentang Penataan Jurusan pada Fakultas di Lingkungan Universitas/Institut Negeri."));
        faq_pertanyaan per1 = new faq_pertanyaan("Pertanyaan 1",faq1);

        ArrayList<faq_jawaban> faq2 = new ArrayList<>();
        faq2.add(new faq_jawaban("Jawaban kedua"));
        faq_pertanyaan per2 = new faq_pertanyaan("Pertanyaan 2",faq2);

        ArrayList<faq_jawaban> faq3 = new ArrayList<>();
        faq3.add(new faq_jawaban("Jawaban ketiga"));
        faq_pertanyaan per3 = new faq_pertanyaan("Pertanyaan 3",faq3);

        ArrayList<faq_jawaban> faq4 = new ArrayList<>();
        faq4.add(new faq_jawaban("Jawaban keempat"));
        faq_pertanyaan per4 = new faq_pertanyaan("Pertanyaan 4",faq4);

        pertanyaans.add(per1);
        pertanyaans.add(per2);
        pertanyaans.add(per3);
        pertanyaans.add(per4);


        faq_adpJawaban adapter = new faq_adpJawaban(pertanyaans);
        recyclerView.setAdapter(adapter);

    }
}
