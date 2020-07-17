package com.sixkery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * JWT 响应返回
 * </p>
 * @author sixkery
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }


    public static void main(String[] args) {
                HashMapThread thread0 = new HashMapThread();
                 HashMapThread thread1 = new HashMapThread();
                 HashMapThread thread2 = new HashMapThread();
                 HashMapThread thread3 = new HashMapThread();
                 HashMapThread thread4 = new HashMapThread();
                 thread0.start();
                 thread1.start();
                 thread2.start();
                 thread3.start();
                 thread4.start();
    }

}
class HashMapThread extends Thread {
private static AtomicInteger ai = new AtomicInteger();
private static Map<Integer, Integer> map = new HashMap<>();

        @Override
public void run() {
        while (ai.get() < 1000000) {
                         map.put(ai.get(), ai.get());
                         ai.incrementAndGet();
                     }
             }
 }
