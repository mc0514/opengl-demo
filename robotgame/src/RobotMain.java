




import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;
import javax.media.opengl.*;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

public class RobotMain extends JFrame {
	GLRender listener=new GLRender();
	static FPSAnimator animator=null;
	public RobotMain() throws HeadlessException {
		super("萌萌哒！！！");
		setSize(600,480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GLCapabilities glcaps=new GLCapabilities();
		GLCanvas canvas=new GLCanvas(glcaps);
		canvas.addGLEventListener(listener);
		//canvas.addMouseListener(listener);
		getContentPane().add(canvas, BorderLayout.CENTER);
		animator=new FPSAnimator(canvas,60,true);
                
		centerWindow(this);		
	}	
	private void centerWindow(Component frame) { // 居中窗体
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		frame.setLocation((screenSize.width - frameSize.width) >> 1,
				(screenSize.height - frameSize.height) >> 1);

	}
	
	public static void main(String[] args) {
		final RobotMain app = new RobotMain();
		// 显示窗体
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				app.setVisible(true);
			}
		});
		// 动画线程开始
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				animator.start();
			}
		});
	}
}
