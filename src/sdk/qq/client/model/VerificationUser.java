package sdk.qq.client.model;

import java.net.*;
import java.io.*;

import sdk.qq.general.*;
import sdk.qq.general.parcel.*;

public class VerificationUser {

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	//���ӷ���������ѯ�������Ƿ�ӵ�и��˺�
	public ParcelModel askServer(User user){		
		
		//������Ϣ�İ�
		UserVerificationParcel send = new UserVerificationParcel(user);	
		//������Ϣ�İ�
		ParcelModel message = null;		
		try {
			socket = new Socket("127.0.0.1", 18010);		
			System.out.println("���ӷ�����");
			//ȡ�����ӹܵ��������˺���Ϣ��������
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());			
			objectOutputStream.writeObject(send);
			//��ȡ���������ص���Ϣ
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			try {
				message = (ParcelModel) objectInputStream.readObject();				
			} catch (ClassNotFoundException e) {
				message = new ErrorParcel();
				e.printStackTrace();
			}			
		} catch (IOException e) {
			message = new ConnectionFailedParcel();
			e.printStackTrace();
		}
		//���ط��������ݹ�������Ϣ
		return message;
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	//�ر���Դ
	public void close(){
		try {
			if(objectInputStream != null){
				
				if(objectOutputStream != null){
					objectOutputStream.close();
				}
				objectInputStream.close();
				socket.close();
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}