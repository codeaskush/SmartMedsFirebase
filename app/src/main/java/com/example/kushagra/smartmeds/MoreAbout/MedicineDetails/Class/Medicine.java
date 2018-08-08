package com.example.kushagra.smartmeds.MoreAbout.MedicineDetails.Class;

public class Medicine {

    String medName;
    String medDesc;
    //String medImg;

    public Medicine() {
    }

    public Medicine(String medName, String medDesc) {
        this.medName = medName;
        this.medDesc = medDesc;
        //this.medImg = medImg;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedDesc() {
        return medDesc;
    }

    public void setMedDesc(String medDesc) {
        this.medDesc = medDesc;
    }

//    public String getMedImg() {
//        return medImg;
//    }

//    public void setMedImg(String medImg) {
//        this.medImg = medImg;
//    }
}
