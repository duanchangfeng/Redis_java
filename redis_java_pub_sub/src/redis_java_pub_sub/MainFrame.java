package redis_java_pub_sub;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import redis.clients.jedis.JedisPool;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JLabel lblNewLabel = new JLabel("");
	
	JedisPool jedisPool;//初始化连接池
	JTextArea textArea = new JTextArea();
	
	Subscriber subscriber=new Subscriber(textArea,lblNewLabel);
	String T;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textArea.setBounds(50, 50, 800, 300);
		contentPane.add(textArea);
		
		
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(350, 400, 500, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("订阅");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				    
					try {
						jedisPool = RedisUtil.initPool();
						System.out.println("redis池已经准备好\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
			        
			        System.out.println("订阅者已经准备好\n");			        
			        SubThread subThread = new SubThread(jedisPool,subscriber);//订阅者		
			        
			        subThread.start();	        
			        


			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(50, 400, 100, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消订阅");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(subscriber.getSubscribedChannels()!=0)
				{
					
			        System.out.println("取消订阅成功\n");
			        subscriber.unsubscribe();
				}
				else
				{
					lblNewLabel.setText("本来就没订阅");
					System.out.println("本来就没订阅\n");
				}
								
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_1.setBounds(200, 400, 100, 30);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(50, 470, 700, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("发布消息");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
		        System.out.println("发布者已经准备好\n");
		        lblNewLabel.setText("发布者已经准备好");
		        Publisher publisher = new Publisher(jedisPool,textField.getText());//发布者
		        publisher.start();
				
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_2.setBounds(750, 469, 100, 30);
		contentPane.add(btnNewButton_2);
		

	}
}
