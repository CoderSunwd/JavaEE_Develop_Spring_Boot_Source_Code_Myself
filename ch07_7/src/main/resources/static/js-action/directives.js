actionApp.directive('datePicker', function(){ // 定义一个指令名为datePicker
	return {
		restrict: 'AC', // 限制为属性指令和样式指令
		link: function(scope, elem, attrs){ // 使用link方法来定义指令，在link方法内可使用当前scope、当前元素及元素属性
			elem.datepicker(); // 初始化jqueryui的datePicker（jquery的写法是 $('#id').datePicker()
		}
	};
});