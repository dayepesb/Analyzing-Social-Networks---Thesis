var url1 = "http://localhost:8080/user/login?username=";
$('.message a').click(function() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
});
function create() {
	var username = document.getElementById("user").value;
	var password = document.getElementById("pass").value;
	if (username != "" && password != "") {
		var urlConcat = url1.concat(username, "&password=", password);
		alert(urlConcat);
		$.ajax({
			url : urlConcat
		}).then(function(data) {
			var object = JSON.parse(data);
			var success = object.status;
			alert(success);
			if (success == "successful") {
				location.href = "../html/Money.html?message="+(object.message);
			} else {
			}
		});
	}
}