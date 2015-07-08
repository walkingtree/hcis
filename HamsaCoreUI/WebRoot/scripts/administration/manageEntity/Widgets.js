Ext.namespace("administration.manageEntity");

administration.manageEntity.Widgets = Ext.extend(Object,{
	constructor: function(){
	
	this.entityTypeCombo = new Ext.form.ComboBox({
		fieldLabel : entityMsg.entitytype,
		mode : 'local',
		store : loadEntityCbx(),
		displayField : 'description',
		valueField : 'code',
		triggerAction : 'all',
		forceSelection : true,
		name:'entityType',
		required:true,
		allowBlank:false,
		anchor:'96%'
	});
	
	this.entityType = new Ext.form.ComboBox({
		fieldLabel : entityMsg.entitytype,
		mode : 'local',
		store : loadEntityCbx(),
		displayField : 'description',
		valueField : 'code',
		triggerAction : 'all',
		forceSelection : true,
		hiddenName:'entityType',
		anchor:'96%'
	});
	
	this.titleCombo = new Ext.form.ComboBox({
		fieldLabel : entityMsg.title,
		mode : 'local',
		store : loadTitleCbx(),
		displayField : 'description',
		valueField : 'code',
		triggerAction : 'all',
		forceSelection : true,
		required:true,
		allowBlank:false,
		name:'entityTitle',
		anchor:'96%'
	});
		
		this.entitytNameTxf = new Ext.form.TextField({
			name: 'entityName',
			fieldLabel :entityMsg.name,
			anchor:'96%',
			required:true,
			allowBlank: false
		});
		
		this.entitytName = new Ext.form.TextField({
			name: 'entityName',
			fieldLabel :entityMsg.name,
			anchor:'96%'
			
		});
		
		this.genderCombo = new Ext.form.ComboBox({
			fieldLabel : entityMsg.sex,
			mode : 'local',
			store : loadGenderCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			forceSelection : true,
			name:'entityGender',
			required:true,
			allowBlank:false,
			anchor:'96%'
		});
		
		this.gender = new Ext.form.ComboBox({
			fieldLabel : entityMsg.sex,
			mode : 'local',
			store : loadGenderCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			forceSelection : true,
			name:'entityGender',
			anchor:'96%'
		});
		
		this.dateofbirthFld = new Ext.form.WTCDateField({
			name: 'entityDateOfBirth',
			fieldLabel : entityMsg.dateofbirth,
			anchor:'96%',
			maxValue:new Date()
		});	
		
		this.entityFromJoiningDate = new Ext.form.WTCDateField({
			name: 'entityJoiningdate',
			fieldLabel : entityMsg.joiningdate,
			anchor:'96%',
			maxValue:new Date()
		});	
		
		this.isActiveChk = new Ext.form.Checkbox({
			boxLabel: entityMsg.parmanententity,
			labelSeperator :'',
			name:'parmanententityChk',
			checked:true,
			anchor:'96%'
		});
		
	},
	
	getIsActiveChk: function(){
		return this.isActiveChk;
	},
	
	getEntityTypeCombo: function(){
		return this.entityTypeCombo;
	},
	
	getEntityType: function(){
		return this.entityType;
	},

	getTitleCombo:function(){
		return this.titleCombo;
	},
	
	getEntitytNameTxf:function(){
	    return this.entitytNameTxf;
	},
	
	getEntitytName:function(){
	    return this.entitytName;
	},
	
	getGenderCombo:function(){
		return this.genderCombo;
	},
	
	getGender:function(){
		return this.gender;
	},
	
	getDateofbirthFld:function(){
		return this.dateofbirthFld;
	},
	getEntityFromJoiningDate:function(){
		return this.entityFromJoiningDate;
	}
	
});