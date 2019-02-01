// 定义一些通用的常量
const BASE_URL = "http://localhost:8080/test/api/v1";

// 定义一些自定义的工具函数
const log = console.log.bind(console);
/**************************************************************
 * DOM相关
 *****************************************************/
const qE = id => document.querySelector(id); // qE是query Element的缩写

function elt(name, attrs, ...children) { // ...操作符，把[a,b,c]打散为a,b,c，打散调用children时仍然要...
	let dom = document.createElement(name);
	for(let attr of Object.keys(attrs)) {
		dom.setAttribute(attr, attrs[attr]);
	}
	for(let child of children) {
		if(["string", "number"].indexOf(typeof child) == -1) {
			dom.appendChild(child);
		} else {
			dom.appendChild(document.createTextNode(child));
		}
	}
	return dom;
}

function object2tds(obj) {
	keys = Object.keys(obj);
	return keys.map(k => elt("td", {}, obj[k]));
}

/**************************************************************
 * 字符串操控
 *****************************************************/
/**
 * 将对象转化为url参数
 */
function obj2url(obj) {
	let urlTail = [];
	for(let attr of Object.keys(obj)) {
		urlTail.push(`${attr}=${obj[attr]}`);
	}
	urlTail = urlTail.join("&");
	return urlTail;
}


function getParamFromUrl(paramName) {
	let urL = location.href;
	let params = urL.slice(urL.indexOf("?") + 1);
	params = params.split("&");
	for (let [name, value] of params.map(x => x.split("="))) {
		if (name === paramName) return value;
	}
}


function addRel(component, action, ievent) {
	component.addEventListener(action, ievent);
}