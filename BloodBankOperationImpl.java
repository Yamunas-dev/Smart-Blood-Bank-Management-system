package com.donor;

import java.sql.*;
import java.util.Scanner;



public class BloodBankOperationImpl implements BloodBankOperation {

    Scanner sc = new Scanner(System.in);
    Connection con = DBConnection.getConnection();

    @Override
    public void registerDonor() {

        try {

            System.out.println("Enter Donor Name : ");
            String name = sc.nextLine();
            sc.nextLine();

            System.out.println("Enter Age : ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Gender : ");
            String gender = sc.nextLine();

            System.out.println("Enter Blood Group : ");
            String bloodGroup = sc.nextLine();

            System.out.println("Enter Phone Number : ");
            String phone = sc.nextLine();

            System.out.println("Enter City : ");
            String city = sc.nextLine();

            System.out.println("Enter Blood Units : ");
            int units = sc.nextInt();

            String sql = "INSERT INTO donor(donor_name,age,gender,blood_group,phone,city,blood_units) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, bloodGroup);
            ps.setString(5, phone);
            ps.setString(6, city);
            ps.setInt(7, units);

            int i = ps.executeUpdate();

            if(i > 0) {
                System.out.println("Donor Registered Successfully...");
            } else {
                System.out.println("Registration Failed...");
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    @Override
    public void viewDonors() {

        try {

            String sql = "SELECT * FROM donor";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n------------ Donor Details ------------");

            while (rs.next()) {

                System.out.println("Donor ID      : " + rs.getInt("donor_id"));
                System.out.println("Name          : " + rs.getString("donor_name"));
                System.out.println("Age           : " + rs.getInt("age"));
                System.out.println("Gender        : " + rs.getString("gender"));
                System.out.println("Blood Group   : " + rs.getString("blood_group"));
                System.out.println("Phone         : " + rs.getString("phone"));
                System.out.println("City          : " + rs.getString("city"));
                System.out.println("Blood Units   : " + rs.getInt("blood_units"));
                System.out.println("--------------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void searchDonor() {

        try {

            System.out.print("Enter Donor ID : ");
            int id = sc.nextInt();

            String sql = "SELECT * FROM donor WHERE donor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nDonor Found");
                System.out.println("Donor ID      : " + rs.getInt("donor_id"));
                System.out.println("Name          : " + rs.getString("donor_name"));
                System.out.println("Age           : " + rs.getInt("age"));
                System.out.println("Gender        : " + rs.getString("gender"));
                System.out.println("Blood Group   : " + rs.getString("blood_group"));
                System.out.println("Phone         : " + rs.getString("phone"));
                System.out.println("City          : " + rs.getString("city"));
                System.out.println("Blood Units   : " + rs.getInt("blood_units"));

            } else {

                System.out.println("Donor Not Found.");

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void updateDonor() {

        try {

            System.out.print("Enter Donor ID : ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Phone Number : ");
            String phone = sc.nextLine();

            System.out.print("Enter New City : ");
            String city = sc.nextLine();

            System.out.print("Enter New Blood Units : ");
            int units = sc.nextInt();

            String sql = "UPDATE donor SET phone=?, city=?, blood_units=? WHERE donor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, phone);
            ps.setString(2, city);
            ps.setInt(3, units);
            ps.setInt(4, id);

            int i = ps.executeUpdate();

            if(i > 0) {
                System.out.println("Donor Details Updated Successfully...");
            } else {
                System.out.println("Donor ID Not Found...");
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void deleteDonor() {

        try {

            System.out.print("Enter Donor ID : ");
            int id = sc.nextInt();

            String sql = "DELETE FROM donor WHERE donor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int i = ps.executeUpdate();

            if(i > 0) {
                System.out.println("Donor Deleted Successfully...");
            } else {
                System.out.println("Donor ID Not Found...");
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void requestBlood() {

        try {

            System.out.print("Enter Blood Group : ");
            sc.nextLine();
            String bloodGroup = sc.nextLine();

            System.out.print("Enter Required Units : ");
            int requiredUnits = sc.nextInt();

            String sql = "SELECT * FROM donor WHERE blood_group=? AND blood_units>=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bloodGroup);
            ps.setInt(2, requiredUnits);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int donorId = rs.getInt("donor_id");
                int availableUnits = rs.getInt("blood_units");

                int remainingUnits = availableUnits - requiredUnits;

                String update = "UPDATE donor SET blood_units=? WHERE donor_id=?";

                PreparedStatement ps1 = con.prepareStatement(update);

                ps1.setInt(1, remainingUnits);
                ps1.setInt(2, donorId);

                ps1.executeUpdate();

                System.out.println("Blood Request Approved...");
                System.out.println("Remaining Units : " + remainingUnits);

            } else {

                System.out.println("Blood Not Available.");

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }
    @Override
    public void viewBloodStock() {

        try {

            String sql = "SELECT blood_group, SUM(blood_units) AS total_units FROM donor GROUP BY blood_group";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n====== Blood Stock ======");

            while (rs.next()) {

                System.out.println(rs.getString("blood_group")
                        + " : "
                        + rs.getInt("total_units")
                        + " Units");

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }
}
