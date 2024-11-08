package com.example.seminar_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.seminar_4.R;

import java.util.ArrayList;
import java.util.List;

public class ListaRezervari extends AppCompatActivity {

    private List<Rezervare> rezervari=null;
    private int idModificat=0;

    private AdapterRezervare adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_rezervari);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it=getIntent();
        List<Rezervare> rezervari= it.getParcelableArrayListExtra("rezervari");

        ListView lv=findViewById(R.id.LVrezervari);

        adapter=new AdapterRezervare(getApplicationContext(),R.layout.layout_listview,rezervari);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentmodify=new Intent(getApplicationContext(),FormularActivitate.class);
                intentmodify.putExtra("rezervari",rezervari.get(i).getId());
                idModificat=i;
                startActivityForResult(intentmodify,300);
                Toast.makeText(getApplicationContext(),rezervari.get(i).getId(),Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK)
        {
            if(requestCode==300)
            {
                rezervari.set(idModificat,data.getParcelableExtra("rezervari"));
                adapter.notifyDataSetChanged();
            }
        }

    }
}