google.charts.load('current', {'packages':['corechart', 'controls']});
google.charts.setOnLoadCallback(setupAndDraw);

var dashboard, data;

var series = {
	0 : { color : '#0000aa' }, // weight
	1 : { color : '#bbbbbb' }, // bmi 25
	2 : { color : '#bbbbbb' }  // bmi 30
};

$(window).resize(function() { redraw(); });

function setupAndDraw() {
	var jsonData = $.ajax({
		url : "/weight/api/weights",
		dataType : "json",
		async : false
	}).responseText;
	
	data = new google.visualization.DataTable(jsonData);
	
	var chart = new google.visualization.ChartWrapper({
		chartType : 'google.charts.Line',
		containerId : 'chart_div',
		options : {
			legend : {
				position : 'none'
			},
			series : series
		}
	});

	var control = new google.visualization.ControlWrapper({
		controlType : 'ChartRangeFilter',
		containerId : 'control_div',
		options : {
			filterColumnIndex : 0,
			ui : {
        		snapToData: true,
//				chartType: 'google.charts.Line',
				chartOptions : {
					series : series,
                    chartArea: {
                        width: '95%'
                    }
				}
			}
		},
        state: {
            range: {
                start: new Date(2017, 4, 11),
                end: new Date(2017, 11, 31)
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