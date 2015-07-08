// vim: ts=4:sw=4:nu:fdc=4:nospell
/**
 * Ext.ux.form.RowActions Plugin Example Application
 *
 * @author    Ing. Jozef Sakáloš
 * @date      22. March 2008
 * @version   $Id: lovcombo.js 78 2008-06-06 09:22:10Z jozo $
 *
 * @license lovcombo.js is licensed under the terms of
 * the Open Source LGPL 3.0 license.  Commercial use is permitted to the extent
 * that the code/component(s) do NOT become part of another Open Source or Commercially
 * licensed development library or toolkit without explicit permission.
 * 
 * License details: http://www.gnu.org/licenses/lgpl.html
 */

/*global Ext, WebPage, Example, console, window */

Ext.BLANK_IMAGE_URL = './ext/resources/images/default/s.gif';
Ext.ns('Example');
Example.version = '1.0';

// application entry point
Ext.onReady(function() {
    Ext.QuickTips.init();

	var adsenseHost = 
		   'lovcombo.localhost' === window.location.host
		|| 'lovcombo.extjs.eu' === window.location.host
	;
	var page = new WebPage({
		 version:Example.version
		,westContent:'west-content'
		,centerContent:'center-content'
		,adRowContent:adsenseHost ? 'adrow-content' : undefined
	});

	var ads = Ext.getBody().select('div.adsense');
	if(adsenseHost) {
		ads.removeClass('x-hidden');
	}
	else {
		ads.remove();
	}

	var lc = new Ext.ux.form.LovCombo({
		 id:'lovcombo'
		,renderTo:'lovcomboct'
		,width:300
		,hideOnSelect:false
		,maxHeight:200
		,store:[
			 [1, 'Personnel []']
			,[11, 'Finance (33)']
			,[5, 'Door']
			,[6, 'Door Panel']
			,[2, 'Management !77']
			,[25, 'Production']
			,[3, 'Users']
			,[20, 'Window']
			,[21, 'Window Panel']
			,[22, 'Form Panel']
			,[23, 'Grid Panel']
			,[24, 'Data View Panel']
		]
//		,store:new Ext.data.SimpleStore({
//			 id:0
//			,fields:[{name:'id',type:'int'}, 'privGroup']
//			,data:[
//				 [1, 'Personnel']
//				,[11, 'Finance']
//				,[2, 'Management']
//				,[22, 'Production']
//				,[3, 'Users']
//			]
//		})
		,triggerAction:'all'
//		,valueField:'id'
//		,displayField:'privGroup'
		,mode:'local'
	});

	var tf = new Ext.form.TextField({
		 renderTo:'textct'
		,id:'tf'
		,width:300
		,selectOnFocus:false
		,listeners:{
			focus:function() {this.setValue(lc.getValue());}
		}
	});

	// window with grid
//    var win = new Ext.Window({
//         width:600
//        ,id:'agwin'
//        ,height:400
//        ,layout:'fit'
//        ,border:false
//		,plain:true
//        ,closable:false
//        ,title:Ext.get('page-title').dom.innerHTML
//		,items:{xtype:'examplegrid',id:'actiongrid'}
//    });
//    win.show();
});

// eof

