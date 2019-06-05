package com.hawk.qrcode.barcode.controller;

/**
 * Created by Lenovo on 2019-06-04.
 */



import com.google.zxing.common.BitMatrix;
import com.hawk.qrcode.barcode.MyQRUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("WebPrintController")

@RequestMapping("/qrPrint")

public class WebPrintController {



    private static final Log logger = LogFactory.getLog(MyQRUtils.class);



    /**

     * 二维码预览页面

     * @param

     * @return

     */


    @RequestMapping("/index")
    public String index()
    {
        return "/barcode/barcode";
    }

    @RequestMapping("/showList.do")

    public  String  showQRList(Model model){

        //模拟获取数据库数据

        List listData = new ArrayList();

        StringBuffer ids = new StringBuffer();

        String code = "print00";

        for (int i = 0; i < 3; i++) {

            Map<String,String> map = new HashMap<String,String>(); //模拟VO

            map.put("id",code+""+i);

            ids.append(code+""+i).append(",");

            listData.add(map);

        }

        model.addAttribute("showListData", listData);

        model.addAttribute("ids", ids);

        return "/barcode/showQR";

    }



    /**

     * 二维码打印页面

     * 隐藏在iframe中

     * @param model

     * @return

     */

    @RequestMapping("/printEWM.do")

    public  String  printQRFrame(Model model, HttpServletRequest request){

        String ids = request.getParameter("ids");

        model.addAttribute("ids", ids);

        return "/barcode/printFrameQR";

    }



    /**

     * 显示二维码图片

     * @param request

     * @param response

     * @throws Exception

     */

    @RequestMapping("/showEWMImage.do")

    public void showImageByType(HttpServletRequest request, HttpServletResponse response){

        String id = request.getParameter("showID"); //ID



        //此处可从数据库中获取内容

        String content ="打印二维码\n打印测试\nID："+id;



        OutputStream outStream = null;

        try {

            outStream = response.getOutputStream();

            String format = "jpg";

            String bottomText = "一路不停"; //水印文字



            BitMatrix bitMatrix = MyQRUtils.setBitMatrix(content, 180 , 180);

            //暂时不使用LOGO图片

            MyQRUtils.megerToFile(bitMatrix, format, outStream, null,bottomText);



        } catch (Exception e) {

            logger.error("二维码生成失败", e);

        }finally{

            try {

                if(outStream!=null){

                    outStream.flush();

                    outStream.close();

                }

            } catch (IOException e) {

                logger.error("关闭数据流失败", e);

            }



        }

    }





}
