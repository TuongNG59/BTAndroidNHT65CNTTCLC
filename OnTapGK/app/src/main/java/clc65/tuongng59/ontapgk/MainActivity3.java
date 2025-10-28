package clc65.tuongng59.ontapgk;

import clc65.tuongng59.ontapgk.Item3Activity;
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
    private ListView lvMonHoc;
    private ArrayList<String> dsMonHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lvMonHoc = findViewById(R.id.lvMonHoc);

        dsMonHoc = loadMonHocFromAssets();

        if (dsMonHoc.isEmpty()) {
            Toast.makeText(this, "Không thể tải dữ liệu môn học", Toast.LENGTH_SHORT).show();
            dsMonHoc.add("Lỗi tải file JSON");
        }

        // 2️⃣ Adapter (Đã đúng)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_sub_list_view,
                R.id.tv_item_name,
                dsMonHoc
        );
        lvMonHoc.setAdapter(adapter);

        // 3️⃣ Sự kiện click vào 1 môn học (Không đổi)
        lvMonHoc.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            String monHoc = dsMonHoc.get(position);

            Intent intent = new Intent(MainActivity3.this, Item3Activity.class);
            intent.putExtra("tenMon", monHoc);
            startActivity(intent);
        });
    }

    private ArrayList<String> loadMonHocFromAssets() {
        ArrayList<String> monHocList = new ArrayList<>();
        String jsonString;

        try {
            InputStream is = getAssets().open("list.json"); // Đọc file list.json
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
            // Phân tích Mảng các Đối tượng (JSONArray of JSONObjects)
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                // Lấy từng đối tượng môn học
                JSONObject monHocObject = jsonArray.getJSONObject(i);

                // Chỉ lấy ra "tenMon" để hiển thị lên ListView
                String tenMon = monHocObject.getString("tenMon");
                monHocList.add(tenMon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return monHocList;
    }
}