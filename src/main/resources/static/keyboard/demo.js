$(document).ready(function(){
  $('.keyboard').mlKeyboard({layout: 'en_US'});
});

var isArabic = /^([\u0600-\u06ff]|[\u0750-\u077f]|[\ufb50-\ufbc1]|[\ufbd3-\ufd3f]|[\ufd50-\ufd8f]|[\ufd92-\ufdc7]|[\ufe70-\ufefc]|[\ufdf0-\ufdfd])*$/g;


//Solution 2: This will not let the user type chars that are not arabic
//but seem to fail on my browser... I don't quite get how does javascript splits the string.
$(".txtArabic").bind('keyup', function(e) {
	var filterFn = isArabic.test.bind(isArabic),
 	newValue = this.value.split('').filter(filterFn).join('');

	if (this.value != newValue)
	this.value = newValue;
});

