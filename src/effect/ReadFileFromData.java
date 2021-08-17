package effect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFileFromData {
	private Animation animationFrame;

	public ReadFileFromData() {
		super();
	}

	public ReadFileFromData(String filepath) {
		super();
		animationFrame = new Animation(100);
		try {
			LoadFrame(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void LoadFrame(String frameFile) throws IOException {

		FileReader fr = new FileReader(frameFile);
		BufferedReader br = new BufferedReader(fr);

		String line = null;
		if (br.readLine() == null) {
			System.out.println("No data");
		} else {

			fr = new FileReader(frameFile);
			br = new BufferedReader(fr);

			while ((line = br.readLine()).equals(""))
				;
			String[] str;
			str = line.split(" ");
			int n = Integer.parseInt(str[0]);
			for (int i = 0; i < n; i++) {

				while ((line = br.readLine()).equals(""))
					;

				while ((line = br.readLine()).equals(""))
					;
				str = line.split(" ");
				String path = str[1];

				animationFrame.addFrame(Resource.getResourceImage(path));
			}

		}

		br.close();
		fr.close();
	}

	public Animation getAnimation() {
		return animationFrame;
	}

	public int readBestScore(String path) {
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		try {
			fr = new FileReader(new File(path));
			br = new BufferedReader(fr);
			line = br.readLine();
			return Integer.parseInt(line);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public void writeBestScore(String path, String Score) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(new File(path));
			bw = new BufferedWriter(fw);
			bw.write(Score);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
