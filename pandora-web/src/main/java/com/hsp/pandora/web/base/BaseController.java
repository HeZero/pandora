package com.hsp.pandora.web.base;

import com.hsp.pandora.mapper.beans.BasePoJo;
import com.hsp.pandora.mapper.support.AbstractBaseServiceSupport;
import com.hsp.pandora.web.beans.ApiResult;
import com.hsp.pandora.web.beans.AuthContext;
import com.hsp.pandora.web.beans.BaseApiCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hsp.pandora.web.beans.AuthEnum.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: heshipeng
 * @Date: 2019/2/28
 * @Time: 16:37
 * @Description: 基础controller控制器，封装了基本增删改查，需要扩展可继承
 */
public abstract class BaseController<BS extends AbstractBaseServiceSupport<T, Long>, T extends BasePoJo>
{

    @Autowired
    protected BS baseServiceImpl;

    @AuthContext(value = ACCESS)
    @GetMapping(value = "list")
    @ResponseBody
    protected ApiResult list(@RequestParam("pageNum")int pageNum,
                       @RequestParam("pageSize")int pageSize)
    {
        if(pageNum <= 0) pageNum = 1;
        List<T> list = baseServiceImpl.listAll(pageNum, pageSize);
        return ApiResult.of(BaseApiCode.SUCCESS, list);

    }

    @AuthContext(value = INSERT)
    @PostMapping(value = "/add")
    @ResponseBody
    protected ApiResult add(@ModelAttribute("obj")T obj)
    {
        baseServiceImpl.save(obj);
        return ApiResult.of(BaseApiCode.SUCCESS, obj);
    }

    @AuthContext(value = DELETE)
    @DeleteMapping(value = "/delete")
    @ResponseBody
    protected ApiResult delete(@RequestParam("id")Long id)
    {
        baseServiceImpl.delete(id);
        return ApiResult.of(BaseApiCode.SUCCESS, id);
    }

    @AuthContext(value = ACCESS)
    @GetMapping(value = "/detail")
    @ResponseBody
    protected ApiResult detail(@RequestParam("id")Long id)
    {
        T obj = baseServiceImpl.selectByPrimaryKey(id);
        return ApiResult.of(BaseApiCode.SUCCESS, obj);
    }

    @AuthContext(value = UPDATE)
    @PostMapping(value = "/update")
    @ResponseBody
    protected ApiResult update(@ModelAttribute("obj")T obj)
    {
        baseServiceImpl.updateByPrimaryKeySelective(obj);
        return ApiResult.of(BaseApiCode.SUCCESS, obj);
    }
}