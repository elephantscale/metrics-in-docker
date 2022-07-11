package com.elephantscale.metrics_demo.archive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.codahale.metrics.Meter;
import com.elephantscale.metrics_demo.MyMetricsRegistry;
import com.elephantscale.metrics_demo.MyUtils;

public class Meters {
	
	private static final Meter metricsMeterRequests = MyMetricsRegistry.metrics.meter("requests");
	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
		
		while (true) {
			doSomethingCool();
			
//			try {
//				Thread.sleep(300); 
//			}
//			catch(InterruptedException e) {}
			
		}

	}
	
	public static void doSomethingCool() {
		MyUtils.randomDelay(300);  // simulate doing something cool :-)
		LOG.info("doing something cool");
		metricsMeterRequests.mark();
		
	}

}
