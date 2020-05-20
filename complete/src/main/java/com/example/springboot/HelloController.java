package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

// add Prometheus
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// add CustomMetrics
import com.example.springboot.HelloPromCustomMetrics2;
import com.example.springboot.HelloPromCustomMetrics1;

@RestController
@Configuration
public class HelloController {

	@RequestMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String index() {
		HelloPromCustomMetrics1.reqInc();
		return "<a href=\"/metrics\">Metrics</a>";
	}

	@RequestMapping(value="**",method = RequestMethod.GET)
	public String getAnythingelse(){
		HelloPromCustomMetrics1.reqInc();
		// return "everythingelse";
		return "<a href=\"/metrics\">Metrics</a>";
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		DefaultExports.initialize();
		return new ServletRegistrationBean(new MetricsServlet(), "/metrics");
	}
}
