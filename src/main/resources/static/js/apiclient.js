var apiclient = (function () {
  
	return {
		getMonedas : function (callback) {
			$.getJSON("/monedas" ,function (data) {
					callback(data);
				}
			);
		},

		getLogros : function (callback) {

			$.getJSON("/getLogros" ,function (data) {
					callback(data);
				}
			);
		},
		

	}
})();