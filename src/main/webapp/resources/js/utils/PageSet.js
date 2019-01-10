class PageSet {
	/**
	 * Example：
	 * let pgSet = new PageSet(data, 5);
	 * pgSet.getPage(); // => [x, x, x, x, x]
	 * if (pgSet.next()) doSomething ...
	 */
	constructor(data, pageSize) {
		this.data = data; // 实际数据
		this.pageSize = pageSize; // 单页大小
		this.numberOfPages = Math.ceil(data.length / pageSize); // 页面总数
		this.curPage = 1; // 页码从1开始！
	}
	
	/**
	 * 翻到下一页。
	 * 返回值：true成功翻页&false已经到末页
	 */
	next() {
		if (this.curPage < this.numberOfPages) {
			this.curPage++;
			return true;
		} else {
			return false;
		}		
	}
	
	/**
	 * 翻到前一页。
	 * 返回值：true成功翻页&false已经到首页
	 */
	prev() {
		if (this.curPage > 1) {
			this.curPage--;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 最主要的方法，获取当前页面数据！
	 */
	getPage() {
		let start = (this.curPage - 1) * this.pageSize;
		let end = start + this.pageSize;
		return this.data.slice(start, end);
	}
	
	/**
	 * 返回页面总数
	 */
	getTotal() {
		return this.numberOfPages;
	}

	/**
	 * 返回当前页码
	 */
	getNo() {
		return this.curPage;
	}
	
	setNo(no) {
		this.curPage = no;
	}
}
