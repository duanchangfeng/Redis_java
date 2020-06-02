package redis_java_pub_sub;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {
	
	JTextArea textarea_sub;
	JLabel label_sub;
	
	public Subscriber(JTextArea textarea_main,JLabel label_main) 
	{
		textarea_sub=textarea_main;
		label_sub=label_main;
	}
	@Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("接收到redis发布的消息, 频道 %s, 消息 %s", channel, message));   
        if(textarea_sub.getText()==null)
        {
        	textarea_sub.setText(String.format("接收到redis发布的消息, 频道 %s, 消息 %s", channel, message));
        }
        else
        {
        	textarea_sub.setText(textarea_sub.getText()+"\n"+String.format("接收到redis发布的消息, 频道 %s, 消息 %s", channel, message));
        }
        
    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("订阅redis频道成功, 频道 %s, subscribedChannels %d",
                channel, subscribedChannels));
        label_sub.setText(String.format("订阅redis频道成功, 频道 %s, subscribedChannels %d",
                channel, subscribedChannels));
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("取消订阅redis频道, 频道 %s, subscribedChannels %d",
                channel, subscribedChannels));
        label_sub.setText(String.format("取消订阅redis频道, 频道 %s, subscribedChannels %d",
                channel, subscribedChannels));
        
        textarea_sub.setText("");

    }

}
