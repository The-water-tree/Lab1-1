package lab1;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
   

import java.io.FileOutputStream; 

public class lab1 extends JFrame{
	private graph G=new graph();
	JLabel label1,label2;
	JTextArea textArea1;
	JTextField textField1,textField2,textField3;
	Box Vertical1,Horizontal1;
	JButton button1;
	Container c=getContentPane();
	JScrollPane JS,JS1;
	
	public static void main(String args[]){
		
		lab1 application = new lab1();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*String filename="file1.txt";
		lab1 test=new lab1();
		graph G=test.createDirectedGraph(filename);  //读文件建图
		test.showDirectedGraph(G); 				//可视化
		if(G.getWordNum()>0){
		test.randomWalk(G);
		test.queryBridgeWords(G, "to", "strange");
		}*/
	}
	
	private class ButtonHandler1 implements ActionListener{ //查桥接词 
		public void actionPerformed(ActionEvent event){
			String string1,string2;
			string1=textField1.getText();
			string2=textField2.getText();
			textField3.setText(queryBridgeWords(G, string1, string2));
			string1=textField1.getText();
		}
	}
	  
	private class ButtonHandler2 implements ActionListener{  //生成文本
		public void actionPerformed(ActionEvent event){
			String string1;
			string1=textField1.getText();
			textField3.setText(generateNewText(G, string1));
			
		}
	}
	     
	private class ButtonHandler3 implements ActionListener{  //最短路径
		public void actionPerformed(ActionEvent event){
			
			
			
			
			String string1,string2,string3;
			string1=textField1.getText();
			string2=textField2.getText();
			textField3.setText(calcShortestPath(G,string1,string2));
			
			
		}
	}
	
	private class ButtonHandler4 implements ActionListener{  //单源
		public void actionPerformed(ActionEvent event){
			
			textArea1.setFont(new Font("黑体",Font.PLAIN,20));	
			String string1;
			string1=textField1.getText();
			
			textArea1.setText( singleSource(G,string1));
			G.colorClean();
			showDirectedGraph(G);
			Icon bug1=new ImageIcon("file4.jpg");
			label1.setIcon(bug1);
			
		}
	}
	
	private class ButtonHandler5 implements ActionListener{   //游走
		public void actionPerformed(ActionEvent event){
			
			textField3.setText( randomWalk( G));
			
			
			
		}
	}
	
	
	//GUI
	public lab1(){
		
		super("实验1");
		
		//创建菜单栏并置于窗体中
		
		JMenuBar bar= new JMenuBar();
		setJMenuBar(bar);
		Vertical1=Box.createVerticalBox();
		Horizontal1=Box.createHorizontalBox();
		
		//创建菜单File
		
		JMenu fileMenu=new JMenu("File");
		JMenuItem importItem =new JMenuItem("Import File");
		fileMenu.add(importItem);
		importItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						FileDialog fd=new FileDialog(new Frame(),"选择生成图的文件",FileDialog.LOAD);
						FilenameFilter ff=new FilenameFilter(){
							public boolean accept(File dir,String name){
								if(name.endsWith("txt")){
									G=createDirectedGraph(name);
									showDirectedGraph(G);
									return true;
								}
								else return false;
							}					
						};
						fd.setFilenameFilter(ff);
						fd.setVisible(true);
					}
				}
		);
		bar.add(fileMenu);
		//创建菜单Function
		String functionNames[]={"展示有向图","查询桥接词","根据bridge生成新文本","计算两个单词的最短路径","一个单词到其他单词的最短路径","随机游走"}; 
		JMenuItem []functionItems= new JMenuItem[functionNames.length];
		JMenu functionMenu=new JMenu("Function");
		for(int count=0; count<functionItems.length;count++){
			functionItems[count]=new JMenuItem(functionNames[count]);
			functionMenu.add(functionItems[count]);
		}
		
		
		
		
		functionItems[0].addActionListener( //展示有向图
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						Vertical1.removeAll();
						Horizontal1.removeAll();
						showDirectedGraph(G);
							
						Icon bug=new ImageIcon("file4.jpg");
						
						label1= new JLabel("Directed Grapg",bug,SwingConstants.CENTER);
						label1.setHorizontalTextPosition(SwingConstants.CENTER);
						label1.setVerticalTextPosition(SwingConstants.BOTTOM);
						JScrollPane JS=new JScrollPane(label1);
						
						Vertical1.add(JS);
						c.add(Vertical1);
						
						
						
						setVisible(true);
					}
				}
		);
		
		functionItems[1].addActionListener( //查询桥接词
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						Vertical1.removeAll();
						Horizontal1.removeAll();
						
						showDirectedGraph(G);
						
						Icon bug=new ImageIcon("file4.jpg");
						label1= new JLabel("Directed Grapg",bug,SwingConstants.CENTER);
						label1.setHorizontalTextPosition(SwingConstants.CENTER);
						label1.setVerticalTextPosition(SwingConstants.BOTTOM);
						JScrollPane JS=new JScrollPane(label1);					
						Vertical1.add(JS);
					
						textField3=new JTextField("结果",10);
						label2= new JLabel("查询桥接词:",SwingConstants.CENTER);
						label2.setFont(new Font("黑体",Font.PLAIN,20));									
						Vertical1.add(label2);
						Horizontal1=Box.createHorizontalBox();	
						textField1=new JTextField("请输入第一个单词",10);
						Horizontal1.add(textField1);
						textField2=new JTextField("请输入第二个单词",10);
						Horizontal1.add(textField2);
						button1=new JButton("查询");
						Horizontal1.add(button1);
						ButtonHandler1 handler= new ButtonHandler1();
						button1.addActionListener(handler);
						
											
						Vertical1.add(Horizontal1);
						Vertical1.add(textField3);
						setVisible(true);
					}
				}
		);
	
		
		functionItems[2].addActionListener( //根据bridge word生成新文本
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						
						Horizontal1.removeAll();
						Vertical1.removeAll();
						
						showDirectedGraph(G);
						Icon bug=new ImageIcon("file4.jpg");
						label1= new JLabel("Directed Grapg",bug,SwingConstants.CENTER);
						label1.setHorizontalTextPosition(SwingConstants.CENTER);
						label1.setVerticalTextPosition(SwingConstants.BOTTOM);
						JScrollPane JS=new JScrollPane(label1);					
						Vertical1.add(JS);
					
						textField3=new JTextField("结果",10);
						label2= new JLabel("输入文本:",SwingConstants.CENTER);
						label2.setFont(new Font("黑体",Font.PLAIN,20));									
						Vertical1.add(label2);
						Horizontal1=Box.createHorizontalBox();	
						textField1=new JTextField("请输入第一个单词",10);
						Horizontal1.add(textField1);
						
						button1=new JButton("添加桥接词");
						Horizontal1.add(button1);
						ButtonHandler2 handler= new ButtonHandler2();
						button1.addActionListener(handler);
						
						
						
						Vertical1.add(Horizontal1);
						Vertical1.add(textField3);
						setVisible(true);
					}
				}
		);
		
		functionItems[3].addActionListener( //计算两个单词的最短路径
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						
						Horizontal1.removeAll();
						Vertical1.removeAll();
						
						showDirectedGraph(G);
						Icon bug=new ImageIcon("file4.jpg");
						label1= new JLabel("Directed Grapg",bug,SwingConstants.CENTER);
						label1.setHorizontalTextPosition(SwingConstants.CENTER);
						label1.setVerticalTextPosition(SwingConstants.BOTTOM);
						JScrollPane JS=new JScrollPane(label1);					
						Vertical1.add(JS);
					
						textField3=new JTextField("结果",10);
						label2= new JLabel("计算两个单词的最短路径",SwingConstants.CENTER);
						label2.setFont(new Font("黑体",Font.PLAIN,20));									
						Vertical1.add(label2);
						Horizontal1=Box.createHorizontalBox();	
						textField1=new JTextField("请输入第一个单词",10);
						textField2=new JTextField("请输入第二个单词",10);
						Horizontal1.add(textField1);
						Horizontal1.add(textField2);
						button1=new JButton("查询");
						Horizontal1.add(button1);
						ButtonHandler3 handler= new ButtonHandler3();
						button1.addActionListener(handler);

						Vertical1.add(Horizontal1);
						Vertical1.add(textField3);
						setVisible(true);
					}
				}
		);
		
		functionItems[4].addActionListener( //一个单词到其他单词的最短路径
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						
				
						
						Horizontal1.removeAll();
						Vertical1.removeAll();
						Icon bug=new ImageIcon("file4.jpg");
						label1= new JLabel("Directed Grapg",bug,SwingConstants.CENTER);
						label1.setHorizontalTextPosition(SwingConstants.CENTER);
						label1.setVerticalTextPosition(SwingConstants.BOTTOM);
					
						textArea1=new JTextArea("结果");
						label2= new JLabel("一个单词到其他单词的最短路径",SwingConstants.CENTER);
						label2.setFont(new Font("黑体",Font.PLAIN,20));									
						
						Horizontal1=Box.createHorizontalBox();	
						textField1=new JTextField("请输入单词",10);
						
						JScrollPane JS=new JScrollPane(label1);
						JScrollPane JS1=new JScrollPane(textArea1);
						
						
						
						
						Horizontal1.add(JS);
						Horizontal1.add(JS1);
						Vertical1.add(Horizontal1);
						Vertical1.add(label2);
						Vertical1.add(textField1);
						button1=new JButton("查询");
						Vertical1.add(button1);
						ButtonHandler4 handler= new ButtonHandler4();
						button1.addActionListener(handler);

						setVisible(true);
					}
				}
		);
		
		
		functionItems[5].addActionListener( //随机游走
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						
						Vertical1.removeAll();
						Horizontal1.removeAll();
  					    
  					    Icon bug=new ImageIcon("file4.jpg");
						label1= new JLabel("Directed Grapg",bug,SwingConstants.CENTER);
						label1.setHorizontalTextPosition(SwingConstants.CENTER);
						label1.setVerticalTextPosition(SwingConstants.BOTTOM);
						JScrollPane JS=new JScrollPane(label1);
					
					
						label2= new JLabel("随机游走",SwingConstants.CENTER);
						label2.setFont(new Font("黑体",Font.PLAIN,20));	
						Vertical1.add(JS);
						Vertical1.add(label2);
						Horizontal1=Box.createHorizontalBox();	
						
						button1=new JButton("开始游走");
						Horizontal1.add(button1);
						ButtonHandler5 handler= new ButtonHandler5();
						button1.addActionListener(handler);

						Vertical1.add(Horizontal1);
						Vertical1.add(textField3);
						
						setVisible(true);
					}
				}
		);
		
		
		bar.add(functionMenu);
		
		setSize(500,150);
		setVisible(true);
		
	}
	
	
	public graph createDirectedGraph(String filename){//建图
		G=new graph();
		
		Vector buffer=new Vector();  //读取文件
		String[] stringBuffer=null;	
		stringBuffer=null;
		try{
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader dis = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(dis);
			String s=new String();
			while((s=reader.readLine())!=null) //为null时到文件尾
			{
				stringBuffer=s.split(" ");
				for(int i=0;i<stringBuffer.length;i++){
					stringBuffer[i]=stringBuffer[i].toLowerCase();
					if(stringBuffer[i].charAt(0)<=122&&stringBuffer[i].charAt(0)>=97){
						int lastIndex=stringBuffer[i].length()-1;
						if(stringBuffer[i].charAt(lastIndex)<=122&&stringBuffer[i].charAt(lastIndex)>=97)
							buffer.add(stringBuffer[i]);
						else
							buffer.add(stringBuffer[i].substring(0,lastIndex));		
					}
						
				}													
			}
	
			dis.close();
		}
		catch(IOException e){
			System.out.println(e);
		}		
		graph direcedGraph = new graph();
		int i;
		for( i=0;i<buffer.size()-1;i++){
			//System.out.print(buffer.elementAt(i).toString());
			direcedGraph.createGraph(buffer.elementAt(i).toString(),buffer.elementAt(i+1).toString());
		}	
		if(buffer.size()>0) direcedGraph.createGraph(buffer.elementAt(i).toString());
		//direcedGraph.showGraph();
		return direcedGraph;		
	}
	
	void showDirectedGraph(graph G){  //图形化展示有向图
		try{
		File f1= new File("file3.gv");
		FileOutputStream fos = new FileOutputStream(f1);
		
		String s=new String();//生成命令行语句
		s=G.getCommand();
		byte b[] = s.getBytes();	
		fos.write(b);
		fos.close();
		
		String path = "dot -Tjpg C:\\Users\\majunhua123\\workspace\\lab1\\file3.gv -o C:\\Users\\majunhua123\\workspace\\lab1\\file4.jpg " ; //调用命令行
		
		
		 
	        Runtime run = Runtime.getRuntime();
	        try {
	        
	            Process process = run.exec("cmd.exe  /c " + path);
	            
	            process.waitFor();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		}
		catch(FileNotFoundException e){
			System.out.println("Stream1"+e);
		}catch(IOException e){
			System.err.println("Stream2"+e);
		}
		}
		
		
		public String randomWalk(graph G){  //随机游走
			final int maxNum=100000;
			
			int[][] graphMatrix=G.getMatrix();
		
			Random rand =new Random();
			
			int startNum=(rand.nextInt())%(G.getWordNum()); //初始结点
			while(startNum<0) startNum=(rand.nextInt())%(G.getWordNum());
			Vector<Integer> walkOrder = new Vector<Integer>();
			walkOrder.add(startNum) ;
			int currentNum=startNum,nextNum=0;
			String randomPath=new String();
			
			while(true){
				Vector<Integer> availiableNode=new Vector<Integer>();
				boolean sign=false;
				nextNum=0;
				while(nextNum<G.getWordNum()){
					if(graphMatrix[currentNum][nextNum]!=maxNum){
						sign=true;
	
						availiableNode.add(nextNum);
					}
					nextNum++;
				}		
				if(sign==false) 
					break;
				else{
					int temp=rand.nextInt();
					while(temp<0) temp=rand.nextInt();
					temp=temp%availiableNode.size();
					int randomNum=availiableNode.elementAt(temp);
					
					graphMatrix[currentNum][randomNum]=maxNum;
					currentNum=randomNum;
					walkOrder.add(currentNum);		
				}						
			}
			int i=0;
		
			for( i=0;i<walkOrder.size()-1;i++){
				G.changeEdgeColor(walkOrder.elementAt(i),walkOrder.elementAt(i+1),1);
				
				
				randomPath+=G.indexofVector(walkOrder.elementAt(i))+" ";
				
		
				
				
				}
			randomPath+=G.indexofVector(walkOrder.elementAt(i))+" \n";
			
			
			
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
			
			
			return randomPath ;
		}
		
		public String queryBridgeWords(graph G, String word1, String word2){//查询桥接词
			Vector<String> bridgeWords=new Vector<String>();
			word w1=new word();
			String result=new String();
			int i;
			if (G.queryExist(word1)&&G.queryExist(word2))
			{
				w1=G.findWord(word1);
				bridgeWords=w1.findBridgeWords(G,word2);
				if (bridgeWords.size()==0)
				{
					result="No bridge words from word1 to word2!";
				}
				else
				{
					result="The bridge words from "+word1+" to "+word2+" are: ";
					for (i=0;i<bridgeWords.size();i++){
						result+=bridgeWords.get(i)+ " ";
					}
					result+="\n";
				}				
			}
			else{
				result="No word1 or word2 in the graph!";
			}
			return result;
			
		}
		
		public String generateNewText(graph G, String inputText){//根据bridge word生成新文本
			Vector<String> input=new Vector<String>();
			int i=0;
			String[] temp0;
			temp0=inputText.split(" ");
			for (i=0;i<temp0.length;i++)
			{
				temp0[i]=temp0[i].toLowerCase();
				input.addElement(temp0[i]);
			}
			Random rand =new Random(25);
			int ran; //随机选词插入
			int inputSize=input.size();
			word formerWord=new word();
			StringBuffer buffer=new StringBuffer();
			String laterWord,output;
			Vector<String> temp=new Vector<String>();
			i=0;
			while (i<inputSize-1){
				formerWord=G.findWord(input.get(i));
				laterWord=input.get(i+1);
				temp=formerWord.findBridgeWords(G, laterWord);
				if (temp.size()==0)
				{
					i++;
				}
				else
				{
					if (temp.size()==1)
					{
						input.insertElementAt(temp.get(0), i+1);
						inputSize+=1;
						i=i+2;
					}
					else
					{
						ran=(rand.nextInt(100))%(temp.size());
						input.insertElementAt(temp.get(ran), i+1);
						inputSize+=1;
						i=i+2;
					}
				}
			}
			for (i=0;i<inputSize;i++)
			{
				buffer.append(input.get(i));
				buffer.append(' ');
			}
			output=buffer.toString();
			return output;
		}

		
		public int findMinCost(int indexSource,int currentShortestLength[],boolean flag[] ){
			int x=-1,min=-1;
			int i;
			for (i=0;i<currentShortestLength.length;i++)
			{
				if (flag[i]==false)
				{
					min=currentShortestLength[i];
					x=i;
					break;
				}
			}
			for (i=0;i<currentShortestLength.length;i++)
			{
				if ((flag[i]==false)&&(currentShortestLength[i]<min))
				{
					min=currentShortestLength[i];
					x=i;
				}
			}
			return x;
		}
		
		
		
		public String singleSource(graph G,String sourceWord){//单源到其他所有单词的最短路径
			StringBuffer buffer=new StringBuffer();
			String temp;
			int i;
			for (i=0;i<G.getWordList().size();i++)
			{
				buffer.append(sourceWord);
				buffer.append("到");
				buffer.append(G.getWordList().get(i).getname());
				buffer.append("的最短路径：\n");
				if(G.queryExist(sourceWord)==false){
					return "can't find "+ sourceWord+" in the graph!";
				}
					
				temp=calcShortestPath(G,sourceWord,G.getWordList().get(i).getname());
				
				
				buffer.append(temp);
			}
			temp=buffer.toString();
			return temp;
		}
		
		
		public String calcShortestPath(graph G, String word1, String word2){//计算两个单词之间的最短路径
			final int maxNum=100000;
			Vector<word> wordList=new Vector<word>();
			wordList=G.getWordList();
			int i,j,sum;
			int indexSource,indexTail,indexMin;
			indexSource=G.getWordIndex(word1);
			indexTail=G.getWordIndex(word2);
			int stack[][]=new int[2][10000],top=-1;    //打印路径所需栈
			int countColor=1;     //用于路径颜色的计数
			//Dijkstra算法实现最短路径寻找所需数据结构
			int graphArray[][]=new int[G.getWordNum()][G.getWordNum()];
			int currentShortestLength[]=new int[wordList.size()]; //存源点到各个顶点的当前最短路径长度
			int lastPassWord[][]=new int[wordList.size()][wordList.size()];  //存源点到每个顶点最短路径上最后经过的点
			int countPath[]=new int[wordList.size()];   //记录每个顶点作为最短路径中回溯点的次数+1
			boolean flag[]=new boolean[wordList.size()];  //判断每个点是否已经有了最短路径
			//初始化上述的数据结构
			graphArray=G.getMatrix();
			for (i=0;i<wordList.size();i++)  //初始化currentShortestLength数组
			{
				currentShortestLength[i]=graphArray[indexSource][i];
			}
			for (i=0;i<wordList.size();i++)
			{
				for (j=0;j<wordList.size();j++)
				{
					lastPassWord[i][j]=indexSource;
				}
			}
			for (i=0;i<wordList.size();i++)
			{
				countPath[i]=0;
			}
			for (i=0;i<wordList.size();i++)
			{
				flag[i]=false;
			}
			//初始化完成，开始算法主体
			int count;
			for (i=0;i<wordList.size();i++)
			{
				indexMin=findMinCost(indexSource,currentShortestLength,flag);
				flag[indexMin]=true;
				for (j=0;j<wordList.size();j++)
				{
					if (flag[j]==false)
					{
						sum=currentShortestLength[indexMin]+graphArray[indexMin][j];
						if (sum<currentShortestLength[j])
						{
							currentShortestLength[j]=sum;
							lastPassWord[0][j]=indexMin;
							countPath[j]=0;
						}
						else if (sum==currentShortestLength[j])
						{
							count=countPath[j];
							lastPassWord[++count][j]=indexMin;
							countPath[j]=count;
						}
					}
				}
			}
			//打印最短路径
			int t;
			StringBuffer buffer=new StringBuffer();
			String output=new String();
			if (currentShortestLength[indexTail]<maxNum)
			{
				//先将目的节点入栈
				stack[0][++top]=indexTail;
				stack[1][top]=0;
				//之后将其他节点入栈
				i=lastPassWord[countPath[indexTail]][indexTail];
				t=countPath[indexTail];
				while (i!=indexSource)
				{
					stack[0][++top]=i;
					stack[1][top]=t;
					t=countPath[i];
					i=lastPassWord[countPath[i]][i];
				}
				while (top!=-1)
				{
					//先将一条最短路径读取出来
					t=top;
					buffer.append(word1);
					if (wordList.get(G.getWordIndex(word1)).getColor(wordList.get(stack[0][t]).getname())==0)
					{
						G.changeEdgeColor(G.getWordIndex(word1), stack[0][t], ++countColor);
					}
					else if (wordList.get(G.getWordIndex(word1)).getColor(wordList.get(stack[0][t]).getname())>1)
					{
						G.changeEdgeColor(G.getWordIndex(word1), stack[0][t], 1);
					}
					buffer.append("->");
					while (t!=0)
					{
						buffer.append(wordList.get(stack[0][t]).getname());
						if (wordList.get(G.getWordIndex(wordList.get(stack[0][t]).getname())).getColor(wordList.get(stack[0][t-1]).getname())==0)
						{
							G.changeEdgeColor(G.getWordIndex(wordList.get(stack[0][t]).getname()), stack[0][t-1], countColor);
						}
						else if (wordList.get(G.getWordIndex(wordList.get(stack[0][t]).getname())).getColor(wordList.get(stack[0][t-1]).getname())>1)
						{
							G.changeEdgeColor(G.getWordIndex(wordList.get(stack[0][t]).getname()), stack[0][t-1], 1);
						}
						buffer.append("->");
						t--;
					}
					buffer.append(word2);
					buffer.append("\n");
					//之后再寻找第二条路径（先弹栈再进栈）
					while (stack[1][top]==0)
					{
						top--;
						if (top==-1)
						{
							break;
						}
					}
					if (top==-1)
					{
						break;
					}
					i=lastPassWord[stack[1][top]-1][stack[0][top-1]];
					t=stack[1][top]-1;
					top--;
					while (i!=indexSource)
					{
						stack[0][++top]=i;
						stack[1][top]=t;
						i=lastPassWord[countPath[i]][i];
						t=countPath[i];
					}
				}
				output=buffer.toString();
			
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
				
			}
			else
			{
				output=word1+"与"+word2+"之间无路径！";
			}
			
			
			return output;
		}
	
}
