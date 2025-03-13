package com.college.entity;


public class Payment {
     private int paymentId;
     private String paymentAmount;
     private String paymentMethod;
     private String paymentStatus;
     private String paymentDate;

     private void Payment(){}
        public Payment(int paymentId, String paymentAmount, String paymentMethod, String paymentStatus, String paymentDate) {}

    public int getPaymentId() {
         return paymentId;
    }

    public String getPaymentAmount() {
         return paymentAmount;
    }
    public String getPaymentMethod() {
         return paymentMethod;
    }
    public String getPaymentStatus() {
         return paymentStatus;
    }
    public String getPaymentDate() {
         return paymentDate;
    }

    @Override
    public String toString() {

        return "";
    }
}
