package com.booking.tests;

import com.booking.listeners.ListenerClass;
import com.booking.listeners.MethodInterceptor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(value = {
        ListenerClass.class, MethodInterceptor.class})
/*
 * Issue: https://github.com/cbeust/testng/issues/446
 * IAnnotationTransformer listener test çalıştırılmadan önce bilmesi gerekiyor
 * bundan dolayı  AnnotationTransformer'ı belirtmek mümkün degıl
 */
public class _BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method) {
        //System.out.println("STARTING TEST: " + method.getName());
        //System.out.println("THREAD ID: " + Thread.currentThread().getId());
    }
}
