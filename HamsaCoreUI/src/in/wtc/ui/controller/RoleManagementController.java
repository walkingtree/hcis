/**
 * 
 */
package in.wtc.ui.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wtc.usermanager.bm.Role;
import com.wtc.usermanager.bm.RoleSummary;
import com.wtc.usermanager.bm.Service;
import com.wtc.usermanager.bm.TreeNode;
import com.wtc.usermanager.bm.UserSummary;
import com.wtc.usermanager.bo.role.RoleManager;
import com.wtc.usermanager.util.ListRange;

import in.wtc.ui.utils.Converters;

/**
 * @author Vinay Kurudi
 *
 */
public class RoleManagementController implements Comparator<Service>{

	private RoleManager roleManager;
	
	public List<TreeNode> getServices(String nodeId, String roleId) {
		Map<String, ArrayList<Role>> roleMap = new HashMap<String, ArrayList<Role>>(0) ;
		List<Role> roleList = new ArrayList<Role>(0);
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>(0);
		List<Service> serviceList = new ArrayList<Service>(0);
		if(nodeId.equals("-1")){
			 roleMap = roleManager.getAllAvailableRolesAndServices();
		}
		if(roleMap!=null && roleMap.size()>0){
			roleList = roleMap.get("A");
			Role role = roleList.get(0);
			if((role.getRoleHasServices()!=null && role.getRoleHasServices().size()>0) ){
				serviceList = this.getSortedList( role.getRoleHasServices()) ;
			}
		}
		if(roleId!=null && nodeId.equals("-1")){
			serviceList = this.getSortedList((ArrayList<Service>) roleManager.getAvaliableServicesForRole(roleId) );
		}
		if(serviceList!=null && serviceList.size()>0){
			Iterator<Service> iterService = serviceList.iterator();
			while (iterService.hasNext()) {
				Service service = (Service) iterService.next();
				TreeNode treeNode = new TreeNode();
				if(service.getChildServices()!=null && service.getChildServices().size()>0){
					for(Service childService:service.getChildServices()){
						if( childService.getChildServices() != null && 
								childService.getChildServices().size()>0){
							treeNode.getChildren().add( getTreeNode( childService ));
						}else{
							TreeNode childTreeNode = new TreeNode();
							childTreeNode.setId(childService.getId().toString());
							childTreeNode.setText(childService.getDescription());
							childTreeNode.setChildrenLoaded(true);
							childTreeNode.setExpanded(true);
							childTreeNode.setHasChildren(false);
							childTreeNode.setLeaf(true);
							treeNode.getChildren().add(childTreeNode);
						}
					}
					treeNode.setLeaf(false);
					treeNode.setHasChildren(true);
				}else{
					if(service.getParentService()== null){
						treeNode.setHasChildren(true);
						treeNode.setLeaf(false);
					}
					treeNode.setHasChildren(true);
					treeNode.setLeaf(false);
				}
				treeNode.setId(service.getId().toString());
				treeNode.setText(service.getDescription());
				treeNode.setChildrenLoaded(true);
				treeNode.setExpanded(true);
				treeNodeList.add(treeNode);
			}
		}
		return treeNodeList;
	}
	public List<TreeNode> getAssignedNodes(String nodeId, String roleId) {
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>(0);
		if(roleId!=null && roleId.length()>0){
			RoleSummary roleSummary = new RoleSummary();
			roleSummary.setId(roleId);
			roleSummary.setName(roleId);
			Role role = roleManager.getRoleDetail(null, roleSummary);
			List<Service> serviceList = new ArrayList<Service>();
			if((role.getRoleHasServices()!=null && role.getRoleHasServices().size()>0) ){
				serviceList = this.getSortedList(role.getRoleHasServices());
				
				if(serviceList!=null && serviceList.size()>0 ) {
					for(Service service: serviceList){
						TreeNode treeNode = new TreeNode();
						if(service.getChildServices()!=null && service.getChildServices().size()>0){
							ArrayList<Service> childServiceList = this.getSortedList( service.getChildServices() );
							for(Service childService: childServiceList){
								if( childService.getChildServices() != null 
										&& childService.getChildServices().size() > 0){
									 treeNode.getChildren().add( getTreeNode(childService ));
								}else{
									TreeNode childTreeNode = new TreeNode();
									childTreeNode.setId(childService.getId().toString());
									childTreeNode.setText(childService.getDescription());
									childTreeNode.setChildrenLoaded(true);
									childTreeNode.setIcon("images/leaf.jpeg");
									childTreeNode.setExpanded(true);
									childTreeNode.setHasChildren(false);
									childTreeNode.setLeaf(true);
									treeNode.getChildren().add(childTreeNode);
								}
							}
							treeNode.setLeaf(false);
							treeNode.setHasChildren(true);
						}else{
							treeNode.setHasChildren(false);
							treeNode.setLeaf(true);
						}
						treeNode.setId(service.getId().toString());
						treeNode.setText(service.getDescription());
						treeNode.setChildrenLoaded(true);
						treeNode.setExpanded(true);
						treeNodeList.add(treeNode);

					}
				}
			}	
		}
		return treeNodeList;
	}

	public TreeNode getTreeNode( Service service ){
		TreeNode treeNode = new TreeNode();
		if(service.getChildServices()!=null && service.getChildServices().size()>0){
			ArrayList<Service> childServiceList = this.getSortedList( service.getChildServices() );
			for( Service childService: childServiceList ){
				TreeNode childTreeNode = new TreeNode();
				childTreeNode.setId(childService.getId().toString());
				childTreeNode.setText(childService.getDescription());
				childTreeNode.setChildrenLoaded(true);
				childTreeNode.setIcon("images/leaf.jpeg");
				childTreeNode.setExpanded(true);
				childTreeNode.setHasChildren(false);
				childTreeNode.setLeaf(true);
				treeNode.getChildren().add(childTreeNode);
			}
			treeNode.setLeaf(false);
			treeNode.setHasChildren(true);
		}else{
			treeNode.setHasChildren(false);
			treeNode.setLeaf(true);
		}
		treeNode.setId(service.getId().toString());
		treeNode.setText(service.getDescription());
		treeNode.setChildrenLoaded(true);
		treeNode.setExpanded(true);

		return treeNode;
	}
	public void addRole(Role role){
		roleManager.addRole(null, role);
	}
	
	public void modifyRole(Role role) {
		roleManager.updateRole(null, role);
	}
	public ListRange getRoles(Map<String, String> searchElements,int start, int count, String orderBy ){
		ListRange listRange = new ListRange();
		ArrayList<RoleSummary> roleSummarList = roleManager.getRolesSummary(null, searchElements.get("roleName"),null, false,
										Converters.getDate(searchElements.get("effectiveFrom")), 
										Converters.getDate(searchElements.get("effectiveTo")));
		if(roleSummarList!=null && roleSummarList.size()>0) {
			/**
			 * pagingData list for restricting records to display how many we want
			 */
			List<RoleSummary> pagingData = new ArrayList<RoleSummary>();
			
			int index = 0;
			while( (start+index < start + count) && (roleSummarList.size() > start+index) ){
				pagingData.add(roleSummarList.get(start+index));
					index++;
			}
			listRange.setData(pagingData.toArray());
			listRange.setTotalSize(roleSummarList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		return listRange;
	}
	public void removeRoles(ArrayList<RoleSummary> roleSummaryList) {
		roleManager.removeRoles(null, roleSummaryList);
	}
	
	public Role getRoleDetail(RoleSummary roleSummary) {
		Role role = roleManager.getRoleDetail(null, roleSummary);
		return role;
	}
	/**
	 * @param roleManager the roleManager to set
	 */
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	private ArrayList<Service> getSortedList( ArrayList<Service> serviceList ){
		
		try {
			
			for( int i=0; i<serviceList.size();i++ ){
				for( int j=i+1; j<serviceList.size();j++  ){
					int result = compare( serviceList.get( i ), serviceList.get( j ));
					if( result > 0){
						Service tempService = serviceList.get( i );
						serviceList.set( i , serviceList.get( j));
						serviceList.set( j , tempService);
					}
				}
			}
			
			return serviceList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int compare(Service service1, Service service2) {
		 
		
		return service1.getDescription().compareToIgnoreCase( service2.getDescription() );
	}
}
