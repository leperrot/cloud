$(() => {
	function request(type) {
		$.ajax({
			type: 'GET',
			url: 'client.php?do='+type,
			async: true
		})
		.done((res) => {
			$("#response").html(res);
		})
		.fail((err) => {
			$("#error").html(err);
		});
	};

	$("#accounts").click(() => {
		request("accounts");
	});

	$("#approvals").click(() => {
		request("approvals");
	});
})