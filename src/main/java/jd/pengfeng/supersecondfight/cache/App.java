package jd.pengfeng.supersecondfight.cache;

import jd.pengfeng.supersecondfight.exception.PrameterException;

public class App 
{
	/**
	 * 查询redis中指定开头的key，并统计resid和对应的sku信息
	 * @param args SysID
	 */
    public static void main(String[] args)
    {
    	/*
    	if(args == null || args.length != 1){
    		throw new PrameterException("The number of parameter is wrong!");
    	}
    	*/
    	String SysId = "super_sec_fight";
    	CacheStatistics cacheStatistics  = new CacheStatistics(SysId);
    	cacheStatistics.doStatistics();
    }
}
