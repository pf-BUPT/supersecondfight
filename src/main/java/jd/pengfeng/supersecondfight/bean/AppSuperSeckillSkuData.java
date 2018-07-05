package jd.pengfeng.supersecondfight.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 超值秒拼 sku的缓存对象
 * @author pengfeng3
 *
 */
public class AppSuperSeckillSkuData {
	@SerializedName("sku")
    private long sku;
	@SerializedName("start_time")
    public long startTime;
	@SerializedName("end_time")
    public long endTime;
	@SerializedName("imageRgb")
    private Integer imageRgb;

	public long getSku() {
		return sku;
	}
	public void setSku(long sku) {
		this.sku = sku;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public Integer getImageRgb() {
		return imageRgb;
	}
	public void setImageRgb(Integer imageRgb) {
		this.imageRgb = imageRgb;
	}
	@Override
	public String toString() {
		return "AppSuperSeckillSkuData [sku=" + sku + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", imageRgb=" + imageRgb + "]";
	}
	
}
