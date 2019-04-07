package develop.com.tracnghiem;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartTest extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_stt)
    TextView tvStt;
    @BindView(R.id.tv_qs)
    TextView tvQs;
    @BindView(R.id.tv_ans_1)
    TextView tvAns1;
    @BindView(R.id.ln_ans_1)
    LinearLayout lnAns1;
    @BindView(R.id.tv_ans_2)
    TextView tvAns2;
    @BindView(R.id.ln_ans_2)
    LinearLayout lnAns2;
    @BindView(R.id.tv_ans_3)
    TextView tvAns3;
    @BindView(R.id.ln_ans_3)
    LinearLayout lnAns3;
    @BindView(R.id.tv_ans_4)
    TextView tvAns4;
    @BindView(R.id.ln_ans_4)
    LinearLayout lnAns4;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_timer)
    TextView tvTimer;

    private List<Question> list;

    private int score = 0, highScore = 0;
    private int pos = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        ButterKnife.bind(this);

        init();

        onClick();

        initQuestion(pos);

    }

    private void initQuestion(int pos) {
        if (pos == list.size()) {

            if (score > highScore)
                PrefUtil.saveInt(this, "score", score);
            Toast.makeText(this, "Hoàn thành bài test. Số điểm của bạn là: " + score + " điểm!!!", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        } else {

            countDownTimer = new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    tvTimer.setText("" + String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {
                    checkAnswer("");
                    tvTimer.setText("done!");
                }

            }.start();
            tvStt.setText((pos + 1) + "/" + list.size());
            tvQs.setText(list.get(pos).getQuestion());
            tvAns1.setText("A. " + list.get(pos).getA());
            tvAns2.setText("B. " + list.get(pos).getB());
            tvAns3.setText("C. " + list.get(pos).getC());
            tvAns4.setText("D. " + list.get(pos).getD());
            tvScore.setText(score + "");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (highScore < score)
            PrefUtil.saveInt(this, "score", score);
    }

    private void onClick() {
        PushDownAnim.setPushDownAnimTo(lnAns1, lnAns2, lnAns3, lnAns4)
                .setOnClickListener(this);
    }

    private void init() {
        highScore = PrefUtil.getInt(this, "score", -1);

        list = new ArrayList<>();
        list.add(new Question("Đâu không phải là một bước trong quy trình hiến máu?", "Khám lâm sàng", "Trà đường", "Xét nghiệm máu", "Đăng ký", "b"));
        list.add(new Question("Bạn hãy cho biết khoảng thời gian giữa 2 lần hiếu tiểu cầu là?", "1 tuần", "2 tuần", "3 tuần", "4 tuần", "b"));
        list.add(new Question("Đâu không phải là tên của nhóm máu?", "A rh+", "B rh-", "O rh-", "F rh-", "d"));
        list.add(new Question("Ngày toàn dân hiến máu là?", "7/4", "1/6", "16/4", "8/3", "a"));
        list.add(new Question("Ngày tôn vinh người hiến máu nhân đạo?", "7/4", "15/10", "14/6", "9/5", "c"));
        list.add(new Question("Tác dụng của tiểu cầu?", "Cầm máu", "Hệ miễn dịch", "Vẩn chuyển Protein", "Vẩn chuyển Oxi", "a"));
        list.add(new Question("Ngày thành lập Hội chữ thập đỏ Việt Nam là?", "2/3/1946", "3/2/1964", "23/1/1964", "23/11/1946", "d"));
        list.add(new Question("Ngày thành lập Hội chữ thập đỏ tỉnh Thái Nguyên là?", "15/10/1973", "26/3/1970", "15/1/1971", "1/5/1972", "a"));
        list.add(new Question("Ngày quốc tế tình nguyện là ngày?", "20/2", "15/2", "5/12", "11/1", "c"));

        Collections.shuffle(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ln_ans_1:
                checkAnswer("a");
                break;
            case R.id.ln_ans_2:
                checkAnswer("b");
                break;
            case R.id.ln_ans_3:
                checkAnswer("c");
                break;
            case R.id.ln_ans_4:
                checkAnswer("d");
                break;
        }
    }

    private void checkAnswer(String result) {

        if (result.equals(list.get(pos).getResult()))
            score += 10;

        progress.setVisibility(View.VISIBLE);
        pos++;
        countDownTimer.cancel();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                initQuestion(pos);
            }
        }, 1000);
    }
}
