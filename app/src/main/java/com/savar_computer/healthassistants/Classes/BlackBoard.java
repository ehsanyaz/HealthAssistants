package com.savar_computer.healthassistants.Classes;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlackBoard {

    public static enum FoodSelected {food, fast_food, drink, fruit}

    public static FoodSelected addFoodSelected = FoodSelected.food;

    private static String Matn[][];
    private static int count = 0;

    public static final String Spliter = "@";

    public static Context myContext;

    public BlackBoard(Context context) {
        this.myContext = context;
        try {
            setCount(myContext);
        } catch (IOException e) {
        }
        try {
            setMatn(context);
        } catch (IOException e) {

        }
    }

    public static String[][] getMatn(FoodSelected foodSelected) {
        int type = 0;
        if(foodSelected==null)
            foodSelected=FoodSelected.food;
        switch (foodSelected) {
            case food:
                type = 1;
                break;
            case fast_food:
                type = 2;
                break;
            case drink:
                type = 3;
                break;
            case fruit:
                type = 4;
                break;
        }

        String[][] Sub = new String[getTypeCount(foodSelected)][3];
        int l = 0;
        for (int i = 0; i < count; i++) {
            if (Integer.parseInt(Matn[i][3]) == type) {
                Sub[l][0] = Matn[i][0];
                Sub[l][1] = Matn[i][1];
                Sub[l][2]=Matn[i][2];
                l++;
            }
        }
        return Sub;
    }

    public static int getTypeCount(FoodSelected foodSelected) {
        int type = 0;
        if(foodSelected==null)
            foodSelected=FoodSelected.food;
        switch (foodSelected) {
            case food:
                type = 1;
                break;
            case fast_food:
                type = 2;
                break;
            case drink:
                type = 3;
                break;
            case fruit:
                type = 4;
                break;
        }

        int c = 0;
        for (int i = 0; i < count; i++) {
            if (Integer.parseInt(Matn[i][3]) == type)
                c++;
        }
        return c;
    }

    private static void setCount(Context context) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("Files/foodCallery.txt")));
        // do reading, usually loop until end of file reading
        String mLine = reader.readLine();
        while (mLine != null) {
            mLine = reader.readLine();
            count++;
        }
        reader.close();
    }

    private static void setMatn(Context context) throws IOException {
        Matn = new String[21][4];

        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("Files/foodCallery.txt")));

        // do reading, usually loop until end of file reading
        String mLine;
        String[] line;
        for (int i = 0; i < 21; i++) {
            mLine = reader.readLine();
            line = mLine.split(Spliter);
            Matn[i][0] = line[0];
            Matn[i][1] = line[1];
            Matn[i][2] = line[2];
            Matn[i][3] = line[3];
        }
        reader.close();
    }
}
