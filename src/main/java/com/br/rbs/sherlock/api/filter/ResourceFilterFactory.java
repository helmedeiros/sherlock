package com.br.rbs.sherlock.api.filter;

import java.util.ArrayList;
import java.util.List;
 
import javax.ws.rs.ext.Provider;

import com.br.rbs.sherlock.api.security.SecurityContextFilter;
import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
 
/**
 * FilterFactory to create List of request/response filters to be applied on a particular
 * AbstractMethod of a resource.
 * @author helmedeiros
 */
@Provider  // register as jersey's provider
public class ResourceFilterFactory extends RolesAllowedResourceFilterFactory {
 
    private SecurityContextFilter securityContextFilter = new SecurityContextFilter();
 
    @Override
    public List<ResourceFilter> create(AbstractMethod am) {
        // get filters from RolesAllowedResourceFilterFactory Factory!
        List<ResourceFilter> rolesFilters = super.create(am);
        if (null == rolesFilters) {
            rolesFilters = new ArrayList<ResourceFilter>();
        }
 
        // Convert into mutable List, so as to add more filters that we need
        // (RolesAllowedResourceFilterFactory generates immutable list of filters)
        List<ResourceFilter> filters = new ArrayList<ResourceFilter>(rolesFilters);
 
        // Load SecurityContext first (this will load security context onto request)
        filters.add(0, securityContextFilter);

        return filters;
    }
}