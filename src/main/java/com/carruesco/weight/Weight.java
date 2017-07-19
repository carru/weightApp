package com.carruesco.weight;

import java.util.Date;

public class Weight {

    private final Date date;
    private final double weight;
    private final double height;
    private final double bmi;
    private final double weightFor30;
    private final double weightFor25;
    private final double weightFor18d5;
    
    private double getWeightForBmi(double targetBmi) {
    	return height * height * targetBmi;
    }

    public Weight(Date date, double weight, double height) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.bmi = weight / height / height;
        this.weightFor30 = getWeightForBmi(30);
        this.weightFor25 = getWeightForBmi(25);
        this.weightFor18d5 = getWeightForBmi(18.5);
    }
    
    @Override
    public String toString() {
    	return String.format(
                "Weight[date=%s, weight='%d', height='%d']",
                date, weight, height);
    }

	public Date getDate() {
		return date;
	}

	public double getWeight() {
		return weight;
	}

	public double getHeight() {
		return height;
	}

	public double getBmi() {
//		return String.format("%.2f", bmi);
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
