import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * 队长视图
 * 插入数据
 *
 * @Author: Jack Jparrow 
 * @Date: 2021-06-06 10:32:54 
 * @Last Modified by: Jack Jparrow
 * @Last Modified time: 2021-06-06 21:36:49
 */

public class InsertDate extends JFrame implements ActionListener {
    JLabel yourId, yourKey, yourName, yourSex, yourActivityLastWeek, yourTotalPointsAsOfLastWeek, yourTotalPointsOfLastWeek, yourIncreaseOfIntegral, yourGPA, yourRange, yourPost;
    static JTextField idInf, keyInf, nameInf,  activityLastWeekInf, totalPointsAsOfLastWeekInf,totalPointsOfLastWeekInf, increaseOfIntegralInf, GPAInf, rangeInf, postInf;
    JButton jbqr;
    JRadioButton jrbman, jrbwom;

    LogIn linom = new LogIn();
    // Captain cap = new Captain();

    void insertDate() {
        this.setVisible(true);

        super.setTitle("插入信息");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                this.addWindowListener(new WindowAdapter() {
                        // 重写右上角叉号，使其打开登录界面
                        @Override
                        public void windowClosing(WindowEvent e) {
                                dispose();
                                // linom.logIn();
                                Captain cap = new Captain();
                                try {
                                    cap.captain();
                                } catch (ParseException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                cap.setVisible(true);
                        }
                });
        setSize(600, 768);
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

        yourKey = new JLabel("初  始  密  码：");
        yourKey.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, yourKey, -130, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, yourKey, 90, SpringLayout.NORTH, cot);
        cot.add(yourKey);

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

        yourTotalPointsAsOfLastWeek = new JLabel("截至上周前总积分：");
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
        // idInf.setEditable(false);// 设置不可编辑
        idInf.setBackground(new Color(255, 234, 72));// 文本框背景颜色
        idInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));// 边框线颜色
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, idInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, idInf, 33, SpringLayout.NORTH, cot);
        cot.add(idInf);

        keyInf = new JTextField(18);
        // idInf.setEditable(false);// 设置不可编辑
        keyInf.setBackground(new Color(255, 234, 72));// 文本框背景颜色
        keyInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));// 边框线颜色
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, keyInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, keyInf, 93, SpringLayout.NORTH, cot);
        cot.add(keyInf);

        nameInf = new JTextField(18);
        // nameInf.setEditable(false);
        nameInf.setBackground(new Color(255, 234, 72));
        nameInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, nameInf, 153, SpringLayout.NORTH, cot);
        cot.add(nameInf);

        jrbman = new JRadioButton("男");
        jrbman.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jrbman, 0, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jrbman, 210, SpringLayout.NORTH, cot);
        cot.add(jrbman);

        jrbwom = new JRadioButton("女");
        jrbwom.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jrbwom, 70, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jrbwom, 210, SpringLayout.NORTH, cot);
        cot.add(jrbwom);

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(jrbman);
        bgroup.add(jrbwom);
        // cot.add(bgroup);

        activityLastWeekInf = new JTextField(18);
        // activityLastWeekInf.setEditable(false);
        activityLastWeekInf.setBackground(new Color(255, 234, 72));
        activityLastWeekInf
                        .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, activityLastWeekInf, 50,
                        SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, activityLastWeekInf, 273, SpringLayout.NORTH, cot);
        cot.add(activityLastWeekInf);

        totalPointsAsOfLastWeekInf = new JTextField(18);
        // totalPointsAsOfLastWeekInf.setEditable(false);
        totalPointsAsOfLastWeekInf.setBackground(new Color(255, 234, 72));
        totalPointsAsOfLastWeekInf
                        .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, totalPointsAsOfLastWeekInf, 50,
                        SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, totalPointsAsOfLastWeekInf, 333, SpringLayout.NORTH, cot);
        cot.add(totalPointsAsOfLastWeekInf);

        totalPointsOfLastWeekInf = new JTextField(18);
        // totalPointsOfLastWeekInf.setEditable(false);
        totalPointsOfLastWeekInf.setBackground(new Color(255, 234, 72));
        totalPointsOfLastWeekInf
                        .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, totalPointsOfLastWeekInf, 50,
                        SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, totalPointsOfLastWeekInf, 393, SpringLayout.NORTH, cot);
        cot.add(totalPointsOfLastWeekInf);

        increaseOfIntegralInf = new JTextField(18);
        // increaseOfIntegralInf.setEditable(false);
        increaseOfIntegralInf.setBackground(new Color(255, 234, 72));
        increaseOfIntegralInf
                        .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, increaseOfIntegralInf, 50,
                        SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, increaseOfIntegralInf, 453, SpringLayout.NORTH, cot);
        cot.add(increaseOfIntegralInf);

        GPAInf = new JTextField(18);
        // GPAInf.setEditable(false);
        GPAInf.setBackground(new Color(255, 234, 72));
        GPAInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, GPAInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, GPAInf, 513, SpringLayout.NORTH, cot);
        cot.add(GPAInf);

        rangeInf = new JTextField(18);
        // rangeInf.setEditable(false);
        rangeInf.setBackground(new Color(255, 234, 72));
        rangeInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, rangeInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, rangeInf, 573, SpringLayout.NORTH, cot);
        cot.add(rangeInf);

        postInf = new JTextField(18);
        // postInf.setEditable(false);
        postInf.setBackground(new Color(255, 234, 72));
        postInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, postInf, 50, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, postInf, 633, SpringLayout.NORTH, cot);
        cot.add(postInf);

        jbqr = new JButton("确认");
        jbqr.addActionListener(this);
        jbqr.setPreferredSize(new Dimension(200, 35));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbqr, 0, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbqr, 690, SpringLayout.NORTH, cot);
        cot.add(jbqr);
    }

    // 对密码进行MD5加密
    public String MD5Encryption() {
        String strByMd5 = "";
        String str = keyInf.getText();
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
        // TODO Auto-generated method stub
        if (e.getSource() == jbqr) {
            // cap.setVisible(false);
            insertSqlDatebase();
            JOptionPane.showMessageDialog(this, "插入成功！请重新登录");
            dispose();// 关闭此窗口
            linom.logIn();
            linom.setVisible(true);// 打开登陆窗口
        }
    }

    // 获取当前日期七天前的日期
    public String getStatetime() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);

        return preMonday;
    }

    // 获取那一周的周一
    private String convertWeekByDate(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());

        return imptimeBegin;
    }

    // String 转 Date
    public Date swapstd() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        // Date date = simpleDateFormat.parse(getStatetime());

        return simpleDateFormat.parse(getStatetime());
    }

    //增
    void insertSqlDatebase() {

        // java.sql.ResultSet rs = null;
        java.sql.Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            // 加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmt = conn.createStatement();
            String mdstr1 = "insert into id_userKey_jurisdiction" + " values(" + "'" + idInf.getText().trim() + "'" + ", " + "'" + MD5Encryption().trim() + "'" + ", " + "'" + postInf.getText().trim() + "');";
            String mdstr2 = "insert into weekContains" + /*convertWeekByDate(swapstd())*/"20210524" + " values(" + "'" + nameInf.getText().trim() + "'" + ", " + "'" + idInf.getText().trim() + "', '";

            if (jrbman.isSelected()) {
                mdstr2 += jrbman.getText().trim() + "', " + activityLastWeekInf.getText().trim() + ", " + totalPointsAsOfLastWeekInf.getText().trim() + ", " + totalPointsAsOfLastWeekInf.getText().trim() + ", " + increaseOfIntegralInf.getText().trim() + ", " +GPAInf.getText().trim() + ", " + rangeInf.getText() + ", '" + postInf.getText().trim() + "');";
            }else{
                mdstr2 += jrbwom.getText().trim() + "', " + activityLastWeekInf.getText().trim() + ", " + totalPointsAsOfLastWeekInf.getText().trim() + ", " + totalPointsAsOfLastWeekInf.getText().trim() + ", " + increaseOfIntegralInf.getText().trim() + ", " +GPAInf.getText().trim() + ", " + rangeInf.getText() + ", '" + postInf.getText().trim() + "');";
            }

            stmt.executeUpdate(mdstr1);
            stmt.executeUpdate(mdstr2);
            // System.out.println(mdstr1);
            // System.out.println(mdstr2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
