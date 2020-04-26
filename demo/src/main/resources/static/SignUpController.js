var SignUpController = function($scope,$rootScope,SignUpService,$state) {

	$scope.user={};
	$scope.saveDetails = function(userForm) {
		if(userForm.$valid) {
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
		else
			{
			alert("Enter Valid Details");
			}
	}
}
appName.controller("SignUpController", SignUpController);


