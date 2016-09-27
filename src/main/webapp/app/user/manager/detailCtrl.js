define(['user/module', 'user/manager/service'], function(userModule) {
	return userModule
	.controller('UserDetailCtrl', ['$scope', '$http', '$state', 'userService'
	                         , function($scope, $http, $state, userService) {
		var self = this;
		$scope.getAuthentication();
		self.form = {};// 要提交的表单数据
		self.getDetail = function(id) {
			userService.getUserById(id).success(function(data, status, fun, obj) {
				console.log(data);
				self.detail = data;
				self.form.id = self.detail.id;
				self.form.email = self.detail.email;
				self.form.name = self.detail.name;
				self.form.post = self.detail.post;
				self.form.department = {name : self.detail.department.name};
				self.form.description = self.detail.description;
			})
			.error(function(data, status, fun, obj) {
				console.log(data);
//				location.replace('login');
			});
		};
		self.getDetail($state.params.id);
		
		self.dictionary = {
			'ADMIN' : '系统管理员',
			'EMPLOYEE' : '职员',
			'MANAGER' : '经理',
			'USER' : '普通用户',
			'MALE' : '男',
			'FEMALE' : '女',
			'UNSPECIFIED' : '未知',
		};
		/**
		 * 在详情中展示字符串，有的值是对象，所以需要处理
		 */
		self.getValue = function(k, v) {
			var result, i;
			switch (k) {
				case 'department':
					result = v.name;
					break;
				case 'subsidiary':
					result = v.country + ' ' + v.province + ' ' + v.city;
					break;
				case 'iconSrc':
					result = '';
					break;
				case 'gender':
					result = self.dictionary[v];
					break;
				case 'authorities':
					temp = [];
					for (i = 0; i < v.length; i++) {
						temp.push(self.dictionary[v[i]]);
						result = temp.join('，');
					}
					break;
				default:
					result = v;
				break;
			}
			return result;
		};
		
		self.modal = {
			open : false,
			title : '编辑用户信息',
			type : '',
			whenConfirm : function() {
				userService.update(self.form);
				$state.go('user.detail', { id : self.form.id }, { reload : true });
			},
		};
		self.edit = function() {
			self.modal.open = true;
		};
	}]);
});