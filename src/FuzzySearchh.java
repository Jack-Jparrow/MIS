import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * 模糊查询
 *
 * @Author: Jack Jparrow
 * @Date: 2021-06-23 07:43:50
 * @Last Modified by: Jack Jparrow
 * @Last Modified time: 2021-06-23 16:49:30
 */

public class FuzzySearchh extends JFrame {

    public JPanel jp1;
    public JTable table1;
    public DefaultTableModel model;
    public JScrollPane cen_pan1;

    void fuzzysearch() {
        this.setVisible(true);

        super.setTitle("查询结果");
        setSize(1400, 608);

        setLocationRelativeTo(null);// 屏幕居中
        setResizable(false);// 最大化不可用

        SpringLayout sl = new SpringLayout();
        Container cot = getContentPane();
        cot.setLayout(sl);

        jp1 = new JPanel(new BorderLayout());

        jp1.setPreferredSize(new Dimension(1400, 608));

        table1 = new JTable();// 第二步：获取表格的数据模型
        model = (DefaultTableModel) table1.getModel();// 对表格的数据模型操作
        String[] tab_title = { "id", "昵称", "性别", "上周活跃", "截至上周前总积分", "积分增加量", "绩点", "职务" };
        model.setColumnIdentifiers(tab_title);// 设置表头

        table1.setModel(model);// 更新表格模型
        cen_pan1 = new JScrollPane();
        cen_pan1.setPreferredSize(new Dimension(1400, 608));
        cen_pan1.setViewportView(table1);// 为JScrollPane面板设置一个可视化图表
        jp1.add(cen_pan1, BorderLayout.CENTER);
        cot.add(jp1);

        // 居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table1.setDefaultRenderer(Object.class, renderer);
        table1.setCellSelectionEnabled(true);// 每次只能选一格
        table1.getTableHeader().setReorderingAllowed(false);// 设置不可拖动
        table1.getTableHeader().setResizingAllowed(false);// 设置不可改大小

    }

    // 查
    void fuzzysearchSqlDatebase(String addr, String str) {

        java.sql.ResultSet rs = null;
        java.sql.Statement stmt = null;
        java.sql.Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=MIS", "sa", "Sa123");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "select Id, Pname, Sex, ActivityLastWeek, TotalPointsAsOfLastWeek, TotalPointsOfLastWeek, GPA, Post from weekContains20210524 where Id like '%"
                            + str + "%' or Pname like '%" + str + "%' or Sex like '%" + str
                            + "%' or ActivityLastWeek like '%" + str + "%' or TotalPointsAsOfLastWeek like '%" + str
                            + "%' or TotalPointsOfLastWeek like '%" + str + "%' or GPA like '%" + str
                            + "%' or Post like '%" + str
                            + "%' order by (charindex(rtrim(cast(Post as nchar)), (', 队长, 副队长, 精英, 普通成员, 车队新人, '))), GPA desc;");
            while (rs.next()) {
                // for(int i=0 ; i<10 ; i++) //增加行
                model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) });
            }
        } catch (ClassNotFoundException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        } catch (SQLException e2) {
            // TODO: handle exception
            e2.printStackTrace();
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
