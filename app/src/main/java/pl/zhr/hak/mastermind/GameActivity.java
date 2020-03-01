package pl.zhr.hak.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
    int chances;
    int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        thirdNumber = findViewById(R.id.thirdNumber);
        fourthNumber = findViewById(R.id.fourthNumber);
        checkButton = findViewById(R.id.checkButton);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        difficulty = preferences.getString("difficulty", getString(R.string.easy));
        Toast.makeText(GameActivity.this, getString(R.string.difficulty_level_annotation, difficulty), Toast.LENGTH_SHORT).show();

        //sprawdzanie poprawności wprowadzonych wartości i zmiana skupienia po wpisaniu
        textCheckNext(firstNumber, secondNumber);
        textCheckNext(secondNumber, thirdNumber);
        textCheckNext(thirdNumber, fourthNumber);
        textCheckNext(fourthNumber, fourthNumber);
        textCheckPrevious(firstNumber, secondNumber, thirdNumber, fourthNumber);

        RecyclerView recyclerView = findViewById(R.id.hintRecyclerView);
        hintAdapter = new HintAdapter(hintList, GameActivity.this);
        recyclerView.setAdapter(hintAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int x = 10;
        chances = 8;
        attempts = 0;
        if (difficulty.equals(getString(R.string.easy))) x = 7;
        if (difficulty.equals(getString(R.string.easy))) chances = 10;

        Random random = new Random();
        int[] code = {1, 1, 1, 1};
        code[0] = random.nextInt(x);
        code[1] = random.nextInt(x);
        code[2] = random.nextInt(x);
        code[3] = random.nextInt(x);

        if (!difficulty.equals(getString(R.string.hard))) {
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

        checkButton.setOnClickListener(v -> {
            if (firstNumber.getText().toString().equals("") || secondNumber.getText().toString().equals("") || thirdNumber.getText().toString().equals("") || fourthNumber.getText().toString().equals("")) {
                Toast.makeText(GameActivity.this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
            } else {
                int[] input = {Integer.parseInt(firstNumber.getText().toString()), Integer.parseInt(secondNumber.getText().toString()), Integer.parseInt(thirdNumber.getText().toString()), Integer.parseInt(fourthNumber.getText().toString())};
                hintList.add(new Hint(Integer.parseInt(firstNumber.getText().toString()), Integer.parseInt(secondNumber.getText().toString()), Integer.parseInt(thirdNumber.getText().toString()), Integer.parseInt(fourthNumber.getText().toString()), countBlack(code, input), countWhite(code, input)));

                hintAdapter.setHints(hintList);
                recyclerView.smoothScrollToPosition(hintAdapter.getItemCount() - 1);

                firstNumber.getText().clear();
                secondNumber.getText().clear();
                thirdNumber.getText().clear();
                fourthNumber.getText().clear();
                firstNumber.requestFocus();
                chances--;
                attempts++;

                if (countBlack(code, input) != 4) {
                    Bundle args = new Bundle();
                    args.putInt("attempts", attempts);
                    args.putString("difficulty",difficulty);
                    openWinDialog(args);
                } else {
                    if (chances == 0) {
                        Bundle args = new Bundle();
                        args.putInt("first", code[0]);
                        args.putInt("second", code[1]);
                        args.putInt("third", code[2]);
                        args.putInt("fourth", code[3]);
                        openLoseDialog(args);
                    }
                }
            }
        });
    }

    public void textCheckNext(EditText checkedText, EditText nextText) {
        checkedText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkedText.getText().toString().length() == 1) {
                    if (difficulty.equals(getString(R.string.easy)) && Integer.parseInt(checkedText.getText().toString()) > 6) {
                        Toast.makeText(GameActivity.this, getString(R.string.easy_level_annotation), Toast.LENGTH_LONG).show();
                        checkedText.getText().clear();
                    } else nextText.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void textCheckPrevious(EditText firstNumber, EditText secondNumber, EditText thirdNumber, EditText fourthNumber) {
        fourthNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_DEL && fourthNumber.isFocused()) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        if (fourthNumber.getText().toString().length() == 1) {
                            fourthNumber.setText("");
                            return true;
                        }
                        if (fourthNumber.getText().toString().length() == 0) {
                            thirdNumber.setText("");
                            thirdNumber.requestFocus();
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
                return false;
            }
        });

        thirdNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_DEL && thirdNumber.isFocused()) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        if (thirdNumber.getText().toString().length() == 1) {
                            thirdNumber.setText("");
                            return true;
                        }
                        if (thirdNumber.getText().toString().length() == 0) {
                            secondNumber.setText("");
                            secondNumber.requestFocus();
                            return true;
                        }
                    } else {
                        return true;

                    }
                }
                return false;
            }
        });

        secondNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_DEL && secondNumber.isFocused()) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        if (secondNumber.getText().toString().length() == 1) {
                            secondNumber.setText("");
                            return true;
                        }
                        if (secondNumber.getText().toString().length() == 0) {
                            firstNumber.setText("");
                            firstNumber.requestFocus();
                            return true;
                        }
                    } else {
                        return true;

                    }
                }
                return false;
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
        int[] flags = {0, 0, 0, 0};
        if (input[0] != code[0] && ((input[0] == code[1] && input[1] != code[1]) || (input[0] == code[2] && input[2] != code[2]) || (input[0] == code[3] && input[3] != code[3]))) {
            if (input[0] == code[1] && flags[1] != 1) {
                flags[1] = 1;
                white++;
            } else {
                if (input[0] == code[2] && flags[2] != 1) {
                    flags[2] = 1;
                    white++;
                } else {
                    if (input[0] == code[3] && flags[3] != 1) {
                        flags[3] = 1;
                        white++;
                    }
                }
            }
        }
        if (input[1] != code[1] && ((input[1] == code[2] && input[2] != code[2]) || (input[1] == code[3] && input[3] != code[3]) || (input[1] == code[0] && input[0] != code[0]))) {
            if (input[1] == code[2] && flags[2] != 1) {
                flags[2] = 1;
                white++;
            } else {
                if (input[1] == code[3] && flags[3] != 1) {
                    flags[3] = 1;
                    white++;
                } else {
                    if (input[1] == code[0] && flags[0] != 1) {
                        flags[0] = 1;
                        white++;
                    }
                }
            }
        }
        if (input[2] != code[2] && ((input[2] == code[3] && input[3] != code[3]) || (input[2] == code[0] && input[0] != code[0]) || (input[2] == code[1] && input[1] != code[1]))) {
            if (input[2] == code[3] && flags[3] != 1) {
                flags[3] = 1;
                white++;
            } else {
                if (input[2] == code[0] && flags[0] != 1) {
                    flags[0] = 1;
                    white++;
                } else {
                    if (input[2] == code[1] && flags[1] != 1) {
                        flags[1] = 1;
                        white++;
                    }
                }
            }
        }
        if (input[3] != code[3] && ((input[3] == code[0] && input[0] != code[0]) || (input[3] == code[1] && input[1] != code[1]) || (input[3] == code[2] && input[2] != code[2]))) {
            if (input[3] == code[0] && flags[0] != 1) {
                flags[0] = 1;
                white++;
            } else {
                if (input[3] == code[1] && flags[1] != 1) {
                    flags[1] = 1;
                    white++;
                } else {
                    if (input[3] == code[2] && flags[2] != 1) {
                        flags[2] = 1;
                        white++;
                    }
                }
            }
        }
        return white;
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
