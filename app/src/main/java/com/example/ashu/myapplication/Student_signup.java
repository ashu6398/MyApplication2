package com.example.ashu.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Student_signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Spinner course_spinner;
    public Spinner department_spinner;
    public Spinner year_spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        course_spinner = findViewById(R.id.spinner_course);
        department_spinner = findViewById(R.id.spinner_dept);
        year_spinner = findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Select_Branch,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department_spinner.setAdapter(adapter);
        department_spinner.setOnItemSelectedListener(this);
        course_spinner.setOnItemSelectedListener(this);
        year_spinner.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        {
                if (department_spinner.getSelectedItem().equals("--Select Department--")) {

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.none_select, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }


                if (department_spinner.getSelectedItem().equals("Institute of Technology")) {

                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Institute_of_Technology, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
                if (department_spinner.getSelectedItem().equals("Institute Of Bio Science and Technology")) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Institute_of_Bio_Science, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
                if (department_spinner.getSelectedItem().equals("Institute of Natural Science And Humanities")) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Institute_of_Humanities, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
                if (department_spinner.getSelectedItem().equals("Institute of Management,Commerce and Economics")) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Institute_of_Management, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
                if (department_spinner.getSelectedItem().equals("Institute Of Legal Studies")) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Legal_Studies, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
                if (department_spinner.getSelectedItem().equals("Institute of Media Studies")) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Media_Studies, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
                if (department_spinner.getSelectedItem().equals("Institute of Education And Research")) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Education_Research, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
                if (department_spinner.getSelectedItem().equals("Institute of Architecture and Planning")) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Architecture, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    course_spinner.setAdapter(adapter);
                }
        }

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(this,"Please select department",Toast.LENGTH_LONG).show();

    }

}
