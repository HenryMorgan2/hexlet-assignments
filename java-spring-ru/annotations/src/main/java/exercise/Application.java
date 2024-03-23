package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method: Address.class.getDeclaredMethods()){
            if (method.isAnnotationPresent(Inspect.class)){

                System.out.println("Method " + method.getName() + " returns a value of type " + ((method.getGenericReturnType() == (Type)java.lang.String.class) ? "String": method.getGenericReturnType()));
            }
        }
        // END
    }
}
