package alibaba.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handleException1(BlockException exception) {
        return new CommonResult(4444, "BlockException....exception1");

    }
    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(4444, "BlockException....exception2");

    }
}
