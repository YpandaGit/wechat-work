/**
 * Project Name:liz-qywechat-client
 * File Name:QyWechatCustomerTagApiService.java
 * Package Name:org.qywechat.client.service.wechat
 * Date:Apr 7, 20202:16:28 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package indi.wechat.work.api;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName:QyWechatCustomerTagApiService <br/>
 * Function: 企微标签管理接口 <br/>
 * Date: Apr 7, 2020 2:16:28 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@FeignClient(
        name = "qyWechatCustomerTagApiService",
        url = "https://qyapi.weixin.qq.com")
public interface QyWechatCustomerTagApiService {
    /**
     * 获取企业标签库.<br/>
     * 企业可通过此接口获取企业客户标签详情<br/>
     * /cgi-bin/gettoken?corpid=&corpsecret=SECRET
     * 
     * @param accessToken
     * @param paramMap    { "tag_id": [ "etXXXXXXXXXX", "etYYYYYYYYYY" ]
     *                    }要查询的标签id，如果不填则获取该企业的所有客户标签，目前暂不支持标签组id
     * @return
     */
    @PostMapping("/cgi-bin/externalcontact/get_corp_tag_list")
    public JSONObject getCorpTagList(@RequestParam("access_token") String accessToken, @RequestBody Map<String, Object> paramMap);

    /**
     * 添加企业客户标签<br>
     * 企业可通过此接口向客户标签库中添加新的标签组和标签。
     * 
     * @author youpan
     * @param accessToken
     * @param paramMap    { "group_id": "GROUP_ID", "group_name": "GROUP_NAME",
     *                    "order": 1, "tag": [{ "name": "TAG_NAME_1", "order": 1 },
     *                    { "name": "TAG_NAME_2", "order": 2 } ] }
     * @return
     */
    @PostMapping("/cgi-bin/externalcontact/add_corp_tag")
    public JSONObject addCorpTag(@RequestParam("access_token") String accessToken, @RequestBody Map<String, Object> paramMap);

    /**
     * 编辑企业客户标签<br>
     * 企业可通过此接口编辑客户标签/标签组的名称或次序值。
     * 
     * @author youpan
     * @param accessToken
     * @param paramMap    { "id": "TAG_ID", "name": "NEW_TAG_NAME", "order": 1 }
     * @return
     */
    @PostMapping("/cgi-bin/externalcontact/edit_corp_tag")
    public JSONObject editCorpTag(@RequestParam("access_token") String accessToken, @RequestBody Map<String, Object> paramMap);

    /**
     * 删除企业客户标签<br>
     * 企业可通过此接口删除客户标签库中的标签，或删除整个标签组。<br>
     * tag_id和group_id不可同时为空。 如果一个标签组下所有的标签均被删除，则标签组会被自动删除。
     * 
     * @author youpan
     * @param accessToken
     * @param paramMap    { "tag_id": [ "TAG_ID_1", "TAG_ID_2" ], "group_id": [
     *                    "GROUP_ID_1", "GROUP_ID_2" ] }
     * @return
     */
    @PostMapping("/cgi-bin/externalcontact/del_corp_tag")
    public JSONObject delCorpTag(@RequestParam("access_token") String accessToken, @RequestBody Map<String, Object> paramMap);
}
