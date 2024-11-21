package com.example.seminar_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter
{
    private List<ImaginiRezervare> imagini;

    private Context ctx;

    private int resurseLayout;

    public ImagineAdapter(Context ctx, int resurseLayout, List<ImaginiRezervare> imagini) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resurseLayout = resurseLayout;
    }


    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
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

        ImaginiRezervare item=imagini.get(i);

        ImageView img=v.findViewById(R.id.imagineIV);
        TextView text=v.findViewById(R.id.textImgTV);

        img.setImageBitmap(item.getImagine());
        text.setText(item.getTextAfisat());

        return v;
    }
}
