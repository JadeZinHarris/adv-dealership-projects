package com.pluarsight;

public class LeaseContract extends Contract {
    private double leaseFee;
    private double expectedEndingValue ;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.leaseFee  = .07*this.getVehicleSold().getPrice();
        this.expectedEndingValue= this.getVehicleSold().getPrice()/2;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }


    @Override
    public double getMonthlyPayment() {
        return this.getTotalPrice() * (.04/12) / (1 - Math.pow(1/(1 + (.04/12)), 36));
    }
    @Override
    public double getTotalPrice() {
        return (this.getVehicleSold().getPrice() - expectedEndingValue) + this.getLeaseFee();
    }

    @Override
    public String toString() {
        return   ("LEASE" + "|" + this.getDate() + "|" + this.getCustomerName() + "|" + this.getCustomerEmail() + "|" + this.getVehicleSold().getVin() + "|" + this.getVehicleSold().getYear() + "|" + this.getVehicleSold().getMake() + "|" + this.getVehicleSold().getModel() + "|" + this.getVehicleSold().getVehicleType() + "|" + this.getVehicleSold().getColor() + "|" + this.getVehicleSold().getOdometer() + "|" + this.getVehicleSold().getPrice() + expectedEndingValue + "|" + leaseFee + "|" + getTotalPrice() + "|" + getMonthlyPayment() );


    }

}

