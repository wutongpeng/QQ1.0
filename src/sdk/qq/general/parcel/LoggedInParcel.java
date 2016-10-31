package sdk.qq.general.parcel;

import java.io.Serializable;

public class LoggedInParcel implements ParcelModel, Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3642094832934367141L;
	
	private final MessageEnum message = MessageEnum.LOGGED_IN;
	
	public MessageEnum getMessage() {
		return message;
	}
}
