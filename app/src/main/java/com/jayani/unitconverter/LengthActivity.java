package com.jayani.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LengthActivity extends AppCompatActivity {

    Spinner convertFrom;
    Spinner convertTo;
    Button convert;
    EditText editTextLength;
    TextView result;
    String[] unitTypes = {"centimeter", "meter","inch","foot","yard", "mile"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        convertFrom = (Spinner) findViewById(R.id.spinnerConvertFrom);
        convertTo = (Spinner) findViewById(R.id.spinnerConvertTo);
        convert = (Button) findViewById(R.id.btnLengthConverter);
        editTextLength = (EditText) findViewById(R.id.editTextLength);
        result = (TextView) findViewById(R.id.tvLengthResult);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(LengthActivity.this, android.R.layout.simple_list_item_1,unitTypes);

        convertFrom.setAdapter(spinnerAdapter);
        convertTo.setAdapter(spinnerAdapter);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double beginingQty;
                double endingQty;
                String beginingUnitType;
                String endingUnitType;



                if(!editTextLength.getText().toString().isEmpty()){
                    beginingUnitType = convertFrom.getSelectedItem().toString();
                    endingUnitType = convertTo.getSelectedItem().toString();

                    if (beginingUnitType == endingUnitType){
                        Toast.makeText(getApplicationContext(),"Source unit and Destination unit are same", Toast.LENGTH_LONG ).show();
                        result.setText("");
                    }else {

                        LengthConvert lengthConverter = new LengthConvert();
                        beginingQty = Double.parseDouble(editTextLength.getText().toString());
                        beginingUnitType = convertFrom.getSelectedItem().toString();
                        endingUnitType = convertTo.getSelectedItem().toString();

                        lengthConverter.setBeginingQty(beginingQty);
                        lengthConverter.setBeginingUnitType(beginingUnitType);
                        lengthConverter.setEndingUnitType(endingUnitType);

                        endingQty = lengthConverter.calculateEndingQty();
                        lengthConverter.setEndingQty(endingQty);

                        result.setText(lengthConverter.toString());
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Enter the Length",Toast.LENGTH_LONG ).show();
                    result.setText("");
                }

            }
        });
    }


    }


