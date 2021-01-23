


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.amazonaws.util.IOUtils;

public class FlightSeating {	  

public static void allocateSeats(int[][] seats,int passengerCount) {
	int currentGroup = 0;
	int colCount = 0;
	int currentSeatType = 0;
	int allocatedPassenger = 0;
	List<int[][]> seatsGroup = new  ArrayList<>();
	for(int index = 0;index<seats.length;index++) {
		int[] seatsTypeGroup = seats[index];
		seatsGroup.add(new int[seatsTypeGroup[0]][seatsTypeGroup[1]]);
	}
	while(passengerCount >allocatedPassenger) {
		int[][] currentGroupArray = seatsGroup.get(currentGroup);
		if(currentGroupArray[0].length >= colCount) {
			int rowCount = currentGroupArray.length;
			if(currentGroup == 0 || currentGroup== seatsGroup.size()-1) {
				if(rowCount >1) {
					if(currentSeatType ==0)
						currentGroupArray[colCount][rowCount-1] = allocatedPassenger++;
					else if(currentSeatType == 1)
						for(int i =1;i<rowCount-2;i++)
							currentGroupArray[colCount][i] = allocatedPassenger++;
				}
						currentGroupArray[colCount][currentGroup == 0 ? 0:rowCount-1] = allocatedPassenger++;
			}else {
				if(currentSeatType == 0) {
					currentGroupArray[colCount][0] = allocatedPassenger++;
					if(rowCount >1)
						currentGroupArray[colCount][rowCount-1] = allocatedPassenger++;
				}
				if(currentSeatType ==1)
					for(int i =1;i<rowCount-2;i++)
						currentGroupArray[colCount][i] = allocatedPassenger++;
			}
		}
		currentGroup++;
		if(currentGroup == seatsGroup.size() -1) {
			if(colCount == currentGroupArray[0].length)
				colCount = 0;
			colCount++;
			currentGroup = 0;
		}
	}
File writeFile = new File("D://test");
StringBuilder content = new StringBuilder ();
for(int index = 0;index<seatsGroup.size();index++) {
	int[][] currentGroupArray = seatsGroup.get(index);
	for(int i = 0;i<currentGroupArray.length;i++) {
		for(int j=0;j<currentGroupArray[i].length;j++) {
			content.append(currentGroupArray[i][j]);	
			
		}
	}
}
InputStream responseTextStream =  new ByteArrayInputStream(content.toString().getBytes(StandardCharsets.UTF_8));
try {
	FileUtils.writeByteArrayToFile(writeFile,IOUtils.toByteArray(responseTextStream));
} catch (IOException e) {
System.out.print("error on write File");
}
}

public static void main(String args[]) {
System.out.println("Enter Passenger No Of Passenger Count ");
Scanner sc= new Scanner(System.in);
int count= sc.nextInt();
System.out.println("Enter Flight Seat Position Data");
int[][] seats = new int[4][2];
seats[0][0] = 3;
seats[0][0] = 4;

seats[0][0] = 2;
seats[0][0] = 3;

seats[0][0] = 4;
seats[0][0] = 1;

seats[0][0] = 3;
seats[0][0] = 5;
allocateSeats(seats, count);
}
}