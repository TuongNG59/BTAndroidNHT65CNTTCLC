package thigk1.nguyenhuynhtuong.thigk1_nguyenhuynhtuong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Activity4Adapter adapter;
    private List<Activity4Model> activityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        recyclerView = findViewById(R.id.recyclerView);

        activityList = loadActivitiesFromAssets();

        if (activityList.isEmpty()) {
            Toast.makeText(this, "Không thể tải dữ liệu hoạt động", Toast.LENGTH_SHORT).show();
            // Thêm item lỗi để người dùng biết
            activityList.add(new Activity4Model(R.drawable.ic_event_placeholder, "Lỗi tải JSON", "Không thể đọc file activities.json"));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Activity4Adapter(activityList, new Activity4Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Activity4Model item) {
                // 4️⃣ Khi click (Giữ nguyên)
                Intent intent = new Intent(MainActivity4.this, Item4Activity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("desc", item.getDescription());
                intent.putExtra("image", item.getImageResId());
                startActivity(intent);

                Toast.makeText(MainActivity4.this, "Đang mở: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private List<Activity4Model> loadActivitiesFromAssets() {
        List<Activity4Model> models = new ArrayList<>();
        String jsonString;

        try {
            InputStream is = getAssets().open("recycle.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            return models;
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                String imageName = obj.getString("imageName");
                String title = obj.getString("title");
                String description = obj.getString("description");

                int imageResId = getDrawableIdByName(imageName);

                models.add(new Activity4Model(imageResId, title, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return models;
    }

    private int getDrawableIdByName(String name) {

        int resId = getResources().getIdentifier(name, "drawable", getPackageName());

        if (resId == 0) {
            return R.drawable.ic_event_placeholder;
        }
        return resId;
    }
}