package indi.wechat.work.service;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import indi.wechat.work.WechatWorkApplication;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WechatWorkApplication.class)
@Slf4j
public class WechatServiceTest {
	@Rule
	public ContiPerfRule i = new ContiPerfRule();
//	@Autowired
//	private WechatService wechatService;
	@Autowired
	private ContactService contactService;
	

	@Test
	@PerfTest(invocations = 100, threads = 10)
	@Required(max = 400, average = 150)
	public void testGetAccessToken() {
		String corpId = "ww751198e5ade84f15";
		String secret = "s5bXGMaOEQkZUetpSx3B_FODtLJiBDRgORQlTn6e-q8";
//		String accessToken = wechatService.getAccessToekn(corpId, secret);
//		log.info("access_token:{}", accessToken);
		contactService.getDepartments(corpId, secret);
		log.info("1");
//		log.info("departments:{}", resp);
	}

}
