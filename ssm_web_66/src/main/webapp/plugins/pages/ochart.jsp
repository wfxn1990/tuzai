<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>ECharts</title>
	<!-- 引入 echarts.js -->
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/echarts.min.js"></script>

    <script
            src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<%--<input type="hidden" id="roles" value="${userList}">--%>
	<%--<div  style="width: 600px;height:400px;">--%>
		<%--<c:forEach items="${userList}" var="Sysuser">--%>
			<%--${Sysuser.username}--%>
		<%--</c:forEach>--%>
	<%--</div>--%>

<script type="text/javascript">
    // ["dmin", "zhangsan", "10086", "jack", "rose"]
    $.ajax({
        url : '${pageContext.request.contextPath}/user/findAll2',
        type : 'get',
        xhrFields: {
            //允许接受从服务器端返回的cookie信息 ,默认值为false 也就是说如果必须设置为true的时候 才可以接受cookie
            withCredentials: true
        },
        success:function(result){
            var arr2=new Array();
            var arr3=new Array()

            for (var arr1 in result){
                arr2.push(result[arr1].username)
                arr3.push(result[arr1].id)
            }
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data:arr2

                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: arr3
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        }
    });
    // 基于准备好的dom，初始化echarts实例






</script>
</body>
</html>