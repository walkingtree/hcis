Ext.namespace("dashboard");

dashboard.AvailableGadgetLayout = Ext.extend( Ext.Panel, {
	initComponent: function(){
		this.store = new Ext.data.ArrayStore({
		    fields: [
		        {name:'checked'},
		        {name:'image'},
		        {name:'imageText',type:'string'},
		        {name:'title', type: 'string'},
		        {name:'description', type:'string'}
		        
		    ],
		    data:[[true,"images/UserWithoutPhoto.png","image","Inspirational Quotes and Pictures","Displays beautiful pictures and inspirational quotes from the most inspiring people of all time"],
		          [false,"images/UserWithoutPhoto.png","image1","India News, Latest News in India, Live News India, India Breaking News - Times of India","Get the latest breaking news from India on politics and current affairs."],
		          [false,"images/UserWithoutPhoto.png","image2","Word of the Day","Word of the Day - includes word synonyms, usage example"],
		          [true,"images/UserWithoutPhoto.png","image3","Brain Tuner","Get your brain in shape with Brain Tuner, a great math game for all ages. The rules are simple, the faster you go the better you get; Brain tuner is the leading gadget for Serious Gaming on igoogle"],
		          [false,"images/UserWithoutPhoto.png","image3","Brain Tuner","Get your brain in shape with Brain Tuner, a great math game for all ages. The rules are simple, the faster you go the better you get; Brain tuner is the leading gadget for Serious Gaming on igoogle"],
		          [true,"images/UserWithoutPhoto.png","image3","Brain Tuner","Get your brain in shape with Brain Tuner, a great math game for all ages. The rules are simple, the faster you go the better you get; Brain tuner is the leading gadget for Serious Gaming on igoogle"],
		          [false,"images/UserWithoutPhoto.png","image3","Brain Tuner","Get your brain in shape with Brain Tuner, a great math game for all ages. The rules are simple, the faster you go the better you get; Brain tuner is the leading gadget for Serious Gaming on igoogle"],
		          [false,"images/UserWithoutPhoto.png","image3","Brain Tuner","Get your brain in shape with Brain Tuner, a great math game for all ages. The rules are simple, the faster you go the better you get; Brain tuner is the leading gadget for Serious Gaming on igoogle"],
		          [true,"images/UserWithoutPhoto.png","image3","Brain Tuner","Get your brain in shape with Brain Tuner, a great math game for all ages. The rules are simple, the faster you go the better you get; Brain tuner is the leading gadget for Serious Gaming on igoogle"]]
		});

		this.tpl = new Ext.XTemplate(
				'<tpl for=".">',
		        '<div class ="{[values.checked == true ? "d-gadget" : ""]}" > <table> <tbody><tr> <td>', 
		        '<tpl if="this.isChecked( checked )">',
				'<input class="x-tree-node-cb" type="checkbox" checked="checked"/>',
				'</tpl>',
				'<tpl if="this.isChecked( checked ) == false">',
				'<input class="x-tree-node-cb" type="checkbox"/>',
				'</tpl></td>',
		        '<td> <img src={image} alt="{imageText}"  width="100",height="100"></img></td><td> <table><tr><td> <h5 class ="gadget-title">{title}</h5></td></tr><tr><td><p class ="gadget-description">{description}</p></td></tr></table></td>',
		        '</tr> </tbody></table>',
		    	'</div>',
		    	'</tpl>',
			    {
			    	compiled: true,
			    	isChecked: function( checked ){
			    		if( checked ){
			    			return true;
			    		}else{
			    			return false;
			    		}
			    	}
			    }
		);
		
		this.applyGadgetsBtn = new Ext.Button({
			text: dashBoardMessages.applyGadgets,
			iconCls:'dashboard-icon'
		});
		
		this.colseBtn = new Ext.Button({
			text: dashBoardMessages.close,
			iconCls:'cross_icon'
		});
		
		
		Ext.apply(this,{
		    width:'100%',
		    height: 500,
		    autoScroll: true,
		    buttonAlign: 'right',
		    frame: false,
			items: new Ext.DataView({
		        store: this.getDataStore(),
		        tpl: this.getTpl(),
		        autoHeight:true,
		        multiSelect: true,
		        itemSelector:'div.x-tree-node-cb',
		        emptyText: 'No images to display'
		    }),
		    buttons:[
		         this.getApplyGadgetsBtn(),
		         this.getCloseBtn()
            ]
			
		});
		
		dashboard.AvailableGadgetLayout.superclass.initComponent.apply(this,arguments);

	},
	getDataStore: function(){
		return this.store;
	},
	getCloseBtn: function(){
		return this.colseBtn;
	},
	getApplyGadgetsBtn : function(){
		return this.applyGadgetsBtn;
	},
	getTpl : function(){
		return this.tpl;
	}
	
});