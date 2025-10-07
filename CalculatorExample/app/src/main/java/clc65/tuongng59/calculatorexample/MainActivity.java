package clc65.tuongng59.calculatorexample;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2;
    Button  btnCong, btnTru,btnNhan,btnChia;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt1=findViewById(R.id.EdtA);
        edt2=findViewById(R.id.EdtB);
        btnCong=findViewById(R.id.BtnCong);
        btnTru=findViewById(R.id.BtnTru);
        btnNhan=findViewById(R.id.BtnNhan);
        btnChia=findViewById(R.id.BtnChia);
        txtKetQua=findViewById(R.id.TvKetQua);
    }
    public void XuLyPhepToan(View v){
        int a = Integer.parseInt(edt1.getText().toString());
        int b = Integer.parseInt(edt1.getText().toString());
        int KetQua;

        if(v.getId()==R.id.BtnCong){
            KetQua=a+b;
            txtKetQua.setText(String.valueOf(KetQua));
        }
        else if(v.getId()==R.id.BtnTru){
            KetQua=a-b;
            txtKetQua.setText(String.valueOf(KetQua));
        }
        else if(v.getId()==R.id.BtnNhan){
            KetQua=a*b;
            txtKetQua.setText(String.valueOf(KetQua));
        }
        else if(v.getId()==R.id.BtnChia){
            double KetQuaChia=(double)a/b;
            txtKetQua.setText(String.valueOf(KetQuaChia));
        }
    }
}