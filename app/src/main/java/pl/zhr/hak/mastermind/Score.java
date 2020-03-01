package pl.zhr.hak.mastermind;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Scores")
public class Score {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private int attempts;

    @ColumnInfo
    private int points;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String difficulty;

    public Score(int attempts, int points, String name, String difficulty) {
        this.attempts = attempts;
        this.points = points;
        this.name = name;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}


