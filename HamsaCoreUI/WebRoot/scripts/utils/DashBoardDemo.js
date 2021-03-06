DashBoardDemo = Ext.extend(Ext.Panel, {
	title : 'DashBoard',
    layoutConfig : {
        animate:true
    },
    initComponent : function() {
    
	    var store = new Ext.data.ArrayStore({
	        fields: ['month', 'hits'],
	        data: this.generateData()
	    });
	    
	    this.panel = new Ext.Panel({
	        width: 700,
	        height: 400,
	        renderTo: document.body,
	        title: 'Column Chart with Reload - Hits per Month',
	        items: {
	            xtype: 'columnchart',
	            store: store,
	            yField: 'hits',
	            xField: 'month',
	            xAxis: new Ext.chart.CategoryAxis({
	                title: 'Month'
	            }),
	            yAxis: new Ext.chart.NumericAxis({
	                title: 'Hits'
	            }),
	            extraStyle: {
	               xAxis: {
	                    labelRotation: -90
	                }
	            }
	        }
	    });
    
        Ext.applyIf(this, {
            items: [this.panel]            
        });
        DashBoardDemo.superclass.initComponent.apply(this, arguments);
    },
    
    generateData : function(){
	    var data = [];
	    for(var i = 0; i < 12; ++i){
	        data.push([Date.monthNames[i], (Math.floor(Math.random() *  11) + 1) * 100]);
	    }
	    return data;
    }
});

Ext.reg('dash-board', DashBoardDemo);
