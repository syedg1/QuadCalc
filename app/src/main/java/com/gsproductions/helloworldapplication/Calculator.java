package com.gsproductions.helloworldapplication;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    public TextView calculator;
    boolean operationJustCompleted = false;
    boolean menuQuadPressed = false;
    boolean menuCalcPressed = false;
    boolean menu = true;
    double grand_total = 0;


    String total = "";
    double v1, v2, num;
    String sign = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
    }
    boolean CheckNumber() {
        calculator = (TextView) findViewById(R.id.calcScreen);
        try {
            Double.parseDouble(calculator.getText().toString());
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public void onPosNeg(View v) {
        if (CheckNumber()) {
            num = Double.parseDouble(calculator.getText().toString());
            total = String.valueOf(num * -1);
            grand_total = num * -1;
            calculator.setText(total);
        } else
            Toast.makeText(Calculator.this, "Enter a value", Toast.LENGTH_SHORT).show();
    }


    public void onClick(View v) {
        if (operationJustCompleted) {
            total = "";
            operationJustCompleted = false;
        }
        Button button = (Button) v;
        String Str = button.getText().toString();
        total += Str;
        calculator = (TextView) findViewById(R.id.calcScreen);
        calculator.setText(total);
    }


    public void onAdd(View v) {
        if (CheckNumber()) {
            if (operationJustCompleted) {
                v1 = grand_total;
                operationJustCompleted = false;
            } else {
                v1 = Double.parseDouble(total);
            }
            Button button = (Button) v;
            String Str = button.getText().toString();
            total = "";
            sign = Str;
            TextView calculator = (TextView) findViewById(R.id.calcScreen);
            calculator.setText("");
        } else {
            Toast.makeText(Calculator.this, "Enter a value", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCalculate(View v) {
        if (CheckNumber()) {
            TextView calculator = (TextView) findViewById(R.id.calcScreen);
            String str2 = calculator.getText().toString();
            v2 = Double.parseDouble(str2);
            if (sign.equals("+")) {
                grand_total = v1 + v2;
            } else if (sign.equals("-")) {
                grand_total = v1 - v2;
            } else if (sign.equals("×")) {
                grand_total = v1 * v2;
            } else if (sign.equals("÷")) {
                grand_total = v1 / v2;
            }
            calculator.setText(String.valueOf(grand_total));
            operationJustCompleted = true;
        } else
            Toast.makeText(Calculator.this, "Enter a value", Toast.LENGTH_SHORT).show();
    }


    public void onClear(View v) {
        TextView calculator = (TextView) findViewById(R.id.calcScreen);
        grand_total = 0;
        total = "";
        calculator.setText("");
    }


    public void onDelete(View delete) {
        if (!total.isEmpty()) {
            if (!operationJustCompleted) {
                String text = calculator.getText().toString();
                calculator.setText(text.substring(0, text.length() - 1));
                total = text.substring(0, text.length() - 1);
            } else
                Toast.makeText(Calculator.this, "Cannot delete answer", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(Calculator.this, "Enter a value", Toast.LENGTH_SHORT).show();
    }
}
