package com.elephantscale.metrics_demo;

import java.util.Random;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;


public class MetricsDemo {

	// metrics stuff
	private static final Counter metricsCounter = MyMetricsRegistry.metrics.counter("demo.counter");
	private static final Histogram metricsHistogram = MyMetricsRegistry.metrics.histogram("demo.histogram");
	private static final Meter metricsMeter = MyMetricsRegistry.metrics.meter("demo.meter");
	private static final Timer metricsTimer = MyMetricsRegistry.metrics.timer("demo.timer");

	private static final Random random = new Random();
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("MetricsDemo starting ...");
		
		// setup
		// initial count
		metricsCounter.inc(10);
		
		MyMetricsRegistry.metrics.register("demo.gauge", new Gauge<Integer>() {
		    @Override
		    public Integer getValue() {
		        return random.nextInt(100);
		    }
		});
		
		while (true) {
			counter();
			histogram();
			meter();
			timer();
			MyUtils.randomDelay(500);
		}

	}
	

	public static void counter() {
		int i = random.nextInt(10);
		if (i < 5) {
			// addToQueue();
			metricsCounter.inc();
		}
		else if (i < 9) {
			// removeFromQueue();
			metricsCounter.dec();
		}

		// replenish, so we don't go below zero
		if (metricsCounter.getCount() <= 0)
			metricsCounter.inc(10L);
	}
	
	
	// record things like packet size
	public static void histogram () {
		int packetSize = 100 + random.nextInt(500 - 100 + 1);
		metricsHistogram.update(packetSize);

	}
	
	// record every time we process an event (events /sec)
	public static void meter() {
		metricsMeter.mark();
	}

	// measure the time taken to process
	public static void timer() {
		final Timer.Context timerContext = metricsTimer.time();
		MyUtils.randomDelay(300);  // simulate doing something cool :-)
		timerContext.stop();
		
	}
}
