var view3Controller = function($scope,$rootScope,View3Service) {
	console.log($rootScope.loggedInUserId);
	$scope.loggedInUserId = $rootScope.loggedInUserId;
	$scope.posts = [];
	
		$scope.getUserById = function(loggedInUserId) {
		
			View3Service.getUserById(loggedInUserId).then(function(response) {
				console.log(response.posts);
			$scope.posts = response.posts;
			},function(failure) {
			console.log(failure);
		});	
		
	}
		console.log($scope.loggedInUserId);
		$scope.getUserById($scope.loggedInUserId);
	
}
appName.controller("View3Controller", view3Controller);