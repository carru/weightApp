google.charts.load('current', {'packages':['corechart', 'controls']});
google.charts.setOnLoadCallback(setupAndDraw);

var dashboard, data;
var series = {
	0: { color: '#000000', type: 'line' }, // weight
	1: { color: '#ff9999', type: 'area', lineWidth: 0 }, // bmi 18.5
	2: { color: '#99ff99', type: 'area', lineWidth: 0 }, // bmi 25
	3: { color: '#ffcc00', type: 'area', lineWidth: 0 }, // bmi 30
	4: { color: '#cc0000', type: 'area', lineWidth: 0 }  // bmi 35
};
var aMax = 100;
var aMin = 70;
var tickStep = 5;

$(window).resize(function() { redraw(); });

function getTickValues(min, max, step) {
	var ticks = [ min ];
	var currentTick = min + step;
	while (currentTick <= max) {
	    ticks.push(currentTick);
	    currentTick = currentTick + step;
	}
	return ticks;
}

function setupAndDraw() {
	var jsonData = $.ajax({
		url : "api/weights",
		dataType : "json",
		async : false
	}).responseText;
	
	data = new google.visualization.DataTable(jsonData);
	
	var chart = new google.visualization.ChartWrapper({
//		chartType: 'google.charts.Line',
		chartType: 'ComboChart',
		containerId: 'chart_div',
		options: {
			legend: { position: 'none' },
			series: series,
			theme: 'maximized',
            isStacked: true,
            vAxis: { 
            	viewWindow: { min: aMin, max: aMax },
            	ticks: getTickValues(aMin, aMax, tickStep),
            	minorGridlines: { count: tickStep - 1 }
            },
            trendlines: { 0: {type: 'polynomial', degree: 4, color: '#999999', opacity: 0.5} }
		}
	});

	var control = new google.visualization.ControlWrapper({
		controlType: 'ChartRangeFilter',
		containerId: 'control_div',
		options: {
			filterColumnIndex: 0,
			ui: {
        		snapToData: true,
//				chartType: 'google.charts.Line',
				chartOptions: {
					series: series,
                    chartArea: { width: '95%' },
                    isStacked: true,
                    vAxis: { viewWindow: { min: aMin, max: aMax } }
				}
			}
		},
        state: {
            range: {
                start: new Date(2017, 4, 11)
            }
        }
	});
	
	dashboard = new google.visualization.Dashboard(document.getElementById('dashboard_div'));
	dashboard.bind(control, chart);
	dashboard.draw(data);
}

function redraw() {
	dashboard.draw(data);
}