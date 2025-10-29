package thigk1.nguyenhuynhtuong.thigk1_nguyenhuynhtuong;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class VLdetailActivity extends AppCompatActivity {
    private TextView tvTenVatLieu, tvMoTa;
    private MaterialButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item3);

        tvTenVatLieu = findViewById(R.id.tvTenVatLieu);
        tvMoTa = findViewById(R.id.tvMoTa);
        btnBack = findViewById(R.id.btnBack3);

        String tenVatLieu = getIntent().getStringExtra("tenVatLieu");
        tvTenVatLieu.setText(tenVatLieu);


        findVatLieuDetails(tenVatLieu);


        btnBack.setOnClickListener(v -> finish());
    }

    private void findVatLieuDetails(String tenVatLieuCanTim) {
        String jsonString;
        try {
            InputStream is = getAssets().open("list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi đọc file JSON", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vatLieuObject = jsonArray.getJSONObject(i);

                if (vatLieuObject.getString("tenVatLieu").equals(tenVatLieuCanTim)) {

                    tvMoTa.setText(vatLieuObject.getString("moTa"));
                    return;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi phân tích JSON", Toast.LENGTH_SHORT).show();
        }
    }
}
