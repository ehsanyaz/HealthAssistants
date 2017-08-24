package com.savar_computer.healthassistants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AddFoodMenu extends AppCompatActivity {

    private ImageView food,fastFood,drink,fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_menu);
    }
}
