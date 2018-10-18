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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        et1 =  findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        buttonwrite =  findViewById(R.id.buttonwrite);
        buttonread = findViewById(R.id.buttonread);
        tv1 = findViewById(R.id.tv1);
        buttonwrite.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v) {

                try {
                    writeFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
    });
    }

    public void writeFile() throws FileNotFoundException {
        BufferedWriter writer = null;
        try {
            FileOutputStream fileOutputStream = openFileOutput("pins.txt", Context.MODE_WORLD_READABLE);
            String data = ("Hello");
            writer = new BufferedWriter((new OutputStreamWriter(fileOutputStream)));
            writer.write(data);
            writer.flush();
            tv1.setText("");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null)
            {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error close writer");
                    e.printStackTrace();
                }
            }
        }
    }


}
