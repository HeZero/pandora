package com.hsp.pandora.mapper.support;

import com.github.pagehelper.PageHelper;
import com.hsp.pandora.mapper.BaseMapper;
import com.hsp.pandora.mapper.beans.BasePoJo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基础业务实现
 * @author heshipeng
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractBaseServiceSupport<T extends BasePoJo, PK> implements BaseServiceSupport<T, PK>
{

    @Autowired
    private BaseMapper<T, PK> baseMapper;


    @Override
    public int save(T t)
    {
        return baseMapper.insert(t);
    }

    @Override
    public int delete(PK pk)
    {
        return baseMapper.deleteByPrimaryKey(pk);
    }

    @Override
    public int updateByPrimaryKeySelective(T t)
    {
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public T selectByPrimaryKey(PK pk)
    {
        return baseMapper.selectByPrimaryKey(pk);
    }

    @Override
    public T selectOne(T t)
    {
        return baseMapper.selectOne(t);
    }

    @Override
    public List<T> listAll(int pageNum, int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.selectAll();
    }

    @Override
    public List<T> listByCondition(T c)
    {
        PageHelper.startPage(1, 100);
        return baseMapper.selectByCondition(c);
    }

    @Override
    public List<T> listByIds(List<PK> ids)
    {
        PageHelper.startPage(1, 100);
        return baseMapper.selectByIdList(ids);
    }

    @Override
    public int insertBatch(List<T> list)
    {
        return baseMapper.insertList(list);
    }

    @Override
    public int deleteBatchByIds(List<PK> ids)
    {
        return baseMapper.deleteByIdList(ids);
    }
}
