package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class FoodMenu extends Activity {

    private LinearLayout addFood;
    private LinearLayout weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu);

        addFood=(LinearLayout)findViewById(R.id.food_menu_food_enter_layout);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddFoodMenu.class);
                startActivity(intent);
            }
        });

        weight=(LinearLayout)findViewById(R.id.food_menu_weight_enter_layout);
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FoodMenu.this,Weight.class);
                startActivity(intent);
            }
        });
    }
}
