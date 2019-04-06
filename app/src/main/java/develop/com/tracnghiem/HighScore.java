package develop.com.tracnghiem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HighScore extends AppCompatActivity {

    @BindView(R.id.tv_high_score)
    TextView tvHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        ButterKnife.bind(this);

        int score = PrefUtil.getInt(this, "score", -1);
        if(score == -1){
            tvHighScore.setText("Chưa có điểm cao đã lưu");
        }
        else{
            tvHighScore.setText("Điểm cao: " + score);
        }
    }
}
