/**
 * 可能需要的依赖：
 * 		<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
		<script src="resources/js/Iconst.js"></script>
		<script src="resources/js/utils/customUtils.js"></script>
		<script src="resources/js/utils/PageSet.js"></script>
		<script src="resources/js/RelationContainer.js"></script>
 */
class Transports {
	/**
	 * 发起默认请求，返回一个promise
	 */
	static defaultQuery(start=1, offset=10, uid=1000) {
		return Transports.specificQuery({
			uid: uid,
			start: start,
			offset: offset,
		});
	}
	
	static specificQuery(attr) {
		let urlTail = obj2url(attr);
		return new Promise(resolve => {
			$.ajax({
				url: BASE_URL + "/transports?" + urlTail,
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
	static toTable(transports=[], id="table-transports") {
		let table = elt("table", {
			border: 1,
			id: id,
		}, ...transports.map(transport => {
			return elt("tr", {}, ...object2tds(transport));
		}));
		return table;		
	}	
}
