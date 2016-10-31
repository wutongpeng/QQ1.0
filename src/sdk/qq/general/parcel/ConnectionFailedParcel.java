package sdk.qq.general.parcel;

import java.io.Serializable;


//这个应该是多余的
public class ConnectionFailedParcel implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -998253461261267572L;
	
	private final MessageEnum message = MessageEnum.CONNECTION_FAILED;
	
	public MessageEnum getMessage() {
		return message;
	}
}
