package AWT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Record {
	
	private String rank;
	private int click;
	private int timing;
	private double score;
	private File file;
	LinkedList<String[]> recList; 
	private String rec[];// = new String[4];
	private String tempString;
	public Record() {
		
		file = new File("./record/record.txt");
		recList = new LinkedList<String[]>();
		rec= new String[4];
	
	}
	
	public Record(String rank,int c,int t) {
		this.rank = rank;
		this.click = c;
		this.timing = t;
		this.score = c*0.5+t*0.5;
		file = new File("./record/record.txt");
		recList = new LinkedList<String[]>();
		rec= new String[4];
	}
	
//	public void writeToFile() {
//		
//	}

	
	/* 读出文件 */
	public LinkedList<String[]> fileRead() throws IOException {

		FileReader fr = null;
		fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		//lineCounter = 1;
		while ((br.readLine()) != null) {
			tempString = br.readLine();
			if(tempString!=null) {
				rec = tempString.split("\t");
			}
			
			//rec[0] = tempString[0];
			System.out.println(tempString);
			recList.add(rec); 	
		}
	
		br.close();
		fr.close();
		return recList;
	}

	/* 写回文件 */
	public void fileWrite() throws IOException {
		FileWriter writer = null;
		writer = new FileWriter(file, true);
		// Goods temp = head.nextGoods;

		writer.write(rank+"\t"+click + "\t" + timing+ "\t" + score+ "\t"+"\r\n");

		writer.close();
	}

	public static void main(String[] args) throws IOException {
		Record record =new Record("mm", 1, 1);
		record.fileRead();
	}
}
