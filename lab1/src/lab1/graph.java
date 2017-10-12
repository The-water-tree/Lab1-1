package lab1;
import java.util.*;

public class graph {
	private Vector<word> wordList;//图的基本属性(新添加的git测试用注释）
	
	public graph(){
		wordList=new Vector<word>();    //B1 branch:the first update
	}		
	
	public Vector<word> getWordList(){//返回图中所有节点的集合
		return wordList;
	}
	
	public int getWordIndex(String wd){//返回单词在wordList中的位置
		int i,rt=-1;
		String x;
		for (i=0;i<wordList.size();i++){
			x=wordList.get(i).getname();
			if (x.equalsIgnoreCase(wd))
			{
				rt=i;
				break;
			}
		}
		return rt;
	}
	
	public void createGraph(String firstWord, String secondWord){
		int sign=0;
		int i;			
		for( i=0;i<wordList.size();i++){
			if(wordList.elementAt(i).getname().equals(firstWord)){		//判断此单词是否出现过
				sign=1;
				break;
			}
				}
			if(sign==1){                                  
				wordList.elementAt(i).searchLink(secondWord);}            //判断图的边是否出现过
			else {  //新单词
				word newWord= new word(firstWord);
				wordList.addElement(newWord);
				newWord.addLinkList(secondWord);	
			}	
	}
	
	public void createGraph(String lastWord){
		int sign=0;
		int i;			
		for( i=0;i<wordList.size();i++){
			if(wordList.elementAt(i).getname().equals(lastWord)){		//判断此单词是否出现过
				sign=1;
				break;
			}
				}
			if(sign==1);           //判断图的边是否出现过
			else {  //新单词
				word newWorld= new word(lastWord);
				wordList.addElement(newWorld);	
			}	
	}
	
	
	/*public void showGraph(){
		for(int counter=0;counter< wordList.size();counter++){
			wordList.elementAt(counter).show();
		}
	}*/
	
	public String getCommand(){ //生成graphzip语句
		String s=new String();
		s = "digraph abc{";
		for(int i=0;i<wordList.size();i++){
			s+=wordList.elementAt(i).getname()+";\n";
		}
		for(int i=0;i<wordList.size();i++){
			s+=wordList.elementAt(i).graphInf();
		}	
		s+="}";
		return s;
	}
	
	public int getWordNum(){
		return wordList.size();
	}
	
	public int[][] getMatrix(){//建立有向图的邻接矩阵
		final int maxNum=100000;
		int i,j;
		int sign;
		int graphMatrix[][]=new int[wordList.size()][wordList.size()];
		for (i=0;i<wordList.size();i++)
		{
			for (j=0;j<wordList.size();j++)
			{
				sign=wordList.get(i).judgeOfWord(wordList.get(j).getname());
				if (sign==-1)
				{
					graphMatrix[i][j]=maxNum;
				}
				else
				{
					graphMatrix[i][j]=wordList.get(i).getWeight(sign);
				}
			}
		}
		return graphMatrix;
	}
	
	
	
	public void changeEdgeColor(int orderedNum, int edgeNum, int colorNum){
		word changedWord= wordList.get(orderedNum);    //确定更改颜色的单词
		int edgeIndex=changedWord.indexOfEdge(wordList.get(edgeNum).getname());
		changedWord.changecolor(edgeIndex, colorNum);
	}
	
	public void colorClean(){
		for(int i=0;i<wordList.size();i++)
			wordList.get(i).colorCleaned();
	}

	
	public String indexofVector(int num){
		return wordList.elementAt(num).getname();
	}
	
	//查询桥接词
	public boolean queryExist(String wd){//查询一个单词是否存在于有向图中
		int i;
		for (i=0;i<wordList.size();i++)
		{
			if (wordList.get(i).getname().equalsIgnoreCase(wd))
			{
				return true;
			}
		}
		return false;
	}
	public word findWord(String wd){//返回一个单词所在的节点word对象
		int i;
		word temp=new word();
		for(i=0;i<this.wordList.size();i++){
			temp=this.wordList.get(i);
			if(temp.getname().equalsIgnoreCase(wd))
				break;
		}
		return temp;
	}
	
	
	
}
