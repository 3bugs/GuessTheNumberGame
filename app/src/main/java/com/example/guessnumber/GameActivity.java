package com.example.guessnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

@SuppressWarnings("ConstantConditions")
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTenDigitTextView, mDigitTextView, mAnswerTextView;
    private int mAnswerNumber;
    private String mGuessNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupViews();
        startGame();
    }

    private void startGame() {
        mGuessNumber = "";
        updateDigits();

        Random random = new Random();
        mAnswerNumber = random.nextInt(100);
        Toast t = Toast.makeText(this, "สุ่มได้เลข " + mAnswerNumber, Toast.LENGTH_LONG);
        t.show();
    }

    private void setupViews() {
        mTenDigitTextView = (TextView) findViewById(R.id.ten_digit_text_view);
        mDigitTextView = (TextView) findViewById(R.id.digit_text_view);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        Button number1 = (Button) findViewById(R.id.number_1);
        Button number2 = (Button) findViewById(R.id.number_2);
        Button number3 = (Button) findViewById(R.id.number_3);
        Button number4 = (Button) findViewById(R.id.number_4);
        Button number5 = (Button) findViewById(R.id.number_5);
        Button number6 = (Button) findViewById(R.id.number_6);
        Button number7 = (Button) findViewById(R.id.number_7);
        Button number8 = (Button) findViewById(R.id.number_8);
        Button number9 = (Button) findViewById(R.id.number_9);
        Button number0 = (Button) findViewById(R.id.number_0);

        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        number0.setOnClickListener(this);

        Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuessNumber = "";
                updateDigits();
            }
        });

        final Button guessButton = (Button) findViewById(R.id.guess_button);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGuessNumber.length() == 0) {
                    Toast t = Toast.makeText(GameActivity.this, "คุณยังไม่ได้ทายเลข", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }

                int guessNumber = Integer.valueOf(mGuessNumber);
                if (guessNumber == mAnswerNumber) {
                    mAnswerTextView.setText(guessNumber + " ถูกต้องนะครับ");
                } else if (guessNumber > mAnswerNumber) {
                    mAnswerTextView.setText(guessNumber + " มากเกินไป");
                    mGuessNumber = "";
                    updateDigits();
                } else if (guessNumber < mAnswerNumber) {
                    mAnswerTextView.setText(guessNumber + " น้อยเกินไป");
                    mGuessNumber = "";
                    updateDigits();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (mGuessNumber.length() == 2) {
            Toast t = Toast.makeText(this, "ใส่ตัวเลขครบ 2 หลักแล้ว", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        String numberOnButton = ((Button) v).getText().toString();
        mGuessNumber += numberOnButton;

        updateDigits();
    }

    private void updateDigits() {
        if (mGuessNumber.length() == 1) {
            mDigitTextView.setText(mGuessNumber);
        } else if (mGuessNumber.length() == 2) {
            mDigitTextView.setText(mGuessNumber.substring(1));
            mTenDigitTextView.setText(mGuessNumber.substring(0, 1));
        } else if (mGuessNumber.length() == 0) {
            mDigitTextView.setText("");
            mTenDigitTextView.setText("");
        }
    }
}
