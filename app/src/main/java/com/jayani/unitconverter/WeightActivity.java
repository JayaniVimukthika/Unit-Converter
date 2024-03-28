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

public class WeightActivity extends AppCompatActivity {

    Spinner convertFrom;
    Spinner convertTo;
    Button convert;
    EditText editTextWeight;
    TextView result;
    String[] unitTypes = {"gram","kilogram","pound","ounce","ton"};
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weight);

        convertFrom = (Spinner) findViewById(R.id.spinnerConvertFrom);
        convertTo = (Spinner) findViewById(R.id.spinnerConvertTo);
        convert = (Button) findViewById(R.id.btnWeightConverter);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        result = (TextView) findViewById(R.id.tvWeightResult);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(WeightActivity.this, android.R.layout.simple_list_item_1,unitTypes);

        convertFrom.setAdapter(spinnerAdapter);
        convertTo.setAdapter(spinnerAdapter);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double beginingQty;
                double endingQty;
                String beginingUnitType;
                String endingUnitType;

                weightConvert weightConverter = new weightConvert();

                if(!editTextWeight.getText().toString().isEmpty()){

                    beginingQty = Double.parseDouble(editTextWeight.getText().toString());
                    beginingUnitType = convertFrom.getSelectedItem().toString();
                    endingUnitType = convertTo.getSelectedItem().toString();

                    if (beginingUnitType == endingUnitType){
                        Toast.makeText(getApplicationContext(),"Source unit and Destination unit are same", Toast.LENGTH_LONG ).show();
                        result.setText("");
                    }else {

                        weightConverter.setBeginingQty(beginingQty);
                        weightConverter.setBeginingUnitType(beginingUnitType);
                        weightConverter.setEndingUnitType(endingUnitType);

                        endingQty = weightConverter.calculateEndingQty();
                        weightConverter.setEndingQty(endingQty);

                        result.setText(weightConverter.toString());
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Enter the Weight",Toast.LENGTH_LONG ).show();
                    result.setText("");
                }

            }
        });
    }


}