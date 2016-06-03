package in.org.csi.duketreadmillscore;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText exerciseMinutes,exerciseSeconds,maxST;
    private RadioGroup anginaIndex;

    private TextView DTSResult,risk;

    private List<String> riskCategoryList;

    private Button calculate,reset;

    private TableLayout riskValues;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exerciseLimit();

        riskValuesInitialise(this);

        calculateButton();
        resetButton();
    }

    public void exerciseLimit(){

        exerciseMinutes = (EditText) findViewById(R.id.exerciseMinutes);
        exerciseSeconds = (EditText) findViewById(R.id.exerciseSeconds);
        exerciseMinutes.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});
        exerciseSeconds.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});
    }

    public void calculateButton(){

        calculate = (Button) findViewById(R.id.calculate);

        exerciseMinutes = (EditText) findViewById(R.id.exerciseMinutes);
        exerciseSeconds = (EditText) findViewById(R.id.exerciseSeconds);
        maxST = (EditText) findViewById(R.id.maxST);
        anginaIndex = (RadioGroup) findViewById(R.id.anginaIndex);

        DTSResult = (TextView) findViewById(R.id.DTS);
        risk = (TextView) findViewById(R.id.risk);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int minutes,seconds,angina,intDTS;
                float DTS,st;

                if(exerciseMinutes.getText().length() == 0){
                    minutes = 0;
                    exerciseMinutes.setText("0");
                }
                else {
                    minutes = Integer.parseInt(exerciseMinutes.getText().toString());
                }
                if(exerciseSeconds.getText().length() == 0){
                    seconds = 0;
                    exerciseSeconds.setText("0");
                }
                else {
                    seconds = Integer.parseInt(exerciseSeconds.getText().toString());
                }
                if(seconds >= 30){
                    minutes = minutes + 1;
                }
                if(maxST.getText().length() == 0){
                    st = 0;
                    maxST.setText("0.0");
                }
                else {
                    st = Float.parseFloat(maxST.getText().toString());
                }
                angina = anginaIndex.getCheckedRadioButtonId();
                View anginaVal = (RadioButton) findViewById(angina);
                angina = anginaIndex.indexOfChild(anginaVal);

                DTS = minutes - 5*st - 4*angina;

                intDTS = Math.round(DTS);

                DTSResult.setText(Integer.toString(intDTS));

                if(intDTS >= 5){
                    risk.setText("Low Risk");

                    ((TextView)((TableRow) riskValues.getChildAt(1)).getChildAt(1)).setText("0.9%");
                    ((TextView)((TableRow) riskValues.getChildAt(2)).getChildAt(1)).setText("52.6%");
                    ((TextView)((TableRow) riskValues.getChildAt(3)).getChildAt(1)).setText("22.4%");
                    ((TextView)((TableRow) riskValues.getChildAt(4)).getChildAt(1)).setText("13.6%");
                    ((TextView)((TableRow) riskValues.getChildAt(5)).getChildAt(1)).setText("11.4%");

                    ((TextView)((TableRow) riskValues.getChildAt(1)).getChildAt(2)).setText("0.5%");
                    ((TextView)((TableRow) riskValues.getChildAt(2)).getChildAt(2)).setText("80.9%");
                    ((TextView)((TableRow) riskValues.getChildAt(3)).getChildAt(2)).setText("9.4%");
                    ((TextView)((TableRow) riskValues.getChildAt(4)).getChildAt(2)).setText("6.2%");
                    ((TextView)((TableRow) riskValues.getChildAt(5)).getChildAt(2)).setText("3.5%");
                }
                else if(intDTS >= -10 && intDTS <= 4){
                    risk.setText("Moderate Risk");

                    ((TextView)((TableRow) riskValues.getChildAt(1)).getChildAt(1)).setText("2.9%");
                    ((TextView)((TableRow) riskValues.getChildAt(2)).getChildAt(1)).setText("17.8%");
                    ((TextView)((TableRow) riskValues.getChildAt(3)).getChildAt(1)).setText("15.6%");
                    ((TextView)((TableRow) riskValues.getChildAt(4)).getChildAt(1)).setText("27.9%");
                    ((TextView)((TableRow) riskValues.getChildAt(5)).getChildAt(1)).setText("38.7%");

                    ((TextView)((TableRow) riskValues.getChildAt(1)).getChildAt(2)).setText("1.1%");
                    ((TextView)((TableRow) riskValues.getChildAt(2)).getChildAt(2)).setText("65.1%");
                    ((TextView)((TableRow) riskValues.getChildAt(3)).getChildAt(2)).setText("14.2%");
                    ((TextView)((TableRow) riskValues.getChildAt(4)).getChildAt(2)).setText("8.3%");
                    ((TextView)((TableRow) riskValues.getChildAt(5)).getChildAt(2)).setText("12.4%");
                }
                else{
                    risk.setText("High Risk");

                    ((TextView)((TableRow) riskValues.getChildAt(1)).getChildAt(1)).setText("8.3%");
                    ((TextView)((TableRow) riskValues.getChildAt(2)).getChildAt(1)).setText("1.8%");
                    ((TextView)((TableRow) riskValues.getChildAt(3)).getChildAt(1)).setText("9.1%");
                    ((TextView)((TableRow) riskValues.getChildAt(4)).getChildAt(1)).setText("17.5%");
                    ((TextView)((TableRow) riskValues.getChildAt(5)).getChildAt(1)).setText("71.5%");

                    ((TextView)((TableRow) riskValues.getChildAt(1)).getChildAt(2)).setText("1.8%");
                    ((TextView)((TableRow) riskValues.getChildAt(2)).getChildAt(2)).setText("10.8%");
                    ((TextView)((TableRow) riskValues.getChildAt(3)).getChildAt(2)).setText("18.9%");
                    ((TextView)((TableRow) riskValues.getChildAt(4)).getChildAt(2)).setText("24.3%");
                    ((TextView)((TableRow) riskValues.getChildAt(5)).getChildAt(2)).setText("46%");
                }
            }
        });
    }

    public void resetButton(){

        reset = (Button) findViewById(R.id.reset);

        exerciseMinutes = (EditText) findViewById(R.id.exerciseMinutes);
        exerciseSeconds = (EditText) findViewById(R.id.exerciseSeconds);
        maxST = (EditText) findViewById(R.id.maxST);
        DTSResult = (TextView) findViewById(R.id.DTS);
        risk = (TextView) findViewById(R.id.risk);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exerciseMinutes.setText("0");
                exerciseSeconds.setText("0");
                maxST.setText("0.0");
                DTSResult.setText("");
                risk.setText("");

                for(int i=1;i <= riskCategoryList.size();i++){

                    for(int j=1;j<3;j++){
                        ((TextView)((TableRow) riskValues.getChildAt(i)).getChildAt(j)).setText("");
                    }
                }

                ScrollView mainScroll = (ScrollView) findViewById(R.id.mainScroll);
                mainScroll.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    public void riskValuesInitialise(Context context) {

        riskValues = (TableLayout) findViewById(R.id.riskValues);

        List<String> list = new ArrayList<String>();

        list.addAll(Arrays.asList("1-Yr Mortality", "No Stenosis >=75%", "1VD >=75%" , "2VD >=75%", "3VD >=75% \nor LM >=75%"));

        riskCategoryList = list;

        for (int i = 0; i < list.size(); i++) {

            TableRow row = new TableRow(context);

            TextView risk = new TextView(context);
            TextView men = new TextView(context);
            TextView women = new TextView(context);

            TableLayout.LayoutParams tableRowParams=new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int topMargin = 16;
            int bottomMargin = 16;
            int leftMargin = 8;

            tableRowParams.setMargins(leftMargin, topMargin, 0, bottomMargin);

            row.setLayoutParams(tableRowParams);

            risk.setGravity(Gravity.CENTER);
            risk.setTextSize(16);

            men.setGravity(Gravity.CENTER);
            men.setTextSize(16);

            women.setGravity(Gravity.CENTER);
            women.setTextSize(16);

            risk.setText(list.get(i));
            men.setText("");
            women.setText("");

            row.addView(risk);
            row.addView(men);
            row.addView(women);

            riskValues.addView(row);
        }
    }

}
