package com.panda.rabbitmq;

import com.panda.rabbitmq.common.Result;
import com.panda.rabbitmq.common.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //标记为控制器
@RequestMapping("/panda") //URI映射
public class PandaController
{
    // https://www.cnblogs.com/paddix/p/8215245.html

    @RequestMapping("/test")
    @ResponseBody //返回JSON字符串, @RequestBody 接收JSON格式字符串参数
    public Result Test(){
       return   ResultGenerator.genSuccessResult();
    }
}
