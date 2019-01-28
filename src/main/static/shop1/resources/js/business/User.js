class User {
	static save(username, pwd, type) {
		let user = {
			username: username,
			password: pwd,
			type: type,
		};
		return new Promise((resolve, reject) => {
			$.ajax({
				url: BASE_URL + "/users",
				type: "post",
				data: user,
				dataType: "json",
				crossDomain: true,
			}).done(data => {
				resolve(data);
			}).fail(e => {
				reject(e.responseJSON);
			});
		});
	}

	static query(username, pwd) {
		let user = {
			username: username,
			password: pwd,
		};
		let urlTail = obj2url(user);
		return new Promise((resolve, reject) => {
			$.ajax({
				url: BASE_URL + "/users?" + urlTail,
				type: "get",
				dataType: "json",
				crossDomain: true,
			}).done(data => {
				resolve(data);
			}).fail(e => {
				reject(e.responseJSON);
			});
		});
	}
}