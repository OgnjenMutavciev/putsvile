package com.example.projekat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RadnikAdapter extends BaseAdapter {

    ArrayList<RadnikModel> listaRadnik;
    Context context;

    public RadnikAdapter(Context context) {
        listaRadnik = new ArrayList<>();
        this.context = context;
    }

    public void ubaciElem(String naziv, String cena, String ocena) {
        listaRadnik.add(new RadnikModel(naziv, cena, ocena));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listaRadnik.size();
    }

    @Override
    public Object getItem(int i) {
        return listaRadnik.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.radnici_lista, null);
        }

        TextView naziv = view.findViewById(R.id.naziv);
        TextView cena = view.findViewById(R.id.cena);
        TextView ocena = view.findViewById(R.id.ocena);
        RadnikModel zadatak = (RadnikModel) getItem(i);

        naziv.setText(zadatak.getNaziv());
        cena.setText(zadatak.getCena());
        ocena.setText(zadatak.getOcena());

        return view;
    }
}
