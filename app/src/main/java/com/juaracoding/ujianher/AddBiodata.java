package com.juaracoding.ujianher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddBiodata extends AppCompatActivity {
    private ArrayList<ModelBiodata> soal ;
    EditText txtNama,txtAlamat;
    Button btnTambah;

    public static int RESULT_CODE =222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biodata);

        txtNama = findViewById(R.id.txtNama);
        txtAlamat = findViewById(R.id.txtAlamat);

        btnTambah = findViewById(R.id.btnAdd);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelBiodata model = new ModelBiodata();
                model.setNama(txtNama.getText().toString());
                model.setAlamat(txtAlamat.getText().toString());
                soal.add(model);
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("data",soal);
                setResult(RESULT_CODE,intent);
                finish();
            }
        });
        soal = getIntent().getParcelableArrayListExtra("data");



    }
}
