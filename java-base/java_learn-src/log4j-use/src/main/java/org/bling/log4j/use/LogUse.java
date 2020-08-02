package org.bling.log4j.use;


import org.apache.log4j.Logger;
// import org.apache.log4j.PropertyConfigurator;

public class LogUse {

    // 打开日志
    private static Logger logger = Logger.getLogger(LogUse.class);

    public static void main(String[] args){
         // PropertyConfigurator.configure("log4j.properties");

        // 记录debug级别信息
        logger.debug("this is debug message.");
        // 记录info级别日志
        logger.info("this is info message.");
        // 记录error级别日志
        logger.error("this error message.");
    }
}
