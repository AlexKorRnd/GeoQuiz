package geoquiz.android.bignerdranch.com.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Алексей on 02.05.2015.
 */
public class CheatActivity extends ActionBarActivity {

    private static final String TAG = "CheatActivity ";
    private static final String KEY_INDEX = "CheatActivity_index";

    public static final String EXTRA_ANSWER_IS_TRUE =
            "geoquiz.android.bignerdranch.com.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "geoquiz.android.bignerdranch.com.answer_shown";

    private boolean mAnswerIsTrue;
    private boolean mIsShownAnswer;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    private void setAnswerTextView(){
        if (mAnswerIsTrue){
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
        setAnswerShownResult(mIsShownAnswer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mIsShownAnswer = false;

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsShownAnswer = true;
                setAnswerTextView();
            }
        });

        if (savedInstanceState != null){
            mIsShownAnswer = savedInstanceState.getBoolean(KEY_INDEX);
            Log.d(TAG, "savedInstanceState != null");
            setAnswerTextView();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        Log.d(TAG, " onSaveInstanceState()");
        savedInstanceState.putBoolean(KEY_INDEX, mIsShownAnswer);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, " onPause() calles");
        //setAnswerShownResult(mIsShownAnswer);
    }
}

