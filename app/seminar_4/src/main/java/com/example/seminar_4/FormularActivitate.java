package com.example.seminar_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormularActivitate extends AppCompatActivity {

    private RezervariDataBase dataBase=null;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_activitate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseApp.initializeApp(this);
        dataBase = Room.databaseBuilder(this, RezervariDataBase.class, "InsulaDB").build();
        databaseReference = FirebaseDatabase.getInstance().getReference("insula");

        Intent it=getIntent();
        if(it.hasExtra("rezervare"))
        {
            Rezervare r=it.getParcelableExtra("rezervare");
            EditText IDEt=findViewById(R.id.id);
            EditText activitateET=findViewById(R.id.activitate);
            EditText numeET=findViewById(R.id.nume);
            CheckBox stareCB=findViewById(R.id.activa);

            IDEt.setText(r.getId());
            activitateET.setText(r.getActivitate());
            numeET.setText(r.getNume_client());
            stareCB.setChecked(r.getStare());
        }


        Button btnAdaugare=findViewById(R.id.button2);
        btnAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etID=findViewById(R.id.id);
                int id=Integer.parseInt(etID.getText().toString());

                EditText etActivitate=findViewById(R.id.activitate);
                String activitate=etActivitate.getText().toString();

                EditText etNume=findViewById(R.id.nume);
                String nume=etNume.getText().toString();

                CheckBox cbStare = findViewById(R.id.activa);
                boolean stare = cbStare.isChecked();

                Rezervare rezervare=new Rezervare(activitate,stare,nume,id);

                CheckBox cbOnline = findViewById(R.id.checkBox2);
                if(cbOnline.isChecked())
                {
                    databaseReference.push().setValue(rezervare)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(FormularActivitate.this, "Componenta salvata cu succes în Firebase", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(FormularActivitate.this, "Eroare la salvarea in Firebase: " + task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
                Intent it=new Intent();
                it.putExtra("rezervare",rezervare);
                setResult(RESULT_OK,it);
                finish();

            }
        });
    }
}