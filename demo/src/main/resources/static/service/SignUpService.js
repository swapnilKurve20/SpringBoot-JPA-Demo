appName.service("SignUpService", function($http,$q){

	this.saveUser = function(user){		

		var defer = $q.defer();	
		return $http.post('http://localhost:8080/users',JSON.stringify(user))
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};
	
	this.getUserById = function(userId){		

		var defer = $q.defer();
		return $http.get('http://localhost:8080/users?id='+userId)		
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};
})