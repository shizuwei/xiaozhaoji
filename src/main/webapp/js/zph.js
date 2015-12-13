var curAreaObj = null;
var curCityObj = null;
var curCityId = 0;
var curXJHData = null;
var hint = null;
var hint_list = null;
var trueinput = null;
var falseinput = null;
var searchBtn = null; 

$(function(){
	 hint = $('.hint');
	 hint_list = $('.hint-list');
	 trueinput = $("#txt_Keywords");
	 falseinput = $("#text_Keywords");
	 searchBtn = $('#searchBtn'); 
	 searchFbFun(trueinput,falseinput);
	 subSearchFun(trueinput,falseinput,searchBtn);
});
 
 
function citySelected(curCityObj)
{
		
		if(!curCityObj)
		{
			return;
			
		}else
		{
			$('#cur_city').text(curCityObj.name);
			
			var param = {city_id:curCityObj.id,page:0};
			
			curCityId = curCityObj.id;
			 $("#mask_list").show();
			$.ajax({
			   type: "POST",
			   url: url_getZph,
			   data:param,
			   success: function(data){
				  
				 curXJHData = data;
				 //displayXJH(1);
				 $("#pagination").pagination(data.total, {
						items_per_page:data.items_per_page,
						prev_text:'上一页',
						next_text:'下一页',
						 num_edge_entries: 1,
						    num_display_entries: 1,			
						callback: function(page,jq){
							 
							displayXJH(page,curXJHData);
							return false;
						}
					 });
				}
			});
		}
}

function areaSelected(curAreaObj)
{
	if(curAreaObj == null) 
	{
		$(".curArea").text("地区：全部");
		$('#colleges').empty();
		$('.productFilter dl:first').hide();
		$('#citys').empty();
		$(areas).each(function(i0,area)
		{
			$(area.city).each(function(i1,city)
			{
				if(i0 == 0 && i1 == 0){
					{
						$('#citys').append("<span rel='"+city.id+"' class='select'>"+city.name+"</span>");
						
					}
				}else{
					$('#citys').append("<span rel='"+city.id+"'>"+city.name+"</span>");
				}
			});
		});
		
		
	}else
	{
		$(".productFilter dl").show();
		//$('.productFilter dl:first').show();
		$('#citys').empty();
		 
		$(curAreaObj.city).each(function(i,city)
		{
			if(i == 0){
				{
					$('#citys').append("<span rel='"+city.id+"' class='select'>"+city.name+"</span>");
					
				}
			}else{
				$('#citys').append("<span rel='"+city.id+"'>"+city.name+"</span>");
			}
		});
		$(".curArea").text("地区："+curAreaObj.name);
		citySelected(curAreaObj.city[0]);
		
	}
	
	$("#citys span").click(function(){
		
		$(this).addClass("select").siblings().removeClass('select');
		var cityName = $(this).text();
		var cityID = $(this).attr("rel");
		 
		$(areas).each(function(i,area)
		{
			$(area.city).each(function(i,ctiy)
			{
				if(ctiy.id == parseInt(cityID)) 
				{
					 
					curCityObj = ctiy;	
					//expand();
					$('#cur_city').text(ctiy.name);
				}
			});
	
		});
	
			
		citySelected(curCityObj);
		
	});
}

$(function(){
	//ip = "116.211.225.2";
	getAreaAndCity(ip,function(area,city){
		curAreaObj = area;
		curCityObj = city;
		init();
	})
	
})
function init(){
	  
		areas.push({id:0,name:'全部'});
		
		areaSelected(curAreaObj);
		
		$('.allAreaList').empty();
		
		$(areas).each(function(i,area)
		{
			$('.allAreaList').append("<li rel='"+area.id+"'>"+area.name+"</li>");
		});
		
		$(".allAreaList li").click(function(){
			var areaName = $(this).text();
			var areaID = $(this).attr("rel");
			$(".allArea").hide();
			if(model.name == 'zph')
			{
			 
				$(areas).each(function(i,area)
				{
					if(area.id == parseInt(areaID)) 
					{
					 
						curAreaObj = area;	
					};
				});
				
				if(parseInt(areaID) == 0) {
				
					curAreaObj = null;
				}
				
				areaSelected(curAreaObj);	
			}
		});
		
		//areaSelected(areas[0]);

		$(".curArea").click(function(){
		  $(".allArea").toggle();
		})
		$("body").click(function(event) {
			  if (event.target.className != "curArea"){
				  $(".allArea").hide();
			  }
		});
		$(".allArea").click(function(e) {    
			e = e || window.event;
			if(document.all){
			  e.cancelBubble = true;
			}else{
			  e.stopPropagation();
			}
		});
		
		$(".productFilter dl dd span").click(function(){
			$(this).addClass("select").siblings().removeClass('select');
		})
		
		//折叠展开
		
		if(model.name == 'zph')
		{
			//$("span.righttop").click(expand);
			$("span.righttop").parent().click(expand);
		}
 }
 
function expand()
{
	var obj = $("span.righttop");
	if(obj.text().indexOf('展开')>0) {
		obj.text('点击折叠 ▼'); 
		$(".productFilter dl").show();
	}
	else{
		obj.text('点击展开 ▲');
		if($('#citys').children().length>0)
			$(".productFilter dl").hide();
	}	
}


 function renderXJH(data)
 {
	 	$('#content_tbl tr:has(td)').remove();
		$(data.rows).each(function(i,row)
		{
			if(model.name == 'zph')
			{
				var tr = "<tr><td class='myNm'><a id='"+row.id+"' title='"+row.title+"'>"+SHORT(row.title,20)+"</a></td><td>"+SHORT(row.add_time+row.hold_time,25)+"</td><td>"+SHORT(row.address,10)+"</td></tr>"
				$('#content_tbl').append(tr);
			}else if(model.name == 'xyzp')
			{
				var tr = "<tr><td class='myNm'><a id='"+row.id+"' title='"+row.title+"'>"+SHORT(row.title,20)+"</a></td><td>"+SHORT(row.add_time,25)+"</td><td>"+SHORT(row.address,10)+"</td></tr>"
				$('#content_tbl').append(tr);
			}
		
		});
		$("#content_tbl tr a").addClass('titleAnchor');
		$("#content_tbl tr a").attr('target', '_blank');
		$("#content_tbl tr a").click(function(e){
			 
			openURL (url_ZphContent+'?id='+$(this).attr('id')); 
		});
		//$("#content_tbl tr td:nth-child(1),#content_tbl tr th:nth-child(1)").css('width','30%');
		//$("#content_tbl tr td:nth-child(2),#content_tbl tr th:nth-child(2)").css('width','20%');
		//$("#content_tbl tr td:nth-child(3),#content_tbl tr th:nth-child(3)").css('width','30%');
		//$("#content_tbl tr td:nth-child(4),#content_tbl tr th:nth-child(4)").css('width','10%');
		//$("#content_tbl tr td:nth-child(5),#content_tbl tr th:nth-child(5)").css('width','10%');
		
		$("#content_tbl tr").hover(function(){
			$(this).css("background","#f2f2f2")
		},function(){
			$(this).css("background","#fff")
		})	
		
		 $("#mask_list").hide();
	 
 }
 
 function displayXJH(ipage,data){
	
	if(ipage != data.page)
	{
		var param = {city_id:curCityId,page:ipage};
		$.ajax({
		   type: "POST",
		   url: url_getZph,
		   data:param,
		   success: function(data){
			
			 curXJHData = data;
			 renderXJH(curXJHData);
		   }
		});
	}else 
		renderXJH(curXJHData);
}
 


