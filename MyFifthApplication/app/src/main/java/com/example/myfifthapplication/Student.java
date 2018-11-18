package com.example.myfifthapplication;

public class Student {
    private String stuID;
    private String name;
    private String birthDate;
    private String phoneNumber;

    public Student(){}
    public Student(String stuID, String name,String birthDate, String phoneNumber){
        this.stuID = stuID;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getStuID() {
        return stuID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
