package jd.pengfeng.supersecondfight.bean;


import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * MQ消息对象
 * @author pengfeng3
 *
 */
public class DeliveryData {

    @SerializedName("sys_id")
    public String sysId;
    @SerializedName("res_id")
    public long resId;
    public long ts;
    @SerializedName("msg_type")
    public String msgType;
    @SerializedName("msg_action")
    public String msgAction;
    
    public JsonElement data;

    public DeliveryData() {

    }

    public DeliveryData(String sysId, long resId, long ts, String msgType, String msgAction, JsonElement data) {
        this.sysId = sysId;
        this.resId = resId;
        this.ts = ts;
        this.msgType = msgType;
        this.msgAction = msgAction;
        this.data = data;
    }

	@Override
	public String toString() {
		return "DeliveryData [sysId=" + sysId + ", resId=" + resId + ", ts=" + ts + ", msgType=" + msgType
				+ ", msgAction=" + msgAction + ", data=" + data + "]";
	}
    
    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgAction() {
        return msgAction;
    }

    public void setMsgAction(String msgAction) {
        this.msgAction = msgAction;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
