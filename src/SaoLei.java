import javafx.geometry.Insets;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//BorderLayout 分为五个区域
//GridBagLayout  一个表格 大小不一样，比较灵活
//GridLayout 比较平整的表格，比较适合做按钮

public class SaoLei implements ActionListener {
    JFrame frame=new JFrame();

    ImageIcon imageIcon=new ImageIcon("bg.png");
    ImageIcon guess=new ImageIcon("U7Z]%SXOYSD43A)56}W5WYJ.png");
    ImageIcon lei=new ImageIcon("U7Z]%SXOYSD43A)56}W5WYJ.png");
    ImageIcon cailei=new ImageIcon("cailei.png");
    ImageIcon finish=new ImageIcon("finish.png");
    ImageIcon win=new ImageIcon("win.png");
    JButton button=new JButton(imageIcon);

    //数据结构
    int ROW=20;
    int COL=20;
    int [][]data=new int[ROW][COL];
    JButton[][]btes=new JButton[ROW][COL];
    int LEICOUNt=1;//雷的数量
    int lEIDATA=-1;
    int unopened=ROW*COL;
    int opened=0;
    int sencond=0;//时钟及时
    JLabel label1=new JLabel("雷数"+unopened);
    JLabel label2=new JLabel("已扫"+opened);
    JLabel label3=new JLabel("用时"+sencond+"s" );
    Timer timer=new Timer(1000,this);

    public SaoLei() {
        frame.setSize(1000,1100);//窗口大小
        frame.setAutoRequestFocus(false);//能不能改变大小：false不能
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关掉直接退出

        frame.setLayout(new BorderLayout());//分模块
        setHead();
        addlei();
        setButton();

        timer.start();

        frame.setVisible(true);//显示
    }

    private void addlei() {
        Random a=new Random();
        for (int i = 0; i <LEICOUNt ; ) {
            int r=a.nextInt(ROW);
            int c=a.nextInt(COL);
            if(data[r][c]!=lEIDATA){
                data[r][c]=lEIDATA;
                i++;
            }
        }
        int [][]p=new int [ROW+2][COL+2];
        int m=0;
        for (int i = 0; i <p.length ; i++) {
            for (int j = 0; j <p[i].length ; j++) {
                p[i][j]=0;
            }
        }
        for (int i = 1; i <p.length-1 ; i++) {
            for (int j = 1; j <p[i].length-1 ; j++) {
                p[i][j]=data[i-1][j-1];
            }
        }
        //已经赋值过了；现在直接对数组p进行雷的个数的加减
        for (int j= 1; j <ROW+1; j++) {
            for (int i= 1; i <COL+1; i++) {
                int count=0;
                if(p[i][j]==-1)continue;;
                if(p[i-1][j-1]==lEIDATA)count++;
                if(p[i-1][j]==lEIDATA)count++;
                if(p[i-1][j+1]==lEIDATA)count++;
                if(p[i][j-1]==lEIDATA)count++;
                if(p[i][j+1]==lEIDATA)count++;
                if(p[i+1][j-1]==lEIDATA)count++;
                if(p[i+1][j]==lEIDATA)count++;
                if(p[i+1][j+1]==lEIDATA) count++;
                p[i][j]=count;

            }

        }
        for (int i = 0; i <ROW ; i++) {
            for (int j = 0; j <COL ; j++) {
                data[i][j]=p[i+1][j+1];
            }
        }
        //在进行完的时候，然后再进行一次赋值操作，把P的值赋值给data
        //现在的data就是row*col的数组，且每个都有雷的个数
        //这是方法一
//        for (int i = 0; i < ROW; i++) {
//            for (int j = 0; j <COL ; j++) {
//                if(data[i][j]==lEIDATA)continue;;
//                int tep=0;
//
//            }
//        }
    }

    private void setButton() {
        Container con=new Container();
        con.setLayout(new GridLayout(ROW,COL));
        for (int i = 0; i <ROW ; i++) {
            for (int j = 0; j <COL ; j++) {
                JButton bnt=new JButton(guess);
                bnt.setOpaque(true);
                bnt.setBackground(new Color(244,183,133));
                bnt.addActionListener(this);
//                JButton bnt=new JButton(data[i][j]+"");

                con.add(bnt);
                btes[i][j]=bnt;
            }

        }

        frame.add(con,BorderLayout.CENTER);
    }

    private void setHead() {
        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints c1=new GridBagConstraints(0,0,3,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new java.awt.Insets(0,0,0,0),0,0);
        panel.add(button,c1);
        button.addActionListener(this);


        label1.setOpaque(true);
        label1.setBackground(Color.white);
        label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label2.setOpaque(true);
        label2.setBackground(Color.white);
        label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label3.setOpaque(true);
        label3.setBackground(Color.white);
        label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        button.setOpaque(true);
        button.setBackground(Color.white);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints c2=new GridBagConstraints(0,1,1,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new java.awt.Insets(0,0,0,0),0,0);
        panel.add(label1,c2);

        GridBagConstraints c3=new GridBagConstraints(1,1,1,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new java.awt.Insets(0,0,0,0),0,0);
        panel.add(label2,c3);

        GridBagConstraints c4=new GridBagConstraints(2,1,1,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new java.awt.Insets(0,0,0,0),0,0);
        panel.add(label3,c4);

        frame.add(panel,BorderLayout.NORTH);//把格局分为N，S，W，E，H

    }

    public static void main(String[] args) {
        new SaoLei();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()instanceof Timer){
            sencond++;
            label3.setText("用时"+sencond+"s");
            timer.start();
            return;
        }
        JButton btn=(JButton)e.getSource();
        if(btn.equals((button))){
            restart();
            return;
        }
        for (int i = 0; i <ROW ; i++) {
            for (int j = 0; j <COL ; j++) {
                if(btn.equals(btes[i][j])){
                    if(data[i][j]==-1){
                        lose();
                    }else
                    openCell(i,j);
                    cheak();
                    return;
                }
            }
        }
    }

    //1.给数据清零2.给按钮回复状态3.重新启用时钟
    private void restart() {
        //恢复数据和按钮
        for (int i = 0; i <ROW ; i++) {
            for (int j = 0; j < COL; j++) {
                    data[i][j]=0;
                    btes[i][j].setBackground(new Color(244,183,113));
                    btes[i][j].setEnabled(true);
                    btes[i][j].setText("");
                    btes[i][j].setIcon(guess);
            }
        }
        //恢复状态栏
        unopened=ROW*COL;
        opened=0;
        sencond=0;
        label1=new JLabel("雷数"+unopened);
        label2=new JLabel("已扫"+opened);
        label3=new JLabel("用时"+sencond+"s" );
        //更新时钟
        addlei();
        timer.start();
    }

    private void cheak() {
        int count=0;
        for (int i = 0; i <ROW ; i++) {
            for (int j = 0; j <COL ; j++) {
                if(btes[i][j].isEnabled())count++;
            }
        }
        if(count==LEICOUNt){
            timer.stop();
            for (int i = 0; i <ROW ; i++) {
                for (int j = 0; j <COL ; j++) {
                    if(btes[i][j].isEnabled()){
                        btes[i][j].setIcon(win);
                    }
                }
            }
            button.setIcon(finish);
            JOptionPane.showMessageDialog(frame,"你赢了！\n点击上面的banner重新开始");

        }

    }

    private void lose() {
        timer.stop();
        button.setIcon(cailei);
        for (int i = 0; i <ROW ; i++) {
            for (int j = 0; j <COL ; j++) {
                JButton bnt=btes[i][j];
                    if(btes[i][j].isEnabled()){
                        if(data[i][j]==lEIDATA){
                            bnt.setEnabled(false);
                            bnt.setIcon(lei);
                            bnt.setDisabledIcon(lei);
                        }else{
                            bnt.setIcon(null);
                            bnt.setEnabled(false);
                            bnt.setOpaque(true);
                            bnt.setText(data[i][j]+"");

                        }
                    }
            }
        }
        JOptionPane.showMessageDialog(frame,"踩雷了！\n点击上面的banner重新开始");
    }

    private void    openCell(int i,int j){
        JButton bnt=btes[i][j];
        if(!bnt.isEnabled())return;

        bnt.setIcon(null);
        bnt.setEnabled(false);
        bnt.setOpaque(true);
        bnt.setBackground(Color.YELLOW);
        bnt.setText(data[i][j]+"");
        addOpenCount();

        if(data[i][j]==0){
            if(i>0 &&  j>0 &&data[i-1][j-1]==0)openCell(i-1,j-1);
            if(i>0 && data[i-1][j]==0)openCell(i-1,j);
            if(i>0 && j<COL-1&&data[i-1][j+1]==0)openCell(i-1,j+1);
            if(i>0  && data[i][j-1]==0)openCell(i,j-1);
            if(i<ROW-1 &&data[i][j+1]==0)openCell(i,j+1);
            if(i< ROW-1&&j>0&&data[i+1][j-1]==0)openCell(i+1,j-1);
            if(i<ROW-1 &&data[i+1][j]==0)openCell(i+1,j);
            if(i<ROW-1 &&j<COL-1&&data[i+1][j+1]==0)openCell(i+1,j+1);
        }
    }

    private void addOpenCount() {
        opened++;
        unopened--;
        label1.setText("待开"+unopened);
        label2.setText("待开"+opened);
    }
}
