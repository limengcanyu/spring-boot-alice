package com.spring.boot.nacos.discovery.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("discovery")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    /**
     * 获取服务实例
     *
     * http://localhost:8080/discovery/get?serviceName=nacos-discovery
     *
     * @param serviceName
     * @return
     * @throws NacosException
     */
    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    /**
     * 注册服务实例
     *
     * http://localhost:8080/discovery/registerService
     *
     * @return
     */
    @RequestMapping(value = "/registerService", method = GET)
    @ResponseBody
    public String registerService() {
        try {
            namingService.registerInstance("nacos-discovery", "127.0.0.1", 8080);

            return "ok";
        } catch (NacosException e) {
            e.printStackTrace();

            return "error";
        }
    }

}
