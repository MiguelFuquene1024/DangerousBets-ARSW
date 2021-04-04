/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
var usuario = api.getUsuario(function(obtenerUsuario){

	console.log(obtenerUsuario);
        $("#nombreUsuario").html(obtenerUsuario.name);
});
var nick = api.getUsuario(function(obtenerNickName){

	console.log(obtenerNickName);
        $("#nickUsuario").html(obtenerNickName.nickname);
});
var monedas = api.getMonedas(function(obtenerMonedas){

	console.log(obtenerMonedas);
        $("#cantidadMonedas").html(obtenerMonedas);
}); 


