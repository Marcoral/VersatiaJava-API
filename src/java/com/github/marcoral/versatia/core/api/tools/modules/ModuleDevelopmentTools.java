package com.github.marcoral.versatia.core.api.tools.modules;

import com.github.marcoral.versatia.core.api.VersatiaConstants;
import com.github.marcoral.versatia.core.api.modules.VersatiaModuleInitializer;
import com.github.marcoral.versatia.core.api.modules.submodules.VersatiaModules;
import com.github.marcoral.versatia.core.api.tools.ExternalDependency;
import com.github.marcoral.versatia.core.api.tools.VersatiaTools.NoBridgeFieldException;

public abstract class ModuleDevelopmentTools {
	public static final String INSTANCE_KEY = "INSTANCE";
	
	/**
	 * Convenience method for registering {@link StandardSubmoduleHandlerProvider}.
	 * @param interfaceClass API class of provider
	 * @param provider Provider instance
	 * @param initializer Module introducing the provider
	 * @param <T> VersatiaSubmoduleHandlerProvider
	 */
	public static <T extends StandardSubmoduleHandlerProvider> void setupStandardSubmoduleHandlerProvider(Class<T> interfaceClass, T provider, VersatiaModuleInitializer initializer) {
		getInstance().injectExternalDependencyImpl(interfaceClass, null, INSTANCE_KEY, provider, true);
		initializer.addSubmodule(provider);
		VersatiaModules.registerSubmoduleHandlerProvider(interfaceClass, (T) provider);
	}
	
	/**
	 * Injects value to field annotated with {@link com.github.marcoral.versatia.core.api.tools.ExternalDependency}
	 * @param <T> expected type of returned value
	 * @param containingClass Class containing annotated field
	 * @param instance Instance to inject field to
	 * @param fieldKey Annotation value
	 * @param value Value to inject
	 * @param fieldSurelyExists Whether you are sure that field exists
	 * @throws NoBridgeFieldException Thrown if <code>fieldSurelyExists</code> parameter is set to <code>true</code>, but no field have been found
	 */
    public static <T> void injectExternalDependency(Class<? super T> containingClass, T instance, String fieldKey, Object value, boolean fieldSurelyExists) throws NoBridgeFieldException {
        getInstance().injectExternalDependencyImpl(containingClass, instance, fieldKey, value, fieldSurelyExists);
    }
    
	/**
	 * Gets value from field annotated with {@link com.github.marcoral.versatia.core.api.tools.ExternalDependency}
	 * @param <T> expected type of returned value
	 * @param instance Instance to get field value from
	 * @param keyOfExternal Annotation value
	 * @return Returns value at requested bridge field
	 * @throws RuntimeException Thrown if <code>fieldSurelyExists</code> parameter is set to <code>true</code>, but no field have been found
	 */
    public static <T> T getExternalField(Object instance, String keyOfExternal) throws NoBridgeFieldException {
        return getInstance().getExternalFieldImpl(instance, keyOfExternal);
    }
    
    /* -------
     * BOILERPLATE
     * -------- */
    @ExternalDependency(ModuleDevelopmentTools.INSTANCE_KEY)
    private static ModuleDevelopmentTools INSTANCE = null;

    private static ModuleDevelopmentTools getInstance() {
        if (INSTANCE == null)
            throw new RuntimeException(VersatiaConstants.NOT_LOADED_YET);
        return INSTANCE;
    }
    
    protected abstract <T> void injectExternalDependencyImpl(Class<? super T> containingClass, T instance, String fieldKey, Object value, boolean fieldSurelyExists);
    protected abstract <T> T getExternalFieldImpl(Object instance, String keyOfExternal);
}
