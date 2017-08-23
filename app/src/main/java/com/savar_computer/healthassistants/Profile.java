package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Profile extends Activity
{
    private Spinner spinnerHeight;
    private ArrayList<String> HeightItems;
    private Spinner spinnerWeight;
    private ArrayList<String> WeightItems;

    private EditText edtName;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private Button btnAdd;

    private String name,height,weight,sex;
    SharedPreferences.Editor edit=Splash.sharedPreferences.edit();


    private static int IMG_RESULT = 1;
    private String ImageDecode;
    private ImageView imageViewLoad;
    private Button LoadImage;
    private Intent intent;
    private String[] FILE;

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ////////////////////////////////////////////////////////////////////////////////////////////EditText
        edtName=(EditText) findViewById(R.id.edtName);

        ////////////////////////////////////////////////////////////////////////////////////////////Spinner Height
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
                height=adapterView.getItemAtPosition(i).toString();
                edit.putString("height",height);
                edit.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////Spinner Weight
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
                weight=adapterView.getItemAtPosition(i).toString();
                edit.putString("weight",weight);
                edit.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////RadioGroup
        radioButtonMale=(RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale=(RadioButton) findViewById(R.id.radioButtonFemale);

        radioButtonMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    sex = "male";
                    edit.putString("sex",sex);
                    edit.commit();
                }
            }
        });

        radioButtonFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    sex = "female";
                    edit.putString("sex",sex);
                    edit.commit();
                }
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////Image
        imageViewLoad = (ImageView) findViewById(R.id.image);
        LoadImage = (Button)findViewById(R.id.btnImage);

        LoadImage.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////Button Add
        btnAdd=(Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name=edtName.getText().toString();
                edit.putString("name",name);
                edit.commit();

                if (Splash.sharedPreferences.getString("name",null)==null || Splash.sharedPreferences.getString("height",null)==null || Splash.sharedPreferences.getString("weight",null)==null
                        || Splash.sharedPreferences.getString("sex",null)==null)
                {
                    Toast.makeText(Profile.this,"لطفاٌ اطلاعات را تکمیل نمایید",Toast.LENGTH_LONG).show();
                }
                else
                {
                    edit.putBoolean("flag", false);
                    edit.commit();
                    intent = new Intent(Profile.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }// end of onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            if (requestCode == SELECT_PICTURE)
            {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                imageViewLoad.setImageURI(selectedImageUri);
            }
        }

    }

    public String getPath(Uri uri)
    {
        // just some safety built in
        if( uri == null )
        {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null )
        {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }


}// end of class
