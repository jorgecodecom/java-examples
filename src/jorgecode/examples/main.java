package jorgecode.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Optional;

public class main {

	public static final List<String> NAMES = Arrays.asList("John", "Mary", "Alice", "Martha", "Geoge", "Isabel");

	public static String pickNameOldWay(final List<String> names, final String startingLetter) {
		String pickedName = null;
		for (String name : names) {
			if (name.startsWith(startingLetter)) {
				pickedName = name;
				break;
			}
		}
		return pickedName != null ? pickedName : "There is no name starting with letter '" + startingLetter + "'.";
	}

	public static String pickNameWithIsPresent(final List<String> names, final String startingLetter) {
		final Optional<String> pickedName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
		return pickedName.isPresent() ? pickedName.get()
				: "There is no name starting with letter '" + startingLetter + "'.";
	}

	public static String pickNameWithOrElse(final List<String> names, final String startingLetter) {
		final Optional<String> pickedName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
		return pickedName.orElse("There is no name starting with letter '" + startingLetter + "'.");

	}

	public static void pickNameCallingMethodOldWay(final List<String> names, final String startingLetter) {
		String pickedName = null;
		for (String name : names) {
			if (name.startsWith(startingLetter)) {
				pickedName = name;
				break;
			}
		}
		if (pickedName != null) {
			printMessage(pickedName);
		}
	}

	public static void printMessage(final String name) {
		System.out.println(String.format("First name with '" + name.substring(0, 1) + "' is %s", name));
	}

	public static void pickNameCallingMethodNewWay(final List<String> names, final String startingLetter) {
		final Optional<String> pickName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
		pickName.ifPresent(main::printMessage);
	}

	public static String pickNameThrowExceptionOldWay(final List<String> names, final String startingLetter) {
		String pickedName = null;
		for (String name : names) {
			if (name.startsWith(startingLetter)) {
				pickedName = name;
				break;
			}
		}
		if (pickedName == null) {
			throw new NotFoundException("There is no name starting with letter '" + startingLetter + "'.");
		}
		return pickedName;
	}

	public static String pickNameThrowExceptionNewWay(final List<String> names, final String startingLetter) {
		final Optional<String> pickedName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
		return pickedName.orElseThrow(() -> new NotFoundException("There is no name starting with letter '" + startingLetter + "'."));
	}

	public static void main(String[] args) {
		Collections.sort(NAMES);

		System.out.println("jorgecode.com");
		System.out.println("=============");

		System.out.println("Handling NULLs with Java 8 Optional");
		System.out.println("-----------------------------------");
		System.out.println("NAMES values: " + NAMES.toString() + "\n");

		System.out.println("pickNameOldWay(NAMES, \"M\");");
		System.out.println("First name with 'M' is : " + pickNameOldWay(NAMES, "M"));
		System.out.println("pickNameOldWay(NAMES, \"Z\");");
		System.out.println("First name with 'Z' is : " + pickNameOldWay(NAMES, "Z"));
		System.out.println("");

		System.out.println("pickNameWithIsPresent(NAMES, \"M\");");
		System.out.println("First name with 'M' is : " + pickNameWithIsPresent(NAMES, "M"));
		System.out.println("pickNameWithIsPresent(NAMES, \"Z\");");
		System.out.println("First name with 'Z' is : " + pickNameWithIsPresent(NAMES, "Z"));
		System.out.println("");

		System.out.println("pickNameWithOrElse(NAMES, \"M\");");
		System.out.println("First name with 'M' is : " + pickNameWithOrElse(NAMES, "M"));
		System.out.println("pickNameWithOrElse(NAMES, \"Z\");");
		System.out.println("First name with 'Z' is : " + pickNameWithOrElse(NAMES, "Z"));
		System.out.println("");

		System.out.println("pickNameCallingMethodOldWay(NAMES, \"M\");");
		pickNameCallingMethodOldWay(NAMES, "M");
		System.out.println("pickNameCallingMethodOldWay(NAMES, \"Z\");");
		pickNameCallingMethodOldWay(NAMES, "Z");
		System.out.println();

		System.out.println("pickNameCallingMethodNewWay(NAMES, \"M\");");
		pickNameCallingMethodNewWay(NAMES, "M");
		System.out.println("pickNameCallingMethodNewWay(NAMES, \"Z\");");
		pickNameCallingMethodNewWay(NAMES, "Z");
		System.out.println();

		try {
			System.out.println("pickNameThrowExceptionOldWay(NAMES, \"M\");");
			System.out.println(pickNameThrowExceptionOldWay(NAMES, "M"));
			System.out.println("pickNameThrowExceptionOldWay(NAMES, \"Z\");");
			System.out.println(pickNameThrowExceptionOldWay(NAMES, "Z"));
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println();
		
		try {
			System.out.println("pickNameThrowExceptionNewWay(NAMES, \"M\");");
			System.out.println(pickNameThrowExceptionNewWay(NAMES, "M"));
			System.out.println("pickNameThrowExceptionNewWay(NAMES, \"Z\");");
			System.out.println(pickNameThrowExceptionNewWay(NAMES, "Z"));
		}catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("\nThe End");
	}

}

class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}