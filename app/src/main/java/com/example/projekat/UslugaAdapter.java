package com.example.projekat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UslugaAdapter extends BaseAdapter {

    ArrayList<UslugaModel> listaUsluga;
    Context context;

    public UslugaAdapter(Context context) {
        listaUsluga = new ArrayList<>();
        this.context = context;
    }

    public void ubaciElem(String tip, String kolicina) {
        listaUsluga.add(new UslugaModel(tip, kolicina));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listaUsluga.size();
    }

    @Override
    public Object getItem(int i) {
        return listaUsluga.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.usluge_lista, null);
        }

        TextView tip = view.findViewById(R.id.tip);
        TextView kolicina = view.findViewById(R.id.kolicina);
        UslugaModel zadatak = (UslugaModel) getItem(i);

        tip.setText(zadatak.getTip());
        kolicina.setText(zadatak.getKolicina());

        return view;
    }
}
