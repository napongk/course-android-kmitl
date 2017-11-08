package a58070033.kmtil.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MoneyFlowDB moneyFlowDB;
    Button addBudget;
    TextView money;
    private int count;
    private long amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmoneyflow);

        money = findViewById(R.id.mainmoney);
        addBudget = findViewById(R.id.addButton);
        moneyFlowDB = Room.databaseBuilder(this, MoneyFlowDB.class, "MONEYFLOW").build();

        ShowDBTask showdb = new ShowDBTask();
        showdb.execute();

        ShowMoneyTask showMoneyTask = new ShowMoneyTask();
        showMoneyTask.execute();

        CheckEmptyTask checkempty = new CheckEmptyTask();
        checkempty.execute();
        if(getCount() == 0){
            Toast.makeText(getApplicationContext(), String.valueOf(getCount()), Toast.LENGTH_SHORT).show();
            money.setText("0");
        }
        else{

            Toast.makeText(getApplicationContext(), String.valueOf(getAmount()), Toast.LENGTH_SHORT).show();
            money.setText(String.valueOf(getAmount()));
        }

    }


    public void budgetadd(View view) {
        Intent intent = new Intent(this, doTransaction.class);
        startActivity(intent);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    private class ShowDBTask extends AsyncTask<Void, Void, List<MoneyInfo>>{

        @Override
        protected List<MoneyInfo> doInBackground(Void... voids) {
            List<MoneyInfo> result = moneyFlowDB.getMoneyInfoDAO().findMoney();
            result.toString();
            return result;
        }

        @Override
        protected void onPostExecute(List<MoneyInfo> moneyInfos) {
            ArrayAdapter<MoneyInfo> adapter = new ArrayAdapter<MoneyInfo>(MainActivity.this, android.R.layout.simple_list_item_1, moneyInfos);

            ListView moneyInfoList = findViewById(R.id.budgetList);
            moneyInfoList.setAdapter(adapter);
            super.onPostExecute(moneyInfos);
        }
    }

    private class ShowMoneyTask extends AsyncTask<Void, Void, Long>{

        @Override
        protected Long doInBackground(Void... voids) {
            Long result2 = moneyFlowDB.getMoneyInfoDAO().findOnlyMoney();

            return result2;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            setAmount(aLong);
            Toast.makeText(getApplicationContext(), aLong.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private class CheckEmptyTask extends AsyncTask<Void, Void, Integer>{

        @Override
        protected Integer doInBackground(Void... voids) {
            Integer count = moneyFlowDB.getMoneyInfoDAO().checkEmpty();
            return count;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            setCount(integer);
        }
    }
}
