//服务层
app.service('permissionService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../permission/findAll');
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../permission/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../permission/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../permission/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../permission/update',entity );
	}
	//删除
	this.del=function(ids){
        return $http.get("../permission/del?ids="+ids);
	}
	//搜索
	this.search=function(page,rows){
		return $http.get('../permission/search?page='+page+"&rows="+rows);
	}    
	
	this.updateStatus = function(sellerId,status){
		return $http.get('../seller/updateStatus.do?sellerId='+sellerId+"&status="+status);
	}
});
