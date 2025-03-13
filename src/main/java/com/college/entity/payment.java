package com.college.entity;

import java.lang.module.ModuleDescriptor;

public class payment {
     private int paymentId;
     private String paymentAmount;
     private String paymentMethod;
     private String paymentStatus;
     private String paymentDate;

     private void Payment(){}
        public payment(int paymentId, String paymentAmount, String paymentMethod, String paymentStatus, String paymentDate) {}

    private payment(ModuleDescriptor.Builder paymentBuilder){
         this.paymentId = paymentBuilder.builderPaymentId;
         this.paymentAmount = paymentBuilder.builderPaymentAmount;
         this.paymentMethod = paymentBuilder.builderPaymentMethod;
         this.paymentStatus = paymentBuilder.buidlerPaymentStatus;
         this.paymentDate = paymentBuilder.builderPaymentDate;
    }

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

    }
}
