package pl.zhr.hak.mastermind;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScoreViewModel extends AndroidViewModel {

    private ExecutorService executorService;
    private ScoreDao scoreDao;

    public ScoreViewModel(@NonNull Application application) {
        super(application);
        executorService = Executors.newSingleThreadExecutor();
        scoreDao = AppDatabase.getInstance(application).scoreDao();
    }

    LiveData<List<Score>> getScoreList() {
        return scoreDao.getAllByOrder();
    }

    public void insert(Score score) {
        executorService.execute(() -> scoreDao.insert(score));
    }

    public void update(Score score) {
        executorService.execute(() -> scoreDao.update(score));
    }

    public void delete(Score score) {
        executorService.execute(() -> scoreDao.delete(score));
    }

    public void deleteAll (Score score){executorService.execute((() -> scoreDao.deleteAll()));}

}
