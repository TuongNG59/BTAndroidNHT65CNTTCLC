package clc65.tuongng59.ontapgk;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity2 extends AppCompatActivity {

    private EditText edtGiuaKy, edtCuoiKy;
    private TextView tvKetQua;
    private MaterialButton btnTinhTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtGiuaKy = findViewById(R.id.edtGiuaKy);
        edtCuoiKy = findViewById(R.id.edtCuoiKy);
        tvKetQua = findViewById(R.id.tvKetQua);
        btnTinhTB = findViewById(R.id.btnTinhTB);

        btnTinhTB.setOnClickListener(v -> {
            String strGiuaKy = edtGiuaKy.getText().toString().trim();
            String strCuoiKy = edtCuoiKy.getText().toString().trim();

            if (strGiuaKy.isEmpty() || strCuoiKy.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ điểm!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double diemGiuaKy = Double.parseDouble(strGiuaKy);
                double diemCuoiKy = Double.parseDouble(strCuoiKy);

                if (diemGiuaKy < 0 || diemGiuaKy > 10 || diemCuoiKy < 0 || diemCuoiKy > 10) {
                    Toast.makeText(this, "Điểm phải nằm trong khoảng từ 0 - 10!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double diemTB = (diemGiuaKy * 0.5) + (diemCuoiKy * 0.5);

                tvKetQua.setText(String.format("Điểm trung bình: %.2f", diemTB));

                String xepLoai;
                if (diemTB >= 8.5) xepLoai = "Giỏi";
                else if (diemTB >= 7.0) xepLoai = "Khá";
                else if (diemTB >= 5.0) xepLoai = "Trung bình";
                else xepLoai = "Yếu";

                tvKetQua.setText(String.format("Điểm trung bình: %.2f\nXếp loại: %s", diemTB, xepLoai));

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập đúng định dạng số!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}