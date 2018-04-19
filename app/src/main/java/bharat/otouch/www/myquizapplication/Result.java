package bharat.otouch.www.myquizapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    private TextView textView;
    String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = (TextView) findViewById(R.id.result);

        Bundle b =getIntent().getExtras();
        int score = b.getInt("score");
        ss = String.valueOf(score);
        textView.setText(ss);

    }
}
