Ext.namespace("IPD.addClaim");

IPD.addClaim.Claim = Ext.extend(Object, {
	constructor : function(config) {
		Ext.QuickTips.init();
		
		if(Ext.isEmpty(config)){
			config = {};
		}
		var mainThis = this;	
		
		this.record = Ext.data.Record.create([
		  	{name: "code", type: "string"},
		  	{name: "description", type: "string"}
		]);
		
		this.insurerForSponsor = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getInsurerForSponsor, true),
		    reader: new Ext.data.ListRangeReader({totalProperty:'totalSize'}, this.record),
		    remoteSort: true
		 });
		 
		 this.planForInsurer = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getPlanForInsurer, true),
		    reader: new Ext.data.ListRangeReader({totalProperty:'totalSize'}, this.record),
		    remoteSort: true
		 });
		
		 this.insurerForSponsor.load({params:{start:0, limit:9999}, arg:[null]});
		 this.planForInsurer.load({params:{start:0, limit:9999}, arg:[null]});
		 
		 this.insurer = new Ext.form.ComboBox({
			fieldLabel: 'Insurace company',
			store:this.insurerForSponsor,
    		hiddenName:'insurerName',
			mode:'local',
			disabled:true,
			triggerAction: 'all',
			displayField:'code',
			valueField:'code',
	        anchor:'98%',
	        allowBlank:false,
	        required:true,
	        forceSelection : true,
	        listeners:{
	        	select: function( comp, record, index){
					if (this.planForInsurer.getTotalCount() > 0) {
						this.planForInsurer.removeAll();
					}
					this.plan.enable();
					this.plan.clearValue();
					this.planForInsurer.load({params:{start:0, limit:9999}, arg:[record.data.code]});
	    		},
	    		scope: this
	        }
		});
		
		this.plan = new Ext.form.ComboBox({
			fieldLabel: 'Plan',
			store:this.planForInsurer,
    		hiddenName:'planName',
			mode:'local',
			triggerAction: 'all',
			disabled:true,
			displayField:'description',
			valueField:'code',
	        anchor:'98%',
	        allowBlank:false,
	        required:true,
	        forceSelection : true,
	        listeners:{
	        	select:function(comp, record, index){
	        		if(!Ext.isEmpty(comp.getValue())){
	        			InsuranceManager.getInsurancePlanSummary(comp.getValue(),function(InsurancePlanBM){
	        				var config = {
	        						 planCode: InsurancePlanBM.planCode,
									 insurerName: InsurancePlanBM.insurerName,
									 planName: InsurancePlanBM.planName,
									 validFromDt: InsurancePlanBM.validFromDt,
									 validToDt: InsurancePlanBM.validToDt,
									 totalCoverageAmt: InsurancePlanBM.totalCoverageAmt,
									 percentAbsInd: InsurancePlanBM.percentAbsInd
	        				}
	        				mainThis.getPlanPanel(config);
	        			});
	        		}
	        	}
	        }
		});
		
		
		this.claimStatusCbx = {};
		this.approveAmountNbf = {};
		this.finalClaimedAmountNbf = {};
		
		this.maxInsuredAmountNbf = new Ext.form.NumberField({
			fieldLabel: 'Max. insured amount (Rs.)',
			name:'insuranceAmount',
       		readOnly:true,
       		anchor:'98%'
		});
		

		this.requestedAmountNbf = new Ext.form.NumberField({
			fieldLabel: 'Requested amount (Rs.)',
    		name:'requestedAmount',
    		anchor:'98%',
    		allowBlank:false,
    		required:true,
    		listeners:{
    			blur : function(comp){
    				if(!Ext.isEmpty(mainThis.plan.getValue())){
    					if(!Ext.isEmpty(comp.getValue())){
        					InsuranceManager.getInsuranceAmount(
        						mainThis.plan.getValue(),
								comp.getValue(),
								function(insuranceAmount){
									if(!Ext.isEmpty(insuranceAmount)){
										var patientAmount = comp.getValue() - insuranceAmount ;
										if(patientAmount < 0){
											var config = {
												patientAmount : 0,
												insuranceAmount : insuranceAmount
											}
											
											mainThis.panel.getForm().setValues(config);
										}else{
											var config = {
												patientAmount : patientAmount,
												insuranceAmount : insuranceAmount
											}
											
											mainThis.panel.getForm().setValues(config);
										}
									}
								});
        				}
    				}else{
    					alertError("Please select a plan..!");
    				}
    			},
    			change : function(comp , newValue, oldValue){
    				var config = {
						patientAmount : '',
						insuranceAmount : ''
					}
					mainThis.panel.getForm().setValues(config);
    			}
    		}
		});
		
		this.patientAmountNbf = new Ext.form.NumberField({
			fieldLabel: 'Patient amount (Rs.)',
			name:'patientAmount',
    		readOnly:true,
    		anchor:'98%'
		});
		
		
		if(config.mode == 'edit'){
			this.approveAmountNbf = new Ext.form.NumberField({
				fieldLabel: 'Approved amount (Rs.)',
				name:'approvedAmount',
    			anchor:'98%',
    			listeners:{
    				blur:function(comp){
    					if(comp.getValue() > mainThis.requestedAmountNbf.getValue() ||
    					   comp.getValue() > mainThis.maxInsuredAmountNbf.getValue()){
    						Ext.Msg.show({
			 					msg: msg.approvedAmtGrtrThanReqOrMaxInsuredAmt,
							    buttons: Ext.Msg.OK,
							    icon: Ext.MessageBox.ERROR,
							    fn:function(btn){
							    	comp.setValue('');
							    	comp.focus();
							    }
		 					});		
    					}
    				}
    			}
			});
			
			this.finalClaimedAmountNbf = new Ext.form.NumberField({
				fieldLabel: 'Final claimed amount (Rs.)',
				name:'finalClaimAmount',
    			anchor:'98%'
			});
			
	 		this.claimStatusCbx = new Ext.form.ComboBox({
				fieldLabel: 'Claim status',
				store : loadClaimStatusCbx(),
        		hiddenName:'claimStatusCd',
				mode:'local',
				triggerAction: 'all',
				displayField:'description',
				valueField:'code',
		        anchor:'98%',
		        allowBlank:config.mode == 'edit'?false:true,
		        required:config.mode == 'edit'?true:false,
		        disabled : config.mode == 'edit'?true:false,		
		        forceSelection : true,
		        listeners:{
		        	select:function(comp, record, index){
		        		if(comp.getValue()==configs.claimFullyApproved){
		        			mainThis.approveAmountNbf.setValue(mainThis.requestedAmountNbf.getValue());
		        			mainThis.patientAmountNbf.setValue(mainThis.requestedAmountNbf.getValue()-
		        											   mainThis.approveAmountNbf.getValue());
		        			mainThis.approveAmountNbf.getEl().dom.readOnly = true;
		        		}else if(comp.getValue()==configs.claimPartiallyApproved){
		        			mainThis.approveAmountNbf.getEl().dom.readOnly = false;
		        		}
		        	}
		        }
			});
	 	}
		
		 	
	 	this.admissionReqNoCbx = new Ext.form.ComboBox({
	 		fieldLabel: 'ARN',
	 		store:loadAdmReqNoCbx(),
	        hiddenName:'admissionReqNbr',
			mode:'local',
			triggerAction: 'all',
			displayField:'code',
			valueField:'code',
			disabled : ((config.mode=='edit')?true:false),
			allowBlank:false,
			required:true,
	        forceSelection : true,
    		anchor:'98%',
    		listeners:{
	        	select:function(comp, record, index){
	        		if(!Ext.isEmpty(comp.getValue())){
	        			InsuranceManager.getAdmissionInfo(parseInt(comp.getValue()),function(admissionObj){
	        				if(admissionObj != null){
	        					var config = {
	        						estimatedCost : admissionObj.estimatedTreatmentAmnt,
	        						estimationGivenBy : admissionObj.estimatedBy,
	        						patientName : admissionObj.patientName
	        					};
	        					mainThis.panel.getForm().setValues(config);
	        				}
	        			});
	        		}
	        	}
	        }
	 	});
	 	
	 	
		this.sponsorCbx = new Ext.form.ComboBox({
				fieldLabel: 'Sponsor',
        		xtype: 'combo',
        		store:loadSponsorsCbx(),
        		name:'sponsorName',
				mode:'local',
				triggerAction: 'all',
				displayField:'code',
				valueField:'code',
		        anchor:'98%',
		        allowBlank:false,
		        required:true,
		        forceSelection : true,
		        listeners:{
		        	select: function( comp, record, index){
						if (this.insurerForSponsor.getTotalCount() > 0) {
							this.insurerForSponsor.removeAll();
							this.planForInsurer.removeAll();
						}
						this.insurer.enable();
						this.insurer.clearValue();
						this.plan.clearValue();
						this.insurerForSponsor.load({params:{start:0, limit:9999}, arg:[record.data.code]});
		    		},
		    		scope: this
		        }
			}
	 	);
	 	
	 	
	 	this.saveBtn = new Ext.Button({
	 		disabled:true,
	 		text:'Save',
			iconCls : 'save_btn',
			scope:this,
			handler:function(){
				this.handleSaveBtn(config);
			}
	 	});
	 	
	 	this.resetBtn = new Ext.Button({
	 		text:'Reset',
			iconCls : 'cancel_btn',
			scope:this,
			handler:function(){
				this.handleResetBtn(config);
			}
	 	});
	 	
	 	this.planPanel = new Ext.Panel();
	 	
		this.panel = new Ext.form.FormPanel({
			frame:true,
			layout:'column',
			defaults:{labelWidth:150,columnWidth:.3},
			monitorValid: true,
			buttonAlign:'right',
			items:[
				{
					layout:'form',
					columnWidth:.34,
					items:[
						this.admissionReqNoCbx,
						{
							fieldLabel: 'Patient name',
			        		xtype: 'textfield',
			        		readOnly:true,
			        		name:'patientName',
			        		anchor:'98%'
						},
						{
							fieldLabel: 'Estimated cost(Rs.)',
			        		xtype: 'numberfield',
			        		name:'estimatedCost',
			        		readOnly:true,
			        		anchor:'98%'
						},
						{
							fieldLabel: 'Estimation given by',
			        		xtype: 'textfield',
			        		name:'estimationGivenBy',
			        		readOnly:true,
			        		anchor:'98%'
						},
						this.requestedAmountNbf,
						this.approveAmountNbf,
						this.patientAmountNbf,
						this.finalClaimedAmountNbf,
						this.maxInsuredAmountNbf
					]
				},
				{
					layout:'form',
					columnWidth:.34,
					items:[this.sponsorCbx,
						this.insurer,
						this.plan,
						{
							fieldLabel: 'Policy number',
			        		xtype: 'textfield',
			        		name:'policyNbr',
			        		allowBlank:false,
			        		required:true,
			        		anchor:'98%'
						},
						{
							fieldLabel: 'Policy holder name',
			        		xtype: 'textfield',
			        		name:'policyHolderName',
			        		anchor:'98%'
						},
						{
							fieldLabel: 'Valid upto',
			        		xtype: 'wtcdatefield',
			        		name:'policyEffectiveToDt',
			        		allowBlank:false,
			        		required:true,
			        		anchor:'98%'
						},
						this.claimStatusCbx
					]
				},
				{
					layout:'form',
					style:'padding-left:20px',
					items:this.planPanel
				}	
			],
			buttons:[
				this.saveBtn,
				this.resetBtn
			]
		});
		
		this.panel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.saveBtn.enable();
			} else {
				this.saveBtn.disable();
			}
		}, this);
	},
	getPanel : function() {
		return this.panel;
	},
	//setting focus for first element.
	getFocus : function(){
		this.admissionReqNoCbx.focus();
	},
	
	handleSaveBtn : function(config){
		var mainThis = this;
		if(this.panel.getForm().isValid()){
			var claimDetails = this.panel.getForm().getValues();
			claimDetails.createdBy = authorisedUser.userName;
			claimDetails.policyEffectiveToDt = Date.parseDate(claimDetails.policyEffectiveToDt,'d/m/Y');
			claimDetails.planCode = claimDetails.planName;
			
			
			if((!Ext.isEmpty(config)) && (config.mode == 'add')){
				InsuranceManager.createClaimRequest(claimDetails, function(insurerDetailBM){
					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
					Ext.ux.event.Broadcast.publish('closeClaimPanel');					}
			    );
			 }
			 else if((!Ext.isEmpty(config)) && (config.mode == 'edit')){
				claimDetails.requestSequenceNbr = config.selectedRow.requestSequenceNbr;
				claimDetails.claimSubsequenceNbr = config.selectedRow.claimSubsequenceNbr;
				claimDetails.requestDtm = config.selectedRow.requestDtm;
				claimDetails.insurerName = this.insurer.getValue();
//				claimDetails.planCode = config.selectedRow.planCode;
				claimDetails.claimStatusCd = {code : this.claimStatusCbx.getValue()};
				claimDetails.sponsorName = this.sponsorCbx.getValue();
				InsuranceManager.modifyClaimRequests(claimDetails,null,function(){
					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
					Ext.ux.event.Broadcast.publish('closeClaimPanel');
				});
			 
			 	
			 }else if( (!Ext.isEmpty(config)) && (config.isFromAdmission == true ) ){
			 	InsuranceManager.createClaimRequest(claimDetails, function(insurerDetailBM){
					Ext.ux.event.Broadcast.publish('closeClaimPanelFromAdmission');				}
			    );
			 }
			 else{
				InsuranceManager.createClaimRequest(claimDetails,function(){
						mainThis.panel.getForm().reset();
			 			mainThis.planPanel.remove(mainThis.planeFieldSet.getPanel());
						mainThis.planPanel.doLayout();
						
					}
			    );
			 } 
		}else{
			alertError(msg.enterValidData);
		}
	},
	
	handleResetBtn : function(config){
		if(config.mode == 'edit' && !Ext.isEmpty(config)){
			this.panel.getForm().reset();
			this.loadData(config);
		}
		else{
		this.panel.getForm().reset();
		if( !Ext.isEmpty(this.planeFieldSet)){
		this.planPanel.remove(this.planeFieldSet.getPanel());
		}
		this.insurer.disable();
		this.plan.disable();
		this.planPanel.doLayout();
		}
		
	},

	//setting focus for first element.
	getFocus : function(){
		this.admissionReqNoCbx.focus();
		this.planeFieldSet.hide();
	},
	
	getPlanPanel : function(config){
		if(!Ext.isEmpty(this.planPanel.items)){
			this.planPanel.removeAll();
		}
		
		this.planeFieldSet = new IPD.addClaim.plan(config);
		this.planPanel.add(this.planeFieldSet.getPanel());
		this.planPanel.doLayout();
	},
	loadData : function(config){
		var config = config.selectedRow;
		if(config.claimStatusCd != configs.claimCreated){
			this.sponsorCbx.disable();
			this.insurer.disable();
			this.plan.disable();
		}
		
		if(config.claimStatusCd == configs.claimFullyApproved ||
			config.claimStatusCd == configs.claimPartiallyApproved || 
			 config.claimStatusCd == configs.claimRejected){
			this.claimStatusCbx.disable();
		}
		
		this.planForInsurer.reload();

		this.planForInsurer.on('load', function(){
			this.panel.getForm().setValues(config);
			this.plan.setValue(config.planCode);
			this.planForInsurer.events['load'].clearListeners();
		},this);
	
		var tmpThis = this;
//		
//		this.planForInsurer.load(config['insurerName']);
//
		InsuranceManager.getInsurancePlanSummary(config['planCode'],function(InsurancePlanBM){
			var config = {
				 planCode: InsurancePlanBM.planCode,
				 insurerName: InsurancePlanBM.insurerName,
				 planName: InsurancePlanBM.planName,
				 validFromDt: InsurancePlanBM.validFromDt,
				 validToDt: InsurancePlanBM.validToDt,
				 totalCoverageAmt: InsurancePlanBM.totalCoverageAmt,
				 percentAbsInd: InsurancePlanBM.percentAbsInd
			}
			tmpThis.getPlanPanel(config);
		});
		
		
	}
	
});