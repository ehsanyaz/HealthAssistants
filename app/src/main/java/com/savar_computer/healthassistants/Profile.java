package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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

    private Button LoadImage;
    private Intent intent;

    private int PICK_IMAGE_REQUEST = 1;

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
        LoadImage = (Button)findViewById(R.id.btnImage);

        LoadImage.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {

            Uri uri = data.getData();

            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.Image);
                imageView.setImageResource(0);
                imageView.setBackgroundResource(R.drawable.white);
                imageView.setImageBitmap(bitmap);

                edit.putString("image",encodeTobase64(bitmap));
                edit.commit();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immage = image;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

}// end of class
