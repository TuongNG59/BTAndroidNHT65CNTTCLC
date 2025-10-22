package clc65.tuongng59.viduintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //1. Nhận về Intent
        Intent iNhan = getIntent();
        //2. Bóc ra
        String htNhan = iNhan.getStringExtra("ht");
        //3. Xử lý
            //Set lên textivew

        //Nút Back
        Button buttonBack = findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBack = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(iBack);
            }
        });
    }
}