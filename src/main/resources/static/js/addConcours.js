//variable
function onCategorieChange()
{
	let dropdownRegion=document.getElementById('categorieDropdown');
	var categorieID=dropdownRegion.options[dropdownRegion.selectedIndex].value;
	let dropdown = document.getElementById('ecoleDropdown1');
	let dropdown2 = document.getElementById('ecoleDropdown2');
	dropdown.length = 0;
	dropdown2.length = 0;
	const url = 'http://' + window.location.host+'/rest/ecoles?id=' + categorieID;
	fetch(url).then(  
		function(response) {  
			if (response.status !== 200) {  
				console.warn('Looks like there was a problem. Status Code: ' +response.status);  
				return;  
				}
			response.json().then(function(data) {  
				for (let i = 0; i < data.length; i++) {
					option = document.createElement('option');
					option.text = data[i].nomEcole;
					option.value = data[i].ecoleID;
					dropdown.add(option);
					}    
				for (let i = 0; i < data.length; i++) {
					option = document.createElement('option');
					option.text = data[i].nomEcole;
					option.value = data[i].ecoleID;
					dropdown2.add(option);
					}  
				//dropdown.selectedIndex = 0;
				//dropdown2.selectedIndex = 0;
			});  
		}).catch(function(err) {  
			console.error('Fetch Error -', err);  
	});
}
onCategorieChange();
