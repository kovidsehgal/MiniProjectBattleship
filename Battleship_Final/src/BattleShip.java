import logic.BattleshipLogic;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.table.*;


public class BattleShip extends JPanel {
    private static void createAndShowGUI() {
        BattleshipLogic battleshipLogic = new BattleshipLogic();
        battleshipLogic.generateShips();
        JFrame frame = new JFrame("Battleship-Game");
        frame.setSize(670, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable(7, 7);
        table.setRowHeight(91);
        table.setShowGrid(true);
        setColumnWidths(table, 93, 93, 93, 93, 93, 93, 93);
        table.setOpaque(false);
        DefaultTableCellRenderer renderer =
                (DefaultTableCellRenderer) table.getDefaultRenderer(Object.class);
        renderer.setOpaque(false);

        URL url = ClassLoader.getSystemClassLoader().getResource("resources\\board1.jpg");
        ImageIcon iconBoard = new ImageIcon(url);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
                                   public void mouseClicked(java.awt.event.MouseEvent e) {
                                       int row = table.rowAtPoint(e.getPoint());
                                       int col = table.columnAtPoint(e.getPoint());
                                       boolean isHit = battleshipLogic.hit(row, col);
                                       System.out.println("Is a hit? " + isHit);
                                       if (isHit == false) {
                                           table.setValueAt("    \"MISS\"", row, col);
                                           JOptionPane.showMessageDialog(null, "The cell at row " + row + " & column " + col + " was a MISS");
                                       } else {
                                           table.setValueAt("    \"HIT\"", row, col);
                                           battleshipLogic.checkforWin();
                                           JOptionPane.showMessageDialog(null, "The cell at row " + row + " & column " + col + " was a HIT");
                                       }
                                   }
                               }
        );

        JPanel background = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(iconBoard.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        background.add(table);
        frame.add(background, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public static void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            } else break;
        }
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}


