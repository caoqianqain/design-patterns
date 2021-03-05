package com.cqq.chain.process;

import com.cqq.chain.flow.AbstractLinkedProcessHandler;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/2/25 23:34
 */
public class WashFace extends AbstractLinkedProcessHandler {

    @Override
    public void entry(String req, String resp) {

        System.out.println("洗脸");
        fireEntry(req, resp);
    }
}
