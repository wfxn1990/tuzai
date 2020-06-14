//服务层
app.service('sellerService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../user/findAll');
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../seller/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../user/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../user/add',entity );
	}
	//给用户添加角色
    this.addRolesToUser=function(id,ids){
        return $http.get('../user/addRolesToUser?id='+id+"&ids="+ids);
    }
	//修改 
	this.update=function(entity){
		return  $http.post('../user/update',entity );
	}
	//删除
	this.del=function(ids){
        return $http.get("../user/del?ids="+ids);
	}
	//搜索
	this.search=function(page,rows){
		return $http.get('../user/search?page='+page+"&rows="+rows);
	}    
	
	this.updateStatus = function(sellerId,status){
		return $http.get('../seller/updateStatus.do?sellerId='+sellerId+"&status="+status);
	}
});
