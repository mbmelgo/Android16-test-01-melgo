package com.cmsc128.OneVault_test;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by Radley Rosal on 26/10/2015.
 */
public class UDExpense extends Activity {
    EditText amount;
    EditText refChck;
    EditText description;
    EditText tax;
    EditText quantity;
    EditText payee;
    EditText tags;
    DatePicker datePicker;
    Calendar calendar;
    int year, month, day;
    Boolean passed = false;
    DatabaseHandler db;
    Transaction transaction;

    String payment_method[] =
            {"Cash" , "Check" , "Debit", "Credit Card", "Electronic Transfer"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udexpenselayout);

        Spinner pay_methods = (Spinner) findViewById(R.id.spin_paymethod);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, payment_method);
        pay_methods.setAdapter(arrayAdapter);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        db = new DatabaseHandler(this);
        int id = bundle.getInt("expenseID");
        initializeFields();
        fillFromDB(id);
    }
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Set Date", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            year = arg1;
            month = arg2 +1;
            day = arg3;
        }
    };

    private void initializeFields() {
        amount = (EditText) findViewById(R.id.field_amount);
        refChck = (EditText) findViewById(R.id.field_refOrChck);
        description = (EditText) findViewById(R.id.field_description);
        tax = (EditText) findViewById(R.id.field_tax);
        quantity = (EditText) findViewById(R.id.field_quantity);
        payee = (EditText) findViewById(R.id.field_payee);
        tags = (EditText) findViewById(R.id.field_tags);
    }
    private void fillFromDB(int id) {
        try {
            transaction = db.getExpenseTransaction(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        amount.setText("" + transaction.getKEY_AMOUNT());
        refChck.setText("" + transaction.getKEY_REF_CHECK());
        tax.setText("" + transaction.getKEY_TAX());
        quantity.setText("" + transaction.getKEY_QUANTITY());
        payee.setText("" + transaction.getKEY_PAYER());
        tags.setText("" + transaction.getKEY_TAGS());
        description.setText(""+ transaction.getKEY_DESCRIPTION());

    }
    public void ReturnMainUpdate(View view){

        DatabaseHandler db = new DatabaseHandler(this);
        EditText editText = (EditText) findViewById(R.id.field_amount);
        Spinner s = (Spinner) findViewById(R.id.spin_paymethod);

        double amount = Double.parseDouble(editText.getText().toString());
        String method = s.getSelectedItem().toString();

        editText = (EditText) findViewById(R.id.field_refOrChck);
        int refOrCheck = Integer.parseInt(editText.getText().toString());

        editText = (EditText) findViewById(R.id.field_description);
        String description = editText.getText().toString();

        editText = (EditText) findViewById(R.id.field_tax);
        double tax = Double.parseDouble(editText.getText().toString());

        editText = (EditText) findViewById(R.id.field_quantity);
        int quantity = Integer.parseInt(editText.getText().toString());

        editText = (EditText) findViewById(R.id.field_payee);
        String payee = editText.getText().toString();

        editText = (EditText) findViewById(R.id.field_tags);
        String tags = editText.getText().toString();


        Date date = new Date((year-1900),month,day);
        transaction.setKEY_AMOUNT(amount);
        transaction.setKEY_PAYMETHOD(method);
        transaction.setKEY_DATE(new java.sql.Date(date.getTime()));
        transaction.setKEY_REF_CHECK(refOrCheck);
        transaction.setKEY_DESCRIPTION(description);
        transaction.setKEY_TAX(tax);
        transaction.setKEY_QUANTITY(quantity);
        transaction.setKEY_PAYER(payee);
        transaction.setKEY_TAGS(tags);

        db.updateExpenseTransaction(transaction);
        Toast.makeText(getApplicationContext(), "Transaction Updated!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, mainActivity.class);
        startActivity(intent);
    }

    public void ReturnMainDelete(View view){
        db.deleteExpenseTransaction(transaction.getKEY_ID());
        Toast.makeText(getApplicationContext(), "Transaction Deleted!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, mainActivity.class);
        startActivity(intent);

    }
}