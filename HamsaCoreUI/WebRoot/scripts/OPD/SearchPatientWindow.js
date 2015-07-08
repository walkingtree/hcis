function getSearchPatientPanel( form , mainThis) {
	var searchPanel = new OPD.managePatient.PatientMgmnt();
	searchPanel.gridPanel.getPanel().getTopToolbar().hide();
	searchPanel.bottomButtonsPanel.hide();
	searchPanel.gridPanel.getPanel().on('cellclick',function(grid , rowIndex, columnIndex, e){
		var selectedRowModel = grid.getSelectionModel();
		var rowCount = selectedRowModel.getCount();
		if( rowCount === 1 ){
			okBtn.enable();
		}else{
			okBtn.disable();
		}
	});
	
	var okBtn = new Ext.Button({
		text: msg.ok,
		disabled:true,
		iconCls:'accept-icon ',
		handler:function(){
			var selectedRowModel = searchPanel.gridPanel.getPanel().getSelectionModel();
			var selectedRowData = selectedRowModel.getSelections();
			var rowCount = selectedRowModel.getCount();
			if(rowCount === 1){
				form.setValues( selectedRowData[0].data );
				if( !Ext.isEmpty( mainThis )){
					if( !Ext.isEmpty( mainThis.viewPatinetDetailsBtn )){
						mainThis.viewPatinetDetailsBtn.enable();
					}
					if( !Ext.isEmpty( mainThis.otBookingWidgets)){
						mainThis.otBookingWidgets.getSurgeryCbx().getStore().load({params:{start:0, limit:999}, arg:[null, null,selectedRowData[0].data.patientId,"SURGICAL"]});
					}
				}
				window.close();		
			}
		}
	});
	var window = new Ext.Window({
			modal:true,
			resizable:false,
			title:msg.findpatientwindow,
			width:'80%',
			height:'80%',
			frame:true,
			closeAction: 'hide',
			items:searchPanel.getPanel(),
			y:10,
			buttons:[
				okBtn,
				{
					text: msg.btn_cancel,
					iconCls:'cross_icon',
					scope:this,
					handler: function(){
						window.close();
					}
				}
			]
		});
		
		return window;
}