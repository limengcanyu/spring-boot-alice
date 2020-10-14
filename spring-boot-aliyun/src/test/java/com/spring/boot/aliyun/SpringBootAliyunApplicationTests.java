package com.spring.boot.aliyun;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeRegionsRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeRegionsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAliyunApplicationTests {

    private String accessKeyId = "";
    private String accessSecret = "";
    private String regionId = "cn-beijing";

    @Test
    void describeRegions() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeRegionsRequest request = new DescribeRegionsRequest();
        request.setSysRegionId(regionId);

        try {
            // 发起请求并处理应答或异常。
            DescribeRegionsResponse response = client.getAcsResponse(request);
            for (DescribeRegionsResponse.Region region : response.getRegions()) {
                System.out.println(region.getRegionId());
                System.out.println(region.getRegionEndpoint());
                System.out.println(region.getLocalName());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    @Test
    void describeInstances() {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        DescribeInstancesRequest request = new DescribeInstancesRequest();
        request.setSysRegionId(regionId);
        JSONArray instanceIds = new JSONArray();
        instanceIds.add("i-2zeejnlt2j84dgiulhva");
        request.setInstanceIds(instanceIds.toJSONString());

        try {
            // 发起请求并处理应答或异常。
            DescribeInstancesResponse response = client.getAcsResponse(request);
            for (DescribeInstancesResponse.Instance instance : response.getInstances()) {
                System.out.println(instance.getImageId());
                System.out.println(instance.getInstanceId());
                System.out.println(instance.getPublicIpAddress());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

}
