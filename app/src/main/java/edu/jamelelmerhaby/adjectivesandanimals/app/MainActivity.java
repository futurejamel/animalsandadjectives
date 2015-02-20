package edu.jamelelmerhaby.adjectivesandanimals.app;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Toast aboutToast = Toast.makeText(this, "Created by: " + R.string.author, Toast.LENGTH_SHORT);
            aboutToast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MainFragment extends Fragment{
        private Button genButton;
        private TextView name;
        private String[] adjectives;
        private String[] animals;
        private Random rand;


        public MainFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            findViews(rootView);
            setArraysFromRes();
            return rootView;
        }

        private void findViews(View rootView) {
            genButton = (Button)rootView.findViewById(R.id.generateButton);
            attachButtonListener();
            name = (TextView)rootView.findViewById(R.id.nameView);
        }

        private void attachButtonListener() {
            genButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String generatedName = generateName();
                    name.setText(generatedName);
                }
            });
        }

        private void setArraysFromRes() {
            Resources res = getResources();
            adjectives = res.getStringArray(R.array.adjectives);
            animals = res.getStringArray(R.array.animals);
        }

        private String generateName() {
            String generatedName = "";
            rand = new Random();
            int index = rand.nextInt(adjectives.length - 1);
            generatedName += adjectives[index];
            generatedName += " ";
            index = rand.nextInt(animals.length - 1);
            generatedName += animals[index];
            return generatedName.toLowerCase();
        }
    }
}
