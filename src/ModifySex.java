import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.sql.DriverManager;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

/**
 * 修改性别
 *
 * @Author: Jack Jparrow 
 * @Date: 2021-06-01 21:16:15 
 * @Last Modified by: Jack Jparrow
 * @Last Modified time: 2021-06-02 11:00:27
 */

public class ModifySex extends JFrame implements ActionListener {
    public JRadioButton jrbman, jrbwom;
    public JButton jbqdsex;

    void modifySex(){
        this.setVisible(true);

        Font zi = new Font("宋体", Font.TYPE1_FONT, 18);
        
        super.setTitle("修改性别");
        setSize(350, 250);
        // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// 屏幕居中
        setResizable(false);// 最大化不可用

        SpringLayout sl = new SpringLayout();
        Container cot = getContentPane();
        cot.setLayout(sl);

        jrbman = new JRadioButton("男");
        jrbman.addActionListener(this);
        jrbman.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jrbman, -50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jrbman, -150, SpringLayout.SOUTH, cot);
        cot.add(jrbman);

        jrbwom = new JRadioButton("女");
        jrbwom.addActionListener(this);
        jrbwom.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jrbwom, 50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jrbwom, -150, SpringLayout.SOUTH, cot);
        cot.add(jrbwom);

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(jrbman);
        bgroup.add(jrbwom);
        
        jbqdsex = new JButton("确定");
        jbqdsex.addActionListener(this);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbqdsex, 0, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jbqdsex, -90, SpringLayout.SOUTH, cot);
        cot.add(jbqdsex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        OrdinaryMembers om = new OrdinaryMembers();
        if (e.getSource() == jbqdsex) {
            if (jrbman.isSelected()) {
                om.sexInf.setText(jrbman.getText());
                try {
                    modifySqlDatebase(new Captain().getaddr(), new OrdinaryMembers().getid());
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }else{
                om.sexInf.setText(jrbwom.getText());
                try {
                    modifySqlDatebase(new Captain().getaddr(), new OrdinaryMembers().getid());
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(this, "修改成功");
            dispose();
        }
    }

    //改
    void modifySqlDatebase(String addr, String strid) {

        // java.sql.ResultSet rs = null;
        java.sql.Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            // 加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmt = conn.createStatement();
            String mdstr = "update " + addr;

            if (jrbman.isSelected()) {
                mdstr += " set Sex = '" + jrbman.getText() + "'";
            }else{
                mdstr += " set Sex = '" + jrbwom.getText() + "'";
            }
            
            mdstr += "where Id = '" + strid + "'";

            stmt.executeUpdate(mdstr);
            // System.out.println(mdstr);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
