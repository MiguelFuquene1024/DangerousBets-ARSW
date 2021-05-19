var api = apiclient;
if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}


function ranking(ran){
	console.log(ran);
	let s='<table class="tabla"><tr><th class="puesto">Puesto</th><th class="nombre">Nombre</th><th class="cmonedas">Cantidad de Monedas</th></tr>';
	let cont=1
	for(numero in ran){
		
		s=s+'<tr id="'+ ran[numero][0] +'" class="jugadores"><th>'+ cont +'</th><th>'+ran[numero][0]+'</th><th>'+ran[numero][1]+'</th></tr>';
		
		cont+=1
	}
	s=s+'</table>';
	$("#ranking").append(s);
		
	$("#" + ran[0][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[0][0];
	});
	$("#" + ran[1][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[1][0];
	});
	$("#" + ran[2][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[2][0];
	});
	$("#" + ran[3][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[3][0];
	});
	$("#" + ran[4][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[4][0];
	});
	$("#" + ran[5][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[5][0];
	});
	$("#" + ran[6][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[6][0];
	});
	$("#" + ran[7][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[7][0];
	});
	$("#" + ran[8][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[8][0];
	});
	$("#" + ran[9][0]).click(function(){
		window.location.href="/perfilranking.html?name="+ran[9][0];
	});
		
	
	
}


$(document).ready(function(){	
	$(document).ready(function(){	
	
	api.getMejoresJugadores(ranking);			
		
	});
});