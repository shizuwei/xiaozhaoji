function log(a,b){
	
	if(console != undefined){
		
		switch(typeof(b))
		{
		case 'string':console.log(a+b);break;
		case 'object':console.log(a+JSON.stringify(b));break;
		default:console.log(a+b);break;
		}
		
	}else alert(a);
	
}
function openURL(url){
	//document.write();
	//window.open(url);
	//$('body').append("<a id='openURL' target='_blank' href='"+url+"' > </a>");
	 
	//$('#openURL').click();
	//$('#openURL').remove();
	var sUserAgent = navigator.userAgent.toLowerCase();
	var bIsIpad = sUserAgent .match(/ipad/i) == "ipad";    //判断是否为 ipad
	var bIsIphoneOs = sUserAgent .match(/iphone os/i) == "iphone os";//判断是否为iphone os
	var bIsMidp = sUserAgent .match(/midp/i) == "midp";  //判断是否为 midp
	var bIsUc = sUserAgent .match(/ucweb/i) == "ucweb";  //判断是否为 ucweb
	var bIsAndroid = sUserAgent .match(/android/i) == "android";  //判断是否为 android
	var bIsCE = sUserAgent .match(/windows ce/i) == "windows ce";  //判断是否为 windows ce
	var bIsWM = sUserAgent .match(/windows mobile/i) == "windows mobile";
	if(bIsIpad||bIsIphoneOs||bIsMidp||bIsUc||bIsAndroid||bIsCE||bIsWM)
	{
		window.location.href = url;
	}else{
		
		var newA = document.createElement("a");
		newA.id='gg'
		newA.target='_blank';
	    newA.href=url;
	    document.body.appendChild(newA);
	    newA.click();
	    setTimeout(function(){document.body.removeChild(newA);},200);
	    
	}
	
   
}

$(function(){
	
});

$(function(){
	switch (model.name)
	{
		case 'xjh':
			$('#xjh-head').addClass('cur').siblings().removeClass('cur');
			break;
		case 'xjh-content':
			$('#xjh-head').addClass('cur').siblings().removeClass('cur');
			break;
		case 'xyzp':
			$('#xyzp-head').addClass('cur').siblings().removeClass('cur');
			break;
		case 'xyzp-content':
			$('#xyzp-head').addClass('cur').siblings().removeClass('cur');
			break;
		case 'zph':
			$('#zph-head').addClass('cur').siblings().removeClass('cur');
			break;
		case 'zph-content':
			$('#zph-head').addClass('cur').siblings().removeClass('cur');
			break;
		case 'news':
			$('#news-head').addClass('cur').siblings().removeClass('cur');
			break;
		case 'news-content':
			$('#news-head').addClass('cur').siblings().removeClass('cur');
			break;
		default:break;
	}
});

function is_mobile(){
	if ((navigator.userAgent.match(/(iPhone|iPod|Android|ios|iOS|iPad|Backerry|WebOS|Symbian|Windows Phone|Phone)/i)))
		return true;
	 
		return false;
	
	
}

$(function(){
	
	if(model.name == 'news-content')
		{$("#mask_content").hide(10);return;}
	
	
	//$("#content tbody").css('width','100%');
	var children = $("#content").find("*");
	children.removeClass();

	children.removeAttr("style"); //ie,ff均支持
	children.attr("style","");   //ff支持，ie不支持 
	
	$("#content table,#content tbody,#content tr").css({
		'width':'660px'		
	});
	$("#content table, #content th, #content td").css( {
	'margin': '0',
	'padding':'0',
	'border': '1px solid #B6D5F3',
	'line-height': '35px',
	'height': '35px',
	'text-align': 'center',
	'font-size': '13px'
	});
	 
	$("#content table,#content tbody").attr('border','none');
	$("#content table *").css('border','0px');
	$("#content table *").css('margin','0px');
	$("#content table *").css('padding','0px');
	$("#content table").attr('cellpadding','0');
	$("#content table").attr('cellspacing','0');
	$("#content table td,#content table th").css('border','1px solid #ddd');
	$("#content table tr").css('border','0px');
	$("#content table").attr('border-collapse','collapse'); 
	$("#content table").attr('border-spacing','0'); 
	$("#content table ").css({
		'border':'0px',
		'background-color': 'transparent'
	});
	
 	children.css('text-align','');
 //	children.css('line-height','auto');
//	children.css('font-size','');
//	children.css('font-family','');
//	children.css('margin','auto');
//	children.css('padding','auto');
//	children.css('text-indent','2em');
 	//children.css('background-color','#f4f4f4');
//	children.css('width','auto'); 
//	children.attr('align','');
//	children.attr('size','');
	
	$("#content *").css( {
		
		'line-height': '35px',
		'text-align': 'left',
		'font-size': '13px',
		'font-family':'\5FAE\8F6F\96C5\9ED1,\5b8b\4f53,sans-serif,tahoma,arial',
		'text-indent':'2em',
		
	});
	
	if(is_mobile()){
		$("#content p,#content span").css({'background':'#f4f4f4'});
	}
	//$("#content").find("p").removeAttr("style");
	//$("#content").find("span").removeAttr("style");
	
	$("#mask_content").hide(10);
 
})



function distinctArr(arr,attr){
    var  newArr=[];
    if(!arr || arr.length < 2) return arr;
    var len=arr.length
    for(var i = 0; i < len; i++){
        
    	for(var j = 0; j < len; j++){

            if(arr[i][attr] == arr[j][attr] && i != j)
            {
                break;
            }
        }  
    	
        if(j >= len){
            newArr.push(arr[i])
        }
    }
    return newArr;
}

function SHORT(str,len)
{
	try{
		
		if(typeof(str) == 'number' )
		{
			if(str > 999) return '999+'; 
			else return str;
		}
			
		else if(typeof(str) == 'string')
		{
			var l = str.length;
			if(l <= len) return str;
			else{
				if(len > 3)
					return str.substr(0, len - 3)+'...';
			}
		}else return '-'
		
	}catch(e) {return '-'};	
	
	return str;
		
}



function getAreaAndCity(str_ip,fn)
{
	
	var param = {ip : str_ip };
	jQuery.getJSON(url_getIp,param,function(data){
	   
		var curArea=null,curCity=null;
		if(data && data.code != undefined && data.code != null && data.code == 0){
			
			$(data.data).each(function(i,o)
					{
						$(areas).each(function(i,area)
						{
							if(area.name.indexOf(o.region) >= 0 || o.region.indexOf(area.name) >= 0)
							{
								curArea = area;
								$(curArea.city).each(function(i,city)
								{
									if(city.name.indexOf(o.city) >= 0 || o.city.indexOf(city.name) >= 0)
									{
										curCity = city;
										return false;
									}
								});
								return false;
							}
									
						});		
					});
			
		}
		
		
		if(fn)
			fn(curArea, curCity);
	 });
	 
}

//搜索

function searchFbFun(tureInput,falseInput){
   falseInput.focus(function(e) {
      tureInput.show(); 
      falseInput.hide();
      if(tureInput.val().length>0)
    	  hint.show();
      tureInput.focus();
  });
  tureInput.blur(function(e) {                
      if (tureInput.val() == '') {
        falseInput.show();
        tureInput.hide();
        hint.hide();
      }
  }); 
}

function dispHint(){
	
	
	
}
var lastKey='';


function doHint() { 
	  var key = $.trim(trueinput.val()).replace(/[A-Za-z]+$/g, "");
	  if(key && key.length >= 1 && lastKey != key)
	  { 
		  //log('hint key:',key);
			 
		  lastKey = key;
		  hint.show();
		  $.ajax({
			   type: "POST",
			   url: url_hint,
			   data:{key:trueinput.val()},
			   success: function(data){
				 //  log('hint return from server:',data);
					
				   data = distinctArr(data,'title');
				   hint_list.empty();
				   $(data).each(function(i,o){
					   hint_list.append("<dd>"+o.title+"</dd>");
				   });
				   $('.hint-list dd').click(function(){
					   
					   trueinput.val($(this).text());
				   });
				   
				   $('.hint-list-box dd').click(function(){
					   
					   trueinput.val($(this).text());
				   });
				   
				   
			   }
		  }); 
		  
	  }/*else 
		  $('#hint').hide();*/
	  
	 
}

function subSearchFun(tureInput,falseInput,searchButton){
  
	$("body").click(function(event) {
		  if (event.target.className.indexOf('hint') == -1 && event.target.id != 'txt_Keywords'){
			  hint.hide();
		  }
	});
	//$("#txt_Keywords")
	searchButton.attr("href","javascript:void(0);");
  
	tureInput.keydown(function(e) { 
	
	  if($(this).val().length >= 1 /*&&  $('.hint').is(":hidden")*/)  hint.show();
	  if (e.keyCode == 13) { 
		  searchButton.click(); 
		  
	  }
	});
	
	tureInput.click(function(e) { 
		//log('click search ',$(this).val().length);
		  
		  if($(this).val().length >= 1)  hint.show();
	})
  
 
	if("\v"=="v") {
		  //alert("IE");
			tureInput.bind("propertychange", doHint);
		}else{
		 // alert("NO");
			 tureInput.on('input',doHint);
		}
  
  searchButton.bind("click", function(){
	  if (checkKeywords(tureInput,falseInput)) 
	  {
		  var a = encodeURI(tureInput.val());
		  if(model.name == 'xjh' || model.name == 'xjh-content')
		  {
			  openURL (url_search+'?key='+a+'&m='+model.name);
			  
		  }else if(model.name == 'search')
		  {
			  if(search)	  
			  {
				  search(a);
			  }else; 
				 // log('can not find search func.');
		  
		  }else  if(model.name == 'zph' || model.name == 'zph-content')
		  {
			  openURL (url_search+'?key='+a+'&m='+model.name);
			  
		  } else if(model.name == 'news' || model.name == 'news-content')
		  {
			  openURL (url_search+'?key='+a+'&m='+model.name);
			  
		  }
		  
	  }else 
	  {
      	  //window.open (url_search+'?key='+a);
	  }  
  });
}
function checkKeywords(tureInput,falseInput) {
    String.prototype.Trim = function() 
    { 
    	return this.replace(/^\s+|\s+$|(?:^ )+|(?: $)+/g, ""); 
    }
    var keywords = tureInput.val().Trim(); 
    if (keywords == "") { 
      falseInput.show();
      falseInput.attr("style", "background:#F5EDD8");
      setTimeout(function() { falseInput.attr("style", ""); }, 200);
      setTimeout(function() { falseInput.attr("style", "background:#F5EDD8"); }, 300);
      setTimeout(function() { falseInput.attr("style", ""); }, 500);
      return false;
    }    
    return true;
} 