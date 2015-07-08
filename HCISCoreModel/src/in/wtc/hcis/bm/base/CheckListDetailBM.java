/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class CheckListDetailBM implements Serializable {

	private Long checkListDetailId;
	private String answer;
	private CodeAndDescription role; //Role for which this checklist question is applicable;
	private Integer checkListId;
	private String question;
	private String group;
	
	public Long getCheckListDetailId() {
		return checkListDetailId;
	}
	public void setCheckListDetailId(Long checkListDetailId) {
		this.checkListDetailId = checkListDetailId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public CodeAndDescription getRole() {
		return role;
	}
	public void setRole(CodeAndDescription role) {
		this.role = role;
	}
	public Integer getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(Integer checkListId) {
		this.checkListId = checkListId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
}
