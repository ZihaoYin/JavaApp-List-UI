package com.example.shoppingcart5part;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    //public static final String LISTS = "Lists";
    private Spinner listsSpinner;
    private Spinner listFunctionSpinner;
    EditText listNameInput;

    ArrayList<String> lists = new ArrayList<>();

    int listIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup lists
        lists.add("list1");
        lists.add("list2");
        lists.add("list3");
        lists.add("list4");


        //Lists Drop Down Menu
        listsSpinner = findViewById(R.id.ListsSpinner);
        //String[] lists = getResources().getStringArray(R.array.Lists);
        final ArrayAdapter ListsAdapter = new ArrayAdapter( this,R.layout.support_simple_spinner_dropdown_item,lists);
        ListsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listsSpinner.setAdapter(ListsAdapter);

        listsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            //!!!select list function, not fully implemented yet due to it's relation with item
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String listName = parent.getItemAtPosition(position).toString() + " has been selected";
                listIndex = position;
                Toast.makeText(MainActivity.this,listName,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //List Name Input
        listNameInput = findViewById(R.id.listNameInput);

        //List Function Drop Down Menu(select, delete, rename a list)
        listFunctionSpinner = findViewById(R.id.ListFunctionSpinner);
        String[] listFunction = getResources().getStringArray(R.array.ListFunction);
        ArrayAdapter ListFunctionAdapter = new ArrayAdapter( this,R.layout.support_simple_spinner_dropdown_item,listFunction);
        ListFunctionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listFunctionSpinner.setAdapter(ListFunctionAdapter);

        // function lists
        listFunctionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String valueFormSpinner = parent.getItemAtPosition(position).toString();
                // add list function
                if(valueFormSpinner.equals("Create List")){
                    String newList = listNameInput.getText().toString();
                    // empty list name warning
                    if(newList.isEmpty()){
                        Toast.makeText(MainActivity.this, "Empty list name, please enter again", Toast.LENGTH_SHORT).show();
                    }else if(lists.contains(newList)){ // avoid same list name conflict
                        Toast.makeText(MainActivity.this, "list exists, please enter again", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        lists.add(newList);
                        ListsAdapter.notifyDataSetChanged();
                    }

                    listNameInput.setText("");
                    parent.setSelection(0);
                }

                //delete list function
                if(valueFormSpinner.equals("Delete List")){
                    String listName = lists.get(listIndex) + " has been deleted";
                    Toast.makeText(MainActivity.this, listName, Toast.LENGTH_SHORT).show();
                    lists.remove(listIndex);
                    ListsAdapter.notifyDataSetChanged();
                    parent.setSelection(0);
                }

                //rename list function
                if(valueFormSpinner.equals("Rename List")){
                    String newListName = listNameInput.getText().toString();
                    // empty list name warning
                    if(newListName.isEmpty()){
                        Toast.makeText(MainActivity.this, "Empty list name, please enter again", Toast.LENGTH_SHORT).show();
                    }else {
                        lists.set(listIndex, newListName);
                        ListsAdapter.notifyDataSetChanged();
                    }
                    listNameInput.setText("");
                    parent.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



}