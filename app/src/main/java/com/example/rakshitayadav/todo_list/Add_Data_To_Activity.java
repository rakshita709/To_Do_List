package com.example.rakshitayadav.todo_list;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Data_To_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_layout);
    }



    public void save_data(View v)
    {
        String DataText = ((EditText) findViewById(R.id.data_for_todo)).getText().toString();
        if(DataText.equals(""))
        {

        }
        else
        {
            Intent intent = new Intent();
            intent.putExtra(Intent_Constants.INTENT_MESSAGE_FIELD,DataText);
            setResult(Intent_Constants.INTENT_RESULT_CODE,intent);
            finish();
        }
    }




}
