package com.carruesco.weight;

import java.util.Date;

public class Weight {

    private Date date;
    private Double weight;
    private Double height;
    private double bmi;
    private double weightFor30;
    private double weightFor25;
    private double weightFor18d5;
    
    private double getWeightForBmi(double targetBmi) {
    	return height * height * targetBmi;
    }
    
    public Weight() {
    	this(null, null, null);
	}

    public Weight(Date date, Double weight, Double height) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        
        if (weight != null && height != null) {
        	this.bmi = weight / height / height;
        	this.weightFor30 = getWeightForBmi(30);
        	this.weightFor25 = getWeightForBmi(25);
        	this.weightFor18d5 = getWeightForBmi(18.5);
        } else {        	
        	this.bmi = 0.0;
        	this.weightFor30 = 0.0;
        	this.weightFor25 = 0.0;
        	this.weightFor18d5 = 0.0;
        }
    }
    
    @Override
    public String toString() {
    	return String.format(
                "Weight[date=%s, weight='%d', height='%d']",
                date, weight, height);
    }

	public void setDate(Date date) {
		this.date = date;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Date getDate() {
		return date;
	}

	public Double getWeight() {
		return weight;
	}

	public Double getHeight() {
		return height;
	}

	public double getBmi() {
		return bmi;
	}

	public double getWeightFor30() {
		return weightFor30;
	}

	public double getWeightFor25() {
		return weightFor25;
	}

	public double getWeightFor18d5() {
		return weightFor18d5;
	}
    
}
