package br.com.sisdb.vendas;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;



public class Algoritimos {

	
	public static void main(String[] args) {
		

		Scanner ler = new Scanner(System.in);
		
//		double a = 0, b = 0;
//		
//		System.out.println("Digite o prim num");
//		a = ler.nextDouble();
//   
//		System.out.println("Digite o segundo num");
//		b = ler.nextDouble();
//		
//		double somaA = (a % b);
//		double somaB = (b % a);
//		
//		if (a % b == 0  || b % a == 0) {
//			System.out.println("Ok, sao mutiplos :"+somaA+ " Soma de B:"+ somaB);
//		}else {
//			System.out.println("nao são multiplos "+somaA+ " Soma de B:"+ somaB);
//		}
		
		
		Integer a = 10516;
		Integer b = 512;
		int c = 0;
		int aux = 0;
		
		String aStr = a.toString(), bStr = b.toString(), cStr = "";
		
		
		if (a > b) {
			
			for ( int i = 0; i < a.toString().length(); i ++  ) {
				
				
//				if (i == 1) {
//				   cStr = (a.toString().substring(0,i)+""+(b.toString().substring(0,i)))    ;
//                System.out.println("1 Valor c:"+cStr);			
//				}
				
				if (i == 1) {
					   
//					   cStr += (a.toString().substring(0,1));
//					   cStr += (b.toString().substring(0,1));
			//		   cStr += (a.toString().substring(0,1));
//					   cStr += (a.toString().substring(2,a.toString().length()));
					   System.out.println(" Valor c:"+cStr);
				}
			}
			
			
		}
		cStr = (aStr.substring(0,1));
		cStr +=  (bStr.substring(0,1));
		 cStr +=  (aStr.substring(1,2));
		 cStr +=  (bStr.substring(1,2));
		 cStr += (aStr.substring(2, aStr.length()));
	    
		 c = Integer.parseInt(cStr);
	    System.out.println("2  Valor de c: "+c);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
