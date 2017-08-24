package com.savar_computer.healthassistants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class FoodActivity extends Activity {

    private LinearLayout addFood;
    private LinearLayout weight;
    private LinearLayout weightCharts;
    private LinearLayout advise;
    private LinearLayout activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);

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
                Intent intent=new Intent(FoodActivity.this,Weight.class);
                startActivity(intent);
            }
        });

        weightCharts=(LinearLayout)findViewById(R.id.food_menu_diagram_enter_layout);
        weightCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FoodActivity.this,WeightCharts.class);
                startActivity(intent);
            }
        });
        advise=(LinearLayout)findViewById(R.id.food_menu_advise_enter_layout);
        advise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FoodActivity.this,Advise.class);
                startActivity(intent);
            }
        });
        activity=(LinearLayout)findViewById(R.id.food_menu_activity_enter_layout);
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FoodActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }
}
