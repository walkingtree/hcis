Ext.namespace("Ext.ux");
/**
 * @class Ext.ux.DWRTreeLoader
 * @extends Ext.tree.TreeLoader
 * @author Carina Stumpf
 *
 * DWRTreeloader loads tree nodes by calling a DWR service.
 * Version 2.1
 *
 */

/**
 * @constructor
 * @param cfg {Object} config A config object
 *    @cfg dwrCall the DWR function to call when loading the nodes
 */

Ext.ux.DWRTreeLoader = function(config) {
  Ext.ux.DWRTreeLoader.superclass.constructor.call(this, config);
};

Ext.extend(Ext.ux.DWRTreeLoader, Ext.tree.TreeLoader, {
/**
 * Load an {@link Ext.tree.TreeNode} from the DWR service.
 * This function is called automatically when a node is expanded, but may be used to reload
 * a node (or append new children if the {@link #clearOnLoad} option is false.)
 * @param {Object} node node for which child elements should be retrieved
 * @param {Function} callback function that should be called before executing the DWR call
 */
  load : function(node, callback) {
    var cs, i;
    if (this.clearOnLoad) {
      while (node.firstChild) {
        node.removeChild(node.firstChild);
      }
    }
    if (node.attributes.children && node.attributes.hasChildren) { // preloaded json children
      cs = node.attributes.children;
      for (i = 0,len = cs.length; i<len; i++) {
        node.appendChild(this.createNode(cs[i]));
      }
      if (typeof callback == "function") {
        callback();
      }
    } else if (this.dwrCall) {
      this.requestData(node, callback);
    }
  },

/**
 * Performs the actual load request
 * @param {Object} node node for which child elements should be retrieved
 * @param {Function} callback function that should be called before executing the DWR call
 */
  requestData : function(node, callback) {
    var callParams;
    var success, error, rootId, dataContainsRoot;

    if (this.fireEvent("beforeload", this, node, callback) !== false) {

      callParams = this.getParams(node,this.baseParams);

      success = this.handleResponse.createDelegate(this, [node, callback], 1);
      error = this.handleFailure.createDelegate(this, [node, callback], 1);

      callParams.push({callback:success, errorHandler:error});

      this.transId = true;
      this.dwrCall.apply(this, callParams);
    } else {
      // if the load is cancelled, make sure we notify
      // the node that we are done
      if (typeof callback == "function") {
        callback();
      }
    }
  },

/**
 * Override this to add custom request parameters. Default adds the node id as first and only parameter
 */
  getParams : function(node,baseParams) {
        var buf = [node.id], bp = baseParams;
        for(var key in bp){
            if(typeof bp[key] != "function"){
                buf.push(encodeURIComponent(bp[key]));
            }
        }
    return buf;
  },

/**
 * Handles a successful response.
 * @param {Object} childrenData data that was sent back by the server that contains the child nodes
 * @param {Object} parent parent node to which the child nodes will be appended
 * @param {Function} callback callback that will be performed after appending the nodes
 */
  handleResponse : function(childrenData, parent, callback) {
    this.transId = false;
    this.processResponse(childrenData, parent, callback);
  },

/**
 * Handles loading error
 * @param {Object} response data that was sent back by the server that contains the child nodes
 * @param {Object} parent parent node to which child nodes will be appended
 * @param {Function} callback callback that will be performed after appending the nodes
 */
  handleFailure : function(response, parent, callback) {
    this.transId = false;
    this.fireEvent("loadexception", this, parent, response);
    if (typeof callback == "function") {
      callback(this, parent);
    }
   //console.log(e)("DwrTreeLoader: error during tree loading. Received response: " + response);
  },

/**
 * Process the response that was received from server
 * @param {Object} childrenData data that was sent back by the server that contains the attributes for the child nodes to be created
 * @param {Object} parent parent node to which child nodes will be appended
 * @param {Function} callback callback that will be performed after appending the nodes
 */
  processResponse : function(childrenData, parent, callback) {
    var i, n, nodeData;
    try {
      for (var i = 0; i<childrenData.length; i++) {
        var n = this.createNode(childrenData[i]);
        if (n) {
          n.hasChildren = childrenData[i].hasChildren;
          parent.appendChild(n);
        }
      }

      if (typeof callback == "function") {
        callback(this, parent);
      }
    } catch(e) {
      this.handleFailure(childrenData);
    }
  }
});