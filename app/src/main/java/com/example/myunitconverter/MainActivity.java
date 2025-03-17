package com.example.myunitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText convertedValue;
    Button buttonToConvert;
    TextView textViewResult;
    Spinner spinnerSource, spinnerDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        convertedValue = findViewById(R.id.ConvertedValue);
        buttonToConvert = findViewById(R.id.buttonToConvert);
        textViewResult = findViewById(R.id.textViewResult);
        spinnerSource = findViewById(R.id.spinnerSource);
        spinnerDestination = findViewById(R.id.spinnerDestination);


        String[] vals_option = {"Inch", "Foot", "Yard", "Mile", "Centimeter", "Kilometer",
                "Celsius", "Fahrenheit", "Kelvin", "Pound", "Ounce", "Ton",
                "Kilogram", "Gram"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vals_option);
        spinnerSource.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);

        buttonToConvert.setOnClickListener(v -> convertValues());
    }

    private void convertValues() {
        String sourceSpinnerValue = spinnerSource.getSelectedItem().toString();
        String destinationSpinnerValue = spinnerDestination.getSelectedItem().toString();
        String inputValue = convertedValue.getText().toString();

        if (inputValue.isEmpty()) {
            textViewResult.setText("Please enter a value.");
            return;
        }

        double enteredValue;
        try {
            enteredValue = Double.parseDouble(inputValue);
        } catch (NumberFormatException e) {
            textViewResult.setText("Please enter a number.");
            return;
        }


        double finalValueResult = convertUnits(enteredValue, sourceSpinnerValue, destinationSpinnerValue);
        textViewResult.setText("Converted Value is <<: " + finalValueResult);
    }

    private double convertUnits(double enteredValue, String sourceSpinnerValue, String destinationSpinnerValue) {

        if (sourceSpinnerValue.equals("Inch")) enteredValue *= 2.54;
        if (sourceSpinnerValue.equals("Foot")) enteredValue *= 30.48;
        if (sourceSpinnerValue.equals("Yard")) enteredValue *= 91.44;
        if (sourceSpinnerValue.equals("Mile")) enteredValue *= 1609.34;

        if (destinationSpinnerValue.equals("Inch")) return enteredValue / 2.54;
        if (destinationSpinnerValue.equals("Foot")) return enteredValue / 30.48;
        if (destinationSpinnerValue.equals("Yard")) return enteredValue / 91.44;
        if (destinationSpinnerValue.equals("Mile")) return enteredValue / 1609.34;


        if (sourceSpinnerValue.equals("Pound")) enteredValue *= 0.453592;
        if (sourceSpinnerValue.equals("Ounce")) enteredValue *= 0.0283495;
        if (sourceSpinnerValue.equals("Ton")) enteredValue *= 907.185;

        if (destinationSpinnerValue.equals("Pound")) return enteredValue / 0.453592;
        if (destinationSpinnerValue.equals("Ounce")) return enteredValue / 0.0283495;
        if (destinationSpinnerValue.equals("Ton")) return enteredValue / 907.185;


        if (sourceSpinnerValue.equals("Celsius") && destinationSpinnerValue.equals("Fahrenheit"))
            return (enteredValue * 1.8) + 32;
        if (sourceSpinnerValue.equals("Fahrenheit") && destinationSpinnerValue.equals("Celsius"))
            return (enteredValue - 32) / 1.8;
        if (sourceSpinnerValue.equals("Celsius") && destinationSpinnerValue.equals("Kelvin"))
            return enteredValue + 273.15;
        if (sourceSpinnerValue.equals("Kelvin") && destinationSpinnerValue.equals("Celsius"))
            return enteredValue - 273.15;

        return enteredValue;
    }
}
