package com.example.acolhe_pet_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DenunciaAdapter extends ArrayAdapter<ModeloDenuncia> {

    private Context context;
    private List<ModeloDenuncia> listaDenuncia = null;
    public DenunciaAdapter(Context context, List<ModeloDenuncia> listaDenuncia) {
        super(context, 0, listaDenuncia);
        this.listaDenuncia = listaDenuncia;
        this.context = context;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        ModeloDenuncia dadosDenuncia = listaDenuncia.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_denuncia, null);
        }

        //Ligar as variáveis dos TextViews aos seus IDs que estão em "item_denuncia"
        TextView txtCONIdDenuncia = (TextView) view.findViewById(R.id.txtCONIdDenuncia);
        TextView txtCONEmail = (TextView) view.findViewById(R.id.txtCONEmail);
        TextView txtCONData = (TextView) view.findViewById(R.id.txtCONData);
        TextView txtCONNumero = (TextView) view.findViewById(R.id.txtCONNumero);
        TextView txtCONRua = (TextView) view.findViewById(R.id.txtCONRua);
        TextView txtCONBairro = (TextView) view.findViewById(R.id.txtCONBairro);
        TextView txtCONCidade = (TextView) view.findViewById(R.id.txtCONCidade);
        TextView txtCONDescricaoProblema = (TextView) view.findViewById(R.id.txtCONDescricaoProblema);
        TextView txtCONUrgencia = (TextView) view.findViewById(R.id.txtCONUrgencia);
        TextView txtCONCelularDenun = (TextView) view.findViewById(R.id.txtCONCelularDenun);
        TextView txtCONNomeDenun = (TextView) view.findViewById(R.id.txtCONNomeDenun);

        //Ligar os TextViews do arquivo "item_denuncia" aos métodos gets do arquivo "ModeloDenuncia".
        //O "CON" vem de "consulta".
        txtCONIdDenuncia.setText(String.valueOf(dadosDenuncia.getIdDenuncia()));
        txtCONEmail.setText(String.valueOf(dadosDenuncia.getEmail()));
        txtCONData.setText(String.valueOf(dadosDenuncia.getData()));
        txtCONNumero.setText(String.valueOf(dadosDenuncia.getNumero()));
        txtCONRua.setText(String.valueOf(dadosDenuncia.getRua()));
        txtCONBairro.setText(String.valueOf(dadosDenuncia.getBairro()));
        txtCONCidade.setText(String.valueOf(dadosDenuncia.getCidade()));
        txtCONDescricaoProblema.setText(String.valueOf(dadosDenuncia.getDescricaoProblema()));
        txtCONUrgencia.setText(String.valueOf(dadosDenuncia.getUrgencia()));
        txtCONCelularDenun.setText(String.valueOf(dadosDenuncia.getCelularDenun()));
        txtCONNomeDenun.setText(String.valueOf(dadosDenuncia.getNomeDenun()));

        return view;
    }

}