package AIService.AIServiceImpl;

import AIService.AIService;
import com.goBang.model.Board;

import java.awt.*;
import java.util.ArrayList;

import static checkBoardService.size.column;
import static checkBoardService.size.row;

public class AIwork implements AIService {
    //黑1 白2 空0
    //各个点的AIscore得分数组；
    int [][]AIscore=new int[row][column];
    //各个点player的得分数组
    int [][] playerscore=new int[row][column];
    //target的point类
    ArrayList<Point> target = new ArrayList<Point>();
    //找出棋盘上全部的棋子旁边的位置 标记;
    public void Find(Board board){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(board.chessboard[i][j]!='0'){//找到棋子
                    //做标记
                    if(i-1>=0&&board.chessboard[i-1][j+1]=='0'){//找到棋子附近位置且该位置在棋盘内且未被填写棋子
                        Point a=new Point(i-1,j+1);
                        target.add(a);
                    }
                    if(i-1>=0&&board.chessboard[i-1][j]=='0'){
                        Point a=new Point(i-1,j);
                        target.add(a);
                    }
                    if(i-1>=0&&j-1>=0&&board.chessboard[i-1][j-1]=='0'){
                        Point a=new Point(i-1,j-1);
                        target.add(a);
                    }
                    if(j-1>=0&&board.chessboard[i][j-1]=='0'){
                        Point a=new Point(i,j-1);
                        target.add(a);
                    }
                    if(j-1>=0&&board.chessboard[i+1][j-1]=='0'){
                        Point a=new Point(i+1,j-1);
                        target.add(a);
                    }
                    if(board.chessboard[i][j+1]=='0') {
                        Point a = new Point(i, j + 1);
                        target.add(a);
                    }
                    if(board.chessboard[i+1][j+1]=='0') {
                        Point a = new Point(i + 1, j + 1);
                        target.add(a);
                    }
                    if(board.chessboard[i+1][j]=='0') {
                        Point a = new Point(i + 1, j);
                        target.add(a);
                    }
                }
            }
        }
    }

    //对target中的每个位置的八个方向进行评分,取各个方向的四个棋子
    public void InitSCore(Board board, char AI,char player) {
        //活五
        //向左
        int array = 4;
        for (int i = 0; i < target.size(); i++) {
            int count = 0;
                for (int j = (int) (target.get(i).getX() - 1); j >= 0 && array > 0; j--) {
                    array--;
                    if (board.chessboard[j][(int) target.get(i).getY()] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }

                //向右
                array = 4;
                count = 0;
                for (int j = (int) target.get(i).getX() + 1; j < row && array > 0; j++) {
                    array--;
                    if (board.chessboard[j][(int) target.get(i).getY()] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }
                //向上
                array = 4;
                count = 0;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    if (board.chessboard[(int) target.get(i).getX()][j] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }
                //向下
                array = 4;
                count = 0;
                for (int j = (int) target.get(i).getY() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    if (board.chessboard[(int) target.get(i).getX()][j] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }

                //左斜上
                array = 4;
                count = 0;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    if (board.chessboard[j][k] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }
                //左斜下
                array = 4;
                count = 0;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    if (board.chessboard[j][k] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }
                //右斜上
                array = 4;
                count = 0;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    if (board.chessboard[j][k] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }
                //右斜下
                array = 4;
                count = 0;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    if (board.chessboard[j][k] == AI) {
                        count++;
                    }
                    if (count == 4) {
                        AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                    }
                }

                String code = AI + "";
                //活四
                String alive_4 = '0' + AI + AI + AI + AI + '0' + "";
                String Ncode = "";
                //横向
                array = 4;
                for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    code = board.chessboard[j][(int) target.get(i).getY()] + code;
                    Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
                }
                array = 4;
                for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                    array--;
                    code = code + board.chessboard[k][(int) target.get(i).getY()];
                    Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
                }
                if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
                }
                //纵向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    code = code + board.chessboard[(int) target.get(i).getX()][j];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                    array--;
                    code = board.chessboard[(int) target.get(i).getX()][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
                }
                //斜线方向\
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
                }
                //反斜线方向/
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
                }

                //双眠四
                String Fhighdie_4 = '0' + AI + AI + AI + AI + player + "";
                String Shighdie_4 = AI + '0' + AI + AI + AI + "";
                String Thighdie_4 = AI + AI + '0' + AI + AI + "";
                String FOhighdie_4 = AI + AI + AI + '0' + AI + "";
                String fihighdie_4 = player + AI + AI + AI + AI + '0' + "";
                int number_highdie_4 = 0;//记录有几个眠四
                //横向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    code = board.chessboard[j][(int) target.get(i).getY()] + code;
                    Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
                }
                array = 4;
                for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                    array--;
                    code = code + board.chessboard[k][(int) target.get(i).getY()];
                    Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
                }
                if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                    number_highdie_4++;
                }
                //纵向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    code = code + board.chessboard[(int) target.get(i).getX()][j];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                    array--;
                    code = board.chessboard[(int) target.get(i).getX()][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                    number_highdie_4++;
                }
                //斜线方向\
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                    number_highdie_4++;
                }
                //反斜线方向/
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                    number_highdie_4++;
                }
                if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                    number_highdie_4++;
                }
                if (number_highdie_4 > 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
                }

                //highdie_4&alive_3
                int number_alive_3 = 0;//记录活三的数量
                String Falive_3 = '0' + AI + AI + AI + '0' + "";
                String Salive_3 = AI + '0' + AI + AI + "";//跳活三
                String Talive_3 = AI + AI + '0' + AI + "";//跳活三
                //横向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    code = board.chessboard[j][(int) target.get(i).getY()] + code;
                    Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
                }
                array = 4;
                for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                    array--;
                    code = code + board.chessboard[k][(int) target.get(i).getY()];
                    Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
                }
                if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                    number_alive_3++;
                }
                //纵向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    code = code + board.chessboard[(int) target.get(i).getX()][j];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                    array--;
                    code = board.chessboard[(int) target.get(i).getX()][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                    number_alive_3++;
                }
                //斜线方向\
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                    number_alive_3++;
                }
                //反斜线方向/
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_alive_3++;
                }
                if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                    number_alive_3++;
                }
                //眠四活三
                if (number_alive_3 > 1 && number_highdie_4 > 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
                }

                //双活三
                if (number_alive_3 > 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 5000;
                }

                //眠三活三
                String Fdie_3 = '0' + '0' + AI + AI + AI + player + "";
                String Sdie_3 = '0' + AI + '0' + AI + AI + player + "";
                String Tdie_3 = '0' + AI + AI + '0' + AI + player + "";
                String FOdie_3 = AI + '0' + '0' + AI + AI + "";
                String fidie_3 = AI + '0' + AI + '0' + AI + "";
                String Sidie_3 = player + '0' + AI + AI + AI + '0' + player + "";
                int number_die_3 = 0;//记录眠三的数量
                //横向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    code = board.chessboard[j][(int) target.get(i).getY()] + code;
                    Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
                }
                array = 4;
                for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                    array--;
                    code = code + board.chessboard[k][(int) target.get(i).getY()];
                    Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
                }
                if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                    number_die_3++;
                }
                if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                    number_die_3++;
                }
                if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                    number_die_3++;
                }
                //纵向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    code = code + board.chessboard[(int) target.get(i).getX()][j];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                    array--;
                    code = board.chessboard[(int) target.get(i).getX()][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                    number_die_3++;
                }
                if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                    number_die_3++;
                }
                if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                    number_die_3++;
                }
                //斜线方向\
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                    number_die_3++;
                }
                if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                    number_die_3++;
                }
                if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                    number_die_3++;
                }
                //反斜线方向/
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                    number_die_3++;
                }
                if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                    number_die_3++;
                }
                if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                    number_die_3++;
                }
                if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                    number_die_3++;
                }
                if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                    number_die_3++;
                }
                //眠三活三
                if (number_die_3 > 1 && number_alive_3 > 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 1000;
                }

                //双眠三
                if (number_die_3 > 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 500;
                }

                //眠四
                if (number_highdie_4 == 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 500;
                }

                //单活三
                if (number_alive_3 == 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100;
                }

                //跳活三
                int number_tiaoalive_3 = 0;
                //横向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    code = board.chessboard[j][(int) target.get(i).getY()] + code;
                    Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
                }
                array = 4;
                for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                    array--;
                    code = code + board.chessboard[k][(int) target.get(i).getY()];
                    Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_tiaoalive_3++;
                }
                if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                    number_tiaoalive_3++;
                }
                //纵向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    code = code + board.chessboard[(int) target.get(i).getX()][j];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                    array--;
                    code = board.chessboard[(int) target.get(i).getX()][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_tiaoalive_3++;
                }
                if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                    number_tiaoalive_3++;
                }
                //斜线方向\
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_tiaoalive_3++;
                }
                if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                    number_tiaoalive_3++;
                }
                //反斜线方向/
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                    number_tiaoalive_3++;
                }
                if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                    number_tiaoalive_3++;
                }
                //跳活三
                if (number_tiaoalive_3 >= 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 90;
                }

                //活二
                String Falive_2 = '0' + '0' + AI + AI + '0' + '0' + "";
                String Salive_2 = '0' + AI + '0' + AI + '0' + "";
                String Talive_2 = AI + '0' + '0' + AI + "";
                int number_alive_2 = 0;//记录活二的数量
                //横向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    code = board.chessboard[j][(int) target.get(i).getY()] + code;
                    Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
                }
                array = 4;
                for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                    array--;
                    code = code + board.chessboard[k][(int) target.get(i).getY()];
                    Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
                }
                if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                    number_alive_2++;
                }
                //纵向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    code = code + board.chessboard[(int) target.get(i).getX()][j];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                    array--;
                    code = board.chessboard[(int) target.get(i).getX()][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                    number_alive_2++;
                }
                //斜线方向\
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                    number_alive_2++;
                }
                //反斜线方向/
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                    number_alive_2++;
                }
                if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                    number_alive_2++;
                }
                //双活二
                if (number_alive_2 > 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 50;
                }
                //活二
                if (number_alive_2 == 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10;
                }
                //眠三
                if (number_die_3 == 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 5;
                }

                //眠二
                String Fdie_2 = '0' + '0' + '0' + AI + AI + player + "";
                String Sdie_2 = '0' + '0' + AI + '0' + AI + player + "";
                String Tdie_2 = '0' + AI + '0' + '0' + AI + player + "";
                String Fodie_2 = AI + '0' + '0' + '0' + AI + "";
                int number_die_2 = 0;//记录活二的数量
                //横向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                    array--;
                    code = board.chessboard[j][(int) target.get(i).getY()] + code;
                    Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
                }
                array = 4;
                for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                    array--;
                    code = code + board.chessboard[k][(int) target.get(i).getY()];
                    Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
                }
                if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                    number_die_2++;
                }
                //纵向
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                    array--;
                    code = code + board.chessboard[(int) target.get(i).getX()][j];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                    array--;
                    code = board.chessboard[(int) target.get(i).getX()][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                    number_die_2++;
                }
                //斜线方向\
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                    number_die_2++;
                }
                //反斜线方向/
                code = AI + "";
                Ncode = "";
                array = 4;
                for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                    array--;
                    code = code + board.chessboard[j][k];
                    Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
                }
                array = 4;
                for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                    array--;
                    code = board.chessboard[j][k] + code;
                    Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
                }
                if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                    number_die_2++;
                }
                if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                    number_die_2++;
                }
                if (number_die_2 >= 1) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 2;
                }

            }
        }
    public void InitSCore_2(Board board, char AI,char player) {
        //活五
        //向左
        int array = 4;
        for (int i = 0; i < target.size(); i++) {
            int count = 0;
            for (int j = (int) (target.get(i).getX() - 1); j >= 0 && array > 0; j--) {
                array--;
                if (board.chessboard[j][(int) target.get(i).getY()] == AI) {
                    count++;
                }
                if (count == 4) {
                    AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }

            //向右
            array = 4;
            count = 0;
            for (int j = (int) target.get(i).getX() + 1; j < row && array > 0; j++) {
                array--;
                if (board.chessboard[j][(int) target.get(i).getY()] == AI) {
                    count++;
                }
                if (count == 4) {
                    playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }
            //向上
            array = 4;
            count = 0;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                if (board.chessboard[(int) target.get(i).getX()][j] == AI) {
                    count++;
                }
                if (count == 4) {
                    playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }
            //向下
            array = 4;
            count = 0;
            for (int j = (int) target.get(i).getY() - 1; j >= 0 && array > 0; j--) {
                array--;
                if (board.chessboard[(int) target.get(i).getX()][j] == AI) {
                    count++;
                }
                if (count == 4) {
                    playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }

            //左斜上
            array = 4;
            count = 0;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                if (board.chessboard[j][k] == AI) {
                    count++;
                }
                if (count == 4) {
                    playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }
            //左斜下
            array = 4;
            count = 0;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                if (board.chessboard[j][k] == AI) {
                    count++;
                }
                if (count == 4) {
                    playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }
            //右斜上
            array = 4;
            count = 0;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                if (board.chessboard[j][k] == AI) {
                    count++;
                }
                if (count == 4) {
                    playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }
            //右斜下
            array = 4;
            count = 0;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                if (board.chessboard[j][k] == AI) {
                    count++;
                }
                if (count == 4) {
                    playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100000;
                }
            }

            String code = AI + "";
            //活四
            String alive_4 = '0' + AI + AI + AI + AI + '0' + "";
            String Ncode = "";
            //横向
            array = 4;
            for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                array--;
                code = board.chessboard[j][(int) target.get(i).getY()] + code;
                Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
            }
            array = 4;
            for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                array--;
                code = code + board.chessboard[k][(int) target.get(i).getY()];
                Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
            }
            if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
            }
            //纵向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                code = code + board.chessboard[(int) target.get(i).getX()][j];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                array--;
                code = board.chessboard[(int) target.get(i).getX()][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
            }
            //斜线方向\
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
            }
            //反斜线方向/
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(alive_4) && Ncode.contains(alive_4)) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
            }

            //双眠四
            String Fhighdie_4 = '0' + AI + AI + AI + AI + player + "";
            String Shighdie_4 = AI + '0' + AI + AI + AI + "";
            String Thighdie_4 = AI + AI + '0' + AI + AI + "";
            String FOhighdie_4 = AI + AI + AI + '0' + AI + "";
            String fihighdie_4 = player + AI + AI + AI + AI + '0' + "";
            int number_highdie_4 = 0;//记录有几个眠四
            //横向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                array--;
                code = board.chessboard[j][(int) target.get(i).getY()] + code;
                Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
            }
            array = 4;
            for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                array--;
                code = code + board.chessboard[k][(int) target.get(i).getY()];
                Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
            }
            if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                number_highdie_4++;
            }
            //纵向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                code = code + board.chessboard[(int) target.get(i).getX()][j];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                array--;
                code = board.chessboard[(int) target.get(i).getX()][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                number_highdie_4++;
            }
            //斜线方向\
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                number_highdie_4++;
            }
            //反斜线方向/
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fhighdie_4) && Ncode.contains(Fhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Shighdie_4) && Ncode.contains(Shighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(Thighdie_4) && Ncode.contains(Thighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(FOhighdie_4) && Ncode.contains(FOhighdie_4)) {
                number_highdie_4++;
            }
            if (code.contains(fihighdie_4) && Ncode.contains(fihighdie_4)) {
                number_highdie_4++;
            }
            if (number_highdie_4 > 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
            }

            //highdie_4&alive_3
            int number_alive_3 = 0;//记录活三的数量
            String Falive_3 = '0' + AI + AI + AI + '0' + "";
            String Salive_3 = AI + '0' + AI + AI + "";//跳活三
            String Talive_3 = AI + AI + '0' + AI + "";//跳活三
            //横向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                array--;
                code = board.chessboard[j][(int) target.get(i).getY()] + code;
                Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
            }
            array = 4;
            for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                array--;
                code = code + board.chessboard[k][(int) target.get(i).getY()];
                Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
            }
            if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                number_alive_3++;
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_alive_3++;
            }
            if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                number_alive_3++;
            }
            //纵向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                code = code + board.chessboard[(int) target.get(i).getX()][j];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                array--;
                code = board.chessboard[(int) target.get(i).getX()][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                number_alive_3++;
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_alive_3++;
            }
            if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                number_alive_3++;
            }
            //斜线方向\
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                number_alive_3++;
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_alive_3++;
            }
            if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                number_alive_3++;
            }
            //反斜线方向/
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                number_alive_3++;
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_alive_3++;
            }
            if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                number_alive_3++;
            }
            //眠四活三
            if (number_alive_3 > 1 && number_highdie_4 > 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10000;
            }

            //双活三
            if (number_alive_3 > 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 5000;
            }

            //眠三活三
            String Fdie_3 = '0' + '0' + AI + AI + AI + player + "";
            String Sdie_3 = '0' + AI + '0' + AI + AI + player + "";
            String Tdie_3 = '0' + AI + AI + '0' + AI + player + "";
            String FOdie_3 = AI + '0' + '0' + AI + AI + "";
            String fidie_3 = AI + '0' + AI + '0' + AI + "";
            String Sidie_3 = player + '0' + AI + AI + AI + '0' + player + "";
            int number_die_3 = 0;//记录眠三的数量
            //横向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                array--;
                code = board.chessboard[j][(int) target.get(i).getY()] + code;
                Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
            }
            array = 4;
            for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                array--;
                code = code + board.chessboard[k][(int) target.get(i).getY()];
                Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
            }
            if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                number_die_3++;
            }
            if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                number_die_3++;
            }
            if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                number_die_3++;
            }
            if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                number_die_3++;
            }
            if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                number_die_3++;
            }
            if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                number_die_3++;
            }
            //纵向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                code = code + board.chessboard[(int) target.get(i).getX()][j];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                array--;
                code = board.chessboard[(int) target.get(i).getX()][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                number_die_3++;
            }
            if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                number_die_3++;
            }
            if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                number_die_3++;
            }
            if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                number_die_3++;
            }
            if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                number_die_3++;
            }
            if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                number_die_3++;
            }
            //斜线方向\
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                number_die_3++;
            }
            if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                number_die_3++;
            }
            if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                number_die_3++;
            }
            if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                number_die_3++;
            }
            if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                number_die_3++;
            }
            if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                number_die_3++;
            }
            //反斜线方向/
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fdie_3) && Ncode.contains(Fdie_3)) {
                number_die_3++;
            }
            if (code.contains(Sdie_3) && Ncode.contains(Sdie_3)) {
                number_die_3++;
            }
            if (code.contains(Tdie_3) && Ncode.contains(Tdie_3)) {
                number_die_3++;
            }
            if (code.contains(FOdie_3) && Ncode.contains(FOdie_3)) {
                number_die_3++;
            }
            if (code.contains(fidie_3) && Ncode.contains(fidie_3)) {
                number_die_3++;
            }
            if (code.contains(Sidie_3) && Ncode.contains(Sidie_3)) {
                number_die_3++;
            }
            //眠三活三
            if (number_die_3 > 1 && number_alive_3 > 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 1000;
            }

            //双眠三
            if (number_die_3 > 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 500;
            }

            //眠四
            if (number_highdie_4 == 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 500;
            }

            //单活三
            if (number_alive_3 == 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 100;
            }

            //跳活三
            int number_tiaoalive_3 = 0;
            //横向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                array--;
                code = board.chessboard[j][(int) target.get(i).getY()] + code;
                Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
            }
            array = 4;
            for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                array--;
                code = code + board.chessboard[k][(int) target.get(i).getY()];
                Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_tiaoalive_3++;
            }
            if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                number_tiaoalive_3++;
            }
            //纵向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                code = code + board.chessboard[(int) target.get(i).getX()][j];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                array--;
                code = board.chessboard[(int) target.get(i).getX()][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_tiaoalive_3++;
            }
            if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                number_tiaoalive_3++;
            }
            //斜线方向\
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_tiaoalive_3++;
            }
            if (code.contains(Talive_3) && Ncode.contains(Talive_3)) {
                number_tiaoalive_3++;
            }
            //反斜线方向/
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Falive_3) && Ncode.contains(Falive_3)) {
                number_tiaoalive_3++;
            }
            if (code.contains(Salive_3) && Ncode.contains(Salive_3)) {
                number_tiaoalive_3++;
            }
            //跳活三
            if (number_tiaoalive_3 >= 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 90;
            }

            //活二
            String Falive_2 = '0' + '0' + AI + AI + '0' + '0' + "";
            String Salive_2 = '0' + AI + '0' + AI + '0' + "";
            String Talive_2 = AI + '0' + '0' + AI + "";
            int number_alive_2 = 0;//记录活二的数量
            //横向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                array--;
                code = board.chessboard[j][(int) target.get(i).getY()] + code;
                Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
            }
            array = 4;
            for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                array--;
                code = code + board.chessboard[k][(int) target.get(i).getY()];
                Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
            }
            if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                number_alive_2++;
            }
            if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                number_alive_2++;
            }
            if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                number_alive_2++;
            }
            //纵向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                code = code + board.chessboard[(int) target.get(i).getX()][j];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                array--;
                code = board.chessboard[(int) target.get(i).getX()][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                number_alive_2++;
            }
            if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                number_alive_2++;
            }
            if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                number_alive_2++;
            }
            //斜线方向\
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                number_alive_2++;
            }
            if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                number_alive_2++;
            }
            if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                number_alive_2++;
            }
            //反斜线方向/
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Falive_2) && Ncode.contains(Falive_2)) {
                number_alive_2++;
            }
            if (code.contains(Salive_2) && Ncode.contains(Salive_2)) {
                number_alive_2++;
            }
            if (code.contains(Talive_2) && Ncode.contains(Talive_2)) {
                number_alive_2++;
            }
            //双活二
            if (number_alive_2 > 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 50;
            }
            //活二
            if (number_alive_2 == 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 10;
            }
            //眠三
            if (number_die_3 == 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 5;
            }

            //眠二
            String Fdie_2 = '0' + '0' + '0' + AI + AI + player + "";
            String Sdie_2 = '0' + '0' + AI + '0' + AI + player + "";
            String Tdie_2 = '0' + AI + '0' + '0' + AI + player + "";
            String Fodie_2 = AI + '0' + '0' + '0' + AI + "";
            int number_die_2 = 0;//记录活二的数量
            //横向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1; j >= 0 && array > 0; j--) {
                array--;
                code = board.chessboard[j][(int) target.get(i).getY()] + code;
                Ncode = board.chessboard[j][(int) target.get(i).getY()] + Ncode;
            }
            array = 4;
            for (int k = (int) target.get(i).getX() + 1; k < column && array > 0; k++) {
                array--;
                code = code + board.chessboard[k][(int) target.get(i).getY()];
                Ncode = Ncode + board.chessboard[k][(int) target.get(i).getY()];
            }
            if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                number_die_2++;
            }
            if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                number_die_2++;
            }
            if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                number_die_2++;
            }
            if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                number_die_2++;
            }
            //纵向
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getY() + 1; j < column && array > 0; j++) {
                array--;
                code = code + board.chessboard[(int) target.get(i).getX()][j];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int k = (int) target.get(i).getY() - 1; k >= 0; k--) {
                array--;
                code = board.chessboard[(int) target.get(i).getX()][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                number_die_2++;
            }
            if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                number_die_2++;
            }
            if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                number_die_2++;
            }
            if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                number_die_2++;
            }
            //斜线方向\
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() + 1; j >= 0 && k < column && array > 0; j--, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() - 1; j < row && k >= 0 && array > 0; j++, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                number_die_2++;
            }
            if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                number_die_2++;
            }
            if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                number_die_2++;
            }
            if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                number_die_2++;
            }
            //反斜线方向/
            code = AI + "";
            Ncode = "";
            array = 4;
            for (int j = (int) target.get(i).getX() + 1, k = (int) target.get(i).getY() + 1; j < row && k < column && array > 0; j++, k++) {
                array--;
                code = code + board.chessboard[j][k];
                Ncode = Ncode + board.chessboard[j][(int) target.get(i).getY()];
            }
            array = 4;
            for (int j = (int) target.get(i).getX() - 1, k = (int) target.get(i).getY() - 1; j >= 0 && k >= 0 && array > 0; j--, k--) {
                array--;
                code = board.chessboard[j][k] + code;
                Ncode = board.chessboard[k][(int) target.get(i).getY()] + Ncode;
            }
            if (code.contains(Fdie_2) && Ncode.contains(Fdie_2)) {
                number_die_2++;
            }
            if (code.contains(Sdie_2) && Ncode.contains(Sdie_2)) {
                number_die_2++;
            }
            if (code.contains(Tdie_2) && Ncode.contains(Tdie_2)) {
                number_die_2++;
            }
            if (code.contains(Fodie_2) && Ncode.contains(Fodie_2)) {
                number_die_2++;
            }
            if (number_die_2 >= 1) {
                playerscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 2;
            }

        }
    }


    //找到所有点中得分最大的点，并返回该点
    public Point Max(){
        int min=0;
        Point a = new Point(0,0);
        for(int i=0;i<target.size();i++){
            AIscore[(int) target.get(i).getX()][(int) target.get(i).getY()] += 1;
        }
        for(int i=0;i<column;i++){
            for(int j=0;j<row;j++){
                AIscore[i][j]-=playerscore[i][j];
            }
        }
        target.clear();
        for(int i=0;i<column;i++) {
            for(int j=0;j<row;j++) {
                //System.out.print(AIscore[i][j]+"  ");
                if(AIscore[i][j]>min){
                    min=AIscore[i][j];
                    a=new Point(i,j);
                }
            }
           // System.out.println();
        }
        for(int i=column-1;i>=0;i--) {
            for(int j=0;j<row;j++) {
                System.out.print(AIscore[i][j]+"  ");
                AIscore[i][j]=0;
                playerscore[i][j]=0;
                }
            System.out.println();

        }

        return a;
    }
}
