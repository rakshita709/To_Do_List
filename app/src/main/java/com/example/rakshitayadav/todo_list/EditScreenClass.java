package com.example.rakshitayadav.todo_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditScreenClass extends AppCompatActivity {

    String new_text;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_layout);
        Intent intent = getIntent();
        new_text = intent.getStringExtra(Intent_Constants.INTENT_EDIT_DATA);
        pos = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION,-1);
        EditText edited_data = (EditText) findViewById(R.id.data_for_todo);
        edited_data.setText(new_text);
    }

    public void save_data(View v)
    {
        String changedData = ((EditText)findViewById(R.id.data_for_todo)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION,pos);
        setResult(Intent_Constants.INTENT_RESULT_CODE_NEW,intent);
        finish();
    }

}
