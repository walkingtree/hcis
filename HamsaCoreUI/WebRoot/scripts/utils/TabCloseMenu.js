Ext.namespace("utils");

// Very simple plugin for adding a close context menu to tabs
utils.TabCloseMenu = function(){
    var tabs, menu, ctxItem;
    this.init = function(tp){
        tabs = tp;
        tabs.on('contextmenu', onContextMenu);
    }

   function onContextMenu(ts, item, e){
       if(!menu){ // create context menu on first right click
           menu = new Ext.menu.Menu([{
               id: tabs.id + '-close',
               text: 'Close Tab',
               handler : function(){
                   tabs.remove(ctxItem);
               }
           },{
               id: tabs.id + '-close-others',
               text: 'Close Other Tabs',
               handler : function(){
                   tabs.items.each(function(item){
                       if(item.closable && item != ctxItem){
                           tabs.remove(item);
                       }
                   });
               }
           }/*,{
               id: tabs.id + '-add-to-favourites',
               text: 'Add to favourites',
               handler : function(){
               		alert("Add to favourites clicked ..!");
               }
           }*/]);
       }
       ctxItem = item;
       var items = menu.items;
       items.get(tabs.id + '-close').setDisabled(!item.closable);
       var disableOthers = true;
       tabs.items.each(function(){
           if(this != item && this.closable){
               disableOthers = false;
               return false;
           }
       });
       items.get(tabs.id + '-close-others').setDisabled(disableOthers);
       menu.showAt(e.getPoint());
   }
};