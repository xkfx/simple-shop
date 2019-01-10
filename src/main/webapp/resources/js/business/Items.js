/**
 * 可能需要的依赖：
 * 		<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
		<script src="resources/js/Iconst.js"></script>
		<script src="resources/js/utils/customUtils.js"></script>
		<script src="resources/js/utils/PageSet.js"></script>
		<script src="resources/js/RelationContainer.js"></script>
 */
class Items {
	static queryAll4customer(uid = 1000) {
		return Items.specificQuery({
			uid: uid,
		});
	}

	/**
	 * rpc,getByuid方法
	 */
	static queryAll4merchant(uid = 1000, status = 0) {
		return Items.specificQuery({
			uid: uid,
			status: status,
		});
	}

	/**
	 * @Deprecated
	 * 发起默认请求，返回一个promise
	 */
	static defaultQuery(curPage = 0, uid = 1000) {
		return Items.specificQuery({
			uid: uid,
			status: 1,
			curPage: curPage,
			pageSize: 10,
		});
	}

	static specificQuery(attr) {
		let urlTail = obj2url(attr);
		return new Promise(resolve => {
			$.ajax({
				url: BASE_URL_TWO + "/items?" + urlTail,
				type: "get",
				dataType: "json",
				crossDomain: true,
			}).done(result => {
				resolve(result);
			});
		});
	}

	/**
	 * rpc,创建新商品
	 */
	static saveItem(uid, name, price, quantity) {
		let item = {
			uid: uid,
			name: name,
			price: price,
			quantity: quantity,
		};
		return new Promise((resolve, reject) => {
			$.ajax({
				url: BASE_URL_TWO + "/items",
				type: "post",
				data: item,
				dataType: "json",
				crossDomain: true,
			}).done(data => {
				resolve(data);
			}).fail(e => {
				reject(e.responseJSON);
			});
		});
	}

	/**
	 * rpc方法,更新商品信息
	 */
	static update(id, name, price, quantity) {
		let item = {
			id: id,
			name: name,
			price: price,
			quantity: quantity,
		};
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": BASE_URL_TWO + "/items?" + obj2url(item),
			"method": "PUT",
			"headers": {
				"Content-Type": "application/x-www-form-urlencoded",
			},
		}
		return Items.wrapAjaxWithPromise(settings);
	}

	/**
	 * rpc方法,商品上架/下架
	 */
	static updateStatus(id, status) {
		let item = {
			id: id,
			status: status,
		};
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": BASE_URL_TWO + "/items?" + obj2url(item),
			"method": "PUT",
			"headers": {
				"Content-Type": "application/x-www-form-urlencoded",
			},
		}
		return Items.wrapAjaxWithPromise(settings);
	}

	/**
	 * rpc,删除商品
	 */
	static deleteItem(id) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": BASE_URL_TWO + "/items?id=" + id,
			"method": "DELETE",
		}
		return Items.wrapAjaxWithPromise(settings);
	}

	/**
	 * 简单封装ajax
	 */
	static wrapAjaxWithPromise(settings) {
		return new Promise((resolve, reject) => {
			$.ajax(settings).done(result => {
				resolve(result);
			}).fail(e => {
				reject(e.responseJSON);
			});;
		});
	}

	/**
	 * 本地方法,生成默认ui组件
	 */
	static toTable(items = [], id = "table-items") {
		let table = elt("table", {
			border: 1,
			id: id,
		}, ...items.map(item => {
			return elt("tr", {}, ...object2tds(item));
		}));
		// table.insertBefore(elt("th", {}, ...Object.keys(items[0])), table.firstChild);
		return table;
	}
}