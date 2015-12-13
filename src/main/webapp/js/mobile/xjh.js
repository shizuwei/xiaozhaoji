var curAreaObj = null;
var curCityObj = null;
var curColID = 0;
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
function collegeSelected(collegeID)
{
	 
	curColID = collegeID;
	var param = {colid:collegeID,page:0};
	$("#mask_list").show();
	$.ajax({
	   type: "POST",
	   url: url_getXJH,
	   data:param,
	   success: function(data){
		 //console.log('seccess get XJH data:'+JSON.stringify(data));
		 curXJHData = data;
		 //displayXJH(1);
		 $("#pagination").pagination(data.total, {
				items_per_page:data.items_per_page,
				prev_text:'上一页',
				next_text:'下一页',
				num_edge_entries:1,
				num_display_entries: 1,			
				callback: function(page,jq){
					//console.log(page+','+jq);
					displayXJH(page,curXJHData);
					return false;
				}
			 });
		}
	});
}

 
function citySelected(curCityObj)
{
		$('#colleges').empty();
				
		if(!curCityObj || !curCityObj.college)
		{
			$('#colleges').append("<span'>暂时没有此城市的学校信息</span>");
			$('#content_tbl tr:has(td)').remove();
			 $("#pagination").pagination(0,null);
			return;
			
		}else
		{
			$(curCityObj.college).each(function(i,college)
			{
			 
				if(i == 0){
					$('#colleges').append("<span rel='"+college.id+"' class='select'>"+college.name+"</span>");
				}else{
					$('#colleges').append("<span rel='"+college.id+"'>"+college.name+"</span>");
				}
			});
			collegeSelected(curCityObj.college[0].id);
			$("#cur_col").text(curCityObj.college[0].name);
			$("#colleges span").click(function(){
				$(this).addClass("select").siblings().removeClass('select');
				var collegeName = $(this).text();
				var collegeID = $(this).attr("rel");
				collegeSelected(collegeID);
				//$(".productFilter dl").hide();
				 
				$("#cur_col").text(collegeName);
				expand();
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
				$(city.college).each(function(i2,college)
				{
					if(i0 == 0 && i1 == 0 && i2 == 0){
						$('#colleges').append("<span rel='"+college.id+"' class='select'>"+college.name+"</span>");
						collegeSelected(college.id);
						$("#cur_col").text(college.name);
					}else{
						$('#colleges').append("<span rel='"+college.id+"'>"+college.name+"</span>");
					}	
			
				});
			});
		});
		$("#colleges span").click(function(){
			$(this).addClass("select").siblings().removeClass('select');
			var collegeName = $(this).text();
			var collegeID = $(this).attr("rel");
			$(".productFilter dl").hide();
			$("#cur_col").text(collegeName);
			collegeSelected(collegeID);
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
		curCityObj = curAreaObj.city[0];
		if($.inArray(curCityObj, curAreaObj.city) && curCityObj.college[0])
			{
				collegeSelected(curCityObj.college[0].id);
				$("#cur_col").text(curCityObj.college[0].name);
			}
			
		else {
			collegeSelected(curAreaObj.city[0].college[0].id);
			$("#cur_col").text(curCityObj.college[0].name);
		}
		 	
		
	}
	
	$("#citys span").click(function(){
		
		$(this).addClass("select").siblings().removeClass('select');
		var cityName = $(this).text();
		var cityID = $(this).attr("rel");
		 
		$(curAreaObj.city).each(function(i,ctiy)
		{
			if(ctiy.id == parseInt(cityID)) 
			{
				 
				curCityObj = ctiy;	
			}
		});
			
		citySelected(curCityObj);
		
	});
}



$(function(){
	//ip = "116.211.225.2";
	getAreaAndCity(ip,function(area,city){
		curAreaObj = area;
		curCityObj = city;
		//alert('area:'+curAreaObj.name);
		//alert('area:'+curCityObj.name);
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
			if(model.name == 'xjh'||model.name == 'xyzp')
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
		
		if(model.name == 'xjh'|| model.name == 'xyzp')
		{
			//$("span.righttop").click(expand);
			$("span.righttop").parent().click(expand);
		}
		
		
 }
 
function expand()
{
	var obj = $("span.righttop");
	$(".productFilter dl").toggle();
	 
}


 function renderXJH(data)
 {
	 	$('#content_tbl tr:has(td)').remove();
		$(data.rows).each(function(i,row)
		{
			if(model.name == 'xjh')
			{
				var tr = "<tr><td class='myNm'><a id='"+row.id+"' title='"+row.title+"'>"+SHORT(row.title,20)+"</a></td><td>"+SHORT(row.hold_time,10)+"</td><td>"+SHORT(row.address,10)+"</td></tr>"
				$('#content_tbl').append(tr);
			}else if(model.name == 'xyzp')
			{
				var tr = "<tr><td class='myNm'><a id='"+row.id+"' title='"+row.title+"'>"+SHORT(row.title,20)+"</a></td><td>"+SHORT(row.add_time,10)+"</td><td>"+SHORT(row.address,10)+"</td></tr>"
				$('#content_tbl').append(tr);
			}
		
		});
		$("#content_tbl tr a").addClass('titleAnchor');
		$("#content_tbl tr a").attr('target', '_blank');
		$("#content_tbl tr a").click(function(e){
			 
			openURL (url_TalkContent+'?id='+$(this).attr('id')); 
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
	 
	if(ipage != data.page || curColID != data.colid)
	{
		var param = {colid:curColID,page:ipage};
		$.ajax({
		   type: "POST",
		   url: url_getXJH,
		   data:param,
		   success: function(data){
			 
			 curXJHData = data;
			 renderXJH(curXJHData);
			
		   }
		});
	}else  
	renderXJH(curXJHData);
	
 
}
 


