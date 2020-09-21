/**
 * Project Name:qywechat-client
 * File Name:QyWechatServiceApiService.java
 * Package Name:com.gemii.qywechatclient.client
 * Date:2020年3月8日下午5:22:59
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package indi.wechat.work.api;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName:QyWechatServiceApiService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年3月8日 下午5:22:59 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@FeignClient(
        name = "qyWechatServiceApiService",
        url = "https://qyapi.weixin.qq.com")
public interface QyWechatServiceApiService {

    /**
     * 获取企业微信应用accessToken.<br/>
     * getGroupCodeById:(Description). <br/>
     * /cgi-bin/service/get_suite_token?corpid=&corpsecret=SECRET
     * 
     * @param groupId
     * @return
     */
    @PostMapping("/cgi-bin/service/get_suite_token")
    public JSONObject getQyWechatAccesstoken(@RequestBody Map<String, String> param);

    /**
     * 获取访问用户身份
     * 
     * @author youpan
     * @param suiteAccessToken
     * @return
     */
    @GetMapping("/cgi-bin/service/getuserinfo3rd")
    public JSONObject getUserinfo3rd(@RequestParam("suite_access_token") String suiteAccessToken, @RequestParam("code") String code);

    /**
     * 获取登录用户信息
     * 
     * @author youpan
     * @param providerAccessToken
     * @param map                 {"auth_code":"xxxxx"}
     * @return
     */
    @GetMapping("/cgi-bin/service/get_login_info")
    public JSONObject getLoginInfo(@RequestParam("access_token") String providerAccessToken, @RequestBody Map<String, String> map);

    /**
     * 获取访问用户详细身份
     * 
     * @author youpan
     * @param suiteAccessToken
     * @return
     */
    @PostMapping("/cgi-bin/service/getuserdetail3rd")
    public JSONObject getUserDetail3rd(@RequestParam("suite_access_token") String suiteAccessToken, @RequestBody Map<String, String> map);

    /**
     * 获取JSAPI-TICKET
     * 
     * @author youpan
     * @param suiteAccessToken
     * @return
     */
    @GetMapping("/cgi-bin/get_jsapi_ticket")
    public JSONObject getJsapiTicket(@RequestParam("access_token") String accessToken);

    /**
     * 获取临时授权码
     * 
     * @author youpan
     * @param suiteAccessToken
     * @return
     */
    @GetMapping("/cgi-bin/service/get_pre_auth_code")
    public JSONObject getQyWechatPreAccesstoken(@RequestParam("suite_access_token") String suiteAccessToken);

    /**
     * 获取ticket,agentConfig注入使用
     * 
     * @author youpan
     * @param accessToken
     * @param type        固定agent_config
     * @return
     */
    @GetMapping("/cgi-bin/ticket/get")
    public JSONObject getJsApiTicketFroAgentConfig(@RequestParam("access_token") String accessToken, @RequestParam("type") String type);

    /**
     * 获取部门成员列表.<br/>
     * getAgentDeptSimpleList:(Description). <br/>
     *
     * @param accessToken
     * @param departmentId
     * @param fetchChild   1/0 是否递归获取
     * @return
     */
    @GetMapping("/cgi-bin/user/simplelist")
    public Object getAgentDeptSimpleList(@RequestParam("access_token") String accessToken, @RequestParam("department_id") String departmentId,
            @RequestParam("fetch_child") String fetchChild);

    /**
     * 获取部门成员详情.<br/>
     * getAgentDeptCmpxUserList:(Description). <br/>
     * https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD
     * 
     * @param accessToken
     * @param departmentId
     * @param fetchChild
     * @return
     */
    @GetMapping("/cgi-bin/user/list")
    public Object getAgentDeptCmpxUserList(@RequestParam("access_token") String accessToken, @RequestParam("department_id") String departmentId,
            @RequestParam("fetch_child") String fetchChild);

    /**
     * 推送消息.<br/>
     * ssss sendApplicationMsg:(Description). <br/>
     *
     * @return
     */
    @PostMapping("/cgi-bin/message/send")
    public JSONObject sendApplicationMsg(@RequestParam("access_token") String accessToken, @RequestBody Map<String, Object> param);

    /**
     * 获取企业永久授权码.<br/>
     * getPermentCode:(Description). <br/>
     *
     * @param suitAccessToken
     * @param param
     * @return
     */
    @PostMapping("/cgi-bin/service/get_permanent_code")
    public JSONObject getPermentCode(@RequestParam("suite_access_token") String suitAccessToken, @RequestBody Map<String, String> param);

    /**
     * 获取ACCESS_TOKEN
     * 
     * @author youpan
     * @param suiteAccessToken
     * @param param
     * @return
     */
    @PostMapping("/cgi-bin/service/get_corp_token")
    public JSONObject getAccessToken(@RequestParam("suite_access_token") String suiteAccessToken, @RequestBody Map<String, String> param);

    /**
     * 获取服务商ACCESS_TOKEN
     * 
     * @author youpan
     * @param param {"corpid":"xxxxx","provider_secret":"xxx"}
     * @return
     */
    @PostMapping("/cgi-bin/service/get_provider_token")
    public JSONObject getProviderAccessToken(@RequestBody Map<String, String> param);

    /**
     * 获取应用的管理员列表
     * 
     * @author youpan
     * @param param { "auth_corpid": "auth_corpid_value", "agentid": 1000046 }
     * @return
     */
    @PostMapping("/cgi-bin/service/get_admin_list")
    public JSONObject getAdminList(@RequestParam("suite_access_token") String suiteAccessToken, @RequestBody Map<String, Object> param);

    /**
     * 自建应用获取访问用户身份
     * 
     * @author youpan
     * @param accessToken
     * @param code
     * @return { "errcode": 0, "errmsg": "ok", "UserId":"USERID",
     *         "DeviceId":"DEVICEID" }
     */
    @GetMapping("/cgi-bin/user/getuserinfo")
    public JSONObject getuserinfo(@RequestParam("access_token") String accessToken, @RequestParam("code") String code);
}
