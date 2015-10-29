package com.cmsc128.OneVault_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by radleyrosal on 10/22/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //INITIALIZE DATABASE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "transactions";

    // column ids for each attributes
    private final int INDEX_INCOME_ID = 0;
    private final int INDEX_INCOME_AMOUNT = 1;
    private final int INDEX_INCOME_DATE = 2;
    private final int INDEX_INCOME_REF_CHECK = 3;
    private final int INDEX_INCOME_DESCRIPTION = 4;
    private final int INDEX_INCOME_TAX = 5;
    private final int INDEX_INCOME_QUANTITY = 6;
    private final int INDEX_INCOME_PAYER = 7;


    private final int INDEX_ID = 0;
    private final int INDEX_AMOUNT = 1;
    private final int INDEX_PAYMENT_METHOD = 2;
    private final int INDEX_DATE = 3;
    private final int INDEX_REF_CHECK = 4;
    private final int INDEX_DESCRIPTION = 5;
    private final int INDEX_TAX = 6;
    private final int INDEX_QUANTITY = 7;
    private final int INDEX_PAYER = 8;
    private final int INDEX_TAGS = 9;

    //DECLARE INCOME TABLE
    private static final String TABLE_INCOME = "income";
    //attributes
    private static final String KEY_ID_INCOME = "transac_id"; //INTEGER
    private static final String KEY_AMOUNT_INCOME = "amount"; // DOUBLE
    private static final String KEY_DATE_INCOME = "date"; // DATETIME
    private static final String KEY_REF_CHECK_INCOME = "ref_check"; // INTEGER
    private static final String KEY_DESCRIPTION_INCOME = "description"; // VARCHAR(200)
    private static final String KEY_TAX_INCOME = "tax"; //DOUBLE
    private static final String KEY_QUANTITY_INCOME = "quantity"; //INTEGER
    private static final String KEY_PAYER_INCOME = "payee"; //VARCHAR(30)

    //DECLARE EXPENSE TABLE
    private static final String TABLE_EXPENSE = "expense";
    //attributes
    private static final String KEY_ID_EXP = "transac_id"; //INTEGER
    private static final String KEY_AMOUNT_EXP = "amount"; // DOUBLE
    private static final String KEY_PAYMENT_METHOD_EXP = "payment_method"; // DOUBLE
    private static final String KEY_DATE_EXP = "date"; //DATETIME
    private static final String KEY_REF_CHECK_EXP = "ref_check"; // INTEGER
    private static final String KEY_DESCRIPTION_EXP = "description"; //VARCHAR(300)
    private static final String KEY_TAX_EXP = "tax";  //DOUBLE
    private static final String KEY_QUANTITY_EXP = "quantity"; //INTEGER
    private static final String KEY_PAYEE = "payee"; //VARCHAR(30)
    private static final String KEY_TAGS= "tags"; //VARCHAR(30)

    //DECLARE CUSTOM TABLE
    private static final String TABLE_CUSTOM = "custom_values";
    //attributes
    private static final String FKEY_ID_CUSTOM = "transac_id";
    private static final String KEY_UNIT ="unit";
    private static final String KEY_PAYMENTMETHOD = "payment_method";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_IS_INCOME_CUSTOM = "is_income";
    private static final String KEY_STATUS = "status";

    //DECLARE RECURRENCES
    private static final String TABLE_RECURRENCE = "recurrence";
    //attributes
    private static final String FKEY_ID_RECURRENCE = "transac_id";
    private static final String KEY_NO_PAYMENTS = "no_of_payment";
    private static final String KEY_FREQUENCY = "frequency";
    private static final String KEY_FIRST_PAYMENT = "first_payment";
    private static final String KEY_IS_INCOME_RECURRENCE = "is_income";

    //DECLARE ACCOUNT
    private static final String TABLE_ACCOUNT = "account";
    //attributes
    private static final String KEY_ID_ACCOUNT = "account_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION_ACCOUNT = "description";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_INIT_BALANCE = "init_balance";
    private static final String KEY_HAS_CARD = "has_card";
    private static final String KEY_LIMIT = "card_limit";

    //DECLARE LOG
    private static final String TABLE_LOG = "log";
    //attributes
    private static final String KEY_ID_LOG = "log_id";
    private static final String FKEY_ACCOUNT_ID = "account_id";
    private static final String FKEY_TRANSAC_ID = "transac_id";



    // CREATE INCOME TABLE
    private static final String CREATE_INCOME_TABLE =
            "CREATE TABLE " + TABLE_INCOME + " (" + KEY_ID_INCOME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_AMOUNT_INCOME + " DOUBLE, " + KEY_DATE_INCOME + " DATETIME, " + KEY_REF_CHECK_INCOME + " INTEGER, " +
                    KEY_DESCRIPTION_INCOME + " VARCHAR(200), " + KEY_TAX_INCOME + " DOUBLE, " + KEY_QUANTITY_INCOME + " INTEGER, " +
                    KEY_PAYER_INCOME + " VARCHAR(30))";

    // CREATE EXPENSE TABLE
    public static final String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_EXPENSE +
            "(" + KEY_ID_EXP + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_AMOUNT_EXP + " DOUBLE,"+KEY_PAYMENT_METHOD_EXP +
            " STRING,"+ KEY_DATE_EXP + " DATETIME, "+KEY_REF_CHECK_EXP+" INTEGER,"+KEY_DESCRIPTION_EXP+" VARCHAR(300), "
            +KEY_TAX_EXP+" DOUBLE, "+KEY_QUANTITY_EXP+" INTEGER, "+KEY_PAYEE+" VARCHAR(30), "+KEY_TAGS+" VARCHAR(30) )";

    // CREATE RECURRENCE TABLE

    // CREATE CUSTOM TABLE


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EXPENSE_TABLE);
        db.execSQL(CREATE_INCOME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        onCreate(db);
    }


    /*
        INCOME QUERIES HERE
        CRUD Operations
        If there is a RetrieveAll method,  return an ArrayList
        E suwat ang parameters (input) then unsa iya e return
     */

    // add new income transaction
    public void addIncome(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_AMOUNT_INCOME, transaction.getKEY_AMOUNT());
        //values.put(KEY_DATE_INCOME, transaction.getKEY_DATE());
        values.put(KEY_REF_CHECK_INCOME, transaction.getKEY_REF_CHECK());
        values.put(KEY_DESCRIPTION_INCOME, transaction.getKEY_DESCRIPTION());
        values.put(KEY_TAX_INCOME, transaction.getKEY_TAX());
        values.put(KEY_QUANTITY_INCOME, transaction.getKEY_QUANTITY());
        values.put(KEY_PAYER_INCOME, transaction.getKEY_PAYER());

        db.insert(TABLE_INCOME, null, values);
        db.close();

    }

    public Transaction getIncomeTransaction(String args){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_INCOME + " WHERE " + KEY_DATE_INCOME +
                "= ?", new String[]{args});
        Log.w("WhereClause", args);
        Transaction transaction = new Transaction();


        if(cursor!=null && cursor.moveToFirst()){
            transaction.setKEY_AMOUNT(cursor.getDouble(INDEX_INCOME_AMOUNT));
            transaction.setKEY_TAX(cursor.getDouble(INDEX_INCOME_TAX));
            transaction.setKEY_DESCRIPTION(cursor.getString(INDEX_INCOME_DESCRIPTION));
            transaction.setKEY_PAYER(cursor.getString(INDEX_INCOME_PAYER));
            transaction.setKEY_REF_CHECK(cursor.getInt(INDEX_INCOME_REF_CHECK));
            //transaction.setKEY_DATE(cursor.getInt(INDEX_INCOME_DATE)); Change to DATETIME
            transaction.setKEY_QUANTITY(cursor.getInt(INDEX_INCOME_QUANTITY));
            transaction.setKEY_ID(cursor.getInt(INDEX_INCOME_ID));
            cursor.close();
            Log.w("GETINCOMETRANSACTION", "Inside IF statement");
        }
        else{
            Log.w("GETINCOMETRANSACTION" , "Inside ELSE statement");
        }

        return transaction;
    }


    public ArrayList<Transaction> getAllIncomeTransactions(){
        ArrayList<Transaction> transactionList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_INCOME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Transaction transaction = new Transaction();
                transaction.setKEY_AMOUNT(cursor.getDouble(INDEX_INCOME_AMOUNT));
                //transaction.setKEY_DATE(cursor.getInt(INDEX_INCOME_DATE)); Change to DATETIME
                transaction.setKEY_REF_CHECK(cursor.getInt(INDEX_INCOME_REF_CHECK));
                transaction.setKEY_DESCRIPTION(cursor.getString(INDEX_INCOME_DESCRIPTION));
                transaction.setKEY_TAX(cursor.getDouble(INDEX_INCOME_TAX));
                transaction.setKEY_QUANTITY(cursor.getInt(INDEX_INCOME_QUANTITY));
                transaction.setKEY_PAYER(cursor.getString(INDEX_INCOME_PAYER));
                transactionList.add(transaction);
            }while(cursor.moveToNext());
        }

        return transactionList;

    }

    // return income transaction count
    public int getIncomeTransactionCount(){
        String query = "SELECT * FROM " + TABLE_INCOME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();

        return cursor.getCount();
    }

    public void updateIncomeTransaction(Transaction transaction, int KEY_ID){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        values.put(KEY_AMOUNT_INCOME, transaction.getKEY_AMOUNT());
        //values.put(KEY_DATE_INCOME, transaction.getKEY_DATE()); (Change to DATETIME)
        values.put(KEY_REF_CHECK_INCOME, transaction.getKEY_REF_CHECK());
        values.put(KEY_DESCRIPTION_INCOME, transaction.getKEY_DESCRIPTION());
        values.put(KEY_TAX_INCOME, transaction.getKEY_TAX());
        values.put(KEY_QUANTITY_INCOME, transaction.getKEY_QUANTITY());
        values.put(KEY_PAYER_INCOME, transaction.getKEY_PAYER());

        String whereClause = KEY_ID_INCOME + " LIKE ? ";
        String[] whereArgs = {""+KEY_ID};


        db.update( TABLE_INCOME, values, whereClause, whereArgs);
    }
    //
    public void deleteIncomeTransaction(int KEY_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INCOME, KEY_ID_INCOME+" = ?" , new String[]{""+KEY_ID});
    }

     /*
        EXPENSE QUERIES HERE
        CRUD Operations
        If there is a RetrieveAll method,  return an ArrayList
        E suwat ang parameters (input) then unsa iya e return
     */


    //Insert Expense
     public void addExpenseTransaction(Transaction transaction){
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(KEY_AMOUNT_EXP, transaction.getKEY_AMOUNT());
         values.put(KEY_PAYMENT_METHOD_EXP, transaction.getKEY_PAYMETHOD());
         values.put(KEY_DATE_EXP, String.valueOf(transaction.getKEY_DATE()));
         values.put(KEY_REF_CHECK_EXP, transaction.getKEY_REF_CHECK());
         values.put(KEY_DESCRIPTION_EXP, transaction.getKEY_DESCRIPTION());
         values.put(KEY_TAX_EXP, transaction.getKEY_TAX());
         values.put(KEY_QUANTITY_EXP, transaction.getKEY_QUANTITY());
         values.put(KEY_PAYEE, transaction.getKEY_PAYER());
         values.put(KEY_TAGS, transaction.getKEY_TAGS());

         db.insert(TABLE_EXPENSE, null, values);
         db.close();
     }

     //retrieve a specific expense transaction
     public Transaction getExpenseTransaction(int id)throws ParseException {
        //TODO

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_EXPENSE + " WHERE " +KEY_ID_EXP+" = "+id;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor!=null)
            cursor.moveToFirst();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(cursor.getString(3));
        Date dates = new Date(date.getTime());
        Transaction transaction = new Transaction( cursor.getDouble(1),
                cursor.getString(2),dates,cursor.getInt(4),cursor.getString(5),
                cursor.getDouble(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9));
         transaction.setKEY_ID(cursor.getInt(0));
        return transaction;
     }

    // retrieve all expense transactions
     public ArrayList<Transaction> getAllExpenseTransactions() throws ParseException {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_EXPENSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Transaction transaction = new Transaction();
                transaction.setKEY_ID(cursor.getInt(INDEX_ID));
                transaction.setKEY_AMOUNT(cursor.getDouble(INDEX_AMOUNT));
                transaction.setKEY_PAYMETHOD(cursor.getString(INDEX_PAYMENT_METHOD));
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date date = new java.sql.Date(sdf1.parse(cursor.getString(INDEX_DATE)).getTime());
                transaction.setKEY_DATE(date);
                transaction.setKEY_REF_CHECK(cursor.getInt(INDEX_REF_CHECK));
                transaction.setKEY_DESCRIPTION(cursor.getString(INDEX_DESCRIPTION));
                transaction.setKEY_TAX(cursor.getDouble(INDEX_TAX));
                transaction.setKEY_QUANTITY(cursor.getInt(INDEX_QUANTITY));
                transaction.setKEY_PAYER(cursor.getString(INDEX_PAYER));
                transaction.setKEY_TAGS(cursor.getString(INDEX_TAGS));
                transactionList.add(transaction);

            }while(cursor.moveToNext());
        }

        return transactionList;

     }

    //update a specific expense transaction
    public void updateExpenseTransaction(Transaction transaction){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID_EXP, transaction.getKEY_ID());
        values.put(KEY_AMOUNT_EXP, transaction.getKEY_AMOUNT());
        values.put(KEY_PAYMENT_METHOD_EXP, transaction.getKEY_PAYMETHOD());
        values.put(KEY_DATE_EXP, String.valueOf(transaction.getKEY_DATE()));
        values.put(KEY_REF_CHECK_EXP, transaction.getKEY_REF_CHECK());
        values.put(KEY_DESCRIPTION_EXP, transaction.getKEY_DESCRIPTION());
        values.put(KEY_TAX_EXP, transaction.getKEY_TAX());
        values.put(KEY_QUANTITY_EXP, transaction.getKEY_QUANTITY());
        values.put(KEY_PAYEE, transaction.getKEY_PAYER());
        values.put(KEY_TAGS, transaction.getKEY_TAGS());

        String[] string = {Integer.toString(transaction.getKEY_ID())};

        db.update(TABLE_EXPENSE, values, KEY_ID_EXP +" = "+transaction.getKEY_ID(), null);
        db.close();


    }

    //delete all expense transactions
    public void deleteAllExpenseTransactions(){
        String query = "DELETE FROM " + TABLE_EXPENSE;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();

    }

    //delete a specific expense transaction
    public void deleteExpenseTransaction(int id){
        String[] string = {Integer.toString(id)};
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_EXPENSE, KEY_ID_EXP + "=" + id, null);
        db.close();
    }

    //get the numbers of transactions
    public int getExpenseTransactionCount(){
        String query = "SELECT * FROM " + TABLE_EXPENSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();

        return cursor.getCount();
    }

     /*
        RECURRENCES QUERIES HERE
        CRUD Operations
        If there is a RetrieveAll method,  return an ArrayList
        E suwat ang parameters (input) then unsa iya e return
     */




     /*
        CUSTOM QUERIES HERE
        CRUD Operations
        If there is a RetrieveAll method,  return an ArrayList
        E suwat ang parameters (input) then unsa iya e return
     */


}
