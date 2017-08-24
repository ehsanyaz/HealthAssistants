package com.savar_computer.healthassistants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.savar_computer.healthassistants.Classes.BlackBoard;

public class AddFoodMenu extends AppCompatActivity {

    private ImageView food,fastFood,drink,fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_menu);


        food=(ImageView)findViewById(R.id.add_food_food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlackBoard.addFoodSelected= BlackBoard.FoodSelected.food;
                Intent intent=new Intent(getApplicationContext(),addFood.class);
                startActivity(intent);
            }
        });


        fastFood=(ImageView)findViewById(R.id.add_food_fast_food);
        fastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlackBoard.addFoodSelected= BlackBoard.FoodSelected.fast_food;
                Intent intent=new Intent(getApplicationContext(),addFood.class);
                startActivity(intent);
            }
        });


        drink=(ImageView)findViewById(R.id.add_food_drink);
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlackBoard.addFoodSelected= BlackBoard.FoodSelected.drink;
                Intent intent=new Intent(getApplicationContext(),addFood.class);
                startActivity(intent);
            }
        });


        fruit=(ImageView)findViewById(R.id.add_food_fruit);
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlackBoard.addFoodSelected= BlackBoard.FoodSelected.fruit;
                Intent intent=new Intent(getApplicationContext(),addFood.class);
                startActivity(intent);
            }
        });
    }
}
