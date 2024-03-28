package com.jayani.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TempActivity extends AppCompatActivity {
    Spinner convertFrom;
    Spinner convertTo;
    Button convert;
    EditText editTextTemp;
    TextView result;
    String[] unitTypes = {"Celcius","Fahrenheit","Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_temp);

        convertFrom = (Spinner) findViewById(R.id.spinnerConvertFrom);
        convertTo = (Spinner) findViewById(R.id.spinnerConvertTo);
        convert = (Button) findViewById(R.id.btnTempConverter);
        editTextTemp = (EditText) findViewById(R.id.editTextTemp);
        result = (TextView) findViewById(R.id.tvTempResult);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(TempActivity.this, android.R.layout.simple_list_item_1,unitTypes);

        convertFrom.setAdapter(spinnerAdapter);
        convertTo.setAdapter(spinnerAdapter);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double beginingQty;
                double endingQty;
                String beginingUnitType;
                String endingUnitType;

                TempConvert tempConverter = new TempConvert();



                if(!editTextTemp.getText().toString().isEmpty()){

                    beginingUnitType = convertFrom.getSelectedItem().toString();
                    endingUnitType = convertTo.getSelectedItem().toString();
                    beginingQty = Double.parseDouble(editTextTemp.getText().toString());

                    tempConverter.setBeginingQty(beginingQty);
                    tempConverter.setBeginingUnitType(beginingUnitType);
                    tempConverter.setEndingUnitType(endingUnitType);

                    if (beginingUnitType == endingUnitType){
                        Toast.makeText(getApplicationContext(),"Source unit and Destination unit are same",Toast.LENGTH_LONG ).show();
                        result.setText("");
                    }else {

                        if ((beginingUnitType == "Celcius") && (endingUnitType == "Fahrenheit")){
                            endingQty = tempConverter.convertCelsiustoFahrenheit();
                            tempConverter.setEndingQty(endingQty);
                            result.setText(tempConverter.toString());

                        } else if ((beginingUnitType == "Celcius") && (endingUnitType == "Kelvin")){
                            endingQty = tempConverter.convertCelsiustoKelvin();
                            tempConverter.setEndingQty(endingQty);
                            result.setText(tempConverter.toString());

                        }else if ((beginingUnitType == "Fahrenheit") && (endingUnitType == "Celcius")){
                            endingQty = tempConverter.convertFahrenheittoCelsius();
                            tempConverter.setEndingQty(endingQty);
                            result.setText(tempConverter.toString());

                        }else if ((beginingUnitType == "Fahrenheit") && (endingUnitType == "Kelvin")){
                            endingQty = tempConverter.convertFahrenheittoKelvin();
                            tempConverter.setEndingQty(endingQty);
                            result.setText(tempConverter.toString());

                        }else if ((beginingUnitType == "Kelvin") && (endingUnitType == "Celcius")){
                            endingQty = tempConverter.convertKelvintoCelsius();
                            tempConverter.setEndingQty(endingQty);
                            result.setText(tempConverter.toString());

                        }else if ((beginingUnitType == "Kelvin") && (endingUnitType == "Fahrenheit")){
                            endingQty = tempConverter.convertKelvintoFahrenheit();
                            tempConverter.setEndingQty(endingQty);
                            result.setText(tempConverter.toString());
                        }


                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please Enter the Temperature", Toast.LENGTH_LONG ).show();
                    result.setText("");
                }

            }

        });
    }

}