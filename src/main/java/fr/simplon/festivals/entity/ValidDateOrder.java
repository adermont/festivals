package fr.simplon.festivals.entity;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.chrono.ChronoLocalDate;

/**
 * Classe réalisée à partir du tutoriel <a href="https://www.baeldung.com/spring-mvc-custom-validator">Spring MV Custom
 * Validation</a>.
 */
@Constraint(validatedBy = ValidDateOrder.FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateOrder
{
    /**
     * Cette valeur doit être soit directement le message transmis à l'utilisateur, ou bien l'identifiant du message
     * entre accolades. Et dans ce dernier cas, Spring ira chercher le message dans un fichier messages.properties
     * corresponsant à la langue de l'utilisateur.
     *
     * @return Le message d'erreur.
     */
    String message() default "{fr.simplon.festivals.entity.ValidDateOrder}";

    /**
     * Des noms de classessssss (ou d'interfaces) qui servent juste à faire varier les règles qu'on veut appliquer dans
     * certains cas.
     *
     * @return La liste des groupes de validation à appliquer.
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return Nom de l'attribut qui représente la date de début qu'on doit vérifier.
     */
    String dateMin();

    /**
     * @return Nom de l'attribut qui représente la date de fin qu'on doit vérifier.
     */
    String dateMax();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List
    {
        ValidDateOrder[] value();
    }

    class FieldsValueMatchValidator implements ConstraintValidator<ValidDateOrder, Object>
    {
        private String dateMinField;
        private String dateMaxField;

        /**
         * On surcharge la méthode {@link #initialize(ValidDateOrder)} pour stocker les noms des champs.
         *
         * @param annotation
         */
        public void initialize(ValidDateOrder annotation)
        {
            this.dateMinField = annotation.dateMin();
            this.dateMaxField = annotation.dateMax();
        }

        /**
         * Méthode permettant de valider un objet.
         *
         * @param value   L'objet à valider.
         * @param context Le contexte de validation.
         * @return {@code true} si l'objet est valide.
         */
        public boolean isValid(
                Object value, ConstraintValidatorContext context)
        {
            ChronoLocalDate fieldMinValue = (ChronoLocalDate) new BeanWrapperImpl(value).getPropertyValue(dateMinField);
            ChronoLocalDate fieldMaxValue = (ChronoLocalDate) new BeanWrapperImpl(value).getPropertyValue(dateMaxField);

            return fieldMinValue.compareTo(fieldMaxValue) <= 0;
        }
    }
}