package jd.pengfeng.supersecondfight.cache;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jd.pengfeng.supersecondfight.bean.AppSuperSeckillSkuData;
import jd.pengfeng.supersecondfight.bean.ResStatistics;
import jd.pengfeng.supersecondfight.exception.PrameterException;
import jd.pengfeng.supersecondfight.util.CacheCloudUtil;
import jd.pengfeng.supersecondfight.util.GsonUtil;

/**
 * 查询redis中指定开头的key，并统计resid和对应的sku信息 缓存中的key格式：sysID_ResId
 * 
 * @author pengfeng3
 */
public class CacheStatistics {

	final Gson gson = GsonUtil.instance();
	final Logger logger = LoggerFactory.getLogger(CacheStatistics.class);

	String sysID;
	
	public CacheStatistics(String sysID) {
		super();
		this.sysID = sysID;
	}

	public void doStatistics() {
		// 1.查询pattern 对应的key
		HashSet<String> keySet = new HashSet<String>(CacheCloudUtil.keys(sysID + "*"));
		// 2.统计did和sku信息
		logger.info("key count: {}", keySet.size());
		for (String key : keySet) {
			if (key.length() <= sysID.length()) {
				logger.warn("wrong key : {}", key);
				continue;
			}
			String resid = key.substring(key.indexOf(sysID) + sysID.length() + 1);
			String value = CacheCloudUtil.get(key);
			List<AppSuperSeckillSkuData> skuList = gson.fromJson(value, new TypeToken<List<AppSuperSeckillSkuData>>() {
			}.getType());
			int skuCount = 0, validSkuCount = 0, expiredSkuNum = 0, futureValidSkuNum = 0;
			for (AppSuperSeckillSkuData sku : skuList) {
				skuCount++;
				long currentTimeStamp = System.currentTimeMillis();
				if (currentTimeStamp < sku.getEndTime() && currentTimeStamp > sku.getStartTime())
					validSkuCount++;
				if (currentTimeStamp >= sku.getEndTime())
					expiredSkuNum++;
				if (currentTimeStamp <= sku.getStartTime())
					futureValidSkuNum++;
			}
			ResStatistics resStatistic = new ResStatistics();
			resStatistic.setResid(resid);
			resStatistic.setSkuNum(skuCount);
			resStatistic.setValidSkuNum(validSkuCount);
			resStatistic.setExpiredSkuNum(expiredSkuNum);
			resStatistic.setFutureValidSkuNum(futureValidSkuNum);
			logger.info("缓存统计结果——ResId:{} 有效sku:{} 过期sku:{} 未来有效sku:{}", resStatistic.getResid(),
					resStatistic.getValidSkuNum(), resStatistic.getExpiredSkuNum(),resStatistic.getFutureValidSkuNum());
			System.out.println(resStatistic.toString());
		}
	}
}
