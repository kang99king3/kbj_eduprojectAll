$(function(){
    		//1.
    		$("#selected-plays ul li").addClass("special");
    		//2.
//     		$("tr").each(function(){
//     			$(this).children("td").eq(2).addClass("year");
//     		});
    		$("td:nth-child(3)").addClass("year");
    		
    		$("td:contains(Tragedy)").eq(0).parent().addClass("special");
//    		for (var i = 0; i < $("td").length; i++) {
//    			if($("td").eq(i).text()=="Tragedy"){
//    				$("td").eq(i).parent().attr("class","Tragedy");
//    			}
//			}
    		
    		$("a").parent("li").next().addClass("afterlink");
    		
    		$("a[href$='.pdf']").parents("ul").eq(0).addClass("tragedy");
    	});