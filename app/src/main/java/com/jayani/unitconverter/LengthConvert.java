package com.jayani.unitconverter;

import java.text.NumberFormat;

public class LengthConvert {
    final double meter = 1;
    final double centimeter = 100;
    final double inch = 39.37;
    final double foot = 3.281;
    final double yard = 1.093613;
    final double mile = 0.0006214;

    private double beginingQty;
    private double endingQty;
    private String beginingUnitType;
    private String endingUnitType;

    public LengthConvert(){
        beginingQty = 0;
        endingQty = 0;
        beginingUnitType = "";
        endingUnitType = "";

    }

    public double getBeginingQty() {
        return beginingQty;
    }

    public void setBeginingQty(double beginingQty) {
        this.beginingQty = beginingQty;
    }

    public double getEndingQty() {
        return endingQty;
    }

    public void setEndingQty(double endingQty) {
        this.endingQty = endingQty;
    }

    public String getBeginingUnitType() {
        return beginingUnitType;
    }

    public void setBeginingUnitType(String beginingUnitType) {
        this.beginingUnitType = beginingUnitType;
    }

    public String getEndingUnitType() {
        return endingUnitType;
    }

    public void setEndingUnitType(String endingUnitType) {
        this.endingUnitType = endingUnitType;
    }

    public double getUnitTypeConstant (String unit_type){
        if(unit_type == "centimeter"){return centimeter;}
        if(unit_type == "meter"){return meter;}
        if(unit_type == "inch"){return inch;}
        if(unit_type == "foot"){return foot;}
        if(unit_type == "yard"){return yard;}
        if(unit_type == "mile"){return mile;}
        return 0;
    }

    public double calculateEndingQty(){
        double biginingQty = getBeginingQty();
        double endingQty = getEndingQty();
        double begin_unitType = getUnitTypeConstant(getBeginingUnitType());
        double end_unitType = getUnitTypeConstant(getEndingUnitType());

        endingQty = meter/begin_unitType;
        endingQty = endingQty*end_unitType;

        endingQty = endingQty * beginingQty;
        return endingQty;

    }

    public String toString(){

        NumberFormat nf = NumberFormat.getNumberInstance();

        if(endingUnitType.equals("inch") || endingUnitType.equals("yard") || endingUnitType.equals("foot")) {
            nf.setMaximumFractionDigits(2);
        }else{
            nf.setMaximumFractionDigits(4);
        }
        return nf.format(getEndingQty() )+ " " + getEndingUnitType();
    }
}
