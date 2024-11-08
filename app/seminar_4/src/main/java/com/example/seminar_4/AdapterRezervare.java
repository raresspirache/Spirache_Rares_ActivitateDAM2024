package com.example.seminar_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class AdapterRezervare extends BaseAdapter
{
    private List<Rezervare> rezervari=null;
    private Context ctx;

    private int resurseLayout;
    public AdapterRezervare(Context ctx, int resurseLayout, List<Rezervare> rezervari)
    {
        this.rezervari = rezervari;
        this.ctx = ctx;
        this.resurseLayout = resurseLayout;
    }


    @Override
    public int getCount() {
        return rezervari.size();
    }

    @Override
    public Object getItem(int i) {
        return rezervari.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater= LayoutInflater.from(ctx);
        View v=inflater.inflate(resurseLayout,viewGroup,false);

        TextView idTV=v.findViewById(R.id.id);
        TextView activitateTV=v.findViewById(R.id.activitate);
        TextView numeTV=v.findViewById(R.id.nume);
        CheckBox stareCB=v.findViewById(R.id.activa);

        Rezervare r = (Rezervare)getItem(i);
        idTV.setText(r.getId());
        activitateTV.setText(r.getActivitate());
        numeTV.setText(r.getNume_client());
        stareCB.setChecked(r.getStare());

        return v;
    }
}
