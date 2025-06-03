package com.example.androidprojects;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    private TextView display;
    private double firstValue = 0;
    private String pendingOperation = "=";
    private boolean newInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);

        // 数字按钮
        setNumberButton(R.id.btn0, "0");
        setNumberButton(R.id.btn1, "1");
        setNumberButton(R.id.btn2, "2");
        setNumberButton(R.id.btn3, "3");
        setNumberButton(R.id.btn4, "4");
        setNumberButton(R.id.btn5, "5");
        setNumberButton(R.id.btn6, "6");
        setNumberButton(R.id.btn7, "7");
        setNumberButton(R.id.btn8, "8");
        setNumberButton(R.id.btn9, "9");
        setNumberButton(R.id.btnDot, ".");

        // 操作按钮
        setOperationButton(R.id.btnAdd, "+");
        setOperationButton(R.id.btnSubtract, "-");
        setOperationButton(R.id.btnMultiply, "×");
        setOperationButton(R.id.btnDivide, "÷");

        // 功能按钮
        findViewById(R.id.btnEquals).setOnClickListener(v -> performCalculation());
        findViewById(R.id.btnClear).setOnClickListener(v -> clearCalculator());
    }

    private void setNumberButton(int id, final String value) {
        findViewById(id).setOnClickListener(v -> {
            if (newInput) {
                display.setText("");
                newInput = false;
            }
            display.append(value);
        });
    }

    private void setOperationButton(int id, final String op) {
        findViewById(id).setOnClickListener(v -> {
            try {
                firstValue = Double.parseDouble(display.getText().toString());
            } catch (NumberFormatException e) {
                // 忽略错误
            }
            pendingOperation = op;
            newInput = true;
        });
    }

    private void performCalculation() {
        if (newInput) return;

        double secondValue = Double.parseDouble(display.getText().toString());
        double result = 0;

        switch (pendingOperation) {
            case "+": result = firstValue + secondValue; break;
            case "-": result = firstValue - secondValue; break;
            case "×": result = firstValue * secondValue; break;
            case "÷":
                if (secondValue != 0) result = firstValue / secondValue;
                else display.setText("错误");
                break;
            case "=": result = secondValue; break;
        }

        // 显示结果（如果是整数则显示整数）
        if (result == (int) result) {
            display.setText(String.valueOf((int) result));
        } else {
            display.setText(String.valueOf(result));
        }
        newInput = true;
    }

    private void clearCalculator() {
        display.setText("0");
        firstValue = 0;
        pendingOperation = "=";
        newInput = true;
    }
}