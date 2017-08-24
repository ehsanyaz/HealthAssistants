package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class EditProfile extends Activity
{
    private Spinner spinnerHeight2;
    private ArrayList<String> HeightItems;
    private Spinner spinnerWeight2;
    private ArrayList<String> WeightItems;

    private EditText edtName2;
    private RadioButton radioButtonMale2;
    private RadioButton radioButtonFemale2;
    private Button btnAdd2;
    private ImageView imageView;

    private String name,height,weight,sex;
    SharedPreferences.Editor edit=Splash.sharedPreferences.edit();

    private int PICK_IMAGE_REQUEST = 1;

    private Button LoadImage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        ////////////////////////////////////////////////////////////////////////////////////////////EditText
        edtName2=(EditText) findViewById(R.id.edtName2);
        edtName2.setText(Splash.sharedPreferences.getString("name",null));

        ////////////////////////////////////////////////////////////////////////////////////////////Spinner Height
        spinnerHeight2=findViewById(R.id.spinnerHeight2);

        HeightItems = new ArrayList<String>();
        for (int i=0;i<10;i++)
            HeightItems.add("1.0"+i+" متر");
        for (int i=10;i<100;i++)
            HeightItems.add("1."+i+" متر");
        for (int i=0;i<10;i++)
            HeightItems.add("2.0"+i+" متر");
        for (int i=10;i<=50;i++)
            HeightItems.add("2."+i+" متر");
        ArrayAdapter arrayAdapterHeight = new ArrayAdapter(EditProfile.this, android.R.layout.simple_spinner_dropdown_item, HeightItems);
        spinnerHeight2.setAdapter(arrayAdapterHeight);

        int spinnerPosition=arrayAdapterHeight.getPosition(Splash.sharedPreferences.getString("height","1.00 متر"));
        spinnerHeight2.setSelection(spinnerPosition);

        spinnerHeight2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
        spinnerWeight2=findViewById(R.id.spinnerWeight2);

        WeightItems = new ArrayList<String>();
        for (int i=20;i<=200;i++)
            WeightItems.add(i+" کیلو گرم");

        ArrayAdapter arrayAdapterWeight = new ArrayAdapter(EditProfile.this, android.R.layout.simple_spinner_dropdown_item, WeightItems);
        spinnerWeight2.setAdapter(arrayAdapterWeight);

        int spinnerPosition2=arrayAdapterWeight.getPosition(Splash.sharedPreferences.getString("weight","20 کیلوگرم"));
        spinnerWeight2.setSelection(spinnerPosition2);

        spinnerWeight2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
        radioButtonMale2=(RadioButton) findViewById(R.id.radioButtonMale2);
        radioButtonFemale2=(RadioButton) findViewById(R.id.radioButtonFemale2);

        if (Splash.sharedPreferences.getString("sex",null).compareTo("male")==0)
            radioButtonMale2.setChecked(true);
        else
            radioButtonFemale2.setChecked(true);

        radioButtonMale2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
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

        radioButtonFemale2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
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
        if (Splash.sharedPreferences.getString("image",null)!= null)
        {
            imageView = (ImageView) findViewById(R.id.Image2);
            imageView.setImageResource(0);
            imageView.setBackgroundResource(R.drawable.white);
            imageView.setImageBitmap(decodeBase64(Splash.sharedPreferences.getString("image", null)));
        }
        LoadImage = (Button) findViewById(R.id.btnImage2);
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
        btnAdd2=(Button) findViewById(R.id.btnAdd2);

        btnAdd2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name=edtName2.getText().toString();
                edit.putString("name",name);
                edit.commit();

                if (Splash.sharedPreferences.getString("name",null)==null || Splash.sharedPreferences.getString("height",null)==null || Splash.sharedPreferences.getString("weight",null)==null
                        || Splash.sharedPreferences.getString("sex",null)==null)
                {
                    Toast.makeText(EditProfile.this,"لطفاٌ اطلاعات را تکمیل نمایید",Toast.LENGTH_LONG).show();
                }
                else
                {
                    edit.putBoolean("flag", false);
                    edit.commit();
                    intent = new Intent(EditProfile.this, Menu.class);
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

                imageView = (ImageView) findViewById(R.id.Image2);
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

    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}// end of class
