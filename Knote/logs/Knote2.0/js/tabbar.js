			var ip ;
			$(function(){
				//获取IP
				ip = getIP();
				//加载个人信息
				me.loadMsg();
				//加载个人笔记
				index.loadMyNote();
				//加载好友
				//chat.loadFrind();
			});

			var me = {
			url:{
				loadMsg:function(){
					return ip+"me/loadMsg";
				}
			},
			loadMsg:function(){
				var url = me.url.loadMsg();
				
				$.ajax({
					type:"get",
					url:url,
					async:true,
					xhrFields:{
						withCredentials:true
					},
					success:function(res){
						if(res.code==1){
						//回填个人信息
						$('#phone').html(res.data.phone);
						$('#head-img').attr("src",res.data.headPath);
					    }else if(res.code==702){
					    	alert("用户失效，请重新登录！");
					    }
					},
					error:function(){
						
					}
				});
			}
		};

			var index ={
				URL:{
					loadMyNote:function(){
						return ip+"note/loadMyNote";
					},
					loadNoteDesc:function(){
						
					}
				},
				//加载自己笔记数据
				loadMyNote:function (){
					var url = index.URL.loadMyNote(); 
					var params = {
					
					};
					$.ajax({
						type:"post",
						url:url,
						async:true,
						xhrFields:{
						    withCredentials:true
						},
						success:function(res){
							console.log(res);
							if(res.code==1){
								commonNote.addMyNote(res.data);
							}else{
								alert(res.msg);
							}
						},
						error:function(){
							
						}
					});
				}
			};



