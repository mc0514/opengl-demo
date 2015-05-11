/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Component;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author Administrator
 */
public class GLRender implements GLEventListener {

    private GL gl;
    private GLU glu;
    private Robot robot;
    private float rotationAngle;

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL();
        glu = new GLU();
        robot = new Robot(gl);
        rotationAngle = 0.0f;

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);

        int w = ((Component) drawable).getWidth();
        int h = ((Component) drawable).getHeight();

        gl.glViewport(0, 0, w, h);		// �����ӵ��Сreset the viewport to new dimensions
        gl.glMatrixMode(GL.GL_PROJECTION);			//���õ�ǰ����ģʽ set projection matrix current matrix
        gl.glLoadIdentity();						//�ѵ�ǰ�滭���ƶ�����Ļ���� reset projection matrix

        //������������������� calculate aspect ratio of window
        glu.gluPerspective(52.0f, (float) w / (float) h, 1.0f, 1000.0f);

        gl.glMatrixMode(GL.GL_MODELVIEW);				//���õ�ǰ����ģʽ set modelview matrix
        gl.glLoadIdentity();                          //�ѵ�ǰ�滭���ƶ�����Ļ���� reset modelview matrix

    }

    public void display(GLAutoDrawable drawable) {
        //ˢ�±�����ɫ clear screen and depth buffer
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        //�ѵ�ǰ�滭���ƶ�����Ļ���� load the identity matrix (clear to default position and orientation)
        gl.glLoadIdentity();

        gl.glPushMatrix();							//���浱ǰ���� put current matrix on stack
        gl.glLoadIdentity();					//�ѵ�ǰ�滭���ƶ�����Ļ����  reset matrix
        gl.glTranslatef(0.0f, 0.0f, -30.0f);	//�ѵ�ǰ�滭���ƶ���(x,y,z) move to (0, 0, -30)
        gl.glRotatef(rotationAngle, 0.0f, 1.0f, 0.0f);	//��ת(�Ƕ�ֵ,x��,Y��,Z��) rotate the robot on its y-axis
        robot.DrawRobot(0.0f, 0.0f, 0.0f);		//�������� draw the robot
        gl.glPopMatrix();

        this.Prepare(0.05f);          //ˢ�»����˶���

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void Prepare(float dt) {
        rotationAngle += 45.0f * dt;	// increase our rotation angle counter
        if (rotationAngle >= 360.0f) // if we've gone in a circle, reset counter
        {
            rotationAngle = 0.0f;
        }

        robot.Prepare(dt);
    }
}


	