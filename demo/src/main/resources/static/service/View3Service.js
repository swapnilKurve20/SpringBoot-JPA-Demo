appName.service("View3Service", function($http,$q){

	this.getUserById = function(loggedInUserId){		

		var defer = $q.defer();
		return $http.get('http://localhost:8080/users?id='+loggedInUserId)		
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};
})