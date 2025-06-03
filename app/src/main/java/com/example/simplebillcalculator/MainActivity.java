package com.example.simplebillcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 声明UI组件
    private EditText etFood, etTraffic, etStudy, etOther;
    private TextView tvTotal;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化UI组件
        initViews();

        // 设置按钮点击事件
        setupButtonListeners();
    }

    private void initViews() {
        etFood = findViewById(R.id.et_food);
        etTraffic = findViewById(R.id.et_traffic);
        etStudy = findViewById(R.id.et_study);
        etOther = findViewById(R.id.et_other);
        tvTotal = findViewById(R.id.tv_total);
        btnCalculate = findViewById(R.id.btn_calculate);
    }

    private void setupButtonListeners() {
        // 计算按钮点击事件
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        double total = 0.0;

        // 获取各分类金额
        total += getValue(etFood);
        total += getValue(etTraffic);
        total += getValue(etStudy);
        total += getValue(etOther);

        // 显示结果（保留两位小数）
        tvTotal.setText("总金额：" + String.format("%.2f", total));
    }

    // 安全转换方法
    private double getValue(EditText editText) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}