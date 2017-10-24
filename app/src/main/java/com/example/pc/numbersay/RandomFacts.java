package com.example.pc.numbersay;


import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;


public class RandomFacts extends AppCompatActivity {
RequestQueue requestQueue;
    TextView fact;
    Button trivia, math, date, year;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_facts);
        requestQueue = Volley.newRequestQueue(RandomFacts.this);
        trivia = (Button) findViewById(R.id.trivia);
        math = (Button) findViewById(R.id.math);
        date = (Button) findViewById(R.id.date);
        year = (Button) findViewById(R.id.year);
        fact = (TextView) findViewById(R.id.fact);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RandomFacts.this,QuestsFacts.class);
                startActivity(intent);
            }
        });
    }

    public void getTrivia(View view) {
        getFact(trivia.getText().toString().toLowerCase());
    }

    public void getMath(View view)  {
        getFact( math.getText().toString().toLowerCase());
    }

    public void getDate(View view) {
        getFact(date.getText().toString().toLowerCase());
    }

    public void getYear(View view) {
        getFact(year.getText().toString().toLowerCase());
    }

    public void getFact(String type) {
        String RANDOM_FACT_URL = "http://numbersapi.com/random/" + type;
        StringRequest request = new StringRequest(Request.Method.GET, RANDOM_FACT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)  {
                fact.setText(response);
                fact.setBackgroundColor(Color.parseColor("#8BC34A"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(request);
    }




}


