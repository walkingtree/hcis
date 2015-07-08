/**
 * @author chander
 */
Ext.ux.MultiGroupingStore = Ext.extend(Ext.data.GroupingStore, {
//    sortInfo: [],
    sort: function(field, dir){
		
      
        var f = [];
        
        if (Ext.isArray(field)) {
            for (i = 0, len = field.length; i < len; ++i) {
                f.push(this.fields.get(field[i]));
            }
        }
        else {
            f.push(this.fields.get(field));
        }
        
        if (f.length < 1) {
            return false;
        }
        
        if (!dir) {
            if (this.sortInfo && this.sortInfo.length > 0 && this.sortInfo[0].field == f[0].name) { // toggle sort dir
                dir = (this.sortToggle[f[0].name] || "ASC").toggle("ASC", "DESC");
            }
            else {
                dir = f[0].sortDir;
            }
        }
        
        var st = (this.sortToggle) ? this.sortToggle[f[0].name] : null;
        var si = (this.sortInfo) ? this.sortInfo : null;
        
        this.sortToggle[f[0].name] = dir;
        this.sortInfo = [];
        for (i = 0, len = f.length; i < len; ++i) {
            this.sortInfo.push({
                field: f[i].name,
                direction: dir
            });
        }
        
        if (!this.remoteSort) {
            this.applySort();
            this.fireEvent("datachanged", this);
        }
        else {
            if (!this.load(this.lastOptions)) {
                if (st) {
                    this.sortToggle[f[0].name] = st;
                }
                if (si) {
                    this.sortInfo = si;
                }
            }
        }
	
    },
    
    setDefaultSort: function(field, dir){
       
        dir = dir ? dir.toUpperCase() : "ASC";
        this.sortInfo = [];
        
        if (!Ext.isArray(field)) 
            this.sortInfo.push({
                field: field,
                direction: dir
            });
        else {
            for (i = 0, len = field.length; i < len; ++i) {
                this.sortInfo.push({
                    field: field[i].field,
                    direction: dir
                });
                this.sortToggle[field[i]] = dir;
            }
        }
    },
    
    constructor: function(config){
        Ext.ux.MultiGroupingStore.superclass.constructor.apply(this, arguments);
    },
    
    groupBy: function(field, forceRegroup){
       
        if (!forceRegroup && this.groupField == field) {
            return; // already grouped by this field
        }
        
        
        if (this.groupField) {
            for (z = 0; z < this.groupField.length; z++) 
                if (field == this.groupField[z]) 
                    return;
            this.groupField.push(field);
        }
        else 
            this.groupField = [field];
        
        if (this.remoteGroup) {
            if (!this.baseParams) {
                this.baseParams = {};
            }
            this.baseParams['groupBy'] = field;
        }
        if (this.groupOnSort) {
            this.sort(field);
            return;
        }
        if (this.remoteGroup) {
            this.reload();
        }
        else {
            var si = this.sortInfo || [];
            if (si.field != field) {
                this.applySort();
            }
            else {
                this.sortData(field);
            }
            this.fireEvent('datachanged', this);
        }
    },
    
    applySort: function(){
		
      
        var si = this.sortInfo;
        
        if (si && si.length > 0 && !this.remoteSort) {
            this.sortData(si, si[0].direction);
        }
        
        if (!this.groupOnSort && !this.remoteGroup) {
            var gs = this.getGroupState();
            if (gs && gs != this.sortInfo) {
           
                this.sortData(this.groupField);
            }
        }
    },
    
    getGroupState: function(){
       
        return this.groupOnSort && this.groupField !== false ? (this.sortInfo ? this.sortInfo : undefined) : this.groupField;
    },
    
    sortData: function(flist, direction){
       
        direction = direction || 'ASC';
        
        var st = [];
        
        var o;
        for (i = 0, len = flist.length; i < len; ++i) {
            o = flist[i];
       
            
            st.push(this.fields.get(o.field ? o.field : o).sortType);
        }
       
        
        var fn = function(r1, r2){
        
            var v1 = [];
            var v2 = [];
            var len = flist.length;
            var o;
            var name;
          
            for (i = 0; i < len; ++i) {
                o = flist[i];
                name = o.field ? o.field : o;
               
                v1.push(st[i](r1.data[name]));
                v2.push(st[i](r2.data[name]));
            }
            
            var result;
            for (i = 0; i < len; ++i) {
                result = v1[i] > v2[i] ? 1 : (v1[i] < v2[i] ? -1 : 0);
                if (result != 0) 
                    return result;
            }
            
            return result; //if it gets here, that means all fields are equal
        };
        
        this.data.sort(direction, fn);
        if (this.snapshot && this.snapshot != this.data) {
            this.snapshot.sort(direction, fn);
        }
    }
    
});


Ext.ux.MultiGroupingView = Ext.extend(Ext.grid.GroupingView, {
    displayEmptyFields: false,
    displayFieldSeperator: ', ',
    
    renderRows: function(){
        var groupField = this.getGroupField();
        var eg = !!groupField;
        // if they turned off grouping and the last grouped field is hidden
        if (this.hideGroupedColumn) {
            var colIndexes = [];
            for (i = 0, len = groupField.length; i < len; ++i) {
                colIndexes.push(this.cm.findColumnIndex(groupField[i]));
            }
            if (!eg && this.lastGroupField !== undefined) {
                this.mainBody.update('');
                for (i = 0, len = this.lastGroupField.length; i < len; ++i) {
                    this.cm.setHidden(this.cm.findColumnIndex(this.lastGroupField[i]), false);
                }
                delete this.lastGroupField;
            }
            else 
                if (eg && colIndexes.length > 0 && this.lastGroupField === undefined) {
                    this.lastGroupField = groupField;
                    for (i = 0, len = colIndexes.length; i < len; ++i) {
                        this.cm.setHidden(colIndexes[i], true);
                    }
                }
                else 
                    if (eg && this.lastGroupField !== undefined && groupField !== this.lastGroupField) {
                        this.mainBody.update('');
                        for (i = 0, len = this.lastGroupField.length; i < len; ++i) {
                            this.cm.setHidden(this.cm.findColumnIndex(this.lastGroupField[i]), false);
                        }
                        this.lastGroupField = groupField;
                        for (i = 0, len = colIndexes.length; i < len; ++i) {
                            this.cm.setHidden(colIndexes[i], true);
                        }
                    }
        }
        return Ext.ux.MultiGroupingView.superclass.renderRows.apply(this, arguments);
    },
	getRows : function(){
	
        if(!this.enableGrouping){
            return Ext.grid.GroupingView.superclass.getRows.call(this);
        }
		var groupField=this.getGroupField();
        var r = [];
        var g, gs = this.getGroups();
		
        for(var i = 0, len = gs.length; i < len; i++){
			
			
			var groupName=gs[i].childNodes[0].childNodes[0].innerHTML;
			if(groupName.substring(0,groupName.indexOf(':'))==groupField[groupField.length-1])
			{
            g = gs[i].childNodes[1].childNodes;
            for(var j = 0, jlen = g.length; j < jlen; j++){
				
                r[r.length] = g[j];
            }
			}
			else
			{
				r=getRowsFromGroup(r,gs[i].childNodes[1].childNodes,groupField[groupField.length-1])
			}
        }
        return r;
    }
    ,
    doRender: function(cs, rs, ds, startRow, colCount, stripe){
     
        if (rs.length < 1) {
            return '';
        }
	
        var groupField = this.getGroupField();
        this.enableGrouping = !!groupField;
        
        if (!this.enableGrouping || this.isUpdating) {
            return Ext.grid.GroupingView.superclass.doRender.apply(this, arguments);
        }
        
        var gstyle = 'width:' + this.getTotalWidth() + ';';
        
        var gidPrefix = this.grid.getGridEl().id;
        
        var groups = [], curGroup, i, len, gid;
        var lastvalues = [];
      
		var added=0;
	
        for (i = 0, len = rs.length; i < len; i++) {
			added=0;
            var rowIndex = startRow + i;
            var r = rs[i];
            var differ=0;
            var gvalue = [];
            var fieldName;
            var grpDisplayValues = [];
            var v;
            var changed = 0;
          
            for (j = 0, gfLen = groupField.length; j < gfLen; j++) {
            
                fieldName = groupField[j];
           
                v = r.data[fieldName];
                
            
                
                if (v) {
                
                    if (i == 0) {
                        lastvalues[j] = v;
                        gvalue.push(v);
                        grpDisplayValues.push(fieldName + ': ' + v);
                        gvalue.push(v);
                    }
                    else {
					
                        if (lastvalues[j] != v) {
                            changed = 1;
                            gvalue.push(v);
                            grpDisplayValues.push(fieldName + ': ' + v);
                            lastvalues[j] = v;
							differ=1;
						
                        }
                        else {
                      
                            if (groupField.length - 1 == j&&differ!=1) 
							{
								curGroup.rs.push(r);
						
							
							}
                       
                            if (changed == 1) {
								grpDisplayValues.push(fieldName + ': ' + v);
						
							}
                        }
                        
                    }
                }
                else 
                    if (this.displayEmptyFields) {
                        grpDisplayValues.push(fieldName + ': ');
                    }
            }
            
          
           
            if (gvalue.length < 1 && this.emptyGroupText) 
                g = this.emptyGroupText;
            else 
                g = grpDisplayValues;//.join(this.displayFieldSeperator);
          
            for (k = 0; k < grpDisplayValues.length; k++) {
             
                g = grpDisplayValues[k];
              
                if (!curGroup || curGroup.group != g) {
                
                    gid = gidPrefix + '-gp-' + groupField[k] + '-' + Ext.util.Format.htmlEncode(g);
               
                    
                    // if state is defined use it, however state is in terms of expanded
                    // so negate it, otherwise use the default.
                    var isCollapsed = typeof this.state[gid] !== 'undefined' ? !this.state[gid] : this.startCollapsed;
                    var gcls = isCollapsed ? 'x-grid-group-collapsed' : '';
					var jjj
					for (jjj = 0; jjj < groupField.length; jjj++) {
							if (g.substring(0, g.indexOf(':')) == groupField[jjj]) 
								break;
						}
                    if (k == grpDisplayValues.length - 1) {
						
							curGroup = {
								group: g,
								gvalue: gvalue[k],
								text: g,
								groupId: gid,
								startRow: rowIndex,
								rs: [r],
								cls: gcls,
								style: gstyle + 'padding-left:' + (jjj * 12) + 'px;'
							};
						
                    }
                    else {
                        curGroup = {
                            group: g,
                            gvalue: gvalue[k],
                            text: g,
                            groupId: gid,
                            startRow: rowIndex,
                            rs: [],
                            cls: gcls,
                            style: gstyle+'padding-left:'+(jjj*12)+'px;'
                        };
                    }
                    groups.push(curGroup);
                }
                else {
					
                    curGroup.rs.push(r);
					
                }
                r._groupId = gid;
            }
        }
        var buf = [];
		var toEnd=0;
        for (var ilen = 0, len = groups.length; ilen < len; ilen++) {
			toEnd++;
			
            var g = groups[ilen];
		
            this.doGroupStart(buf, g, cs, ds, colCount);
          
		if(g.rs.length!=0)
		
            buf[buf.length] = Ext.grid.GroupingView.superclass.doRender.call(this, cs, g.rs, ds, g.startRow, colCount, stripe);
		
       
				if(g.group.substring(0,g.group.indexOf(':'))==groupField[groupField.length-1])
				{
				var jj;
			
				var gg=groups[ilen+1];
			
					if(gg!=null)
					{
						
						for (jj = 0; jj < groupField.length; jj++) {
							if (gg.group.substring(0, gg.group.indexOf(':')) == groupField[jj]) 
								break;
						}
						
						toEnd=groupField.length-jj;
					}
				for (k = 0; k < toEnd; k++) {
					this.doGroupEnd(buf, g, cs, ds, colCount);
					
				}
				toEnd=jj;
			
			}
        
        }
    
        return buf.join('');
    }
});
function getRowsFromGroup(r,gs,lsField)
{
	for(var i = 0, len = gs.length; i < len; i++){
				
			var groupName=gs[i].childNodes[0].childNodes[0].innerHTML;
			
			if(groupName.substring(0,groupName.indexOf(':'))==lsField)
			{
            g = gs[i].childNodes[1].childNodes;
            for(var j = 0, jlen = g.length; j < jlen; j++){
				
                r[r.length] = g[j];
            }
			}
			else
			{
				r=getRowsFromGroup(r,gs[i].childNodes[1].childNodes,lsField);
			}
        }
	return r;
}
