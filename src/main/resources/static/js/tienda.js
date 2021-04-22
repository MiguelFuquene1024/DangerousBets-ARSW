/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}


function noComprados(logos){
	$(".imagen").remove();
	for(numero in logos){
			$("#menu").append('<div class="imagen"><img src="/estilos/imagenes/' + logos[numero].recurso +'" width="100%" height="100%"><button id="'+ logos[numero].recurso +'" type="button" class="btn btn-success boton_imagen">$'+ logos[numero].valor +'</button></div>');
			
		}	
		$(".boton_imagen").click(async function(){
				let promise=await api.comprarLogo($(this).attr("id"),window.localStorage.usuario);
				api.logosNoComprados(window.localStorage.usuario,noComprados);

		});
}


var usuario = api.logosNoComprados(window.localStorage.usuario,noComprados);
		
		




