package pl.zhr.hak.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noData;
    List<Score> scoreList = new ArrayList<>();
    ScoreAdapter scoreAdapter;
    ScoreViewModel scoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        recyclerView = findViewById(R.id.scoreRecyclerView);
        noData = findViewById(R.id.noData);

        scoreAdapter = new ScoreAdapter(scoreList, ScoreActivity.this);
        recyclerView.setAdapter(scoreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        scoreViewModel.getScoreList().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(List<Score> scores) {
                scoreList = scores;
                scoreAdapter.setScores(scoreList);

                if (scoreAdapter.getItemCount() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    noData.setVisibility(View.GONE);
                }
            }
        });
    }
}
