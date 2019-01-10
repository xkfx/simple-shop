class RelationContainer {
	static addRelation(component, action, ievent) {
		component.addEventListener(action, ievent);
	}
}

/**
 * 为了缩短代码用的，没啥特别意义。。。
 * @param {Object} componentId 组件id
 * @param {Object} action 动作
 * @param {Object} ievent 回调函数
 */
function addRel(component, action, ievent) {
	RelationContainer.addRelation(component, action, ievent);
}
