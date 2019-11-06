package com.juaracoding.ujianher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.developer.filepicker.controller.DialogSelectionListener;
import com.developer.filepicker.model.DialogConfigs;
import com.developer.filepicker.model.DialogProperties;
import com.developer.filepicker.view.FilePickerDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    Button btnTambah, btnImport;
    private ArrayList<ModelBiodata> soal ;

    public static int REQUEST_CODE = 111;



    RecyclerView lstData ;
    BiodataAdapter itemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        lstData = findViewById(R.id.lstData);
        btnTambah = findViewById(R.id.btnAdd);
        btnImport = findViewById(R.id.btnImport);

        soal = new ArrayList<ModelBiodata>();

        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;

        final FilePickerDialog dialog = new FilePickerDialog(MainActivity.this,properties);
        dialog.setTitle("Pilih file csv");

        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                //files is the array of the paths of files selected by the Application User.
              ArrayList<ModelBiodata> dummy =   baca(files[0]);

              for(int x = 0 ;x < dummy.size();x++){
                  soal.add(dummy.get(x));
              }
                setList(soal);
            }
        });

        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        Intent intent = new Intent(MainActivity.this,AddBiodata.class);
                        intent.putParcelableArrayListExtra("data",soal);
                        startActivityForResult(intent,REQUEST_CODE);


            }
        });
    }

    public ArrayList<ModelBiodata> baca(String fileName) {
        ArrayList<ModelBiodata> todoList = new ArrayList<>();

        try {


            Scanner scanner = new Scanner(new File(fileName));
            Scanner valueScanner = null;
            int index = 0;


            while (scanner.hasNextLine()) {
                valueScanner = new Scanner(scanner.nextLine());
                valueScanner.useDelimiter(";");
                ModelBiodata soal = new ModelBiodata();

                while (valueScanner.hasNext()) {
                    String data = valueScanner.next();
                    if (index == 0) {
                        soal.setNama(data);
                    } else if (index == 1) {
                        soal.setAlamat(data);
                    }
                    index++;
                }
                index = 0;
                todoList.add(soal);
            }

            scanner.close();
        } catch (IOException e) {

        }

        return todoList;
    }

    public void setList(ArrayList<ModelBiodata>soal ){
        itemArrayAdapter = new BiodataAdapter(soal);

        lstData.setLayoutManager(new LinearLayoutManager(this));
        lstData.setItemAnimator(new DefaultItemAnimator());
        lstData.setAdapter(itemArrayAdapter);
        lstData.invalidate();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == AddBiodata.RESULT_CODE) {
            soal = data.getParcelableArrayListExtra("data");
            setList(soal);
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
}
