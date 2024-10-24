package com.example.seminar4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularActivitate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formular_activitate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAdaugare=findViewById(R.id.button2);
        btnAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etID=findViewById(R.id.idET);
                int id=Integer.parseInt(etID.getText().toString());

                EditText etActivitate=findViewById(R.id.activitateET);
                String activitate=etActivitate.getText().toString();

                EditText etNume=findViewById(R.id.numeET);
                String nume=etActivitate.getText().toString();

                CheckBox cbStare = findViewById(R.id.activacb);
                boolean stare = cbStare.isChecked();

            }
        });
    }
}