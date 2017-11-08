package a58070033.kmtil.moneyflow;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by napkkk on 8/11/2560.
 */

@Entity(tableName = "MONEYFLOW_INFO")
public class MoneyInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Money")
    private long money;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Item")
    private String itemname;

    @ColumnInfo(name = "Cost")
    private long cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return String.format("%s        %s        %s", type, itemname, cost);
    }
}
