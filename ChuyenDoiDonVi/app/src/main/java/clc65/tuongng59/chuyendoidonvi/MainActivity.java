package clc65.tuongng59.chuyendoidonvi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.*;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Spinner spinnerUnit;
    Button btnConvert;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);
        spinnerUnit = findViewById(R.id.spinnerUnit);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        String[] units = {"Metre → Centimetre", "Kilogram → Gram", "Litre → Millilitre"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertValue();
            }
        });
    }

    private void convertValue() {
        String inputStr = etInput.getText().toString();

        if (inputStr.isEmpty()) {
            tvResult.setText("Please enter a value!");
            return;
        }

        double inputValue = Double.parseDouble(inputStr);
        String selectedUnit = spinnerUnit.getSelectedItem().toString();
        double result = 0;
        String resultText = "";

        switch (selectedUnit) {
            case "Metre → Centimetre":
                result = inputValue * 100;
                resultText = result + " cm";
                break;
            case "Kilogram → Gram":
                result = inputValue * 1000;
                resultText = result + " g";
                break;
            case "Litre → Millilitre":
                result = inputValue * 1000;
                resultText = result + " ml";
                break;
        }

        tvResult.setText("Kết quả: " + resultText);
    }
}