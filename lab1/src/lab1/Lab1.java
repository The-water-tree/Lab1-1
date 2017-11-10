package lab1;  

import java.util.*;  
import java.io.*;  
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.imageio.*;  



public class Lab1 extends JFrame {
    public Graph G = new Graph();  
    JLabel label1, label2;  
    JTextArea textArea1;  
    JTextField textField1, textField2, textField3;  
    Box vertical1, horizontal1;  
    JButton button1;  
    Container c = getContentPane();  
    JScrollPane JS, JS1;  

    public static void main(String args[]) {

        Lab1 application = new Lab1();  
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        /*
         * String filename="file1.txt";   lab1 test=new lab1();   graph
         * G=test.createDirectedGraph(filename);   //���ļ���ͼ test.showDirectedGraph(G);  
         * //���ӻ� if(G.getWordNum()>0){ test.randomWalk(G);   test.queryBridgeWords(G,
         * "to", "strange");   }
         */
    }

    private class ButtonHandler1 implements ActionListener { // ���ŽӴ�
        public void actionPerformed(ActionEvent event) {
            String string1, string2;  
            string1 = textField1.getText();  
            string2 = textField2.getText();  
            textField3.setText(queryBridgeWords(G, string1, string2));  
            string1 = textField1.getText();  
        }
    }

    private class ButtonHandler2 implements ActionListener { // �����ı�
        public void actionPerformed(ActionEvent event) {
            String string1;  
            string1 = textField1.getText();  
            textField3.setText(generateNewText(G, string1));  

        }
    }

    private class ButtonHandler3 implements ActionListener { // ���·��
        public void actionPerformed(ActionEvent event) {

            String string1, string2;  
            string1 = textField1.getText();  
            string2 = textField2.getText();  
            textField3.setText(calcShortestPath(G, string1, string2));  

        }
    }

    private class ButtonHandler4 implements ActionListener { // ��Դ
        public void actionPerformed(ActionEvent event) {

            textArea1.setFont(new Font("����", Font.PLAIN, 20));  
            String string1;  
            string1 = textField1.getText();  

            textArea1.setText(singleSource(G, string1));  
            G.colorClean();  
            showDirectedGraph(G);  
            Icon bug1 = new ImageIcon("file4.jpg");  
            label1.setIcon(bug1);  

        }
    }

    private class ButtonHandler5 implements ActionListener { // ����
        public void actionPerformed(ActionEvent event) {

            textField3.setText(randomWalk(G));  

        }
    }

    // GUI
    public Lab1() {

        super("ʵ��1");  

        // �����˵��������ڴ�����

        JMenuBar bar = new JMenuBar();  
        setJMenuBar(bar);  
        vertical1 = Box.createVerticalBox();  
        horizontal1 = Box.createHorizontalBox();  

        // �����˵�File

        JMenu fileMenu = new JMenu("File");  
        JMenuItem importItem = new JMenuItem("Import File");  
        fileMenu.add(importItem);  
        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                FileDialog fd = new FileDialog(new Frame(), "ѡ������ͼ���ļ�", FileDialog.LOAD);  
                FilenameFilter ff = new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        if (name.endsWith("txt")) {
                            G = createDirectedGraph(name);  
                            showDirectedGraph(G);  
                            return true;  
                        } else
                            return false;  
                    }
                };  
                fd.setFilenameFilter(ff);  
                fd.setVisible(true);  
            }
        });  
        bar.add(fileMenu);  
        // �����˵�Function
        String functionNames[] = { "չʾ����ͼ", "��ѯ�ŽӴ�", "����bridge�������ı�", "�����������ʵ����·��", "һ�����ʵ��������ʵ����·��", "�������" };  
        JMenuItem[] functionItems = new JMenuItem[functionNames.length];  
        JMenu functionMenu = new JMenu("Function");  
        for (int count = 0;   count < functionItems.length;   count++) {
            functionItems[count] = new JMenuItem(functionNames[count]);  
            functionMenu.add(functionItems[count]);  
        }

        functionItems[0].addActionListener( // չʾ����ͼ
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        vertical1.removeAll();  
                        horizontal1.removeAll();  
                        showDirectedGraph(G);  

                        Icon bug = new ImageIcon("file4.jpg");  

                        label1 = new JLabel("Directed Grapg", bug, SwingConstants.CENTER);  
                        label1.setHorizontalTextPosition(SwingConstants.CENTER);  
                        label1.setVerticalTextPosition(SwingConstants.BOTTOM);  
                        JScrollPane JS = new JScrollPane(label1);  

                        vertical1.add(JS);  
                        c.add(vertical1);  

                        setVisible(true);  
                    }
                });  

        functionItems[1].addActionListener( // ��ѯ�ŽӴ�
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        vertical1.removeAll();  
                        horizontal1.removeAll();  

                        showDirectedGraph(G);  

                        Icon bug = new ImageIcon("file4.jpg");  
                        label1 = new JLabel("Directed Grapg", bug, SwingConstants.CENTER);  
                        label1.setHorizontalTextPosition(SwingConstants.CENTER);  
                        label1.setVerticalTextPosition(SwingConstants.BOTTOM);  
                        JScrollPane JS = new JScrollPane(label1);  
                        vertical1.add(JS);  

                        textField3 = new JTextField("���", 10);  
                        label2 = new JLabel("��ѯ�ŽӴ�:", SwingConstants.CENTER);  
                        label2.setFont(new Font("����", Font.PLAIN, 20));  
                        vertical1.add(label2);  
                        horizontal1 = Box.createHorizontalBox();  
                        textField1 = new JTextField("�������һ������", 10);  
                        horizontal1.add(textField1);  
                        textField2 = new JTextField("������ڶ�������", 10);  
                        horizontal1.add(textField2);  
                        button1 = new JButton("��ѯ");  
                        horizontal1.add(button1);  
                        ButtonHandler1 handler = new ButtonHandler1();  
                        button1.addActionListener(handler);  

                        vertical1.add(horizontal1);  
                        vertical1.add(textField3);  
                        setVisible(true);  
                    }
                });  

        functionItems[2].addActionListener( // ����bridge word�������ı�
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        horizontal1.removeAll();  
                        vertical1.removeAll();  

                        showDirectedGraph(G);  
                        Icon bug = new ImageIcon("file4.jpg");  
                        label1 = new JLabel("Directed Grapg", bug, SwingConstants.CENTER);  
                        label1.setHorizontalTextPosition(SwingConstants.CENTER);  
                        label1.setVerticalTextPosition(SwingConstants.BOTTOM);  
                        JScrollPane JS = new JScrollPane(label1);  
                        vertical1.add(JS);  

                        textField3 = new JTextField("���", 10);  
                        label2 = new JLabel("�����ı�:", SwingConstants.CENTER);  
                        label2.setFont(new Font("����", Font.PLAIN, 20));  
                        vertical1.add(label2);  
                        horizontal1 = Box.createHorizontalBox();  
                        textField1 = new JTextField("�������һ������", 10);  
                        horizontal1.add(textField1);  

                        button1 = new JButton("����ŽӴ�");  
                        horizontal1.add(button1);  
                        ButtonHandler2 handler = new ButtonHandler2();  
                        button1.addActionListener(handler);  

                        vertical1.add(horizontal1);  
                        vertical1.add(textField3);  
                        setVisible(true);  
                    }
                });  

        functionItems[3].addActionListener( // �����������ʵ����·��
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        horizontal1.removeAll();  
                        vertical1.removeAll();  

                        showDirectedGraph(G);  
                        Icon bug = new ImageIcon("file4.jpg");  
                        label1 = new JLabel("Directed Grapg", bug, SwingConstants.CENTER);  
                        label1.setHorizontalTextPosition(SwingConstants.CENTER);  
                        label1.setVerticalTextPosition(SwingConstants.BOTTOM);  
                        JScrollPane JS = new JScrollPane(label1);  
                        vertical1.add(JS);  

                        textField3 = new JTextField("���", 10);  
                        label2 = new JLabel("�����������ʵ����·��", SwingConstants.CENTER);  
                        label2.setFont(new Font("����", Font.PLAIN, 20));  
                        vertical1.add(label2);  
                        horizontal1 = Box.createHorizontalBox();  
                        textField1 = new JTextField("�������һ������", 10);  
                        textField2 = new JTextField("������ڶ�������", 10);  
                        horizontal1.add(textField1);  
                        horizontal1.add(textField2);  
                        button1 = new JButton("��ѯ");  
                        horizontal1.add(button1);  
                        ButtonHandler3 handler = new ButtonHandler3();  
                        button1.addActionListener(handler);  

                        vertical1.add(horizontal1);  
                        vertical1.add(textField3);  
                        setVisible(true);  
                    }
                });  

        functionItems[4].addActionListener( // һ�����ʵ��������ʵ����·��
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        horizontal1.removeAll();  
                        vertical1.removeAll();  
                        Icon bug = new ImageIcon("file4.jpg");  
                        label1 = new JLabel("Directed Grapg", bug, SwingConstants.CENTER);  
                        label1.setHorizontalTextPosition(SwingConstants.CENTER);  
                        label1.setVerticalTextPosition(SwingConstants.BOTTOM);  

                        textArea1 = new JTextArea("���");  
                        label2 = new JLabel("һ�����ʵ��������ʵ����·��", SwingConstants.CENTER);  
                        label2.setFont(new Font("����", Font.PLAIN, 20));  

                        horizontal1 = Box.createHorizontalBox();  
                        textField1 = new JTextField("�����뵥��", 10);  

                        JScrollPane JS = new JScrollPane(label1);  
                        JScrollPane JS1 = new JScrollPane(textArea1);  

                        horizontal1.add(JS);  
                        horizontal1.add(JS1);  
                        vertical1.add(horizontal1);  
                        vertical1.add(label2);  
                        vertical1.add(textField1);  
                        button1 = new JButton("��ѯ");  
                        vertical1.add(button1);  
                        ButtonHandler4 handler = new ButtonHandler4();  
                        button1.addActionListener(handler);  

                        setVisible(true);  
                    }
                });  

        functionItems[5].addActionListener( // �������
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        vertical1.removeAll();  
                        horizontal1.removeAll();  

                        Icon bug = new ImageIcon("file4.jpg");  
                        label1 = new JLabel("Directed Grapg", bug, SwingConstants.CENTER);  
                        label1.setHorizontalTextPosition(SwingConstants.CENTER);  
                        label1.setVerticalTextPosition(SwingConstants.BOTTOM);  
                        JScrollPane JS = new JScrollPane(label1);  

                        label2 = new JLabel("�������", SwingConstants.CENTER);  
                        label2.setFont(new Font("����", Font.PLAIN, 20));  
                        vertical1.add(JS);  
                        vertical1.add(label2);  
                        horizontal1 = Box.createHorizontalBox();  

                        button1 = new JButton("��ʼ����");  
                        horizontal1.add(button1);  
                        ButtonHandler5 handler = new ButtonHandler5();  
                        button1.addActionListener(handler);  

                        vertical1.add(horizontal1);  
                        vertical1.add(textField3);  

                        setVisible(true);  
                    }
                });  

        bar.add(functionMenu);  

        setSize(500, 150);  
        setVisible(true);  

    }

    public Graph createDirectedGraph(String filename) {// ��ͼ
        G = new Graph();  

        Vector buffer = new Vector();   // ��ȡ�ļ�
        String[] stringBuffer = null;  
  
        try {
            FileInputStream fis = new FileInputStream(filename);  
            InputStreamReader dis = new InputStreamReader(fis, "UTF-8");  
            BufferedReader reader = new BufferedReader(dis);  
            String s = new String();  
            while ((s = reader.readLine()) != null) // Ϊnullʱ���ļ�β
            {
                stringBuffer = s.split(" ");  
                for (int i = 0;   i < stringBuffer.length;   i++) {
                    stringBuffer[i] = stringBuffer[i].toLowerCase(Locale.US);  
                    if (stringBuffer[i].charAt(0) <= 122 && stringBuffer[i].charAt(0) >= 97) {
                        int lastIndex = stringBuffer[i].length() - 1;  
                        if (stringBuffer[i].charAt(lastIndex) <= 122 && stringBuffer[i].charAt(lastIndex) >= 97)
                            buffer.add(stringBuffer[i]);  
                        else
                            buffer.add(stringBuffer[i].substring(0, lastIndex));  
                    }

                }
            }

            dis.close();  
        } catch (IOException e) {
            System.out.println(e);  
        }
        Graph direcedGraph = new Graph();  
        int i;  
        for (i = 0;   i < buffer.size() - 1;   i++) {
            // System.out.print(buffer.elementAt(i).toString());  
            direcedGraph.createGraph(buffer.elementAt(i).toString(), buffer.elementAt(i + 1).toString());  
        }
        if (!buffer.isEmpty())
            direcedGraph.createGraph(buffer.elementAt(i).toString());  
        // direcedGraph.showGraph();  
        return direcedGraph;  
    }
    // ͼ�λ�չʾ����ͼ
    void showDirectedGraph(Graph G) { 
        try {
            File f1 = new File("file3.gv");  
            FileOutputStream fos = new FileOutputStream(f1);  

            String s = new String();   // �������������
            s = G.getCommand();  
            byte b[] = s.getBytes();  
            fos.write(b);  
            fos.close();

            String path = "dot -Tjpg \"" + System.getProperty("user.dir") + "\\file3.gv\" -o \"" + System.getProperty("user.dir") + "\\file4.jpg\" ";   // ����������

            Runtime run = Runtime.getRuntime();  
            try {

                Process process = run.exec("cmd.exe  /c " + path);  

                process.waitFor();  
            } catch (Exception e) {
                e.printStackTrace();  
            }

        } catch (FileNotFoundException e) {
            System.out.println("Stream1" + e);  
        } catch (IOException e) {
            System.err.println("Stream2" + e);  
        }
    }

    static final int maxNum = 100000;
    public String randomWalk(Graph G) { // �������

        int[][] graphMatrix = G.getMatrix();  

        Random rand = new Random();  

        int startNum = (rand.nextInt()) % (G.getWordNum());   // ��ʼ���
        while (startNum < 0)
            startNum = (rand.nextInt()) % (G.getWordNum());  
        Vector<Integer> walkOrder = new Vector<Integer>();  
        walkOrder.add(startNum);  
        int currentNum = startNum, nextNum = 0;  
        String randomPath = new String();  

        while (true) {
            Vector<Integer> availiableNode = new Vector<Integer>();  
            boolean sign = false;  
            nextNum = 0;  
            while (nextNum < G.getWordNum()) {
                if (graphMatrix[currentNum][nextNum] != maxNum) {
                    sign = true;  

                    availiableNode.add(nextNum);  
                }
                nextNum++;  
            }
            if (sign == false)
                break;  
            else {
                int temp = rand.nextInt();  
                while (temp < 0)
                    temp = rand.nextInt();  
                temp = temp % availiableNode.size();  
                int randomNum = availiableNode.elementAt(temp);  

                graphMatrix[currentNum][randomNum] = maxNum;  
                currentNum = randomNum;  
                walkOrder.add(currentNum);  
            }
        }
        int i = 0;  

        for (i = 0;   i < walkOrder.size() - 1;   i++) {
            G.changeEdgeColor(walkOrder.elementAt(i), walkOrder.elementAt(i + 1), 1);  

            randomPath += G.indexofVector(walkOrder.elementAt(i)) + " ";  

        }
        randomPath += G.indexofVector(walkOrder.elementAt(i)) + " \n";  

        showDirectedGraph(G);  

        String path = "file4.jpg";  

        Icon icon;  
        try {
            icon = new ImageIcon(ImageIO.read(new File(path)));  
            label1.setIcon(icon);  
        } catch (IOException e) {
            e.printStackTrace();  
        }

        G.colorClean();  

        return randomPath;  
    }

    public String queryBridgeWords(Graph G, String word1, String word2) {// ��ѯ�ŽӴ�
        Vector<String> bridgeWords = new Vector<String>();
        Word w1 = new Word();  
        String result = new String();  
        int i;  
        if (G.queryExist(word1) && G.queryExist(word2)) {
            w1 = G.findWord(word1);  
            bridgeWords = w1.findBridgeWords(G, word2);  
            if (bridgeWords.size() == 0) {
                result = "No bridge words from \"" + word1 + "\" to \"" + word2+ "\"!";  
            } else {
                result = "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: ";  
                for (i = 0;   i < bridgeWords.size();   i++) {
                    result += bridgeWords.get(i) + " ";  
                }
          
            }
        } else {
            result = "No \""+word1+"\" or \""+word2+"\" in the graph!";  
        }
        return result;  

    }

    public String generateNewText(Graph G, String inputText) {// ����bridge word�������ı�
        Vector<String> input = new Vector<String>();  
        int i = 0;  
        String[] temp0;  
        temp0 = inputText.split(" ");  
        for (i = 0;   i < temp0.length;   i++) {
            temp0[i] = temp0[i].toLowerCase();  
            input.addElement(temp0[i]);  
        }
        Random rand = new Random(25);  
        int ran;   // ���ѡ�ʲ���
        int inputSize = input.size();  
        Word formerWord = new Word();  
        StringBuffer buffer = new StringBuffer();  
        String laterWord, output;  
        Vector<String> temp = new Vector<String>();  
        i = 0;  
        while (i < inputSize - 1) {
            formerWord = G.findWord(input.get(i));  
            laterWord = input.get(i + 1);  
            temp = formerWord.findBridgeWords(G, laterWord);  
            if (temp.size() == 0) {
                i++;  
            } else {
                if (temp.size() == 1) {
                    input.insertElementAt(temp.get(0), i + 1);  
                    inputSize += 1;  
                    i = i + 2;  
                } else {
                    ran = (rand.nextInt(100)) % (temp.size());  
                    input.insertElementAt(temp.get(ran), i + 1);  
                    inputSize += 1;  
                    i = i + 2;  
                }
            }
        }
        for (i = 0;   i < inputSize;   i++) {
            buffer.append(input.get(i));  
            buffer.append(' ');  
        }
        output = buffer.toString();  
        return output;  
    }

    public int findMinCost(int indexSource, int currentShortestLength[], boolean flag[]) {
        int x = -1;
        int min = -1;  
        int i;  
        for (i = 0;   i < currentShortestLength.length;   i++) {
            if (flag[i] == false) {
                min = currentShortestLength[i];  
                x = i;  
                break;  
            }
        }
        for (i = 0;   i < currentShortestLength.length;   i++) {
            if ((flag[i] == false) && (currentShortestLength[i] < min)) {
                min = currentShortestLength[i];  
                x = i;  
            }
        }
        return x;  
    }

    boolean repaint = true;
    public String singleSource(Graph G, String sourceWord) {// ��Դ���������е��ʵ����·��
        StringBuffer buffer = new StringBuffer();  
        String temp;  
        int i;  
        repaint = false;
        for (i = 0;   i < G.getWordList().size();   i++) {
            buffer.append(sourceWord);  
            buffer.append("��");  
            buffer.append(G.getWordList().get(i).getname());  
            buffer.append("�����·����\n");
            if (G.queryExist(sourceWord) == false) {
                repaint = true;
                return "can't find " + sourceWord + " in the graph!";  
            }

            if (i + 1 == G.getWordList().size())
                repaint = true;
            temp = calcShortestPath(G, sourceWord, G.getWordList().get(i).getname());  

            buffer.append(temp);  
        }
        repaint = true;
        temp = buffer.toString();  
        return temp;
    }

    public String calcShortestPath(Graph G, String word1, String word2) {// ������������֮������·��
        final int maxNum = 100000;  
        Vector<Word> wordList = new Vector<Word>();  
        wordList = G.getWordList();  
        int i, j, sum;  
        int indexSource, indexTail, indexMin;  
        indexSource = G.getWordIndex(word1);  
        indexTail = G.getWordIndex(word2);  
        int stack[][] = new int[2][10000];
        int top = -1;   // ��ӡ·������ջ
        int countColor = 1;   // ����·����ɫ�ļ���
        // Dijkstra�㷨ʵ�����·��Ѱ���������ݽṹ
        int graphArray[][] = new int[G.getWordNum()][G.getWordNum()];  
        int currentShortestLength[] = new int[wordList.size()];   // ��Դ�㵽��������ĵ�ǰ���·������
        int lastPassWord[][] = new int[wordList.size()][wordList.size()];   // ��Դ�㵽ÿ���������·������󾭹��ĵ�
        int countPath[] = new int[wordList.size()];   // ��¼ÿ��������Ϊ���·���л��ݵ�Ĵ���+1
        boolean flag[] = new boolean[wordList.size()];   // �ж�ÿ�����Ƿ��Ѿ��������·��
        // ��ʼ�����������ݽṹ
        graphArray = G.getMatrix();  
        for (i = 0;   i < wordList.size();   i++) // ��ʼ��currentShortestLength����
        {
            currentShortestLength[i] = graphArray[indexSource][i];  
        }
        for (i = 0;   i < wordList.size();   i++) {
            for (j = 0;   j < wordList.size();   j++) {
                lastPassWord[i][j] = indexSource;  
            }
        }
        for (i = 0;   i < wordList.size();   i++) {
            countPath[i] = 0;  
        }
        for (i = 0;   i < wordList.size();   i++) {
            flag[i] = false;  
        }
        // ��ʼ����ɣ���ʼ�㷨����
        int count;  
        for (i = 0;   i < wordList.size();   i++) {
            indexMin = findMinCost(indexSource, currentShortestLength, flag);  
            flag[indexMin] = true;  
            for (j = 0;   j < wordList.size();   j++) {
                if (flag[j] == false) {
                    sum = currentShortestLength[indexMin] + graphArray[indexMin][j];  
                    if (sum < currentShortestLength[j]) {
                        currentShortestLength[j] = sum;  
                        lastPassWord[0][j] = indexMin;  
                        countPath[j] = 0;  
                    } else if (sum == currentShortestLength[j]) {
                        count = countPath[j];  
                        lastPassWord[++count][j] = indexMin;  
                        countPath[j] = count;  
                    }
                }
            }
        }
        // ��ӡ���·��
        int t;  
        StringBuffer buffer = new StringBuffer();  
        String output = new String();  
        if (currentShortestLength[indexTail] < maxNum) {
            // �Ƚ�Ŀ�Ľڵ���ջ
            stack[0][++top] = indexTail;  
            stack[1][top] = 0;  
            // ֮�������ڵ���ջ
            i = lastPassWord[countPath[indexTail]][indexTail];  
            t = countPath[indexTail];  
            while (i != indexSource) {
                stack[0][++top] = i;  
                stack[1][top] = t;  
                t = countPath[i];  
                i = lastPassWord[countPath[i]][i];  
            }
            while (top != -1) {
                // �Ƚ�һ�����·����ȡ����
                t = top;  
                buffer.append(word1);  
                if (wordList.get(G.getWordIndex(word1)).getColor(wordList.get(stack[0][t]).getname()) == 0) {
                    G.changeEdgeColor(G.getWordIndex(word1), stack[0][t], ++countColor);  
                } else if (wordList.get(G.getWordIndex(word1)).getColor(wordList.get(stack[0][t]).getname()) > 1) {
                    G.changeEdgeColor(G.getWordIndex(word1), stack[0][t], 1);  
                }
                buffer.append("->");  
                while (t != 0) {
                    buffer.append(wordList.get(stack[0][t]).getname());  
                    if (wordList.get(G.getWordIndex(wordList.get(stack[0][t]).getname()))
                            .getColor(wordList.get(stack[0][t - 1]).getname()) == 0) {
                        G.changeEdgeColor(G.getWordIndex(wordList.get(stack[0][t]).getname()), stack[0][t - 1],
                                countColor);  
                    } else if (wordList.get(G.getWordIndex(wordList.get(stack[0][t]).getname()))
                            .getColor(wordList.get(stack[0][t - 1]).getname()) > 1) {
                        G.changeEdgeColor(G.getWordIndex(wordList.get(stack[0][t]).getname()), stack[0][t - 1], 1);  
                    }
                    buffer.append("->");  
                    t--;  
                }
                buffer.append(word2);  
                buffer.append("\n");  
                // ֮����Ѱ�ҵڶ���·�����ȵ�ջ�ٽ�ջ��
                while (stack[1][top] == 0) {
                    top--;  
                    if (top == -1) {
                        break;  
                    }
                }
                if (top == -1) {
                    break;  
                }
                i = lastPassWord[stack[1][top] - 1][stack[0][top - 1]];  
                t = stack[1][top] - 1;  
                top--;  
                while (i != indexSource) {
                    stack[0][++top] = i;  
                    stack[1][top] = t;  
                    i = lastPassWord[countPath[i]][i];  
                    t = countPath[i];  
                }
            }
            output = buffer.toString();  

            if (repaint) {
                showDirectedGraph(G);

                String path = "file4.jpg";  
    
                Icon icon;  
                try {
                    icon = new ImageIcon(ImageIO.read(new File(path)));  
                    label1.setIcon(icon);  
                } catch (IOException e) {
                    e.printStackTrace();  
                }
            }

            G.colorClean();  

        } else {
            output = word1 + "��" + word2 + "֮����·����";  
        }

        return output;  
    }

}
