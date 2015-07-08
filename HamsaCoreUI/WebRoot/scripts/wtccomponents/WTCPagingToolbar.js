Ext.WTCPagingToolbar = Ext.extend(Ext.PagingToolbar, {
	initComponent: function(){
		Ext.apply(this,Ext.apply(this.initialConfig,{pageSize : 10,displayInfo : true}));
		Ext.WTCPagingToolbar.superclass.initComponent.apply( this, arguments );
		
		this.first.handler = function(){
			this.store.reload({params:{start: 0, limit: this.pageSize}});
		};
		this.prev.handler = function(){
			  this.store.reload({params:{start: Math.max(0, this.cursor-this.pageSize), limit: this.pageSize}});
		};
		this.next.handler = function(){
			 this.store.reload({params:{start: this.cursor+this.pageSize, limit: this.pageSize}});
		};
		this.last.handler = function(){
			var total =  this.store.getTotalCount();
           var extra = total % this.pageSize;
           var lastStart = extra ? (total - extra) : total-this.pageSize;
            this.store.reload({params:{start: lastStart, limit: this.pageSize}});
		};
		
		this.refresh.handler = function(){
			 this.store.reload({params:{start: this.cursor,limit:this.pageSize,myparams:'val'}});
		};
		
	}
//	 doLoad : function(start){
//        var o = {}, pn = this.getParams();
//        o[pn.start] = start;
//        o[pn.limit] = this.pageSize;
//        if(this.fireEvent('beforechange', this, o) !== false){
//        	var activePage = this.getPageData().activePage;
//        	var start = ( (activePage - 1)*this.pageSize )+1;
//            this.store.reload({params:{start: start,limit:this.pageSize,myparams:'val'}});
//        }
//    }

});
Ext.reg('wtcpagingtoolbar', Ext.WTCPagingToolbar );