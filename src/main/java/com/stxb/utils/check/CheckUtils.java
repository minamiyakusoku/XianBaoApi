package com.stxb.utils.check;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * 各种类型校验
 * @author Souls
 *
 */
class CheckUtils {
 
    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_MOBILE;

    /**
     * 正则表达式：验证邮箱
     */
    private static final String REGEX_EMAIL;

    /**
     * 正则表达式：验证汉字
     */
    private static final String REGEX_CHINESE;

    /**
     * 正则表达式：验证身份证
     */
    private static final String REGEX_ID_CARD;
 
    /**
     * 正则表达式：验证URL
     */
    private static final String REGEX_URL;
 
    /**
     * 正则表达式：验证IP地址
     */
    private static final String REGEX_IP_ADDR;
	
    /**
     * 正则表达式：验证中文名
     */
    private static final String REGEX_NAME;

    static {
        REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        REGEX_NAME = "^(([\u4e00-\u9fa5]{2,8})|([a-zA-Z]{2,16}))$";
        REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        REGEX_ID_CARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
    }

    /**
     * 校验身份证
     * 
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
 
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
 
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * 校验汉字
     * 
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
 
    /**
     * 校验IP地址
     * 
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
	
    /**
     * 校验时间
     * @param Format
     * @param Time
     * @return 如果格式化失败返回false
     */
    public static boolean isTime(String Format,String Time)  
    {  
        try  
        {  
            SimpleDateFormat dateFormat = new SimpleDateFormat(Format);  
   
            dateFormat.parse(Time);
            return true;  
        }  
        catch (Exception e)  
        {  
            return false;  
        }  
    }  
	
    /**
     * 校验中文名
     * @param name
     * @return
     */
    public static boolean isName(String name){
        return Pattern.matches(REGEX_NAME, name);
    }
    
//	public static void main(String[] args) {
//		System.out.println(CheckUtils.isTime("YYYYMM","199909"));
//	}
}
