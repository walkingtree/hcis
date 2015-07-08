/**
 * 
 */
package in.wtc.ui.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wtc.usermanager.bm.AuthorisedUser;
import com.wtc.usermanager.bm.Role;
import com.wtc.usermanager.bm.User;
import com.wtc.usermanager.bm.UserSummary;
import com.wtc.usermanager.bo.user.UserManager;
import com.wtc.usermanager.util.ListRange;

import in.wtc.utils.Converters;

/**
 * @author Vinay Kurudi
 *
 */
public class UserManagementController {

	UserManager userManager;
	public AuthorisedUser validateUser(String userName, String password){
		AuthorisedUser authorisedUser = userManager.login(userName, password);
//		AuthorisedUser authorisedUser2 = new AuthorisedUser();
		return authorisedUser;
	}
	
	public void saveUser(User user) {
		userManager.addUser(null, user);
	}
	public ListRange getUsers(HashMap<String,String> searchElements,int start, int count, String orderBy ) {
		List<UserSummary> userList =userManager.getUsers( null, searchElements.get("userName"), 
				searchElements.get("role"),searchElements.get("name"), searchElements.get("state"),
				searchElements.get("country"), 
				Converters.getDate(searchElements.get("effectiveFrom")),Converters.getDate(searchElements.get("effectiveTo")));
			ListRange listRange = new ListRange();
		if(userList!=null && userList.size()>0) {
			/**
			 * pagingData list for restricting records to display how many we want
			 */
			List<UserSummary> pagingData = new ArrayList<UserSummary>();
			/**
			 * 	we are creating another list because, in the above list we get country and state may be null. in that case dwr thrown get exception.
				Because in the record mapping done for country and state.
			 */
			List<UserSummary> userData = new ArrayList<UserSummary>();
			Iterator<UserSummary> iter = userList.iterator();
			while(iter.hasNext()){
				UserSummary userSummary= iter.next();
				userSummary.setCountry(Converters.setCodeAndDescriptionNull(userSummary.getCountry()));
				userSummary.setState(Converters.setCodeAndDescriptionNull(userSummary.getState()));
				if(userSummary.getRole()== null){
					userSummary.setRole(new Role());
				}
				userData.add(userSummary);
			}
			int index = 0;
			while( (start+index < start + count) && (userData.size() > start+index) ){
				pagingData.add(userData.get(start+index));
					index++;
			}
			listRange.setData(pagingData.toArray());
			listRange.setTotalSize(userData.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		return listRange;
	}
	
	public User getUserDetails(UserSummary userSummary){
		User user = userManager.getUserDetail(null, userSummary);
		return user;
	}
	public void  modifyUser(User user,AuthorisedUser authorisedUser) {
		User ModifiedUser = userManager.updateUser(authorisedUser, user);
	}
	public void removeUsers(ArrayList<UserSummary> usersList) {
		userManager.removeUsers(null, usersList);
	}
	public void changePassword(AuthorisedUser user, String newPassword) {
		userManager.changePassword(user, newPassword);
	}
	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
