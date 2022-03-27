    var sbac=0;
    var bac=0;
    const url = window.location.origin+'/rest/candidat/bac';
	fetch(url).then(  
		function(response) {  
			if (response.status !== 200) {  
				console.warn('Looks like there was a problem. Status Code: ' +response.status);  
				return;  
				}
			response.json().then(function(data) {
				//after respense
				bac=data[0];
				sbac=data[1];
				draw();
			});  
		}).catch(function(err) {  
			console.error('Fetch Error -', err);  
	});
	
	function draw()
	{
    var config = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [
                    sbac,
                    bac,
                ],
                backgroundColor: [
                    "#F7464A",
                    "#46BFBD",
                ],
                label: 'Dataset 1'
            }],
            labels: [
                "Sans baccalauréat",
                "Avec baccalauréat",
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
    var ctx = document.getElementById("chart-areaBac").getContext("2d");
    window.myDoughnut = new Chart(ctx, config);
    document.getElementById('bacp').textContent=bac+"%";
    document.getElementById('sbacp').textContent=sbac+"%";
	}

