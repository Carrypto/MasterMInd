package pl.zhr.hak.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    EditText firstNumber;
    EditText secondNumber;
    EditText thirdNumber;
    EditText fourthNumber;
    Button checkButton;
    HintAdapter hintAdapter;
    List<Hint> hintList = new ArrayList<>();
    String difficulty;
    int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //połączenie elementów
        connectViewElements();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        difficulty = preferences.getString("difficulty", "medium");
        Toast.makeText(GameActivity.this,getString(R.string.difficulty_level_annotation,difficulty), Toast.LENGTH_SHORT).show();

        //sprawdzanie poprawności wprowadzonych wartości i zmiana skupienia po wpisaniu
        textCheck(firstNumber, secondNumber);
        textCheck(secondNumber, thirdNumber);
        textCheck(thirdNumber, fourthNumber);
        textCheck(fourthNumber, fourthNumber);

        //działanie gry
        game();

        RecyclerView recyclerView = findViewById(R.id.hintRecyclerView);
        hintAdapter = new HintAdapter(hintList, GameActivity.this);
        recyclerView.setAdapter(hintAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void game() {
        attempts = 8;
        if (difficulty.equals("easy")) attempts = 10;
        Random random = new Random();
        int[] code = {1, 1, 1, 1};
        code[0] = random.nextInt(10);
        code[1] = random.nextInt(10);
        code[2] = random.nextInt(10);
        code[3] = random.nextInt(10);

        if (!difficulty.equals("hard")) {
            int x = 7;
            if (difficulty.equals("medium")) x = 10;
            while (code[1] == code[0]) {
                code[1] = random.nextInt(x);
            }
            while (code[2] == code[0] || code[2] == code[1]) {
                code[2] = random.nextInt(x);
            }
            while (code[3] == code[0] || code[3] == code[1] || code[3] == code[2]) {
                code[3] = random.nextInt(x);
            }
        }

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNumber.getText().toString().equals("") || secondNumber.getText().toString().equals("") || thirdNumber.getText().toString().equals("") || fourthNumber.getText().toString().equals("")) {
                    Toast.makeText(GameActivity.this,getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                } else {
                    int[] input = {Integer.parseInt(firstNumber.getText().toString()), Integer.parseInt(secondNumber.getText().toString()), Integer.parseInt(thirdNumber.getText().toString()), Integer.parseInt(fourthNumber.getText().toString())};
                    hintList.add(new Hint(Integer.parseInt(firstNumber.getText().toString()), Integer.parseInt(secondNumber.getText().toString()), Integer.parseInt(thirdNumber.getText().toString()), Integer.parseInt(fourthNumber.getText().toString()), countBlack(code, input), countWhite(code, input)));
                    hintAdapter.setHints(hintList);
                    firstNumber.getText().clear();
                    secondNumber.getText().clear();
                    thirdNumber.getText().clear();
                    fourthNumber.getText().clear();
                    firstNumber.requestFocus();
                    attempts--;
                    if (countBlack(code, input) == 4) {
                        Bundle args = new Bundle();
                        openWinDialog(args);
                    } else {
                        if (attempts == 0) {
                            Bundle args = new Bundle();
                            openLoseDialog(args);
                        }
                    }
                }
            }
        });
    }

    public void textCheck(EditText checkedText, EditText nextText) {
        checkedText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkedText.getText().toString().length() == 1) {
                    if (difficulty.equals("easy") && Integer.parseInt(checkedText.getText().toString()) > 6) {
                        Toast.makeText(GameActivity.this,getString(R.string.easy_level_annotation), Toast.LENGTH_LONG).show();
                        checkedText.getText().clear();
                    } else nextText.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public int countBlack(int[] code, int[] input) {
        int black = 0;
        for (int i = 0; i < 4; i++) {
            if (code[i] == input[i]) black++;
        }
        return black;
    }

    public int countWhite(int[] code, int[] input) {
        int white = 0;
        if (code[0] != input[0] && (code[0] == input[1] || code[0] == input[2] || code[0] == input[3]))
            white++;
        if (code[1] != input[1] && (code[1] == input[0] || code[1] == input[2] || code[1] == input[3]))
            white++;
        if (code[2] != input[2] && (code[2] == input[1] || code[2] == input[0] || code[2] == input[3]))
            white++;
        if (code[3] != input[3] && (code[3] == input[1] || code[3] == input[2] || code[3] == input[0]))
            white++;
        return white;
    }

    //todo
    public int countWhiteRepeatable(int[] code, int[] input) {
        int white = 0;
        if (code[0] == code[1]) {
            if (code[0] != input[0] && (code[0] == input[1] || code[0] == input[2] || code[0] == input[3]))
                white++;
        }
        if (code[0] != input[0] && (code[0] == input[1] || code[0] == input[2] || code[0] == input[3]))
            white++;
        if (code[1] != input[1] && (code[1] == input[0] || code[1] == input[2] || code[1] == input[3]))
            white++;
        if (code[2] != input[2] && (code[2] == input[1] || code[2] == input[0] || code[2] == input[3]))
            white++;
        if (code[3] != input[3] && (code[3] == input[1] || code[3] == input[2] || code[3] == input[0]))
            white++;
        return white;
    }

    public void connectViewElements() {
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        thirdNumber = findViewById(R.id.thirdNumber);
        fourthNumber = findViewById(R.id.fourthNumber);
        checkButton = findViewById(R.id.checkButton);
    }

    public void openWinDialog(Bundle args) {
        WinDialog winDialog = new WinDialog();
        winDialog.setArguments(args);
        winDialog.show(getSupportFragmentManager(), "winDialog");
    }

    public void openLoseDialog(Bundle args) {
        LoseDialog loseDialog = new LoseDialog();
        loseDialog.setArguments(args);
        loseDialog.show(getSupportFragmentManager(), "loseDialog");
    }
}
