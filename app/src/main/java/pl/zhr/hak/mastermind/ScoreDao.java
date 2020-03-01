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

    @Query("SELECT * FROM SCORES ORDER BY points DESC")
    LiveData<List<Score>> getAllByOrder();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Score score);

    @Delete
    void delete(Score score);

    @Update
    void update(Score score);

    @Query("DELETE FROM Scores")
    void deleteAll();
}
