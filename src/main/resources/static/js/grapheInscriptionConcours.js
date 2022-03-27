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
    const url4 = window.location.origin+'/rest/concours/graphe';
	fetch(url4).then(  
		function(response) {  
			if (response.status !== 200) {  
				console.warn('Looks like there was a problem. Status Code: ' +response.status);  
				return;  
				}
			response.json().then(function(data) {
				//after respense
				for (let i = 0; i < data.length; i++)
				{
					titre[i] = data[i].titre;
					total[i]=data[i].total;
					colors[i]=randomColor(1);
				}
				drawInscriptionConcours();
			});  
		}).catch(function(err) {  
			console.error('Fetch Error -', err);  
	});
	
	function drawInscriptionConcours()
	{
		var barChartData = {
	            labels: titre,
	            datasets: [{
	                label: 'Inscriptions',
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
                        text: 'Inscriptions par concours'
                    },
					scale: {
						ticks: {
						beginAtZero: true
						},
						reverse: false
					},
		            animation: {
		                animateRotate: false
		            }
                }
            };
    var ctx = document.getElementById("chart-areaInscriptionConcours").getContext("2d");
    window.myDoughnut2 = new Chart.PolarArea(ctx, config);
	}

