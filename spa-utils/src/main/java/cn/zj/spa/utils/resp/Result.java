package cn.zj.spa.utils.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:05
 * @description 返回的结果数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /*状态码*/
    private Integer code;
    /*状态描述*/
    private String codeMsg;
    /*返回的数据*/
    private T data;
}
