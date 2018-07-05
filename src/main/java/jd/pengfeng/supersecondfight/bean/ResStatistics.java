package jd.pengfeng.supersecondfight.bean;

/**
 * 超值秒拼 缓存的统计结果
 * @author pengfeng3
 *
 */
public class ResStatistics {

	public String resid;
	public int skuNum;
	public int expiredSkuNum;
	public int validSkuNum;
	public int futureValidSkuNum;
	public String getResid() {
		return resid;
	}
	public void setResid(String resid) {
		this.resid = resid;
	}
	public int getSkuNum() {
		return skuNum;
	}
	public void setSkuNum(int skuNum) {
		this.skuNum = skuNum;
	}
	public int getValidSkuNum() {
		return validSkuNum;
	}
	public void setValidSkuNum(int validSkuNum) {
		this.validSkuNum = validSkuNum;
	}
	
	public int getFutureValidSkuNum() {
		return futureValidSkuNum;
	}
	public void setFutureValidSkuNum(int futureValidSkuNum) {
		this.futureValidSkuNum = futureValidSkuNum;
	}
	public int getExpiredSkuNum() {
		return expiredSkuNum;
	}
	public void setExpiredSkuNum(int expiredSkuNum) {
		this.expiredSkuNum = expiredSkuNum;
	}
	@Override
	public String toString() {
		return "ResStatistics [resid=" + resid + ", skuNum=" + skuNum + ", expiredSkuNum=" + expiredSkuNum
				+ ", validSkuNum=" + validSkuNum + ", futureValidSkuNum=" + futureValidSkuNum + "]";
	}
	
}
