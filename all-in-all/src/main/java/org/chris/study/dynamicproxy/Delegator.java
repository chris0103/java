package org.chris.study.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Delegator implements InvocationHandler {
    
    private Class<?>[] interfaces;
    
    private Object[] delegates;

    private static Method hashCodeMethod;

    private static Method equalsMethod;

    private static Method toStringMethod;

    static {
        try {
            hashCodeMethod = Object.class.getMethod("hashCode", (Class[]) null);
            equalsMethod = Object.class.getMethod("equals", new Class[] { Object.class });
            toStringMethod = Object.class.getMethod("toString", (Class[]) null);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    public Delegator(Class<?>[] interfaces, Object[] delegates) {
        this.interfaces = (Class[]) interfaces.clone();
        this.delegates = (Object[]) delegates.clone();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass == Object.class) {
            if (method.equals(hashCodeMethod)) {
                return proxyHashCode(proxy);
            } else if (method.equals(equalsMethod)) {
                return proxyEquals(proxy, args[0]);
            } else if (method.equals(toStringMethod)) {
                return proxyToString(proxy);
            } else {
                throw new InternalError("unexpected Object method dispatched: " + method);
            }
        } else {
            for (int i = 0; i < interfaces.length; i++) {
                if (declaringClass.isAssignableFrom(interfaces[i])) {
                    try {
                        System.out.println("Invoking method of " + declaringClass.getName() + "." + method.getName() + "()...");
                        return method.invoke(delegates[i], args);
                    } catch (InvocationTargetException e) {
                        throw e.getTargetException();
                    }
                }
            }

            return invokeNotDelegated(proxy, method, args);
        }
    }

    protected Object invokeNotDelegated(Object proxy, Method method, Object[] args) throws Throwable {
        throw new InternalError("unexpected method dispatched " + method);
    }

    protected Integer proxyHashCode(Object proxy) {
        System.out.println("Invoking delegated method of Object.hashCode()...");
        return new Integer(System.identityHashCode(proxy));
    }

    protected Boolean proxyEquals(Object proxy, Object other) {
        System.out.println("Invoking delegated method of Object.equals(Object)...");
        return (proxy == other ? Boolean.TRUE : Boolean.FALSE);
    }

    protected String proxyToString(Object proxy) {
        System.out.println("Invoking delegated method of Object.toString()...");
        return proxy.getClass().getName() + '@' + Integer.toHexString(proxy.hashCode());
    }

}
