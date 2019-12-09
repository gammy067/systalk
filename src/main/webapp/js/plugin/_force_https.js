  
  var force_https = true;
  
  if(force_https){
	
		var full_url = window.location.href;
		var targetProtocol = "https:";	
		var url_domin_name = window.location.href.substring(window.location.protocol.length);
		
		var redirect_url = false;
		
		if(url_domin_name.startsWith("//systalk-digital")){
			url_domin_name = "//www." + url_domin_name.substring(2);		
			redirect_url = true;
		}
		
		if (window.location.protocol != targetProtocol){
			redirect_url = true;		
		}
		if(redirect_url){
			full_url = targetProtocol + url_domin_name;
			window.location.href = full_url;
		}
  }
  
  
  
