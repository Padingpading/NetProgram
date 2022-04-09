package cn.enjoyedu.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *
 *类说明：贵金属期货的实现
 */
@Controller
public class NobleMetalController {

    private static Logger logger = LoggerFactory.getLogger(NobleMetalController.class);

    @RequestMapping("/nobleMetal")
    public String stock(){
        return "nobleMetal";
    }

    @RequestMapping(value="/needPrice"
            //告诉浏览器,发送的事件流(一批数据)。
            ,produces="text/event-stream;charset=UTF-8"
            )
    @ResponseBody
    public String push(){
        Random r = new Random();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //客户端返回数据
        return makeResp(r);

    }
    
    /**SSE响应的数据要遵循某种格式。
     * @param
     * @return
     */
    /*业务方法，生成贵金属的实时价格*/
    private String makeResp(Random r){
        //格式是规范的。
        StringBuilder stringBuilder = new StringBuilder("");
        //重试时间。
        stringBuilder.append("retry:2000\n")
                //响应的数据
                .append("data:")
                .append(r.nextInt(100)+50+",")
                .append(r.nextInt(40)+35)
                //data的结束符号
                .append("\n\n");
        return stringBuilder.toString();
    }








    /*------------以下为正确使用SSE的姿势------------------*/
    @RequestMapping("/nobleMetalr")
    public String stockr(){
        return "nobleMetalAlso";
    }

    @RequestMapping(value="needPricer")
    public void pushRight(HttpServletResponse response){
        //配置sse
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        Random r = new Random();
        try {
            //流写出去
            PrintWriter pw = response.getWriter();
            int i = 0;
            while(i<10){
                if(pw.checkError()){
                    System.out.println("客户端断开连接");
                    return;
                }
                Thread.sleep(1000);
                pw.write(makeResp(r));
                pw.flush();
                i++;
            }
            System.out.println("达到阈值，结束发送.......");
            pw.write("data:stop\n\n");
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
