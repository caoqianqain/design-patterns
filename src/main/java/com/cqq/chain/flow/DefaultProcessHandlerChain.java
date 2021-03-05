package com.cqq.chain.flow;

/**
 * @author caoqianqian
 * @Description:定义链表的生成逻辑  责任链调用逻辑入口
 * @date 2021/2/25 23:47
 */
public class DefaultProcessHandlerChain extends ProcessHandlerChain {

   private AbstractLinkedProcessHandler first = new AbstractLinkedProcessHandler() {
        @Override
        public void entry(String req, String resp) {
            super.fireEntry(req, resp);
        }
    };

    private AbstractLinkedProcessHandler end = first;

    @Override
    public void addFirst(AbstractLinkedProcessHandler processHandler) {

        processHandler.setNext(first.getNext());
        first.setNext(processHandler);

        if(end == first){
            end = processHandler;
        }
    }

    @Override
    public void addEnd(AbstractLinkedProcessHandler processHandler) {

        end.setNext(processHandler);
        end = processHandler;

    }

    @Override
    public void entry(String req, String resp) {
        first.entry(req, resp);//指向AbstractLinkedProcessHandler的实现
    }
}
