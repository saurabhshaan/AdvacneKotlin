package bharat.otouch.www.myquizapplication;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> questions;
    int score=0;
    int qid = 0;
    Question currentQ;

    private TextView textView;
    private RadioButton rd0,rd1,rd2,rd3;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhelper dBhelper = new DBhelper(this);
        questions=dBhelper.getAllQuestions();
        currentQ= questions.get(qid);

        textView = (TextView) findViewById(R.id.textbox);
        rd0 = (RadioButton) findViewById(R.id.radio0);
        rd1 = (RadioButton) findViewById(R.id.radio1);
        rd2 = (RadioButton) findViewById(R.id.radio2);
        //rd3 = (RadioButton) findViewById(R.id.radio3);
        button = (Button) findViewById(R.id.nextbtn);
        setQuestionView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
                int select = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(select);

                if (currentQ.getANSWER().equals(radioButton.getText())){
                    score++;
                    Log.d("TAG","ANS"+radioButton.getText());
                }
                if (qid<5){
                    currentQ=questions.get(qid);
                    setQuestionView();
                }else {
                    Intent intent = new Intent(MainActivity.this,Result.class);
                    Bundle b = new Bundle();
                    b.putInt("score",score);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();


                }
            }
        });
    }
    private void setQuestionView()
    {
        textView.setText(currentQ.getQUESTION());
        rd0.setText(currentQ.getOPTA());
        rd1.setText(currentQ.getOPTB());
        rd2.setText(currentQ.getOPTC());
        qid++;
    }

}
