package com.shangguan.order.utils;

import com.shangguan.order.VO.ResultVO;

public class ResultVOUtil {

   public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
