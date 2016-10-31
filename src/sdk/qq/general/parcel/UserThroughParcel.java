package sdk.qq.general.parcel;

import java.io.Serializable;

import sdk.qq.general.UserInformation;

public class UserThroughParcel implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1956551231810567245L;

	//消息类型
	private final MessageEnum message = MessageEnum.USER_THROUGH;
	
	private final UserInformation user;
	
	public UserThroughParcel(UserInformation user){
		this.user = user;
	} 
	
	public MessageEnum getMessage(){
		return message;
	}
	
	public UserInformation getUserInformation(){
		return user;
	}
}
