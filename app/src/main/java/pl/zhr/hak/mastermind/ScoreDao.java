package pl.zhr.hak.mastermind;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoreDao {

    @Query("SELECT * FROM SCORES")
    LiveData<List<Score>> getAll();

    @Insert
    void insert(Score score);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Score> scores);

    @Delete
    void delete(Score score);

    @Update
    void update(Score score);

    @Query("DELETE FROM Scores")
    void deleteAll();
}
