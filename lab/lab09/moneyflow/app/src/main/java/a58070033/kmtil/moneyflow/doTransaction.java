package a58070033.kmtil.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

public class doTransaction extends AppCompatActivity {

    private MoneyFlowDB moneyFlowDB;
    Button submitBtn;
    EditText item, cost;
    RadioButton income,outcome;
    private String mode = "income";
    private int count;
    private long amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createbudget);

        submitBtn = findViewById(R.id.submitBtn);
        item = findViewById(R.id.item);
        cost = findViewById(R.id.amount);
        income = findViewById(R.id.income);
        outcome = findViewById(R.id.outcome);

        moneyFlowDB = Room.databaseBuilder(this, MoneyFlowDB.class, "MONEYFLOW").build();

        ShowMoneyTask showMoneyTask = new ShowMoneyTask();




    }

    public void setSubmitBtn(View view) {
        addTransTask addtrans = new addTransTask();
        addtrans.execute();
        onBackPressed();
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    private class addTransTask extends AsyncTask<Void, Void, List<MoneyInfo>>{

        @Override
        protected List<MoneyInfo> doInBackground(Void... voids) {
            MoneyInfo moneyInfo = new MoneyInfo();
            moneyInfo.setItemname(item.getText() + "");
            moneyInfo.setCost(Long.parseLong(cost.getText() + ""));
            moneyInfo.setType(getMode());

            ShowMoneyTask showmoney = new ShowMoneyTask();
            CheckEmptyTask checkempty = new CheckEmptyTask();
            checkempty.execute();

            if(getCount() == 0){
                moneyInfo.setMoney(Long.parseLong(cost.getText() + ""));

            }
            else{
                if(getMode().equals("income")){
                    moneyInfo.setType("+");
                    moneyInfo.setMoney(Long.parseLong(cost.getText() + "") + getAmount());
                }
                else{
                    moneyInfo.setType("-");
                    moneyInfo.setMoney(Long.parseLong(cost.getText() + "") - getAmount());
                }
            }


            moneyFlowDB.getMoneyInfoDAO().insert(moneyInfo);


            return null;
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.income:
                if (checked)
                    outcome.setTextColor(Color.parseColor("#FF3F51B4"));
                    income.setTextColor(Color.parseColor("#FFF9F9F9"));
                    setMode("income");
                    break;
            case R.id.outcome:
                if (checked)
                    income.setTextColor(Color.parseColor("#FF3F51B4"));
                    outcome.setTextColor(Color.parseColor("#FFF9F9F9"));
                    setMode("outcome");
                    break;
        }
    }



    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkType() {

    }
}
