package com.panda.rabbitmq;

import com.panda.rabbitmq.Repository.GlobalNewsRepository;
import com.panda.rabbitmq.common.ResultGenerator;
import com.panda.rabbitmq.model.GlobalNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/rest")
public class HelloController
{
    @Autowired
    private GlobalNewsRepository globalnewsRepository;

    @RequestMapping(value = "/list",method = GET,produces = "application/json")
    public String say()
    {
        return ResultGenerator.genSuccessResult(getAllGlobNews()).toString();
    }

    @RequestMapping(value = "/add",method = POST,produces = "application/json")
    public String SaveData(@RequestBody Object obj)
    {
        return  ResultGenerator.genSuccessResult().toString();
    }

    @RequestMapping(value = "/details/{id}",method = DELETE,produces = "application/json")
    public String DeleteData(@PathVariable Long id)
    {
        return id.toString();
    }

    @GetMapping(path="/save") // MAP ONLY GET Rwquests
    public @ResponseBody String addNews(@RequestParam String content,int time){
        GlobalNews globalnews = new GlobalNews();
        globalnews.setContent(content);
        globalnews.setAddtime(time);
        globalnewsRepository.save(globalnews);
        return  ResultGenerator.genSuccessResult().toString();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<GlobalNews> getAllGlobNews()
    {
        return globalnewsRepository.findAll();
    }
}
