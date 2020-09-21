/**
 * Project Name:qywechat-client
 * File Name:QyWechatApiService.java
 * Package Name:com.gemii.qywechat.client.retrofit
 * Date:2020年1月10日下午1:52:32
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package indi.wechat.work.api;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName:QyWechatApiService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年1月10日 下午1:52:32 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@FeignClient(
        name = "qyWechatApiService",
        url = "https://qyapi.weixin.qq.com")
public interface QyWechatApiService {

    /**
     * ##########通讯录相关###########
     */
    /**
     * 获取企业微信应用accessToken.<br/>
     * getGroupCodeById:(Description). <br/>
     * /cgi-bin/gettoken?corpid=&corpsecret=SECRET
     * 
     * @param groupId
     * @return
     */
    @GetMapping("/cgi-bin/gettoken")
    public JSONObject getQyWechatAccesstoken(@RequestParam("corpid") String corpid, @RequestParam("corpsecret") String corpsecret);

    /**
     * 读取企业微信成员.<br/>
     * getAllAgentUser:(Description). <br/>
     * https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID
     * 
     * @return
     */
    @GetMapping("/cgi-bin/user/get")
    public JSONObject getAgentUser(@RequestParam("access_token") String accessToken, @RequestParam("userid") String userid);

    /**
     * 更新企业成员.<br/>
     * updateAgentUser:(Description). <br/>
     *
     * @param accessToken
     * @param reqParam
     * @return
     */
    @PostMapping("/cgi-bin/user/update")
    public JSONObject updateAgentUser(@RequestParam("access_token") String accessToken, @RequestBody Object reqParam);

    /**
     * 更新企业成员.<br/>
     * updateAgentUser:(Description). <br/>
     *
     * @param accessToken
     * @param userid
     * @return
     */
    @PostMapping("/cgi-bin/user/update")
    public JSONObject deleteAgentUser(@RequestParam("access_token") String accessToken, @RequestParam("userid") String userid);

    /**
     * 获取部门列表.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID
     * getAgentDepartment:(Description). <br/>
     *
     * @param accessToken
     * @param id
     * @return
     */
    @GetMapping("/cgi-bin/department/list")
    public JSONObject getAgentDepartment(@RequestParam("access_token") String accessToken, @RequestParam(
            value = "id",
            required = false) String id);

    /**
     * 获取部门成员.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD
     * getAgentDeptUserList:(Description). <br/>
     *
     * @param accessToken
     * @param departmentId 获取的部门id
     * @param fetchChild   是否递归获取子部门下面的成员：1-递归获取，0-只获取本部门
     * @return
     */
    @GetMapping("/cgi-bin/user/simplelist")
    public Object getAgentDeptUserList(@RequestParam("access_token") String accessToken, @RequestParam("department_id") String departmentId,
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
    public JSONObject getAgentDeptCmpxUserList(@RequestParam("access_token") String accessToken, @RequestParam("department_id") Integer departmentId,
            @RequestParam("fetch_child") Integer fetchChild);

    /**
     * 获取标签成员.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=TAGID
     * getTagUserList:(Description). <br/>
     *
     * @param accessToken
     * @param tagid
     * @return
     */
    @GetMapping("/cgi-bin/tag/get")
    public Object getTagUserList(@RequestParam("access_token") String accessToken, @RequestParam("tagid") String tagid);

    /**
     * ##########外部联系人相关###########
     */
    /**
     * 获取配置了客户联系功能的成员列表.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_follow_user_list?access_token=ACCESS_TOKEN
     * getExternalcontact:(Description). <br/>
     *
     * @param accessToken
     * @return
     */
    @GetMapping("/cgi-bin/externalcontact/get_follow_user_list")
    public JSONObject getExternalcontact(@RequestParam("access_token") String accessToken);

    /**
     * 获取客户列表.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list?access_token=ACCESS_TOKEN&userid=USERID
     * getExternalContactList:(Description). <br/>
     *
     * @param accessToken
     * @param userid
     * @return
     */
    @GetMapping("/cgi-bin/externalcontact/list")
    public JSONObject getExternalContactList(@RequestParam("access_token") String accessToken, @RequestParam("userid") String userid);

    /**
     * 获取客户详情.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get?access_token=ACCESS_TOKEN&external_userid=EXTERNAL_USERID
     * getExternalContactDetail:(Description). <br/>
     *
     * @param accessToken
     * @param userid
     * @return
     */
    @GetMapping("/cgi-bin/externalcontact/get")
    public JSONObject getExternalContactDetail(@RequestParam("access_token") String accessToken, @RequestParam("external_userid") String externalUserid);

    /**
     * ##########BOT机器人相关###########
     */
    /**
     * 通过Key指定的bot发送消息到群.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=39ec21a7-e717-4630-9a27-d5e20cc2b4cd
     * postWebhookMsg:(Description). <br/>
     *
     * @param contentType
     * @param key
     * @param msg
     */
    @PostMapping("/cgi-bin/webhook/send")
    public Object postWebhookMsg(@RequestHeader("Content-Type") String contentType, @RequestParam("key") String key, @RequestBody Object msg);

    /**
     * 获取客户群列表.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/list?access_token=ACCESS_TOKEN
     * 
     *
     * @param accessToken
     * @param reqParam
     * @return
     */
    @PostMapping("/cgi-bin/externalcontact/groupchat/list")
    public Object getGroupCharList(@RequestParam("access_token") String accessToken, @RequestBody Object reqParam);

    /**
     * 获取客户群详情.<br/>
     * https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/get?access_token=ACCESS_TOKEN
     * 
     *
     * @param accessToken
     * @param reqParam
     * @return
     */
    @PostMapping("/cgi-bin/externalcontact/groupchat/get")
    public Object getGroupCharInfo(@RequestParam("access_token") String accessToken, @RequestBody Object reqParam);

    /**
     * 编辑客户企业标签.<br/>
     *
     * @param accessToken
     * @param reqParam
     * @return
     */
    @PostMapping("/cgi-bin/externalcontact/mark_tag")
    public JSONObject markTag(@RequestParam("access_token") String accessToken, @RequestBody Object reqParam);

    /**
     * 上传临时素材
     * 
     * @param accessToken
     * @param type
     * @param file
     * @return
     */
    @PostMapping(
            value = "/cgi-bin/media/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JSONObject upload(@RequestParam("access_token") String accessToken, @RequestParam("type") String type, @RequestPart("file") MultipartFile file);

    /**
     * 上传素材图片
     *
     * @param accessToken
     * @param file
     * @return
     */
    @PostMapping(
            value = "/cgi-bin/media/uploadimg",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JSONObject uploadImg(@RequestParam("access_token") String accessToken, @RequestPart("file") MultipartFile file);

    /**
     * 获取企业已配置的「联系我」方式
     * 
     * @param accessToken
     * @param configId    { "config_id":"42b34949e138eb6e027c123cba77fad7" }
     * @return
     */
    @PostMapping(
            value = "/cgi-bin/externalcontact/get_contact_way")
    public JSONObject getContactWay(@RequestParam("access_token") String accessToken, @RequestBody Map<String, String> configId);

    /**
     * 删除企业已配置的「联系我」方式
     * 
     * @param accessToken
     * @param configId    { "config_id":"42b34949e138eb6e027c123cba77fad7" }
     * @return
     */
    @PostMapping(
            value = "/cgi-bin/externalcontact/del_contact_way")
    public JSONObject delContactWay(@RequestParam("access_token") String accessToken, @RequestBody Map<String, String> configId);

}
