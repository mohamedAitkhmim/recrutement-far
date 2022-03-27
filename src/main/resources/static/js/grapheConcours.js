
	var titre=new Array();
	var rejette=new Array();
	var accepte=new Array();
	var encours=new Array();
    const url3 = window.location.origin+'/rest/concours/graphe';
	fetch(url3).then(  
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
					rejette[i]=data[i].rejette;
					accepte[i] = data[i].accepte;
					encours[i] = data[i].encours;
				}
				drawConcours();
			});  
		}).catch(function(err) {  
			console.error('Fetch Error -', err);  
	});
	
	function drawConcours()
	{
		
		var barChartData = {
	            labels: titre,
	            datasets: [{
	                label: 'Acceptés',
	                backgroundColor: "rgba(0,255,0,0.5)",
	                data: accepte
	            }, {
	                label: 'Rejetés',
	                backgroundColor: "rgba(255,0,0,0.5)",
	                data: rejette
	            }, {
	                label: 'En cours',
	                backgroundColor: "rgba(255,255,0,0.5)",
	                data: encours
	            }]
	        };
		var config = {
                type: 'bar',
                data: barChartData,
                options: {
                    title: {
                        display: true,
                        text: 'Résultats par concours'
                    }
                }
            };
		
		var ctx = document.getElementById("canvasConcours").getContext("2d");
    window.myBar = new Chart(ctx, config);
	}

