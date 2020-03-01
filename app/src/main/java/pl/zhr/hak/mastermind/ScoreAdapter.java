package pl.zhr.hak.mastermind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {

        private final TextView scoreOrderNumber;
        private final TextView scoreName;
        private final TextView scorePoints;
        private final TextView scorePt;
        private final TextView scoreAttempts;
        private final TextView scoreAt;
        private final TextView scoreDifficulty;
        private final TextView scoreDi;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.scoreOrderNumber = itemView.findViewById(R.id.scoreOrderNumber);
            this.scoreName = itemView.findViewById(R.id.scoreName);
            this.scorePoints = itemView.findViewById(R.id.scorePoints);
            this.scorePt = itemView.findViewById(R.id.scorePt);
            this.scoreAttempts = itemView.findViewById(R.id.scoreAttempts);
            this.scoreAt = itemView.findViewById(R.id.scoreAt);
            this.scoreDifficulty = itemView.findViewById(R.id.scoreDifficulty);
            this.scoreDi = itemView.findViewById(R.id.scoreDi);
        }
    }

    private List<Score> mScoreList;
    private final LayoutInflater mInflater;
    private Context mContext;

    public ScoreAdapter(List<Score> mScoreList, Context context) {
        this.mScoreList = mScoreList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.score_layout, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {

        int diffAtt;
        if (mScoreList.get(position).getDifficulty().equals(String.valueOf(R.string.easy))) {
            diffAtt = 10;
        } else diffAtt = 8;

        String attempts = ((mScoreList.get(position).getAttempts()) + "/" + diffAtt);

        holder.scoreOrderNumber.setText(String.valueOf(position + 1));
        holder.scoreName.setText(mScoreList.get(position).getName());
        holder.scorePoints.setText(String.valueOf(mScoreList.get(position).getPoints()));
        holder.scorePt.setText(R.string.points);
        holder.scoreAttempts.setText(attempts);
        holder.scoreAt.setText(R.string.attempts);
        holder.scoreDifficulty.setText(mScoreList.get(position).getDifficulty());
        holder.scoreDi.setText(R.string.difficulty);
    }

    @Override
    public int getItemCount() {
        return mScoreList == null ? 0 : mScoreList.size();
    }

    void setScores(List<Score> scores) {
        mScoreList = scores;
        notifyDataSetChanged();
    }

}
