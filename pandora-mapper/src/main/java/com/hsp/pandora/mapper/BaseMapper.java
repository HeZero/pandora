package com.hsp.pandora.mapper;

import com.hsp.pandora.mapper.beans.BasePoJo;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionSelectiveMapper;

/**
 * 基于tk定制mapper
 * Created by heshipeng on 2018/8/29.
 */
public interface BaseMapper<T extends BasePoJo, PK> extends InsertMapper<T>,
        InsertListMapper<T>,
        DeleteByPrimaryKeyMapper<T>,
        DeleteByIdListMapper<T, PK>,
        UpdateByPrimaryKeySelectiveMapper<T>,
        UpdateByConditionSelectiveMapper<T>,
        SelectByIdListMapper<T, PK>,
        SelectByPrimaryKeyMapper<T>,
        SelectAllMapper<T>,
        SelectByConditionMapper<T>,
        SelectOneMapper<T>
{

}
