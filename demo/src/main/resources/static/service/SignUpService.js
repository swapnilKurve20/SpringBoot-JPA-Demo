appName.service("SignUpService", function($http,$q){

	this.saveUser = function(user){		

		var defer = $q.defer();
		return $http.post('http://admin:admin123@localhost:8080/users',JSON.stringify(user))		
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};
})