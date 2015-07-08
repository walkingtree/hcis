

GridRowActions = function ( actionList ){
	
	var actionArray = new Array();
	
	for( var i=0 ; i< actionList.length ;i++){
		var action = {
			iconCls : actionList[i].iconCls,
			tooltip : actionList[i].tooltip,
			hideIndex : actionList[i].hideIndex
		};
		actionArray.push(action);
	}

	
	var action = new Ext.ux.grid.RowActions({
		header : 'Actions',
		keepSelection : true,
		widthSlope : 50,
		actions : actionArray
	});
	
	return action;
	
};	

