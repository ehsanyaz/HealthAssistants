package com.savar_computer.healthassistants;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savar_computer.healthassistants.Classes.ActivityBlackBoard;
import com.savar_computer.healthassistants.Classes.BlackBoard;

public class AddActivity extends ListActivity {

    private String matn[][];
    private String title[];
    private String callery[];
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        this.matn = ActivityBlackBoard.getMatn();
        this.title = new String[ActivityBlackBoard.count];
        this.callery = new String[ActivityBlackBoard.count];
        for (int i = 0; i < ActivityBlackBoard.count; i++) {
            title[i] = matn[i][0];
            callery[i] = matn[i][1];
        }
        setListAdapter(new AA());
    }

    class AA extends ArrayAdapter<String> {

        public AA() {

            super(AddActivity.this, R.layout.add_activity_raw, title);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater in = getLayoutInflater();
            View row = in.inflate(R.layout.add_activity_raw, parent, false);

            LinearLayout container;
            container = (LinearLayout) row.findViewById(R.id.add_activity_raw);

            TextView Title = (TextView) row.findViewById(R.id.add_activity_raw_title);
            TextView Callery = (TextView) row.findViewById(R.id.add_activity_raw_calley);

            if (pref.getBoolean("Light", false)) {
                //do nothing
            } else {
                Title.setTextColor(getResources().getColor(R.color.white));
                Callery.setTextColor(getResources().getColor(R.color.white));
                container.setBackgroundColor(getResources().getColor(R.color.DarkBackGround));
            }

            if (pref.getString("listPref", "2").trim().equals("1")) {
                Title.setTextSize(32f);
                Callery.setTextSize(22f);
            } else if (pref.getString("listPref", "").trim().equals("3")) {
                Title.setTextSize(20f);
                Callery.setTextSize(10f);
            }

            Typeface tpf = Typeface.createFromAsset(getAssets(), "Font/font.ttf");

            Title.setTypeface(tpf);
            Callery.setTypeface(tpf);

            Title.setText(title[position]);
            Callery.setText(callery[position]);

           /* switch (BlackBoard.addFoodSelected) {
                case food:
                    Icon.setImageResource(R.drawable.food);
                    break;
                case fast_food:
                    Icon.setImageResource(R.drawable.fastfood);
                    break;
                case drink:
                    Icon.setImageResource(R.drawable.drink);
                    break;
                case fruit:
                   Icon.setImageResource(R.drawable.fruit);
                    break;
                default:
                    break;
            }*/

            return (row);
        }
    }
}
