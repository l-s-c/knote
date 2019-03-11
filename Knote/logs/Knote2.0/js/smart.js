   	var ip;
    	$(function(){
    		ip = getIP();
    		//弹窗询问
    		smart.showAlert();
    	});
    	
    	var smart = {
    		URL:{
    			base64:function(){
    				return ip+"note/base64";
    			}
    		},
    		//选择图片弹窗
    		showAlert:function(){
    			$('#sq').show();
				  $('#cover').show();
    		},
    		// 拍照获取图片  
				getImage:function () {  
				    var c = plus.camera.getCamera();  
				    c.captureImage(function(e) {  
				        plus.io.resolveLocalFileSystemURL(e, function(entry) {  
				            var imgSrc = entry.toLocalURL() + "?version=" + new Date().getTime(); //拿到图片路径  
				           	  smart.setHtml(imgSrc);  
				        }, function(e) {  
				            console.log("读取拍照文件错误：" + e.message);  
				        });  
				    }, function(s) {  
				        console.log("error" + s);  
				    }, {  
				        filename: "_doc/camera/"  
				    })  
				},
				// 从相册中选择图片   
				galleryImg:function (){  
					    plus.gallery.pick( function(e){  
					        for(var i in e.files){  
					            var fileSrc = e.files[i]  
					            smart.setHtml(fileSrc);  
					        }   
					    }, function ( e ) {  
					        console.log( "取消选择图片" );  
					    },{  
					        filter: "image",  
					        multiple: true,  
					        maximum: 1,  								//最大张数
					        system: false,  
					        onmaxed: function() {  
					            plus.nativeUI.alert('只能选择1张图片');  
					        }  
					    });  
				} ,
				//更换背景图 （被编辑的图片）
				setHtml: function(fileSrc){
				  	smart.cancel();
				  	console.log(fileSrc)
						$('#background').attr("src",fileSrc);
						document.getElementById('test').src = fileSrc;
				},
				//取消
				cancel:function(){
						$('#sq').hide();
				  	$('#cover').hide();
				}
    	};
    	
    	
    	  var test = $("#father").get(0); //将jQuery对象转换为dom对象
   	 // 点击转成canvas
    $('.toCanvas').click(function(e) {
        // 调用html2canvas插件
        html2canvas(test).then(function(canvas) {
           // $('.toCanvas').after(canvas);
            //将画布转换为base64
            var base64 = canvas.toDataURL("image/png");
            //保存base64
            var url  = smart.URL.base64();
            $.post(url,{base64:base64},function(res){
            	if(res.code==1){
            		
            	}
            });
           // console.log(base64);
           // document.getElementById('nowPic').src=base64;
        });
    });