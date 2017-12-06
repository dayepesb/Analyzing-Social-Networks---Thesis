var url = "http://localhost:8080/user/save?username=";
$('.message a').click(function() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
});
function create() {
	var username = document.getElementById("user").value;
	var password = document.getElementById("password").value;
	if (username != "" && password != "") {
		var urlConcat = url.concat(username, "&password=", password);
		$.ajax({
			url : urlConcat
		}).then(function(data) {
			var object = JSON.parse(data);
			var success = object.status;
			if (success == "successful") {
				alert("user create");
				location.href = "../html/login.html";
			} else {
				aler("user no create");
			}
		});
	}
}