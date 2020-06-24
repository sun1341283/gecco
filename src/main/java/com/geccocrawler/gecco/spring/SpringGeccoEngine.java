package com.geccocrawler.gecco.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.geccocrawler.gecco.pipeline.PipelineFactory;

public abstract class SpringGeccoEngine implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	protected PipelineFactory springPipelineFactory;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null) {
			init();
		}
	}

	/**
	 * spring下启动gecco的初始化方法，spring下必须要用非阻塞方式start()运行GeccoEngine
	 */
	public abstract void init();
}
