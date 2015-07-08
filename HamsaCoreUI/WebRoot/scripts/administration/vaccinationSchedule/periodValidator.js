
Ext.apply(Ext.form.VTypes, {
   period: function(value, field)
   {
      var hasSpecial = value.match( /^([\,\-0-9])+$/ );
 
      return ( hasSpecial );
   },
   periodText: 'Period contain either a number or a special charecters (,-)',
  
   afterPeriod: function(value, field)
   {
      var hasSpecial = value.match( /^([0-9])+$/ );
 
      return ( hasSpecial );
   },
   afterPeriodText: ' after Period contain either a number'

});

