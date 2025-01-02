package quiz;

import java.util.Scanner;

//Candidate class ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Candidate {
	String name;
	String address;
	long phoneNumber;
	Scanner sc = new Scanner(System.in);

	void collectDetails() {
		System.out.print("Enter your name: ");
		name = sc.nextLine();
		System.out.print("Enter your address: ");
		address = sc.nextLine();
		System.out.print("Enter your phone number: ");
		phoneNumber = sc.nextLong();
	}

	void displayCongrats() {
		System.out.println("Congrats " + name + "! This is the correct option.");
	}

	void displayEncouragement() {
		System.out.println("Sorry " + name + "! That is not the correct option.");
	}
}

//Question class ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Question {
	String questionText;
	String correctOption;
	int prizeAmount;
	String[] options;
	int[] percentages;

	public Question(String questionText, String correctOption, int prizeAmount, String[] options, int[] percentages) {
		this.questionText = questionText;
		this.correctOption = correctOption;
		this.prizeAmount = prizeAmount;
		this.options = options;
		this.percentages = percentages;
	}

	boolean isAnswerCorrect(String selectedOption, Candidate candidate) {
		if (selectedOption.equals(correctOption)) {
			return true;
		} else {
			candidate.displayEncouragement();
			return false;
		}
	}

	void display50_50Lifeline(Scanner sc, Candidate candidate, int[] prizeAmount) {
	    System.out.println("You chose the 50-50 lifeline.");

	    // Find the index of the correct option
	    int correctIndex = -1;
	    for (int i = 0; i < options.length; i++) {
	        if (options[i].equals(correctOption)) {
	            correctIndex = i; 
	            break;
	        }
	    }

	    // Randomly select one incorrect option
	    String incorrectOption = null;
	    while (incorrectOption == null || incorrectOption.equals(correctOption)) {
	        int randomIndex = (int) (Math.random() * options.length);
	        if (!options[randomIndex].equals(correctOption) && !options[randomIndex].equals("Lifeline")) {
	            incorrectOption = options[randomIndex];
	        }
	    }

	    // Create an array to hold the two options
	    String[] selectedOptions = new String[]{correctOption, incorrectOption};

	    // Shuffle the options
	    if (Math.random() < 0.5) {
	        // Swap if needed for randomization
	        String temp = selectedOptions[0];
	        selectedOptions[0] = selectedOptions[1];
	        selectedOptions[1] = temp;
	    }

	    // Display the options
	    System.out.println("Option 1: " + selectedOptions[0]);
	    System.out.println("Option 2: " + selectedOptions[1]);

	    // Prompt the user to choose between these two options only
	    int chosenOption = ConductQuiz.promptForValidOption(sc, 1, 2);

	    // Check the user's choice against the correct option
	    String chosenAnswer = selectedOptions[chosenOption - 1]; // Get the selected option based on user input
	    if (chosenAnswer.equals(correctOption)) {
	        candidate.displayCongrats();
	        System.out.println("You've won: " + prizeAmount[0]); // Display the prize amount
	    } else {
	        candidate.displayEncouragement();
	        System.out.println("You lost! Total prize: " + prizeAmount[0]); // Display the total prize amount
	        System.exit(0);
	    }
	}

	void displayAudiencePoll() {
		System.out.println("You chose the Audience Poll lifeline.");
		System.out.println("Option 1: " + options[0] + " - " + (percentages[0]) + "%");
		System.out.println("Option 2: " + options[1] + " - " + (percentages[1]) + "%");
		System.out.println("Option 3: " + options[2] + " - " + (percentages[2]) + "%");
		System.out.println("Option 4: " + options[3] + " - " + (percentages[3]) + "%");
	}
}

//Main class ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public class ConductQuiz {
	static int MAX_LIFELINE_USES = 2;
	static boolean used50_50 = false;
	static boolean usedAudiencePoll = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Candidate candidate = new Candidate();
		candidate.collectDetails();

		System.out.println("\n---------------RULES---------------");
		System.out.println("1. Only pick options from the given choices.");
		System.out.println("2. You can use each lifeline only once.");
		System.out.println("3. Lifelines available:");
		System.out.println("   3.1 50-50 - Shows one correct and one incorrect option.");
		System.out.println("   3.2 Audience Poll - Shows percentage of audience support for each option.");
		System.out.println();
		System.out.println(
				"(Questions are collected from - https://blog.livereacting.com/100-fun-general-knowledge-quiz-questions/)");

		Question[] questions = new Question[] {
				new Question(
	            		"The medical term for white blood cells is:",
	            		"Leukocytes", 
	            		10000,
	            		new String[]{"Erythrocytes", "Thrombocytes", "Leukocytes", "Phagocytes", "Lifeline"},
	            		new int[] {21, 45, 19, 15}
	            		),
	            new Question(
	            		"How many bones are there in the adult human body?", 
	            		"206", 
	            		20000, 
	            		new String[]{"186", "206", "226", "246", "Lifeline"}, 
	            		new int[] {4, 48, 4, 44}
	            		),
	            new Question(
	            		"What does the Yoga term ‘Pranayama’ refer to?",
	            		"Breathing exercises", 
	            		30000,
	            		new String[]{"Physical postures", "Breathing exercises", "Meditation techniques", "Mindfulness", "Lifeline"},
	            		new int[] {31, 53, 14, 2}
	            		),
	            new Question(
	            		"Which spice is known as \"queen of spices\"?",
	            		"Cardamom", 
	            		40000,
	            		new String[]{"Cinnamon", "Cardamom", "Nutmeg", "Black pepper", "Lifeline"},
	            		new int[] {40, 8, 31, 21}
	            		),
	            new Question(
	            		"What part of the brain is responsible for memory and learning?",
	            		"Hippocampus", 
	            		50000,
	            		new String[]{"Cerebellum", "Hippocampus", "Hypothalamus", "Medulla oblongata", "Lifeline"},
	            		new int[] {17, 63, 7, 13}
	            		),
	            new Question(
	            		"What parent company owns both Instagram and WhatsApp?",
	            		"Facebook (now Meta)", 
	            		60000,
	            		new String[]{"Facebook (now Meta)", "Google", "Apple", "Amazon", "Lifeline"},
	            		new int[] {10, 3, 39, 48}
	            		),
	            new Question(
	            		"What is the main function of red blood cells?",
	            		"Carrying oxygen", 
	            		70000,
	            		new String[]{"Carrying oxygen", "Fighting infections", "Clotting blood", "Regulating body temperature", "Lifeline"},
	            		new int[] {35, 15, 49, 1}
	            		),
	            new Question(
	            		"What is the medical term for high blood pressure?",
	            		"Hypertension", 
	            		80000,
	            		new String[]{"Hypotension", "Hypertension", "Hyperglycemia", "Acidosis", "Lifeline"},
	            		new int[] {16, 4, 63, 17}
	            		),
	            new Question(
	            		"Which human bone is the longest and strongest?",
	            		"Femur", 
	            		90000,
	            		new String[]{"Tibia", "Femur", "Humerus", "Radius", "Lifeline"},
	            		new int[] {21, 59, 8, 12}
	            		),
	            new Question(
	            		"What is the normal range for human body temperature in Celsius?",
	            		"36-37", 
	            		100000,
	            		new String[]{"34-35", "35-36", "36-37", "37-38", "Lifeline"},
	            		new int[] {10, 13, 29, 48}
	            		)
	        };

		int totalPrize = 0;
		int lifelineUses = 0;

		for (Question question : questions) {
		    System.out.println("\nQuestion: " + question.questionText);
		    int maxOption = lifelineUses < MAX_LIFELINE_USES ? 5 : 4;
		    for (int i = 0; i < maxOption; i++) {
		        System.out.println((i + 1) + " - " + question.options[i]);
		    }

		    int chosenOption = promptForValidOption(sc, 1, maxOption);
		    String selectedOption = question.options[chosenOption - 1];

		    if (selectedOption.equals("Lifeline") && lifelineUses < MAX_LIFELINE_USES) {
		        System.out.println("You selected Lifeline. Choose an option:");
		        boolean canChoose50_50 = !used50_50;
		        boolean canChooseAudiencePoll = !usedAudiencePoll;

		        if (canChoose50_50) {
		            System.out.println("1: 50-50");
		        }
		        if (canChooseAudiencePoll) {
		            System.out.println("2: Audience Poll");
		        }

		        int lifelineChoice = promptForValidOption(sc,
		                canChoose50_50 && canChooseAudiencePoll ? 1 : canChoose50_50 ? 1 : 2,
		                canChooseAudiencePoll ? 2 : 1);

		        if (lifelineChoice == 1 && canChoose50_50) {
		            question.display50_50Lifeline(sc, candidate, new int[]{question.prizeAmount});
		            used50_50 = true;
		            lifelineUses++;
		            continue; 
		        } else if (lifelineChoice == 2 && canChooseAudiencePoll) {
		            question.displayAudiencePoll();
		            usedAudiencePoll = true;
		            lifelineUses++;
		            
		            // After using the Audience Poll, the user has to choose again
		            chosenOption = promptForValidOption(sc, 1, 4); 
		            selectedOption = question.options[chosenOption - 1]; // Map to the correct option
		        }
		    }

		    // Check if the answer is correct
		    if (question.isAnswerCorrect(selectedOption, candidate)) {
		        candidate.displayCongrats();
		        totalPrize = question.prizeAmount;
		        System.out.println("You've won: " + totalPrize);
		    } else {
		        System.out.println("You lost! Total prize: " + totalPrize);
		        break;
		    }

		    if (lifelineUses == MAX_LIFELINE_USES) {
		        System.out.println("Lifelines have been fully used. Proceeding without further lifelines.");
		    }
		}

		System.out.println("Thank you for playing, " + candidate.name + "! Your total prize is: " + totalPrize);
		sc.close();
	}

	static int promptForValidOption(Scanner sc, int minOption, int maxOption) {
		int chosenOption;
		while (true) {
			if(minOption == maxOption) {
				System.out.print("Select your option number("+ minOption +"): ");
			}else {				
				System.out.print("Enter your option number (" + minOption + "-" + maxOption + "): ");
			}
			if (sc.hasNextInt()) {
				chosenOption = sc.nextInt();
				if (chosenOption >= minOption && chosenOption <= maxOption) {
					break;
				}
			} else {
				sc.next(); // clear invalid input
			}
			System.out
					.println("Invalid option. Please enter a number between " + minOption + " and " + maxOption + ".");
		}
		return chosenOption;
	}
}
