package indi.wechat.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.wechat.work.api.QyWechatApiService;

@Service
public class ContactService {
	@Autowired
	private QyWechatApiService qyWechatApiService;
	@Autowired
	private WechatService wechatService;

	public Object getDepartments(String corpId, String secret) {
		String accessToken = wechatService.getAccessToekn(corpId, secret);
		return qyWechatApiService.getAgentDepartment(accessToken, null);
	}

}
