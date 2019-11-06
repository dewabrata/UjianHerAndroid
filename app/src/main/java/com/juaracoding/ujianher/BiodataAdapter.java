package com.juaracoding.ujianher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BiodataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<ModelBiodata> dataItemList;

    public ArrayList<ModelBiodata> getDataItemList() {
        return dataItemList;
    }

    public BiodataAdapter(ArrayList<ModelBiodata> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



            this.context = parent.getContext();

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_biodata, parent, false);
            Penampung penampung = new Penampung(view);
            return penampung;


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


            ((Penampung)holder).txtNama.setText(dataItemList.get(position).getNama());
            ((Penampung)holder).txtAlamat.setText(dataItemList.get(position).getAlamat());







    }



    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }


    static class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNama,txtAlamat;


        public Penampung(View itemView) {
            super(itemView);
          //  itemView.setOnClickListener(this);
            txtNama = (TextView) itemView.findViewById(R.id.nama);
            txtAlamat = (TextView) itemView.findViewById(R.id.txtAlamat);

        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + txtNama.getText());
        }
    }


}
