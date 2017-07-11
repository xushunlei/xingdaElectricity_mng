jQuery(document).ready(function($){
	$(".headermenu li").removeClass('current');
	$(".headermenu li").eq(0).addClass('current');
	
	/*	
	///// SWITCHING LIST FROM 3 COLUMNS TO 2 COLUMN LIST /////
	function rearrangeShortcuts() {
		if(jQuery(window).width() < 430) {
			if(jQuery('.shortcuts li.one_half').length == 0) {
				var count = 0;
				jQuery('.shortcuts li').removeAttr('class');
				jQuery('.shortcuts li').each(function(){
					jQuery(this).addClass('one_half');
					if(count%2 != 0) jQuery(this).addClass('last');
					count++;
				});	
			}
		} else {
			if(jQuery('.shortcuts li.one_half').length > 0) {
				jQuery('.shortcuts li').removeAttr('class');
			}
		}
	}
	
	rearrangeShortcuts();
	
	///// ON RESIZE WINDOW /////
	jQuery(window).resize(function(){
		rearrangeShortcuts();
	});
	*/

});
