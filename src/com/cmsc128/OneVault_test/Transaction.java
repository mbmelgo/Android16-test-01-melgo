package com.cmsc128.OneVault_test;

import java.sql.Date;

/**
 * Created by radleyrosal on 10/22/2015.
 */
public class Transaction {

    private int KEY_ID;
    private double KEY_AMOUNT = 0;
    private String KEY_PAYMETHOD = "";
    private Date KEY_DATE;
    private int KEY_REF_CHECK = 0;
    private String KEY_DESCRIPTION = "";
    private double KEY_TAX = 0;
    private int KEY_QUANTITY = 0;
    private String KEY_PAYER = "";
    private String KEY_TAGS = "";

    Transaction(){}

    public Transaction(double KEY_AMOUNT, String KEY_PAYMETHOD,
                       Date KEY_DATE, int KEY_REF_CHECK,
                       String KEY_DESCRIPTION, double KEY_TAX,
                       int KEY_QUANTITY, String KEY_PAYER,
                       String KEY_TAGS) {
        this.KEY_AMOUNT = KEY_AMOUNT;
        this.KEY_PAYMETHOD = KEY_PAYMETHOD;
        this.KEY_DATE = KEY_DATE;
        this.KEY_REF_CHECK = KEY_REF_CHECK;
        this.KEY_DESCRIPTION = KEY_DESCRIPTION;
        this.KEY_TAX = KEY_TAX;
        this.KEY_QUANTITY = KEY_QUANTITY;
        this.KEY_PAYER = KEY_PAYER;
        this.KEY_TAGS = KEY_TAGS;
    }

    public String getKEY_PAYMETHOD() {
        return KEY_PAYMETHOD;
    }

    public void setKEY_PAYMETHOD(String KEY_PAYMETHOD) {
        this.KEY_PAYMETHOD = KEY_PAYMETHOD;
    }

    public int getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(int KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public double getKEY_AMOUNT() {return KEY_AMOUNT;}

    public void setKEY_AMOUNT(double KEY_AMOUNT) {this.KEY_AMOUNT = KEY_AMOUNT;}

    public Date getKEY_DATE() {return KEY_DATE;}

    public void setKEY_DATE(Date KEY_DATE) {this.KEY_DATE = KEY_DATE;}

    public int getKEY_REF_CHECK() {return KEY_REF_CHECK;}

    public void setKEY_REF_CHECK(int KEY_REF_CHECK) {this.KEY_REF_CHECK = KEY_REF_CHECK;}

    public String getKEY_DESCRIPTION() {return KEY_DESCRIPTION;}

    public void setKEY_DESCRIPTION(String KEY_DESCRIPTION) {this.KEY_DESCRIPTION = KEY_DESCRIPTION;}

    public double getKEY_TAX() {return KEY_TAX;}

    public void setKEY_TAX(double KEY_TAX) {this.KEY_TAX = KEY_TAX;}

    public int getKEY_QUANTITY() {return KEY_QUANTITY;}

    public void setKEY_QUANTITY(int KEY_QUANTITY) {this.KEY_QUANTITY = KEY_QUANTITY;}

    public String getKEY_PAYER() {return KEY_PAYER;}

    public void setKEY_PAYER(String KEY_PAYER) {this.KEY_PAYER = KEY_PAYER;}

    public String getKEY_TAGS() {return KEY_TAGS;}

    public void setKEY_TAGS(String KEY_TAGS) {this.KEY_TAGS = KEY_TAGS;}
}
