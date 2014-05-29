import javax.swing.*; 

import java.awt.*;


public class Splash_Screen extends JWindow{

		private static final long serialVersionUID = 1L;
		final Runnable closerRunner;
		
	    public Splash_Screen(String filename, Frame f, int waitTime){
	    	super(f);
	        JLabel l = new JLabel(new ImageIcon(filename));
	        getContentPane().add(l, BorderLayout.CENTER);
	        pack();
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        Dimension labelSize = l.getPreferredSize();
	        setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2));
	        final int pause = waitTime;
	        closerRunner = new Runnable(){
	                public void run(){
	                	setVisible(false);
	                    dispose();
	                }
	            };
	        Runnable waitRunner = new Runnable(){
	                public void run(){
	                    try{
	                            Thread.sleep(pause);
	                            closerRunner.run();
	                        }
	                    catch(Exception e){
	                            e.printStackTrace();
	                            // can catch InvocationTargetException
	                            // can catch InterruptedException
	                        }
	                }
	            };
	        setVisible(true);
	        Thread splashThread = new Thread(waitRunner, "SplashThread");
	        splashThread.run();
	    }
	}


