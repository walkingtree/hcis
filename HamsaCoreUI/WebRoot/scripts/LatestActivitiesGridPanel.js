LatestActivitiesGridPanel = Ext.extend(Ext.Panel, {
   	  title : 'Latest activities',
      layout : 'column',
      border : false,
      frame : false,
      initComponent : function() {

            this.record = Ext.data.Record.create( [ {
                  name :'code',
                  mapping :'code'
            }, {
                  name :'eventDesc',
                  mapping :'eventDesc'
            } ]);

            this.gridColumns = [{
                  header :"Latest events",
                  dataIndex :'eventDesc',
                  sortable :true
            } ];
            

            this.gridPnl = new Ext.grid.GridPanel( {
			  border : false,
              height : 185,
			  frame : false,
              stripeRows :true,
              autoScroll :true,
              remoteSort :true,
              hideHeaders : true,
              store : [],
              viewConfig : {
                    forceFit :true
              },
              columns : this.gridColumns
            });

        Ext.applyIf(this, {
    	  items: [
               {
                     columnWidth : 1,
                     items :[this.gridPnl]
               }
			]            
        });

        LatestActivitiesGridPanel.superclass.initComponent.apply(this, arguments);
      }
});

Ext.reg('latest-activities-grid-panel', LatestActivitiesGridPanel);