package com.pluarsight;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<Contract> contractList;

    UserInterface ui = new UserInterface();
    public void display(){
        getContract();
        displayMenu();
    }

    public void processGetAllContracts(){
        displayHelper(contractList);
    }
    private void getContract(){
        ContractDataManager contractDataManager = new ContractDataManager();
        contractList=contractDataManager.loadContract();
    }
    private void displayHelper(ArrayList<Contract> listToBeDisplayed){
        for (Contract contract: listToBeDisplayed){
            System.out.println(contract.toString());
        }
    }



    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println(" Choose an option from the following:");
            System.out.println("0 .) All Contracts \u25C6");
            System.out.println("2 .) Main menu \u25C6 ");


            System.out.print("Your Selection \u25C6");
            String input = scanner.nextLine().trim();


            switch (input) {
                case "0":
                    processGetAllContracts();
                    break;
                case "2":
                    ui.display();
                    running=false;

                    break;
            }
        }
    }
}