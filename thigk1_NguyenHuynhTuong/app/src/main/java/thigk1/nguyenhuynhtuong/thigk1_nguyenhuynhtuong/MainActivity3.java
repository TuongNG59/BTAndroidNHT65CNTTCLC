package thigk1.nguyenhuynhtuong.thigk1_nguyenhuynhtuong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private ListView lvVatLieu;
    private ArrayList<String> dsVatLieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lvVatLieu = findViewById(R.id.lvVatLieu);

        dsVatLieu = loadMonHocFromAssets();

        if (dsVatLieu.isEmpty()) {
            Toast.makeText(this, "Không thể tải dữ liệu môn học", Toast.LENGTH_SHORT).show();
            dsVatLieu.add("Lỗi tải file JSON");
        }

        // 2️⃣ Adapter (Đã đúng)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_sub_list_view,
                R.id.tv_item_name,
                dsVatLieu
        );
        lvVatLieu.setAdapter(adapter);

        // 3️⃣ Sự kiện click vào 1 môn học (Không đổi)
        lvVatLieu.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            String monHoc = dsVatLieu.get(position);

            Intent intent = new Intent(MainActivity3.this, VLdetailActivity.class);
            intent.putExtra("tenMon", monHoc);
            startActivity(intent);
        });
    }

    private ArrayList<String> loadMonHocFromAssets() {
        ArrayList<String> monHocList = new ArrayList<>();
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
            return monHocList;
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject monHocObject = jsonArray.getJSONObject(i);
                String tenMon = monHocObject.getString("tenMon");
                monHocList.add(tenMon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return monHocList;
    }
}