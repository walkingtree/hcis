
Ext.namespace('Ext.ux');
 
/**
 *
 * @class GMapPanel
 * @extends Ext.Panel
 */
Ext.ux.GMapPanel = Ext.extend(Ext.Panel, {
    initComponent : function(){
        
        var defConfig = {
        	plain: true,
        	zoomLevel: 3,
        	yaw: 180,
        	pitch: 0,
        	zoom: 0,
        	gmapType: 'map',
            border: false
        }
        
        Ext.applyIf(this,defConfig);
        
		Ext.ux.GMapPanel.superclass.initComponent.call(this);        
    },
    afterRender : function(){
        
        var wh = this.ownerCt.getSize();
        Ext.applyIf(this, wh);
        
		Ext.ux.GMapPanel.superclass.afterRender.call(this);	
        
		if (this.gmapType === 'map'){
			this.gmap = new GMap2(this.body.dom);
			var mapControl = new GMapTypeControl();
			this.gmap.addControl(mapControl);
			this.gmap.addControl(new GLargeMapControl());
			map = this.gmap;
		}
		
		if (this.gmapType === 'panorama'){
			this.gmap = new GStreetviewPanorama(this.body.dom);
		}
		
		if (typeof this.addControl === 'object' && this.gmapType === 'map') {
			this.gmap.addControl(this.addControl);
		}
		
		if (typeof this.setCenter === 'object') {
			if (typeof this.setCenter.geoCodeAddr === 'string'){
				this.geoCodeLookup(this.setCenter.geoCodeAddr);
			}else{
				if (this.gmapType === 'map'){
					var point = new GLatLng(this.setCenter.lat,this.setCenter['long']);
					this.gmap.setCenter(point, this.zoomLevel);	
				}
				if (typeof this.setCenter.marker === 'object' && typeof point === 'object'){
					this.addMarker(point,this.setCenter.marker,this.setCenter.marker.clear);
				}
			}
			if (this.gmapType === 'panorama'){
				this.gmap.setLocationAndPOV(new GLatLng(this.setCenter.lat,this.setCenter['long']), {yaw: this.yaw, pitch: this.pitch, zoom: this.zoom});
			}
		}
		
        var dt = new Ext.util.DelayedTask();
        dt.delay(1, function(){
//            this.addMarkers(this.markers);
//            this.addPolylines(this.getpolylines);
        }, this);

	},
    onResize : function(w, h){

        if (typeof this.gmap == 'object') {
            this.gmap.checkResize();
        }
		
		Ext.ux.GMapPanel.superclass.onResize.call(this, w, h);

    },
    setSize : function(width, height, animate){
        
        if (typeof this.gmap == 'object') {
            this.gmap.checkResize();
        }
		
		Ext.ux.GMapPanel.superclass.setSize.call(this, width, height, animate);
        
    },
	getMap: function(){
		
		return this.gmap;
		
	},
	addMarkers: function(markers) {
		if (Ext.isArray(markers)){
			var testThis = this;
			var index =0;
			var itemList =null;
			var test =1;
			var test1 = 1;
			window.setInterval(function(){
				if (index < markers.length) {
					if(markers[index].marker.title=='192.168.1.5'){
						var mkr_point = new GLatLng(markers[index].lat,markers[index]['long']);
						testThis.addMarker(mkr_point,markers[index].marker,false,markers[index].setCenter,1,config.device1+'position Id:'+test);
						index++;
						test++;
					}else {
						var mkr_point = new GLatLng(markers[index].lat,markers[index]['long']);
						testThis.addMarker(mkr_point,markers[index].marker,false,markers[index].setCenter,0,config.device2+'position Id:'+test1);
						index++;
						test1++;
					}
					
					
				}
			
			},2000);
		}
	},
	addMarker: function(point, marker, clear, center,index,titleMarkar){
		
		if(index !='undefined' && index == 0){
			markerOptions = { icon:G_DEFAULT_ICON, title:titleMarkar,labelText:titleMarkar };
		}else{
			var blueIcon = new GIcon(G_DEFAULT_ICON);
			blueIcon.image = "http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png";
			markerOptions = { icon:blueIcon, title:titleMarkar };
		}

		if (clear === true){
			this.gmap.clearOverlays();
		}
        if (center === true) {
            this.gmap.setCenter(point, this.zoomLevel);
        }
        
		var mark = new GMarker(point,markerOptions);
   		this.gmap.addOverlay(mark);

	},
	geoCodeLookup : function(addr) {
		
		this.geocoder = new GClientGeocoder();
		this.geocoder.getLocations(addr, this.addAddressToMap.createDelegate(this));
		
	},
    addAddressToMap : function(response) {
		
		if (!response || response.Status.code != 200) {
			Ext.MessageBox.alert('Error', 'Code '+response.Status.code+' Error Returned');
  		} else {
    		place = response.Placemark[0];
			addressinfo = place.AddressDetails;
			accuracy = addressinfo.Accuracy;
			if (accuracy === 0) {
				Ext.MessageBox.alert('Unable to Locate Address', 'Unable to Locate the Address you provided');
			}else{
				if (accuracy < 7) {
					Ext.MessageBox.alert('Address Accuracy', 'The address provided has a low accuracy.<br><br>Level '+accuracy+' Accuracy (8 = Exact Match, 1 = Vague Match)');
				}else{
	        		point = new GLatLng(place.Point.coordinates[1], place.Point.coordinates[0]);
					if (typeof this.setCenter.marker === 'object' && typeof point === 'object'){
						this.addMarker(point,this.setCenter.marker,this.setCenter.marker.clear,true);
					}
				}
			}
	  	}
		
	},
	addPolylines: function(markers) {
//		var latLogList = new Array();
//		var objList = new Array();
//		if(Ext.isArray(markers)) {
//			for(var i=0; i<markers.length;i++) {
//				var markerItem = markers[i];
//				var latLng = new GLatLng(markerItem.lat,markerItem['long']);
//					var obj ={
//						latLong:latLng,
//						title:markerItem.marker.title
//					};
//					latLogList.push(obj);
//			}
//		}
//			var testThis = this;
//			var index =0;
//			window.setInterval(function(){
//				if (index < markers.length) {
//					var latLogList1 = new Array();
//					if(index == 0){
//						latLogList1.push(latLogList[0]);
//						latLogList1.push(latLogList[1])
//						testThis.addPolyline(latLogList1);
//					}else {
//						var test = index;
//						latLogList1.push(latLogList[index]);
//						latLogList1.push(latLogList[test+1])
//						testThis.addPolyline(latLogList1)
//					}	
//					index++;
//				}
//			},2000);
//			for(var j =0; j<latLogList.length;j++) {
//				var latLogList1 = new Array();
//				if(j == 0){
//					latLogList1.push(latLogList[0]);
//					latLogList1.push(latLogList[1])
//					this.addPolyline(latLogList1);
//				}else {
//					latLogList1.push(latLogList[j]);
//					latLogList1.push(latLogList[j+1])
//					this.addPolyline(latLogList1)
//				}	
//			}
	},
	addPolyline: function(list) {
//		var polyOptions = {geodesic:true};
//		var dt = new Ext.util.DelayedTask();
//		var items = new Array();
//		var color;
//		if(list[0].title == list[1].title) {
//			items.push(list[0].latLong);
//			items.push(list[1].latLong);
//			if(list[0].title == '192.168.1.5'){
//				color = config.color1;
//			}else{
//				color =config.color2;
//			}
//			dt.delay(1000, function(){
//	        	var polyline = new GPolyline(items, color, 10, 1, polyOptions);
//				this.gmap.addOverlay(polyline);
//        	}, this);
//		}
	}
});

Ext.reg('gmappanel',Ext.ux.GMapPanel);