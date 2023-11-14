package com.pluarsight;

public class SalesContract  extends Contract {

    private double salesTaxAmount ;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
        this.salesTaxAmount= .05*this.getVehicleSold().getPrice();
        this.recordingFee=100;
        this.processingFee = this.getVehicleSold().getPrice()<10000 ? 295 :495;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getMonthlyPayment(){
        if (this.isFinance()){
            return getTotalPrice() * (getVehicleSold().getPrice() < 10000 ? (.0525/12) : (.0425/12))
                    / (1 - Math.pow(1/(1 + (getVehicleSold().getPrice() < 10000 ? (.0525/12) : (.0425/12))), (getVehicleSold().getPrice() < 10000 ? 24 : 48)));
        }
        return 0;
    }

    @Override
    public double getTotalPrice() {
        return this.getVehicleSold().getPrice() + this.getSalesTaxAmount() + this.getRecordingFee() + this.getProcessingFee();
    }

    @Override
    public String toString() {
        return ("SALES" + "|" + this.getDate() + "|" + this.getCustomerName() + "|" + this.getCustomerEmail() + "|" + this.getVehicleSold().getVin() + "|" + this.getVehicleSold().getYear() + "|" + this.getVehicleSold().getMake() + "|" + this.getVehicleSold().getModel() + "|" + this.getVehicleSold().getVehicleType() + "|" + this.getVehicleSold().getColor() + "|" + this.getVehicleSold().getOdometer() + "|" + this.getVehicleSold().getPrice() + "|" + salesTaxAmount + "|" + recordingFee + "|" + processingFee + "|" + getTotalPrice() + "|" + finance + "".toUpperCase() + "|" + getMonthlyPayment());

    }
}

