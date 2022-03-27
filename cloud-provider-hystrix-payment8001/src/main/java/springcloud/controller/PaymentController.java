package springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
   @Resource
    private PaymentService paymentService;

   @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties =
                    //三秒钟以内是正常逻辑 超过三秒走兜底方法
                    {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*******result:"+result);
        return result;
    }
    //兜底方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"系统繁忙或者运行报错，请稍后再试,id"+id+"\t"+"o(╥﹏╥)o";
    }
    //===服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*******result:"+result);
        return result;
    }






}
