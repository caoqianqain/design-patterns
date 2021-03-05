package com.cqq.chain.flow;

/**
 * @author caoqianqian
 * @Description:链表元素，包含责任链的执行逻辑
 * @date 2021/2/25 8:09
 */
public abstract  class AbstractLinkedProcessHandler implements Handler {

    private AbstractLinkedProcessHandler next = null;//默认指向null

    public AbstractLinkedProcessHandler getNext() {
        return next;
    }

    public void setNext(AbstractLinkedProcessHandler next){
        this.next = next;
    }

    public void fireEntry(String req, String resp) {
        if(next != null){//判断是否到达end
            next.entry(req, resp);
        }

    }
}
