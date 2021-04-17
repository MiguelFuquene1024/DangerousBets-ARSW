/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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



var usuario = api.getUsuario(getQueryVariable("name"),function(obtenerUsuario){

		$("#nombreUsuario").html(obtenerUsuario.name);
		$("#nickUsuario").html(obtenerUsuario.nickname);
	
});

var monedas = api.getPerfil(getQueryVariable("name"),function(obtenerMonedas){
        $("#cantidadMonedas").html(obtenerMonedas.moneda);
		$("#imagen_perfil").append('<img src="/estilos/imagenes/' + obtenerMonedas.imagen_perfil + '" width="63%">');
}); 


