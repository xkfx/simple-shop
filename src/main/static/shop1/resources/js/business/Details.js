class Details {

	static getByMerchantId(merchantId) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/details?merchantId=${merchantId}`,
			"method": "GET",
		}
		return Details.wrapAjax(settings)
	}

	static getByOrderId(oid) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": `http://localhost:8080/test/api/v1/details?oid=${oid}`,
			"method": "GET",
		}
		return Details.wrapAjax(settings)
	}

	static pay(detailId) { // rpc4customer
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": "http://localhost:8080/test/api/v1/details",
			"method": "POST",
			"data": {
				"detailId": detailId,
				"pay": "true",
			},
		}
		return Details.wrapAjax(settings);
	}

	static deliver(detailId, expressUid) {
		let settings = {
			"async": true,
			"crossDomain": true,
			"url": "http://localhost:8080/test/api/v1/details",
			"method": "POST",
			"data": {
				"detailId": detailId,
				"expressUid": expressUid,
			}
		}
		return Details.wrapAjax(settings);
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
}