package thigk1.nguyenhuynhtuong.thigk1_nguyenhuynhtuong;

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

    private EditText edtChieuDai, edtChieuRong;
    private TextView tvKetQua;
    private MaterialButton btnTinhDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtChieuDai = findViewById(R.id.edtChieuDai);
        edtChieuRong = findViewById(R.id.edtChieuRong);
        tvKetQua = findViewById(R.id.tvKetQua);
        btnTinhDT = findViewById(R.id.btnTinhDT);

        btnTinhDT.setOnClickListener(v -> {
            String strChieuDai = edtChieuDai.getText().toString().trim();
            String strChieuRong = edtChieuRong.getText().toString().trim();

            if (strChieuDai.isEmpty() || strChieuRong.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ chiều dài, chiều rộng!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double chieuDai = Double.parseDouble(strChieuDai);
                double chieuRong = Double.parseDouble(strChieuRong);

                if (chieuDai<0||chieuRong<0) {
                    Toast.makeText(this, "Chiều dài, chiều rộng phải > 0!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double dienTich = chieuDai * chieuRong;

                tvKetQua.setText("Diện tích hình chữ nhật: "+ dienTich);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập đúng định dạng số!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}