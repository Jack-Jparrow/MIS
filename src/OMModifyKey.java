import java.awt.Container;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

/**
 * 修改密码
 *
 * @Author: Jack Jparrow
 * @Date: 2021-06-16 08:33:08
 * @Last Modified by: Jack Jparrow
 * @Last Modified time: 2021-06-23 09:39:09
 */

public class OMModifyKey extends JFrame implements ActionListener {
    static JLabel dycmm;
    static JLabel decmm;
    public static JPasswordField jpdycmm, jpdecmm;
    static JButton jbqd;
    static JButton jbcz;

    public String st;
    public String sp;
    // public String jljurisdiction;

    void ommodifykey() {
        super.setTitle("修改密码");
        setSize(350, 250);
        // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// 屏幕居中
        setResizable(false);// 最大化不可用

        SpringLayout sl = new SpringLayout();
        Container cot = getContentPane();
        cot.setLayout(sl);

        dycmm = new JLabel("输入密码：");
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, dycmm, -80, SpringLayout.HORIZONTAL_CENTER, cot);// 水平位置
        sl.putConstraint(SpringLayout.SOUTH, dycmm, -170, SpringLayout.SOUTH, cot);// 垂直位置
        cot.add(dycmm);

        decmm = new JLabel("确认密码：");
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, decmm, -80, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, decmm, -120, SpringLayout.SOUTH, cot);
        cot.add(decmm);

        jpdycmm = new JPasswordField(15);
        // jpdycmm.setSize(250, 100);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jpdycmm, 20, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jpdycmm, -165, SpringLayout.SOUTH, cot);
        cot.add(jpdycmm);

        jpdecmm = new JPasswordField(15);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jpdecmm, 20, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jpdecmm, -115, SpringLayout.SOUTH, cot);
        cot.add(jpdecmm);
        jpdecmm.setEchoChar('*');

        jbqd = new JButton("确定");
        jbqd.addActionListener(this);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbqd, -75, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jbqd, -60, SpringLayout.SOUTH, cot);
        cot.add(jbqd);

        jbcz = new JButton("重置");
        jbcz.addActionListener(this);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbcz, 60, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jbcz, -60, SpringLayout.SOUTH, cot);
        cot.add(jbcz);

        this.setVisible(true);
    }

    // 对密码进行MD5加密
    public String MD5Encryption(String strmd) {
        String strByMd5 = "";
        // char[] cha = jpdecmm.getPassword();
        // String str = String.valueOf(cha);
        String str = strmd;
        // System.out.println(str);

        try {
            // 生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要。
            md.update(str.getBytes());
            // 通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            // 生成具体的md5密码到buf数组
            int i;
            StringBuilder buf = new StringBuilder("");

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            strByMd5 = buf.toString(); // 32位加密的结果
            // System.out.println(strByMd5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strByMd5;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // st = jpdycmm.getText();
        char[] pas = jpdecmm.getPassword();
        sp = String.valueOf(pas);

        // System.out.println(st);

        // TODO Auto-generated method stub
        if (e.getSource() == jbqd) {
            // if (st.length() > 0 && pas.length > 0 /*&& isInteger()*/) {

            try {
                conToSqlTo_id_userKey_jurisdiction();
                JOptionPane.showMessageDialog(this, "密码修改成功");
                this.setVisible(false);
                // System.out.println(MD5Encryption(String.valueOf(jpdycmm.getPassword())));
                // System.out.println(MD5Encryption(String.valueOf(jpdecmm.getPassword())));
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // }
        } else {
            jpdycmm.setText("");
            jpdecmm.setText("");
        }
    }

    public String getIdForLogin, getuserKeyForLogin, getJurisdictionForLogin;

    void conToSqlTo_id_userKey_jurisdiction() throws ParseException {
        java.sql.ResultSet rs = null;
        java.sql.Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from id_userKey_jurisdiction where id = '" + st + "'");
            // System.out.println("select * from id_userKey_jurisdiction where id = '" + st
            // + "'");

            if (rs.next()) {
                if ((MD5Encryption(String.valueOf(jpdycmm.getPassword())))
                        .equals(MD5Encryption(String.valueOf(jpdecmm.getPassword())))) {// SqlServer2019会给后面补足空格，用.trim()去除空格
                    // System.out.println(MD5Encryption(String.valueOf(jpdycmm.getPassword())));
                    // System.out.println(MDn(String.valueOf(jpdecmm.getPassword())));
                    String mdstr = "update id_userKey_jurisdiction set userKey = '"
                            + MD5Encryption(String.valueOf(jpdycmm.getPassword())) + "' where id = '" + st + "'";
                    // System.out.println(mdstr);
                    // System.out.println(jpdycmm.getPassword());
                    // System.out.println(jpdecmm.getPassword());
                    stmt.executeUpdate(mdstr);
                } else {
                    JOptionPane.showMessageDialog(this, "两次密码不一致， 请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                } else if (stmt != null) {
                    stmt.close();
                    stmt = null;
                } else if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }

    }
}
