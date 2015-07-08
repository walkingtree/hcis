Ext.namespace("LIMS.testTemplate.configure");

LIMS.testTemplate.configure.DynamicWidgetPanel = Ext.extend(Ext.Panel,{
	draggable : false,
	customProperty : 'Test custom',
	initComponent : function getWidget(){
		var mainThis = this;
		this.widgetData = this.initialConfig.data ;
		this.widget = null; 
		if(this.widgetData.widgetType == 'string'){
			this.widget = new Ext.form.TextField({
				name:this.widgetData.widgetCode,
				anchor:'98%',
				fieldLabel:this.widgetData.widgetLabel,
				disabled : false,
				value : this.widgetData.widgetValue,
				readOnly : true,
				sectionCode : this.widgetData.sectionCode,
				isEdited : false
			});
		}else if(this.widgetData.widgetType == 'MVL'){
				
			var record = Ext.data.Record.create([
					    {name: 'code'},         
					    {name: 'description'}   
					]);
			var stores = new Ext.data.Store({
				reader:new Ext.data.ArrayReader({
						    id: 0                   
						}, record)
			});
			if(!Ext.isEmpty(this.widgetData.MVLValueList)){
				for(var j =0;j<this.widgetData.MVLValueList.length;j++){
					var recordData = stores.recordType;
					var codeDesc = this.widgetData.MVLValueList[j];
					var data = new recordData({
						code:codeDesc.code,
						description:codeDesc.description
					});
					stores.add(data);
				}
			}
			
			this.widget = new Ext.form.ComboBox({
				hiddenName:this.widgetData.widgetCode,
				store:stores,
				anchor:'98%',
				fieldLabel:this.widgetData.widgetLabel,
				value : this.widgetData.widgetValue,
				displayField:'description',
				valueField:'code',
				triggerAction:'all',
				forceSelection:true,
				mode:'local',
				readOnly : true,
				sectionCode : this.widgetData.sectionCode,	
				isEdited : false
			});
	
		}else if(this.widgetData.widgetType == 'number'){
			if( !Ext.isEmpty( this.widgetData.minValue ) || !Ext.isEmpty( this.widgetData.maxValue )){
				this.numberField = new Ext.form.NumberField({
					name:this.widgetData.widgetCode,
					anchor:'98%',
					readOnly : true,
					fieldLabel:this.widgetData.widgetLabel,
					value : parseInt( this.widgetData.widgetValue ),
					sectionCode : this.widgetData.sectionCode,
					isEdited : false
				});
				
				this.minValueTxf = new Ext.form.TextField({
					   value : this.widgetData.minValue,
					   anchor : '90%',
					   fieldLabel : 'Min val',
					   disabled : true
					   
			   	   });
				
				this.maxValueTxf = new Ext.form.TextField({
					   value : this.widgetData.maxValue,
					   anchor : '90%',
					   fieldLabel : 'Max val',
					   disabled : true
			   	   });
	
				
				this.widget = new Ext.Panel({
					border : false,
					frame : false,
					layout : 'column',
					items:[
					   {
						   columnWidth : .5,
						   layout : 'form',
						   labelWidth : 110, 
						   items:this.numberField
					   },   
					   {
						   columnWidth : .25,
						   layout : 'form',
						   labelWidth : 50,
						   items:this.minValueTxf
					   },
					   {
						   columnWidth : .25,
						   layout : 'form',
						   labelWidth : 50,
						   items:this.maxValueTxf
					   }   
					]
				});
			}
			else{
				this.widget = new Ext.form.NumberField({
					name:this.widgetData.widgetCode,
					anchor:'98%',
					fieldLabel:this.widgetData.widgetLabel,
					value : parseInt( this.widgetData.widgetValue ),
					readOnly : true,
					sectionCode : this.widgetData.sectionCode,
					isEdited : false
				});
			}
			
		}
		
		else if( this.widgetData.widgetType == 'datetime' ){
			this.widget = new Ext.form.WTCDateTimeField({
				name : this.widgetData.widgetCode,
				anchor : '98%',
				fieldLabel : this.widgetData.widgetLabel,
				readOnly : true,
				sectionCode : this.widgetData.sectionCode,
				isEdited : false
			});
		}
		
		else if(this.widgetData.widgetType == 'label'){
			this.widget = new Ext.form.Label({
				name:this.widgetData.widgetCode,
				anchor:'98%',
				fieldLabel:this.widgetData.widgetLabel,
				disabled : false,
				value : this.widgetData.widgetValue,
				readOnly : true,
				sectionCode : this.widgetData.sectionCode,
				isEdited : false
			});
		}
		
		this.delBtn = new Ext.Button({
	        iconCls:'cross_icon',
	        hidden : true,
	        listeners : {
				click : function( thisBtn , e){
					var mainPanel = thisBtn.ownerCt.ownerCt.ownerCt;
					var tmp = mainPanel.getHeight();
					mainPanel.remove( thisBtn.ownerCt.ownerCt );
					if( mainPanel.items.items.length < 2 ){
						mainPanel.setHeight(86);
					}
					else{
						mainPanel.setHeight('auto');
					}
					mainPanel.doLayout();
				}
			}
		});
		
		this.on('render',function(thisPanel){
			thisPanel.body.on('mouseover',function( event ){
				mainThis.getDelButton().show();
			},this);
			thisPanel.body.on('mouseout',function( event ){
				mainThis.getDelButton().hide();
			},this);
		},this);
		
		Ext.applyIf( this,{
			
			layout : 'column',
			items :[ 
				{	
					columnWidth :.9,
					layout : 'form',
					labelWidth : 110,
					items : this.widget
				},
				{
					columnWidth : .1,
					items : this.delBtn
				}
			]
		});
		
		LIMS.testTemplate.configure.DynamicWidgetPanel.superclass.initComponent.apply(this,arguments);
	},
	
	getPanel : function(){
		return this;
	},
	
	getDelButton : function(){
		return this.delBtn;
	}
	
});


	
