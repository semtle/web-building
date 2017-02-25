define(['cms/module', 'common/context' ], function(cmsModule) {
	return cmsModule.factory('articleService', [ '$http', 'util', function($http, util) {
		/**
		 * 提交服务器前，将实体数据模型转成平面的表单数据结构
		 */
		function entity2form(e) {
			var f = {};
			f.id = e.id;
			f.title = e.title;
			f.body = e.body;
			f.keywords = e.keywords;
			f.type = e.type;
			return f;
		}
		
		return {
			search : function(query, page) {
				var param = {
					page : page,
					query : query,
				};
				param = util.encodeUrlParams(param);
				return $http.get('cms/article/search' + (param ? '?' + param : ''));
			},
			findArticle : function(id) {
				return $http.get('cms/article/' + id);
			},
			saveArticle : function(article) {
				return $http.post('cms/article', entity2form(article));
			},
			updateArticle : function(id, article) {
				return $http.put('cms/article/' + id, entity2form(article));
			},
			deleteArticle : function(id) {
				return $http['delete']('cms/article/' + id);
			},
		};
	}]);
});