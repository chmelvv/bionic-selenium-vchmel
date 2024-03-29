import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class HelloWorldTest {

    // Copyleft from http://professorjava.weebly.com/isprime.html
    public static Boolean isPrime(int num){ //method signature. returns Boolean, true if number isPrime, false if not
        if(num==2){ //for case num=2, function returns true. detailed explanation underneath
            return(true);
        }
        for(int i=2;i<=(int)Math.sqrt(num)+1;i++){ //loops through 2 to sqrt(num). All you need to check- efficient
            if(num%i==0){ //if a divisor is found, its not prime. returns false
                return(false);
            }
        }
        return(true); //if all cases don't divide num, it is prime.
    }

    @Test
    public void info(){
        Assert.assertTrue(isPrime(5));
    }
}