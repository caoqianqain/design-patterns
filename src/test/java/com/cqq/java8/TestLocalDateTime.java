package com.cqq.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;

/**
 * @author caoqianqian
 * @Description:
 * @date 2021/3/18 23:21
 */
public class TestLocalDateTime {
    @Test
    public void teat01(){

        //LocalDateTime（日期时间） LocalDate（日期） LocalTime（时间）
        // 类的实例变量是不可变得对象，所以是线程安全的，它们分别表示使
        // 用ISO-8601日历系统的时间（ISO-8601日历系统是国际标准化组织制定的现代公民的日期和时间的表示法）
        // 都位于java.time包下

        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        LocalDate now1 = LocalDate.now();
        LocalTime now2 = LocalTime.now();
        System.out.println("【当前日期时间】"+now);
        System.out.println("【当前日期】"+now1);
        System.out.println("【当前时间】"+now2);

        //LocalDate  指定一个日期
        LocalDate localDate = LocalDate.now();
        System.out.println("【当前日期】"+localDate);
        LocalDate year = localDate.withDayOfYear(100);
        System.out.println("【当前日期为基础，指定本年当中的第几天，1-31号】"+year);
        LocalDate month = localDate.withDayOfMonth(2);
        System.out.println("【当前日期为基础，指定本月当中的第几月，1-31号】"+month);
        System.out.println("【当前日期为基础，直接指定月份】"+LocalDate.now().withMonth(5));

        //LocalDateTime 获取当前日期
        System.out.println("【当前时间】"+LocalDateTime.now());
        System.out.println("【当前时间是本年的第几天】"+LocalDateTime.now().getDayOfYear());
        System.out.println("【当前时间是本月的第几个天】"+LocalDateTime.now().getDayOfMonth());
        System.out.println("【当前时间是周几】"+LocalDateTime.now().getDayOfWeek());
        //获取年月日    当前时间】2021年MARCH月20日9时43分43秒
        System.out.println("【当前时间】"+LocalDateTime.now().getYear()+"年"+LocalDateTime.now().getMonth()+"月"+LocalDateTime.now().getDayOfMonth() +"日"
                +LocalDateTime.now().getHour()+"时"+LocalDateTime.now().getMinute()+"分"+LocalDateTime.now().getSecond()+"秒");


    }

    //时间日期的比较和运算判断
    @Test
    public void teat02(){

        System.out.println("【当前时间加一年加一天】"+LocalDateTime.now().plusYears(1).plusDays(1));
        System.out.println("【当前时间减俩月】"+LocalDateTime.now().minusMonths(2));

        LocalDate localDate1 = LocalDate.of(2021, 03, 20);
        LocalDate localDate2 = LocalDate.of(2020, 03, 20);
        System.out.println(localDate1.isBefore(localDate2));

        //判断是否闰年
        System.out.println(LocalDate.now()+"【当前时间是否闰年】"+LocalDate.now().isLeapYear());

    }

    @Test
    public void teat05(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format = dateTimeFormatter.format(now);
        System.out.println(now);
        System.out.println(format);

    }

    //时间戳
    @Test
    public void teat03(){
        //默认获取的是以UTC时区（世界协调时间，也叫格林威治时间）为基础的时间戳
        //中国在东八区，所以这个输出的时间是差了八个时区的
        Instant instant = Instant.now();
        System.out.println("【当前时间时间戳为】"+instant);

        //做一个偏移量的运算   偏移八个时区即为中国时间
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("【当前时间时间戳偏移八个时区】"+offsetDateTime);

        System.out.println("当前时间的毫秒值"+Instant.now().toEpochMilli());
        System.out.println("系统当前的毫秒值"+System.currentTimeMillis());
        
        //相互转换
        //Date.from(Instant.now())把Instant转成java,util,date
        Date date = Date.from(Instant.now());
        System.out.println("【date】 "+date);
        //new Date().toInstant()把Date转成Instant
        Instant instant1 = date.toInstant();
        System.out.println("【instant】"+instant1);
    }
    //ZoneId
    @Test
    public void test03(){
        //可以查看所有Java8中支持的时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("支持的时区个数："+availableZoneIds.size());
        //指定一个时区去构建一个日期
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);
        //构建出一个带时区的时间日期
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);
    }

    @Test
    public void test04(){
        Instant instant1 = Instant.now();
        LocalDateTime localDateTime1 = LocalDateTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant2 = Instant.now();
        LocalDateTime localDateTime2 = LocalDateTime.now();
        //时间戳的间隔
        System.out.println(Duration.between(instant1,instant2));
        System.out.println(Duration.between(instant2,instant1));

        //日期时间的间隔
        System.out.println(Duration.between(localDateTime1,localDateTime2));
        //获取毫秒值
        System.out.println("时间戳的间隔计算 "+Duration.between(instant1,instant2).toMillis());
        System.out.println("日期时间的间隔计算 "+Duration.between(localDateTime1,localDateTime2).toMillis());

        //Period
        LocalDate ld1 = LocalDate.of(2020, 3, 20);
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1, ld2);
        System.out.println("Period计算日期间隔 "+period);
        System.out.println(period.getYears()+"年"+period.getMonths()+"月"+period.getDays()+"日");

    }

    @Test
    public void teat06(){

        Clock clock = Clock.systemUTC();

        System.out.println(clock);
        System.out.println( Clock.systemDefaultZone());
        //可代替System.currentTimeMillis获取毫秒值
        System.out.println( Clock.systemDefaultZone().millis());
        System.out.println(System.currentTimeMillis());

    }

    @Test
    public void teat07(){

        Clock clock = Clock.systemUTC();

        System.out.println(clock);
        System.out.println( Clock.systemDefaultZone());
        //可代替System.currentTimeMillis获取毫秒值
        System.out.println( Clock.systemDefaultZone().millis());
        System.out.println(System.currentTimeMillis());

    }

    @Test
    public void teat08(){

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime with = localDateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        //下一个周五
        System.out.println(with);//2021-03-26T11:31:53.682

    }

}
