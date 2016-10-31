package sdk.qq.general.parcel;

import java.io.Serializable;

public class NotThroughParcel  implements ParcelModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1956551231810567245L;

	//消息类型
	private final MessageEnum message = MessageEnum.NOT_THROUGH;

	public MessageEnum getMessage(){
		return message;
	}
}
