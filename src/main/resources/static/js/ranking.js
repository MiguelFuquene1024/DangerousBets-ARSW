var api = apiclient;
if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
function ranking(ran){
	console.log(ran);
	let s='<table class="default"><tr><th>Nombre</th><th>Cantidad de Monedas</th></tr>';
	for(numero in ran){
		s=s+'<tr><th>'+ran[numero][0]+'</th><th>'+ran[numero][1]+'</th></tr>';
	}
	s=s+'</table>';
	$("#ranking").append(s);
	
	}


$(document).ready(function(){	
	$(document).ready(function(){	
	
	api.getMejoresJugadores(ranking);			
		
	});
});