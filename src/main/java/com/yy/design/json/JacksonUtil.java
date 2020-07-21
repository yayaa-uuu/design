package com.yy.design.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @date 2020/7/20 17:09
 */
public class JacksonUtil {

    static Logger log = LoggerFactory.getLogger(JacksonUtil.class);

    /**
     * 将对象转换成json格式
     *
     * @param ts
     * @return
     */
    public static String objectToJson(Object ts) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(ts);
    }

    /**
     * 将对象转换成json格式空值保留
     *
     * @param ts
     * @return
     */
    public static String objectToJsonNull(Object ts) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(ts);
    }

    /**
     * 将对象转换成json格式(并自定义日期格式)
     *
     * @param ts
     * @return
     */

    public static String objectToJsonDateSerializer(Object ts, String dateformat) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat(dateformat);
        mapper.setDateFormat(df);
        return mapper.writeValueAsString(ts);
    }

    /**
     * 将json格式转换成list对象，并准确指定类型
     *
     * @param jsonStr
     * @return
     */
    public static List jsonToList(String jsonStr, Class c) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, c);
        return mapper.readValue(jsonStr, listType);
    }

    private static <T> T fromJson(String jsonStr, Class<T> c) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, c);

    }

    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, LinkedHashMap.class);
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> cl) throws Exception {
        return fromJson(jsonStr, cl);
    }
}
