package com.cqq.java8.parallel;

import java.util.concurrent.RecursiveTask;

/**
 * @author caoqianqian
 * @Description:递归进行拆分
 * @date 2021/3/14 7:55
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    public ForkJoinCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //临界值,当大于临界值的时候就一直拆分，小于临界值就不再进行拆分了
    private static final long THREASHOLD = 100000000L;

    @Override
    protected Long compute() {

        long length = end - start;
        if(length <= THREASHOLD){//到临界值就不能再拆了
            long sum = 0;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{//不到临界值就进行拆分
            long middle = (end + start);
            ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
            //拆分子任务，同时压入线程队列
            left.fork();
            ForkJoinCalculate right = new ForkJoinCalculate(middle+1,end);
            right.fork();
            //拆完之后，合并，把fork()之后的结果得一个个合并，即累加总和
            return left.join()+right.join();

        }
    }
}
