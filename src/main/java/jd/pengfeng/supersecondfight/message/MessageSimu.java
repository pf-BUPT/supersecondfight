package jd.pengfeng.supersecondfight.message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jd.pengfeng.supersecondfight.bean.AppSuperSeckillSkuData;
import jd.pengfeng.supersecondfight.bean.DeliveryData;
import jd.pengfeng.supersecondfight.cache.App;
import jd.pengfeng.supersecondfight.util.GsonUtil;

/**
 * 超值秒拼 模拟生成发送的json消息 每个did对应的sku随机成过期的，有效的和未来有效的
 * 
 * @author pengfeng3
 *
 */
public class MessageSimu {

	static final long TWO_DAYS_MILLIS = 2 * 24 * 60 * 60 * 1000;
	static final String date1 = "2018-05-01";
	static final String date2 = "2018-07-01";
	static final String date3 = "2018-08-01";
	static final String date4 = "2018-010-01";
	static final Gson gson = GsonUtil.instance();
	final Logger logger = LoggerFactory.getLogger(MessageSimu.class);
	String skuFile;
	String jsonOutFile;
	long resId;
	public MessageSimu(String skuFile, String jsonOutFile,long resId) {
		super();
		this.skuFile = skuFile;
		this.jsonOutFile = jsonOutFile;
		this.resId = resId;
	}

	public void doSimu() throws IOException {
		// 1.读取sku list
		ArrayList<Long> list = new ArrayList<Long>();
		FileReader m;
		BufferedReader reader;
		StringBuffer sb = new StringBuffer();
		m = new FileReader(new File(skuFile));
		reader = new BufferedReader(m);
		while (true) {
			String nextline = reader.readLine();
			if (nextline == null)
				break;
			sb.append(nextline);
		}
		reader.close();
		list = gson.fromJson(sb.toString(), new TypeToken<List<Long>>() {
		}.getType());
		logger.info("sku size : {}",list.size());
		// 2.deliveryData
		DeliveryData deliveryData = new DeliveryData();
		deliveryData.setMsgAction("add");
		deliveryData.setMsgType("sku");
		deliveryData.setSysId("super_sec_fight");
		deliveryData.setResId(resId);
		deliveryData.setTs(System.currentTimeMillis());
		// 3.为每个sku随机生成start和end
		ArrayList<AppSuperSeckillSkuData> dataList = new ArrayList<AppSuperSeckillSkuData>();
		for (Long sku : list) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			AppSuperSeckillSkuData skuData = new AppSuperSeckillSkuData();
			skuData.setSku(sku);
			int random = new Random().nextInt(3);
			Date start, end;
			String startDateStr, endDateStr;
			switch (random) {
			case 0:
				// 过期的
				startDateStr = date1;
				endDateStr = date2;
				break;
			case 1:
				// 有效的
				startDateStr = date2;
				endDateStr = date3;
				break;
			case 2:
				// 未来有效的
				startDateStr = date3;
				endDateStr = date4;
				break;
			default:
				return;
			}
			try {
				start = format.parse(startDateStr);// 构造开始日期
				end = format.parse(endDateStr);// 构造结束日期
				skuData.setStartTime(start.getTime());
				skuData.setEndTime(end.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dataList.add(skuData);
		}
		// 4.写json消息到指令文件
		deliveryData.setData(gson.toJsonTree(dataList));
		FileWriter w = new FileWriter(new File(jsonOutFile));
		BufferedWriter writer = new BufferedWriter(w);
		writer.write(gson.toJson(deliveryData));
		writer.close();
	}
}
