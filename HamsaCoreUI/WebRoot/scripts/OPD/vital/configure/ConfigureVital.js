Ext.namespace("OPD.vital.configure");

OPD.vital.configure.ConfigureVital = Ext.extend(Ext.TabPanel,{
	
	initComponent: function(){
	
//	this.title = "Vital detail";
	this.vitalFieldBMList = this.initialConfig.vitalFieldBMList ;
	this.vitalDynamicPanel = null;
	this.patientId = null;
	this.referenceNumber = null;
	this.referenceType = null;
	this.vitalFieldBMListWithValue = null;
	
	this.insertBtn = new Ext.Button({
		text: "Insert",
		scope : this,
		iconCls : 'add_btn',
		disabled : false
	});

//	this.tbar = [{xtype : 'tbfill'},this.insertBtn];
	
	this.configureVitalGrid = new OPD.vital.configure.ConfigureVitalGrid({vitalFieldBMList : this.initialConfig.vitalFieldBMList} );
	this.configureVitalGrid.setTitle = vitalMsg.vitalDetailttl
	
	this.insertBtn.on('click',function(){
		this.insertBtnClicked();
	},this);

	
	
	this.on('render',function(){
		this.patientVitalGraphPanel = new OPD.vital.configure.VitalGraph({dataList : this.initialConfig.vitalFieldBMList});
		this.patientVitalGraphPanel.setTitle( vitalMsg.vitalGraphttl );
		this.patientVitalGraphPanel.setPatientInfo({referenceNumber:this.referenceNumber,referenceType: this.referenceType, patientId : this.patientId})
		this.add( this.patientVitalGraphPanel );
		this.doLayout();
		var mainThis = this;
		VitalManager.getPatientVitalDetails(mainThis.referenceNumber, mainThis.referenceType, new Date().clearTime(), new Date(),function( vitalFieldBMList ){
			mainThis.patientVitalGraphPanel.setData( vitalFieldBMList );
			mainThis.vitalFieldBMListWithValue = vitalFieldBMList;
			mainThis.configureVitalGrid.setData( vitalFieldBMList );
		});
	},this);
	
	this.vitalPanel = new Ext.Panel({
		border : false,
		title : "Vital detail", 
		frame : true,
		height : 220,
		tbar : [{xtype : 'tbfill'},this.insertBtn],
		items : [
	         this.configureVitalGrid
         ]
	});
	
	Ext.applyIf(this, {
		border : false,
		activeTab : 0,
        items:[ 
            this.vitalPanel
        ] 
    });
		
		OPD.vital.configure.ConfigureVital.superclass.initComponent.apply(this,arguments);
	},
	insertBtnClicked : function(){
		
		var time = new Ext.form.TimeField({
			fieldLabel : "Time",
			format : 'g:i A',
			name : 'time',
			anchor : '90%',
			value : new Date().format('g:i A')
		});	
		
		var timeFieldConfig =  {
			columnWidth : .33,
			layout : 'form',
			items : time
		}

		
		this.vitalDynamicPanel = new OPD.vital.configure.DynamicPanel({widgetList : this.vitalFieldBMList});
		this.vitalDynamicPanel.setTimeField( timeFieldConfig );
		var saveResetBtnPanel = new utils.ButtonsPanel();
		saveResetBtnPanel.getSaveBtn().enable();
		var win = new Ext.Window({
		   	width:700,
		  	modal:true,
		  	title:'Insert vital',
		    resizable:false,
		    items:[this.vitalDynamicPanel,saveResetBtnPanel]
		});
		saveResetBtnPanel.getSaveBtn( win ).on('click',function(){
			this.saveBtnClicked( win );
		},this);

		saveResetBtnPanel.getResetBtn().on('click',function(){
			this.resetBtnClicked();
		},this);

		win.show();

	},
	
	saveBtnClicked : function( win ){
		var mainThis = this;
		var vitalFieldBMList = this.vitalDynamicPanel.getCodeAndValueList();
		var patientVitalBM = {
			
			patientId : parseInt( this.patientId ),
			referenceType : this.referenceType,
			referenceNumber : this.referenceNumber,
			userId : getAuthorizedUserInfo().userName,
			vitalFieldBMList : vitalFieldBMList
		}
		
		VitalManager.addVital( patientVitalBM ,function(){
			win.close();
			VitalManager.getPatientVitalDetails(mainThis.referenceNumber, mainThis.referenceType, null, null,function( vitalFieldBMList ){
				mainThis.vitalFieldBMListWithValue = vitalFieldBMList ;
				mainThis.configureVitalGrid.setData( vitalFieldBMList );
			});

		});
		
	},
	
	resetBtnClicked : function(){
		this.vitalDynamicPanel.reset();
	},
	
	setPatientInfo : function( patientInfo ){
		if( Ext.isEmpty( patientInfo )){
			patientInfo = {};
		}
		this.patientId = patientInfo.patientId;
		this.referenceNumber = patientInfo.referenceNumber;
		this.referenceType = patientInfo.referenceType;
		if( !Ext.isEmpty( this.patientVitalGraphPanel)){
			this.patientVitalGraphPanel.setPatientInfo( patientInfo );
		}
			
	},
	 
	getVitalFieldBMListWithValue : function(){
		return this.vitalFieldBMListWithValue;
	}
	
});
