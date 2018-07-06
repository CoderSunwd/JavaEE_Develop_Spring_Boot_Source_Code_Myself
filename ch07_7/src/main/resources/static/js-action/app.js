var actionApp = angular.module('actionApp', ['ngRoute']); // 定义模块 actionApp， 并依赖于路由模块 ngRoute

actionApp.config(['$routeProvider', function($routeProvider){ // 配置路由，并注入$routeProvider来配置
	$routeProvider.when('/oper', { // /oper为路由名称
		controller: 'View1Controller', // controller定义的是路由的控制器名称
		templateUrl: 'views/view1.html', // templateUrl 定义的是视图的真正地址
	}).when('/directive', {
		controller: 'View2Controller',
		templateUrl: 'views/view2.html',
	});
}]);
