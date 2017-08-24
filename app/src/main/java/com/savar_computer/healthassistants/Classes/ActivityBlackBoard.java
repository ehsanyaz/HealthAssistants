package com.savar_computer.healthassistants.Classes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActivityBlackBoard {

    private static String Matn[][];
    public static int count = 0;

    public static final String Spliter = "@";

    public static Context myContext;

    public ActivityBlackBoard(Context context) {
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

    public static String[][] getMatn() {
        return Matn;
    }


    private static void setCount(Context context) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("Files/ActivityCallery.txt")));
        // do reading, usually loop until end of file reading
        String mLine = reader.readLine();
        while (mLine != null) {
            mLine = reader.readLine();
            count++;
        }
        reader.close();
    }

    private static void setMatn(Context context) throws IOException {
        Matn = new String[count][2];

        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("Files/ActivityCallery.txt")));

        // do reading, usually loop until end of file reading
        String mLine;
        String[] line;
        for (int i = 0; i < count; i++) {
            mLine = reader.readLine();
            line = mLine.split(Spliter);
            Matn[i][0] = line[0];
            Matn[i][1] = line[1];
        }
        reader.close();
    }
}
