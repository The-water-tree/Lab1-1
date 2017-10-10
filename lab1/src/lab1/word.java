package lab1;
import java.util.*;
import java.io.*;

public class word {
	private String wordName;
	private Vector<String> linkList ;    //相邻边集合
	private Vector<Integer> weight; //边的权重
	private Vector<Integer> color;
	public  word(){
	}
	public  word (String name){
		wordName=new String();
		wordName=name;
		linkList=new Vector<String> ();
		weight= new Vector<Integer>();
		color= new Vector<Integer>();
	}
	public String getname(){
		return wordName;
	}
	
	public String getLinkList(int index){
		return linkList.get(index);
	}
	public int getWeight(int index){
		return weight.get(index);
	}
	
	
	
	public String graphInf(){  //返回graphzip文件代码
		String s=new String();
		String colorType[]={"red","green","blue","yellow"};
		
		for(int i=0;i<linkList.size();i++){
			s+=this.getname()+" -> ";		
			s+=linkList.elementAt(i);
			s+=" [label = \""+weight.elementAt(i)+"\" ";
			if(color.elementAt(i)!=0) s+=", color = \""+colorType[color.elementAt(i)-1]+"\"";
			else ;
			s+="];\n";
		}		
		return s;
	}
	public void searchLink(String newName){ //检查临边需要如何改变
		int searchIndex=linkList.indexOf(newName);
		if(searchIndex==-1) addLinkList(newName);
		else changeLinkList(newName);
	}
	public void addLinkList(String newName){  //添加临边
		
		weight.add(1);
		color.add(0);
		linkList.addElement(newName);
	}
	
		
	public void changeLinkList(String name){ //改变临边权重
		
		int changeIndex=linkList.indexOf(name);	
		int changeNum= (int)weight.elementAt(changeIndex);
		weight.setElementAt(changeNum+1, changeIndex);
	}
	
	public void changecolor(int index,int colorNum){
		color.set(index, colorNum);
	}
	
	public void colorCleaned(){ //清除颜色
		for(int i=0;i<color.size();i++)
			color.set(i, 0);
	}
	
	public int indexOfEdge(String edgeName){  
		return linkList.indexOf(edgeName);
	}
	
	public int getColor(String name)    //再次继续添加一个git测试的注释
	{
		int i;
		int rt=0;
		for (i=0;i<color.size();i++)
		{
			if (linkList.get(i).equalsIgnoreCase(name))
			{
				rt=i;
				break;
			}
		}
		return color.get(rt);
	}
	
	/*public void show(){        //显示每个单词信息
		System.out.print("name:"+getname()+"\n");
		showlinkList();
		showlinkedNumber();
	}
	public void showlinkList(){
		printVector(linkList);		
	}
	public void showlinkedNumber(){
		printVector(weight);	
	}
	public static void  printVector(Vector vectorToOutput){
		if(vectorToOutput.isEmpty())
			System.out.print("Vector is empty\n");
		else{
			System.out.print("Vector contains: ");
			Enumeration items=vectorToOutput.elements();
			while(items.hasMoreElements())
				System.out.print(items.nextElement()+" ");
			System.out.print("\n");
		}
		
	}
	*/
	//查询桥接词所用方法
	
	public int judgeOfWord(String wd){//判断一个单词是否与当前word对象邻接，邻接时返回其下标
		int index=-1;
		index=linkList.indexOf(wd);
		return index;
	}
	
	public Vector<String> findBridgeWords(graph G,String word2){//找到当前对象与word2桥接的词
		Vector<String> bridgeWords=new Vector<String>();
		String tempWord;
		word temp;
		int i,j;
		for (i=0;i<this.linkList.size();i++){
			tempWord=linkList.elementAt(i);
			temp=G.findWord(tempWord);
			for (j=0;j<temp.linkList.size();j++){
				if (word2.equalsIgnoreCase(temp.getLinkList(j)))
				{
					bridgeWords.addElement(tempWord);
					break;
				}
			}
		}
		return bridgeWords;
	}
	
	
}
