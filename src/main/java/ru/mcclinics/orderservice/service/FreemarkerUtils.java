package ru.mcclinics.orderservice.service;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FreemarkerUtils {
    private final Configuration configuration;

    public FreemarkerUtils() {
        configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassForTemplateLoading(getClass(), "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setObjectWrapper(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_31).build());
    }

    public String processTemplate(String templateName, Object dataModel) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        StringWriter writer = new StringWriter();
        template.process(dataModel, writer);
        return writer.toString();
    }
    public String encodeHTML(String input) throws UnsupportedEncodingException {
        return URLEncoder.encode(input, "UTF-8").replace("+", "%20");
    }
    public String renderHtmlAndEncodeBase64(String templateName, Object dataModel) throws IOException, TemplateException {
        String renderedHtmlContent = processTemplate(templateName, dataModel);
        return Base64.getEncoder().encodeToString(renderedHtmlContent.getBytes(StandardCharsets.UTF_8));
    }
}
