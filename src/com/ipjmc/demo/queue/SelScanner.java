package com.ipjmc.demo.queue;

import java.io.Serializable;
import java.util.Comparator;


public class SelScanner implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int scannerId;
	private int jobId;

    public SelScanner() {
    	
    }

    public SelScanner(int scannerId, int jobId) {
    	this.scannerId = scannerId;
    	this.jobId = jobId;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScannerId() {
		return this.scannerId;
	}

	public void setScannerId(int scannerId) {
		this.scannerId = scannerId;
	}

	public int getJobId() {
		return this.jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SelScanner) {
			SelScanner selScanner = (SelScanner) obj;
			return selScanner.jobId == this.jobId && selScanner.scannerId == this.scannerId;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Job [id=" + id + ", scannerId=" + scannerId
				+ ", jobId=" + jobId + "]";
	}
	
	public static class SelScannerComparator implements Comparator<SelScanner> {

		/**
		 * jobId越小，优先级越高(non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(SelScanner o1, SelScanner o2) {
			
			
			if (o1.getJobId() < o2.getJobId()) {
				return -1;
			}
			
			if (o1.getJobId() > o2.getJobId()) {
				return 1;
			}
			
			return 0;
		}
		
	}

}