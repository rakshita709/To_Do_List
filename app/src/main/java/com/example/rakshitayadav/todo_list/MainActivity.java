package com.example.rakshitayadav.todo_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = ((ListView)findViewById(R.id.listView));
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,android.R.id.l,arrayList);
    }

    public void AddData(View v)
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
    }
}
