package com.example.seminar_4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaImagini extends AppCompatActivity {

    List<Bitmap> imagini = null;
    List<ImaginiRezervare> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini = new ArrayList<>();
        imagini = new ArrayList<>();
        linkuriImagini.add("https://www.rinairporthotel.ro/wp-content/uploads/2020/11/rustic-3-min.png");
        linkuriImagini.add("https://st5.depositphotos.com/1018728/74676/i/600/depositphotos_746763058-stock-photo-bowling-ball-rolling-lane-tenpin.jpg");
        linkuriImagini.add("https://cdn.pixabay.com/photo/2019/03/08/20/17/beauty-salon-4043096_1280.jpg");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo4QtrYJ7GTiilFDvoccRn9OmmmtLj9_9Usg&s");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH80ulcsaASMdceXjz8nz_3SCwzZqYG6198g&s");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();

                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));

                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imagini == null || imagini.isEmpty()) {
                            Log.e("DataError", "No images were retrieved");
                            return;
                        }
                        lista = new ArrayList<>();
                        lista.add(new ImaginiRezervare("Rezervare 1",imagini.get(0),"https://www.rinairporthotel.ro/wp-content/uploads/2020/11/rustic-3-min.png"));
                        lista.add(new ImaginiRezervare("Rezervare 2",imagini.get(1),"https://st5.depositphotos.com/1018728/74676/i/600/depositphotos_746763058-stock-photo-bowling-ball-rolling-lane-tenpin.jpg"));
                        lista.add(new ImaginiRezervare("Rezervare 3",imagini.get(2),"https://cdn.pixabay.com/photo/2019/03/08/20/17/beauty-salon-4043096_1280.jpg"));
                        lista.add(new ImaginiRezervare("Rezervare 4",imagini.get(3),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo4QtrYJ7GTiilFDvoccRn9OmmmtLj9_9Usg&s"));
                        lista.add(new ImaginiRezervare("Rezervare 5",imagini.get(4),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH80ulcsaASMdceXjz8nz_3SCwzZqYG6198g&s"));

                        ListView lv = findViewById(R.id.imagini);
                        ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(), R.layout.layout_img, lista);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });



        ListView lv = findViewById(R.id.imagini);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActiv.class);
                it.putExtra("link",lista.get(i).getLink());
                startActivity(it);
            }
        });

    }
}