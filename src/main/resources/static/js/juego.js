var api = apiclient;
var juegoComenzado=false;
if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
function getQueryVariable(variable) {
   var query = window.location.search.substring(1);
   var vars = query.split("&");
   for (var i=0; i < vars.length; i++) {
       var pair = vars[i].split("=");
       if(pair[0] == variable) {
           return pair[1];
       }
   }
   return window.localStorage.usuario;
}

function actualizarJuego(){
	
	api.investigarPokerPlayer(name,window.localStorage.usuario,function(datos){
		//console.log(datos);
	});
	api.obtenerMesa(name,function(datos){
		console.log(datos);
	});
	
}



var name=getQueryVariable("name");
var jugadores_actuales=[];
$(document).ready(async function(){
		

	setInterval(actualizarJuego, 3000);


		

	
	

});