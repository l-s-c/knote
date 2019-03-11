var commonNote = {
					//界面初始数据
				addNote:function(data){
					var div = '	<div class="panel panel-default" id="[id]">'
						+	'	<div class="panel-body" onclick="commonNote.chooseNoteDesc([lable],[id])">'
						+	'	[title]'
						+	'	</div>'
						+	'	<div class="panel-footer"><span style="font-size: 10px;"><img style="height:25px;" src="../images/label.png" />  [lableDesc]</span></div>'
						+	'	</div>';
					$('#allNote').empty();
					for(var i=0;i<data.length;i++){
						var d = div.replace("[id]",data[i].id)
								   .replace("[id]",data[i].id)
								   .replace("[title]",data[i].noteTitle)
								   .replace("[lable]",data[i].label)
								   .replace("[lableDesc]",data[i].labelDesc);
						$('#allNote').append(d);
					}
				},
				//个人笔记追加
				addMyNote:function(data){
					var div = '	<div class="panel panel-default" id="[id]">'
						+	'	<div class="panel-body" onclick="commonNote.chooseNoteDesc([lable],[id])">'
						+	'	[title]'
						+	'	</div>'
						+	'	<div class="panel-footer"><span style="font-size: 10px;"><img style="height:25px;" src="../images/label.png" />  [lableDesc]</span></div>'
						+	'	</div>';
					$('#allMyNote').empty();
					for(var i=0;i<data.length;i++){
						var d = div.replace("[id]",data[i].id)
								   .replace("[id]",data[i].id)
								   .replace("[title]",data[i].noteTitle)
								   .replace("[lable]",data[i].label)
								   .replace("[lableDesc]",data[i].labelDesc);
						$('#allMyNote').append(d);
					}
				},
				//追加数据
				addAfterNote:function(data){
					var table = document.getElementById('allMyNote');
					for(var i = 0; i< data.length; i++) {
						var div = document.createElement('div');
						div.className = 'panel panel-default';
						div.innerHTML = 
						'<div onclick="index.chooseNoteDesc('+data[i].label+','+data[i].id+');" class="panel-body" id="'+data[i].id+'">'
						+data[i].noteTitle+'</div><div class="panel-footer"><span style="font-size: 10px;"><img style="height:25px;" src="../images/label.png" />'
						+data[i].labelDesc+'</span></div>';
						table.appendChild(div);
					}
				},
				//打开笔记详情
				chooseNoteDesc:function(labelId,id){
					if(labelId==3){				//备忘录
						window.open('reminder.html?id='+id, 'new', {}, 'slide-in-right', 200);
					}else if(labelId==2||labelId==1){	//自定义笔记或智能笔记
						plus.webview.open('customNote.html?id='+id, 'new', {}, 'slide-in-right', 200);
					}
				}
};
