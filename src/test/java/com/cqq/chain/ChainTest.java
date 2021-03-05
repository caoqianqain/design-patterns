package com.cqq.chain;

import com.cqq.chain.flow.DefaultProcessHandlerChain;
import com.cqq.chain.flow.ProcessHandlerChain;
import com.cqq.chain.process.BrushTeeth;
import com.cqq.chain.process.Sleep;
import com.cqq.chain.process.WashFace;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/2/25 23:59
 */
public class ChainTest {
    public static void main(String[] args) {
        ProcessHandlerChain chain = new DefaultProcessHandlerChain();
        chain.addEnd(new BrushTeeth());
        chain.addEnd(new WashFace());
        chain.addEnd(new Sleep());

        chain.entry("request","response");
    }
}
