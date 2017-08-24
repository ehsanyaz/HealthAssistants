package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Weight extends Activity {

    private TextView textView;
    private EditText editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight);


        textView = (TextView) findViewById(R.id.weight_txt_please_enter_weight);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Font/font.ttf");
        textView.setTypeface(typeface);

        editText = (EditText) findViewById(R.id.weight_edit_enter_weight);

        editText.setText(Splash.sharedPreferences.getString("weight", "0 کیلوگرم"));

        btn = (Button) findViewById(R.id.weight_btn_accept);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = Splash.sharedPreferences.edit();
                editor.putString("weight", editText.getText().toString() + "کیلوگرم");
                editText.setText(editText.getText().toString() + " " + "کیلوگرم");
                editor.commit();
                Toast.makeText(getApplicationContext(), "اطلاعات با موفقیت ثبت شد", Toast.LENGTH_LONG).show();
            }
        });
    }
}
