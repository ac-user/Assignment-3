package EncryptionMachine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EncryptionMachineTests {
	    
	private String getPromptMessageToVerify(ByteArrayOutputStream outputStream, String substring) {
		String output = outputStream.toString();
        var outputResponses = output.splitWithDelimiters("\n", 0);
        int indexOfMessage = Arrays.asList(outputResponses).indexOf(
        		Arrays.stream(outputResponses)
                .filter(message -> message.contains(substring) && !message.contains("Welcome"))
                .findFirst()
                .orElse(null));	
        return outputResponses[indexOfMessage];
	}
	


	@Test
	@DisplayName("Verify Assumption: Can call EncriptionMachine as new() class")
	@org.junit.jupiter.api.Order(1)
	void encrypt_VerifyAssumption_RunTestsByCreatingNew() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input 
        String input = "csci\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine encrypt = new EncriptionMachine();
        encrypt.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String output = outputStream.toString();       
                
        // validate results
        assertTrue(output.contains("Welcome"));
	}

	@Test
	@DisplayName("Verify Assumption: Can call EncriptionMachine as static method")
	@org.junit.jupiter.api.Order(2)
	void encrypt_VerifyAssumption_RunTestsStatically() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input
        String input = "csci\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String output = outputStream.toString();

        // Validate results
        assertTrue(output.contains("fvfl")); //forwards 3
        assertFalse(output.contains("zpzf")); //backwards 3
	}
	
	@Test
	@DisplayName("Verify Assumption: Default shift is three ahead")
	@org.junit.jupiter.api.Order(3)
	void encrypt_VerifyAssumption_EncryptedByMovingThree() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input
        String input = "csci\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String output = outputStream.toString();

        // Validate results
        assertTrue(output.contains("fvfl")); //forwards 3
        assertFalse(output.contains("zpzf")); //backwards 3
	}
	
	@Test
	@DisplayName("Verify Assumption: Default lowercase letters only")
	@org.junit.jupiter.api.Order(4)
	void encrypt_VerifyAssumption_LowerCaseLettersOnlyByDeafult() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input
        String input = "cSCi\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String output = outputStream.toString();

        // Validate results
        assertFalse(output.contains("fvfl"));
        assertFalse(output.contains("fVFl"));
        
	}
	
	
	@Test
	@DisplayName("Inroduction message")
	@org.junit.jupiter.api.Order(5)
	void encrypt_NewSession_IntroMessages() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input for stopping right at initial prompt
        String input = "csci\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String output = outputStream.toString();

        // Validate results
        assertTrue(output.contains("Welcome"));
        
	}

	@Test
	@DisplayName("First prompt for key")
	@org.junit.jupiter.api.Order(6)
	void encrypt_NewSession_KeyPrompt() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input
        String input = "csci\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String output = outputStream.toString();

        // Validate results
        assertTrue(output.contains("key"));
        
	}
		
	@ParameterizedTest
    @CsvSource({
        "csci, fvfl",
    	"csci , fvfl ",
    })
	@DisplayName("Key is encrypted")
	@org.junit.jupiter.api.Order(7)
	void encrypt_KeyPrompt_EncryptedKey(String word, String expectedEncryption) { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input 
        String input = word + "\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output        
        String promptResponse =  getPromptMessageToVerify(outputStream, word);
		
        // Validate results
        assertTrue(promptResponse.contains(expectedEncryption));
        
	}
		
	
	@ParameterizedTest
    @CsvSource({
        "play, sodb",
    	"thee, wkhh",
    	"quick, txlfn",
    	"brown, eurzq",
    	"fox, ira",
    	"jumps, mxpsv",
    	"over, ryhu",
        "the, wkh",
        "lazy, odcb",
        "dog, grj"
    })
	@DisplayName("Message is encrypted")
	@org.junit.jupiter.api.Order(8)
	void encrypt_MessagePrompt_EncryptedMessage(String word, String expectedEncryption) { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input 
        String input = "csci\n1\n"+ word +"\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String promptResponse =  getPromptMessageToVerify(outputStream, word);

        // Validate results
        assertTrue(promptResponse.contains(expectedEncryption));
	}

	@Test
	@DisplayName("Multiple messages are encrypted")
	@org.junit.jupiter.api.Order(9)
	void encrypt_MultipleMessagePrompts_EncryptedMessages() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input 
        String promptWordOne = "play";
        String promptWordTwo = "fox";
        String promptWordThree = "over";
        String input = "csci\n3\n" + promptWordOne + "\n" + promptWordTwo + "\n" + promptWordThree + "\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String promptResponseOne =  getPromptMessageToVerify(outputStream, promptWordOne);
        String promptResponseTwo =  getPromptMessageToVerify(outputStream, promptWordTwo);
        String promptResponseThree =  getPromptMessageToVerify(outputStream, promptWordThree);

        // Validate results
        assertTrue(promptResponseOne.contains("sodb"));
        assertTrue(promptResponseTwo.contains("ira"));
        assertTrue(promptResponseThree.contains("ryhu"));
        
	}
	
	@Test
	@DisplayName("Only prompted and encrypting messages as many times as asked")
	@org.junit.jupiter.api.Order(10)
	void encrypt_MultipleMessagePrompts_AsManyEncryptedMessagesAsAsked() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input 
        String promptWordOne = "play";
        String promptWordTwo = "fox";
        String promptWordThree = "over";
        String input = "csci\n2\n" + promptWordOne + "\n" + promptWordTwo + "\n" + promptWordThree + "\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String promptResponseOne =  getPromptMessageToVerify(outputStream, promptWordOne);
        String promptResponseTwo =  getPromptMessageToVerify(outputStream, promptWordTwo);

        // validate results
        assertTrue(promptResponseOne.contains("sodb"));
        assertTrue(promptResponseTwo.contains("ira"));
        assertFalse(outputStream.toString().contains("ryhu"));
        
	}


	@ParameterizedTest
    @CsvSource({
        "5, play, uqfd",
    	"2, csci, euek"
    })
	@DisplayName("Can change SHIFT")
	@org.junit.jupiter.api.Order(11)
	void encrypt_SHIFTModification_EncodedWordBasedOnNewShift(int shift, String word, String expectedEncryption) { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input
        String input = word + "\n0\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.SHIFT = shift;
        EncriptionMachine.main(new String[0]);
        EncriptionMachine.SHIFT = 3; //putting it back to default
        
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String promptResponse =  getPromptMessageToVerify(outputStream, word);        

        // Validate results
        assertTrue(promptResponse.contains(expectedEncryption));
        
	}
	

	@Test
	@DisplayName("Passing nothing to be encrypted doesn't break")
	@org.junit.jupiter.api.Order(12)
	void encrypt_EmptyPrompts_SuccessfulEncodeNothingAsManyTimesAsAsked() { 
		// Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save original System.out
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        // Simulate user input 
        String input = "\n2\n\n\n";  
        System.setIn(new ByteArrayInputStream(input.getBytes()));  // Redirect System.in

        //Run the encryption
        EncriptionMachine.main(new String[0]);
		
		// Restore original System.out
        System.setOut(originalOut);
        
        // Check the captured output
        String output = outputStream.toString();       
        //Based on manual testing know that the prompt will say 'Next word' so splitting on
        //that should give us three sections, one before the first 'Next word', one in between,
        //and one after the last
        var outputSections = output.split("Next word"); 

        // validate results
        assertEquals(3, outputSections.length);
        
	}
	
}