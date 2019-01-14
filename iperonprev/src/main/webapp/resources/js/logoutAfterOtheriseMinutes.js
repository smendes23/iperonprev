$(document).ready(function() {
	$.idleTimer(100000);
	$(document).bind("idle.idleTimer", function() {
		var url = "#{request.contextPath}/logout"
		$(window.document.location).attr('href', url);
	});
});