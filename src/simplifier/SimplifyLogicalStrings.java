package simplifier;

public class SimplifyLogicalStrings {

	static String simplifiedStr = ""; 

	public static void main(String[] args) {
		System.out.println(simplify("(B&A)=>(B=>R)"));

	}

	public static String simplify(String str) {
		String solv = str;
		String comment ="";
		String result ="";
		int index;
		int count = -1;
		String word;
		String ch, ch2;
		
		for (char a: str.toCharArray()) {
			count +=1;
			index = count;
			switch(a) {
			case '&':
				comment = "Using Idempotent Law\n"
						+ "----------------------\n";
				if((solv.charAt(index-1))==(solv.charAt(index+1))) {
					ch = solv.charAt(index-1)+"";
					word = ch+"&"+ch;
					solv = solv.replaceAll(word, ch);
					result += comment + solv + "\n\n";
				}
				break;
				
			case '|':
				comment = "Using Idempotent Law\n"
						+ "----------------------\n";
				if((solv.charAt(index-1))==(solv.charAt(index+1))) {
					ch = solv.charAt(index+1)+"";
					word = ch+"\\|"+ch;
					solv = solv.replaceAll(word, ch);
					result += comment + solv + "\n\n";
				}
				break;
			case '>':
				comment = "Using Conditional Identities Law\n"
						+ "----------------------\n";
				if((solv.charAt(index-2)!= solv.charAt(index+1)) && (solv.charAt(index+1) != '(')) {
					ch = solv.charAt(index+1)+"";
					ch2 = solv.charAt(index -2)+ "";
					word = ch2+"=>"+ch;
					solv = solv.replaceAll(word, "~"+ch2+"|"+ch);
					result += comment + solv + "\n\n";
				}
				break;
			case '~':
				comment = "Using Double Negation Law\n"
						+ "----------------------\n";
				if((solv.charAt(index-1))==(solv.charAt(index))) {
					ch = solv.charAt(index+1)+"";
					word = "~~"+ch;
					solv = solv.replaceAll(word, ch);
					result += comment + solv + "\n\n";	
				}
				break;
			}
		}
		setSimplifiedStr(solv);
		return result;	
	}

	public static void setSimplifiedStr(String str) {
		simplifiedStr = str;
	}

	public static String getSimplifiedStr() {
		return simplifiedStr;
	}

}
