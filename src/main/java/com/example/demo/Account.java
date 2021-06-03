package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String Account_Number;
    private String Ownership;
    private String Current_Balance;
    private String End_Date;
    private String Date_Reported;
    private String Overdue;
    private String Sanctioned;
    private String Date_Disbursed;
    private String Payment_History;
    private String Payment_Start_Date;
    private String Date_Last_Payment;
    private String ReportingName;
    private String AccountType;
    private String RateOfInterest;
    private String CashLimit;
    private String RepaymentTenure;
    private String EmiAmount;
    private String PrincipalAmount;
    private String TotalAmount;
    private String SettlementAmount;
}
