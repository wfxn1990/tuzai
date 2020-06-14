 //控制层 
app.controller('sellerController' ,function($scope,$controller,sellerService){
	
	$controller('baseController',{$scope:$scope});//继承
	//解决ng-repeat jquery无法获取值

    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		sellerService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}
    $scope.findById = function(id){
        sellerService.findOne(id).success(function(data){
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
       var jj= $scope.entity
		//alert(kk[0].id)
		alert(jj[0])
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
		sellerService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}*/
	
	//查询实体 
	$scope.findOne=function(id){				
		sellerService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象
		if($scope.entity.id!=null){//如果有ID
			serviceObject=sellerService.update( $scope.entity ); //修改  
		}else{
			serviceObject=sellerService.add( $scope.entity  );//增加 
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
    $scope.ids = [];
    // 更新复选框：
    $scope.updateIds = function($event,id){
        // 复选框选中
        if($event.target.checked){
            // 向数组中添加元素
            $scope.ids.push(id);
        }else{
            // 从数组中移除
            var idx = $scope.ids.indexOf(id);
            $scope.ids.splice(idx,1);
        }
    }
	//保存角色
    $scope.save2=function(){
        var serviceObject;//服务层对象
		alert($scope.ids)
		serviceObject=sellerService.addRolesToUser( $scope.entity[0],$scope.ids  );//增加
        serviceObject.success(
            function(response){
                if(response.flag){
                    //重新查询
                    $scope.reloadList();//重新加载
                    $scope.ids = [];
                }else{
                    alert(response.message);
                }
            }
        );
    }

	//批量删除 
	$scope.del=function(){
		//获取选中的复选框			
		sellerService.del( $scope.selectIds ).success(
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
		sellerService.search(page,rows).success(
			function(response){
				$scope.list=response.list;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	$scope.updateStatus = function(sellerId,status){
		sellerService.updateStatus(sellerId,status).success(function(response){
			if(response.flag){
				//重新查询 
	        	$scope.reloadList();//重新加载
			}else{
				alert(response.message);
			}
		});
	}
});	
