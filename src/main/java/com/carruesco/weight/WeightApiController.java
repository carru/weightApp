package com.carruesco.weight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeightApiController {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	private final String SUCCESS = "OK";
	
    @GetMapping("/api/weights")
    public DataTablePojo weights() {
    	List<Weight> weights = new ArrayList<Weight>();
    	
    	jdbcTemplate.query(
                "SELECT date, weight, height FROM weights ORDER BY date ASC", 
                new Object[] {  },
                (rs, rowNum) -> new Weight(rs.getDate("date"), rs.getDouble("weight"), rs.getDouble("height"))
        ).forEach(weight -> weights.add(weight));
    	
    	return new DataTablePojo(weights);
    }
    
    @PostMapping("/api/weight")
    public String addWeight(@RequestParam(value="date", defaultValue="") String date, 
    		@RequestParam(value="weight") String weight, 
    		@RequestParam(value="height", defaultValue="") String height) {
    	
    	if ("".equals(date)) date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	if ("".equals(height)) height = getLastHeight();
    	
    	String sql = "INSERT INTO weight.weights (date, weight, height)" + 
    				 " VALUES (?, ?, ?)" +
    				 " ON DUPLICATE KEY UPDATE weight = ?, height = ?;";
    	Object[] args = new Object[] { date, weight, height, weight, height };
    	
    	try {
    		jdbcTemplate.update(sql, args);    		
    	} catch (Exception e) {
			return e.getMessage();
		}
    	
    	return SUCCESS;
    }
    
    private String getLastHeight() {
    	String sql = "SELECT height FROM weight.weights ORDER BY date DESC LIMIT 1;";
    	return jdbcTemplate.queryForObject(sql, String.class);
    }
}
