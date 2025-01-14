package com.example.seminar_4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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

public class Firebase extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference databaseReference;
    private List<Rezervare> ListaRezervari;
    private ArrayAdapter<Rezervare> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        listView = findViewById(R.id.firebaseListView);
        ListaRezervari = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListaRezervari);
        listView.setAdapter(adapter);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("components");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListaRezervari.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Rezervare component = dataSnapshot.getValue(Rezervare.class);
                    if (component != null) {
                        ListaRezervari.add(component);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Firebase.this, "Eroare la citirea din Firebase: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}