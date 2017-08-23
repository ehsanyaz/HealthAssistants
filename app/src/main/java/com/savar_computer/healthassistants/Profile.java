package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Profile extends Activity
{
    Spinner spinnerHeight;
    ArrayList<String> HeightItems;
    Spinner spinnerWeight;
    ArrayList<String> WeightItems;

    private static int IMG_RESULT = 1;
    String ImageDecode;
    //ImageView imageViewLoad;
    Button LoadImage;
    Intent intent;
    String[] FILE;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ////////////////////////////////////////////////////////////////////////////////////////////Spinner
        spinnerHeight=findViewById(R.id.spinnerHeight);

        HeightItems = new ArrayList<String>();
        for (int i=0;i<10;i++)
            HeightItems.add("1.0"+i+" متر");
        for (int i=10;i<100;i++)
            HeightItems.add("1."+i+" متر");
        for (int i=0;i<10;i++)
            HeightItems.add("2.0"+i+" متر");
        for (int i=10;i<=50;i++)
            HeightItems.add("2."+i+" متر");
        ArrayAdapter arrayAdapterHeight = new ArrayAdapter(Profile.this, android.R.layout.simple_spinner_dropdown_item, HeightItems);
        spinnerHeight.setAdapter(arrayAdapterHeight);

        spinnerHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(Profile.this, item , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


        spinnerWeight=findViewById(R.id.spinnerWeight);

        WeightItems = new ArrayList<String>();
        for (int i=20;i<=200;i++)
            WeightItems.add(i+" کیلو گرم");

        ArrayAdapter arrayAdapterWeight = new ArrayAdapter(Profile.this, android.R.layout.simple_spinner_dropdown_item, WeightItems);
        spinnerWeight.setAdapter(arrayAdapterWeight);

        spinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(Profile.this, item , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////Image
        LoadImage = (Button)findViewById(R.id.btnImage);

        LoadImage.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        try
        {

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK && null != data)
            {


                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };


                Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();

//                imageViewLoad.setImageBitmap(BitmapFactory
//                        .decodeFile(ImageDecode));

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();
        }

    }

}// end of class
