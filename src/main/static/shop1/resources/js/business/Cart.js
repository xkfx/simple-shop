class Cart {
	// 用于封装cart相关的业务方法（rpc+本地服务方法）	
	static getByUid(uid) { // rpc:cart?uid=1000
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": "http://localhost:8080/test/api/v1/carts?uid=" + uid,
			"method": "GET",
		};
		return Cart.wrapAjax(settings);
	}

	static addItem(uid, itemId) { // rpc:cart?uid=1000&itemId=1005
		let cart = {
			uid: uid,
			itemId: itemId,
		};
		let settings = {
			url: BASE_URL + "/carts",
			type: "post",
			data: cart,
			dataType: "json",
			crossDomain: true,
		};
		return Cart.wrapAjax(settings);
	}

	static remove(id) { // rpc:cart?id=1000
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": "http://localhost:8080/test/api/v1/carts?id=" + id,
			"method": "DELETE",
		};
		return Cart.wrapAjax(settings);
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

	static toTable(carts, id = "table-carts") { // local
		carts = carts.map(cart => {
			cart["removeButton"] = elt("button", {
				class: "btn-cart-remove",
			}, "删除");
			cart["removeButton"].dataset.cartId = cart.id;
			return cart;
		});
		let table = elt("table", {
			border: 1,
			id: id,
		}, ...carts.map(cart => {
			return elt("tr", {}, ...object2tds(cart));
		}));
		return table;
	}
}