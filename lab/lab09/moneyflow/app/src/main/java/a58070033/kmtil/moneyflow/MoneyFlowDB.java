package a58070033.kmtil.moneyflow;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by napkkk on 8/11/2560.
 */

@Database(entities = {MoneyInfo.class}, version = 1)
public abstract class MoneyFlowDB extends RoomDatabase{
    public abstract MoneyInfoDAO getMoneyInfoDAO();
}
