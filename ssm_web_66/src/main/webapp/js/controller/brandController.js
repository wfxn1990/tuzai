// 定义控制器:
app.controller("brandController",function($scope,$controller,brandService){
	// AngularJS中的继承:伪继承
	$controller('baseController',{$scope:$scope});
	
	// 查询所有的品牌列表的方法:
	$scope.findAll = function(){
		// 向后台发送请求:
		brandService.findAll().success(function(data){
			$scope.list = data;
		});
	}

	// 分页查询
	$scope.findPage = function(page,rows){
		// 向后台发送请求获取数据:
		brandService.findPage(page,rows).success(function(data){
			$scope.paginationConf.totalItems = data.total;
			$scope.list = data.rows;
		});
	}
	
	// 保存品牌的方法:
	$scope.save = function(){
		// 区分是保存还是修改
		var object;
		if($scope.entity.id != null){
			// 更新
			object = brandService.update($scope.entity);
		}else{
			// 保存
			object = brandService.add($scope.entity);
		}
		object.success(function(data){
			// {flag:true,message:xxx}
			// 判断保存是否成功:
			if(data.flag){
				// 保存成功
				alert(data.message);
				$scope.reloadList();
			}else{
				// 保存失败
				alert(data.message);
			}
		});
	}
	
	// 查询一个:
	$scope.findById = function(id){
		brandService.findOne(id).success(function(data){
			// {id:xx,name:yy,firstChar:zz}
			$scope.entity = data;
		});
	}
	
	// 删除品牌:
	$scope.dele = function(){
		brandService.dele($scope.selectIds).success(function(data){
			// 判断保存是否成功:
			if(data.flag==true){
				// 保存成功
				// alert(data.message);
				$scope.reloadList();
				$scope.selectIds = [];
			}else{
				// 保存失败
				alert(data.message);
			}
		});
	}
	
	$scope.searchEntity={};
	
	// 假设定义一个查询的实体：searchEntity
	$scope.search = function(page,rows){
		// 向后台发送请求获取数据:
		brandService.search(page,rows,$scope.searchEntity).success(function(data){
			$scope.paginationConf.totalItems = data.total;
			$scope.list = data.rows;
		});
	}
	
});
