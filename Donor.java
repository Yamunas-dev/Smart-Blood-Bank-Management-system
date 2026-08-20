package com.donor;

public class Donor {

    private int donorId;
    private String donorName;
    private int age;
    private String gender;
    private String bloodGroup;
    private String phone;
    private String city;
    private int bloodUnits;

    public Donor() {
    }

    public Donor(int donorId, String donorName, int age, String gender,
                 String bloodGroup, String phone, String city, int bloodUnits) {

        this.donorId = donorId;
        this.donorName = donorName;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.city = city;
        this.bloodUnits = bloodUnits;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBloodUnits() {
        return bloodUnits;
    }

    public void setBloodUnits(int bloodUnits) {
        this.bloodUnits = bloodUnits;
    }
}
