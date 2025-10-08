package clc65.tuongng59.vidulistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button bSayhi, bSayhello;
    TextView tResult;

    void TimDK() {
        bSayhi = findViewById(R.id.btnSayHi);
        bSayhello = findViewById(R.id.btnSayHello);
        tResult = findViewById(R.id.txtResult);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDK();
        bSayhi.setOnClickListener(ngheSayhi);

        bSayhello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tResult.setText("Chào");
            }
        });

    }

    View.OnClickListener ngheSayhi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bSayhi.setOnClickListener(ngheSayhi);
            tResult.setText("Hi");
        }
    };

}