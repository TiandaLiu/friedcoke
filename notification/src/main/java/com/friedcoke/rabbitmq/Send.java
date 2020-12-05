import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.json.JSONWriter;


public class Send {

  private final static String QUEUE_NAME = "newqueue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(QUEUE_NAME, BuiltinExchangeType.DIRECT);

    channel.queueDeclare(QUEUE_NAME, true, false, false, null);

    //String message = "Hello Mark!";
    LoggingMessage message = new LoggingMessage();
// 		message.setUserID(123456);
// 		message.setMsgText("OMG It Worked!!!");
// 		message.setProducingService("UserManagement");
// 		message.setDateTimeStamp(new Date());
// 		message.setLogLevel(LoggingMessage.LogLevel.INFO.name());

		JSONWriter rabbitmqJson = new JSONWriter();
		String logMsg = rabbitmqJson.write(message);
		Map<String,Object> headerMap = new HashMap<String, Object>();
    headerMap.put(Constants.producerIP, InetAddress.getLocalHost().getHostAddress());

    //getAppId, getContentEncoding, getContentType, getCorrelationId, getDeliveryMode, getExpiration, getHeaders,
    //getMessageId, getPriority, getReplyTo, getTimestamp, getType, getUserId, setAppId, setContentEncoding,
    //setContentType, setCorrelationId, setDeliveryMode, setExpiration, setHeaders, setMessageId, setPriority,
    //setReplyTo, setTimestamp, setType, setUserId
	BasicProperties messageProperties  = new BasicProperties.Builder()
	.timestamp(new Date())
	.userId("guest")
	.deliveryMode(2)
	.headers(headerMap)
	.build();
	 System.out.println("Sending message....");
	 channel.basicPublish("", QUEUE_NAME, messageProperties, logMsg.getBytes());

	System.out.println("The message has been sent!");

    //channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}