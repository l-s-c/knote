			var ip ;
			$(function(){
				//获取IP
				ip = getIP();
				//加载个人信息
				//	me.loadMsg();
				//加载个人笔记
				//index.loadMyNote();
				//加载好友
				chat.loadFrind();
			});

			var me = {
			url:{
				loadMsg:function(){
					return ip+"me/loadMsg";
				}
			},
			loadMsg:function(){
				var url = me.url.loadMsg();
				$.get(url,function(res){
					if(res.code==1){
						//回填个人信息
						console.log(res);
						$('#phone').html(res.data.phone);
						$('#head').src = res.data.headPath;
					}
				});
			}
		};

			var index ={
				URL:{
					loadMyNote:function(){
						return ip+"note/loadMyNote";
					}
				},
				//加载自己笔记数据
				loadMyNote:function (){
					var url = index.URL.loadMyNote(); 
					var params = {
					
					};
					$.post(url,params,function(res){
						console.log(res);
						if(res.code==1){
							index.addMyNote(res.data);
						}else{
							alert(res.msg);
						}
					});
				},
				//追加数据
				addMyNote:function(data){
					var table = document.getElementById('allMyNote');
					for(var i = 0; i< data.length; i++) {
						var div = document.createElement('div');
						div.className = 'panel panel-default';
						div.innerHTML = 
						'<div class="panel-body" id="'+data[i].id+'">'
						+data[i].noteTitle+'</div><div class="panel-footer"><span>'
						+data[i].label+'</span></div>';
						table.appendChild(div);
					}
				}
			};


var chat = {
	URL:{
		loadFrind:function(){
			return ip+"chat/loadFrind";
		}
	},
	//初始加载，个人好友
	loadFrind:function(){
		var url = chat.URL.loadFrind();
		$.get(url,function(res){
			console.log("cat:"+res);            //TODO
			chat.addFrind(res.data);
		});
	},
	//将个人好友回填界面
	addFrind:function(data){
	 var li  = '	<li class="mui-table-view-cell" id="[phone]" onclick="chat.tranceform(this.id);">'
			+	'		<div class="mui-slider-cell">'
			+	'		<div class="oa-contact-cell mui-table">'
			+	'				<div class="oa-contact-avatar mui-table-cell">'
			+	'					<img src="../images/60x60.gif" />'
			+	'				</div>'
			+	'				<div class="oa-contact-content mui-table-cell">'
			+	'					<div class="mui-clearfix">'
			+	'						<h4 class="oa-contact-name">[phone]</h4>'
			+	'						<span class="oa-contact-position mui-h6">[remark]</span>'
			+	'					</div>'
			+	'					<p class="oa-contact-email mui-h6">'
			+	'						[phone]'
			+	'					</p>'
			+	'				</div>'
			+	'			</div>'
			+	'		</div>'
			+	'	</li>';
		$('#addPeople').empty();
		for(var count = 0;count<data.length;count++){
			var l = li.replace("[phone]",data[count].frPhone)
					  .replace("[phone]",data[count].frPhone)
					  .replace("[remark]",data[count].remark);
			$('#addPeople').append(l);
		}
	},
	//跳转到聊天窗口
	tranceform:function(phone){
		window.location.href="im-chat.html?phone="+phone;
	}
};
