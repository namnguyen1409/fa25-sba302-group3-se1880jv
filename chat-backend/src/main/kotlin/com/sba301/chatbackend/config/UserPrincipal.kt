package com.sba301.chatbackend.config

import java.security.Principal

class UserPrincipal(val userId: String) : Principal {
    override fun getName(): String = userId
}
