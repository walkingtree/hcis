Ext.namespace("wtccomponents");

/**
 *  This class is responsible for creating WorkFlow based on the configured data on the database. 
 */

wtccomponents.WorkFlow = Ext.extend(Ext.Panel,{
	frame : true,
	title : "Workflow",
	initComponent : function(){
		/*
		 * This method will create Arrowline(Line with arrow).
		 * This arrowLine will show link between process.
		 */
		Raphael.fn.arrow = function (x1, y1, x2, y2, size) {
			var angle = Math.atan2(x1-x2,y2-y1);
			angle = (angle / (2 * Math.PI)) * 360;
			var arrowPath = this.path("M" + x2 + " " + y2 + " L" + (x2 - size) + " " + (y2 - size) + " L" + (x2 - size) + " " + (y2 + size) + " L" + x2 + " " + y2 ).attr("fill","red").rotate((90+angle),x2,y2);
			var linePath = this.path("M" + x1 + " " + y1 + " L" + x2 + " " + y2);
			return [linePath,arrowPath];
		}; 		
		this.on('render',function( thisPanel ){
			WorkFlowManager.getWorkFlow(1,{
				callback : function( workFlowProcessList ){
					if( !Ext.isEmpty( workFlowProcessList )){
						this.createPanel( workFlowProcessList );
						this.drawLine();
					}
				},
				scope : this
			});
		},this);
		wtccomponents.WorkFlow.superclass.initComponent.call(this,arguments);
	},
	/**
	 *  This method is responsible to create panel with the process.
	 *  
	 */
	
	createPanel : function( workFlowProcessList ){
		var workFlowPanel = this;
		var div = document.createElement("div");
		this.body.dom.appendChild( div );
		this.paper = Raphael(div, 800, 400); 
		var x1 = 50;
		var y1 = 50;
		var height = 50;
		var width = 100;
		this.processList = new Array();
		
		for( var i = 0 ; i < workFlowProcessList.length ; i++){
			var process = workFlowProcessList[i];
			var c = Raphael.getColor(); 
			var st = this.paper.set();
			var rect = this.paper.rect(x1, y1, width, height, 10).attr({stroke: c, fill: c}).click(function(){
				var process = workFlowPanel.getProcess(this.textContent);
				workFlowPanel.processClicked(process);
				
			}).mouseover(function(){
				this.node.style.cursor = "pointer";
				this.animate({"fill-opacity": .70,height : 52,width : 102}, 500); 
				
			}).mouseout(function(){
				 this.animate({"fill-opacity": 1,height : 50,width : 100}, 500); 
			});
			var text = this.paper.text(x1 + 50,y1+20,process.processName).mouseover(function () {
				this.node.style.cursor = "pointer";
			});
			
			rect.textContent =  process.processName;
			text.node.onclick = function(){
				var process = workFlowPanel.getProcess( this.textContent );
				workFlowPanel.processClicked(process);
			};
			
			st.push(rect);
			st.push(text);
			

			process.x1 = x1;
			process.y1 = y1;
			process.height = height;
			process.width = width;
			
			
			
			if( x1 +200 >= this.paper.width){
				x1 = 100;
				y1 = y1 + 100;
			}
			else{
				x1 = x1 + 200;
				y1 =y1;
			}
			
			this.processList.push( process );
		}
	},
	/**
	 *  If someone clicked on any process then this method will return that process based on the processName
	 *  This process contains (x,y) coordinate ,height and widthof the process(rect). 
	 */
	getProcess : function( processName ){
		for( var i = 0 ; i < this.processList.length ; i++){
			var process = this.processList[i];
			if( process.processName === processName ){
				return process;
			}
		}
	},
	/*
	 * If someone clicks on the process then this method will be called.
	 * this method is responsible for opening clicked process in new window.
	 */ 
	
	processClicked : function( process ){
		var node = {attributes : { id : process.serviceId+""}};
		var panel = layout.westPanel.items.items[0].getModulesTreePanel().getPanelToAdd( node );
		panel.title = process.serviceName;
		panel.frame = true;
		panel.closable = true;
		panel.height = 420;
		panel.windowCode = process.serviceId;
		
		
		var activeTab =	layout.getCenterRegionTabPanel().add(panel);
		
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		layout.getCenterRegionTabPanel().doLayout();

	},
	/*
	 * This method is reponsible for drawing line form any process to  linked process.
	 * This method first calculate All the coordiantes of the the process(rect).
	 * Then it check this process is having any linked process or not .
	 * If it is having any linked process the it first calculate all the coordinates of the linked process.
	 * and it draws line from the process to linked process.
	 * 
	 * 
	 * 	(x1,y1)						(x4,y4)
	 *      ________________________
	 *     |						|
	 *     |						|
	 *     |						|
	 *     |						|
	 *     |________________________|
	 * 
	 *	(x2,y2) 					(x3,y3) 
	 * 
	 * 
	 */
	drawLine : function(){
		var coordinate = {};
		var process = null;
		var linkedProcess = null;
		for( var i =0 ; i < this.processList.length ; i++){
			process = this.processList[i];
			coordinate.height = process.height;
			coordinate.width =  process.width;

			coordinate.x1 = process.x1;
			coordinate.y1 = process.y1;
			coordinate.x2 = coordinate.x1 ;//+ this.height;
			coordinate.y2 = coordinate.y1 + coordinate.height;
			coordinate.x3 = coordinate.x2 + coordinate.width;
			coordinate.y3 = coordinate.y2; //+ this.width;
			coordinate.x4 = coordinate.x1 + coordinate.width;
			coordinate.y4 = coordinate.y1 ;//+ this.width;
			if( !Ext.isEmpty( process.linkedProcessSet )){
				for( var j= 0 ; j < process.linkedProcessSet.length ; j++){
					if( !Ext.isEmpty( process.linkedProcessSet[j] )){
						
						linkedProcess = this.getProcess(process.linkedProcessSet[j].processName);
						coordinate.linkedProcessHeight = linkedProcess.height;
						coordinate.linkedProcessWidth = linkedProcess.width;
						coordinate.linkedProcessX1 = linkedProcess.x1;
						coordinate.linkedProcessY1 = linkedProcess.y1;
						coordinate.linkedProcessX2 = coordinate.linkedProcessX1; //+ this.linkedBtnHeight;
						coordinate.linkedProcessY2 = coordinate.linkedProcessY1 + coordinate.linkedProcessHeight;
						coordinate.linkedProcessX3 = coordinate.linkedProcessX2 + coordinate.linkedProcessWidth;
						coordinate.linkedProcessY3 = coordinate.linkedProcessY2 ;//+ this.linkedBtnWidth;
						coordinate.linkedProcessX4 = coordinate.linkedProcessX1 + coordinate.linkedProcessWidth;
						coordinate.linkedProcessY4 = coordinate.linkedProcessY1; //+ this.linkedBtnWidth;
						
						var linkedBtnPos = this.getLinkedProcessPosition(coordinate.x1,coordinate.y1,coordinate.linkedProcessX1,coordinate.linkedProcessY1);
						
						if( linkedBtnPos === "RIGHT" || linkedBtnPos === "RIGHT-TOP" || linkedBtnPos === "RIGHT-BOTTOM"){
							var fromCoordinate = this.calculateCenterPoint( coordinate.x3 , coordinate.y3 , coordinate.x4, coordinate.y4);
							var toCoordinate = this.calculateCenterPoint(  coordinate.linkedProcessX1 , coordinate.linkedProcessY1 , coordinate.linkedProcessX2, coordinate.linkedProcessY2);
							
							this.paper.arrow(fromCoordinate[0],fromCoordinate[1],toCoordinate[0],toCoordinate[1], 8);
						}
						else if( linkedBtnPos === "LEFT" || linkedBtnPos === "LEFT-TOP" || linkedBtnPos === "LEFT-BOTTOM"){
							var fromCoordinate = this.calculateCenterPoint( coordinate.x1 , coordinate.y1 , coordinate.x2, coordinate.y2);
							var toCoordinate = this.calculateCenterPoint(  coordinate.linkedProcessX3 , coordinate.linkedProcessY3 , coordinate.linkedProcessX4, coordinate.linkedProcessY4);
							
							this.paper.arrow(fromCoordinate[0],fromCoordinate[1],toCoordinate[0],toCoordinate[1], 8);
						}
						else if( linkedBtnPos === "TOP" ){
							var fromCoordinate = this.calculateCenterPoint( coordinate.x1 , coordinate.y1 , coordinate.x4, coordinate.y4);
							var toCoordinate = this.calculateCenterPoint(  coordinate.linkedProcessX2 , coordinate.linkedProcessY2 , coordinate.linkedProcessX3, coordinate.linkedProcessY3);
							
							this.paper.arrow(fromCoordinate[0],fromCoordinate[1],toCoordinate[0],toCoordinate[1], 8);
							
						}
						else if( linkedBtnPos === "BOTTOM"){
							var fromCoordinate = this.calculateCenterPoint( coordinate.x2 , coordinate.y2 , coordinate.x3, coordinate.y3);
							var toCoordinate = this.calculateCenterPoint(  coordinate.linkedProcessX1 , coordinate.linkedProcessY1 , coordinate.linkedProcessX4, coordinate.linkedProcessY4);
							
							this.paper.arrow(fromCoordinate[0],fromCoordinate[1],toCoordinate[0],toCoordinate[1], 8);
							
						}
					}
				}
			}
			
		}
	},
	
	// This method will calculate process position.
	
	getLinkedProcessPosition : function(fromX1 , fromY1 , toX1 , toY1){
		var linkeBtnPos = null;
		if( fromX1 < toX1 ){
			if( fromY1 < toY1 ){
				linkeBtnPos = "RIGHT-BOTTOM";
			}
			else if( fromY1 === toY1 ){
				linkeBtnPos = "RIGHT";
			}
			else {
				linkeBtnPos = "RIGHT-TOP";
			}
		}
		else if( fromX1 > toX1){
			if( fromY1 < toY1 ){
				linkeBtnPos = "LEFT-BOTTOM";
			}
			else if( fromY1 === toY1 ){
				linkeBtnPos = "LEFT";
			}
			else {
				linkeBtnPos = "LEFT-TOP";
			}
			
		}
		else{
			if( fromY1 < toY1 ){
				linkeBtnPos = "BOTTOM";
			}
			else if( fromY1 === toY1 ){
				linkeBtnPos = "";
			}
			else {
				linkeBtnPos = "TOP";
			}
		}
		return linkeBtnPos;
	},
	
	// This method will calculate center points between two points.
	
	calculateCenterPoint : function( x1 , y1 , x2 , y2 ){
		var coordinate = new Array();
		coordinate.push( (x1 + x2)/2 );
		coordinate.push( (y1 + y2)/2 );
		
		return coordinate;
	}
	
});

