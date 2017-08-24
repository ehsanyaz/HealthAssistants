package com.savar_computer.healthassistants;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savar_computer.healthassistants.Classes.BlackBoard;

public class addFood extends ListActivity {

    private String Matn[][];
    private String title[];
    private String value[];
    private String callery[];

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        pref = PreferenceManager.getDefaultSharedPreferences(this);


        switch (BlackBoard.addFoodSelected) {
            case food:

                break;
            case fast_food:

                break;
            case drink:

                break;
            case fruit:

                break;
            default:
                break;
        }
        BlackBoard.addFoodSelected = null;

        this.Matn = BlackBoard.getMatn(BlackBoard.addFoodSelected);
        this.title = new String[BlackBoard.getTypeCount(BlackBoard.addFoodSelected)];
        this.value = new String[BlackBoard.getTypeCount(BlackBoard.addFoodSelected)];
        this.callery = new String[BlackBoard.getTypeCount(BlackBoard.addFoodSelected)];
        for (int i = 0; i < BlackBoard.getTypeCount(BlackBoard.addFoodSelected); i++) {
            title[i] = Matn[i][0];
            value[i] = Matn[i][1];
            callery[i] = Matn[i][2];
        }
        setListAdapter(new AA());
    }

    ////------------------------------------------------------Array Adaptor
    class AA extends ArrayAdapter<String> {
        public AA() {
            super(addFood.this, R.layout.add_food_raw, title);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater in = getLayoutInflater();
            View row = in.inflate(R.layout.add_food_raw, parent, false);

            LinearLayout container;
            container = (LinearLayout) row.findViewById(R.id.add_food_raw);

            TextView Title = (TextView) row.findViewById(R.id.add_food_raw_title);
            TextView Value = (TextView) row.findViewById(R.id.add_food_raw_value);
            TextView Callery=(TextView) row.findViewById(R.id.add_food_raw_callery);

            if (!pref.getBoolean("Light", false)) {
                //do nothing
            } else {
                Title.setTextColor(getResources().getColor(R.color.white));
                Value.setTextColor(getResources().getColor(R.color.white));
                Callery.setTextColor(getResources().getColor(R.color.white));
                container.setBackgroundColor(getResources().getColor(R.color.DarkBackGround));
            }

            if (pref.getString("listPref", "2").trim().equals("1")) {
                Title.setTextSize(32f);
                Value.setTextSize(22f);
                Callery.setTextSize(22f);
            } else if (pref.getString("listPref", "").trim().equals("3")) {
                Title.setTextSize(20f);
                Value.setTextSize(10f);
                Callery.setTextSize(10f);
            }

            Typeface tpf = Typeface.createFromAsset(getAssets(), "Font/font.ttf");

            Title.setTypeface(tpf);
            Value.setTypeface(tpf);
            Callery.setTypeface(tpf);

            Title.setText(title[position]);
            Value.setText(value[position]);
            Callery.setText(callery[position]);

            return (row);
        }
    }
}
