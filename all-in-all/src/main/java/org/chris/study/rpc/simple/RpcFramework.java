package org.chris.study.rpc.simple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A demo PRC framework.
 */
public class RpcFramework {

    /**
     * Exports the service.
     */
    public static void export(final Object service, int port) throws IOException {
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Exporting service " + service.getClass().getName() + " on port " + port + "...");
        try (ServerSocket server = new ServerSocket(port)) {
            ExecutorService executor = Executors.newCachedThreadPool();
            while (true) {
                Socket socket = server.accept();
                executor.submit(() -> {
                    try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                         ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
                        String methodName = input.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                        Object[] arguments = (Object[]) input.readObject();
                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object result = method.invoke(service, arguments);
                        output.writeObject(result);
                    } catch (Throwable t) {
                        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
                            output.writeObject(t);
                        } catch (Exception ex) {
                            Logger.getLogger(RpcFramework.class.getName()).log(Level.SEVERE, "Error writing exception", ex);
                        }
                    }
                });
            }

        } catch (Exception e) {
            Logger.getLogger(RpcFramework.class.getName()).log(Level.SEVERE, "Server error", e);
        }
    }

    /**
     * References the service.
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        if (! interfaceClass.isInterface()) {
            throw new IllegalArgumentException(interfaceClass.getName() + " must be interface class!");
        }
        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("Host == null!");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println(
                "Referencing remote service " + interfaceClass.getName() + " from server " + host + ":" + port + "...");
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, (proxy, method, arguments) -> {
                    try (Socket socket = new Socket(host, port);
                         ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(arguments);

                        Object result = input.readObject();
                        if (result instanceof Throwable) {
                            throw (Throwable) result;
                        }
                        return result;
                    } catch (Exception e) {
                        Logger.getLogger(RpcFramework.class.getName()).log(Level.SEVERE, "Client error", e);
                        throw e;
                    }
                });
    }
}
