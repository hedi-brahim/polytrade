/*
 * =============================================================================
 * 
 *   Copyright (c) 2016-2017, The Polymec team (http://www.polymec.com)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package com.polymec.domain;



public enum Role {
    
    ADMIN("ROLE_ADMIN"), 
    MANAGER("ROLE_MANAGER"), 
    USER("ROLE_USER");

    
    public static final Role[] ALL = { ADMIN, MANAGER, USER };
    
    
    private final String name;

    
    
    public static Role forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for role");
        }
        if (name.toUpperCase().equals("ROLE_ADMIN")) {
            return ADMIN;
        } else if (name.toUpperCase().equals("ROLE_MANAGER")) {
            return MANAGER;
        } else if (name.toUpperCase().equals("ROLE_USER")) {
            return USER;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any Role");
    }
    
    
    private Role(final String name) {
        this.name = name;
    }
    
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
}
