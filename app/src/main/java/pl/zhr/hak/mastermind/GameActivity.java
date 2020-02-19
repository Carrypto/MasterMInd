package pl.zhr.hak.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    EditText firstNumber;
    EditText secondNumber;
    EditText thirdNumber;
    EditText fourthNumber;
    Button checkButton;
    HintAdapter hintAdapter;
    List<Hint> hintList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //połączenie elementów
        connectViewElements();
        //zmiana pola po wpisaniu liczby
        nextFocus(firstNumber, secondNumber);
        nextFocus(secondNumber, thirdNumber);
        nextFocus(thirdNumber, fourthNumber);

        hintList.add(new Hint(1,2,3,4,2,1));
        hintList.add(new Hint(1,2,3,4,2,1));

        RecyclerView recyclerView = findViewById(R.id.hintRecyclerView);
        hintAdapter = new HintAdapter(hintList,GameActivity.this);
        recyclerView.setAdapter(hintAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        firstNumber.clearFocus();
    }

    public void nextFocus(EditText checkedText, EditText nextText) {
        checkedText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkedText.getText().toString().length() == 1)     //size as per your requirement
                {
                    nextText.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void previousFocus(EditText checkedText, EditText nextText) {
        checkedText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkedText.getText().toString().length() == 0)     //size as per your requirement
                {
                    nextText.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void connectViewElements() {
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        thirdNumber = findViewById(R.id.thirdNumber);
        fourthNumber = findViewById(R.id.fourthNumber);
        checkButton = findViewById(R.id.checkButton);
    }
}
