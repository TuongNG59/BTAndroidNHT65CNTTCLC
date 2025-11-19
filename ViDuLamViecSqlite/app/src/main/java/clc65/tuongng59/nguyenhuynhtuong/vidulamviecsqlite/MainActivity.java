package clc65.tuongng59.nguyenhuynhtuong.vidulamviecsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

//        getBookData();

        ArrayList<String> dsTenSach = getBookName();

        ListView listView = findViewById(R.id.lvDanhSachTenSach);
        ArrayAdapter<String> adapterTenSach = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,dsTenSach);
        listView.setAdapter(adapterTenSach);


        Button bThem = findViewById(R.id.btnThemSach);
        bThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu
                EditText edTenSach = findViewById(R.id.edtTenSach);
                String tenSach = edTenSach.getText().toString();

                EditText edGia = findViewById(R.id.edtGiaBan);
                float giaBan  = Float.parseFloat(edGia.getText().toString());

                // Thêm vào DB
                ContentValues row = new ContentValues();
                row.put("BookName", tenSach);
                row.put("Price", giaBan);

                db.insert("BOOKS", null, row);

                // Làm tươi lại ListView
                adapterTenSach.notifyDataSetChanged();
            }
        });



    }

    ArrayList<BOOKS> getBookData(){
        // Tạo 1 cớ sở dữ liệu
        db =  openOrCreateDatabase("books.db", MODE_PRIVATE, null);

//        String sqlTaoBang ="CREATE TABLE BOOKS(BookID integer PRIMARY KEY, BookName text, Page integer, Price Float, Description text)";
//        String sqlXoaBang = "DROP TABLE IF EXISTS BOOKS";
//
//        db.execSQL(sqlXoaBang);
//        db.execSQL(sqlTaoBang);
//
//
//        String sqlThem1 = "INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'sách về java')";
//        String sqlThem2 = "INSERT INTO BOOKS VALUES(2, 'Android', 320, 19.00, 'Android cơ bản')";
//
//        db.execSQL(sqlThem1);
//        db.execSQL(sqlThem2);

        //Truy vấn
        String sqlSelectAll = "SELECT * FROM BOOKS";
        Cursor resultSet = db.rawQuery(sqlSelectAll, null);
        ArrayList<BOOKS> dsSach = new ArrayList<BOOKS>();
        resultSet.moveToFirst();
        while(true) {
            //Lấy dữ liệu của dòng/bản ghi hiện tại, trả bởi resultSet
            int maSach = resultSet.getInt(0);
            String tenSach = resultSet.getString(1);
            int soTrang  = resultSet.getInt(2);
            float giaBan = resultSet.getFloat(3);
            String moTa = resultSet.getString(4);
            //Gói vào 1 đối tượng --> tạo 1 thực thể/lớp
            BOOKS book = new BOOKS(maSach,tenSach,soTrang,giaBan,moTa);
            //Hiện tên sách lên listview
            //Thêm vào 1 biến danh sách
            dsSach.add(book);

            //Di chuyển đến bản ghi tiếp theo, nếu đã hết thì thoát vòng lặp
            if(resultSet.moveToNext()==false) break;
        }

        db.close();

        return dsSach;
    }

    ArrayList<String> getBookName(){
        db =  openOrCreateDatabase("books.db", MODE_PRIVATE, null);
        String sqlSelectAll = "SELECT * FROM BOOKS";
        Cursor resultSet = db.rawQuery(sqlSelectAll, null);
        ArrayList<String> dsTenSach = new ArrayList<String>();
        resultSet.moveToFirst();
        while(true){
            int maSach = resultSet.getInt(0);
            String tenSach = resultSet.getString(1);
            dsTenSach.add(tenSach);
            if(resultSet.moveToNext()==false) break;
        }
        db.close();
        return dsTenSach;
    }
}