var curAreaObj = null;
var curCityObj = null;
var curColID = 0;
var curXJHData = null;
var hint = null;
var hint_list = null;
var trueinput = null;
var falseinput = null;
var searchBtn = null;

$(function() {

	hint = $('.hint');
	hint_list = $('.hint-list');
	trueinput = $("#txt_Keywords");
	falseinput = $("#text_Keywords");
	searchBtn = $('#searchBtn');
	searchFbFun(trueinput, falseinput);
	subSearchFun(trueinput, falseinput, searchBtn);

	$(".curArea").click(function() {
		$(".allArea").toggle();
	})

	$("#content_tbl tr").hover(function() {
		$(this).css("background", "#f2f2f2")
	}, function() {
		$(this).css("background", "#fff")
	})

	// $(".productFilter dl dd span").click(function() {
	// $(this).addClass("select").siblings().removeClass('select');
	// })

	// $(".allArea").hide();

	$(".allArea").click(function(e) {
		e = e || window.event;

		if (document.all) {
			e.cancelBubble = true;
		} else {
			e.stopPropagation();
		}
	});

	$("body").click(function(event) {
		if (event.target.className != "curArea") {
			$(".allArea").hide();
		}
	});
	
	$("span.righttop").parent().click(expand);

});

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
		else $('#colleges').parent().hide();	
	}	
}
