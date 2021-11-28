import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 * 队长视图 
 * 最高权限
 *
 * @Author: Jack Jparrow
 * @Date: 2021-06-01 12:43:05
 * @Last Modified by: Jack Jparrow
 * @Last Modified time: 2021-06-23 09:37:26
 */

public class Captain extends JFrame implements ActionListener {

    public JLabel jlxzb, jlid, jljurisdiction;
    public JComboBox jcb;
    public JTextField jtfsearch;
    public JButton jbsearch, jbyjsc, jbcr, jbbc, jbsx, jbcxdl, jbwyxg, jbmhsearch;
    public JRadioButton jrbqx, jrbqbx;
    public Vector rowData, columnNames, line1;
    public JTable jt;
    public JScrollPane jsp;
    public DefaultTableModel dtm;
    // public JCheckBox jcx;

    public int n = 1;
    // public String sqlstr;
    public String c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, jcm;

    LogIn linom = new LogIn();

    public void captain() throws ParseException {
        // this.setVisible(true);
        conToSqlDatebase();

        Font zi = new Font("宋体", Font.TYPE1_FONT, 18);
        // Font zi2 = new Font("宋体", Font.TYPE1_FONT, 15);

        super.setTitle("查看所有人");
        setSize(1440, 1050);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            // 重写右上角叉号，使其打开登录界面
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                linom.logIn();
            }
        });
        setLocationRelativeTo(null);// 屏幕居中
        setResizable(false);// 最大化不可用

        SpringLayout sl = new SpringLayout();
        Container cot = getContentPane();
        cot.setLayout(sl);

        String[] strTable = new String[n];
        for (int i = 0; i < n; i++) {
            // strTable[i] = "weekContains" + convertWeekByDate(swapstd());
            // System.out.println(strTable[i]);
            strTable[i] = "weekContains20210524";
        }

        jlid = new JLabel("当前账号id：" + linom.jtfid.getText());
        jlid.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jlid, -550, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jlid, 55, SpringLayout.NORTH, cot);
        cot.add(jlid);

        // jljurisdiction = new JLabel("权限：" + linom.);
        // jljurisdiction.setFont(zi);
        // sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jljurisdiction, -600,
        // SpringLayout.HORIZONTAL_CENTER, cot);
        // sl.putConstraint(SpringLayout.NORTH, jljurisdiction, 75, SpringLayout.NORTH,
        // cot);
        // cot.add(jljurisdiction);

        jlxzb = new JLabel("请选择查看的表：");
        jlxzb.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jlxzb, -200, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jlxzb, 55, SpringLayout.NORTH, cot);
        cot.add(jlxzb);

        jcb = new JComboBox(strTable);
        // jcb.setSelectedItem(strTable);
        jcb.setPreferredSize(new Dimension(200, 30));
        jcb.setEditable(true);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jcb, -20, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jcb, 50, SpringLayout.NORTH, cot);
        cot.add(jcb);

        jcm = (String) jcb.getSelectedItem();
        // getaddr(jcm);
        // System.out.println(jcm);

        jbsearch = new JButton("id精确查找");
        jbsearch.addActionListener(this);
        // jbsearch.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbsearch, 530, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbsearch, 55, SpringLayout.NORTH, cot);
        cot.add(jbsearch);

        jbmhsearch = new JButton("模糊查找");
        jbmhsearch.addActionListener(this);
        // jbsearch.setFont(zi);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbmhsearch, 630, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbmhsearch, 55, SpringLayout.NORTH, cot);
        cot.add(jbmhsearch);

        jtfsearch = new JTextField("请输入要查找内容");
        // jtfsearch.addActionListener(this);
        // jbsearch.setFont(zi);
        jtfsearch.setPreferredSize(new Dimension(200, 30));
        jtfsearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 64, 54)));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jtfsearch, 360, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jtfsearch, 53, SpringLayout.NORTH, cot);
        cot.add(jtfsearch);

        JSeparator sep = new JSeparator(SwingConstants.CENTER);
        sep.setPreferredSize(new Dimension(this.getWidth(), 5));
        sep.setBackground(new Color(153, 153, 153));
        // sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, sep, 460,
        // SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, sep, 100, SpringLayout.NORTH, cot);
        // sep.setBackground(Color.GRAY);
        cot.add(sep);

        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbqx);
        bg.add(jrbqbx);

        jbyjsc = new JButton("删除选中行");
        jbyjsc.addActionListener(this);
        // jbyjsc.setFont(zi2);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbyjsc, -370, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbyjsc, 120, SpringLayout.NORTH, cot);
        cot.add(jbyjsc);

        jbcr = new JButton("插入新行");
        jbcr.addActionListener(this);
        // jbyjsc.setFont(zi2);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbcr, -270, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbcr, 120, SpringLayout.NORTH, cot);
        cot.add(jbcr);

        jbwyxg = new JButton("修改内容");
        jbwyxg.addActionListener(this);
        // jbyjsc.setFont(zi2);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbwyxg, -170, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbwyxg, 120, SpringLayout.NORTH, cot);
        cot.add(jbwyxg);

        jbbc = new JButton("提交修改");
        jbbc.addActionListener(this);
        // jbyjsc.setFont(zi2);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbbc, -70, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbbc, 120, SpringLayout.NORTH, cot);
        cot.add(jbbc);

        jbcxdl = new JButton("退出登录");
        jbcxdl.addActionListener(this);
        // jbyjsc.setFont(zi2);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbcxdl, 30, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jbcxdl, 120, SpringLayout.NORTH, cot);
        cot.add(jbcxdl);

        // jbsx = new JButton("刷新");
        // jbsx.addActionListener(this);
        // // jbyjsc.setFont(zi2);
        // sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jbsx, -70,
        // SpringLayout.HORIZONTAL_CENTER, cot);
        // sl.putConstraint(SpringLayout.NORTH, jbsx, 120, SpringLayout.NORTH, cot);
        // cot.add(jbsx);

        JSeparator sep2 = new JSeparator(SwingConstants.CENTER);
        sep2.setPreferredSize(new Dimension(this.getWidth(), 5));
        sep2.setBackground(new Color(153, 153, 153));
        // sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, sep2, 460,
        // SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, sep2, 155, SpringLayout.NORTH, cot);
        // sep.setBackground(Color.GRAY);
        cot.add(sep2);

        jt = new JTable(rowData, columnNames);
        dtm = new DefaultTableModel(rowData, columnNames);
        jt.setPreferredSize(new Dimension(1400, 608));
        jsp = new JScrollPane(jt);
        jsp.setPreferredSize(new Dimension(1400, 608));
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, jsp, 0, SpringLayout.HORIZONTAL_CENTER, cot);
        sl.putConstraint(SpringLayout.NORTH, jsp, 180, SpringLayout.NORTH, cot);
        // 居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        jt.setDefaultRenderer(Object.class, renderer);
        jt.setCellSelectionEnabled(true);// 每次只能选一格
        jt.getTableHeader().setReorderingAllowed(false);//设置不可拖动
        jt.getTableHeader().setResizingAllowed(false);//设置不可改大小

        // DefaultTableModel newTableModel = new DefaultTableModel(rowData,columnNames){
        // @Override
        // public boolean isCellEditable(int row,int column){
        // return false;
        // }
        // };

        // jt.setModel(newTableModel);
        jt.setEnabled(false);

        jsp.setVisible(true);
        jt.setVisible(true);
        // cot.add(jt);
        cot.add(jsp);
        // conToSqlDatebase();

        // this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        // 改
        if (e.getSource() == jbbc) {
            // System.out.println("qwe");
            for (int i = 0; i < rowData.size(); i++) {
                c0 = String.valueOf(jt.getValueAt(i, 0));
                c1 = String.valueOf(jt.getValueAt(i, 1));
                c2 = String.valueOf(jt.getValueAt(i, 2));
                c3 = String.valueOf(jt.getValueAt(i, 3));
                c4 = String.valueOf(jt.getValueAt(i, 4));
                c5 = String.valueOf(jt.getValueAt(i, 5));
                c6 = String.valueOf(jt.getValueAt(i, 6));
                c7 = String.valueOf(jt.getValueAt(i, 7));
                // c8 = String.valueOf(jt.getValueAt(i, 8));
                // c9 = String.valueOf(jt.getValueAt(i, 9));
                modifySqlDatebase();

                // System.out.println(c9);
            }
            JOptionPane.showMessageDialog(this, "保存修改成功！");
        }

        // 增
        if (e.getSource() == jbcr) {
            InsertDate ind = new InsertDate();
            ind.insertDate();
            ind.setVisible(true);
            this.setVisible(false);
        }

        // 删
        if (e.getSource() == jbyjsc) {
            // System.out.println("dfd");
            int numrow = jt.getSelectedRow();
            int count = jt.getSelectedRowCount();
            // if (numrow > 0) {
            for (int i = 0; i < count; i++) {
                // dtm.removeRow(numrow + 1);
                // System.out.println(numrow + 1);
                String strid = String.valueOf(jt.getValueAt(numrow, 0));
                deleteSqlDatebase(strid);
            }

            JOptionPane.showMessageDialog(this, "删除成功！请重新登录");
            dispose();// 关闭此窗口
            linom.logIn();
            linom.setVisible(true);// 打开登陆窗口
            // }
        }

        // 精确查
        if (e.getSource() == jbsearch) {
            // String jcm = (String )jcb.getSelectedItem();
            Searchres sc = new Searchres();
            sc.searchres();
            sc.searchSqlDatebase(jcm, jtfsearch.getText());
            // sc.setVisible(true);
            // System.out.println(jtfsearch.getText());
        }

        if (e.getSource() == jbmhsearch) {
            FuzzySearchh fs = new FuzzySearchh();
            fs.fuzzysearch();
            fs.fuzzysearchSqlDatebase(jcm, jtfsearch.getText());
        }

        if (e.getSource() == jbcxdl) {
            dispose();
            linom.logIn();
        }

        if (e.getSource() == jbwyxg) {
            jt.setEnabled(true);
        }
    }

    public String getaddr() throws ParseException {
        captain();
        // System.out.println(jcm);
        return jcm;

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

    // 列出
    void conToSqlDatebase() {

        java.sql.ResultSet rs = null;
        java.sql.Statement stmt = null;
        java.sql.Connection conn = null;

        columnNames = new Vector();
        // 设置列名

        columnNames.add("ID");
        columnNames.add("昵称");
        columnNames.add("性别");
        columnNames.add("上周活跃");
        columnNames.add("截至上周前总积分");
        columnNames.add("截至上周结束总积分");
        columnNames.add("积分增加量");
        columnNames.add("绩点");
        // columnNames.add("排名");
        // columnNames.add("职务");
        // columnNames.add(" ");

        rowData = new Vector();
        // rowData可以存放多行,开始从数据库里取

        try {
            // 加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from weekContains" + /* convertWeekByDate(swapstd()) */"20210524"
                    + " where Post in ('队长', '副队长', '精英', '普通成员', '车队新人') order by (charindex(rtrim(cast(Post as nchar)), (', 队长, 副队长, 精英, 普通成员, 车队新人, '))), GPA desc;");

            while (rs.next()) {
                // rowData可以存放多行
                line1 = new Vector();

                line1.add(rs.getString(2).trim());
                line1.add(rs.getString(1).trim());
                line1.add(rs.getString(3).trim());
                line1.add(rs.getString(4).trim());
                line1.add(rs.getString(5).trim());
                line1.add(rs.getString(6).trim());
                line1.add(rs.getString(7).trim());
                line1.add(rs.getString(8).trim());
                line1.add(rs.getString(9).trim());
                // line1.add(rs.getString("Post").trim());
                // line1.add("");

                // System.out.println(rs.getString("Post"));

                // 加入到rowData
                rowData.add(line1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    // 改
    void modifySqlDatebase() {

        // java.sql.ResultSet rs = null;
        java.sql.Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            // 加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmt = conn.createStatement();
            String mdstr = "update weekContains" + /* convertWeekByDate(swapstd()) */"20210524" + " set Pname = '" + c1
                    + "', Id = '" + c0 + "', Sex = '" + c2 + "', ActivityLastWeek = " + c3
                    + ", TotalPointsAsOfLastWeek = " + c4 + ", TotalPointsOfLastWeek = " + c5
                    + ", IncreaseOfIntegral = " + c6 + ", GPA = " + c7 + "' where Id = '" + c0 + "'";

            // stmt.executeUpdate(mdstr);
            System.out.println(mdstr);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    // 删
    void deleteSqlDatebase(String strid) {

        // java.sql.ResultSet rs = null;
        java.sql.Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            // 加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmt = conn.createStatement();
            String dlstr1 = "delete from weekContains" + /* convertWeekByDate(swapstd()) */"20210524" + " where Id = '"
                    + strid + "'";
            String dlstr2 = "delete from id_userKey_jurisdiction where Id = '" + strid + "'";

            stmt.executeUpdate(dlstr1);
            stmt.executeUpdate(dlstr2);
            // System.out.println(dlstr);
            // System.out.println(strid);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
