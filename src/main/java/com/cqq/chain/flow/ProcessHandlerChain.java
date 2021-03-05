package com.cqq.chain.flow;

/**
 * @author caoqianqian
 * @Description:定义链表结构的头部插入和尾部插入
 * @date 2021/2/25 23:41
 */
public abstract class ProcessHandlerChain extends AbstractLinkedProcessHandler{
    public abstract void addFirst(AbstractLinkedProcessHandler processHandler);
    public abstract void addEnd(AbstractLinkedProcessHandler processHandler);

}
