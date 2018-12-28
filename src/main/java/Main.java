import essw.com.kafka.KafkaTaskHandler;
import essw.com.kafka.KafkaTaskProducer;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static Logger logger = Logger.getLogger("Main");
    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("help",false,"help information");
        options.addOption("role",true,"specify your role: notifier/scheduler");

        options.addOption("msg",true,"message to send, only active in <notifier> role");

        //parser
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("help")) {
            //调用默认的help函数打印
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "java -jar xxx.jar -role <notifier/scheduler> <OPTION>", options );
            return;
        }


        // 使用 spring 来管理实例
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        if (cmd.getOptionValue("role").equals("scheduler")) {
            KafkaTaskHandler logConsumer = (KafkaTaskHandler) ctx.getBean("KafkaTaskHandler");
            logConsumer.run();
        } else if (cmd.getOptionValue("role").equals("notifier")){
            KafkaTaskProducer logProducer = (KafkaTaskProducer) ctx.getBean("KafkaTaskProducer");
            if(cmd.hasOption("msg")) {
                String msg = cmd.getOptionValue("msg");
                logProducer.produce(msg);
                logger.info("produce new msg: " + msg);
            } else {
                logger.error("please specify message.");
                System.exit(1);
            }
        }

    }
}
