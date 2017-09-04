package com.stxb.database;

import java.lang.reflect.Method;  





import org.aspectj.lang.JoinPoint;  
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;  
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
  
  
/** 
 * AOP类根据注解com.any.annotation.DataSource的数据源名称 
 * 更新数据源DynamicDataSourceHolder.setDataSource(..) 
 *  
 * @author danny 
 * @version 1.0 2016-5-10 
 * @implements Ordered aop优先级接口
 */  

public class DataSourceAspect implements Ordered{  
  
  
    /** 
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源 
     *  
     * @param point 
     * @throws Exception 
     */  
    public void interceptMethod(JoinPoint point) throws Exception {  
        Class<?> target = point.getTarget().getClass();  
        MethodSignature signature = (MethodSignature) point.getSignature();  

        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解类  
        for (Class<?> cls : target.getInterfaces()) {  
            resetDataSource(cls, signature.getMethod());  
        }  
        resetDataSource(target, signature.getMethod());  
    }  
  
  
    /** 
     * 提取目标对象方法注解和类注解中的数据源标识 
     *  
     * @param cla 
     * @param method 
     */  
    private void resetDataSource(Class<?> cls, Method method) {  
        try {  
            Class<?>[] types = method.getParameterTypes();  
            // 默认使用类注解  
            if (cls.isAnnotationPresent(DataSource.class)) {  
                DataSource source = cls.getAnnotation(DataSource.class);  
                DynamicDataSourceHolder.setDataSource(source.value());  
            }  
            // 方法注解可以覆盖类注解  
            Method m = cls.getMethod(method.getName(), types);  
            if (m != null && m.isAnnotationPresent(DataSource.class)) {  
                DataSource source = m.getAnnotation(DataSource.class);   
                DynamicDataSourceHolder.setDataSource(source.value());  
            }  
        } catch (Exception e) {  
            System.out.println(cls + ":" + e.getMessage());  
        }  
    }


	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}  
  
  
}  