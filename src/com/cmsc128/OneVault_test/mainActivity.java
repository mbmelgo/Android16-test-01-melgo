package com.cmsc128.OneVault_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;

public class mainActivity extends Activity {
    DatabaseHandler db;
    ArrayList<Transaction> expense_transaction;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        db = new DatabaseHandler(this);
        ListView view = (ListView) findViewById(R.id.listView_transac_expense);
        //ListView view2 = (ListView) findViewById(R.id.listView_transac_income);
        createListenerExpense(view);
        //createListenerIncome(view2);
        ArrayList<Transaction> expense = null;
        //ArrayList<Transaction> income = null;

        //IF INCOME SELECTED

//        ListView view = (ListView) findViewById(R.id.listView_transac);
//        createListener(view);
//        ArrayList<Transaction> e = db.getAllIncomeTransactions();
//        ArrayList<String> transaction = new ArrayList<>();

        try {
            expense = db.getAllExpenseTransactions();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        expense_transaction = new ArrayList<>();
        ArrayList<String> exp_trans = new ArrayList<>();

        if(!expense.isEmpty()) {
            for (Transaction t : expense) {
                expense_transaction.add(t);
                exp_trans.add(t.getKEY_DATE() + "\nAmount:" + t.getKEY_AMOUNT() +
                        "\nDescription: " + t.getKEY_DESCRIPTION());;
            }
        }
        else{
            exp_trans.add("No Transactions");

        }
        ArrayAdapter<String> expenseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, exp_trans);
        view.setAdapter(expenseArrayAdapter);

//        income = db.getAllIncomeTransactions();
//
//        ArrayList<String> income_transaction = new ArrayList<>();
//
//        if(!income.isEmpty()) {
//            for (Transaction t : income) {
//                income_transaction.add(t.getKEY_DATE() + "\nAmount:" + t.getKEY_AMOUNT() +
//                        "\nDescription: " + t.getKEY_DESCRIPTION());
//            }
//        }
//        else{
//            income_transaction.add("No Income transaction at the moment");
//        }
//
//        ArrayAdapter<String> incomeArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, expense_transaction);
//        view2.setAdapter(incomeArrayAdapter);

    }

//    private void createListenerIncome(ListView Listview) {
//        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = (String) (Listview.getItemAtPosition(position));
//                selectedItem = selectedItem.replaceAll("\\s", "");
//                String whereClause = selectedItem.substring(0, selectedItem.indexOf('A'));
//                //change this to context later since it has only one data to pass..
//                Bundle bundle = new Bundle();
//                bundle.putString("whereClause", whereClause);
//
//                Intent intent = new Intent(mainActivity.this, UDIncome.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
//    }

    private void createListenerExpense(ListView Listview) {
        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //change this to context later since it has only one data to pass..
                Bundle bundle = new Bundle();

                Transaction t = null;
                t = expense_transaction.get(position);
                bundle.putInt("expenseID",t.getKEY_ID());
                Intent intent = new Intent(mainActivity.this, UDExpense.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void AddExpenses(View view){
        //intent will trigger an activity
        //An Intent is an object that provides runtime binding between separate components
        // (such as two activities).
        // The Intent represents an app�s "intent to do something."
        // You can use intents for a wide variety of tasks, but most often they�re used to start another activity.
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }

//    public void AddIncome(View view){
//        Intent intent = new Intent(this, AddIncome.class);
//        startActivity(intent);
//    }
}
