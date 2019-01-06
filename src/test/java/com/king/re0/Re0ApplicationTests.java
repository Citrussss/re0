package com.king.re0;

import org.junit.Test;
import reactor.core.publisher.Flux;


public class Re0ApplicationTests {

	@Test
	public void contextLoads() {
		Flux.just("123").subscribe(System.out::println);
	}

	@Test
	public void decodeAtIndex() {
        String S;
        int K;
        S = "ajx37nyx97niysdrzice4petvcvmcgqn282zicpbx6okybw93vhk782unctdbgmcj";
//                "82unctdbgmcjmbqn25rorktmu5ig2qn2y4xagtru2nehmsp";
        K = 976159153;
        for (int i = 1; i <= 64; i++) {
            System.out.print(fun(S.toCharArray(),0,0,i,0));
        }
//	   System.out.print(fun(S.toCharArray(),0,0,K,0)+
//               fun(S.toCharArray(),0,0,K,0));
	}
	private char fun(char[] chars,int start,long lenth,long target,int cycle){
//	    System.out.println("target"+target);
        for (int i = start; i < chars.length; i++) {
            char c = chars[i];
           if(isShuzi(c)){
               long newLenth = (lenth + i - start) * (c-48);
                if(newLenth<target){
//                    System.out.println(newLenth);
                    return fun(chars,i+1,newLenth,target,cycle+1);
                }else if(target%(lenth + i - start)!=0)return fun(chars,0,0,target%(lenth + i - start),0);
                else return returnChar(chars,i);
            }else if(i+lenth-start+1==target)return returnChar(chars,i);
        }
        return '1';
    }
    private boolean isShuzi(char c){
        return c<='9'&&c>='0';
    }
    private char returnChar(char[] chars,int position){
	    return isShuzi(chars[position])?returnChar(chars,position-1):chars[position];
    };
}
