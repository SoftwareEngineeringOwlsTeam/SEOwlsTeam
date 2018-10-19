package com.example.user.treasurehunter;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOtester extends AppCompatActivity {

    EditText et1;
    EditText et2;
    Button buttonwrite;
    Button buttonread;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_iotester);
        et1 =  findViewById(R.id.eddt1);
        et2 = findViewById(R.id.et2);
        buttonwrite =  findViewById(R.id.buttonwrite);
        buttonread = findViewById(R.id.buttonread);
        tv1 = findViewById(R.id.tv1);


    }
}
