package com.example.shoppingcart5part;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity{
    public static final String LISTS = "Lists";
    private Spinner listsSpinner;
    private Spinner listFunctionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lists Drop Down Menu
        listsSpinner = findViewById(R.id.ListsSpinner);
        String[] lists = getResources().getStringArray(R.array.Lists);
        ArrayAdapter ListsAdapter = new ArrayAdapter( this,R.layout.support_simple_spinner_dropdown_item,lists);
        ListsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listsSpinner.setAdapter(ListsAdapter);

        //listsSpinner.setOnItemSelectedListener(this);

        //List Function Drop Down Menu(select, delete, rename a list)
        listFunctionSpinner = findViewById(R.id.ListFunctionSpinner);
        String[] listFunction = getResources().getStringArray(R.array.ListFunction);
        ArrayAdapter ListFunctionAdapter = new ArrayAdapter( this,R.layout.support_simple_spinner_dropdown_item,listFunction);
        ListFunctionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listFunctionSpinner.setAdapter(ListFunctionAdapter);

        listFunctionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               String valueFormSpinner = parent.getItemAtPosition(position).toString();
                if(valueFormSpinner.equals("Delete List")){
                    openDeleteListWindow();
                    parent.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    // pop up Delete List Window
    private void openDeleteListWindow(){
        Intent intent = new Intent(this, DeleteListActivity.class);
        String[] lists = getResources().getStringArray(R.array.Lists);
        intent.putExtra(LISTS,lists);
        startActivity(intent);
    }
}