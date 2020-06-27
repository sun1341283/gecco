package com.geccocrawler.gecco.myGecco;


import java.util.HashMap;
import java.util.Map;

import com.geccocrawler.gecco.spider.render.CustomFieldRenderFactory;
import com.geccocrawler.gecco.spider.render.Render;
import com.geccocrawler.gecco.spider.render.RenderType;
import org.reflections.Reflections;


public abstract class RenderFactory {

    private Map<RenderType, Render> renders;

    public RenderFactory(Reflections reflections) {
        CustomFieldRenderFactory customFieldRenderFactory = new CustomFieldRenderFactory(reflections);
        renders = new HashMap<RenderType, Render>();

        AbstractRender htmlRender = createHtmlRender();
        htmlRender.setCustomFieldRenderFactory(customFieldRenderFactory);

        AbstractRender jsonRender = createJsonRender();
        jsonRender.setCustomFieldRenderFactory(customFieldRenderFactory);

        renders.put(RenderType.HTML, htmlRender);
        renders.put(RenderType.JSON, jsonRender);
    }

    public Render getRender(RenderType type) {
        return renders.get(type);
    }

    public abstract HtmlRender createHtmlRender();

    public abstract JsonRender createJsonRender();

}

