package com.example.simplebillcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etFood, etTraffic, etStudy, etOther;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        etFood = findViewById(R.id.et_food);
        etTraffic = findViewById(R.id.et_traffic);
        etStudy = findViewById(R.id.et_study);
        etOther = findViewById(R.id.et_other);
        tvTotal = findViewById(R.id.tv_total);

        Button btnCalculate = findViewById(R.id.btn_calculate);

        // 统计按钮点击事件
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        double total = 0.0;

        // 获取各分类金额（空值处理）
        total += getValue(etFood);
        total += getValue(etTraffic);
        total += getValue(etStudy);
        total += getValue(etOther);

        // 显示结果（保留两位小数）
        tvTotal.setText(String.format("总金额：￥%.2f", total));
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