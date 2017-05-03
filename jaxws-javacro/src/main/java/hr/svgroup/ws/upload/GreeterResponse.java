package hr.svgroup.ws.upload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class GreeterResponse {

	private long requestId;
	private boolean success;

	public GreeterResponse(){}

	public GreeterResponse(long requestId, boolean success) {
		super();
		this.requestId = requestId;
		this.success = success;
	}

	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
