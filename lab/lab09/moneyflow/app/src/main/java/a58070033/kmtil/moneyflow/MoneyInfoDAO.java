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

    @Query("SELECT * FROM MONEYFLOW_INFO")
    List<MoneyInfo> findMoney();

    @Query("SELECT Money FROM MONEYFLOW_INFO ORDER BY id DESC limit 1")
    Long findOnlyMoney();

    @Query("SELECT COUNT(*) FROM MONEYFLOW_INFO")
    int checkEmpty();

    @Insert
    void insert(MoneyInfo moneyInfo);

    @Delete
    void delete(MoneyInfo moneyInfo);
}
