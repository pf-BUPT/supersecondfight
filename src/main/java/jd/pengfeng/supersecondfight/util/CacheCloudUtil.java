package jd.pengfeng.supersecondfight.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.jim.cli.Cluster;
import com.jd.si.jupiter.db.JimCacheCloud;

public class CacheCloudUtil {
	private static final Logger logger = LoggerFactory.getLogger(CacheCloudUtil.class);
	// 新缓存
	private static JimCacheCloud newMjqCloud = new JimCacheCloud
			("jim://2792067590312384811/1749", "jim://2792067590312384811/1749");

	public static void set(String key, String value) {
		logger.info("key:{} value:{}", key, value);
		getCluster(newMjqCloud).set(key, value);
	}

	public static String get(String key) {
		if(StringUtils.isEmpty(key)){
			return null;
		}

		String value = getCluster(newMjqCloud).get(key);
		if (StringUtils.isNotBlank(value)){
			logger.info("key:{} value:{}", key, value);
			return value;
		}
		logger.info("key:{} value:{}", key, value);
		return value;
	}

	public static void del(String key){
		if(StringUtils.isEmpty(key)){
			return;
		}
		getCluster(newMjqCloud).del(key);
	}

	public static void sadd(String key, String value) {
		getCluster(newMjqCloud).sAdd(key, value);
	}

	public static List<String> scan(String key) {
		List<String> rst = new ArrayList<>();
		for (String val = getCluster(newMjqCloud).sPop(key); !StringUtils.isEmpty(val);
			 val = getCluster(newMjqCloud).sPop(key)) {
			rst.add(val);
		}
		return rst;
	}

	private static Cluster getCluster(JimCacheCloud cloud) {
		Cluster cluster = cloud.getJimClient();
		// 避免赖加载模式下获取到没初始化完成的对象
		for(int i = 0; cluster == null && i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		if(cluster == null){
			logger.error("init cluster error");
		}

		return cluster;
	}

	public static void set(String key, String value,int expireDays) {
		getCluster(newMjqCloud).setEx(key,value,expireDays,TimeUnit.DAYS);
	}
	
	@SuppressWarnings("deprecation")
	public static Set<String> keys(String pattern){
		logger.info("keys parttern : {}", pattern);
		return getCluster(newMjqCloud).keys(pattern);
	}
}
