Ext.namespace("OT.register"); 

OT.register.OTNotes = Ext.extend(Ext.Panel, {
	initComponent : function(){
		this.frame = true;
		this.patientSurgeryId = null;
		
		this.patientNameTxf= new Ext.form.TextField({
			name : 'patientName',
			fieldLabel : "Patient name",
			anchor : '90%'
		});

		this.surgeryNameTxf= new Ext.form.TextField({
			name : 'surgery',
			fieldLabel : "Surgery ",
			anchor : '90%'
		});

		this.surgeonNameTxf= new Ext.form.TextField({
			name : 'surgeon',
			fieldLabel : "Surgeon",
			anchor : '90%'
		});
		
		this.otNotesFormPanel = new Ext.form.FormPanel({
			border : false,
			items : [
		         this.patientNameTxf,
		         this.surgeryNameTxf,
		         this.surgeonNameTxf
	         ]
		});
		
		this.otNotesFieldsPanel = new OT.register.OTNotesFieldsPanel({widgetList : this.initialConfig.otNotesBM});
		
		this.buttonsPanel = new utils.ButtonsPanel();
		this.buttonsPanel.getSaveBtn().enable();
		this.buttonsPanel.getSaveBtn().on('click',function(){
			this.saveBtnClicked();
		},this);

		this.buttonsPanel.getResetBtn().on('click',function(){
			this.resetBtnClicked();
		},this);

		Ext.applyIf(this,{
			items : [
		         this.otNotesFormPanel,
		         this.otNotesFieldsPanel,
		         this.buttonsPanel
			]
		});
		OT.register.OTNotes.superclass.initComponent.apply(this,arguments);
	},
	
	saveBtnClicked : function(){
		var This = this;
		
		var otNotesFieldsBMList = this.otNotesFieldsPanel.getValuesAsOTNotesFieldsBMList();
		
		var otNotesBM = {
			patientSurgeryId : this.patientSurgeryId,
			userId : getAuthorizedUserInfo().userName,
			otNotesFieldsBMList :otNotesFieldsBMList	
		}
		
		SurgeryManager.saveOtNotes( otNotesBM , function(){
			This.initialConfig.otRegisterGrid.otNotesWindow.close();
			This.initialConfig.otRegisterGrid.loadData();
		});
		
	},
	
	resetBtnClicked : function(){
		this.otNotesFieldsPanel.reset();
	},
	
	setValues : function( config ){
		this.otNotesFormPanel.getForm().setValues( config );
		this.patientSurgeryId = config.patientSurgeryId;
	}
	
});