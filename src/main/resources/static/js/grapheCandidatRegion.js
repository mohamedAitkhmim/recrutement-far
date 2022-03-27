	var titre=new Array();
	var total=new Array();
	var colors=new Array();
	var randomScalingFactor = function() {
        return Math.round(Math.random() * 100);
    };
    var randomColorFactor = function() {
        return Math.round(Math.random() * 255);
    };
	var randomColor = function(opacity) {
        return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',' + (opacity || '.3') + ')';
    };
    const url5 = window.location.origin+'/rest/region/graphe';
    console.log(url5);
	fetch(url5).then(  
		function(response) {  
			if (response.status !== 200) {  
				console.warn('Looks like there was a problem. Status Code: ' +response.status);  
				return;  
				}
			response.json().then(function(data) {
				//after respense
				for (let i = 0; i < data.length; i++)
				{
					console.log(total[i]=data[i].region);
					console.log(total[i]=data[i].candidats);
					titre[i] = data[i].region;
					total[i]=data[i].candidats;
					colors[i]=randomColor(1);
				}
				drawCandidatRegion();
			});  
		}).catch(function(err) {  
			console.error('Fetch Error -', err);  
	});
	
	function drawCandidatRegion()
	{
		var barChartData = {
	            labels: titre,
	            datasets: [{
	                label: 'Candidats',
	                backgroundColor: colors,
	                data: total
	            }]
	        };
		var config = {
                type: 'doughnut',
                data: barChartData,
                options: {
                    title: {
                        display: true,
                        text: 'Candidats par région'
                    },
					scale: {
						ticks: {
						beginAtZero: true
						},
						reverse: false
					},
		            animation: {
		            	animateScale: true,
		                animateRotate: true
		            }
                }
            };
    var ctx = document.getElementById("chart-areaCandidatRegion").getContext("2d");
    window.myDoughnut2 = new Chart(ctx, config);
	}

