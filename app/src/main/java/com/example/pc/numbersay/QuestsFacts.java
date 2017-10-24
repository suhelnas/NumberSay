package com.example.pc.numbersay;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class QuestsFacts extends AppCompatActivity {
    RequestQueue queue;
    RadioButton trivia,math,date,year;
    EditText editText;
    TextView fact;

    String type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quests_facts);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        queue = Volley.newRequestQueue(QuestsFacts.this);
        trivia = (RadioButton) findViewById(R.id.trivia);
        math = (RadioButton) findViewById(R.id.math);
        date = (RadioButton) findViewById(R.id.date);
        year = (RadioButton) findViewById(R.id.year);
        editText = (EditText) findViewById(R.id.edit_text);
        fact = (TextView) findViewById(R.id.fact);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), RandomFacts.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void getQuestFact(View view) {
        String text = editText.getText().toString();
        if (type.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(QuestsFacts.this);
            builder.setMessage(R.string.alertdialogtype);
            AlertDialog dialog = builder.create();
        }
        else if(text.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(QuestsFacts.this);
            builder.setMessage(R.string.alertdialogemptyedit);
            AlertDialog dialog = builder.create();
        }
        else {

            String QUEST_FACTS_URL = "http://numbersapi.com/" + text + "/" + type;
            StringRequest request = new StringRequest(Request.Method.GET, QUEST_FACTS_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    fact.setText(response);
                    fact.setBackgroundColor(Color.parseColor("#8BC34A"));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );
            queue.add(request);

        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId())  {
            case R.id.trivia:
                if(checked){
                    type = trivia.getText().toString().toLowerCase();
                    editText.setHint("");
                    break;
                }
            case R.id.math:
                if(checked) {
                    type = math.getText().toString().toLowerCase();
                    editText.setHint("");
                    break;
                }
            case R.id.date:
                if(checked) {
                    type = date.getText().toString().toLowerCase();
                    editText.setHint("MM/DD");
                    break;
                }
            case R.id.year:
                if(checked){
                  type = year.getText().toString().toLowerCase();
                    editText.setHint("");
                }


        }
    }
}
