package com.qdynasty.util;

import com.qdynasty.model.Result;

/**
 * Created on 2017/11/7.
 *
 * @author zlf
 * @since 1.0
 */
public class ResultUtil {
    public static Result<Object> success(Object object) {
        Result<Object> result = new Result<Object>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> error(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
