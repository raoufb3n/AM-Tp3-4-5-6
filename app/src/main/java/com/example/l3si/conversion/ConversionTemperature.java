package com.example.l3si.conversion;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConversionTemperature extends AppCompatActivity {

    private EditText inputTemp;
    private RadioButton radioCtoF, radioFtoC;
    private TextView resultTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_temperature);

        inputTemp = findViewById(R.id.edit_temp_input);
        radioCtoF = findViewById(R.id.radio_c_to_f);
        radioFtoC = findViewById(R.id.radio_f_to_c);
        resultTemp = findViewById(R.id.text_temp_result);
    }

    public void convertTemperature(android.view.View view) {
        try {
            float input = Float.parseFloat(inputTemp.getText().toString());
            float result;

            if (radioCtoF.isChecked()) {
                result = (9f / 5f) * input + 32f;
            } else if (radioFtoC.isChecked()) {
                result = (5f / 9f) * (input - 32f);
            } else {
                Toast.makeText(this, "Sélectionnez un mode de conversion", Toast.LENGTH_SHORT).show();
                return;
            }

            resultTemp.setText("Résultat : " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez entrer une valeur valide", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Conversion Euro <-> Dinar");
        menu.add(0, 2, 0, "Quitter");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case 2:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
