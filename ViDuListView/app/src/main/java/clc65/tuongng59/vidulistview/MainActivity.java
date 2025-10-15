package clc65.tuongng59.vidulistview;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvBattleship;

    void TimDK(){
        lvBattleship = findViewById(R.id.lvBattleship);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDK();
        //Chuẩn bị nguồn dữ liệu hiển thị
        //--- Khai Báo
        ArrayList<String> lstBB;
        lstBB = new ArrayList<String>();
        //Lấy dữ liệu đưa vòa lstBB
        //Lấy ở đâu? = file, database, internet(cloud)...
        lstBB = getData();
        //Tạo adapter
        ArrayAdapter<String> bbAdapter;
        bbAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lstBB);
        lvBattleship.setAdapter(bbAdapter);

        lvBattleship.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Xử lý việc chọn item ở đây
                //Biến position chứa vị trí của item được chọn
                //(4.1) Lấy giá trị phần tử
                //C1: Lấy gián tiếp qua Adapter
                String bbDuocChon = bbAdapter.getItem(position).toString();
                //C2: Lấy trực tiếp từ biến chứa danh sách
                //String bbDuocChon1 = lstBB.get(position);
                //(4.2) làm gì với nó thì tùy bài toán
                String thongBao = "Bạn đã chọn grind BB: " + bbDuocChon;
                Toast.makeText(MainActivity.this, thongBao, Toast.LENGTH_LONG).show();
            }
        });
    }

    ArrayList<String> getData(){
        //Code đọc dữ liệu và cất vào biến tạm, trước khi return
        ArrayList<String> dsTam = new ArrayList<String>();
        //Code ở đây
        //Bài này, ta hard-code, ta fake dữ liệu tại đây
        dsTam.add("République");
        dsTam.add("Cristoforo Colombo");
        dsTam.add("St. Vincent");
        dsTam.add("Schlieffen");
        return dsTam;
    }
}