
Ext.form.OptComboBox = Ext.extend(Ext.ux.ComboBox, {
	selectOnFocus: true,
	editable: true,
initList:function () {
	if (!this.list) {
		var cls = "x-combo-list";
		this.list = new Ext.Layer({shadow:this.shadow, cls:[cls, this.listClass].join(" "), constrain:false});
		var lw = this.listWidth || Math.max(this.wrap.getWidth(), this.minListWidth);
		this.list.setWidth(lw);
		this.list.swallowEvent("mousewheel");
		this.assetHeight = 0;
		if (this.title) {
			this.header = this.list.createChild({cls:cls + "-hd", html:this.title});
			this.assetHeight += this.header.getHeight();
		}
		this.innerList = this.list.createChild({cls:cls + "-inner"});
		this.innerList.on("mouseover", this.onViewOver, this);
		this.innerList.on("mousemove", this.onViewMove, this);
		this.innerList.setWidth(lw - this.list.getFrameWidth("lr"));
		if (this.pageSize) {
			this.footer = this.list.createChild({cls:cls + "-ft"});
			this.pageTb = new Ext.PagingToolbar({store:this.store, pageSize:this.pageSize, renderTo:this.footer});
			this.assetHeight += this.footer.getHeight();
		}
		if (!this.tpl) {
			this.tpl = "<tpl for=\".\"><div class=\"" + cls + "-item\">{" + this.displayField + "}</div></tpl>";
		}
		this.view = new Ext.DataView({applyTo:this.innerList, tpl:this.tpl, singleSelect:true, selectedClass:this.selectedClass, itemSelector:this.itemSelector || "." + cls + "-item"});

		var rowRecord = Ext.data.Record.create(this.store.fields);
		var recordIndex = this.store.find('description', '-Any-' );
		// here we are checking the record already exists are not. if exists we wont add 
		// another record for that.
		if( recordIndex == -1 ){
			var arrFields = new Array();
			arrFields[this.store.fields.items[0].name] = '';
			arrFields[this.store.fields.items[1].name] = '-Any-';
			this.store.insert(0, new rowRecord(arrFields));
		}
		
		this.view.on("click", this.onViewClick, this);
		this.bindStore(this.store, true);
		if (this.resizable) {
			this.resizer = new Ext.Resizable(this.list, {pinned:true, handles:"se"});
			this.resizer.on("resize", function (r, w, h) {
				this.maxHeight = h - this.handleHeight - this.list.getFrameWidth("tb") - this.assetHeight;
				this.listWidth = w;
				this.innerList.setWidth(w - this.list.getFrameWidth("lr"));
				this.restrictHeight();
			}, this);
			this[this.pageSize ? "footer" : "innerList"].setStyle("margin-bottom", this.handleHeight + "px");
		}
		
		if(!this.isValid()){
			this.setValue('');
		}

		this.addListener('change',function(){
			if(!this.isValid()){
				this.setValue('');
			}
		},this);
		this.addListener('blur',function(){
			if(!this.isValid()){
				this.setValue('');
			}
		},this);
		
//		this.allowBlank = false;
	}
}
});
Ext.reg("optcombo", Ext.form.OptComboBox);

