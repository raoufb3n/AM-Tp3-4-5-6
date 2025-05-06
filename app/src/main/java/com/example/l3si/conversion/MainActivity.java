package com.example.l3si.conversion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioEuroToDinar;
    private RadioButton radioDinarToEuro;
    private EditText inputValue;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);


        radioEuroToDinar = findViewById(R.id.radio_euro_to_dinar);
        radioDinarToEuro = findViewById(R.id.radio_dinar_to_euro);
        inputValue = findViewById(R.id.edit_input_value);
        resultText = findViewById(R.id.text_result);
    }

    public void convertir(View v) {
        try {
            // Get the input value
            float value = Float.valueOf(inputValue.getText().toString());
            float result;

            if (radioEuroToDinar.isChecked()) {
                result = euroToDinar(value);
            } else if (radioDinarToEuro.isChecked()) {
                result = dinarsToEuro(value);
            } else {

                result = 0;
            }


            resultText.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            resultText.setText("Veuillez entrer une valeur valide");
        }
    }

    private float dinarsToEuro(float valeurDinar) {
        return (float) (valeurDinar / 145);
    }

    private float euroToDinar(float valeurEuro) {
        return (float) (valeurEuro * 145);
    }

    public void auClicMethode(View v) {

    }
}