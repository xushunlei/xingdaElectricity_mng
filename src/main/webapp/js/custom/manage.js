
jQuery(document).ready(function($){	
	
	$("#pagename").text();
	
	
	///// CHECKBOX TRANSFORM /////
	jQuery('input:checkbox').uniform();

	
	
	///// CHECK ALL /////
	jQuery('.checkall, .checkall2').click(function(){
		if(jQuery(this).is(':checked')) {
			jQuery(this).parents('table')
						.find('input:checkbox')
						.each(function(){
									   
				jQuery(this).attr('checked',true);
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will add class "selected" in a row when checked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').addClass('selected');
			});
		} else {
			jQuery(this).parents('table')
						.find('input:checkbox')
						.each(function(){
									   
				jQuery(this).attr('checked',false);
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will remove class "selected" in a row when unchecked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').removeClass('selected');
			});
		}
		
		//this is needed to remain the checkbox in transformed (uniform) state
		jQuery.uniform.update();
	});
	
	
	///// EACH CHECKBOX CLICK EVENT /////
	jQuery('.mailinbox input:checkbox').each(function(){
		jQuery(this).click(function(){
			if(!jQuery(this).is(':checked')) {
				
				//this will hide trash icon only when there are no selected row
				var hidetrash = true;
				jQuery('.mailinbox tbody input:checkbox').each(function(){
					if(jQuery(this).is(':checked'))
						hidetrash = false;
				});
				
				if(hidetrash)
					jQuery('.dropdown_label, .reportspam, .msgtrash').hide();
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will remove class "selected" in a row when checked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').removeClass('selected');
					
			} else {
				
				//we use css({display:block}) instead of show() because show() is 
				//using display: inline to show element
				jQuery('.dropdown_label, .reportspam, .msgtrash').css({display: 'block'});
				
				//check if this checkbox is part of the list(tbody) not in the header/footer (thead/tfoot)
				//this will add class "selected" in a row when checked
				if(jQuery(this).parents('tbody').length > 0)
					jQuery(this).parents('tr').addClass('selected');
			
			}
		});
	});
	
	
	///// SHOW DROP DOWN BUTTON /////
	jQuery('.dropdown').each(function(){
		var t = jQuery(this);
		t.find('a.dropdown_label').click(function(){
			if(!t.hasClass('open')) {
				var h = t.height();
				t.find('ul').show().css({top: h+2+'px'});	
				t.addClass('open');
			} else {
				t.find('ul').hide();	
				t.removeClass('open');				   
			}
			return false;
		});
		
	});
	
	///// HIDE DROP DOWN BUTTON /////
	jQuery(document).click(function(){
		jQuery('.dropdown').removeClass('open').find('ul').hide();
	});
	jQuery("#do_enable").click(function(){
		var str="";
		var idss=document.getElementsByName("cuser");
		for(var i=0;i<idss.length;i++){
			if(idss[i].checked){
				str=str+idss[i].value+",";
			}
		}
		str=str.substring(0, str.length-1);
		console.log(str);
		window.location="admin/enable?userid="+str;
	});
	
	jQuery("#do_disable").click(function(){
		var str="";
		var idss=document.getElementsByName("cuser");
		for(var i=0;i<idss.length;i++){
			if(idss[i].checked){
				str=str+idss[i].value+",";
			}
		}
		str=str.substring(0, str.length-1);
		window.location="admin/disable?userid="+str;
	});
	jQuery("#keyword").focus(function(){
		jQuery("#keyword").addClass("my_cleancolor");
		var searchText=jQuery("#keyword").val();
		if(searchText=="请输入关键字"){
			jQuery("#keyword").val("");
		}
	});
	jQuery("#keyword").blur(function(){
		jQuery("#keyword").removeClass("my_cleancolor");
	});
});
