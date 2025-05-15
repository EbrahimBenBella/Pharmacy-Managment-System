package com.mycompany.pharmacymanagment;
import java.util.ArrayList;
import java.util.Collections;

public class PharmacyManager {
private ArrayList<PharmacyItems> pharmacyitems = new ArrayList<PharmacyItems>();
private ArrayList<Member> members = new ArrayList<Member>();
PharmacyManager() {

}
PharmacyManager(ArrayList<PharmacyItems> pharmacyitems) {
    this.pharmacyitems = pharmacyitems;
}
PharmacyManager(ArrayList<PharmacyItems> pharmacyitems, ArrayList<Member> members) {
    this.pharmacyitems = pharmacyitems;
    this.members = members;
}
   public void addPharmacyItem(PharmacyItems item) {
        pharmacyitems.add(item);
    }
    public void removePharmacyItem(PharmacyItems item) {
        pharmacyitems.remove(item);
    }
    public void addMember(Member member) {
        members.add(member);
    }
    public void removeMember(Member member) {
        members.remove(member);
    }
    public ArrayList<PharmacyItems> getPharmacyItems() {
        return pharmacyitems;
    }
    public ArrayList<Member> getMembers() {
        return members;
    }
    public void displayPharmacyItems() {
        Collections.sort(pharmacyitems);
        for (PharmacyItems item : pharmacyitems) {
            System.out.println(item.printDetails());
        }
    }
    public void displayMembers() {
        Collections.sort(members);
        for (Member member : members) {
            member.displayMemberInfo();
        }
    }
        public void displaysorteddrugs(){
            ArrayList<Drugs> drugs = new ArrayList<>();
            for(PharmacyItems i: pharmacyitems){
                if(i instanceof Drugs){
                    drugs.add((Drugs)i);
                }
            }
            Collections.sort(drugs);
            for(Drugs i: drugs){
                System.out.println(i.printDetails());
            }
        }
        public PharmacyItems findItembyid (int id){
            for(PharmacyItems i: pharmacyitems){
                if(i.getItemId() == id){
                    return i;
                }
            }
            return null;
        
        }
        public Member findMemberbyid (int id){
            for(Member i: members){
                if(i.getMemberId() == id){
                    return i;
                }
            } return null;
        }
        public void displaysortedequipment(){
            ArrayList<Equipment> equipment = new ArrayList<>();
            for(PharmacyItems i: pharmacyitems){
                if(i instanceof Equipment){
                    equipment.add((Equipment)i);
                }
            }
            Collections.sort(equipment);
            for(Equipment i: equipment){
                System.out.println(i.printDetails());
            }
        }
        public void displaysortedhealthcareproduct(){
            ArrayList<HealthCareProduct> healthcareproduct = new ArrayList<>();
            for(PharmacyItems i: pharmacyitems){
                if(i instanceof HealthCareProduct){
                    healthcareproduct.add((HealthCareProduct)i);
                }
            }
            Collections.sort(healthcareproduct);
            for(HealthCareProduct i: healthcareproduct){
                System.out.println(i.printDetails());
            }
        }
    }   



