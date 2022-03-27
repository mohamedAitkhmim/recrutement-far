    var male=0;
    var female=0;
    const url2 = window.location.origin+'/rest/candidat/sexe';
	fetch(url2).then(  
		function(response) {  
			if (response.status !== 200) {  
				console.warn('Looks like there was a problem. Status Code: ' +response.status);  
				return;  
				}
			response.json().then(function(data) {
				//after respense
				male=data[0];
				female=data[1];
				drawSexe();
			});  
		}).catch(function(err) {  
			console.error('Fetch Error -', err);  
	});
	
	function drawSexe()
	{
    var config = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    male,
                    female,
                ],
                backgroundColor: [
                    "#0000FF",
                    "#800080",
                ],
                label: 'Dataset 1'
            }],
            labels: [
                "Masculin",
                "Feminin",
            ]
        },
        options: {
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Candidats'
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    };
    var ctx = document.getElementById("chart-areaSexe").getContext("2d");
    window.myDoughnut2 = new Chart(ctx, config);
    document.getElementById('male').textContent=male;
    document.getElementById('female').textContent=female;
	}

