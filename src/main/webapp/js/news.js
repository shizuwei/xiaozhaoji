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
 
 
 

function areaSelected(curAreaObj)
{
	if(curAreaObj == null) 
	{
		$(".curArea").text("地区：全部");

	}else
	{
		$(".curArea").text("地区："+curAreaObj.name);
	}

	 
}

$(function(){
	//ip = "116.211.225.2";
	getAreaAndCity(ip,function(area,city){
		curAreaObj = area;
		//curCityObj = city;
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
 	 
 }
 

 function renderXJH(data)
 {
	   $("#mask_list").hide();
	 	$('#content_tbl tr:has(td)').remove();
		$(data.rows).each(function(i,row)
		{
			var tr = "<tr><td class='myNm'><a id='"+row.id+"' title='"+row.title+"'>"+SHORT(row.title,30)+"</a></td><td>"+SHORT(row.add_time,13)+"</td></tr>"
			$('#content_tbl').append(tr);
		});
		$("#content_tbl tr a").addClass('titleAnchor');
		$("#content_tbl tr a").attr('target', '_blank');
		$("#content_tbl tr a").click(function(e){
		 
			openURL (url_NewsContent+'?id='+$(this).attr('id')); 
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
	 
 }
 
 function displayXJH(ipage,data){
	
	if(ipage != data.page)
	{
		var param = {page:ipage};
		$.ajax({
		   type: "POST",
		   url: url_getNews,
		   data:param,
		   success: function(data){
		 	 curXJHData = data;
			 renderXJH(curXJHData);
		   }
		});
	}else renderXJH(curXJHData);
}
 
 
 $(function(){
	 $("#mask_list").show();
	 var param = {page:0};
		$.ajax({
			   type: "POST",
			   url: url_getNews,
			   data:param,
			   success: function(data){
				 		 curXJHData = data;
				 //displayXJH(1);
				 $("#pagination").pagination(data.total, {
						items_per_page:data.items_per_page,
						prev_text:'上一页',
						next_text:'下一页',
						 num_edge_entries: 1,
						    num_display_entries:1,				
						callback: function(page,jq){
							 		displayXJH(page,curXJHData);
							return false;
						}
					 });
				}
			});
	 
	 
 });


