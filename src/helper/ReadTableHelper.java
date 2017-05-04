package helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Manager.ManageExcel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadTableHelper {

	String fieldsString;
	String targetsString;
	String targetTable;
	File inXLS;
	Workbook readingWB;
	
    //Leggo la tabella
    public ReadTableHelper(){
    	targetTable=new String();
		
		fieldsString = new String();
		targetsString = new String();
		
		inXLS = new File(".\\resources\\input.xls");
		
		//Legge la tabella xls e la mette nel Workbook readingWB
		try {
			readingWB = Workbook.getWorkbook(inXLS);
			//Lo metto solo per capire se effettivamente legge
			System.out.println("---Valori TABELLA INPUT XLS letti---");
		} catch (BiffException | IOException e) {
			e.printStackTrace();
			System.out.println("ReadTableHelper ERROR");
		}
		
    }
    
    //UTILITY
    //MOSTRANO INFORMAZIONI SULLA TABELLA
    public void addTable(String tableName)//NOME DEL FOGLIO DI CALCOLO
	{
		targetTable=tableName;
	}
    public void addFields(String fields)//CAMPI DA VISUALIZZARE
    {
    	fieldsString=fields;
    }
	public void addTargets(String targets)//CONDIZIONI DI VISUALIZZAZIONE
	{
		targetsString = targets;
	}
	
//Stampo i valori letti dalla query del file.txt
	public void printName()
	{
		System.out.println("\n---Valori QUERY TXT---");
		System.out.println("Tabella: "+targetTable);
		System.out.println("Campi: "+fieldsString);
		System.out.println("Target: "+targetsString);
	}

	//---METODO PRINCIPALE---
	public void readTable(){
		
		Sheet sheet = readingWB.getSheet(targetTable.replaceAll("`", ""));
		if(sheet == null)
			System.out.println("\n -->ERRORE! La tabella "+ targetTable + " non esiste");
		else{
			ArrayList<Integer> columnValues = new ArrayList<Integer>();
			ArrayList<Integer> rowValues = new ArrayList<Integer>();
			ArrayList<String> ComlumnHeader = new ArrayList<String>();//contine gli hedaer delle colonne selezionate con SELECT
			
			Integer maxColumns = sheet.getColumns();
//			
			//CONTROLLO SE LA SELECT HA LA *
			if(fieldsString.replaceAll("`", "").equals("*")){
				//Se è così prendo tutte le colonne
				for(int k=0;k<maxColumns;k++){
					columnValues.add(k);
					ComlumnHeader.add(sheet.getCell(k, 0).getContents().replaceAll("`", ""));
				}
			}
			else{
				String [] field = fieldsString.split("\\-");
				for(String f : field){
					boolean headerFound=false;
					//ricerco nell'intestazione (riga 0) se qualcuna delle colonne si chiama come il campo
					for(int i=0;i<maxColumns;i++)
					{
						Cell header = sheet.getCell(i, 0);//vario la colonna, tengo fissa la prima riga (HEADER, intestazione)
						if(header.getContents().equals(f.replaceAll("`", "")))
						{
							headerFound=true;
							columnValues.add(i);
							ComlumnHeader.add(f.replaceAll("`", ""));
							break;
						}
					}
					if(!headerFound)
					{
						System.out.println("\n -->ERRORE! Il campo " + f+ " non esiste!");
						return;
					}
				}//chiusura foreach field 
			}//chiusura else fieldString == *
			
			ArrayList<String> WHEREHeader = new ArrayList<String>();//contiene gli header di WHERE
			ArrayList<String> WHEREValue = new ArrayList<String>();//contiene i valori di confronto 
			//PARTE DI ELABORAZIONE DELLA STRINGA DI TARGET
			String[] conditions = targetsString.split("\\|");
			ArrayList<String> compareType = new ArrayList<String>();
			ArrayList<String> clausolaType = new ArrayList<String>();
			for(String c : conditions)
			{	String[] target = c.split("\\-");
				String[] cmp = target[1].split("\\]");
				compareType.add(cmp[0]);
				
				String[] clausola = cmp[1].split("\\[");
				if(clausola.length>1){//se ho qualche clausola AND o OR
					clausolaType.add(clausola[1]);
					target[1] = clausola[0];
				}else{
					clausolaType.add("none");
					target[1] = cmp[1];
				}
				
				WHEREHeader.add(target[0].replaceAll("`", ""));
				WHEREValue.add(target[1].replaceAll("'", ""));
				
				//ORA CERCO EFFETTIVAMENTE I VALORI	
				for(int i=0;i<maxColumns;i++)
				{	Cell intCell = sheet.getCell(i, 0);
					//Scorro l'intestazione per cercare le colonne da controllare, ossia cerco nell'intestazione il NOME del campo
					if(intCell.getContents().equals(target[0].replaceAll("`", "")))
					{
						//Trovate le colonne scorro le righe
						Integer maxRows = sheet.getRows();
						for(int j=1;j<maxRows;j++)
						{
							switch (cmp[0]) {
							case "=":
								if(sheet.getCell(i, j).getContents().compareTo(target[1].replaceAll("'", ""))==0){
									rowValues.add(j);
								}
								break;
							case "<":
								if(sheet.getCell(i, j).getContents().compareTo(target[1].replaceAll("'", ""))<0){
									rowValues.add(j);
								}
								break;
							case ">":
								if(sheet.getCell(i, j).getContents().compareTo(target[1].replaceAll("'", ""))>0){
									rowValues.add(j);
								}
								break;

							default:
								System.out.println("\n----NONE---");
								break;
							}
						}
					}
					
				}//fine FOR di ricerca righe bersaglio
			}
			//A questo punto ho dal primo controllo sui campi le colonne che devo mostrare
			//e dal secondo controllo le righe che devono rispettano le condizioni del WHERE
			//Devo unire queste 2 informazioni per mostrare solo le colonne che mi interessano
			//delle righe che rispettano le condizioni del where
			System.out.println("\n---Riepilogo dati---");
			System.out.println("Colonne SELECT: " + ComlumnHeader);
			System.out.println("Colonne WHERE: " + WHEREHeader);
			System.out.println("Valori di confronto WHERE: " + WHEREValue);
			checkResults(rowValues, columnValues, clausolaType, ComlumnHeader);
			}//Chiusura else Sheet==null
	}
//////////////////QUI TROVIAMO SALVO NEGLI ARRAY I DATI CHE POI DOVRO' SCRIVERE SUL FILE!//////////////////////////////////////////////////   
private void checkResults(ArrayList<Integer> rowV, ArrayList<Integer> colV,ArrayList<String> condV, ArrayList<String> Heads) 
{	
		ArrayList<ArrayList<String>> RESULTselect = new ArrayList<ArrayList<String>>();//QUI HO L'OUTPUT CON LE RIGHE FILTRATE!

		Sheet resultSheet = readingWB.getSheet(targetTable.replaceAll("`", ""));
		if(resultSheet == null)
			System.out.println("\n-->ERRORE! La tabella "+ targetTable + " non esiste");
		else{
			for(Integer j : rowV){
				ArrayList<String> TARGETRow = new ArrayList<String>();//qui salvo una ad una le righe che mi occorono
				for(Integer i : colV){
					Cell resultCell = resultSheet.getCell(i, j);
					TARGETRow.add(resultCell.getContents().toString());
				}
				RESULTselect.add(TARGETRow);
			}
		}
		checkDuplicate(RESULTselect);
		checkDuplicate(RESULTselect);
		System.out.println("Righe SELECT: " + RESULTselect);
////////////SCRIVO L'OUTPUT SU DI UNA TABELLA/////////////////////////////////////
		ManageExcel m = new ManageExcel(RESULTselect,Heads);
	}

private void checkDuplicate(ArrayList<ArrayList<String>> r){
	for(int i=0; i<r.size();i++){
		ArrayList<String> tmp = r.get(i);
		for(int k=i+1;k<r.size();k++){
			if(r.get(k).get(0).equals(tmp.get(0))){
				r.remove(k);
			}
		}		
	}
}

}
