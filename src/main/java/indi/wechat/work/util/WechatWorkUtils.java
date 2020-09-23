/**
 * Project Name:qywechat-client
 * File Name:QyWechatUtils.java
 * Package Name:com.gemii.qywechatclient.utils
 * Date:2020年1月13日上午11:56:09
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package indi.wechat.work.util;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import indi.wechat.work.pojo.wechat.SignatureVo;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:QyWechatUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年1月13日 上午11:56:09 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Slf4j
public class WechatWorkUtils {

	private static final String signatureFormat = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";

	public static WXBizMsgCrypt createWxBizMsgCrypt(String ateken, String aencodingAesKey, String acorpId)
			throws AesException {
		return new WXBizMsgCrypt(ateken, aencodingAesKey, acorpId);
	}

	/*
	 * 验证回调Get URL--------------- 企业开启回调模式时，企业微信会向验证url发送一个get请求 假设点击验证时，企业收到类似请求：
	 * GET 接收到该请求时，企业应
	 * 1.解析出Get请求的参数，包括消息体签名(msg_signature)，时间戳(timestamp)，随机数字串(nonce)
	 * 以及企业微信推送过来的随机加密字符串(echostr), 这一步注意作URL解码.<br/> 2.验证消息体签名的正确性.<br/>
	 * 3.解密出echostr原文，将原文当作Get请求的response，返回给企业微信
	 * 第2，3步可以用企业微信提供的库函数VerifyURL来实现。<br/>
	 * 
	 */
	public static String verifyCallbackUrl(WXBizMsgCrypt wxcpt, String sVerifyMsgSig, String sVerifyTimeStamp, String sVerifyNonce,
			String sVerifyEchoStr) throws IOException {
		if (wxcpt == null) {
			throw new IOException("工具空");
		}
		if (sVerifyEchoStr == null) {
			return null;
		}
		try {
			String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
			return sEchoStr;
		} catch (Exception e) {
			// 验证URL失败，错误原因请查看异常
			log.error(e.getMessage(), e);
			throw new IOException(e.getMessage());
		}
	}

	/*
	 * 验证回调Post URL--------------- 企业开启回调模式时，企业微信会向验证url发送一个get请求 假设点击验证时，企业收到类似请求：
	 * GET 接收到该请求时，企业应
	 * 1.解析出Get请求的参数，包括消息体签名(msg_signature)，时间戳(timestamp)，随机数字串(nonce)
	 * 以及企业微信推送过来的随机加密字符串(echostr), 这一步注意作URL解码.<br/> 2.验证消息体签名的正确性.<br/>
	 * 3.解密出echostr原文，将原文当作Get请求的response，返回给企业微信
	 * 第2，3步可以用企业微信提供的库函数VerifyURL来实现。<br/>
	 * 
	 */
	public static String verifyPostCallbackUrl(WXBizMsgCrypt wxcpt, String sVerifyMsgSig, String sVerifyTimeStamp, String sVerifyNonce,
			String postData) throws IOException {
		if (wxcpt == null) {
			throw new IOException("工具空");
		}
		try {
			String sEchoStr = wxcpt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, postData);
			return sEchoStr;
		} catch (AesException e) {

			log.error(e.getMessage(), e);
			throw new IOException(e.getMessage());

		}
	}

	/*
	 * ------------对用户回复的消息解密---------------
	 * 用户回复消息或者点击事件响应时，企业会收到回调消息，此消息是经过企业微信加密之后的密文以post形式发送给企业，密文格式请参考官方文档
	 * 假设企业收到企业微信的回调消息如下： POST /cgi-bin/wxpush?
	 * msg_signature=477715d11cdb4164915debcba66cb864d751f3e6&timestamp=
	 * 1409659813&nonce=1372623149 HTTP/1.1 Host: qy.weixin.qq.com Content-Length:
	 * 613 <xml>
	 * <ToUserName><![CDATA[wx5823bf96d3bd56c7]]></ToUserName><Encrypt><![CDATA[
	 * RypEvHKD8QQKFhvQ6QleEB4J58tiPdvo+rtK1I9qca6aM/wvqnLSV5zEPeusUiX5L5X/
	 * 0lWfrf0QADHHhGd3QczcdCUpj911L3vg3W/
	 * sYYvuJTs3TUUkSUXxaccAS0qhxchrRYt66wiSpGLYL42aM6A8dTT+
	 * 6k4aSknmPj48kzJs8qLjvd4Xgpue06DOdnLxAUHzM6+kDZ+HMZfJYuR+
	 * LtwGc2hgf5gsijff0ekUNXZiqATP7PF5mZxZ3Izoun1s4zG4LUMnvw2r+KqCKIw+3IQH03v+
	 * BCA9nMELNqbSf6tiWSrXJB3LAVGUcallcrw8V2t9EL4EhzJWrQUax5wLVMNS0+
	 * rUPA3k22Ncx4XXZS9o0MBH27Bo6BpNelZpS+/ uh9KsNlY6bHCmJU9p8g7m3fVKn28H3KDYA5Pl/
	 * T8Z1ptDAVe0lXdQ2YoyyH2uyPIGHBZZIs2pDBS8R07+qN+E7Q==]]></Encrypt>
	 * <AgentID><![CDATA[218]]></AgentID> </xml>
	 * 
	 * 企业收到post请求之后应该
	 * 1.解析出url上的参数，包括消息体签名(msg_signature)，时间戳(timestamp)以及随机数字串(nonce)
	 * 2.验证消息体签名的正确性。
	 * 3.将post请求的数据进行xml解析，并将<Encrypt>标签的内容进行解密，解密出来的明文即是用户回复消息的明文，明文格式请参考官方文档
	 * 第2，3步可以用企业微信提供的库函数DecryptMsg来实现。
	 */
	public static String decrypyMsg(WXBizMsgCrypt wxcpt, String sReqMsgSig, String sReqTimeStamp, String sReqNonce, String sReqData)
			throws IOException {
		if (wxcpt == null) {
			throw new IOException("工具为空");
		}
		String sMsg = null;
		try {
			sMsg = wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, sReqData);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return sMsg;
	}

	/**
	 * 获取xml节点.<br/>
	 * getElementByTagName:(Description). <br/>
	 *
	 * @param sMsg
	 * @param tagName
	 * @return
	 */
	public static String getElementByTagName(String sMsg, String tagName) {
		// 解析出明文xml标签的内容进行处理
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		String content = null;
		try {
			db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(sMsg);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName(tagName);
			content = nodelist1.item(0)
					.getTextContent();
		} catch (ParserConfigurationException e) {
			log.error(e.getMessage(), e);
		} catch (SAXException e) {

			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);

		} catch (IOException e) {

			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);

		}

		return content;
	}

	/*
	 * ------------企业回复用户消息的加密--------------- 企业被动回复用户的消息也需要进行加密，并且拼接成密文格式的xml串。
	 * 假设企业需要回复用户的明文如下： <xml> <ToUserName><![CDATA[mycreate]]></ToUserName>
	 * <FromUserName><![CDATA[wx5823bf96d3bd56c7]]></FromUserName>
	 * <CreateTime>1348831860</CreateTime> <MsgType><![CDATA[text]]></MsgType>
	 * <Content><![CDATA[this is a test]]></Content> <MsgId>1234567890123456</MsgId>
	 * <AgentID>128</AgentID> </xml>
	 * 
	 * 为了将此段明文回复给用户，企业应：
	 * 1.自己生成时间时间戳(timestamp),随机数字串(nonce)以便生成消息体签名，也可以直接用从企业微信的post url上解析出的对应值。
	 * 2.将明文加密得到密文。 3.用密文，步骤1生成的timestamp,nonce和企业在企业微信设定的token生成消息体签名。
	 * 4.将密文，消息体签名，时间戳，随机数字串拼接成xml格式的字符串，发送给企业。 以上2，3，4步可以用企业微信提供的库函数EncryptMsg来实现。
	 */
	public static String encryptMsg(WXBizMsgCrypt wxcpt, String sRespData) throws IOException {
		String sReqTimeStamp = Long.toString(new Date().getTime());
		String sReqNonce = Long.toString(new Date().getTime());
		if (wxcpt == null) {
			throw new IOException("工具为空");
		}
		try {
			String sEncryptMsg = wxcpt.EncryptMsg(sRespData, sReqTimeStamp, sReqNonce);
			System.out.println("after encrypt sEncrytMsg: " + sEncryptMsg);
			return sEncryptMsg;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// 加密失败
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * 生成签名
	 * 
	 * @author youpan
	 * @param corpId
	 * @param ticket
	 * @param url
	 * @return
	 */
	public static SignatureVo createSignature(String corpId, String ticket, String url) {
		String noncestr = RandomUtil.randomString(10);
		String timestamp = String.valueOf(DateUtil.currentSeconds());
		try {
			String originStr = String.format(signatureFormat, ticket, noncestr, timestamp, url);
			log.info("originStr:{}", originStr);
			String signature = SecureUtil.sha1(originStr);
			log.info("digestStr:{}", signature);
			return new SignatureVo(corpId, signature, ticket, noncestr, timestamp, url);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	/**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

}
