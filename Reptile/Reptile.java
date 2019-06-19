import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reptile{
	private String pathname="D:\\URLS.txt";
	private File outfile=new File(pathname);
	private String pathname1="D:\\News.txt";
	private File outfile1=new File(pathname1);
	private BufferedReader br=null;
	private BufferedWriter out=null;
	public Reptile() {
		InputStream isr=null;
		URL url=null;
		HttpURLConnection conn=null;
		String regex= "((?<=href=\\\")http.*(?=\\\"))";
		Pattern pa=Pattern.compile(regex);
		try {
			url=new URL("https://news.baidu.com/");
			if(!outfile.exists()) {
				outfile.createNewFile();
			}
			conn=(HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setDoInput(true);
			conn.connect();
			isr=conn.getInputStream();
			InetAddress addr1=InetAddress.getLocalHost();
			System.out.println("本地主机名:"+addr1.getHostName()+"\n本地主机地址:"+addr1.getHostAddress());
			InetAddress addr2=InetAddress.getByName("news.sina.com.cn");
			System.out.println("目标网址主机名:"+addr2.getHostName()+"\n目标网址主机地址:"+addr2.getHostAddress());
			br=new BufferedReader(new InputStreamReader(isr,"utf-8"));
			String str=null;
			FileWriter wr=new FileWriter(outfile);
			while((str=br.readLine())!=null) {
				Matcher mat=pa.matcher(str);
				if(mat.find()) {
					wr.write(str);
					wr.write("\n");
					wr.flush();
				}
			}
			wr.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.disconnect();
			try {
				isr.close();
				br.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		try {
			FileInputStream fr=new FileInputStream(outfile);
			br=new BufferedReader(new InputStreamReader(fr,"utf-8"));
			String str1=null;
			String urls=null;
			while((str1=br.readLine())!=null) {
				String reg = "(?i)<a[^>]+href[=\"\']+([^\"\']+)[\"\']?[^>]*>((?!<\\/a>)[\\s\\S]*)<\\/a>";
				Pattern p = Pattern.compile(reg);
				Matcher m = p.matcher(str1);
				if(m.find()){
				    urls=m.group(1);
				    visit(urls);
				    System.out.println(m.group(1));
				}
			}
			fr.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void visit(String urls) {
		try {
			URL url1=new URL(urls);
			if(!outfile1.exists()) {
				outfile1.createNewFile();
			}
			HttpURLConnection conn1=(HttpURLConnection) url1.openConnection();
			conn1.setConnectTimeout(5000);
			conn1.setReadTimeout(5000);
			conn1.setDoInput(true);
			conn1.connect();
			InputStream isr1=conn1.getInputStream();
			BufferedReader br1=new BufferedReader(new InputStreamReader(isr1,"utf-8"));
			String str2=null;
			FileWriter wr=new FileWriter(outfile1,true);
			out=new BufferedWriter(wr);
			Pattern pa2 = Pattern.compile("^[\\u4e00-\\u9fa5]+$");
			while((str2=br1.readLine())!=null) {
				Matcher mat=pa2.matcher(str2);
				if(mat.find()) {
					out.write(mat.group());
					out.write("\n");
					out.flush();
				}
			}
			out.close();
			br1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Reptile();
	}
}