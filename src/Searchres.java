
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.DriverManager;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * 普通成员视图 弹性布局
 *
 * @Author: Jack Jparrow
 * @Date: 2021-06-01 12:42:19
 * @Last Modified by: Jack Jparrow
 * @Last Modified time: 2021-06-23 07:48:50
 */

public class Searchres extends JFrame implements ActionListener {

    public JLabel yourId, modifyKey, yourName, yourSex, yourActivityLastWeek, yourTotalPointsAsOfLastWeek,
                    yourTotalPointsOfLastWeek, yourIncreaseOfIntegral, yourGPA, yourRange, yourPost;
    public static JTextField idInf, nameInf, sexInf, activityLastWeekInf, totalPointsAsOfLastWeekInf,
                    totalPointsOfLastWeekInf, increaseOfIntegralInf, GPAInf, rangeInf, postInf;
    JButton jbqr, xgnc, xgxb, jbgm;

    LogIn linom = new LogIn();

    void searchres() {
        this.setVisible(true);

        super.setTitle("查询结果");
            setSize(600, 768);
            // setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            // this.addWindowListener(new WindowAdapter() {
            //         // 重写右上角叉号，使其打开登录界面
            //         @Override
            //         public void windowClosing(WindowEvent e) {
            //                 dispose();
            //                 linom.logIn();
            //         }
            // });
            setLocationRelativeTo(null);// 屏幕居中
            setResizable(false);// 最大化不可用
            SpringLayout sl = new SpringLayout();
            Container cot = getContentPane();
            cot.setLayout(sl);

            Font zi = new Font("宋体", Font.TYPE1_FONT, 18);

            yourId = new JLabel("账   号   id  ：");
            yourId.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourId, -130, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourId, 30, SpringLayout.NORTH, cot);
            cot.add(yourId);

            modifyKey = new JLabel("密         码 ：");
            modifyKey.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, modifyKey, -130, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, modifyKey, 90, SpringLayout.NORTH, cot);
            cot.add(modifyKey);

            yourName = new JLabel("昵         称 ：");
            yourName.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourName, -130, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourName, 150, SpringLayout.NORTH, cot);
            cot.add(yourName);

            yourSex = new JLabel("性         别 ：");
            yourSex.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourSex, -130, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourSex, 210, SpringLayout.NORTH, cot);
            cot.add(yourSex);

            yourActivityLastWeek = new JLabel("上 周 活 跃 度：");
            yourActivityLastWeek.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourActivityLastWeek, -130,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourActivityLastWeek, 270, SpringLayout.NORTH, cot);
            cot.add(yourActivityLastWeek);

            yourTotalPointsAsOfLastWeek = new JLabel("截至上周总积分：");
            yourTotalPointsAsOfLastWeek.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourTotalPointsAsOfLastWeek, -130,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourTotalPointsAsOfLastWeek, 330, SpringLayout.NORTH, cot);
            cot.add(yourTotalPointsAsOfLastWeek);

            yourTotalPointsOfLastWeek = new JLabel("上周获得积分  ：");
            yourTotalPointsOfLastWeek.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourTotalPointsOfLastWeek, -130,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourTotalPointsOfLastWeek, 390, SpringLayout.NORTH, cot);
            cot.add(yourTotalPointsOfLastWeek);

            yourIncreaseOfIntegral = new JLabel("积分的增加量  ：");
            yourIncreaseOfIntegral.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourIncreaseOfIntegral, -130,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourIncreaseOfIntegral, 450, SpringLayout.NORTH, cot);
            cot.add(yourIncreaseOfIntegral);

            yourGPA = new JLabel("绩         点 ：");
            yourGPA.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourGPA, -130, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourGPA, 510, SpringLayout.NORTH, cot);
            cot.add(yourGPA);

            yourRange = new JLabel("综  合  排  名：");
            yourRange.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourRange, -130, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourRange, 570, SpringLayout.NORTH, cot);
            cot.add(yourRange);

            yourPost = new JLabel("职         务 ：");
            yourPost.setFont(zi);
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourPost, -130, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, yourPost, 630, SpringLayout.NORTH, cot);
            cot.add(yourPost);

            idInf = new JTextField(18);
            idInf.setEditable(false);// 设置不可编辑
            idInf.setBackground(new Color(255, 234, 72));// 文本框背景颜色
            idInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));// 边框线颜色
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, idInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, idInf, 33, SpringLayout.NORTH, cot);
            cot.add(idInf);

            jbgm = new JButton("修改密码");
            jbgm.addActionListener(this);
            jbgm.setPreferredSize(new Dimension(150, 25));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbgm, 50, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, jbgm, 93, SpringLayout.NORTH, cot);
            cot.add(jbgm);

            nameInf = new JTextField(18);
            nameInf.setEditable(false);
            nameInf.setBackground(new Color(255, 234, 72));
            nameInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, nameInf, 153, SpringLayout.NORTH, cot);
            cot.add(nameInf);

            sexInf = new JTextField(18);
            nameInf.setEditable(false);
            sexInf.setBackground(new Color(255, 234, 72));
            sexInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, sexInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, sexInf, 213, SpringLayout.NORTH, cot);
            cot.add(sexInf);

            activityLastWeekInf = new JTextField(18);
            activityLastWeekInf.setEditable(false);
            activityLastWeekInf.setBackground(new Color(255, 234, 72));
            activityLastWeekInf
                            .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, activityLastWeekInf, 50,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, activityLastWeekInf, 273, SpringLayout.NORTH, cot);
            cot.add(activityLastWeekInf);

            totalPointsAsOfLastWeekInf = new JTextField(18);
            totalPointsAsOfLastWeekInf.setEditable(false);
            totalPointsAsOfLastWeekInf.setBackground(new Color(255, 234, 72));
            totalPointsAsOfLastWeekInf
                            .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, totalPointsAsOfLastWeekInf, 50,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, totalPointsAsOfLastWeekInf, 333, SpringLayout.NORTH, cot);
            cot.add(totalPointsAsOfLastWeekInf);

            totalPointsOfLastWeekInf = new JTextField(18);
            totalPointsOfLastWeekInf.setEditable(false);
            totalPointsOfLastWeekInf.setBackground(new Color(255, 234, 72));
            totalPointsOfLastWeekInf
                            .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, totalPointsOfLastWeekInf, 50,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, totalPointsOfLastWeekInf, 393, SpringLayout.NORTH, cot);
            cot.add(totalPointsOfLastWeekInf);

            increaseOfIntegralInf = new JTextField(18);
            increaseOfIntegralInf.setEditable(false);
            increaseOfIntegralInf.setBackground(new Color(255, 234, 72));
            increaseOfIntegralInf
                            .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, increaseOfIntegralInf, 50,
                            SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, increaseOfIntegralInf, 453, SpringLayout.NORTH, cot);
            cot.add(increaseOfIntegralInf);

            GPAInf = new JTextField(18);
            GPAInf.setEditable(false);
            GPAInf.setBackground(new Color(255, 234, 72));
            GPAInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, GPAInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, GPAInf, 513, SpringLayout.NORTH, cot);
            cot.add(GPAInf);

            rangeInf = new JTextField(18);
            rangeInf.setEditable(false);
            rangeInf.setBackground(new Color(255, 234, 72));
            rangeInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, rangeInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, rangeInf, 573, SpringLayout.NORTH, cot);
            cot.add(rangeInf);

            postInf = new JTextField(18);
            postInf.setEditable(false);
            postInf.setBackground(new Color(255, 234, 72));
            postInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, postInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, postInf, 633, SpringLayout.NORTH, cot);
            cot.add(postInf);

            jbqr = new JButton("确认信息");
            jbqr.addActionListener(this);
            jbqr.setPreferredSize(new Dimension(200, 35));
            sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbqr, 0, SpringLayout.HORIZONTAL_CENTER, cot);
            sl.putConstraint(SpringLayout.NORTH, jbqr, 690, SpringLayout.NORTH, cot);
            cot.add(jbqr);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == jbqr) {
                dispose();// 关闭此窗口
               // linom.logIn();
               // linom.setVisible(true);// 打开登陆窗口
            } else{
                OMModifyKey omk = new OMModifyKey();
                omk.ommodifykey();
                omk.st = idInf.getText();
            }
        }

    //查
    void searchSqlDatebase(String addr, String strid) {

        java.sql.ResultSet rss = null;
        java.sql.Statement stmts = null;
        java.sql.Connection conns = null;

        try {
            // 加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conns = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmts = conns.createStatement();
            // String dlstr1 = "select * from weekContains" + convertWeekByDate(swapstd()) + " where Id = '" + strid + "'";
            // String dlstr2 = "delete from id_userKey_jurisdiction where Id = '" + strid + "'";

            rss = stmts.executeQuery("select * from " + addr + " where Id = '" + strid + "'");
            // stmt.executeUpdate(dlstr2);
            // System.out.println(dlstr);
            // System.out.println(strid);

            while (rss.next()) {
                idInf.setText(rss.getString("Id").trim());
                nameInf.setText(rss.getString("Pname").trim());
                sexInf.setText(rss.getString("Sex").trim());
                activityLastWeekInf.setText(rss.getString("ActivityLastWeek").trim());
                totalPointsAsOfLastWeekInf.setText(rss.getString("TotalPointsAsOfLastWeek").trim());
                totalPointsOfLastWeekInf.setText(rss.getString("TotalPointsOfLastWeek").trim());
                increaseOfIntegralInf.setText(rss.getString("IncreaseOfIntegral").trim());
                rangeInf.setText(rss.getString("Prange").trim());
                GPAInf.setText(rss.getString("GPA").trim());
                postInf.setText(rss.getString("Post").trim());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
