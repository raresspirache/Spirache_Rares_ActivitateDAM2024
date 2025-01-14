package com.example.seminar_13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormularAdaugare extends AppCompatActivity {

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_adaugare);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        Intent it = getIntent();
        if (it.hasExtra("student")) {
            Student student = (Student) it.getSerializableExtra("student");
            EditText etNume = findViewById(R.id.editNume);
            EditText etVarsta = findViewById(R.id.editVarsta);
            EditText etNrMatricol = findViewById(R.id.editNrMatricol);
            CheckBox cbDisponibil = findViewById(R.id.checkBox);

            etNume.setText(student.getNume());
            etVarsta.setText(String.valueOf(student.getVarsta()));
            etNrMatricol.setText(String.valueOf(student.getNrMatricol()));
            cbDisponibil.setChecked(student.isDisponibil());
        }

        Button btnFinalizare = findViewById(R.id.button);
        btnFinalizare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etNume = findViewById(R.id.editNume);
                String nume = etNume.getText().toString();

                EditText etVarsta = findViewById(R.id.editVarsta);
                int varsta = 0;
                try {
                    varsta = Integer.parseInt(etVarsta.getText().toString());
                } catch (NumberFormatException e) {
                    etVarsta.setError("Introduceți o valoare validă pentru vârstă!");
                    return;
                }

                EditText etNrMatricol = findViewById(R.id.editNrMatricol);
                int nrMatricol = 0;
                try {
                    nrMatricol = Integer.parseInt(etNrMatricol.getText().toString());
                } catch (NumberFormatException e) {
                    etNrMatricol.setError("Introduceți un număr matricol valid!");
                    return;
                }

                CheckBox cbDisponibil = findViewById(R.id.checkBox);
                boolean disponibil = cbDisponibil.isChecked();


                Student student = new Student(nume, varsta, nrMatricol, disponibil);

                if (disponibil) {
                    databaseReference.push().setValue(student)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(FormularAdaugare.this, "Student salvat cu succes în Firebase", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FormularAdaugare.this,
                                            "Eroare la salvarea în Firebase: " + task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                }

                Intent it = new Intent();
                it.putExtra("student", student);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}
