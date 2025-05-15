package com.mycompany.pharmacymanagment;
public class Member implements Comparable<Member> {
    private int memberId=0;
    private String name;
    private String phoneNumber;
    Member(int memberId, String name, String phoneNumber) {
       this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getMemberId() {
        return memberId;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public String getName() {
        return name;
    }
    public void removeMemberId(int memberId) {
        this.memberId = 0;
        System.out.println( memberId+"this member is removed");
    }
    
    public void displayMemberInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
    }

    public String getPhone() {
        return phoneNumber;
    }

    @Override
    public int compareTo(Member other) {
        return Integer.compare(this.memberId, other.memberId);
    }

}
