package dev.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePageable implements Serializable {

    /**
     * 页码; 1-Based;
     */
    private Integer page;

    /**
     * 每页大小
     */
    private Integer limit;
}
