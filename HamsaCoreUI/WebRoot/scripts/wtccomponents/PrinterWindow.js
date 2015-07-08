/*  Ext.ux.PrinterFriendly, Version 0.2
 *  (c) 2008 Steffen Hiller (http://www.extjswithrails.com)
 *
 *  License: Ext.ux.PrinterFriendly is licensed under the terms of
 *  the Open Source LGPL 3.0 license.  Commercial use is permitted to the extent
 *  that the code/component(s) do NOT become part of another Open Source or Commercially
 *  licensed development library or toolkit without explicit permission.
 *
 *  License details: http://www.gnu.org/licenses/lgpl.html
 *
 *  This is an extension for the Ext JS Library, for more information see http://www.extjs.com.
 *--------------------------------------------------------------------------*/

Ext.ux.PrinterWindow = Ext.extend(Ext.Window, {
	
  plain: true,
  
  modal: true,
  
  maximizable: false,
  
  initComponent : function() {
	
    this.id = 'printer-window';
    this.width = this.width || 600;
    this.height = this.height || 600;
    this.title = this.title || 'Print Preview';
    this.closeText = this.closeText || 'Close';
    this.printText = this.printText || 'Print';
    
//    this.html = { id: 'printer-iframe', name: 'printer-iframe', tag: 'iframe', width: '100%', height: '100%', frameborder: '0' };
    this.items = this.panel;
    
    this.panel.buttonsPanel.hide();
    
    this.buttons = [{ text: this.closeText, handler: this.close, scope: this }, { text: this.printText, handler: this.printWindow, scope: this }]
                    
    this.on('afterrender',function(){
    	var mainThis = this;
    	
    	this.panel.addClass( 'x-form-display-only-field' );
    	
    	setTimeout(function(){
    		mainThis.getViewMode(mainThis.panel.templateDetailPanel);
    		mainThis.getViewMode(mainThis.panel.patientDetailFieldSet);
    		mainThis.getViewMode(mainThis.panel.doctorDetailFieldSet);
    		mainThis.getViewMode(mainThis.panel.testAttributeFieldSet);
    		mainThis.getViewMode(mainThis.panel.approverDetailPanel);
    	},100);
    	
//    	this.getViewMode(this.panel.templateDetailPanel );
//    	this.getViewMode(this.panel.patientDetailFieldSet );
//    	this.getViewMode(this.panel.doctorDetailFieldSet );
//    	this.getViewMode(this.panel.testAttributeFieldSet );
//    	this.getViewMode(this.panel.approverDetailPanel );
//    	this.addClassToPanel(this.panel );
    },this);                
    
    Ext.ux.PrinterWindow.superclass.initComponent.call(this);
  },
  
  show : function(animateTarget, cb, scope) {
	  
    Ext.ux.PrinterWindow.superclass.show.call(this, animateTarget, cb, scope);
    this.maximize();
//    this.tools.restore.hide();
  },
  
  printWindow : function() {
	  
	 print();

  },
  
//  getIframe : function() {
//    return Ext.get('printer-iframe');
//  },
//  
//  getIframeWindow : function() {
//    return this.getIframe().dom.contentWindow;
//  },
//  
//  // private
//  iframeSrc : function() {
//    var href = window.location.href.split('#')[0];
//    return href + (href.match(/\?/) ? '&' : '?' ) + '_format=printerfriendly';
//  },
  
  getViewMode : function( fieldSet ){
	  var fieldSetItems = fieldSet.items.items;
	  for( var j = 0 ; j < fieldSetItems.length ; j++){
		  var widget = null;
		  if( !Ext.isEmpty( fieldSetItems[j].numberField)){
			  widget = fieldSetItems[j].numberField;
			  fieldSetItems[j].minValueTxf.uxDisplayOnly(true,'textfield-displayonly');
			  fieldSetItems[j].maxValueTxf.uxDisplayOnly(true,'textfield-displayonly');
			  
		  }
		  else if( !Ext.isEmpty( fieldSetItems[j].widget) ){
			  widget = fieldSetItems[j].widget ;
		  }
		  if( widget.getXType() === 'textfield' || 
				  widget.getXType() === 'numberfield' ||
				  widget.getXType() === 'combo' ){
			  widget.uxDisplayOnly(true,'textfield-displayonly');
		  }
	  }
  }
  
//  addStyleSheet : function(){
//	var ss = document.createElement("link");
//	ss.setAttribute("rel", "stylesheet");
//	ss.setAttribute("type", "text/css");
//	ss.setAttribute("href", url);
//	document.getElementsByTagName("body")[0].appendChild(ss);
//
//  },
//  
//  finishIframe : function(){
//	this.iFrameWindow.contentWindow.document.body.appendChild( this.div );  
//  }
  
//  addClassToPanel : function( mainPanel ){
//	  var panel = null;
//	  if( !Ext.isEmpty(  mainPanel.items )){
//		  var panel = mainPanel.items.items;
//		  for( var i = 0 ; i < panel.length ; i++){
//			  if( panel[i].defaultType === 'panel'){
//				  if( panel[i].getXType() === 'fieldset'){
//					  panel[i].addClass( ' x-form-display-only-fieldset' ); 
//				  }
//				  else{
//					  panel[i].addClass( 'x-form-display-only-field' );
//					  panel[i].addClass( 'x-panel-body' );
//				  }  
//				  if( !Ext.isEmpty( panel[i].items)){
//					  this.addClassToPanel( panel[i] );
//				  }
//			  }
//		  }
//	  }
//  }
});












