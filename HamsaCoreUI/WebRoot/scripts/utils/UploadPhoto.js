
UploadPhotoPanel = Ext.extend(Object,{
	constructor: function(config){
//		this.uploadPhotoBtn = new Ext.Button({
//			text:msg.upload,
//			scope:this,
//			hidden:!Ext.isEmpty(config)&& config.type,//type is a flag which has the value true or false
//			style:'margin-left:35px;margin-top:1px;',
//			handler:function(){
//           		var fileUploader  =	new FileUploader(
//					{width: 500,modal : true}, 
//					this.onPhotoUploadSuccessCallback
//				);
//				fileUploader.show();
//            }
//		});
		
	 	if( Ext.isEmpty(config) ){
	 		config = { }
	 	}
	 	
	 	this.imageName = null;
	 	this.imagePath = null;
		
//	 	this.uploadPhotoBtn = {};

	 	this.photoBox = new Ext.BoxComponent({
//			id: 'photo-box',
			name:'personaldetailsDoctorPic',
			style:'padding-bottom:5px',
			autoShow : true,
		    autoEl : {
				tag : 'img',
				src : 'images/UserWithoutPhoto.png',
				style : 'width:130px;height:150px'
			}
		});
		this.panel = new Ext.form.FieldSet({
			layout:'column',
			height:'100%',
			border: true,
			defaults:{ columnWidth:1,labelWidth: 60, labelAlign:'top' },
			items:[	
		         {
			           layout:'form',
			           border:false,
			           items:this.photoBox
		         }
			]
		});
		
		if( config.showButton || config.mode == 'edit'){		
			this.uploadPhotoBtn =  this.getUploadPhotoBtn();
			this.panel.add(this.uploadPhotoBtn);
		}	
		
		this.photoBox.on('render',function( thisPanel ){
			if(config.mode == 'edit' && !Ext.isEmpty(config.imagePropertyBM )){
				if( !Ext.isEmpty( config.imagePropertyBM.bufferedImage )){ 
					dwr.util.setValue(thisPanel.getId(), config.imagePropertyBM.bufferedImage);
					this.imageName = config.imagePropertyBM.fileName;
				}
			}
		},this);
	},
	getPanel: function(){
		return this.panel;
	},
	getUploadPhotoBtn : function(config){
		if(Ext.isEmpty(config)){
			config = { }
		}
		var mainThis = this;
		var uploadPhotoBtn = new Ext.ux.form.FileUploadField({
	        buttonOnly: true,
	        buttonText:'Change photo',
	        width: 150,
	        name:'photoPath',
	        listeners: {
	            'fileselected': function(fb, v){

	            	var fileUpload = dwr.util.getValue(fb.fileInput.dom.id);

	            	FileUploader.uploadFile(fileUpload, function( imagePropertyBM ){
	            		dwr.util.setValue(mainThis.photoBox.getId(), imagePropertyBM.bufferedImage);
	            		mainThis.imageName = imagePropertyBM.fileName;
	            		mainThis.imagePath = imagePropertyBM.filePath;
	            		mainThis.panel.remove( mainThis.uploadPhotoBtn );
	            		mainThis.uploadPhotoBtn = mainThis.getUploadPhotoBtn();
	            		mainThis.panel.add( mainThis.uploadPhotoBtn );
	            		mainThis.panel.doLayout();
	            	});
	            }
	        }
	    });
	    
	    return uploadPhotoBtn;
	},
	reset : function( config ){
		if(Ext.isEmpty( config )){
			config = { };
		}
		this.photoBox.el.dom.src = 'images/UserWithoutPhoto.png';
		if( !Ext.isEmpty(config.imagePropertyBM )){
			if( !Ext.isEmpty(config.imagePropertyBM.bufferedImage )){
				dwr.util.setValue(this.photoBox.getId(), config.imagePropertyBM.bufferedImage);
			}
		}
//		else{
		this.imageName = null;
		this.imagePath = null;
//		}	
	},
	getImageName : function(){
		return this.imageName;
	},
	getImagePath : function(){
		return this.imagePath;
	}
})
