package in.wtc.hcis.bo.common;

import java.io.Serializable;

public class ListRange implements Serializable {
	
	private Object[] data;
	private int totalSize;

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}