//服务层
app.service('roleService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../role/findAll');
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../role/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../role/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../role/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../role/update',entity );
	}
	//删除
	this.del=function(ids){
        return $http.get("../role/del?ids="+ids);
	}
	//搜索
	this.search=function(page,rows){
		return $http.get('../role/search?page='+page+"&rows="+rows);
	}    
	
	this.updateStatus = function(sellerId,status){
		return $http.get('../seller/updateStatus.do?sellerId='+sellerId+"&status="+status);
	}
});
