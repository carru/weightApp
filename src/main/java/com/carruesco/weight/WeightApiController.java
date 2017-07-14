package com.carruesco.weight;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeightApiController {
	
	@Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/api/weights")
    public DataTablePojo weights() {
    	List<Weight> weights = new ArrayList<Weight>();
    	
    	jdbcTemplate.query(
                "SELECT date, weight, height FROM weights", 
                new Object[] {  },
                (rs, rowNum) -> new Weight(rs.getDate("date"), rs.getDouble("weight"), rs.getDouble("height"))
        ).forEach(weight -> weights.add(weight));
    	
    	return new DataTablePojo(weights);
    }
}
