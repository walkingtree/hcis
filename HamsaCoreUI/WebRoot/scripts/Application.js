	Ext.onReady(function(){
		var appUser = CookieUtil.get('appuser');
		if(!Ext.isEmpty(appUser)){
			layout = new ApplicationLayout();
			//frontPage is an object which is created in index.jsp for Login
			if( !Ext.isEmpty( frontPage )){
				frontPage.getEl().unmask();
			}
			layout.show();
			
			dwr.engine.setPreHook ( function(){
				/*var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"Please wait..."});
				myMask.show();*/
				layout.getEl().mask('Please wait...');
			});
			
			 dwr.engine.setPostHook( function(){
				/*var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"Please wait..."});
				myMask.hide();*/
			 	layout.getEl().unmask();
			 });
		}
		
	});
	
