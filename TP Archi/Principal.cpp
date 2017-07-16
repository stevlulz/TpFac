
#include<iostream>
#include <stdio.h>
#include <string.h>
#include <malloc.h>
#include <stdlib.h>
#include <fstream>
#include <ctype.h> //TO UPPER ;)

using namespace std;

int pc=0;// Program Counter To Store Address Of Next Intruction That Should be Excuted ;) ;) ;)
typedef struct reg reg;
typedef struct reg //A Variable That Hold The Name Of Cpu Registers And Values Of Them (Alternative name + Value)
{
	char alt_name[4];			// Stores names Like R0,R1,R2,R3 ... ^^
	int val;                    // Stores Values Accept Byte Short and Integer Type Which Is Less Than 4Bytes
};
struct reg reg_file[5];        // Data Structure That Holds 5 Registers

void init_reg_file()           // Fucntion That Initiallize The Names Of The Previous Registers R0,R1,R2,R3,R4,R5
{                              //Note!!!!!!!!!! That function Should be Called in first of our programm in order to initiallize These Register !
	strcpy(reg_file[0].alt_name,"R0"); // We're Making Name For The First Register ^^^We continue doing That for all register
	strcpy(reg_file[1].alt_name,"R1");
	strcpy(reg_file[2].alt_name,"R2");
	strcpy(reg_file[3].alt_name,"R3");
	strcpy(reg_file[4].alt_name,"R4");
}

union RM{
	int rm1;
	char rm2[4];
	
};
bool is_char_same(char*x,char*y)//check if two Char are same
{
	return (!strcmp (x,y));
}
typedef struct inst inst;
typedef struct inst{
	char code_op[4];
	char operant1[4];
	char operant2[4];
};

struct inst reg_inst[10];

void init_inst_file(int i)
{
	ifstream file_("tp.txt");
	char code_op[4];//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	char operant1[4];
	char operant2[4];
	if(file_.is_open())
	{
	   int a=0;
		  while((file_ >> code_op >> operant1 >> operant2) && a<i)
		  {
		  	strcpy(reg_inst[a].code_op,code_op);
		  	strcpy(reg_inst[a].operant1,operant1);
		  	strcpy(reg_inst[a].operant2,operant2);
		  	a++;
		  }
		  file_.close();
	}
	else
	cout <<"could not find the file which hold the instructions";
	
}
//====================================================
  void print_state(int i)
  {
  	
  	cout<<pc<<"\t"<<reg_inst[i].code_op<<" "<<reg_inst[i].operant1<<" "                 //PC,RA,RM states....S
	    <<reg_inst[i].operant2<<"\t"<<reg_inst[i].code_op<<" "<<reg_inst[i].operant1   //PC,RA,RM states....
		<<" "<<reg_inst[i].operant2<<"\t"                                             //PC,RA,RM states...
		<<reg_file[0].val<<"\t"<<reg_file[1].val                                     //Register State ....
		<<"\t"<<reg_file[2].val<<"\t"<<reg_file[3].val<<"\t"<<reg_file[4].val<<endl;//Register State ....
  }
//===================================================
//==================================================================================================
 /* functions of all Operations would be right here ;) Down*/
      
//==================================================================================================
  void ADD(int src,int& dest)
  {
    int pc2=pc;
  	print_state(pc2);
  	pc++;
  	print_state(pc2);
  	dest +=src;
  	print_state(pc2);
  }
  void MOV(int src,int& dest)
  {
  	int pc2=pc;
  	print_state(pc2);
  	pc++;
  	print_state(pc2);
  	dest =src;
  	print_state(pc2);  	
  }
  void SUB(int src,int& dest)
  {
  	int pc2=pc;
  	print_state(pc2);
  	pc++;
  	print_state(pc2);
  	dest -=src;
  	print_state(pc2);
  	
  }
  void LOAD(int src,int& dest)
  {
  	int pc2=pc;
  	print_state(pc2);
  	pc++;
  	print_state(pc2);
  	dest =src;
  	print_state(pc2);
  	
  }
  
  void JMP(int adrs)
  {
  	int pc2=pc;
  	print_state(pc2);
  	print_state(pc2);
  	pc=adrs;
  	print_state(pc2);
  }
  void INC(int& a)
  {
  	int pc2=pc;
  	print_state(pc2);
  	print_state(pc2);
  	a++;
  	print_state(pc2);
  }
  void DEC(int& a)
  {
  	int pc2=pc;
  	print_state(pc2);
  	print_state(pc2);
  	a--;
  	print_state(pc2);
  }
  //======================================================================================
  bool is_digit(char*x)//check of Set of caracteres composed a Number (digit)
  {
  	
  	if(x[0])
  	return isdigit(x[0]);
  	else return 0;
  }
  int char_to_int(char*x)//Convert From char to int (it takes just first three indexes i doesn't care about rest if"111a"==>111
  {
  	if(is_digit(x) && x[1] )
  	return atoi(x);
  	else if(is_digit(x) && ! x[1])
  	return x[0]-48;
  	else
  	return -1;
  }
  int get_reg_index(char*x)// To Get The Index(address) Of Register
   {
	if(is_char_same("R0",x))
	return 0;
	else if(is_char_same("R1",x)) return 1;
	else if(is_char_same("R2",x)) return 2;
	else if(is_char_same("R3",x)) return 3;
	else if(is_char_same("R4",x)) return 4;
	else return -1;	
  }  

//===============================================================================================
              //Decoder ....Chose which instruction should be use ADD,SUB,JUMP,............. ^^
//===============================================================================================
int decode_inst(inst i)// To Get The Index(address) Of Register
{
	if(is_char_same("ADD",i.code_op))
	{ 
	   if(get_reg_index(i.operant1) !=-1 && get_reg_index(i.operant2) !=-1) // BOTH OF OPERANTS ARE REGISTER
		{
		   int a,b;
		   a=get_reg_index(i.operant1);
		   b=get_reg_index(i.operant2);
           ADD(reg_file[a].val,reg_file[b].val);
           return 1;
		}
		else if(char_to_int(i.operant1) !=-1 && get_reg_index(i.operant2) !=-1)// First operant is int and second is register
		{
			int a,b;
			a=char_to_int(i.operant1);
			b=get_reg_index(i.operant2);
			ADD(a,reg_file[b].val);	
			return 2;
		}
		else  return -1; // Instruction Error ....	
	}
	else if(is_char_same("JMP",i.code_op))
	 {
	 	if(char_to_int(i.operant1) != -1)
	 	 {
	 	 	int a=char_to_int(i.operant1);
	 	 	JMP(a);
	 	 	return 1; // ALLthings are going good ;)
		 }
		 else
		 return -1;//Error Check out your instruction 
	  } 
	else if(is_char_same("MOV",i.code_op)) 
	{
		if(get_reg_index(i.operant1) !=-1 && get_reg_index(i.operant2) !=-1) // BOTH OF OPERANTS ARE REGISTER
		{
		   int a,b;
		   a=get_reg_index(i.operant1);
		   b=get_reg_index(i.operant2);
           MOV(reg_file[a].val,reg_file[b].val);
           return 1;
		}
		else if(char_to_int(i.operant1) !=-1 && get_reg_index(i.operant2) !=-1)// First operant is int and second is register
		{
			int a,b;
			a=char_to_int(i.operant1);
			b=get_reg_index(i.operant2);
			MOV(a,reg_file[b].val);	
			return 2;
		}
		else  return -1; // Instruction Error ....
		
	}
	
	else if(is_char_same("SUB",i.code_op))
	{
		if(get_reg_index(i.operant1) !=-1 && get_reg_index(i.operant2) !=-1) // BOTH OF OPERANTS ARE REGISTER
		{
		   int a,b;
		   a=get_reg_index(i.operant1);
		   b=get_reg_index(i.operant2);
           SUB(reg_file[a].val,reg_file[b].val);
           return 1;
		}
		else if(char_to_int(i.operant1) !=-1 && get_reg_index(i.operant2) !=-1)// First operant is int and second is register
		{
			int a,b;
			a=char_to_int(i.operant1);
			b=get_reg_index(i.operant2);
			SUB(a,reg_file[b].val);	
			return 2;
		}
		else  return -1; // Instruction Error ....
		
	}
	

	//else if(is_char_same("MUL",i.code_op)) return 1;
	else return 0;
	
}
//=================================================================================================
                //Excute the program here
//=================================================================================================

void excute(int n)// This function should be called in main program, it'll be holding all sub-scripts ^^
{
	while(pc<n)
	{
		decode_inst(reg_inst[pc]);
		cout<<"==================="<<endl;	
	}
}
int main(int argc, char** argv) {
	init_reg_file();
    init_inst_file(4);
    excute(4);
   
   
    
   // ADD(  reg_file[1].val,  reg_file[1].val);
    //SUB(reg_file[1].val,  reg_file[2].val);
   // JUMP(0);
    
      
	
	return 0;
}
