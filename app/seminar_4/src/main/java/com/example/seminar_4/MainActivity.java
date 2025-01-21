package com.example.seminar_4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Rezervare> rezervari=null;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("rezervari");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Toast.makeText(MainActivity.this,
                        "Baza de date Firebase a fost actualizata!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,
                        "Eroare la citirea din Firebase: " + error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        rezervari=new ArrayList<>();
        rezervari.add(new Rezervare("Fotbal",true,"Rares",1));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it=new Intent(getApplicationContext(),FormularActivitate.class) ;
                startActivityForResult(it,200);
            }
        });

        Button adaugare=findViewById(R.id.button2);
        adaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListaRezervari.class);
                it.putParcelableArrayListExtra("rezervari", (ArrayList<? extends Parcelable>) rezervari);
                startActivity(it);
            }
        });

        Button imagini = findViewById(R.id.button3);
        imagini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListaImagini.class);
                startActivity(it);

            }
        });

        Button json = findViewById(R.id.button4);
        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), interactiuneJson.class);
                startActivity(it);

            }
        });

        Button btnFav=findViewById(R.id.btnFav);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });

        Button btnTest=findViewById(R.id.button5);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(getApplicationContext(), Firebase.class);
                startActivity(it);
            }
        });

        Button btnDesenare=findViewById(R.id.button6);
        btnDesenare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(it);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200)
        {
            if(resultCode==RESULT_OK)
            {
                Rezervare r=data.getParcelableExtra("rezervare");
                rezervari.add(r);
            }
        }
    }


}