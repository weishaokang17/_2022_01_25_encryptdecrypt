package per.wsk.study02;

import per.wsk.study01.KaiserTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 频率分析法破解凯撒密码
 */
public class FrequencyAnalysis {

	//文章
	public static String article = "My father was a self-taught mandolin player. He was one of the best string instrument players in our town. He could not read music, but if he heard a tune a few times, he could play it. When he was younger, he was a member of a small country music band. They would play at local dances and on a few occasions would play for the local radio station. He often told us how he had auditioned and earned a position in a band that featured Patsy Cline as their lead singer. He told the family that after he was hired he never went back. Dad was a very religious man. He stated that there was a lot of drinking and cursing the day of his audition and he did not want to be around that type of environment.\n" +
			" \n" +
			"　　Occasionally, Dad would get out his mandolin and play for the family. We three children: Trisha, Monte and I, George Jr., would often sing along. Songs such as the Tennessee Waltz, Harbor Lights and around Christmas time, the well-known rendition of Silver Bells. \"Silver Bells, Silver Bells, its Christmas time in the city\" would ring throughout the house. One of Dad's favorite hymns was \"The Old Rugged Cross\". We learned the words to the hymn when we were very young, and would sing it with Dad when he would play and sing. Another song that was often shared in our house was a song that accompanied the Walt Disney series: Davey Crockett. Dad only had to hear the song twice before he learned it well enough to play it. \"Davey, Davey Crockett, King of the Wild Frontier\" was a favorite song for the family. He knew we enjoyed the song and the program and would often get out the mandolin after the program was over. I could never get over how he could play the songs so well after only hearing them a few times. I loved to sing, but I never learned how to play the mandolin. This is something I regret to this day.\n" +
			" \n" +
			"　　Dad loved to play the mandolin for his family he knew we enjoyed singing, and hearing him play. He was like that. If he could give pleasure to others, he would, especially his family. He was always there, sacrificing his time and efforts to see that his family had enough in their life. I had to mature into a man and have children of my own before I realized how much he had sacrificed.\n" +
			" \n" +
			"　　I joined the United States Air Force in January of 1962. Whenever I would come home on leave, I would ask Dad to play the mandolin. Nobody played the mandolin like my father. He could touch your soul with the tones that came out of that old mandolin. He seemed to shine when he was playing. You could see his pride in his ability to play so well for his family.\n" +
			" \n" +
			"　　When Dad was younger, he worked for his father on the farm. His father was a farmer and sharecropped a farm for the man who owned the property. In 1950, our family moved from the farm. Dad had gained employment at the local limestone quarry. When the quarry closed in August of 1957, he had to seek other employment. He worked for Owens Yacht Company in Dundalk, Maryland and for Todd Steel in Point of Rocks, Maryland. While working at Todd Steel, he was involved in an accident. His job was to roll angle iron onto a conveyor so that the welders farther up the production line would have it to complete their job. On this particular day Dad got the third index finger of his left hand mashed between two pieces of steel. The doctor who operated on the finger could not save it, and Dad ended up having the tip of the finger amputated. He didn't lose enough of the finger where it would stop him picking up anything, but it did impact his ability to play the mandolin.\n" +
			" \n" +
			"　　After the accident, Dad was reluctant to play the mandolin. He felt that he could not play as well as he had before the accident. When I came home on leave and asked him to play he would make excuses for why he couldn't play. Eventually, we would wear him down and he would say \"Okay, but remember, I can't hold down on the strings the way I used to\" or \"Since the accident to this finger I can't play as good\". For the family it didn't make any difference that Dad couldn't play as well. We were just glad that he would play. When he played the old mandolin it would carry us back to a cheerful, happier time in our lives. \"Davey, Davey Crockett, King of the Wild Frontier\", would again be heard in the little town of Bakerton, West Virginia.\n" +
			" \n" +
			"　　In August of 1993 my father was diagnosed with inoperable lung cancer. He chose not to receive chemotherapy treatments so that he could live out the rest of his life in dignity. About a week before his death, we asked Dad if he would play the mandolin for us. He made excuses but said \"okay\". He knew it would probably be the last time he would play for us. He tuned up the old mandolin and played a few notes. When I looked around, there was not a dry eye in the family. We saw before us a quiet humble man with an inner strength that comes from knowing God, and living with him in one's life. Dad would never play the mandolin for us again. We felt at the time that he wouldn't have enough strength to play, and that makes the memory of that day even stronger. Dad was doing something he had done all his life, giving. As sick as he was, he was still pleasing others. Dad sure could play that Mandolin!";



	//英文里出现次数最多的字符
	private static final char MAGIC_CHAR = 'e';
	//破解生成的最大文件数
	private static final int DE_MAX_FILE = 4;




	public static void main(String[] args) throws Exception {
		//测试1，统计字符个数
		printCharCount("article.txt");
		
		//加密文件
		int key = 3;
		encryptFile("article.txt", "article_en.txt", key);
		
		//读取加密后的文件
//	    String artile = Util.file2String("article_en.txt");
//	    //解密（会生成多个备选文件）
//	    decryptCaesarCode(artile, "article_de.txt");
	}
	
	public static void printCharCount(String path) throws IOException{
		String data = Util.file2String(path);
		List<Entry<Character, Integer>> mapList = getMaxCountChar(data);
		for (Entry<Character, Integer> entry : mapList) {
			//输出前几位的统计信息
			System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
		}
	}
	
	public static void encryptFile(String srcFile, String destFile, int key) throws IOException {
		String artile = Util.file2String(srcFile);
		//加密文件
		String encryptData = KaiserTest.encryptKaiser(artile, key);
		//保存加密后的文件
		Util.string2File(encryptData, destFile);
	}
	
	/**
	 * 破解凯撒密码
	 * @param input 数据源
	 * @return 返回解密后的数据
	 */
	public static void decryptCaesarCode(String input, String destPath) {
		int deCount = 0;//当前解密生成的备选文件数
		//获取出现频率最高的字符信息（出现次数越多越靠前）
		List<Entry<Character, Integer>> mapList = getMaxCountChar(input);
		for (Entry<Character, Integer> entry : mapList) {
			//限制解密文件备选数
			if (deCount >= DE_MAX_FILE) {
				break;
			}
			
			//输出前几位的统计信息
			System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
			
			++deCount;
			//出现次数最高的字符跟MAGIC_CHAR的偏移量即为秘钥
			int key = entry.getKey() - MAGIC_CHAR;
			System.out.println("猜测key = " + key + "， 解密生成第" + deCount + "个备选文件" + "\n");
			String decrypt = KaiserTest.decryptKaiser(input, key);
			
			String fileName = "de_" + deCount + destPath;
			Util.string2File(decrypt, fileName);
		}
	}
	
	//统计String里出现最多的字符
	public static List<Entry<Character, Integer>> getMaxCountChar(String data) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] array = data.toCharArray();
		for (char c : array) {
			if(!map.containsKey(c)) {
				map.put(c, 1);
			}else{
				Integer count = map.get(c);
				map.put(c, count + 1);
			}
		}
		
		//输出统计信息
		/*for (Entry<Character, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "出现" + entry.getValue() +  "次");
		}*/
		
		//获取获取最大值
		int maxCount = 0;
		for (Entry<Character, Integer> entry : map.entrySet()) {
			//不统计空格
			if (/*entry.getKey() != ' ' && */entry.getValue() > maxCount) { 
				maxCount = entry.getValue();
			}
		}
		
		//map转换成list便于排序
		List<Entry<Character, Integer>> mapList = new ArrayList<Entry<Character,Integer>>(map.entrySet());
		//根据字符出现次数排序
		Collections.sort(mapList, new Comparator<Entry<Character, Integer>>(){

			public int compare(Entry<Character, Integer> o1,
					Entry<Character, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return mapList;
	}
}
