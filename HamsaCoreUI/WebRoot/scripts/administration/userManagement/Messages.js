
Ext.namespace("utils");

utils.Messages = function() {
// buttons	
this.add = "Add";
this.del = "Delete";
this.edit = "Edit";
this.view ="View";
this.search ="Search";
this.save = "Save";
this.reset = "Reset";
this.cancel = "Cancel";
this.login = "Login";
this.update ="Update";

//labels
this.usrename = "User name";
this.firstname = "First name";
this.lastname = "Last name";
this.password = "Password";
this.oldpassword="Old password";
this.newpassword ="New password";
this.confirmpassword ="Confirm password";
this.effectivefrom ="Effective(from)";
this.effectiveto = "Effective(to)";
this.address ="Address";
this.zipcode = "Zip code";
this.stateprovince ="State/Province";
this.country ="Country";
this.city ="City";
this.email = "Email Id";
this.contactnumber = "Contact number";
this.rolename ="Role name";
this.description ="description";
this.passwordprofilename ="Password profile name";
this.role = "Select role";
this.name = "Name";
this.housename = "House name/no";
this.street = "Place/street";
this.userId = "User ID";
//tool tips
this.saveuser ="Save user";
this.saverole = "Save role";
this.findrole ="Find role";
this.finduser ="Find user";
this.findpasswordprofilename ="Find password profile name";

// titles
this.adduser = "Add user";
this.addrole = "Add role";
this.manageuser = "Manage users";
this.manageroles ="Manage roles";
this.managepasswordprofiles = "Manage password profiles";
this.serviceassignement ="Service assignement";
this.changepassword ="Change password";
this.viewrole ="View role";
this.editrole ="Edit role";

// success messages
this.addusersucessmsg = "User added successfully";
this.updateusersucessmsg= "User updated successfully";
this.addrolesucessmsg = "Role added successfully";
this.updaterolesucessmsg = "Role updated successfully";
this.removeusersucessmsg = "User(s) removed successfully";
this.updatepasswordsuccessmsg = "Password updated successfully";
this.invalidpassword ="Invalid password";
this.mandatoryfields = "Please enter the mandatory fields";
this.removeusersmsg ="Do you want to delete selected user(s)?";

this.resetSearchCreteria = "Reset search creteria";
this.addMode = "add";
this.editMode ="edit";

this.joiningdatecomparition ="Please verify from date and to date";
}

var userMsg = new utils.Messages();