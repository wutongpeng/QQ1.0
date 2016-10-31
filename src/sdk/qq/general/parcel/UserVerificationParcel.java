package sdk.qq.general.parcel;

import java.io.Serializable;

import sdk.qq.general.User;

public class UserVerificationParcel  implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1956551231810567245L;

	//消息类型
	private final MessageEnum message = MessageEnum.USER_VERIFICATION;
	//账户
	private final User user;
	
	public UserVerificationParcel(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public MessageEnum getMessage(){
		return message;
	}
}
