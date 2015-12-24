
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Klijent {
	
    static Socket soketZaKomunikaciju=null;	
	static Socket soketZaPodatke=null;
	
	static PrintStream izlazniTokKaServeru=null;
	static BufferedReader ulazniTokOdServera=null;
	
	static PrintStream izlazniTokPodakaKaServeru=null;
	static BufferedReader ulazniTokPodatakaOdServera=null;
	
	static BufferedReader ulazKonzola=null;
	static boolean kraj=false;
	
	public static void main(String[] args) {
		String imeKorisnika;
		String linija;
		String operacija;
		String brojevi;
		double rezultat=0;
		try {
			//port za kontrolnu vezu
			int port=1908;
			
			//povezujemo se na host	
			soketZaKomunikaciju=new Socket("localhost", port);
		

			//inicijalizacija ulaza sa konzole
			ulazKonzola=new BufferedReader(new InputStreamReader(System.in));
			
			izlazniTokKaServeru=new PrintStream(soketZaKomunikaciju.getOutputStream());
			ulazniTokOdServera=new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			
		
			
			System.out.println(ulazniTokOdServera.readLine());
			imeKorisnika=ulazKonzola.readLine();
			//saljemo serveru
			izlazniTokKaServeru.println(imeKorisnika);
			char pretposlednjeSlovo=imeKorisnika.charAt(imeKorisnika.length()-2);
			switch (pretposlednjeSlovo) {
			case 'c':
				System.out.println("Dosli ste na pravo mesto "+imeKorisnika.substring(0, imeKorisnika.length()-1)+"e"+"!\n Ovo je interaktivni digitron duca4.0! Ako zelite da uzivate u carima osnovnih operacija pritisnite ENTER, ako smatrate da ste na pogresnom mestu napisite PAPA");
				break;
			case 'a':
				System.out.println("Dosli ste na pravo mesto "+imeKorisnika+"e"+"! Ovo je interaktivni digitron duca4.0!\n  Ako zelite da uzivate u carima osnovnih operacija pritisnite ENTER, ako smatrate da ste na pogresnom mestu napisite PAPA");
				break;
			case 'o':
				System.out.println("Dosli ste na pravo mesto "+imeKorisnika+"e"+"! Ovo je interaktivni digitron duca4.0!\n  Ako zelite da uzivate u carima osnovnih operacija pritisnite ENTER, ako smatrate da ste na pogresnom mestu napisite PAPA");
				break;

			default:System.out.println("Dosli ste na pravo mesto "+imeKorisnika+"! Ovo je interaktivni digitron duca4.0!\n  Ako zelite da uzivate u carima osnovnih operacija pritisnite ENTER, ako smatrate da ste na pogresnom mestu napisite PAPA");
				break;
			}
			
			
			linija=ulazKonzola.readLine();
        		if(linija.startsWith("p")||linija.startsWith("P")){
        			System.out.println("Dovidjenja i hvala Vam iako niste raèunali sa nama! :(");
        			return;}
			
        		int y = 1;

    			for (int x=0; y<10; x++){
    				System.out.println("Izaberi jednu operaciju sabiranje(+),oduzimanje(-),množenje(*),deljenje(/)");
    			   
    			    
    			
    				while(true){
    					operacija=ulazKonzola.readLine();
    					if((operacija.equals("+"))||(operacija.equals("-"))||(operacija.equals("*"))||(operacija.equals("/"))
    							||(operacija.startsWith("s"))||(operacija.startsWith("o"))||(operacija.startsWith("m"))||(operacija.startsWith("d"))){
    					
    					break;}
    					System.out.println("Pogrešno ste ukucali operaciju. pokušajte ponovo!");
    					
    				
    				}
    				izlazniTokKaServeru.println(operacija);
    				//port za podatke
    				int port2=40;
    				
    				if(args.length>0){
    					port2=Integer.parseInt(args[0]);
    				}
    				soketZaPodatke=new Socket("localhost",port2);
    				izlazniTokPodakaKaServeru=new PrintStream(soketZaPodatke.getOutputStream());
    				ulazniTokPodatakaOdServera=new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));
    				
    			
    				System.out.println(ulazniTokOdServera.readLine());
    				
    				
    				while(true){
    					brojevi=ulazKonzola.readLine();
    					if(brojevi.matches("[0-9 .-]+")){
    					
    					break;}
    					System.out.println("Pogresno ste uneli, pazite na razmak i slova koja nisu dozvoljena!Pokusajte ponovo!");
    					
    				
    				}
    				
    				
    				
    				izlazniTokPodakaKaServeru.println(brojevi);
    				
    				rezultat = Double.parseDouble(ulazniTokPodatakaOdServera.readLine());
    				System.out.println("Resenje je "+rezultat);
    				
    				soketZaPodatke.close();
    				
    				System.out.println("Ako jos zelite da racunate sa nama recite DA,a ako ne recite DOSTA");
					
    				
   		         while (true) {
   		        		linija=ulazKonzola.readLine();
   		        		if(linija.startsWith("DOSTA")||linija.startsWith("dosta")){
   		        			System.out.println("Dovidjenja hvala Vam što ste raèunali sa nama!");
   		        			y=10;
   		        			break;
   		        			}
   		        		break;
   		        	}
   		         }
    				
        		
        		    
        		    
        		    
        		
        		
        		
        		
        		
        		
        		
    			
			while (!kraj) {
				izlazniTokKaServeru.println(ulazKonzola.readLine());
				
			}
			soketZaKomunikaciju.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}