package lab1;

import java.util.*;

public class Graph {
    private Vector<Word> wordList;

    public Graph() {
        wordList = new Vector<Word>();
    }

    public Vector<Word> getWordList() {// ����ͼ�����нڵ�ļ���
        return wordList;
    }

    public int getWordIndex(String wd) {// ���ص�����wordList�е�λ��
        int i, rt = -1;
        String x;
        for (i = 0; i < wordList.size(); i++) {
            x = wordList.get(i).getname();
            if (x.equalsIgnoreCase(wd)) {
                rt = i;
                break;
            }
        }
        return rt;
    }

    public void createGraph(String firstWord, String secondWord) {
        int sign = 0;
        int i;
        for (i = 0; i < wordList.size(); i++) {
            if (wordList.elementAt(i).getname().equals(firstWord)) { // �жϴ˵����Ƿ���ֹ�
                sign = 1;
                break;
            }
        }
        if (sign == 1) {
            wordList.elementAt(i).searchLink(secondWord);
        } // �ж�ͼ�ı��Ƿ���ֹ�
        else { // �µ���
            Word newWord = new Word(firstWord);
            wordList.addElement(newWord);
            newWord.addLinkList(secondWord);
        }
    }

    public void createGraph(String lastWord) {
        int sign = 0;
        int i;
        for (i = 0; i < wordList.size(); i++) {
            if (wordList.elementAt(i).getname().equals(lastWord)) { // �жϴ˵����Ƿ���ֹ�
                sign = 1;
                break;
            }
        }
        if (sign != 1)
        {
//            ; // �ж�ͼ�ı��Ƿ���ֹ�
//        else { // �µ���
            Word newWorld = new Word(lastWord);
            wordList.addElement(newWorld);
        }
    }

    /*
     * public void showGraph(){ for(int counter=0;counter<
     * wordList.size();counter++){ wordList.elementAt(counter).show(); } }
     */

    public String getCommand() { // ����graphzip���
        String s;
        StringBuffer buf = new StringBuffer();
        buf.append("digraph abc{");
        for (int i = 0; i < wordList.size(); i++) {
            buf.append(wordList.elementAt(i).getname() + ";\n");
        }
        for (int i = 0; i < wordList.size(); i++) {
            buf.append(wordList.elementAt(i).graphInf());
        }
        buf.append("}");
        s = buf.toString();
        return s;
    }

    public int getWordNum() {
        return wordList.size();
    }
    static final int  maxNum=100000;
    public int[][] getMatrix() {// ��������ͼ���ڽӾ���

        int i, j;
        int sign;
        int graphMatrix[][] = new int[wordList.size()][wordList.size()];
        for (i = 0; i < wordList.size(); i++) {
            for (j = 0; j < wordList.size(); j++) {
                sign = wordList.get(i).judgeOfWord(wordList.get(j).getname());
                if (sign == -1) {
                    graphMatrix[i][j] = maxNum;
                } else {
                    graphMatrix[i][j] = wordList.get(i).getWeight(sign);
                }
            }
        }
        return graphMatrix;
    }

    public void changeEdgeColor(int orderedNum, int edgeNum, int colorNum) {
        Word changedWord = wordList.get(orderedNum); // ȷ��������ɫ�ĵ���
        int edgeIndex = changedWord.indexOfEdge(wordList.get(edgeNum).getname());
        changedWord.changecolor(edgeIndex, colorNum);
    }

    public void colorClean() {
        for (int i = 0; i < wordList.size(); i++)
            wordList.get(i).colorCleaned();
    }

    public String indexofVector(int num) {
        return wordList.elementAt(num).getname();
    }

    // ��ѯ�ŽӴ�
    public boolean queryExist(String wd) {// ��ѯһ�������Ƿ����������ͼ��
        int i;
        for (i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getname().equalsIgnoreCase(wd)) {
                return true;
            }
        }
        return false;
    }

    public Word findWord(String wd) {// ����һ���������ڵĽڵ�word����
        int loopvariable;
        Word temp = new Word();
        for (loopvariable = 0; loopvariable < this.wordList.size(); loopvariable++) {
            temp = this.wordList.get(loopvariable);
            if (temp.getname().equalsIgnoreCase(wd))
                break;
        }
        return temp;
    }

}
