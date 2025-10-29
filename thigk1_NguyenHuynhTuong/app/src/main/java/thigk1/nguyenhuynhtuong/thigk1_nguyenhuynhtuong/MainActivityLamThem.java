package thigk1.nguyenhuynhtuong.thigk1_nguyenhuynhtuong;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityLamThem extends AppCompatActivity {
    private TextView tvQuestion, tvScore;
    private RadioGroup rgOptions;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnNext;

    private String[] questions = {
            "Android được phát triển dựa trên ngôn ngữ nào?",
            "File giao diện trong Android thường có đuôi là gì?",
            "Thành phần nào dùng để hiển thị danh sách?",
            "Tên công cụ dùng để biên dịch project Android là?"
    };

    private String[][] options = {
            {"Python", "Java", "C++", "Swift"},
            {"html", "xml", "json", "css"},
            {"ListView", "ImageView", "TextView", "EditText"},
            {"Gradle", "Maven", "Android Studio", "Logcat"}
    };

    private int[] correctAnswers = {1, 1, 0, 0};
    private int index = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lam_them);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        rgOptions = findViewById(R.id.rgOptions);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnNext = findViewById(R.id.btnNext);

        loadQuestion();

        btnNext.setOnClickListener(v -> {
            int selectedId = rgOptions.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Vui lòng chọn 1 đáp án!", Toast.LENGTH_SHORT).show();
                return;
            }

            int answerIndex = rgOptions.indexOfChild(findViewById(selectedId));
            if (answerIndex == correctAnswers[index]) {
                score++;
                Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Sai rồi!", Toast.LENGTH_SHORT).show();
            }

            index++;
            if (index < questions.length) {
                loadQuestion();
            } else {
                tvQuestion.setText("KẾT THÚC!");
                tvScore.setText("Điểm của bạn là: " + score + "/" + questions.length);
                btnNext.setEnabled(false);
                rgOptions.clearCheck();
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb3.setEnabled(false);
                rb4.setEnabled(false);
            }
        });
    }

    private void loadQuestion() {
        tvQuestion.setText(questions[index]);
        rb1.setText(options[index][0]);
        rb2.setText(options[index][1]);
        rb3.setText(options[index][2]);
        rb4.setText(options[index][3]);
        tvScore.setText("Câu " + (index + 1) + "/" + questions.length);
        rgOptions.clearCheck();
    }
}