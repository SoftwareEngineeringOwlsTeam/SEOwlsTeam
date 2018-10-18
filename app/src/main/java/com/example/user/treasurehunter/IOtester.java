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
//        buttonwrite.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                try
//                {
//                    writeFile();
//                }
//                catch (FileNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public void readFile(View view) throws FileNotFoundException
    {
        FileInputStream fileInputStream = openFileInput("pins");
        String words = "Wrong";
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream)))
        {
            while ((words = br.readLine()) != null)
            {
                resultStringBuilder.append(words).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        tv1.setText(resultStringBuilder.toString());
    }

    public void writeFile(View view) throws FileNotFoundException
    {
        BufferedWriter writer = null;
        try
        {
//            File file = new File("pins.txt");
//            file.canWrite();
//            if (!file.exists())
//            {
//                file.createNewFile();
//            }
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileOutputStream fileOutputStream = openFileOutput("pins", Context.MODE_PRIVATE);
            String data = (et1.getText().toString() + " and " + et2.getText().toString());
            //writer = new BufferedWriter((new OutputStreamWriter(fileOutputStream, "utf-8")));
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
            //writer.write(data);
            //writer.flush();
            tv1.setText("");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(writer != null)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    System.err.println("Error close writer");
                    e.printStackTrace();
                }
            }
        }
    }
}
