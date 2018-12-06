package com.hsp.pandora.mapper.support;

import com.hsp.pandora.mapper.beans.BasePoJo;

import java.util.List;

/**
 * 基础业务支持接口
 * Created by heshipeng on 2018/8/27.
 */
public interface BaseServiceSupport<T extends BasePoJo, PK>
{


    int save(T t);

    int delete(PK pk);

    int updateByPrimaryKeySelective(T t);

    T selectByPrimaryKey(PK pk);

    T selectOne(T t);

    List<T> listByCondition(T c);

    List<T> listAll(int pageNum, int pageSize);

    List<T> listByIds(List<PK> ids);

    int insertBatch(List<T> list);

    int deleteBatchByIds(List<PK> ids);

}
