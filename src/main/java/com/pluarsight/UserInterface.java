package com.pluarsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    Scanner input = new Scanner(System.in);

//__________________________


    public UserInterface() {
    }
    //__________________________
    public Dealership getDealership() {
        return dealership;
    }

    public void setDealership(Dealership dealership) {
        this.dealership = dealership;
    }

//__________________________

    private void init(){
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealership = dealershipFileManager.getDealership();
    }
    private void updateDealership(){
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);
    }


    public void display(){
        init();
        displayMenu();
    }

    public void processGetByPriceRequest(){
        Double min = null;
        Double max = null;

        while (min == null || max == null || min >= max) {
            System.out.print("Enter min:\uD83D\uDC31 ");
            String minInput = input.next();

            System.out.print("Enter max: \uD83D\uDC31");
            String maxInput = input.next();

            try {
                min = Double.parseDouble(minInput);
                max = Double.parseDouble(maxInput);

                if (min >= max) {
                    System.out.println("Min price should be less than max price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        }

        displayHelper(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest(){
        System.out.println("enter make:\uD83D\uDC31");
        String make = input.next();
        System.out.println("enter model:\uD83D\uDC31");
        String model = input.next();
        displayHelper(dealership.getVehiclesByMakeModel(make, model));

    }

    public void processGetByYearRequest(){
        int min = -1;
        int max = -1;

        while (min == -1 || max == -1 || min >= max) {
            System.out.print("Enter min:\uD83D\uDC31 ");
            String minInput = input.next();

            System.out.print("Enter max: \uD83D\uDC31");
            String maxInput = input.next();

            try {
                min = Integer.parseInt(minInput);
                max = Integer.parseInt(maxInput);

                if (min >= max) {
                    System.out.println("Min price should be less than max price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        }
        displayHelper(dealership.getVehicleByYear(min, max));

    }

    public void processGetByColorRequest(){
        System.out.println("enter color:\uD83D\uDC49\uD83C\uDFFD");
        String color = input.next();
        System.out.println(color);
        displayHelper(dealership.getVehicleByColor(color));
    }

    public void processGetByMileageRequest(){
        int min = -1;
        int max = -1;

        while (min == -1 || max == -1 || min >= max) {
            System.out.print("Enter min:\u26D0 ");
            String minInput = input.next();

            System.out.print("Enter max: \u26D0");
            String maxInput = input.next();

            try {
                min = Integer.parseInt(minInput);
                max = Integer.parseInt(maxInput);

                if (min >= max) {
                    System.out.println("Min price should be less than max price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        }
        displayHelper(dealership.getVehicleByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest(){
        System.out.println("enter vehicle type:\u26D0");
        String type = input.next();
        displayHelper(dealership.getVehicleByType(type));
    }

    public void processGetAllVehicleRequest(){
        displayHelper(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest(){
        int vin = -1;
        int year = -1;
        String make = null;
        String model = null;
        String vehicleType = null;
        String color = null;
        int odometer = -1;
        Double price = -1.0;
        while (vin == -1) {
            System.out.print("Enter vin: \u26EB ");
            try {
                vin = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for VIN. Please enter a valid integer(s) ONLY.");
            }
        }
        while (year == -1) {
            System.out.print("Enter year: \u26EB");
            try {
                year = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for year. Please enter a valid integer(s) ONLY.");
            }
        }

        while (make == null) {
            System.out.print("Enter make:\uD83D\uDC49\uD83C\uDFFD ");
            make = input.nextLine();
            if (make.contains("|")) {
                System.out.println("Make cannot contain a pipe character (|)");
                make = null;
            }
        }

        while (model == null) {
            System.out.print("Enter model: \u26EB");
            model = input.nextLine();
            if (model.contains("|")) {
                System.out.println("Model cannot contain a pipe character (|)");
                model = null;
            }
        }

        while (vehicleType == null) {
            System.out.print("Enter vehicle type:\u26EB ");
            vehicleType = input.nextLine();
            if (vehicleType.contains("|")) {
                System.out.println("Vehicle type cannot contain a pipe character (|)");
                vehicleType = null;
            }
        }

        while (color == null) {
            System.out.print("Enter color: \u26D3");
            color = input.nextLine();
            if (color.contains("|")) {
                System.out.println("Color cannot contain a pipe character (|)");
                color = null;
            }
        }

        while (odometer == -1) {
            System.out.print("Enter odometer: \u26D3 ");
            try {
                odometer = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for odometer. Please enter a valid integer(s) ONLY.");
            }
        }

        while (price == -1.0) {
            System.out.print("Enter price: \u26D3 ");
            try {
                price = Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for price. Please enter a valid number ONLY");
            }
        }

        Vehicle vehicle = new Vehicle(vin,year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        System.out.println("vehicle is added " + vehicle);
        updateDealership();
    }

    public void processRemoveVehicleRequest(){
        System.out.println("enter the vin of the vehicle you want to remove: \u25C6 ");
        int vin = Integer.parseInt(input.next());
        int counter = 0;
        for (Vehicle x: dealership.getAllVehicles()){
            if (x.getVin()==vin){
                dealership.removeVehicle(x);
                System.out.println("vehicle is removed " + x);
                counter++;
            }
        }
        if (counter==0) {
            System.out.println("VIN not found! NO vehicle removed!");
        }else{
            updateDealership();
        }

    }

    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1) Find Vehicle within a price range");
            System.out.println("2) Find Vehicle by make or model");
            System.out.println("3) Find vehicle by year range");
            System.out.println("4) Find vehicle by color");
            System.out.println("5) Find vehicle by mileage range");
            System.out.println("6) Find vehicle by vehicle type (car,truck,suv,van)");
            System.out.println("7) List all vehicles");
            System.out.println("8) Add a vehicle");
            System.out.println("9) Remove a vehicle");
            System.out.println("0) Quit");


            System.out.print("Your Selection \uD83D\uDC36");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehicleRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;

                case "0":

                    running=false;
                    break;
            }
        }
    }


    private void displayHelper(List<Vehicle> listToBeDisplayed){
        for (Vehicle vehicle: listToBeDisplayed){
            System.out.println(vehicle);
        }
    }

}