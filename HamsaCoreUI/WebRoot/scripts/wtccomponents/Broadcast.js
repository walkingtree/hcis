Ext.namespace('Ext.ux.event');
	
	Ext.override(Ext.util.Observable, {
	
		/**
		 * This function is <b>addListener</b> analog for broadcasted messages. It accept the same parameters and have the same functionality. For further details please refer to <b>Ext.util.Observable</b> documentation
		 * object parameter will have property that will contain all the subscribed events in this object. When this object will 
		 * destroyed all this subscribed events will be unsubscribed.   
		 * @name subscribe
		 * @methodOf Ext.ux.event.Broadcast 
		 * @param {String} eventName
		 * @param {Function} fn
		 * @param {Object} scope
		 * @param {Object} o
		 * @param {Object} object
		 */
		subscribe: function(eventName, fn, scope, o, object) {
	        Ext.ux.event.Broadcast.addEvents(eventName);
	        if( !Ext.isEmpty( object )){
	        	object.eventsArray = object.eventsArray || new Array();
	        	object.eventsArray.push( eventName );
	        }
	        Ext.ux.event.Broadcast.on(eventName, fn, scope, o);
	    },
	    
	    
		/**
		 * This function is <b>fireEvent</b> analog for broadcasted messages. It accept the same parameters and have the same functionality. For further details please refer to <b>Ext.util.Observable</b> documentation
		 * @name publish
		 * @methodOf Ext.ux.event.Broadcast 
	     * @param {String} eventName
	     * @param {Object} args Variable number of parameters are passed to handlers
	     * @return {Boolean} returns false if any of the handlers return false otherwise it returns true
		 */
		publish : function() {        
	        if(Ext.ux.event.Broadcast.eventsSuspended !== true){
	            var ce = Ext.ux.event.Broadcast.events ? Ext.ux.event.Broadcast.events[arguments[0].toLowerCase()] : false;
	            if(typeof ce == "object"){
	                return ce.fire.apply(ce, Array.prototype.slice.call(arguments, 1));
	            }
	        }
	        return true;
	    },
	    
		/**
		 * This function is <b>removeListener</b> analog for broadcasted messages.
		 * @name removeSubscriptionsFor
		 * @methodOf Ext.ux.event.Broadcast 
	     * @param {String}   eventName     The type of event which subscriptions will be removed. If this parameter is evaluted to false, then ALL subscriptions for ALL events will be removed.
	     */
	    removeSubscriptionsFor : function(eventName) {        
	        for(var evt in Ext.ux.event.Broadcast.events) {
	            if ( (evt == eventName) || (!eventName) ) {            
	                if(typeof Ext.ux.event.Broadcast.events[evt] == "object"){
	                    Ext.ux.event.Broadcast.events[evt].clearListeners();
	                }
	            }
	        }
	    },
	    
	    /**
	     *  This function will remove all the subscribed events for the given object 
	     */
	    
	    removeSubscriptionsForObject : function(object){
	    	object.eventsArray =object.eventsArray || new Array();
	    	for(var i=0 ; i < object.eventsArray.length ; i++){
	    		object.eventsArray[i] = object.eventsArray[i].toLowerCase();
	    		Ext.ux.event.Broadcast.events[object.eventsArray[i]].clearListeners();
	    	}
	    }
	});
	
	
	/**
	 * This extension provide broadcast capabilites for Ext events system
	 * It adds 3 new methods to Observable prototype, which mean they can be used with any of its subclasses (panels, windows, formfields etc)
	 * <br>Examples of usage:
<pre><code>
var w = new Ext.Window({...});
w.subscribe('userlogin', w.redraw, w);

.......

var loginDialog = new Ext.Window({...});

loginDialog.onLoginClick = function (){
	....
	if ( password == db_password) {
		this.publish('userlogin', user_id);
	}
};
	
</code></pre>
	 * note that in the example above both objects uses their's own scopes to access broadcasting capabilities:
	 * subscription was made as : <b>w.subscribe</b> and publishing as <b>loginDialog.publish</b>
	 * <br/><br/>Link to the source code: <a href="http://extjs-ux.org/repo/authors/SamuraiJack/trunk/Ext/ux/event/Broadcast.js">http://extjs-ux.org/repo/authors/SamuraiJack/trunk/Ext/ux/event/Broadcast.js</a>
	 * <br/>Link to the forum thread: <a href="http://extjs.com/forum/showthread.php?t=37422">http://extjs.com/forum/showthread.php?t=37422</a> 
	 * @class Ext.ux.event.Broadcast
	 * @extends Ext.util.Observable
	 */
	Ext.ux.event.Broadcast = new Ext.util.Observable;
	

