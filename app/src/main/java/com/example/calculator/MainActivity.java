package com.example.calculator;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import com.example.calculator.databinding.CalculatorLayoutBinding;
import android.widget.Button;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private CalculatorLayoutBinding calc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calc = CalculatorLayoutBinding.inflate(getLayoutInflater());
        setContentView(calc.getRoot());
        License.iConfirmNonCommercialUse("muk");


        clickListener(calc.bZero,"0");
        clickListener(calc.bOne,"1");
        clickListener(calc.bTwo,"2");
        clickListener(calc.bThree,"3");
        clickListener(calc.bFour,"4");
        clickListener(calc.bFive,"5");
        clickListener(calc.bSix,"6");
        clickListener(calc.bSeven,"7");
        clickListener(calc.bEight,"8");
        clickListener(calc.bNine,"9");

        clickListener(calc.bDivide,"/");
        clickListener(calc.bPlus,"+");
        clickListener(calc.bMinus,"-");
        clickListener(calc.bTimes,"*");
        clickListener(calc.bDot,".");

        calc.bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.textOutput.setText("");
            }
        });

        calc.bEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Expression expression = new Expression(calc.textOutput.getText().toString());
                    double result = expression.calculate();
                    calc.textOutput.setText(new DecimalFormat("0.####").format(result).toString());
                } catch (Exception e){
                    Toast.makeText(getBaseContext(),"Error in your Expression",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void clickListener(Button b, String s){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = calc.textOutput.getText().toString();
                t+=s;
                calc.textOutput.setText(t);
            }
        });
    }
}