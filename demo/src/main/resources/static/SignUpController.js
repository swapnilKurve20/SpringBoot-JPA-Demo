var SignUpController = function($scope,$rootScope,SignUpService,$state) {

	$scope.user={};
	$scope.saveDetails = function() {

		$scope.user;
		/*debugger;*/
		SignUpService.saveUser($scope.user).then(function(success) {
			console.log(success);
			$rootScope.loggedInUser = $scope.user
			alert("Sign Up Successful...");
			$state.go('view2');
		},function(failure) {
			alert("Sign Up Failed...");
			console.log(failure);
		});		
	}
}
appName.controller("SignUpController", SignUpController);


