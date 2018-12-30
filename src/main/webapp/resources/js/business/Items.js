class Items {
	/**
	 * 发起默认请求，返回一个promise
	 */
	static defaultQuery(curPage=0, uid=1000) {
		return Items.specificQuery({
			uid: uid,
			status: 1,
			curPage: curPage,
			pageSize: 7,
		});
	}
	
	static specificQuery(attr) {
		let urlTail = obj2url(attr);
		return new Promise(resolve => {
			$.ajax({
				url: BASE_URL + "/items?" + urlTail,
				type: "get",
				dataType: "json",
				crossDomain: true,
			}).done(result => {
				resolve(result);
			});
		});		
	}
	
	/**
	 * 生成默认ui组件
	 */
	static toTable(items=[], id="table-items") {
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
