package com.shangguan.user.utils;

import com.shangguan.user.VO.ResultVO;
import com.shangguan.user.enums.ResultEnum;

public class ResultVOUtil {

   public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
   
   public static ResultVO success() {
       ResultVO resultVO = new ResultVO();
       resultVO.setCode(0);
       resultVO.setMsg("成功");
       return resultVO;
   }
   
   public static ResultVO error(ResultEnum resultEnum) {
       ResultVO resultVO = new ResultVO();
       resultVO.setCode(resultEnum.getCode());
       resultVO.setMsg(resultEnum.getMessage());
       return resultVO;
   }
   
}
