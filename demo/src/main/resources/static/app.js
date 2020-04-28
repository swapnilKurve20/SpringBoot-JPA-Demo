var appName = angular.module('appName', ['ui.router']);

appName.constant('RESOURCES', (function() {
	  var protocol = 'http://';
	return {
		  PROD_DOMAIN: protocol + 'localhost:8080'
	  }
	})());

appName.config(function($stateProvider,$locationProvider) {//,$urlProvider

	$stateProvider
	.state("view1", {
		url:'/view1',
		templateUrl: 'view1.html',
		controller : 'View1Controller'
	})
	.state("profile", {
		url:'/profile',
		templateUrl: 'view2.html',
		controller : 'View2Controller'
	})
	.state("posts", {
		url:'/posts',
		templateUrl: 'view3.html',
		controller : 'View3Controller'
	})
	.state("signup", {
		url:'/signup',
		templateUrl: 'signup.html',
		controller : 'SignUpController'
	})
	
	//$urlProvider.otherwise("/");

});