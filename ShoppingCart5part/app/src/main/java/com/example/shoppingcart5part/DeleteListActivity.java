package com.example.shoppingcart5part;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DeleteListActivity extends AppCompatActivity {
    private Spinner deleteListSpinner;
    Button cancel;
    Button okay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_list);

        //dropdown list
        Intent intent = getIntent();
        String[] deleteList = intent.getStringArrayExtra(MainActivity.LISTS);
        deleteListSpinner = findViewById(R.id.deleteListSpinner);
        ArrayAdapter DeleteListAdapter = new ArrayAdapter( this,R.layout.support_simple_spinner_dropdown_item,deleteList);
        DeleteListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deleteListSpinner.setAdapter(DeleteListAdapter);

        //cancel button
        cancel = findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent intent = new Intent(DeleteListActivity.this,MainActivity.class);
////                startActivity(intent);
                finish();
            }
        });

        //okay button
        okay = findViewById(R.id.btn_okay);
        okay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
    }
}