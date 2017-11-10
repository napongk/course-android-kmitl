package a58070033.kmtil.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    ListView budgetList;
    Long incometotal, outcometotal, allmoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmoneyflow);

        budgetList = findViewById(R.id.budgetList);
        money = findViewById(R.id.mainmoney);
        addBudget = findViewById(R.id.addButton);
        moneyFlowDB = Room.databaseBuilder(this, MoneyFlowDB.class, "MONEYFLOW").build();

        final ShowDBTask showdb = new ShowDBTask();
        showdb.execute();

        ShowMoneyTask showmon = new ShowMoneyTask();
        showmon.execute();




        budgetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                moneyFlowDB.
//                moneyFlowDB.getMoneyInfoDAO().deleteOn(i+1);
//                DeleteTask del = new DeleteTask(i);
//                del.execute();
//                Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void budgetadd(View view) {
        Intent intent = new Intent(this, doTransaction.class);
        startActivity(intent);
    }

    private class ShowDBTask extends AsyncTask<Void, Void, List<MoneyInfo>>{

        @Override
        protected List<MoneyInfo> doInBackground(Void... voids) {
            List<MoneyInfo> result = moneyFlowDB.getMoneyInfoDAO().findTransaction();
            result.toString();
            return result;
        }

        @Override
        protected void onPostExecute(List<MoneyInfo> moneyInfos) {
            ArrayAdapter<MoneyInfo> adapter = new ArrayAdapter<MoneyInfo>(MainActivity.this, android.R.layout.simple_list_item_1, moneyInfos);

            budgetList.setAdapter(adapter);
            super.onPostExecute(moneyInfos);
        }
    }

    private class ShowMoneyTask extends AsyncTask<Void, Void, Long>{

        @Override
        protected Long doInBackground(Void... voids) {
            incometotal = moneyFlowDB.getMoneyInfoDAO().checkIncome();
            outcometotal = moneyFlowDB.getMoneyInfoDAO().checkOutcome();
            allmoney = incometotal - outcometotal;
            return allmoney;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            Toast.makeText(MainActivity.this, "allmoney = " + String.valueOf(allmoney) + " income = " + String.valueOf(incometotal), Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, String.valueOf((double) allmoney / incometotal), Toast.LENGTH_SHORT).show();
            money.setText(String.valueOf(aLong));
            if ((double)allmoney / incometotal > 0.5){

                money.setTextColor(Color.parseColor("#68ab4d"));
            }
            else if (((double) allmoney / incometotal <= 0.5) && ((double) allmoney / incometotal >= 0.25)){
                money.setTextColor(Color.parseColor("#f0c330"));
            }
            else{
                money.setTextColor(Color.parseColor("#cc0000"));
            }
        }
    }

    private class DeleteTask extends AsyncTask<Void, Void, Integer>{
        int position;

        public DeleteTask(int i){
            Toast.makeText(MainActivity.this, String.valueOf(i+1), Toast.LENGTH_SHORT).show();
            position = i+1;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            moneyFlowDB.getMoneyInfoDAO().deleteOn(position);
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            super.onPostExecute(integer);
        }
    }
}
