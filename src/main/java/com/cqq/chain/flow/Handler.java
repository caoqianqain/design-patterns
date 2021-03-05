package com.cqq.chain.flow;

/**
 * @author caoqianqian
 * @Description:业务执行接口
 * @date 2021/2/25 8:07
 */
public interface Handler {
    void entry(String req,String resp);
    //责任链逻辑
    void fireEntry(String req,String resp);

}
