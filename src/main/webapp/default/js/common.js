function is_mobile(){
	if ((navigator.userAgent.match(/(iPhone|iPod|Android|ios|iOS|iPad|Backerry|WebOS|Symbian|Windows Phone|Phone)/i)))
		return true;
	else
		return false;
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

$(function(){
	//$("#content tbody").css('width','100%');
	
	if(!$("#content")) return;
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