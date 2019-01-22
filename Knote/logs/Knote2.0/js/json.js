/**
 * 读取json文件 访问路径
 */
function getIP(){
			var ip ;
		$.ajax({
			type:'get',
			url:'ip.json',
			dataType:'json',
			async:false,
			success:function(data){	
				ip= data[0].ip;				
			}
		});
	return ip;
}
	