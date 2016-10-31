package sdk.qq.tools;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class VerlicelColumn implements LayoutManager, java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int vgap = 0;

    public VerlicelColumn() {
    }

    public VerlicelColumn(int vg) {
        this.vgap = vg;
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    //ȷ�����ֵ���ѡ��С    
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            int ncomponents = parent.getComponentCount();
            int h = 0;
            
            for (int i = 0; i < ncomponents; i++) {
                Component comp = parent.getComponent(i);
                h += comp.getPreferredSize().height + vgap;
            }
            return new Dimension(parent.getWidth(), h + 2 * vgap);
        }
    }

    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    //�÷����������������������λ�úʹ�С
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int ncomponents = parent.getComponentCount();
            if (ncomponents == 0) {
                return;
            }
            
            // y ��ֵ���Ǹ�����������¼��
            int y = vgap;
            for (int c = 0; c < ncomponents; c++) {
                int h = parent.getComponent(c).getPreferredSize().height;
                parent.getComponent(c).setBounds(
                        0,
                        y,
                        parent.getWidth(),
                        h);
                y += h + vgap;
            }
        }
    }
}

