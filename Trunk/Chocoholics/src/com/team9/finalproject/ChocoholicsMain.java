package com.team9.finalproject;

import java.util.Scanner;

import com.team9.finalproject.connectors.InteractiveConnector;
import com.team9.finalproject.connectors.ProviderConnector;

public class ChocoholicsMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter I for interactive mode, or P for provider mode");
		switch(scan.next().toUpperCase().charAt(0))
		{
			case 'I':
				new InteractiveConnector();
				break;
			case 'P':
				new ProviderConnector();
				break;
		}
		scan.close();

	}

}
