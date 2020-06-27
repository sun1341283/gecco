package com.geccocrawler.gecco.myGecco;

import org.reflections.Reflections;

import net.sf.cglib.proxy.Enhancer;

import com.geccocrawler.gecco.monitor.RenderMointorIntercetor;

public class MonitorRenderFactory extends RenderFactory {

    public MonitorRenderFactory(Reflections reflections) {
        super(reflections);
    }

    @Override
    public HtmlRender createHtmlRender() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HtmlRender.class);
        enhancer.setCallback(new RenderMointorIntercetor());
        return (HtmlRender)enhancer.create();
    }

    @Override
    public JsonRender createJsonRender() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(JsonRender.class);
        enhancer.setCallback(new RenderMointorIntercetor());
        return (JsonRender)enhancer.create();
    }



}
