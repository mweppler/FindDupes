package info.mattweppler.finddupes;

import info.mattweppler.fileutils.FileUtils;

import java.util.ArrayList;

public class FindDupes
{
	private ArrayList<String[]> tokens;
	private Options options;
	private String[] arguments;
	
	private void continueApp()
	{
		int counter=0;
		StringBuilder stringForLogger = new StringBuilder();
		for (String[] token : tokens) {
			++counter;
			if (token.length>=options.getUniqueField()) {
				if (!token[options.getUniqueField()].equals("")) {
					String temp = null;
					if (options.getFieldType()==1) {
						try {
							int number = Integer.parseInt(token[options.getUniqueField()]);
							temp = String.valueOf(number);
							stringForLogger = stringForLogger.append(findDupe(tokens, options.getUniqueField(),temp, counter));
						} catch (NumberFormatException nfe) {
							//nfe.printStackTrace();
							// If the field does not contain a number, skip it.
						}
					} else {
						temp = token[options.getUniqueField()];
						stringForLogger = stringForLogger.append(findDupe(tokens, options.getUniqueField(),temp, counter));
					}
				}
			}
		}
		Logger logger = new Logger();
		try {
			logger.displayLog(stringForLogger.toString());
		} catch (OutOfMemoryError oome) {
			//oome.printStackTrace();
			logger.displayLog("Great Scott! So Many Dupes you ran out of memory! Fix it Man!");
		}
	}
	
	private void setUpApplication()
	{
		tokens = null;
		try {
			tokens = FileUtils.retrieveArrayListOfStringArraysFromFile(FileUtils.openFileDialog());
		} catch (NullPointerException npe) {
			//npe.printStackTrace();
			System.out.println("No File Selected.");
			System.exit(1);
		}
		options = new Options(arguments, tokens.get(0));
		options.addCustomListener(new ContinueListener());
	}
	
	public static String findDupe(ArrayList<String[]> list, int row, String search, int lineToSkip)
	{
		int counter=0;
		StringBuilder strBuilder = new StringBuilder();
		for (String[] token : list) {
			++counter;
			if (token.length>=row) {
				if (search.equals(token[row]) && counter!=lineToSkip) {
					//System.out.println("Search:"+search+" on line:"+lineToSkip+" - Found Dupe:"+token[row]+" on line:"+counter);
					strBuilder = strBuilder.append("Search:"+search+" on line:"+lineToSkip+" - Found Dupe:"+token[row]+" on line:"+counter+"\n");
				}
			}
		}
		return strBuilder.toString();
	}
	
	public static void main(String[] args)
	{
		FindDupes fd = new FindDupes();
		fd.arguments = args;
		fd.setUpApplication();
	}
	
	public class ContinueListener implements CustomListener
	{
		@Override
		public void customEventOccurred(CustomEvent event)
		{
			continueApp();
		}
	}
}
