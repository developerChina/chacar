package org.core.util;

import java.text.SimpleDateFormat;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
/**
 * 门禁下发控制
 * @author Administrator
 *
 */
public class AControlUtil {
	// 和webService服务器交互
	// webService命名空间
	static final String namespace = "http://tempuri.org/";
	// 访问对象地址
	static final String transUrl = "http://192.168.1.18:8081/MJService.asmx";
	// 后台询问或者从wsdl文档或者服务说明中查看
	static final int envolopeVersion = SoapEnvelope.VER12;

	public static void main(String[] args) {
		//DeleUserCard("192.168.1.178", 4001, 1, "0000", "000000", "012F0984E0");
		//AddUserCard("192.168.1.178", 4001, 1,"0000", "000000","192","012F0984E0","0000","2017-11-11 11:11:11");
		while(true) {
			AddUserCard("192.168.1.178", 4001, 1,"0000", "000000","192","012F0984E0","0000","2017-11-11 11:11:11");
			try {
				Thread.sleep(1000*5); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ReadCardLog("192.168.1.178", 4001, 1, "0000", "000000");
			try {
				Thread.sleep(1000*5); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        DeleUserCard("192.168.1.178", 4001, 1, "0000", "000000", "012F0984E0");
	        try {
				Thread.sleep(1000*5); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static String ReadCardLog(String mjIP, int mjPort, int netId, String szSysPwd, String szKeyPwd) {
		String method = "ReadCardLog";
		SoapObject request = new SoapObject(namespace, method);
		request.addProperty("mjIP", mjIP);//
		request.addProperty("mjPort", mjPort);//
		request.addProperty("netId", netId);//
		request.addProperty("szSysPwd", szSysPwd);//
		request.addProperty("szKeyPwd", szKeyPwd);//

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(envolopeVersion);
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		HttpTransportSE httpse = new HttpTransportSE(transUrl);
		Object response = null;
		try {
			httpse.call(null, envelope);
			if (envelope.getResponse() != null) {
				response = (Object) envelope.getResponse();
				System.out.println("=签离人员==:" + response.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

	/**
	 * 
	 * @param mjIP
	 * @param mjPort
	 * @param netId
	 * @param szSysPwd
	 * @param szKeyPwd
	 * @param txtCardNo
	 * @return
	 */
	public static String DeleUserCard(String mjIP, int mjPort, int netId, String szSysPwd, String szKeyPwd,
			String txtCardNo) {
		String method = "DeleUserCard";
		SoapObject request = new SoapObject(namespace, method);
		request.addProperty("mjIP", mjIP);//
		request.addProperty("mjPort", mjPort);//
		request.addProperty("netId", netId);//
		request.addProperty("szSysPwd", szSysPwd);//
		request.addProperty("szKeyPwd", szKeyPwd);//
		request.addProperty("txtCardNo", txtCardNo);//

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(envolopeVersion);
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		HttpTransportSE httpse = new HttpTransportSE(transUrl);
		Object response = null;
		try {
			httpse.call(null, envelope);
			if (envelope.getResponse() != null) {
				response = (Object) envelope.getResponse();
				System.out.println("=删除人员成功==:" + response.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/***
	 * 
	 * @param mjIP
	 * @param mjPort
	 * @param netId
	 * @param szSysPwd
	 * @param szKeyPwd
	 * @param txtDooRight
	 * @param txtCardNo
	 * @param txtDooPwd
	 * @param dt
	 * @return
	 */
	public static String AddUserCard(String mjIP, int mjPort, int netId, String szSysPwd, String szKeyPwd,
			String txtDooRight, String txtCardNo, String txtDooPwd, String dt) {
		String method = "AddUserCard";
		SoapObject request = new SoapObject(namespace, method);
		request.addProperty("mjIP", mjIP);//
		request.addProperty("mjPort", mjPort);//
		request.addProperty("netId", netId);//
		request.addProperty("szSysPwd", szSysPwd);//
		request.addProperty("szKeyPwd", szKeyPwd);//

		request.addProperty("txtDooRight", txtDooRight);//
		request.addProperty("txtCardNo", txtCardNo);//
		request.addProperty("txtDooPwd", txtDooPwd);//

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String str = df.format(DateUtil.StringToDate(dt, "yyyy-MM-dd HH:mm:ss"));
		request.addProperty("dt", str);//

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(envolopeVersion);
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		HttpTransportSE httpse = new HttpTransportSE(transUrl);
		Object response = null;
		try {
			httpse.call(null, envelope);
			if (envelope.getResponse() != null) {
				response = (Object) envelope.getResponse();
				System.out.println("=添加人员=:" + response.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
