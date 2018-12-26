import essw.com.kafka.LogCollectKafkaConsumer;
import essw.com.kafka.LogCollectKafkaProducer;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;


public class Main {
    public static Logger loger = Logger.getLogger("Main");
    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        //boolean型的option
        options.addOption("help",false,"help information");
        //当第二参数是true时，可以是这样的参数  -O4
        options.addOption("role",true,"specify your role: notifier/scheduler");

        //parser
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("help")) {
            //调用默认的help函数打印
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "java -jar xxx.jar -role <notifier/scheduler> <OPTION>", options );
            return;
        }

        //1.创建spring的ioc容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");

        if (cmd.getOptionValue("role").equals("scheduler")) {
            //2.从ioc容器中获取bean实例
            LogCollectKafkaConsumer logConsumer = (LogCollectKafkaConsumer) ctx.getBean("LogCollectKafkaConsumer");
            logConsumer.run();
        } else if (cmd.getOptionValue("role").equals("notifier")){
            LogCollectKafkaProducer logProducer = (LogCollectKafkaProducer) ctx.getBean("LogCollectKafkaProducer");

            Scanner input = new Scanner(System.in);

            while (true) {
                String message = input.nextLine();
                if (message.equals("quit")) {
                    loger.debug("Receive quit message, quiting...");
                    break;
                }
                logProducer.produce(message);
            }
        }

    }
}
