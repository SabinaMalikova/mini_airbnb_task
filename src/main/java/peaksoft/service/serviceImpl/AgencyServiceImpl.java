package peaksoft.service.serviceImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AgencyServiceImpl {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface PhoneNumberConstraint {
        String message() default "Invalid phone number";

        String regex() default "^\\+996\\d{13}$";
    }
}
