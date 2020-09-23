/**
 * Project Name:qywechat-client
 * File Name:SignatureVo.java
 * Package Name:com.gemii.qywechatclient.vo
 * Date:Mar 11, 20205:36:33 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package indi.wechat.work.pojo.wechat;

import java.io.Serializable;

import lombok.Data;

/**
 * ClassName:SignatureVo <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Mar 11, 2020 5:36:33 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Data
public class SignatureVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer agentid;
	private String corpId;
	private String signature;
	private String noncestr;
	private String ticket;
	private String timestamp;
	private String url;

	public SignatureVo(String corpId, String signature, String ticket, String noncestr, String timestamp, String url) {
		super();
		this.corpId = corpId;
		this.signature = signature;
		this.ticket = ticket;
		this.noncestr = noncestr;
		this.timestamp = timestamp;
		this.url = url;
	}

	public SignatureVo() {
		super();
	}

}
