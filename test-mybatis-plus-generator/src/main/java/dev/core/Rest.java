package dev.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Rest<T> {

    private Integer code;
    private String message;
    private T data;

    protected Rest() {
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class List<T> extends Rest<java.util.List<T>> {
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Page<T> extends List<T> {

        private PageMetadata page;
    }

    @Data
    public static class PageMetadata {
        /**
         * 页码; 0-based;
         */
        private long index;

        /**
         * 每页数量
         */
        private long size;

        /**
         * -1: 总量未知;
         */
        private long total;

        public PageMetadata(long index, long size, long total) {
            this.index = index;
            this.size = size;
            this.total = total;
        }
    }

    public static class Message extends Rest<Object> {
    }

    public static Message ok() {
        var model = new Message();
        model.setCode(0);
        model.setMessage(null);
        model.setData(null);
        return model;
    }

    public static Message error() {
        return error(500, "未知异常");
    }

    public static Message error(String message) {
        return error(500, message);
    }

    public static Message error(int code, String message) {
        var model = new Message();
        model.setCode(code);
        model.setMessage(message);
        model.setData(null);
        return model;
    }

    public static <T> Rest<T> data(T content) {
        var model = new Rest<T>();
        model.setCode(0);
        model.setMessage(null);
        model.setData(content);
        return model;
    }

    public static <T> List<T> list(java.util.List<T> content) {
        var model = new List<T>();
        model.setCode(0);
        model.setMessage(null);
        model.setData(content);
        return model;
    }

    public static <T> Page<T> page(java.util.List<T> content, long page, long size, long total) {
        var model = new Page<T>();
        model.setCode(0);
        model.setMessage(null);
        model.setData(content);

        model.setPage(new PageMetadata(page, size, total));

        return model;
    }
}
