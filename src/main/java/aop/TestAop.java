package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 21:56
 * @deprecated
 */
@Aspect    //通过这个注解表明这个类是一个自定义的切面类。
public class TestAop {
    //这里是这个切面要切的方法，也就是切点
    @Pointcut("execution(* service.impl.PersonServiceImpl.findAll())")
    public void findAll(){

    }

    //这里是针对findAll方法做的环绕通知,如果切点有返回值，返回类型就为Object，否则是void
    @Around("findAll()")
    public Object huanraoAop(ProceedingJoinPoint proceedingJoinPoint){
        Object o=null;
        System.out.println("查询之前");
        try {
            o= proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("查询之后");
        return o;
    }
}

