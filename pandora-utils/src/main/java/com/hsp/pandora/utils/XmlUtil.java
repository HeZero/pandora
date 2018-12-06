package com.hsp.pandora.utils;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author heshipeng
 * xml工具类
 */
public class XmlUtil
{
    private static final String SET_METHOD = "set";

    private static final String GET_METHOD = "get";

    private static final String CLASS_METHOD = "class";

    private static final String EMPTY = "";

    /**
     * 单层文档树解析为java类
     * @param document
     * @param clazz
     * @return
     */
    public static Object parseObject(Document document, Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Map<String, String> map = documentToMap(document);
        Object instance = clazz.newInstance();

        Method[] methods = clazz.getMethods();
        for(Method method: methods) {
            if(StringUtils.startsWith(method.getName(), SET_METHOD)) {
                method.invoke(instance, map.get(method.getName().replace(SET_METHOD, EMPTY).toLowerCase()));
            }
        }

        return instance;
    }

    /**
     * 解析对象
     * @param xml
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Object parseObject(String xml, Class clazz) throws Exception {
        Document document = DocumentHelper.parseText(xml);
        return parseObject(document, clazz);
    }


    /**
     * 单层文档树解析为map
     * @param document
     * @return
     */
    public static Map<String, String> documentToMap(Document document) {
        Map<String, String> result = new HashMap<>();
        Element root = document.getRootElement();
        Iterator<Node> it = root.nodeIterator();
        Node node;
        while (it.hasNext()) {
            node = it.next();
            result.put(node.getName(), node.getText());
        }
        return result;
    }

    /**
     * 对象转xml字符串
     * @param object
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static String ObjectToXML(Object object) throws InvocationTargetException, IllegalAccessException {
        if(object == null) {
            return "";
        }
        StringBuffer str = new StringBuffer();
        Method[] methods = object.getClass().getMethods();
        str.append("<xml>");
        String field;
        for(Method method: methods) {
            if(StringUtils.startsWith(method.getName(), GET_METHOD)) {
                field = method.getName().replace(GET_METHOD, EMPTY).toLowerCase();
                if(StringUtils.equalsIgnoreCase(field, CLASS_METHOD)){
                    continue;
                }
                Object v = method.invoke(object);
                if (v == null) continue;
                str.append("<")
                        .append(field)
                        .append(">")
                        .append(method.invoke(object).toString())
                        .append("</")
                        .append(field)
                        .append(">");
            }
        }
        str.append("</xml>");
        return str.toString();
    }

}
