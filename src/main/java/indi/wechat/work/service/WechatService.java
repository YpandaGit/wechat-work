package indi.wechat.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import indi.wechat.work.api.QyWechatApiService;

@Service
public class WechatService {
	@Autowired
	private QyWechatApiService qyWechatApiService;

	/**
	 * 获取自建应用ACCES_TOKEN
	 * 
	 * @param corpId
	 * @param agentId
	 * @return
	 */
	public String getAccessToekn(String corpId, String secret) {
		JSONObject resp = qyWechatApiService.getQyWechatAccesstoken(corpId, secret);
		return resp.getString("access_token");
	}
}
