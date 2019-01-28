class Order {

	static listOrders(uid) { // rpc
		var settings = {
			"async": true,
			"crossDomain": true,
			"url": "http://localhost:8080/test/api/v1/orders?uid=" + uid,
			"method": "GET",
		}
		return Order.wrapAjax(settings);
	}

	static getPreOrder(uid) { // rpc
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": BASE_URL + "/orders?pre=true&uid=" + uid,
			"method": "GET",
		};
		return Order.wrapAjax(settings);
	}

	static placeOrder(uid) { // rpc
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": BASE_URL + "/orders",
			"data": {
				uid: uid
			},
			"method": "POST",
		};
		return Order.wrapAjax(settings);
	}

	static wrapAjax(settings) {
		return new Promise((resolve, reject) => {
			$.ajax(settings).done(result => {
				resolve(result);
			}).fail(e => {
				reject(e.responseJSON);
			});;
		});
	}

	static preOrder2Table(preOrder, id = "table-pre-order") {
		let details = preOrder.details;
		details.map(x => {
			delete x.orderId;
			return x;
		});
		let table = elt("table", {
			border: 1,
			id: id,
		}, ...details.map(x => {
			return elt("tr", {}, ...object2tds(x));
		}));
		table.insertBefore(elt("p", {}, "账单"), table.firstChild);
		table.appendChild(elt("p", {}, "合计：" + preOrder.total));
		return table;
	}
}