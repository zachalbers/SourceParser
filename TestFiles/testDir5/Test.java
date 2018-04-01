package package5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Test {

	private static final String FILENAME = "someDirectoryName";



	public static void main(String[] args) throws NullPointerException{

		if (args.length > 2 ) throw new IllegalArgumentException("Incorrect number of arguments");
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
