package com.spring.boot.qingcloud;

import com.alibaba.fastjson.JSONObject;
import com.qingcloud.sdk.config.EnvContext;
import com.qingcloud.sdk.exception.QCException;
import com.qingcloud.sdk.service.InstanceService;
import com.qingcloud.sdk.service.Types;
import com.qingcloud.sdk.service.VolumeService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@SpringBootTest
class SpringBootQingcloudApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(SpringBootQingcloudApplicationTests.class);

    @Value("${qing.cloud.qy_access_key_id}")
    private String qyAccessKeyId;

    @Value("${qing.cloud.qy_secret_access_key}")
    private String qySecretAccessKey;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void testDescribeInstances() {
        System.out.println(qyAccessKeyId);
        System.out.println(qySecretAccessKey);

        EnvContext context = new EnvContext(qyAccessKeyId, qySecretAccessKey);
        context.setProtocol("https");
        context.setHost("api.qingcloud.com");
        context.setPort("443");
        context.setZone("pek3b");
        context.setApiLang("zh-cn"); // optional, set return message i18n, default to us-en

        InstanceService service = new InstanceService(context);

        InstanceService.DescribeInstancesInput input = new InstanceService.DescribeInstancesInput();
        input.setLimit(1);

        try {
            InstanceService.DescribeInstancesOutput output = service.describeInstances(input);

            for (Types.InstanceModel model : output.getInstanceSet()) {
                System.out.println("==================");
                System.out.println(model.getInstanceID());
                System.out.println(model.getInstanceName());
                System.out.println(model.getImage().getImageID());

                for (Types.NICVxNetModel vxNetModel : model.getVxNets()) {
                    System.out.println("==================");
                    System.out.println(vxNetModel.getVxNetID());
                    System.out.println(vxNetModel.getVxNetType());
                }
            }
        } catch (QCException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDescribeVolumes() {
        EnvContext context = new EnvContext(qyAccessKeyId, qySecretAccessKey);
        context.setProtocol("https");
        context.setHost("api.qingcloud.com");
        context.setPort("443");
        context.setZone("pek3b");
        context.setApiLang("zh-cn"); // optional, set return message i18n, default to us-en

        VolumeService volumeService = new VolumeService(context);

        VolumeService.DescribeVolumesInput input = new VolumeService.DescribeVolumesInput();
        input.setLimit(1);

        try {
            VolumeService.DescribeVolumesOutput output = volumeService.describeVolumes(input);

            for (Types.VolumeModel model : output.getVolumeSet()) {
                System.out.println("==================");
                System.out.println(model.getVolumeID());
                System.out.println(model.getVolumeName());
                System.out.println(model.getVolumeType());

                for (Types.NICVxNetModel vxNetModel : model.getInstance().getVxNets()) {
                    System.out.println("==================");
                    System.out.println(vxNetModel.getVxNetID());
                    System.out.println(vxNetModel.getVxNetType());
                }
            }
        } catch (QCException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testHttp() throws UnsupportedEncodingException {
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        sortedMap.put("access_key_id","QYACCESSKEYIDEXAMPLE");
        sortedMap.put("action","RunInstances");
        sortedMap.put("count",1);
        sortedMap.put("image_id","centos64x86a");
        sortedMap.put("instance_name","demo");
        sortedMap.put("instance_type","small_b");
        sortedMap.put("login_mode","passwd");
        sortedMap.put("login_passwd","QingCloud20130712");
        sortedMap.put("signature_method","HmacSHA256");
        sortedMap.put("signature_version",1);
        sortedMap.put("time_stamp","2013-08-27T14:30:10Z");
        sortedMap.put("version",1);
        sortedMap.put("vxnets.1","vxnet-0");
        sortedMap.put("zone","pek3a");

        String url = "";
        StringBuilder requestUrl = new StringBuilder();
        StringBuilder signatureUrl = new StringBuilder();

        Set<Map.Entry<String, Object>> entrySet = sortedMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            url += entry.getKey() + "=" + entry.getValue() + "&";

            requestUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            signatureUrl.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(entry.getValue().toString(), "UTF-8")).append("&");
        }

        System.out.println("requestUrl: " + requestUrl.toString());
        System.out.println("signatureUrl: " + signatureUrl.toString());


        String signature = QCSignatureUtils.computeIaasSignature("GET", "iaas", url, qyAccessKeyId);
        System.out.println("signature: " + signature);

        url += "signature=" + signature;
        System.out.println("2url: " + url);

        url = "https://api.qingcloud.com/iaas/?" + url;
        System.out.println("3url: " + url);


//        String url = "https://api.qingcloud.com/iaas/?access_key_id=QYACCESSKEYIDEXAMPLE&action=RunInstances&count=1&image_id=centos64x86a&instance_name=demo&instance_type=small_b&login_mode=passwd&login_passwd=QingCloud20130712&signature_method=HmacSHA256&signature_version=1&time_stamp=2013-08-27T14%3A30%3A10Z&version=1&vxnets.1=vxnet-0&zone=pek3a&signature=byjccvWIvAftaq%2BoublemagH3bYAlDWxxLFAzAsyslw%3D";

//        JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);
//        System.out.println(jsonObject);
    }

}
