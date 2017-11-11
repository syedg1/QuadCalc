package com.gsproductions.helloworldapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class quadraticEqnSolver extends AppCompatActivity {

    public EditText num1;
    public EditText num2;
    public EditText num3;
    public TextView ans1;
    public TextView ans2;
    boolean quadraticSolved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quadratic_solver);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }

    }
    boolean CheckFields() {
        num1 = (EditText) findViewById(R.id.editText);
        num2 = (EditText) findViewById(R.id.editText2);
        num3 = (EditText) findViewById(R.id.editText3);
        try {
            Double.parseDouble(num1.getText().toString());
            Double.parseDouble(num2.getText().toString());
            Double.parseDouble(num3.getText().toString());
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public void calcButton(View v) {
        if (CheckFields()) {
            num1 = (EditText) findViewById(R.id.editText);
            num2 = (EditText) findViewById(R.id.editText2);
            num3 = (EditText) findViewById(R.id.editText3);
            ans1 = (TextView) findViewById(R.id.textView2);
            ans2 = (TextView) findViewById(R.id.textView3);

            double a = Double.valueOf(num1.getText().toString());
            double b = Double.valueOf(num2.getText().toString());
            double c = Double.valueOf(num3.getText().toString());

            double root = Math.sqrt(b * b - 4 * a * c);
            double x_1 = (-b + root) / (2 * a);
            double x_2 = (-b - root) / (2 * a);

            ans1.setText(String.format("x = %.4f", x_1));
            ans2.setText(String.format("x = %.4f", x_2));
            quadraticSolved = true;
        } else
            Toast.makeText(quadraticEqnSolver.this, "Fill each field with a numerical value", Toast.LENGTH_SHORT).show();
    }

    public void clearButton(View v) {
        num1 = (EditText) findViewById(R.id.editText);
        num2 = (EditText) findViewById(R.id.editText2);
        num3 = (EditText) findViewById(R.id.editText3);
        if (quadraticSolved) {
            num1.setText("");
            num2.setText("");
            num3.setText("");
            ans1.setText("");
            ans2.setText("");
            quadraticSolved = false;
        } else if (!num1.getText().toString().isEmpty() || !num2.getText().toString().isEmpty()
                || !num3.getText().toString().isEmpty()) {
            num1.setText("");
            num2.setText("");
            num3.setText("");
        } else
            Toast.makeText(quadraticEqnSolver.this, "No values entered", Toast.LENGTH_SHORT).show();
    }

}
