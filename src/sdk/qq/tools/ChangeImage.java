package sdk.qq.tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ChangeImage {
    
    /*
     * @version ��ͼ�����Ϊָ����С��Բ��ͼ��
     * @return BufferedImage
     */
    public static BufferedImage roundedCornerImage(ImageIcon image,
    		int width,
            int height,
    		int cornerRadius) {
    	
    	return roundedCornerImage(image,
    			width,
    			height,
    			width,
    			height,
    			0,
    			0,
    			cornerRadius
    			);
    }
    
    /*
     * @version ��ͼ�����Ϊָ����С��Բ��ͼ��
     * @return ImageIcon
     */
    public static ImageIcon roundedCornerIcon(ImageIcon image,
            int width,
            int height,
    		int cornerRadius){
    		
    	image = new ImageIcon(roundedCornerImage(image,
    			width,
    			height,
    			width,
    			height,
    			0,
    			0,
    			cornerRadius
    			));
		return image;
    }
    
    /*
     * @version �Ը�����ͼ�񹹽�ָ�� ��С��ָ��λ�û���ָ��Բ�Ƕ�������С��ͼ��
     * @return ImageIcon
     */
    public static ImageIcon roundedCornerIcon(ImageIcon image,
            int width,
            int height,
            int imgWidth,
            int imgHeight,
            int x,
            int y,
    		int cornerRadius) {
    	
		return  image = new ImageIcon(roundedCornerImage(image,
    			width,
    			height,
    			imgWidth,
    			imgHeight,
    			x,
    			y,
    			cornerRadius
    			));
    }  
    /*
     * @version �Ը�����ͼ�񹹽�ָ�� ��С��ָ��λ�û���ָ��Բ�Ƕ�������С��ͼ��
     * @return BufferedImage
     */
    public static BufferedImage roundedCornerImage(ImageIcon image,
            int width,
            int height,
            int imgWidth,
            int imgHeight,
            int x,
            int y,
    		int cornerRadius) {
    	
        BufferedImage output = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        
        //ȡ�� output �� ���� 
        Graphics2D g2 = output.createGraphics();

        //���� g2 ģʽ������ģʽ ���Բ���Ҫ����
        g2.setComposite(AlphaComposite.Src);
        //���㷨 �����������
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        //��� ӡˢ�� 
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(x, y, imgWidth, imgHeight, cornerRadius,
                cornerRadius));

        // ӡģ
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY );
        g2.drawImage(image.getImage(), x, y, imgWidth, imgHeight, null);

        g2.dispose();

        return output;
    }
    
    /*
     * @version ��ָ��ͼ�񱣴浽ָ��·����
     * @return success boolean
     */
    public static boolean preservationImage (BufferedImage image, String format, String fileName) {
    	
    	boolean success = true;  	
        try {
        	
        	File file = new File(fileName);
        	file.mkdirs();
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			
			e.printStackTrace();
			success = false;
		} finally {
			
		}
        return success;
    }   
    /*
     * @version ��ָ��ͼ����ָ����ʽ���浽ָ��·����
     * @return success boolean
     */
    public static boolean preservationImage (ImageIcon image, String format, String fileName) {
    	
    	boolean success = true;  	
        try {
        	
        	File file = new File(fileName);
        	file.mkdirs();
	        BufferedImage output = new BufferedImage(image.getIconWidth(), image.getIconHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics2D graphics = output.createGraphics();
	        graphics.drawImage(image.getImage(), 0, 0, null);
			ImageIO.write(output, format, file);
		} catch (IOException e) {
			
			e.printStackTrace();
			success = false;
		}
        return success;
    }
    
    
    //���ڲ���
//    public static void main(String[] args){
//    	
//    	ChangeImage change = new ChangeImage();
//    	
////      40 ��Ϊ 5
//    	ImageIcon img = new ImageIcon("Image\\Head\\2.png");
//    	change.preservationImage(change.roundedCornerImage(img, 
//    			42,
//    			42,
//    			40,
//    			40,
//    			(42-40)/2,
//    			(42-40)/2,
//    			5),
//    			"png", 
//    			"Image\\Head\\2.png");
//    }
}
