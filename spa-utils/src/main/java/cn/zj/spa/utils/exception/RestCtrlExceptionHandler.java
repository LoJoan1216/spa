package cn.zj.spa.utils.exception;

import cn.zj.spa.utils.constants.HttpCode;
import cn.zj.spa.utils.resp.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:02
 * @description 全局异常处理器
 */
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);


    /**
     * @description  全局异常处理，统一返回状态码
     * @param e
     * @return cn.zj.spa.utils.resp.Result<java.lang.String>
     * @author zhangjin
     * @date 2023/3/16 10:08
    */
    @ExceptionHandler(Exception.class)
    public Result<String > handleException(Exception e){
        logger.error("服务器抛出了异常:{}",e);
        return new Result<String >(HttpCode.FAILURE,"执行失败",e.getMessage());
    }
}
