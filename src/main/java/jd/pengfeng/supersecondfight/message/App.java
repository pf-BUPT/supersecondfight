package jd.pengfeng.supersecondfight.message;

import java.io.IOException;

/**
 * 超值秒拼
 * 模拟发送消息的 json数据
 * @author pengfeng3
 *
 */
public class App {

	public static void main(String[] args) {
		String skuFile = args[0];
		String jsonOutFile = args[1];
		long resId = Long.parseLong(args[2]);
		MessageSimu messageSimu = new MessageSimu(skuFile, jsonOutFile,resId);
		try {
			messageSimu.doSimu();
		} catch (IOException e) {
			System.out.println("doSimu fail : " + e);
			e.printStackTrace();
		}
	}

}
