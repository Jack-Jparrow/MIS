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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

/**
 * 登录界面 
 * 弹性布局
 *
 * @Author: Jack Jparrow
 * @Date: 2021-05-31 21:14:10
 * @Last Modified by: Jack Jparrow
 * @Last Modified time: 2021-06-06 21:36:03
 */

public class LogIn extends JFrame implements ActionListener {

    static JLabel jlloginid;
    static JLabel jlloginkey;
    public static JTextField jtfid;
    public static JPasswordField jtfkey;
    static JButton jbqd;
    static JButton jbcz;

    public String st;
    public String sp;
    // public String jljurisdiction;

    void logIn() {
        super.setTitle("登录");
        setSize(350, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// 屏幕居中
        setResizable(false);// 最大化不可用

        SpringLayout sl = new SpringLayout();
        Container cot = getContentPane();
        cot.setLayout(sl);

        jlloginid = new JLabel("账号id：");
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jlloginid, -80, SpringLayout.HORIZONTAL_CENTER, cot);// 水平位置
        sl.putConstraint(SpringLayout.SOUTH, jlloginid, -170, SpringLayout.SOUTH, cot);// 垂直位置
        cot.add(jlloginid);

        jlloginkey = new JLabel("密码：");
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jlloginkey, -80, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jlloginkey, -120, SpringLayout.SOUTH, cot);
        cot.add(jlloginkey);

        jtfid = new JTextField(15);
        // jtfid.setSize(250, 100);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jtfid, 20, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jtfid, -165, SpringLayout.SOUTH, cot);
        cot.add(jtfid);

        jtfkey = new JPasswordField(15);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jtfkey, 20, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.SOUTH, jtfkey, -115, SpringLayout.SOUTH, cot);
        cot.add(jtfkey);
        jtfkey.setEchoChar('*');

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
    public String MD5Encryption() {
        String strByMd5 = "";
        char[] cha = jtfkey.getPassword();
        String str = String.valueOf(cha);
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

    // 判断输入的id是否合法（正整数输入才合法）
    // public boolean isInteger() {
    //     st = jtfid.getText();

    //     for (int i = st.length(); --i >= 0;) {
    //         int chr = st.charAt(i);

    //         if (chr < 48 || chr > 57) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        st = jtfid.getText();
        char[] pas = jtfkey.getPassword();
        sp = String.valueOf(pas);

        // System.out.println(st);

        // TODO Auto-generated method stub
        if (e.getSource() == jbqd) {
            // if (st.length() > 0 && pas.length > 0 /*&& isInteger()*/) {

                try {
                    conToSqlTo_id_userKey_jurisdiction();
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // System.out.println(MD5Encryption());

            // }
        } else {
            jtfid.setText("");
            jtfkey.setText("");
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
            // System.out.println("select * from id_userKey_jurisdiction where id = '" + st + "'");

            if (rs.next()) {
                // System.out.println("ef");

                if (MD5Encryption().equals(rs.getString(2).trim())) {// SqlServer2019会给后面补足空格，用.trim()去除空格

                    // this.setVisible(false);

                    if ((rs.getString(3).trim().equals("普通成员") || rs.getString(3).trim().equals("车队新人")
                            || rs.getString(3).trim().equals("精英") || rs.getString(3).trim().equals("副队长"))) {
                        OrdinaryMembers om = new OrdinaryMembers();
                        // new OrdinaryMembers();
                        om.ordinaryMembers();
                        om.searchSqlDatebase(new Captain().getaddr().trim(), st.trim());
                        
                        om.setVisible(true);
                        this.setVisible(false);
                    }else if ((rs.getString(3).trim().equals("队长"))) {
                        Captain cap = new Captain();
                        rs.getString(3);
                        // new OrdinaryMembers();
                        cap.captain();
                        // cap.conToSqlDatebase();
                        cap.setVisible(true);

                        // OrdinaryMembers om = new OrdinaryMembers();
                        // // new OrdinaryMembers();
                        // om.ordinaryMembers();
                        // om.searchSqlDatebase(new Captain().getaddr().trim(), st.trim());
                        // // om.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        // om.setVisible(true);

                        this.setVisible(false);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "账号id或密码错误", "警告",JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this, "账号id或密码错误", "警告",JOptionPane.WARNING_MESSAGE);
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
