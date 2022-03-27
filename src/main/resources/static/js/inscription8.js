//variable
var inscrit2annee=9;
var technicien=13;

var doc=1;
var bac=3;
var annee2bac=2;
var ts = 4;
var tc = 5;
var emailAvailable=true;

var bacAncienId = 1;

function scolariteChange(){
	console.log("scolariteChange")
	var niveauDropdown = document.getElementById("niveauDropdown");
	var divAnneeBac = document.getElementById("divAnneeBac");
	var bacDropdown = document.getElementById("bacDropdown");
	console.log(niveauDropdown.value)
	if (niveauDropdown.value == emailAvailable){
		divAnneeBac.style.display = "block";
		bacDropdown.required=true;
	} else {
		divAnneeBac.style.display = "none";
		bacDropdown.required=false;
	}
}


function onEtudeChange(){
	var etablissementDropdown = document.getElementById("etablissementDropdown");

	var divmassar = document.getElementById("divmassar");
	var massar = document.getElementById("massar");

	var  divEtablissementCategories = document.getElementById("divEtablissementCategories");
	var  etablissementCategoriesDropdown = document.getElementById("etablissementCategoriesDropdown");

	var  divnomEtablissement = document.getElementById("divnomEtablissement");
	var  etablissementNom = document.getElementById("etablissementNom");

	if (etablissementDropdown.value == "ETRANGER"){
		//afficher
		divEtablissementCategories.style.display = "block";
		etablissementCategoriesDropdown.required=true;
		divnomEtablissement.style.display = "block";
		etablissementNom.required=true;
		//cacher
		divmassar.style.display = "none";
		massar.required=false;
	} else if(etablissementDropdown.value == "NATIONAL") {
		//afficher
		divmassar.style.display = "block";
		massar.required=true;
		//cacher
		divEtablissementCategories.style.display = "none";
		etablissementCategoriesDropdown.required=false;
		divnomEtablissement.style.display = "none";
		etablissementNom.required=false;
	} else {
		//cacher
		divEtablissementCategories.style.display = "none";
		etablissementCategoriesDropdown.required=false;
		divnomEtablissement.style.display = "none";
		etablissementNom.required=false;
		divmassar.style.display = "none";
		massar.required=false;
	}
}

function onRegionChange()
{	
	sendEnable();
	let dropdownRegion=document.getElementById('regionDropdown');
	if(dropdownRegion.options[dropdownRegion.selectedIndex].value != 0){
	var regionID=dropdownRegion.options[dropdownRegion.selectedIndex].value;
	let dropdown = document.getElementById('villeDropdown');
	const url = window.location.protocol+'/inscription/villes?region='+regionID;
	fetch(url).then(  
		function(response) {  
			if (response.status !== 200) {  
				console.warn('Looks like there was a problem. Status Code: ' +response.status);  
				return;  
				}
			response.json().then(function(data) {
			    if(data.length>0) dropdown.length = 0;
				let option;
				option = document.createElement('option');
				option.text = getChoisirLaVille();
				option.value = 0;
				dropdown.add(option);
				for (let i = 0; i < data.length; i++) {
					option = document.createElement('option');
					option.text = data[i].nomVille;
					option.value = data[i].villeID;
					dropdown.add(option);
					}    
				dropdown.selectedIndex = 0;
			});  
		}).catch(function(err) {  
			console.error('Fetch Error -', err);  
	});
	}
}
function onNationnaliteChange() {
	  var checkBox = document.getElementById("multinational");
	  var div = document.getElementById("nationaliteDiv");
	  if (checkBox.checked == true){
	    div.style.display = "block";
	    nationalite.required=true;
	  } else {
	    div.style.display = "none";
	    nationalite.required=false;
	    nationalite.value="";
	  }
}
function onValidationChange() {
	sendEnable();
}
function ValidateEmail(mail) 
{
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))
  {
    return (true)
  }
    return (false)
}
function sendEnable() 
{
	var button = document.getElementById("button");
	var marocain = document.getElementById("marocain");
	var validation = document.getElementById("validation");
	var validationMentions = document.getElementById("validationMentions");
	var selectSexe = document.getElementById("selectSexe");
	var selectSituation = document.getElementById("selectSituation");
	var regionDropdown = document.getElementById("regionDropdown");
	var villeDropdown = document.getElementById("villeDropdown");
	var niveauDropdown = document.getElementById("niveauDropdown");

	var bacDropdown = document.getElementById("bacDropdown");
	var optionDropdown = document.getElementById("optionDropdown");
	//var langueDropdown = document.getElementById("langueDropdown");
	//var etablissementDropdown = document.getElementById("etablissementDropdown");
	//var etablissementCategoriesDropdown = document.getElementById("etablissementCategoriesDropdown");

	button.disabled=!(marocain.checked
			&& validation.checked
			&& validationMentions.checked
			&& emailAvailable 
			&& (!selectSexe.required || selectSexe.value!=0) 
			&& (!selectSituation.required || selectSituation.value!=0) 
			&& (!regionDropdown.required || regionDropdown.value!=0) 
			&& (!villeDropdown.required || villeDropdown.value!=0) 
			&& (!niveauDropdown.required || niveauDropdown.value!=0)

			&& (!bacDropdown.required || bacDropdown.value!=0)
			&& (!optionDropdown.required || optionDropdown.value!=0)
			//&& (!langueDropdown.required || langueDropdown.value!=0)
			//&& (!etablissementDropdown.required || etablissementDropdown.value!=0)
			//&& (!etablissementCategoriesDropdown.required || etablissementCategoriesDropdown.value!=0)
	);
}
function onEmailFocusout()
{
	var emailString=email.value;
	if(ValidateEmail(emailString))
	{
		const url = window.location.protocol+'/inscription/email?e='+emailString;
		fetch(url).then(  
			function(response) {  
				if (response.status !== 200) {  
					console.warn('Looks like there was a problem. Status Code: ' +response.status);  
					return;  
					}
				response.json().then(function(data) {
					if(data)
					{
						emailError.textContent=getCompteExiste();
						emailAvailable=false;
						sendEnable();
					}
					else
					{
						emailError.textContent="";
						emailAvailable=true;
						sendEnable();
					}
				});  
			}).catch(function(err) {  
				console.error('Fetch Error -', err);  
		});
	}
}

function onCnieFocusout()
{
	var cin=document.getElementById("cin").value;
	var cinError=document.getElementById("cniError");

		const url = window.location.protocol+'/inscription/cnie?e='+cin;
		fetch(url).then(
			function(response) {
				if (response.status !== 200) {
					console.warn('Looks like there was a problem. Status Code: ' +response.status);
					return;
				}
				response.json().then(function(data) {
					if(data)
					{
						cinError.textContent=getCinExiste();
						cinAvailable=false;
						sendEnable();
					}
					else
					{
						cinError.textContent="";
						cinAvailable=true;
						sendEnable();
					}
				});
			}).catch(function(err) {
			console.error('Fetch Error -', err);
		});

}

//mouse hover submit button
function onSubmitMouseOver()
{
	
	if(button.disabled)
	{
		var bacDropdown = document.getElementById("bacDropdown");
		var optionDropdown = document.getElementById("optionDropdown");
		//var langueDropdown = document.getElementById("langueDropdown");
		//var etablissementDropdown = document.getElementById("etablissementDropdown");
		//var etablissementCategoriesDropdown = document.getElementById("etablissementCategoriesDropdown");

		let text="";
		let errors=document.getElementById('submitErrors');
		if(!marocain.checked)
			text+=getConditionMarocain()+"</br>";
		if(!validation.checked)
			text+=getConditionInformationsExacts()+"</br>";
		if(!validationMentions.checked)
        		text+=getValidationMentions()+"</br>";
		if(!emailAvailable)
			text+=+getCompteExiste()+"</br>";
		if(selectSexe.required & selectSexe.value==0)
			text+=getRemplirChamps()+" "+getSexe()+"</br>";
		if(regionDropdown.required & regionDropdown.value==0)
			text+=getRemplirChamps()+" "+getRegion()+"</br>";
		if(selectSituation.required & selectSituation.value==0)
			text+=getRemplirChamps()+" "+getSituation()+"</br>";
		if(villeDropdown.required & villeDropdown.value==0)
			text+=getRemplirChamps()+" "+getVille()+"</br>";
		if(niveauDropdown.required & niveauDropdown.value==0)
			text+=getRemplirChamps()+" "+getNiveau()+"</br>";

		if(bacDropdown.required & bacDropdown.value==0) text+=getRemplirChamps()+" "+getbacDropdown()+"</br>";
		if(optionDropdown.required & optionDropdown.value==0) text+=getRemplirChamps()+" "+getoptionDropdown()+"</br>";
		//if(langueDropdown.required & langueDropdown.value==0) text+=getRemplirChamps()+" "+getlangueDropdown()+"</br>";
		//if(etablissementDropdown.required & etablissementDropdown.value==0) text+=getRemplirChamps()+" "+getetablissementDropdown()+"</br>";
		//if(etablissementCategoriesDropdown.required & etablissementCategoriesDropdown.value==0) text+=getRemplirChamps()+" "+getetablissementCategoriesDropdown()+"</br>";


		errors.innerHTML=text;
		let pop=document.getElementById('popup-box');
		pop.style.display = "block";
	}
}

function onSubmitMouseOut()
{
	let pop=document.getElementById('popup-box');
	pop.style.display = "none";
}
$(function () {
    $(".btn:disabled").wrap(function() {
        return '<div onmouseenter="' + $(this).attr('onmouseenter') + '" />';
    });
});
$(function () {
    $(".btn:disabled").wrap(function() {
        return '<div onmouseleave="' + $(this).attr('onmouseleave') + '" />';
    });
});
