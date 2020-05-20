package com.example.springboot;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Collector;

public class HelloPromCustomMetrics2 {
    private static final Counter countReq = Counter.build()
            .name("countReq").help("countReq description help")
            .labelNames("countReq").register();

    public static void reqInc() {
        countReq.labels("countReq").inc();
    }
}
