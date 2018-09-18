package com.mm.test.aspect;

import com.mm.test.BootException;
import com.mm.test.enums.MessageEnum;
import com.mm.test.pojo.ControllerResult;
import com.mm.test.util.ControllerResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.MetaMessage;

@ControllerAdvice
public class ControllerAspect {
    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ControllerResult handle(Exception e) {

        if(e instanceof HttpRequestMethodNotSupportedException){
            return ControllerResultUtil.errorResult(MessageEnum.REQUEST_ERROR.getCode(), e.getMessage());
        }

        if (e instanceof BootException) {
            System.out.println(e.getMessage()+"=-=-===");
            return ControllerResultUtil.errorResult(MessageEnum.CONDITION_ERROR.getCode(), e.getMessage());
        }
        logger.info("未知错误", e);
        return ControllerResultUtil.errorResult(MessageEnum.UNKONW_ERROR.getCode(), MessageEnum.UNKONW_ERROR.getMessage());
    }
}
