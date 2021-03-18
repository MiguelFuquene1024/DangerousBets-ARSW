var apiclient = (function () {
  
	return {
		getMonedas : function (callback) {
			$.getJSON("http://localhost:8080/monedas" ,function (data) {
					callback(data);
				}
			);
		}

	};

})();