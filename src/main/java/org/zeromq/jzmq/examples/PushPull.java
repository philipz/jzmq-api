package org.zeromq.jzmq.examples;

import org.zeromq.api.Context;
import org.zeromq.api.Socket;
import org.zeromq.api.SocketType;
import org.zeromq.jzmq.ManagedContext;

public class PushPull {
    public static void main(String[] args) throws Exception {
        System.out.println("Hi");
        Context ctx = new ManagedContext(1); // This should be some sort of factory to avoid using concrete classes
        Socket puller = ctx.createSocket(SocketType.PULL).bind("ipc:///tmp/pushpull.ipc");
        Socket pusher = ctx.createSocket(SocketType.PUSH).connect("ipc:///tmp/pushpull.ipc");
        System.out.println("Created sockets");
        pusher.send("PING".getBytes());
        System.out.println("Sent ping");
        byte[] buf = puller.receive();
        System.out.println("Received ping");
        System.out.println(new String(buf));

        pusher.close();
        System.out.println("Closed pusher");
        puller.close();
        System.out.println("Closed puller");
        // ctx.close();
        // System.out.println("Closed context");
    }
}
