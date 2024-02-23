package fr.rayandfz.back.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Utility class for bean manipulation.
 */
public class Utils {

    /**
     * Copies properties from one object to another, ignoring {@code null} values.
     * <p>
     * This method copies non-null properties from the source object to the target object.
     * Properties that have {@code null} values in the source object are ignored, and not copied to the target object.
     * This is particularly useful for partial updates of object properties.
     * </p>
     *
     * @param src    the source object from which to copy properties
     * @param target the target object to which properties should be copied
     */
    public static void copyNonNullProperties(final Object src, final Object target) {
        BeanWrapper srcWrap = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = srcWrap.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        Stream.of(pds).forEach(pd -> {
            Object srcValue = srcWrap.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        });

        String[] result = emptyNames.toArray(new String[0]);
        BeanUtils.copyProperties(src, target, result);
    }
}
