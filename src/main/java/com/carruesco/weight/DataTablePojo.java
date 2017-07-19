package com.carruesco.weight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DataTablePojo {
    private List<Col> cols = null;
    private List<Row> rows = null;
    
    public DataTablePojo(List<Weight> weights) {
    	super();
    	
    	// Columns
    	cols = new ArrayList<Col>();
    	cols.add(new Col("date", "Date", "", "date"));
    	cols.add(new Col("weight", "Weight", "", "number"));
    	cols.add(new Col("bmi18d5", "BMI 18.5", "", "number"));
    	cols.add(new Col("bmi25", "BMI 25", "", "number"));
    	cols.add(new Col("bmi30", "BMI 30", "", "number"));
    	cols.add(new Col("bmi35", "BMI 35", "", "number"));
    	
    	// Rows
    	rows = new ArrayList<Row>();
    	for (Weight weight : weights) {
    	    rows.add(new Row(weight));
    	}
	}

	public List<Col> getCols() {
		return cols;
	}

	public List<Row> getRows() {
		return rows;
	}
}

class Col {
	private String id;
	private String label;
	private String pattern;
	private String type;
	
	public Col(String id, String label, String pattern, String type) {
		super();
		this.id = id;
		this.label = label;
		this.pattern = pattern;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getPattern() {
		return pattern;
	}

	public String getType() {
		return type;
	}
}

class Row {
	private List<C> c = null;
	
	public Row(Weight weight) {
		super();
		c = new ArrayList<C>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(weight.getDate());
		c.add(new C(String.format("Date(%d, %d, %d)", cal.get(Calendar.YEAR), 
													  cal.get(Calendar.MONTH), 
													  cal.get(Calendar.DAY_OF_MONTH)), 
				new SimpleDateFormat("yyyy.MM.dd").format(weight.getDate())));
		c.add(new C(weight.getWeight(), String.format("%.1f", weight.getWeight())));
		c.add(new C(weight.getWeightFor18d5(), String.format("%.1f", weight.getWeightFor18d5())));
		c.add(new C(weight.getWeightFor25() - weight.getWeightFor18d5(), String.format("%.1f", weight.getWeightFor25())));
		c.add(new C(weight.getWeightFor30() - weight.getWeightFor25(), String.format("%.1f", weight.getWeightFor30())));
		c.add(new C(weight.getWeightFor35() - weight.getWeightFor30(), String.format("%.1f", weight.getWeightFor35())));
	}

	public List<C> getC() {
		return c;
	}
}

class C {
	private Object v;
	private String f;

	public C(Object v, String f) {
		super();
		this.v = v;
		this.f = f;
	}

	public Object getV() {
		return v;
	}

	public String getF() {
		return f;
	}
}
