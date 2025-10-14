package clc65.tuongng59.chuyendoitiente;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUSD, edtVND;
    Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUSD = findViewById(R.id.edtUSD);
        edtVND = findViewById(R.id.edtVND);
        btnConvert = findViewById(R.id.btnConvert);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usdStr = edtUSD.getText().toString();

                if (usdStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số USD!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double usd = Double.parseDouble(usdStr);
                double vnd = usd * 26355;
                edtVND.setText(String.format("%.0f", vnd));
            }
        });
    }

}