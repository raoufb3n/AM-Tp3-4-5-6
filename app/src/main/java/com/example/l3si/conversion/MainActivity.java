package com.example.l3si.conversion;


import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioButton radioEuroToDinar;
    private RadioButton radioDinarToEuro;
    private EditText inputValue;
    private TextView resultText;

    private static final float EURO_TO_DINAR_RATE = 145f;
    private static final float DINAR_TO_EURO_RATE = 1f / 145f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);


        radioEuroToDinar = findViewById(R.id.radio_euro_to_dinar);
        radioDinarToEuro = findViewById(R.id.radio_dinar_to_euro);
        inputValue = findViewById(R.id.edit_input_value);
        resultText = findViewById(R.id.text_result);


        registerForContextMenu(radioEuroToDinar);
        registerForContextMenu(radioDinarToEuro);


        radioEuroToDinar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return true;
            }
        });

        radioDinarToEuro.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return true;
            }
        });
    }

    // Method associated with the Convertir button via onClick
    public void convertir(View v) {
        try {
            float value = Float.valueOf(inputValue.getText().toString());
            float result;

            // Check which radio button is selected
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
        return valeurDinar * DINAR_TO_EURO_RATE;
    }

    private float euroToDinar(float valeurEuro) {
        return valeurEuro * EURO_TO_DINAR_RATE;
    }


    public void auClicMethode(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Clear any existing items
        menu.clear();
        // Add menu items
        menu.add(0, 1, 0, "Taux dinar -> euro");
        menu.add(0, 2, 0, "Taux euro -> dinar");

        // For debugging
        Toast.makeText(this, "Context menu created", Toast.LENGTH_SHORT).show();
    }

    // Context Menu item selection
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                // Show dinar to euro conversion rate
                Toast.makeText(this, "1 Dinar = " + DINAR_TO_EURO_RATE + " Euro", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this, "1 Euro = " + EURO_TO_DINAR_RATE + " Dinar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Conversion C <-> F");
        menu.add(0, 2, 0, "Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(this, ConversionTemperature.class);
                startActivity(intent);
                return true;

            case 2:

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}