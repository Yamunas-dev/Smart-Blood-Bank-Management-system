package com.donor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BloodBankOperation obj = new BloodBankOperationImpl();

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println(" SMART BLOOD BANK MANAGEMENT SYSTEM ");
            System.out.println("======================================");
            System.out.println("1. Register Donor");
            System.out.println("2. View Donors");
            System.out.println("3. Search Donor");
            System.out.println("4. Update Donor");
            System.out.println("5. Delete Donor");
            System.out.println("6. Request Blood");
            System.out.println("7. View Blood Stock");
            System.out.println("8. Exit");
            System.out.print("Enter Your Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    obj.registerDonor();
                    break;

                case 2:
                    obj.viewDonors();
                    break;

                case 3:
                    obj.searchDonor();
                    break;

                case 4:
                    obj.updateDonor();
                    break;

                case 5:
                    obj.deleteDonor();
                    break;

                case 6:
                    obj.requestBlood();
                    break;

                case 7:
                    obj.viewBloodStock();
                    break;

                case 8:
                    System.out.println("Thank You for Using Smart Blood Bank Management System...");
                    break;

                default:
                    System.out.println("Invalid Choice... Please Try Again.");
            }

        } while (choice != 8);

        sc.close();
    }
}