package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView inputText;
    private TextView answerText;
    private ArrayList<Character> inputTextArr;
    private int cursorIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpeningParenthesis = findViewById(R.id.ButtonParenO);
        Button buttonClosingParenthesis = findViewById(R.id.ButtonParenC);

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
        Button buttonSolve = findViewById(R.id.ButtonSolve);
        Button buttonLeft = findViewById(R.id.ButtonLeft);
        Button buttonRight = findViewById(R.id.ButtonRight);
        Button buttonReset = findViewById(R.id.ButtonReset);
        Button buttonDel = findViewById(R.id.ButtonDel);

        answerText = findViewById(R.id.AnswerText);
        inputText = findViewById(R.id.InputText);
        inputTextArr = new ArrayList<>();
        cursorIndex = 0;
        inputText.setText("");
        //inputText.onResolvePointerIcon(null , cursorIndex);

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
                    //inputText.onResolvePointerIcon(null , cursorIndex);
                }
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cursorIndex < inputTextArr.size()) {
                    cursorIndex++;
                    //inputText.onResolvePointerIcon(null , cursorIndex);
                }
            }
        });


        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputText.length() == 0) {
                    return;
                }
                Calculation calculation = new Calculation(inputTextArr);
                try {
                    answerText.setText(String.format(Locale.US, "%f", calculation.solveAnswer()));
                } catch (InvalidFormatError e) {
                    Toast.makeText(MainActivity.this, "Invalid format used", Toast.LENGTH_SHORT).show();
                } catch (ArithmeticException e) {
                    Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextArr = new ArrayList<>();
                cursorIndex = 0;
                //inputText.onResolvePointerIcon(null , cursorIndex);
                inputText.setText("");
                answerText.setText("");
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
                //inputText.onResolvePointerIcon( , cursorIndex);
                StringBuffer inputStr = new StringBuffer();
                for(char c : inputTextArr) {
                    inputStr.append(c);
                }
                inputText.setText(inputStr);
            }
        });


    }

    private void addInputText(char c) {
        inputTextArr.add(cursorIndex, c);
        cursorIndex++;
        //inputText.onResolvePointerIcon(null , cursorIndex);
        StringBuffer text = new StringBuffer();
        for(char ch : inputTextArr) {
            text.append(ch);
        }
        inputText.setText(text);
    }

    @Override
    public void onClick(View view) {

    }
}
