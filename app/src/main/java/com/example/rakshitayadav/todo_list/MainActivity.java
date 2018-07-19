package com.example.rakshitayadav.todo_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String messageText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,EditScreenClass.class);
                intent.putExtra(Intent_Constants.INTENT_EDIT_DATA,arrayList.get(position));
                intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION,position);

                startActivityForResult(intent,Intent_Constants.INTENT_REQUEST_CODE_NEW);
            }
        });

        //deletion on long press on main screen
       /* listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });*/

        try {
            Scanner sc = new Scanner(openFileInput("todo.txt"));
            while(sc.hasNextLine())
            {
                String data = sc.nextLine();
                arrayAdapter.add(data);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        try {
            PrintWriter pw = new PrintWriter(openFileOutput("todo.txt", Context.MODE_PRIVATE));
            for(String data : arrayList)
                pw.println(data);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    public void onClick(View v)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,Add_Data_To_Activity.class);
        startActivityForResult(intent,Intent_Constants.INTENT_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == Intent_Constants.INTENT_RESULT_CODE)
        {
            messageText = data.getStringExtra(Intent_Constants.INTENT_MESSAGE_FIELD);
            arrayList.add(messageText);
            arrayAdapter.notifyDataSetChanged();
        }
        else if (resultCode == Intent_Constants.INTENT_RESULT_CODE_NEW)
        {
            messageText = data.getStringExtra(Intent_Constants.CHANGED_MESSAGE);
            position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION,-1);
            arrayList.set(position,messageText);
            arrayAdapter.notifyDataSetChanged();
        }

        //if we choose to delete from edit_screen_class
        else if(resultCode == 3)
        {
            position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION,-1);
            arrayList.remove(position);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
