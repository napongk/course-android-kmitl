package a58070033.kmtil.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by napkkk on 8/11/2560.
 */
@Dao
interface MoneyInfoDAO {
    @Query("SELECT * FROM MONEYFLOW_INFO")
    List<MoneyInfo> findTransaction();

    @Query("SELECT COUNT(*) FROM MONEYFLOW_INFO")
    int checkEmpty();

    @Query("SELECT SUM(Cost) FROM MONEYFLOW_INFO WHERE Type = 'income'")
    long checkIncome();

    @Query("SELECT SUM(Cost) FROM MONEYFLOW_INFO WHERE Type = 'outcome'")
    long checkOutcome();

    @Query("DELETE FROM MONEYFLOW_INFO WHERE id = :id")
    int deleteOn(int id);

    @Insert
    void insert(MoneyInfo moneyInfo);

    @Delete
    void deleteItem(MoneyInfo moneyInfo);
}
