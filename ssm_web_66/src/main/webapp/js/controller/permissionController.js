 //控制层 
app.controller('permissionController' ,function($scope,$controller,permissionService){
	
	$controller('baseController',{$scope:$scope});//继承
	//解决ng-repeat jquery无法获取值

    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		permissionService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}
    $scope.findById = function(id){
        permissionService.findOne(id).success(function(data){
            // {id:xx,name:yy,firstChar:zz}
            $scope.entity = data;
            $scope.arr = data[7];
            $scope.arr6 = data[6];
            $scope.arr8 = data[8];

        });
    }
    $scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
        var ii=$scope.arr8
        var kk=$scope.arr6

        //下面是在table render完成后执行的js
        if(ii.search(",".concat(kk[0].id).concat(","))!=-1) {
            $("#collapse-table td input[type='checkbox']:eq(0)").iCheck("check");
        }
         if(ii.search(",".concat(kk[1].id).concat(","))!=-1) {
            $("#collapse-table td input[type='checkbox']:eq(1)").iCheck("check");
        }

    });
	//分页
	/*$scope.findPage=function(page,rows){
		permissionService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}*/
	
	//查询实体 
	$scope.findOne=function(id){				
		permissionService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象
		if($scope.entity.id!=null){//如果有ID
			serviceObject=permissionService.update( $scope.entity ); //修改  
		}else{
			serviceObject=permissionService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.flag){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);

	}
	
	 
	//批量删除 
	$scope.del=function(){
		//获取选中的复选框			
        permissionService.del( $scope.selectIds ).success(
			function(response){
				if(response.flag){
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}						
			}		
		);				
	}
	
	//$scope.searchEntity={};//定义搜索对象
	
	//搜索
	$scope.search=function(page,rows){			
		permissionService.search(page,rows).success(
			function(response){
				$scope.list=response.list;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	$scope.updateStatus = function(sellerId,status){
        permissionService.updateStatus(sellerId,status).success(function(response){
			if(response.flag){
				//重新查询 
	        	$scope.reloadList();//重新加载
			}else{
				alert(response.message);
			}
		});
	}
});	
