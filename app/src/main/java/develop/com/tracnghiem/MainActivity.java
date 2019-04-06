package develop.com.tracnghiem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_start, R.id.btn_score})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startActivity(new Intent(MainActivity.this, StartTest.class));
                break;
            case R.id.btn_score:
                startActivity(new Intent(MainActivity.this, HighScore.class));
                break;
        }
    }
}
