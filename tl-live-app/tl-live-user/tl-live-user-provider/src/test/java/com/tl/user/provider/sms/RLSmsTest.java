package com.tl.user.provider.sms;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Author： roy
 * Description： 对接容联云，发送短信测试
 **/
public class RLSmsTest {

    public static void main(String[] args) {
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //accountSId: 2c94811c92f9eb9801934a33728215c0
        //accountToken: 8657bcb141d94d1085f06b86cb9fb43e
        //appId: 2c94811c92f9eb9801934a33743015c7
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "2c94811c92f9eb9801934a33728215c0";
        String accountToken = "8657bcb141d94d1085f06b86cb9fb43e";
        //请使用管理控制台中已创建应用的APPID
        String appId = "2c94811c92f9eb9801934a33743015c7";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        //测试阶段，消息只发送到测试号码
        String to = "19047109270";
        //测试模板ID为1，模板内容：【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入
        String templateId= "1";
        //自己生成随机验证码
        int code = new Random().nextInt(1000, 9999);
        //模板数据
        String[] datas = {String.valueOf(code),"5",};
        String subAppend="1234";  //可选 扩展码，四位数字 0~9999
        String reqId="fadfafas114";  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas,subAppend,reqId);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }
}
