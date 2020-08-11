package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private boolean formatFraction;
    private EditText inputText;
    private TextView answerText;
    private ArrayList<Character> inputTextArr;
    private int cursorIndex;
    private Button buttonSolve;
    private View fractionDivider;
    private TextView answerTextDenominator;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout = findViewById(R.id.MainLayout);
        mainLayout.measure(0,0);
        screenWidth = mainLayout.getMeasuredWidth();
        
        Button buttonOpeningParenthesis = findViewById(R.id.ButtonParenO);
        Button buttonClosingParenthesis = findViewById(R.id.ButtonParenC);
        ToggleButton buttonFraction = findViewById(R.id.ButtonFraction);

        Button button1 = findViewById(R.id.Button1);
        Button button2 = findViewById(R.id.Button2);
        Button button3 = findViewById(R.id.Button3);
        Button button4 = findViewById(R.id.Button4);
        Button button5 = findViewById(R.id.Button5);
        Button button6 = findViewById(R.id.Button6);
        Button button7 = findViewById(R.id.Button7);
        Button button8 = findViewById(R.id.Button8);
        Button button9 = findViewById(R.id.Button9);
        Button button0 = findViewById(R.id.Button0);
        Button buttonDot = findViewById(R.id.ButtonDot);

        Button buttonAdd = findViewById(R.id.ButtonAdd);
        Button buttonSub = findViewById(R.id.ButtonSub);
        Button buttonMulti = findViewById(R.id.ButtonMulti);
        Button buttonDiv = findViewById(R.id.ButtonDiv);
        buttonSolve = findViewById(R.id.ButtonSolve);
        Button buttonLeft = findViewById(R.id.ButtonLeft);
        Button buttonRight = findViewById(R.id.ButtonRight);
        Button buttonReset = findViewById(R.id.ButtonReset);
        Button buttonDel = findViewById(R.id.ButtonDel);

        fractionDivider = findViewById(R.id.FractionDivider);

        answerText = findViewById(R.id.AnswerText);
        answerTextDenominator = findViewById(R.id.AnswerTextDenominator);
        inputText = findViewById(R.id.InputText);
        inputText.setShowSoftInputOnFocus(false);
        inputTextArr = new ArrayList<>();
        inputText.requestFocus();
        cursorIndex = 0;
        inputText.setText("");

        formatFraction = false;
        updateFraction(false);

        inputText.setSelection(cursorIndex);



        buttonOpeningParenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText('(');
            }
        });
        buttonClosingParenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText(')');
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('1');
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('2');
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('3');
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('4');
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('5');
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('6');
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('7');
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('8');
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addInputText('9');
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText('0');
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText('+');
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText('-');
            }
        });
        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText('x');
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText('/');
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInputText('.');
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cursorIndex > 0) {
                    cursorIndex--;
                    inputText.setSelection(cursorIndex);
                }
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cursorIndex < inputTextArr.size()) {
                    cursorIndex++;
                    inputText.setSelection(cursorIndex);
                }
            }
        });

        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputText.length() == 0) {
                    return;
                }
                try {
                    Calculation calculation = new Calculation(inputTextArr);
                    ArrayList<Integer> list = calculation.solveAnswer();
                    int numerator = list.get(0);
                    int denominator = list.get(1);
                    updateFraction(formatFraction);
                    if(formatFraction) {
                        answerText.setText(String.format(Locale.US, "%d", numerator));
                        answerTextDenominator.setText(String.format(Locale.US, "%d", denominator));
                        setDividerWidth();
                    } else {
                        answerText.getLayoutParams().width = screenWidth;
                        answerText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                        if(denominator == 1) {
                            answerText.setText(String.format(Locale.US, "%d", numerator));
                        } else {
                            double answer = (double) numerator / denominator;
                            answerText.setText(String.format(Locale.US, "%.5f", answer));
                        }
                        answerText.requestLayout();
                    }
                } catch (InvalidFormatError e) {
                    Toast.makeText(MainActivity.this, "Invalid format used", Toast.LENGTH_SHORT).show();
                } catch (ZeroDivisionException e) {
                    Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                } catch (OverflowException e) {
                    Toast.makeText(MainActivity.this, "Cannot handle more than 10 digits", Toast.LENGTH_SHORT).show();
                } catch (ArithmeticException e) {
                    Toast.makeText(MainActivity.this, "The result is too large", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextArr = new ArrayList<>();
                cursorIndex = 0;
                inputText.setText("");
                resetAnswerTexts();
                updateFraction(false);
                inputText.setSelection(cursorIndex);
            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputText.length() < 1) {
                    return;
                }
                inputTextArr.remove(cursorIndex-1);
                cursorIndex--;
                StringBuffer inputStr = new StringBuffer();
                for(char c : inputTextArr) {
                    inputStr.append(c);
                }
                inputText.setText(inputStr);
                inputText.setSelection(cursorIndex);
            }
        });
        buttonFraction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                formatFraction = b;
                buttonSolve.performClick();
            }
        });
    }

    private void addInputText(char c) {
        inputTextArr.add(cursorIndex, c);
        cursorIndex++;
        StringBuffer text = new StringBuffer();
        for(char ch : inputTextArr) {
            text.append(ch);
        }
        inputText.setText(text);
        inputText.setSelection(cursorIndex);
    }

    private void setDividerWidth() {
        answerText.measure(0,0);
        answerTextDenominator.measure(0,0);
        int maxWidth = Math.max(answerText.getMeasuredWidth(), answerTextDenominator.getMeasuredWidth());
        //Log.d("Debug", "Numerator: " + numeratorWidth + "::: Denominator: " + denominatorWidth);
        fractionDivider.getLayoutParams().width = maxWidth;
        answerText.getLayoutParams().width = maxWidth;
        answerText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        answerTextDenominator.getLayoutParams().width = maxWidth;
        answerTextDenominator.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        fractionDivider.requestLayout();
        answerText.requestLayout();
        answerTextDenominator.requestLayout();
    }

    private void updateFraction(boolean visible) {
        int visibility = visible ? View.VISIBLE : View.INVISIBLE;
        fractionDivider.setVisibility(visibility);
        answerTextDenominator.setVisibility(visibility);
    }

    private void resetAnswerTexts() {
        answerText.setText("");
        answerTextDenominator.clearComposingText();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }


}
