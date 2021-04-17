var api = apiclient;

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


 

$(document).ready(function(){
	api.investigarSala(getQueryVariable("name"),function(mesa){
		$("#mesa_vacio").html(mesa.nombre);
		$("#clave_vacio").html(mesa.clave);
	});
	
	
	$("#boton_comenzar").click(function(){
	});
	$("#boton_privacidad").click(function(){
		$("#boton_privacidad").css("display","none");
		$("#boton_publico").css("display","block");
		
	});
	$("#boton_publico").click(function(){
		$("#boton_publico").css("display","none");
		$("#boton_privacidad").css("display","block");
		
	});
	$("#boton_salir").click(function(){
		
	});
	

	
	
	
});
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

